/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import auction.model.ShowItemsBean;
import auction.model.UserBean;

/**
 *
 * @author aris
 */
public class ShowItemsServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String target = "ShowItems.jsp";
        String action = request.getServletPath().substring(1);
        ShowItemsBean sib = (ShowItemsBean) request.getSession().getAttribute("ShowItemsBean");
        UserBean ub = (UserBean) request.getSession().getAttribute("UserBean");

        String keyword = request.getParameter("Keyword");
        String criteria = request.getParameter("Criteria");
        String bidSum = request.getParameter("BidSum");
        String itemId = request.getParameter("ItemId");

        //Start Init Servlet
        if (sib == null) {
            sib = new ShowItemsBean();
            request.getSession().setAttribute("ShowItemsBean", sib);
        }
        if (keyword != null) {
            sib.setKeyword(keyword);
        }
        if (criteria != null) {
            sib.setCriteria(criteria);
        }
        if (itemId != null) {
            sib.setItemId(itemId);
        }
        if (bidSum != null) {
            sib.setBidSum(bidSum);
        }
        // End of Init Servlet


        if (action.equals("showitems")) {
            try {
                sib.reset();
                sib.getItems();
                target = "ShowItems.jsp";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "ShowItems.jsp";
            }
        } else if (action.equals("search")) {
            try {
                sib.searchItems(keyword, criteria);
                target = "ShowItems.jsp";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "ShowItems.jsp";
            }
        } else if (action.equals("showall")) {
            sib.reset();
            target = "showitems";
        } else if (action.equals("showmy")) {
            try {
                sib.getMyItems(ub.getUserId());
                target = "ShowItems.jsp";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "ShowItems.jsp";
            }
        } else if (action.equals("biditem")) {
            try {
                sib.bidItem(ub.getUserId());
                target = "showitems";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "ShowItems.jsp";
            }
        } else if (action.equals("buyitem")) {
            try {
                int item =  sib.buyItem(ub.getUserId());
                sib.prepareBill(ub.getAddress(),ub.getName(),item);                
                target = "showitems";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "ShowItems.jsp";
            }
        }else if (action.equals("deleteitem")) {
            try {
                sib.deleteItem();
                target = "showitems";
            } catch (Exception ex) {
                request.getSession().setAttribute("GlobalMessage", ex.getMessage());
                target = "ShowItems.jsp";
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
