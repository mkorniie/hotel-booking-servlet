<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page import="ua.mkorniie.model.enums.Language" %>
<%@ page import="ua.mkorniie.model.enums.Role" %>
<%@ page import="ua.mkorniie.model.pojo.User" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title><%=Localization.getString("a-header-users")%></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="icon"
          href="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type="image/x-icon">
    <link rel="stylesheet" href="../css/style.css">
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
    <a href="/admin-users?lang=ua"><img width="25px" src="templates/img/ua-01.png" alt="ua"></a>
    <a href="/admin-users?lang=en"><img width="25px" src="templates/img/us-01.png" alt="en"></a>
</div>
<br>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col"><%=Localization.getString("u-name")%></th>
        <th scope="col"><%=Localization.getString("u-role")%></th>
        <th scope="col"><%=Localization.getString("u-email")%></th>
        <th scope="col"><%=Localization.getString("u-lang")%></th>
        <th scope="col"><%=Localization.getString("u-del")%></th>
        <th scope="col"><%=Localization.getString("u-change")%></th>
    </tr>
    </thead>
    <tbody>
            <%  List<User> users = (List<User>) request.getAttribute("entries");
            for(User user : users){    %>
            <tr>
                <th scope="row"><%=user.getId()%>
                </th>
                <td><%=user.getName()%></td>
                <td><%=user.getRole()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getLanguage()%></td>
                <td><a href="/admin-users?method=remove&id=<%=user.getId()%>"><%=Localization.getString("u-del")%></a></td>
                <td>
                    <a href="/admin-users?method=priviledge_a&id=<%=user.getId()%>"><%=Localization.getString("u-to-a")%></a> |
                    <a href="/admin-users?method=priviledge_u&id=<%=user.getId()%>"><%=Localization.getString("u-to-u")%></a>
                </td>
            </tr>
    <% } %>
    </tbody>
</table>
<nav aria-label="my-nav">
    <ul class="pagination">
        <%  Integer pageCount = (Integer) request.getAttribute("page-count");
            Integer active = (Integer) request.getAttribute("active-page");
            for(int i  = 1; i <= pageCount; i++) {
                if (i == active) {%>
                    <li class="page-item active"><a class="page-link" href="/admin-users?page=<%=i%>"><%=i%></a></li>
                <%} else {%>
                    <li class="page-item"><a class="page-link" href="/admin-users?page=<%=i%>"><%=i%></a></li>
                <%}
        }%>
    </ul>
</nav>
<br>
<div class="m-4"><h3><%=Localization.getString("u-add")%></h3></div>
<form action="/admin-users-update" method="post" class="m-4">
    <div class="form-row">
        <div class="form-group col-2">
            <label for="name"><%=Localization.getString("u-u-name")%></label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group col-2">
            <label for="pass"><%=Localization.getString("u-pass")%></label>
            <input type="text" class="form-control" id="pass" name="pass">
        </div>

        <div class="form-group col-2">
            <label for="role"><%=Localization.getString("u-role")%></label>
            <select class="browser-default custom-select" id="role" name="role">
                <% List<Role> roles = Arrays.asList(Role.values());
                    for (Role r: roles) {%>
                <option  value="<%=r.toString()%>"><%=r.toString()%></option>
                <% }%>
            </select>
        </div>
        <div class="form-group col-2">
            <label for="mail"><%=Localization.getString("u-email")%></label>
            <input type="email" class="form-control" id="mail" name="mail">
        </div>
        <div class="form-group col-2">
            <label for="lang"><%=Localization.getString("u-lang")%></label>
            <select class="browser-default custom-select" id="lang" name="lang">
                <% List<Language> languages = Arrays.asList(Language.values());
                    for (Language l: languages) {%>
                <option  value="<%=l.toString()%>"><%=l.toString()%></option>
                <% }%>
            </select>
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><%=Localization.getString("user-main-req-btn")%></button>
</form>

<a href="/admin"><%=Localization.getString("back-to-main")%></a>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
