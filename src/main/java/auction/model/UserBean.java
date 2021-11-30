/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.model;

/**
 *
 * @author aris
 */
public class UserBean {

    boolean loggedIn = false;
    String userId = "";
    String name = "";
    String login = "";
    String password = "";
    String address = "";

    public void UserBean() {
    }

    public void setUserId(String u) {
        this.userId = u;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setLoggedIn(boolean b) {
        this.loggedIn = b;
    }

    public boolean getLoggedIn() {
        return this.loggedIn;
    }

    public void setLogin(String l) {
        this.login = l;
    }

    public String getLogin() {
        return this.login;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String a) {
        this.address = a;
    }

    public String getAddress() {
        return this.address;
    }

    public void reset() {
        this.userId = "";
        this.login = "";
        this.password = "";
        this.address = "";
        this.name = "";
        this.loggedIn = false;
    }
}
