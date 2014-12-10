<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<jsp:include page="include/header.jsp" />

<div class="container">
	<div class="row">
		<h1 class="page-header">Profile</h1>
	</div>
	<div class="row">
		<div class="col-md-4">
			<form class="form-horizontal">
				<fieldset>
					<div id="controlName" class="control-group">
						<label for="name">Last name:</label>
						<div class="controls">
							<input type=text class="form-control" id="name"
								disabled="disabled" value="${user.name}">
						</div>
					</div>

					<div id="controlIntro" class="control-group">
						<label for="firstName">First name:</label>
						<div class="controls">
							<input type="text" class="form-control" id="firstName"
								disabled="disabled" value="${user.firstName}">
						</div>
					</div>
					<div id="controlDis" class="control-group">
						<label for="email">Email:</label>
						<div class="controls">
							<input type="text" class="form-control" id="email"
								disabled="disabled" value="${user.email}">
						</div>
					</div>
					<div id="controlDis" class="control-group">
						<label for="password">Password:</label>
						<div class="controls">
							<input type="password" class="form-control" id="password"
								disabled="disabled" value="password">
						</div>
					</div>
					<div class="control-group">
						<label for="type">Account type:</label>
						<div class="controls">
							<input type="text" class="form-control" id="type"
								disabled="disabled" value="${user.type.type}">
						</div>
					</div>
					<!-- Button (Double) -->
					<div class="control-group">
						<label class="control-label" for="Edit"></label>
						<div class="controls">
							<a class="btn btn-primary" id="Edit"
								href="<c:url value="/user/edit" />">Edit</a> <a
								onClick="history.go(-1);" class="btn btn-default">Back</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<jsp:include page="include/footer.jsp" />