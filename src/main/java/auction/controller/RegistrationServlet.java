/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import auction.model.RegistrationBean;
import auction.model.UserBean;
import auction.model.LoginBean;
import auction.data.UserDAO;

/**
 *
 * @author aris
 */
public class RegistrationServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String target = "Registration.jsp";
        String action = request.getServletPath().substring(1);
        RegistrationBean rb = (RegistrationBean) request.getSession().getAttribute("RegistrationBean");
        UserBean ub = (UserBean) request.getSession().getAttribute("UserBean");
        LoginBean lb = (LoginBean) request.getSession().getAttribute("LoginBean");
        UserDAO userDAO = (UserDAO) request.getSession().getAttribute("UserDAO");
        
        String name = request.getParameter("Name");
        String address = request.getParameter("Address");
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        String rePassword = request.getParameter("RePassword");

        //Start Init Servlet
        if (rb == null) {
            rb = new RegistrationBean();
            request.getSession().setAttribute("RegistrationBean", rb);
        }
        
        if (ub == null) {
            ub = new UserBean();
            request.getSession().setAttribute("UserBean", ub);
        }
        
        if (lb == null) {
            lb = new LoginBean();
            request.getSession().setAttribute("LoginBean", lb);
        }
        
        if (userDAO == null) {
            userDAO = new UserDAO();
            request.getSession().setAttribute("UserDAO", userDAO);
        }

        if (userDAO != null) {
            rb.setUserDAO(userDAO);
        }
        
        if (name != null) {
            rb.setName(name);
        }
        if (address != null) {
            rb.setAddress(address);
        }
        if (login != null) {
            rb.setLogin(login);
        }
        if (password != null) {
            rb.setPassword(password);
        }
        if (rePassword != null){
            rb.setRePassword(rePassword);
        }
        // End of Init Servlet

        if (action.equals("registration")) {
            ub.reset();
            rb.reset();
            target = "Registration.jsp";
        } else if (action.equals("register")) {
            try {
                rb.register();
                lb.setLogin(login);
                lb.setPassword(password);                                
                target = "log";                
            } catch (Exception ex) {
                //rb.reset();
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "Registration.jsp";
            }

        }

        response.sendRedirect(response.encodeRedirectURL(target));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
