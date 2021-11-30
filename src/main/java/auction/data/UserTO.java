/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.data;

/**
 *
 * @author aris
 */
public class UserTO  implements java.io.Serializable {

    private int userId;
    private String name;
    private String address;
    private String login;
    private String password;

    public void setUserId(int u) {
        this.userId = u;
    }

    public int getUserId() {
        return this.userId;
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
}
