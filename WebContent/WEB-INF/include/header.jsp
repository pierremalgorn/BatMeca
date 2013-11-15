
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BatMeca</title>
<script
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">BatMeca</a>

		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li ><a href="IndexMaterial">Material</a></li>
				<li><a href="ConfigGenerator">Config Generation</a></li>
				
			</ul>
			
			
		

		<ul class="nav navbar-nav navbar-right">
			<li><c:if test="${sessionScope.sessionUser.type.id == 1}">
					<a href="IndexUser" >Users</a>
				</c:if></li>
		<c:if test="${sessionScope.sessionUser != null}">
			<li><a href="User" >Profil</a></li>
			<li><a href="Login" >Sign out</a></li>
		</c:if>
		</ul>
	</div>
	</nav>