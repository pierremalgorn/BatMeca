<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />

<div class="container">
	<form name="f" action="<c:url value='j_spring_security_check'/>"
		method="post" id="formLogin" class="form-signin">
		<c:if test="${param['error'] != null}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<p>Wrong email/password combination.</p>
			</div>
		</c:if>
		<c:if test="${param['logout'] != null}">
			<%
				session.invalidate();
			%>
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<p>You are now logged out!</p>
			</div>
		</c:if>
		<h2 class="form-signin-heading">Please sign in</h2>
		<div class="control-group">
			<div class="controls">
				<input type="email" id="inputEmail" name="j_username"
					class="form-control" placeholder="Email address" required autofocus />
				<p class="help-block"></p>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="password" id="inputPassword" name="j_password"
					class="form-control" placeholder="Password" required />
				<p class="help-block"></p>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</div>
	</form>
</div>

<jsp:include page="include/footer.jsp" />
