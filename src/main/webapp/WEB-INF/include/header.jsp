<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>BatMeca</title>

	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.min.css" />">
	<link rel="stylesheet" href="<c:url value="/css/bootstrap-glyphicons.css" />">
	<link rel="stylesheet" href="<c:url value="/css/jquery-smoothness-ui.css" />">
	<link rel="stylesheet" href="<c:url value="/css/style.css" />">

	<script src="<c:url value="/js/jquery.min.js" />"></script>
	<script src="<c:url value="/js/jquery-ui.min.js" />"></script>
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/js/jqBootstrapValidation.js" />"></script>
</head>
<body>
	<div class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/" />">BatM&eacute;ca</a>
			</div>
			<sec:authorize access="isAuthenticated()">
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li id="materials"><a href="<c:url value="/IndexMaterial" />">Materials</a></li>
						<!-- <li id="configgenerator"><a href="ConfigGenerator">Configuration Generator</a></li>-->
						<li><a href="<c:url value="/IndexTest" />">Tests</a></li>
<!-- 						Commentaire d'une fonction non utilisée -->
<%-- 						<li><a href="<c:url value="/Config" />">Config</a></li> --%>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="<c:url value="/admin" />">Administration</a></li>
						</sec:authorize>
						<li><a href="<c:url value="/profile" />">Profile</a></li>
						<li><a href="<c:url value="/j_spring_security_logout" />" class="logout">Sign out</a></li>
					</ul>
				</div>
			</sec:authorize>
		</div>
	</div>
