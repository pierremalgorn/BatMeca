<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Profil</h1>
	</div>
	<div class="row">
		<fieldset>
			<div id="controlName" class="control-group">
				<label for="name">User name:</label>
				<div class="controls">
					<label for="name">${sessionScope.sessionUser.name} </label>
				</div>
			</div>

			<div id="controlIntro" class="control-group">
				<label for="firstName">Firstname</label>
				<div class="controls">
					<label for="name">${sessionScope.sessionUser.firstName} </label>
				</div>
			</div>
			<div id="controlDis" class="control-group">
				<label for="email">Email :</label>
				<div class="controls">
					<label for="email">${sessionScope.sessionUser.email}</label>
				</div>
			</div>
			<div class="control-group">
				<label for="type">Type:</label>
				<div class="controls">
					<label for="name">${sessionScope.sessionUser.type.type} </label>
				</div>
			
			</div>
		</fieldset>
	</div>
</div>
<jsp:include page="include/footer.jsp" />