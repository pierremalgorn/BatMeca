
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BatMeca</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">

			<a class="navbar-brand" href="#">BatMeca</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li><c:if test="${sessionScope.sessionUser.type.id == 1}">
					<a href="IndexUser" >Users</a>
				</c:if></li>
		<c:if test="${sessionScope.sessionUser != null}">
			<li><a href="#" >Profil</a></li>
			<li><a href="Login" >Sign out</a></li>
		</c:if>
		</ul>
	</nav>