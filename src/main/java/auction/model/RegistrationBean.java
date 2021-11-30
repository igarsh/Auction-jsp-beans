/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.model;

import auction.data.UserDAO;

/**
 *
 * @author aris
 */
public class RegistrationBean {

    private String name = "";
    private String login = "";
    private String password = "";
    private String rePassword = "";
    private String address = "";
    private UserDAO userDAO;

    public void RegistrationBean() {
    }
    
    public void setUserDAO(UserDAO u){
        this.userDAO = u;
    }
    
    public UserDAO getUserDAO(){
        return this.userDAO;
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

    public void setRePassword(String p) {
        this.rePassword = p;
    }

    public String getRePassword() {
        return this.rePassword;
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
        this.login = "";
        this.password = "";
        this.address = "";
        this.name = "";
        this.rePassword = "";
    }

    public void register() throws Exception {

        if (this.name.trim().equals("")) {
            throw new Exception("Please, fill Full Name field");
        }
        if (this.address.trim().equals("")) {
            throw new Exception("Please, fill Address field");
        }
        if (this.login.trim().equals("")) {
            throw new Exception("Please, fill Login field");
        }
        if (this.password.trim().equals("")) {
            throw new Exception("Please, fill Password field");
        }
        if (this.password.length() < 6) {
            throw new Exception("Password field can not contain less than 6 characters ");
        }
        if (this.rePassword != null && !rePassword.equals(password)) {
            throw new Exception("Password and re-entered password must be equal");
        }

        try {
            int newUser = userDAO.insertUser(name, address, login, password);
            if (newUser < 0) {
                throw new Exception("Registration failed due Exception");
            }            
        }
        catch (java.sql.SQLException se) {
            se.printStackTrace();
            if(se.getErrorCode() == 1062)
            throw new Exception("Registration failed. user with same login/name exists.");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Registration failed due Exception, uknown reason");
        }

    }
}
