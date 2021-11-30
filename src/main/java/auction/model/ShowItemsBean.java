/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.model;

import auction.data.ItemDAO;
import auction.data.ItemTO;
import java.util.ArrayList;
import auction.util.Validator;

/**
 *
 * @author aris
 */
public class ShowItemsBean {

    private String keyword = "";
    private String criteria = "";
    private String itemId = "";
    private String bidSum = "";
    private ArrayList itemList;
    private ItemDAO itemDAO;

    public void ShowItemsBean() {
    }

    public ArrayList getItemList() {
        return this.itemList;
    }

    public void setItemId(String i) {
        this.itemId = i;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setKeyword(String s) {
        this.keyword = s;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setCriteria(String c) {
        this.criteria = c;
    }

    public String getCriteria() {
        return this.criteria;
    }

    public void setBidSum(String b) {
        this.bidSum = b;
    }

    public String getBidSum() {
        return this.bidSum;
    }
//------------------------------------------------------------------------------

    public void getItems() throws Exception {
        if (this.itemDAO == null) {
            this.itemDAO = new ItemDAO();
        }

        try {
            itemList = itemDAO.getItemList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Item List load failed due Exception, try again later");
        }
        if (itemList == null) {
            throw new Exception("Item List load failed due Exception, try again later");
        }
        if (itemList.size() == 0) {
            throw new Exception("No items found");
        }

    }

    public void searchItems(String keyword, String criteria) throws Exception {
        //validation
        if (Validator.IsEmpty(keyword)) {
            throw new Exception("Please, fill Keyword field");
        }
        if (Validator.IsEmpty(criteria)) {
            throw new Exception("Please, select Criteria field");
        }

        if (this.itemDAO == null) {
            this.itemDAO = new ItemDAO();
        }

        try {
            itemList = itemDAO.searchItem(keyword, criteria);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Search failed due Exception, try again later");
        }

        if (itemList == null) {
            throw new Exception("Search failed due Exception, try again later");
        }
        if (itemList.size() == 0) {
            throw new Exception("Items not found ");
        }



    }

    public void getMyItems(String userId) throws Exception {
        if (Validator.IsEmpty(userId) || !Validator.IsPosInt(userId)) {
            throw new Exception("Unknown userId, no item to select");
        }

        if (this.itemDAO == null) {
            this.itemDAO = new ItemDAO();
        }

        try {
            itemList = itemDAO.getMyItems(Integer.valueOf(userId));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Getting my items failed due Exception, try again later");
        }

        if (itemList == null) {
            throw new Exception("Getting my items failed due Exception, try again later");
        }
        if (itemList.size() == 0) {
            throw new Exception("Items not found ");
        }


    }

    public void bidItem(String userId) throws Exception {
        if (Validator.IsEmpty(this.itemId) || !Validator.IsPosInt(this.itemId)) {
            throw new Exception("Unknown ItemId, no item to delete");
        }
        if (Validator.IsEmpty(this.bidSum) || !Validator.IsPosFloat(this.bidSum)) {
            throw new Exception("Bid field must be positive float");
        }        
        ItemTO item = itemDAO.loadItem(Integer.valueOf(itemId));        
        if (Float.parseFloat(this.bidSum) < item.getBidInc().floatValue()) {
            throw new Exception("Bid field must not be lesser "+item.getBidInc());
        }
        

        int result = -1;
        try {
            result = itemDAO.bidItem(Integer.valueOf(this.itemId), Float.valueOf(this.bidSum), Integer.valueOf(userId));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Item bid failed due Exception, try again later");
        }
        if (result < 0) {
            throw new Exception("Item bid failed due Exception, try again later");
        }
    }

    public int buyItem(String userId) throws Exception {
        if (Validator.IsEmpty(this.itemId) || !Validator.IsPosInt(this.itemId)) {
            throw new Exception("Unknown ItemId, no item to buy");
        }
        int result = -1;
        try {
            result = itemDAO.buyItem(Integer.valueOf(this.itemId), Integer.valueOf(userId));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Item buy failed due Exception, try again later");
        }
        if (result < 0) {
            throw new Exception("Item buy failed due Exception, try again later");
        }
        return result;
    }

    public void deleteItem() throws Exception {
        if (Validator.IsEmpty(this.itemId) || !Validator.IsPosInt(this.itemId)) {
            throw new Exception("Unknown ItemId, no item to delete");
        }

        int result = -1;
        try {
            result = itemDAO.deleteItem(Integer.valueOf(this.itemId));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Item delete failed due Exception, try again later");
        }
        if (result < 0) {
            throw new Exception("Item delete failed due Exception, try again later");
        }
    }

    public void reset() {
        keyword = "";
        criteria = "";
        itemId = "";
        bidSum = "";
    }

    public void prepareBill(String address, String name, int itemId) throws Exception {
        try {
            ItemTO item = itemDAO.loadItem(Integer.valueOf(itemId));
            
            System.out.println("********** Auction Bill *************");
            System.out.println(" Dear "+name);
            System.out.println(" You just won in our auction ");
            System.out.println(" Lot № "+item.getId());
            System.out.println(" Item Name is "+ item.getTitle());
            System.out.println(" Cost =  " + item.getBestOffer());
            System.out.println(" This bill will be sent at your address: " + address);
            System.out.println("**************************************");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Bill prepare failed due Exception");
        }
    }
}
