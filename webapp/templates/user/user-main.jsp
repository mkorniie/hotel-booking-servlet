<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page import="ua.mkorniie.model.enums.RoomClass" %>
<%@ page import="ua.mkorniie.model.pojo.User" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: safety
  Date: 2019-07-20
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<% User u = (User)session.getAttribute("user");%>
<html>
<head>
    <title><%=Localization.getString("user-main-msg")%> <%=u.getName()%></title>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <link rel = "icon" href ="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type = "image/x-icon">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><%=Localization.getString("u-header-welcome-msg")%> 🖖</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/user-main"><%=Localization.getString("u-header-new-req")%></a>
        <a class="p-2 text-dark" href="/user-my-requests"><%=Localization.getString("u-header-all-req")%></a>
        <a class="p-2 text-dark" href="/user-my-bills"><%=Localization.getString("u-header-all-bill")%></a>
    </nav>
    <a class="btn btn-outline-primary" href="/logout"><%=Localization.getString("u-header-logout")%></a>
</div>
<br>
<div>
    <a href="/user-main?lang=ua"><img width="25px" src="templates/img/ua-01.png" alt="ua"></a>
    <a href="/user-main?lang=en"><img width="25px" src="templates/img/us-01.png" alt="en"></a>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4"><%=Localization.getString("user-main-msg")%> <%=u.getName()%></h1>
    <p class="lead"><%=Localization.getString("user-main-msg-small")%></p>
</div>
<div class="wrapper-custom">
    <h3 class="m-4"><%=Localization.getString("user-main-req-title")%></h3>
    <form action="/new-request" method="post" class="m-4">
        <div class="form-row">
            <div class="form-group col-3">
                <label for="places"><%=Localization.getString("user-main-req-places")%> (1 - 10)</label>
                <input type="number" class="form-control" id="places" name="places" min="1" max="10">
            </div>
            <div class="form-group col-3">
                <label for="class"><%=Localization.getString("user-main-req-class")%></label>
                <select class="browser-default custom-select" id="class" name="class">
                    <% List<RoomClass> classes = Arrays.asList(RoomClass.values());
                        for (RoomClass c: classes) {%>
                    <option  value="<%=c.toString()%>"><%=c.toString()%></option>
                    <% }%>
                </select>            </div>
            <div class="form-group col-6">
                <label for="test"><%=Localization.getString("user-main-req-date")%></label><br>
                <input type="text" name="daterange" id="test" value="01/01/2018 - 01/15/2018" />
            </div>

        </div>
        <button type="submit" class="btn btn-primary"><%=Localization.getString("user-main-req-btn")%></button>
    </form>
</div>
</body>
<script>
    $(function() {
        $('input[name="daterange"]').daterangepicker({
            opens: 'left'
        }, function(start, end, label) {
            console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
        });
    });
</script>
</html>
