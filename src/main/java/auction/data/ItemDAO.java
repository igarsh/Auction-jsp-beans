/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.data;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;
import javax.naming.*;

/**
 *
 * @author aris
 */
public class ItemDAO {

    private DataSource ds;

    public void ItemDAO() {
    }

    public int deleteItem(Integer itemId) throws Exception {
        int result = -1;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(
                    "delete from items where items.id=?");
            ps.setObject(1, itemId);
            result = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public int insertItem(ItemTO item) throws Exception {
        int result = -1;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(
                    "insert into auction.items " +
                    "(title, " +
                    "description, " +
                    "seller, " +
                    "start_price, " +
                    "bid_inc, " +
                    "stop_date, " +
                    "buy_it_now) " +
                    "values(?,?,?,?,?,?,?)");
            ps.setObject(1, item.getTitle());
            ps.setObject(2, item.getDescription());
            ps.setObject(3, item.getSeller());
            ps.setObject(4, item.getStartPrice());
            ps.setObject(5, item.getBidInc());
            ps.setObject(6, item.getStopDate());
            ps.setObject(7, item.getBuyNow());

            ps.executeUpdate();

            ResultSet rs = conn.createStatement().executeQuery("select LAST_INSERT_ID() as last_id");
            if (rs.next()) {
                result = rs.getInt("last_id");
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public int updateItem(ItemTO item) throws Exception {
        int result = -1;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(
                    " update items set " +
                    " title = ?, " +
                    " description = ?, " +
                    " start_price = ?, " +
                    " bid_inc = ?, " +
                    " stop_date = ?, " +
                    " buy_it_now = ? " +
                    " where id = ? ");
            ps.setObject(1, item.getTitle());
            ps.setObject(2, item.getDescription());
            ps.setObject(3, item.getStartPrice());
            ps.setObject(4, item.getBidInc());
            ps.setObject(5, item.getStopDate());
            ps.setObject(6, item.getBuyNow());
            ps.setObject(7, item.getId());
            result = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public ItemTO loadItem(Integer itemId) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("select * from auction.items t where t.id=?");
            ps.setObject(1, itemId);
            rs = ps.executeQuery();
            ItemTO item = new ItemTO();
            if (rs.next()) {
                item.setId((Integer) rs.getObject("ID"));
                item.setTitle((String) rs.getObject("TITLE"));
                item.setDescription((String) rs.getObject("DESCRIPTION"));
                item.setStartPrice((Float) rs.getObject("START_PRICE"));
                item.setBestOffer((Float) rs.getObject("BEST_OFFER"));
                item.setBidInc((Float) rs.getObject("BID_INC"));
                item.setStopDate((Long) rs.getObject("STOP_DATE"));
                item.setBuyNow((Integer) rs.getObject("BUY_IT_NOW"));
            }
            conn.commit();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    public ArrayList getItemList() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList itemList = new ArrayList();

        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(
                    " SELECT " +
                    " items.ID, " +
                    " items.TITLE, " +
                    " items.DESCRIPTION, " +
                    " items.SELLER, " +
                    " items.START_PRICE, " +
                    " items.BID_INC, " +
                    " items.BEST_OFFER, " +
                    " items.BIDDER, " +
                    " items.STOP_DATE, " +
                    " items.BUY_IT_NOW, " +
                    " items.SOLD, " +
                    " u1.NAME AS SELLER_NAME, " +
                    " u2.NAME AS BIDDER_NAME " +
                    " FROM " +
                    " items " +
                    " INNER JOIN users u1 ON (items.SELLER = u1.ID) " +
                    " LEFT OUTER JOIN users u2 ON (items.BIDDER = u2.ID) " +
                    " ORDER BY ID");
            rs = ps.executeQuery();

            ItemTO item = null;

            while (rs.next()) {
                item = new ItemTO();
                item.setId((Integer) rs.getObject("ID"));
                item.setTitle((String) rs.getObject("TITLE"));
                item.setDescription((String) rs.getObject("DESCRIPTION"));
                item.setSeller((Integer) rs.getObject("SELLER"));
                item.setStartPrice((Float) rs.getObject("START_PRICE"));
                item.setBidInc((Float) rs.getObject("BID_INC"));
                item.setBestOffer((Float) rs.getObject("BEST_OFFER"));
                item.setStopDate((Long) rs.getObject("STOP_DATE"));
                item.setBuyNow((Integer) rs.getObject("BUY_IT_NOW"));
                item.setSellerName((String) rs.getObject("SELLER_NAME"));
                item.setBidderName((String) rs.getObject("BIDDER_NAME"));
                item.setSold((Integer) rs.getObject("SOLD"));
                itemList.add(item);
            }
            conn.commit();
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    public ArrayList searchItem(String keyword, String criteria) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList itemList = new ArrayList();

        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(
                    " SELECT " +
                    " items.ID, " +
                    " items.TITLE, " +
                    " items.DESCRIPTION, " +
                    " items.SELLER, " +
                    " items.START_PRICE, " +
                    " items.BID_INC, " +
                    " items.BEST_OFFER, " +
                    " items.BIDDER, " +
                    " items.STOP_DATE, " +
                    " items.BUY_IT_NOW, " +
                    " items.SOLD, " +
                    " u1.NAME AS SELLER_NAME, " +
                    " u2.NAME AS BIDDER_NAME " +
                    " FROM " +
                    " items " +
                    " INNER JOIN users u1 ON (items.SELLER = u1.ID) " +
                    " LEFT OUTER JOIN users u2 ON (items.BIDDER = u2.ID) " +
                    " WHERE items." + criteria + " like UPPER(?) " +
                    " ORDER BY ID");
            ps.setString(1, "%" + keyword.toUpperCase() + "%");
            rs = ps.executeQuery();

            ItemTO item = null;

            while (rs.next()) {
                item = new ItemTO();
                item.setId((Integer) rs.getObject("ID"));
                item.setTitle((String) rs.getObject("TITLE"));
                item.setDescription((String) rs.getObject("DESCRIPTION"));
                item.setSeller((Integer) rs.getObject("SELLER"));
                item.setStartPrice((Float) rs.getObject("START_PRICE"));
                item.setBidInc((Float) rs.getObject("BID_INC"));
                item.setBestOffer((Float) rs.getObject("BEST_OFFER"));
                item.setStopDate((Long) rs.getObject("STOP_DATE"));
                item.setBuyNow((Integer) rs.getObject("BUY_IT_NOW"));
                item.setSellerName((String) rs.getObject("SELLER_NAME"));
                item.setBidderName((String) rs.getObject("BIDDER_NAME"));
                item.setSold((Integer) rs.getObject("SOLD"));
                itemList.add(item);
            }
            conn.commit();
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    public ArrayList getMyItems(Integer userId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList itemList = new ArrayList();

        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(
                    " SELECT " +
                    " items.ID, " +
                    " items.TITLE, " +
                    " items.DESCRIPTION, " +
                    " items.SELLER, " +
                    " items.START_PRICE, " +
                    " items.BID_INC, " +
                    " items.BEST_OFFER, " +
                    " items.BIDDER, " +
                    " items.STOP_DATE, " +
                    " items.BUY_IT_NOW, " +
                    " items.SOLD, " +
                    " u1.NAME AS SELLER_NAME, " +
                    " u2.NAME AS BIDDER_NAME " +
                    " FROM " +
                    " items " +
                    " INNER JOIN users u1 ON (items.SELLER = u1.ID) " +
                    " LEFT OUTER JOIN users u2 ON (items.BIDDER = u2.ID) " +
                    " WHERE items.SELLER = ?" +
                    " ORDER BY ID");
            ps.setObject(1, userId);
            rs = ps.executeQuery();

            ItemTO item = null;

            while (rs.next()) {
                item = new ItemTO();
                item.setId((Integer) rs.getObject("ID"));
                item.setTitle((String) rs.getObject("TITLE"));
                item.setDescription((String) rs.getObject("DESCRIPTION"));
                item.setSeller((Integer) rs.getObject("SELLER"));
                item.setStartPrice((Float) rs.getObject("START_PRICE"));
                item.setBidInc((Float) rs.getObject("BID_INC"));
                item.setStopDate((Long) rs.getObject("STOP_DATE"));
                item.setBuyNow((Integer) rs.getObject("BUY_IT_NOW"));
                item.setSellerName((String) rs.getObject("SELLER_NAME"));
                item.setBidderName((String) rs.getObject("BIDDER_NAME"));
                item.setSold((Integer) rs.getObject("SOLD"));
                itemList.add(item);
            }
            conn.commit();
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    public int bidItem(Integer itemId, Float bidSum, Integer userId) throws Exception {
        int result = -1;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update items set best_offer = ?, bidder = ?  where id= ?");
            ItemTO item = loadItem(itemId);

            if (item.getBestOffer() == null) {
                ps.setObject(1, item.getStartPrice() + bidSum);
            } else {
                ps.setObject(1, item.getBestOffer() + bidSum);
            }
            ps.setObject(2, userId);
            ps.setObject(3, itemId);
            result = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public int buyItem(Integer itemId, Integer userId) throws Exception {
        int result = -1;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update items set best_offer = ?, bidder = ?,  sold = 1  where id= ?");
            ItemTO item = loadItem(itemId);
            ps.setObject(1, item.getStartPrice());
            ps.setObject(2, userId);
            ps.setObject(3, itemId);
            result = ps.executeUpdate();
            if (result > 0) {
                result = itemId.intValue();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new Exception(e);
        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }
}
