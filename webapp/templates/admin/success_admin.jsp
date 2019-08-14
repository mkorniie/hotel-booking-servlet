<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page import="ua.mkorniie.model.pojo.Request" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%=Localization.getString("a-header-main")%></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel = "icon" href ="https://www.pinclipart.com/picdir/big/163-1634137_brochure-markant-online-books-icons-clipart.png"
          type = "image/x-icon">
    <link rel="stylesheet" href="templates/css/style.css">
    <link rel="stylesheet" href="templates/css/style2.css">
</head>
<body>


<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><%=Localization.getString("a-header-welcome")%></h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/admin"><%=Localization.getString("a-header-main")%></a>
        <a class="p-2 text-dark" href="/admin-users"><%=Localization.getString("a-header-users")%></a>
        <a class="p-2 text-dark" href="/admin-tables"><%=Localization.getString("a-header-tables")%></a>
    </nav>
    <a class="btn btn-outline-primary" href="/logout"><%=Localization.getString("u-header-logout")%></a>
</div>
<div style="padding:2px 20px">
    <a href="/admin?lang=ua"><img width="25px" src="templates/img/ua-01.png" alt="ua"></a>
    <a href="/admin?lang=en"><img width="25px" src="templates/img/us-01.png" alt="en"></a>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4"><%=Localization.getString("a-success-message")%></h1>
    <p class="lead"><%=Localization.getString("a-success-message-lead")%></p>
</div>
<div class="wrapper-custom">
    <h3 class="m-4"><%=Localization.getString("new-req-title")%></h3>
    <table class="table table-formatting m-4">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><%=Localization.getString("u-u-name")%></th>
            <th scope="col"><%=Localization.getString("user-req-table-plc")%></th>
            <th scope="col"><%=Localization.getString("user-req-table-stdate")%></th>
            <th scope="col"><%=Localization.getString("user-req-table-edate")%></th>
            <th scope="col"><%=Localization.getString("action")%></th>
        </tr>
        </thead>
        <tbody>
        <%  ArrayList<Request> requests = (ArrayList<Request>) request.getAttribute("entries");
            for (Request req : requests) {%>
                    <tr>
                    <th scope="row"><%=req.getId()%></th>
                    <td><%=req.getUser().getName()%></td>
                    <td><%=req.getPlaces()%></td>
                    <td><%=req.getStartDate()%></td>
                    <td><%=req.getEndDate()%></td>
                    <td>
                        <a href="/approve?method=approve&id=<%=req.getId()%>"><%=Localization.getString("approve-btn")%></a></td>
                    </tr>
            <%}%>
        </tbody>
    </table>
    <nav aria-label="my-nav">
        <ul class="pagination">
            <%      Integer pageCount = (Integer) request.getAttribute("page-count");
                Integer active = (Integer) request.getAttribute("active-page");
                for(int i  = 1; i <= pageCount; i++) {
                    if (i == active) {%>
                    <li class="page-item active"><a class="page-link" href="/admin?page=<%=i%>"><%=i%></a></li>
                    <%} else {%>
                    <li class="page-item"><a class="page-link" href="/admin?page=<%=i%>"><%=i%></a></li>
                    <%}
            }%>
        </ul>
    </nav>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>