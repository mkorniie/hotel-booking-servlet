<%@ page import="ua.mkorniie.controller.dao.BillDAOProxy" %>
<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page import="ua.mkorniie.model.pojo.Bill" %>
<%@ page import="ua.mkorniie.model.pojo.Request" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: safety
  Date: 2019-07-22
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><%=Localization.getString("user-req-title")%></title>
    <link rel = "icon" href ="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type = "image/x-icon">

    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><%=Localization.getString("u-header-welcome-msg")%> 🖖</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <%--        <a class="p-2 text-dark" href="/user-my-profile">My Profile</a>--%>
        <a class="p-2 text-dark" href="/user-main"><%=Localization.getString("u-header-new-req")%></a>
        <a class="p-2 text-dark" href="/user-my-requests"><%=Localization.getString("u-header-all-req")%></a>
        <a class="p-2 text-dark" href="/user-my-bills"><%=Localization.getString("u-header-all-bill")%></a>
    </nav>
    <a class="btn btn-outline-primary" href="/logout"><%=Localization.getString("u-header-logout")%></a>
</div>
<br>
<div class="lang-bar">
    <a href="/user-my-requests?lang=ua"><img width="25px" src="templates/img/ua-01.png" alt="ua"></a>
    <a href="/user-my-requests?lang=en"><img width="25px" src="templates/img/us-01.png" alt="en"></a>
</div>
<br>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4"><%=Localization.getString("user-req-table-title")%></h1>
</div>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col"><%=Localization.getString("user-req-table-plc")%></th>
        <th scope="col"><%=Localization.getString("user-req-table-stdate")%></th>
        <th scope="col"><%=Localization.getString("user-req-table-edate")%></th>
        <th scope="col"><%=Localization.getString("user-req-table-app")%></th>
        <th scope="col"><%=Localization.getString("user-req-table-cncl")%></th>
        <th scope="col"><%=Localization.getString("user-req-table-pay")%></th>
    </tr>
    </thead>
    <tbody>
    <%  List<Request> requests = (List<Request>) request.getAttribute("entries");
        for(Request r : requests) {%>
                <tr>
                    <th scope="row"><%=r.getId()%>
                    </th>
                    <td><%=r.getPlaces()%></td>
                    <td><%=r.getStartDate()%></td>
                    <td><%=r.getEndDate()%></td>
                    <td><%=r.isApproved()%></td>
                    <td>
                        <a href="/user-my-requests?method=cancel&id=<%=r.getId()%>"><%=Localization.getString("user-req-table-cncl")%></a></td>
                    <td>
                        <% if (r.isApproved()) {
                                Bill b = new BillDAOProxy().findBillByRequestId(r.getId());
                                if (b != null && b.isPaid()) { %>
                                    <%=Localization.getString("user-req-paid")%>
                                <%} else { %>
                                    <a href="/user-my-bills"><%=Localization.getString("user-req-table-pay")%></a>
                                <%}%>
                        <%} else {%>
                                <%=Localization.getString("user-req-table-cantpay")%>
                            <%}%>
                    </td>
                </tr>
        <%} %>
    </tbody>
</table>

<nav aria-label="my-nav">
    <ul class="pagination">
        <%      Integer pageCount = (Integer) request.getAttribute("page-count");
            Integer active = (Integer) request.getAttribute("active-page");
            for(int i  = 1; i <= pageCount; i++) {
                if (i == active) {%>
        <li class="page-item active"><a class="page-link" href="/user-my-requests?page=<%=i%>"><%=i%></a></li>
        <%} else {%>
        <li class="page-item"><a class="page-link" href="/user-my-requests?page=<%=i%>"><%=i%></a></li>
        <%}
        }%>
    </ul>
</nav>
<br>
<a href="/user-main"><%=Localization.getString("back-to-main")%></a>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
</body>
</html>