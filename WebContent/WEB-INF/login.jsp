<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Login</h1>
	</div>
	<div class="offset3" >

		<form action="Login" method="post" id="formLogin"
			class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="inputEmail">Email</label>
				<div class="controls">
					<input type="text" id="inputEmail" placeholder="Email" name="login">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPassword">Password</label>
				<div class="controls">
					<input type="password" id="inputPassword" placeholder="Password"
						name="password">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">

					<button type="submit" class="btn">Sign in</button>
				</div>
			</div>
		</form>
		</div>
</div>
<jsp:include page="include/footer.jsp" />