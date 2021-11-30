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
public class TimerDAO {

    private DataSource ds;
    
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
    
    public int buyItem(Integer itemId) throws Exception {
        int result = -1;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update items set best_offer = ?, bidder = ?,  sold = 1  where id= ?");
            ItemTO item = new ItemTO();
            ItemDAO idao = new ItemDAO();
            item = idao.loadItem(itemId);
            ps.setObject(1, item.getBestOffer());
            ps.setObject(2, item.getBidder());
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
