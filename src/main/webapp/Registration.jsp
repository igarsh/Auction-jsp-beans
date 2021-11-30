<%-- 
    Document   : registration
    Created on : 17.10.2008, 13:44:03
    Author     : aris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errpage.jsp" 
import="auction.model.RegistrationBean"%>
<jsp:useBean  id="RegistrationBean" scope="session" class="auction.model.RegistrationBean"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%= request.getContextPath()%>/css.css" rel="stylesheet" type="text/css">
        <title>Online marketplace - Registration</title>
    </head>
    <body>
        <div align="right">
        <a href="showitems">Enter as guest</a>
        <br/>
        <a href="login">Login</a>
        </div>
        <h2>Registration Form</h2>
        <br/>
        <div align="left"> <%@include file="/WEB-INF/jspf/GlobalMessage.jspf"%> </div>
        
        <form name="RegForm" method="POST" action="register">            
            <table border="0">
                <tr>
                    <td align="right" class="label">Full Name</td>
                    <td><input type="text" name="Name" size="30" value="${RegistrationBean.name}"/></td>
                </tr>
                <tr>
                    <td align="right" class="label">Billing Address</td>
                    <td><input type="text" name="Address" size="30" value="${RegistrationBean.address}"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="right" class="label">Login</td>
                    <td><input type="text" name="Login" size="30" value="${RegistrationBean.login}"/></td>
                </tr>
                <tr>
                    <td align="right" class="label">Password</td>
                    <td><input type="password" name="Password" size="30" value="${RegistrationBean.password}"/></td>
                </tr>
                <tr>
                    <td align="right" class="label">Re-enter password</td>
                    <td><input type="password" name="RePassword" size="30" value="${RegistrationBean.rePassword}"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <input type="submit" value="Registration"/>
                        <input type="button" value="Cancel" onclick="document.location.href='login'"  />
                    </td>
                </tr>
            </table>            
        </form>        
    </body>
</html>
