/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.data;

import java.util.Date;

/**
 *
 * @author aris
 */
public class ItemTO implements java.io.Serializable {

    private Integer id;
    private String title;
    private String description;
    private Integer seller;
    private String sellerName;
    private Float startPrice;
    private Float bidInc;
    private Float bestOffer;
    private Integer bidder;
    private String bidderName;
    private Long stopDate;
    private Integer buyNow;
    private Integer sold;
    private String stopDateAsString;

    public void setId(Integer i) {
        this.id = i;
    }

    public Integer getId() {
        return this.id;
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

    public void setSeller(Integer s) {
        this.seller = s;
    }

    public Integer getSeller() {
        return this.seller;
    }

    public void setSellerName(String s) {
        this.sellerName = s;
    }

    public String getSellerName() {
        return this.sellerName;
    }

    public void setStartPrice(Float s) {
        this.startPrice = s;
    }

    public Float getStartPrice() {
        return this.startPrice;
    }

    public void setBidInc(Float b) {
        this.bidInc = b;
    }

    public Float getBidInc() {
        return this.bidInc;
    }

    public void setBestOffer(Float b) {
        this.bestOffer = b;
    }

    public Float getBestOffer() {
        return this.bestOffer;
    }

    public void setBidder(Integer b) {
        this.bidder = b;
    }

    public Integer getBidder() {
        return this.bidder;
    }

    public void setBidderName(String b) {
        this.bidderName = b;
    }

    public String getBidderName() {
        return this.bidderName;
    }

    public void setStopDate(Long t) {
        this.stopDate = t;
    }

    public Long getStopDate() {
        return this.stopDate;
    }

    public String getStopDateAsString() {
        if (this.stopDate == null || this.stopDate.toString().trim().equals("")) {
            return "";
        }
        return java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.MEDIUM,
                java.text.DateFormat.MEDIUM, new java.util.Locale("ru", "RU")).format(new Date(this.stopDate));
    }

    public void setStopDateAsString(String s) {
        this.stopDateAsString = s;
    }

    public void setBuyNow(Integer b) {
        this.buyNow = b;
    }

    public Integer getBuyNow() {
        return this.buyNow;
    }

    public void setSold(Integer s) {
        this.sold = s;
    }

    public Integer getSold() {
        return this.sold;
    }
}
