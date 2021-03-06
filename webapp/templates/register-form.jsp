<%@ page import="ua.mkorniie.controller.util.Localization" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	<title><%=Localization.getString("register-title")%></title>
</head>
<body>
<div>
	<div class="p-2">
		<a href="/register?lang=ua"><img width="25px" src="templates/img/ua-01.png" alt="ua"></a>
		<a href="/register?lang=en"><img width="25px" src="templates/img/us-01.png" alt="en"></a>
	</div>
</div>
<br>
<div class="container mt-1">
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<header class="card-header">
					<a href="/login" class="float-right btn btn-outline-primary mt-1"><%=Localization.getString("register-login")%></a>
					<h4 class="card-title mt-2"><%=Localization.getString("register-signup")%></h4>
				</header>
				<article class="card-body">
					<form action="register" method="post">
						<div class="form-row">
							<div class="col form-group">
								<label><%=Localization.getString("u-name")%> <span class="text-danger">*</span> </label>
								<input type="text" name="name" class="form-control" placeholder="">
							</div> <!-- form-group end.// -->
						</div>
						<div class="form-group">
							<label><%=Localization.getString("register-email")%></label>
							<input type="email"  name="email" class="form-control" placeholder="">
							<small class="form-text text-muted"><%=Localization.getString("register-email-desclaimer")%></small>
						</div>
						<div class="form-group">
							<label><%=Localization.getString("register-pass")%><span class="text-danger">*</span></label>
							<input class="form-control" name="password" type="password">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block"><%=Localization.getString("register-link-text")%></button>
						</div>
						<small class="text-muted"><%=Localization.getString("register-marked-red")%><span class="text-danger">*</span>
                            <br><br><%=Localization.getString("register-descl-1")%>
                            <br><%=Localization.getString("register-descl-2")%></small>
					</form>
				</article>
				<div class="border-top card-body text-center"><%=Localization.getString("register-have-acc")%><a href="/login"><%=Localization.getString("register-login")%></a></div>
			</div>
		</div>
	</div>


</div>
<!--container end.//-->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
