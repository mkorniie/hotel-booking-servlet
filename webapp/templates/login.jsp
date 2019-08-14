<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="templates/css/style.css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title><%=Localization.getString("login-title")%></title>
    <link rel = "icon" href ="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type = "image/x-icon">
</head>
<body class="blue">

<div class="lang-bar p-2">
    <a href="/login?lang=ua">
        <img src="http://localhost:8080/templates/img/ua-01.png" alt="ua-flag">
    </a>
    <a href="/login?lang=en">
        <img src="http://localhost:8080/templates/img/us-01.png" alt="us-flag">
    </a>
</div>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="http://pngriver.com/wp-content/uploads/2018/03/Download-Cute-Whale-Transparent-Background-119.png" id="icon" alt="User Icon" />
        </div>
<!---->
        <!-- Login Form -->
        <form action="login" method="post">
            <input type="text" id="login" class="fadeIn second" name="login" placeholder="<%=Localization.getString("login-placeholder")%>">
            <input type="text" id="password" class="fadeIn third" name="password" placeholder="<%=Localization.getString("password-placeholder")%>">
            <input type="submit" class="fadeIn fourth" value="<%=Localization.getString("submit-value")%>">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="/register"><%=Localization.getString("register-link-text")%></a>
        </div>

    </div>
</div>
</body>
</html>