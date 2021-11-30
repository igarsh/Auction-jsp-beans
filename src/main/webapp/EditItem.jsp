<%-- 
    Document   : EditItem
    Created on : 17.10.2008, 13:46:27
    Author     : aris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errpage.jsp" 
        import="auction.model.EditItemBean" %>
<jsp:useBean  id="EditItemBean" scope="session" class="auction.model.EditItemBean"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%= request.getContextPath()%>/css.css" rel="stylesheet" type="text/css">        
        <title>Online marketplace - EditItem</title>
    </head>
    <body>
         <h2> Item Form </h2>
        <br/>
        <div align="left"> <%@include file="/WEB-INF/jspf/GlobalMessage.jspf"%> </div>
        
        <form name="ItemForm" method="POST" action="sell">
            <input type="hidden" name="ItemId" size="30" value="${EditItemBean.itemId}"/>
            <table border="0">
                <tr>
                    <td align="right" class="label">Title of item</td>
                    <td><input type="text" name="Title" size="30" value="${EditItemBean.title}"/></td>
                </tr>
                <tr>
                    <td align="right" class="label">Description</td>
                    <td> 
                    <textarea name="Description" cols="32" rows="10" >
                        ${EditItemBean.description}
                    </textarea> 
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="right" class="label">Start price</td>
                    <td><input type="text" name="StartPrice" size="15" value="${EditItemBean.startPrice}"/></td>
                </tr>
                <tr>
                    <td align="right" class="label">Bid inc</td>
                    <td><input type="text" name="BidInc" size="15" value="${EditItemBean.bidInc}"/></td>
                </tr>
                <tr>
                    <td align="right" class="label">Time left (days)</td>
                    <td><input type="text" name="TimeLeft" size="15" value="${EditItemBean.timeLeft}"/></td>
                </tr>
                <tr>
                    <td align="right" class="label">Buy it now</td>
                    <td><input type="checkbox" name="BuyItNow"  ${EditItemBean.buyNow} /></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <input type="submit" value="Publish"/>
                        <input type="button" value="Cancel" onclick="document.location.href='showitems'"/>
                    </td>
                </tr>
            </table>            
        </form>        
    </body>
</html>
