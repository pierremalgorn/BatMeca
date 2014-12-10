<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add user</h1>
	</div>
	<div class="row">
		<div class="col-md-4">
			<form class="form-horizontal" autocomplete="off" action=""
				method="POST">
				<fieldset>
					<div id="controlName" class="control-group">
						<label for="name">Last name:</label>
						<div class="controls">
							<input id="inputName" type="text" name="name" type=text
								class="form-control" id="name">
						</div>
					</div>

					<div id="controlIntro" class="control-group">
						<label for="firstName">First name:</label>
						<div class="controls">
							<input type="text" class="form-control" name="firstName"
								id="firstName">
						</div>
					</div>
					<div id="controlDis" class="control-group">
						<label for="email">Email:</label>
						<div class="controls">
							<input type="text" name="email" class="form-control" id="email">
						</div>
					</div>
					<div id="controlDis" class="control-group">
						<label for="password">Password:</label>
						<div class="controls">
							<input type="password" name="password" class="form-control"
								id="password">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="type">Account type</label>
						<div class="controls">
							<select id="selectType" class="form-control" name="type">
								<c:forEach items="${requestScope.types}" var="type">
									<option value="${type.id}">${type.type}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="Edit"></label>
						<div class="controls">
							<input type="submit" value="Add" class="btn btn-primary">
							<a onClick="history.go(-1);" class="btn btn-default">Back</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<jsp:include page="include/footer.jsp" />