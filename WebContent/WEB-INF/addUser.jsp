<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add user</h1>
	</div>
	<div class="row">
		<form action="addUser" method="POST">
		<fieldset>
			<div id="controlName" class="control-group">
				<label for="name">User name:</label>
				<div class="controls">
					<input id="inputName"
						type="text" name="name" /> <span
						id="helpName" class="help-inline">Required</span>
				</div>
			</div>

			<div id="controlIntro" class="control-group">
				<label for="firstName">Firstname</label>
				<div class="controls">
					<input type="text" name="firstName"/>
				</div>
			</div>
			<div id="controlDis" class="control-group">
				<label for="email">Email :</label>
				<div class="controls">
					<input type="text" name="email"/>
				</div>
			</div>
			<div id="controlMdp" class="control-group">
				<label class="control-label" for="password">Password</label>
				<div id="pass-help" class="controls">
					<input type="password" id="inputPassword" placeholder="Password" name="password">
				</div>
			</div>
									<label class="control-label" for="type">Type :</label>
						<div class="controls">
							<select id="selectType" name="type">
								<option class="text-center" value="0">--</option>
								<c:forEach items="${requestScope.types}" var="type">
									<option value="${type.id}">${type.type}</option>
								</c:forEach>
							</select> 
						</div>
					</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary">
		</div>
	</form>
	</div>
</div>
<jsp:include page="include/footer.jsp" />