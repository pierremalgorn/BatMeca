<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Edit user</h1>
	</div>
	<div class="row">
		<form action="EditUser" method="POST">
		<fieldset>
			<div id="controlName" class="control-group">
				<label for="name">User name:</label>
				<div class="controls">
					<input hidden="true" name="id" value="${user.id}" /> <input id="inputName"
						type="text" name="name" value="${user.name}" /> <span
						id="helpName" class="help-inline">Required</span>
				</div>
			</div>

			<div id="controlIntro" class="control-group">
				<label for="firstName">Firstname</label>
				<div class="controls">
					<input type="text" name="firstName"
						value="${user.firstName}" />
				</div>
			</div>
			<div id="controlDis" class="control-group">
				<label for="email">Email :</label>
				<div class="controls">
					<input type="text" name="email"
						value="${user.email}" />
				</div>
			</div>
			<div class="control-group">
				<label for="type">Type:</label>
				<div class="controls">
					<select id="selectType" name="type">
								<c:forEach items="${requestScope.types}" var="type">
									<option value="${type.id}">${type.type}</option>
								</c:forEach>
							
					</select>	
					<script>
					var id = '<c:out value="${user.type.id}"/>';
					$("#idselectComp option[value="+id+"]").attr("selected","selected");
					</script>
				</div>
			
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn primary">
		</div>
	</form>
	</div>
</div>
<jsp:include page="include/footer.jsp" />