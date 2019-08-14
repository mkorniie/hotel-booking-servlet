<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: safety
  Date: 2019-07-21
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title><%=Localization.getString("welcome-title")%>
    </title>
</head>
<body>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/login"><%=Localization.getString("submit-value")%>
            </a>
            <a class="p-2 text-dark" href="/register"><%=Localization.getString("register-link-text")%>
            </a>
        </nav>
    </div>
</header>
<div style="padding:2px 20px">
    <a href="/main?lang=ua"><img width="25px" src="templates/img/ua-01.png" alt="ua"></a>
    <a href="/main?lang=en"><img width="25px" src="templates/img/us-01.png" alt="en"></a>
</div>
<div style="width: 100%; padding-bottom: 20px">
    <h1 style="text-align: center; margin-top: 65px"><%=Localization.getString("welcome-message1")%>
        <br>
        <%=Localization.getString("welcome-message2")%> 🏨</h1>
</div>
<div style="width: 70px; height:1px; background: darkgrey; margin: 0 auto;  margin-bottom: 50px"></div>
<img style="display: block;
                margin-left: auto;
                margin-right: auto;" height="700px" src="templates/img/hotel/hotel.jpg" alt="hotel image">
<footer style="width:100%; height: 20px; background: black">
</footer>
</body>
</html>