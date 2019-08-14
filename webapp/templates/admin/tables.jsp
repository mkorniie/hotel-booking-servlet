<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page import="ua.mkorniie.model.enums.RoomClass" %>
<%@ page import="ua.mkorniie.model.pojo.Bill" %>
<%@ page import="ua.mkorniie.model.pojo.Room" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: mkorniie
  Date: 2019-06-11
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title><%=Localization.getString("a-header-tables")%></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel = "icon" href ="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type = "image/x-icon">
    <link rel="stylesheet" href="templates/css/style2.css">
</head>
<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><%=Localization.getString("a-header-welcome")%></h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/admin"><%=Localization.getString("a-header-main")%></a>
        <a class="p-2 text-dark" href="/admin-users"><%=Localization.getString("a-header-users")%></a>
        <a class="p-2 text-dark" href="/admin-tables"><%=Localization.getString("a-header-tables")%></a>
        <%--        <a class="p-2 text-dark" href="/admin-stats">Statistics</a>--%>
    </nav>
    <a class="btn btn-outline-primary" href="/logout"><%=Localization.getString("u-header-logout")%></a>
</div>
<div style="padding:2px 20px">
    <a href="/admin-tables?lang=ua"><img width="25px" src="templates/img/ua-01.png" alt="ua"></a>
    <a href="/admin-tables?lang=en"><img width="25px" src="templates/img/us-01.png" alt="en"></a>
</div>
<br>
<h2><%=Localization.getString("user-bills-table-title")%></h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col"><%=Localization.getString("user-bills-table-sum")%></th>
        <th scope="col"><%=Localization.getString("user-bills-table-req-id")%></th>
        <th scope="col"><%=Localization.getString("user-bills-table-room-id")%></th>
        <th scope="col"><%=Localization.getString("user-bills-table-paid")%></th>
    </tr>
    </thead>
    <tbody>
    <%  List<Bill> bills = (List<Bill>) request.getAttribute("entries-bills");
        for(Bill b : bills) {%>
    <tr>
        <th scope="row"><%=b.getId()%></th>
        <td><%=b.getSum()%></td>
        <td><%=b.getRequest().getId()%></td>
        <td><%=b.getRoom().getId()%></td>
        <td><%=b.isPaid()%></td>
    </tr>
    <%
    } %>
    </tbody>
</table>
<nav aria-label="my-nav">
    <ul class="pagination">
        <%      Integer pageCount = (Integer) request.getAttribute("page-count-bills");
            Integer active = (Integer) request.getAttribute("active-page-bills");
            for(int i  = 1; i <= pageCount; i++) {
                if (i == active) {%>
        <li class="page-item active"><a class="page-link" href="/admin-tables?page-rooms=<%=(Integer) request.getAttribute("active-page-rooms")%>&page-bills=<%=i%>"><%=i%></a></li>
        <%} else {%>
        <li class="page-item"><a class="page-link" href="/admin-tables?page-rooms=<%=(Integer) request.getAttribute("active-page-rooms")%>&page-bills=<%=i%>"><%=i%></a></li>
        <%}
        }%>
    </ul>
</nav>

<br>
<h2><%=Localization.getString("room-title")%></h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col"><%=Localization.getString("room-pic")%></th>
        <th scope="col"><%=Localization.getString("room-place")%></th>
        <th scope="col"><%=Localization.getString("room-room-c")%></th>
        <th scope="col"><%=Localization.getString("room-price")%></th>
    </tr>
    </thead>
    <tbody>
    <%  List<Room> rooms = (List<Room>) request.getAttribute("entries-rooms");
        for(Room r : rooms) {%>
    <tr>
        <th scope="row"><%=r.getId()%></th>
        <td><img height="120px" src="<%=r.getPicURL()%>" alt="room"></td>
        <td><%=r.getPlaces()%></td>
        <td><%=r.getRoomClass().name()%></td>
        <td><%=r.getPrice()%></td>
    </tr>
    <%
        } %>
    </tbody>
</table>
<nav aria-label="my-nav">
    <ul class="pagination">
        <%  pageCount = (Integer) request.getAttribute("page-count-rooms");
            active = (Integer) request.getAttribute("active-page-rooms");
            for(int i  = 1; i <= pageCount; i++) {
                if (i == active) {%>
        <li class="page-item active"><a class="page-link" href="/admin-tables?page-rooms=<%=i%>&&page-bills=<%=(Integer) request.getAttribute("active-page-bills")%>"><%=i%></a></li>
        <%} else {%>
        <li class="page-item"><a class="page-link" href="/admin-tables?page-rooms=<%=i%>&&page-bills=<%=(Integer) request.getAttribute("active-page-bills")%>"><%=i%></a></li>
        <%}
        }%>
    </ul>
</nav>
<br>

<div class="m-4">
    <h3><%=Localization.getString("add-room")%></h3></div>
<form action="/admin-update-rooms" method="post" class="m-4">
    <div class="form-row">
        <div class="form-group col-2">
            <label for="picture"><%=Localization.getString("pic-url")%></label>
            <input type="text" class="form-control" id="picture" name="picture">
        </div>
        <div class="form-group col-2">
            <label for="places"><%=Localization.getString("n-of-places")%></label>
            <input type="number" class="form-control" id="places" name="places" min="0">
        </div>
        <div class="form-group col-3">
            <label for="roomClass"><%=Localization.getString("select-r-class")%></label>
            <select class="browser-default custom-select" id="roomClass" name="roomClass">
                <% List<RoomClass> classes = Arrays.asList(RoomClass.values());
                    for (RoomClass r: classes) {%>
                <option  value="<%=r.toString()%>"><%=r.toString()%></option>
                <% }%>
            </select>
        </div>
        <div class="form-group col-2">
            <label for="price"><%=Localization.getString("room-price")%></label>
            <input type="number" class="form-control" id="price" name="price" min="0" step="0.01">
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><%=Localization.getString("user-main-req-btn")%></button>
</form>

<a href="/admin"><%=Localization.getString("back-to-main")%></a>
<br>
<br>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>