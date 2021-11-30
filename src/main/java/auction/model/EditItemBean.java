/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.model;

import auction.data.ItemDAO;
import auction.data.ItemTO;
import java.util.Calendar;
import auction.util.Validator;

/**
 *
 * @author aris
 */
public class EditItemBean {

    private String itemId = "";
    private String title = "";
    private String description = "";
    private String startPrice = "";
    private String bidInc = "";
    private String timeLeft = "";
    private String buyNow = "";
    private ItemDAO itemDAO;

    public void EditItemBean() {
    }

    public void setItemId(String i) {
        this.itemId = i;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStartPrice(String s) {
        this.startPrice = s;
    }

    public String getStartPrice() {
        return this.startPrice;
    }

    public void setBidInc(String b) {
        this.bidInc = b;
    }

    public String getBidInc() {
        return this.bidInc;
    }

    public void setTimeLeft(String t) {
        this.timeLeft = t;
    }

    public String getTimeLeft() {
        return this.timeLeft;
    }

    public void setBuyNow(String s) {
        this.buyNow = s;
    }

    public String getBuyNow() {
        return this.buyNow;
    }

    public int insertItem(String seller) throws Exception {

        //валидация полей
        if (Validator.IsEmpty(seller)) {
            throw new Exception("Uknown seller, bidding rejected");
        }
        if (Validator.IsEmpty(title)) {
            throw new Exception("Please, fill Title field");
        }
        if (Validator.IsEmpty(description)) {
            throw new Exception("Please, fill Description field");
        }

        if (Validator.IsEmpty(this.startPrice) || !Validator.IsPosFloat(this.startPrice)) {
            throw new Exception("StartPrice field must be positive float, not empty");
        }

        if (!this.getBuyNow().equals("checked")) {
            if (Validator.IsEmpty(this.bidInc) || !Validator.IsPosFloat(this.bidInc)) {
                throw new Exception("Bid inc field must be positive float, not empty");
            }
        }

        if (Validator.IsEmpty(this.timeLeft) || !Validator.IsPosInt(this.timeLeft)) {
            throw new Exception("Time left field must be positive integer, not empty");
        }
        
        if( Integer.parseInt(this.timeLeft) == 0 ){
            throw new Exception("Time left field must be at least 1 day");
        }

        if (this.itemDAO == null) {
            this.itemDAO = new ItemDAO();
        }

        ItemTO item = new ItemTO();
        item.setTitle(this.title);
        item.setDescription(this.description.trim());
        item.setSeller(Integer.valueOf(seller));
        item.setStartPrice(Float.valueOf(this.startPrice));
        if (!this.getBuyNow().equals("checked")) {
            item.setBidInc(Float.valueOf(this.bidInc));
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.DAY_OF_MONTH, Integer.parseInt(this.timeLeft));
        item.setStopDate(rightNow.getTime().getTime());

        if (this.buyNow.equals("checked")) {
            item.setBuyNow(new Integer(1));
            item.setBidInc(null);
        } else {
            item.setBuyNow(new Integer(0));
        }

        int newItem = -1;
        try {
            newItem = itemDAO.insertItem(item);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Item sell failed due Exception, try again later");
        }
        if (newItem < 0) {
            throw new Exception("Item sell failed due Exception, try again later");
        }
        return newItem;
    }

    public void reset() {
        title = "";
        description = "";
        startPrice = "";
        timeLeft = "";
        buyNow = "";
        bidInc = "";
    }
}
