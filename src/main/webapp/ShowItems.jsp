<%-- 
    Document   : ShowItems
    Created on : 17.10.2008, 13:44:59
    Author     : aris
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" errorPage="errpage.jsp"
         import="auction.model.ShowItemsBean, auction.model.UserBean, auction.data.ItemTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean  id="UserBean" scope="session" class="auction.model.UserBean"/>
<jsp:useBean  id="ShowItemsBean" scope="session" class="auction.model.ShowItemsBean"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%= request.getContextPath()%>/css.css" rel="stylesheet" type="text/css">        
        <title>Online marketplace - Items</title>
    </head>
    <body>
        <div align="right"> 
            You are logged in as:            
            <c:choose>
                <c:when test="${UserBean.loggedIn}">
                    <b>${UserBean.name}</b>
                    <br/><a href="logout">Logout</a>
                </c:when>
                <c:otherwise>
                    Guest
                    <br/><a href="login">Login</a>
                    <br/><a href="registration">Register</a> 
                </c:otherwise>
            </c:choose>                                                                   
        </div>
        <h2>Online marketplace</h2>
        <form name="searchForm" method="POST" action="search">
            <table>
                <tr>
                    <td colspan="3">Search parameters</td>
                </tr>
                <tr>
                    <td colspan="3" class="label">Keyword</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="Keyword" size="20" value="${ShowItemsBean.keyword}"/>                    
                    </td>
                    <td>
                        <select name="Criteria">
                            <option value="">&nbsp;</option>
                            <option value="ID" <%= (ShowItemsBean.getCriteria().equals("ID")) ? "selected" : ""%> >UID</option>
                            <option value="Title" <%= (ShowItemsBean.getCriteria().equals("Title")) ? "selected" : ""%> >Title</option>
                            <option value="Description" <%= (ShowItemsBean.getCriteria().equals("Description")) ? "selected" : ""%> >Description</option>
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="Search"/>
                    </td>
                </tr>
            </table>
        </form>
        <div align="left"> <%@include file="/WEB-INF/jspf/GlobalMessage.jspf"%> </div>
        <table cellpadding="5">
            <tr>
                <td><a href="showall">Show All Items</a></td>
                <c:if test="${UserBean.loggedIn}">
                    <td><a href="showmy">Show My Items</a></td>
                    <td><a href="sellitem">Sell</a></td>
                </c:if>                
            </tr>
        </table>
        
        <table border="1">
            <caption align="left"><b>Items</b></caption>
            <tr>
                <th>UID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Seller</th>
                <th>Start Price</th>
                <th>Bid inc</th>
                <th>Best offer</th>
                <th>Bidder</th>
                <th>Stop date</th>
                <c:if test="${UserBean.loggedIn}">
                    <th>Bidding</th>
                </c:if>   
            </tr>
            <% if (ShowItemsBean.getItemList() != null) {%>
            <% java.util.Iterator i = ShowItemsBean.getItemList().iterator();%>
            <% while (i.hasNext()) {%>
            <% ItemTO item = (ItemTO) i.next();%>
            <tr>
                <td align="center"><%= item.getId()%></td>
                <td align="left"><%= item.getTitle()%></td>
                <td align="left"><%= item.getDescription()%></td>
                <td align="center"><%= item.getSellerName()%></td>
                <td align="center"><%= item.getStartPrice()%></td>
                <td align="center"><%= (item.getBidInc() != null) ? item.getBidInc() : "-"%></td>
                <td align="center"><%= (item.getBestOffer() != null) ? item.getBestOffer() : "-"%></td>
                <td align="center"><%= (item.getBidderName() != null) ? item.getBidderName() : "-"%></td>
                <td align="center"><%= item.getStopDateAsString()%></td>                   
                <% if (UserBean.getLoggedIn()) {%>
                <td align="center">
                    
                    <% if (item.getSold() != null && item.getSold().intValue() == 1) {%>
                    <div>Not for sale</div>
                    <% if (item.getSeller().intValue() == Integer.parseInt(UserBean.getUserId())) {%>
                    <a href="deleteitem?ItemId=<%= item.getId()%>">Delete</a>
                    <%}%>
                    <% } else {%>                       
                    
                    <% if (item.getSeller().intValue() == Integer.parseInt(UserBean.getUserId())) {%>
                    <a href="deleteitem?ItemId=<%= item.getId()%>">Delete</a>
                    <% } else {%>
                    
                    <% if (item.getBuyNow() != null && item.getBuyNow().intValue() == 1) {%>
                    <a href="buyitem?ItemId=<%= item.getId()%>">Buy now</a>
                    
                    <%} else {%>
                    <form method="POST" action="biditem">
                        <input name="ItemId" type="hidden" value="<%= item.getId()%>" />
                        <input type="text" size="10" name="BidSum" value=""/>
                        <input type="submit" size="10" value="Bid"/>
                    </form>
                    <% }%>                        
                    <% }%>                        
                    <% }%>
                    
                </td>
                
                <%}%>   
            </tr>
            <% }%>
            <% }%>
        </table>
        
    </body>
</html>
