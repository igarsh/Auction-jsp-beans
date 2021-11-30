/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import auction.model.EditItemBean;
import auction.model.UserBean;
import auction.util.TimerBean;

/**
 *
 * @author aris
 */
public class EditItemServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String target = "EditItem.jsp";
        String action = request.getServletPath().substring(1);
        EditItemBean eib = (EditItemBean) request.getSession().getAttribute("EditItemBean");
        UserBean ub = (UserBean) request.getSession().getAttribute("UserBean");

        String itemId = request.getParameter("ItemId");
        String title = request.getParameter("Title");
        String description = request.getParameter("Description");
        String startPrice = request.getParameter("StartPrice");
        String bidInc = request.getParameter("BidInc");
        String timeLeft = request.getParameter("TimeLeft");
        String checked = request.getParameter("BuyItNow");

        //Start Init Servlet
        if (eib == null) {
            eib = new EditItemBean();
            request.getSession().setAttribute("EditItemBean", eib);
        }

        if (ub == null) {
            ub = new UserBean();
            request.getSession().setAttribute("UserBean", ub);
        }


        if (itemId != null) {
            eib.setItemId(itemId);
        }
        if (title != null) {
            eib.setTitle(title);
        }
        if (description != null) {
            eib.setDescription(description);
        }
        if (startPrice != null) {
            eib.setStartPrice(startPrice);
        }
        if (bidInc != null) {
            eib.setBidInc(bidInc);
        }
        if (timeLeft != null) {
            eib.setTimeLeft(timeLeft);
        }
        if (checked != null) {
            eib.setBuyNow("checked");
        } else {
            eib.setBuyNow("");
        }
        // End of Init Servlet


        //Start  Controlling section
        if (action.equals("sellitem")) {
            eib.reset();
            target = "EditItem.jsp";
        } else if (action.equals("sell")) {
            try {
                int newItem = eib.insertItem(ub.getUserId());
                TimerBean timer = (TimerBean) request.getSession().getServletContext().getAttribute("timer");
                timer.loadTask(newItem);
                target = "showitems";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "EditItem.jsp";
            }
        }
        //End  Controlling section

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
