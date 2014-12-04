<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>BatMeca</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-glyphicons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-smoothness-ui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jqBootstrapValidation.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="IndexMaterial">BatM&eacute;ca</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li id="materials"><a href="IndexMaterial">Materials</a></li>
					<!-- <li id="configgenerator"><a href="ConfigGenerator">Configuration Generator</a></li>-->
					<li><a href="IndexTest">Tests</a></li>
					<li><a href="Config">Config</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><c:if test="${sessionScope.sessionUser.type.id == 1}">
							<a href="IndexUser">Administration</a>
						</c:if></li>
					<c:if test="${sessionScope.sessionUser != null}">
						<li><a href="User">Profile</a></li>
						<li><a href="<c:url value="/j_spring_security_logout" />"
							class="logout">Sign out</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
