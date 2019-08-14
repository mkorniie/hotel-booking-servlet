<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page import="ua.mkorniie.model.enums.Role" %>
<%@ page import="ua.mkorniie.model.pojo.User" %>
<%--
  Created by IntelliJ IDEA.
  User: safety
  Date: 2019-07-23
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel = "icon" href ="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type = "image/x-icon">
    <title><%=Localization.getString("access-den-title")%></title>
</head>
<body class="error">
    <h1 class="display-4"><%=Localization.getString("access-den-err")%></h1>
    <p class="lead"><%=Localization.getString("access-den-err-text")%></p>
    <br>
    <a href= <% User u = (User)session.getAttribute("user");
                if (session == null || u == null) {%>
                    <%="/login"%>
               <% } else {
                    if (u.getRole() == Role.ADMIN) {%>
                        <%="/admin"%>
                    <% } else if (u.getRole() == Role.USER) {%>
                        <%="/new-request"%>
                    <%}
                  } %>
    ><%=Localization.getString("access-den-back")%></a>
</body>
</html>
