/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.data;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 *
 * @author aris
 */
public class UserDAO {

    DataSource ds;

    public void UserDAO() {
    }

    public int insertUser(String name, String address, String login, String password) throws Exception {
        int result = -1;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into auction.users (name,address,login,password) values(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, login);
            ps.setString(4, password);
            result = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw e;
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

    public UserTO findUser(String login, String password) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/auction");
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("select * from auction.users u where u.login=? and u.password=?");
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            UserTO user = null;
            if (rs.next()) {
                user = new UserTO();
                user.setUserId(rs.getInt("ID"));
                user.setAddress(rs.getString("ADDRESS"));
                user.setLogin(rs.getString("LOGIN"));
                user.setName(rs.getString("NAME"));
                user.setPassword(rs.getString("PASSWORD"));
            }
            conn.commit();
            return user;
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
}
