<%-- 
    Document   : index
    Created on : 17.10.2008, 10:20:06
    Author     : aris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errpage.jsp" import="auction.model.LoginBean"%>
<jsp:useBean  id="LoginBean" scope="session" class="auction.model.LoginBean"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%= request.getContextPath()%>/css.css" rel="stylesheet" type="text/css">        
        <title>Online marketplace - Welcome!</title>
    </head>
    <body>
        
        <form method="POST" action="log" name="LoginForm">
            <table align="center" border="0"  cellspacing="2" cellpadding="2">
                <thead>
                    <tr>                        
                        <th colspan="3" class="header1">Online marketplace</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td align="right">Login</td>
                        <td><input type="text" name="Login" value="${LoginBean.login}" size="15" /></td>
                        <td><a href="showitems">Enter as guest</a></td>
                    </tr>
                    <tr>
                        <td align="right">Password</td>
                        <td><input type="password" name="Passw" value="${LoginBean.password}" size="15" /></td>
                        <td><a href="registration">Register</a></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <input type="submit" value="       Sign in    " />
                        </td>
                        <td>&nbsp;</td>
                    </tr>                    
                </tbody>
            </table>
        </form>
        <div align="center"> <%@include file="/WEB-INF/jspf/GlobalMessage.jspf"%> </div>        
    </body>
</html>
