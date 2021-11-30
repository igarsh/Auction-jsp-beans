/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import auction.model.LoginBean;
import auction.model.UserBean;
import auction.data.UserDAO;

/**
 *
 * @author aris
 */
public class LoginServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String target = "Login.jsp";
        String action = request.getServletPath().substring(1);

        LoginBean lb = (LoginBean) request.getSession().getAttribute("LoginBean");
        UserBean ub = (UserBean) request.getSession().getAttribute("UserBean");
        UserDAO userDAO = (UserDAO) request.getSession().getAttribute("UserDAO");

        String login = request.getParameter("Login");
        String password = request.getParameter("Passw");

        //Start Init Servlet

        if (lb == null) {
            lb = new LoginBean();
            request.getSession().setAttribute("LoginBean", lb);
        }

        if (ub == null) {
            ub = new UserBean();
            request.getSession().setAttribute("UserBean", ub);
        }

        if (login != null) {
            lb.setLogin(login);
        }
        if (password != null) {
            lb.setPassword(password);
        }
        if (userDAO == null) {
            userDAO = new UserDAO();
            request.getSession().setAttribute("UserDAO", userDAO);
        }

        if (userDAO != null) {
            lb.setUserDAO(userDAO);
        }

        // End of Init Servlet

        if (action.equals("login")) {
            lb.reset();
            ub.reset();
            target = "Login.jsp";
        } else if (action.equals("log")) {
            try {
                lb.findUser();
                //в случае удачного логина заполнить UserBean инфой
                ub.setUserId(String.valueOf(lb.getUser().getUserId()));
                ub.setName(lb.getUser().getName());
                ub.setLogin(lb.getUser().getLogin());
                ub.setPassword(lb.getUser().getPassword());
                ub.setAddress(lb.getUser().getAddress());
                ub.setLoggedIn(true);
                lb.reset();
                target = "showitems";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "Login.jsp";
            }

        } else if (action.equals("logout")) {
            if (ub.getLoggedIn()) {
                //some cleaning actions
                ub.reset();
                request.getSession().invalidate();
            }
            request.getSession().setAttribute("GlobalMessage", "Logout successfully");
            target = "Login.jsp";
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
