<%@ page contentType="text/html; charset=UTF-8" 
language="java" 
import="java.sql.SQLException" 
isErrorPage="true" %>
<html>
<head>
<title>Ошибка приложения</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
<% if(exception!=null){ %>
<% pageContext.getServletContext().log("Ошибка приложения",exception); %>
<% String message="Ошибка приложения, обратитесь к разработчикам."; %>
<% if( exception instanceof SQLException){ %>

<%
	int code= ((SQLException)exception).getErrorCode();
	switch(code) {
			case 2292 : message="Невозможно удалить запись! Нарушено ограничение целостности."; break;
			case 942 : message="Таблица или представление не существует."; break;
			case 904 : message="Неверное имя столбца"; break;
			case 900 : message="Неверное выражение на языке SQL"; break;
			case 1400 : message="Заполните обязательные поля."; break;
			case 17002 : message="Нет связи с базой данных."; break;
			case 01034 : message="Нет связи с базой данных."; break;
			//case 01840 : message="Неверный формат даты."; break;
                        //case 00001 : message="Нарушено ограничение уникальности"; break;
			default: message="Ошибка базы данных, обратитесь к разработчикам.";
	} 
%>

    <%= code %>
    <%= message %>
                                
				
<%} else { %>
    <%= message %>
<% } %>

<% } %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<noscript>
<h2 class="errorMessage">Application Error</h2>
<a href="<%= request.getContextPath() %>">Back to Main Page</a> 
</noscript>
</head>
<body> </body>
</html>
