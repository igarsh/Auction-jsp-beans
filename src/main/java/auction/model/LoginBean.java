/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.model;

import auction.data.UserDAO;
import auction.data.UserTO;

/**
 *
 * @author aris
 */
public class LoginBean {

    private String login = "";
    private String password = "";
    private UserTO user;
    private UserDAO userDAO;

    public void LoginBean() {
    }
    
    public void setUserDAO(UserDAO u){
        this.userDAO = u;
    }
    
    public UserDAO getUserDAO(){
        return this.userDAO;
    }

    public UserTO getUser() {
        return this.user;
    }
    
    public void setUser(UserTO u){
        this.user = u;
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

    public void findUser() throws Exception {
        //валидация
        if (this.login != null && login.trim().equals("")) {
            throw new Exception("Please, fill Login field");
        }
        if (this.password != null && password.equals("")) {
            throw new Exception("Please, fill Password field");
        }

        try {
            this.user = userDAO.findUser(login, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Login failed due Exception, try again later");
        }
        if (this.user == null) {
            throw new Exception("No User found, please check login and(or) password");
        }
    }

    public void reset() {
        this.login = "";
        this.password = "";
    }
}
