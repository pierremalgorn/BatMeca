<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />

<div class="container" >
	<div class="row" >
		<h1 class="page-header" >
			Users list
			<a href="addUser" class="btn btn-primary pull-right" >Add User</a>
		</h1>
	</div>
	<c:if test="${requestScope.event eq 'userremoved'}">
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			The user has been removed successfully!
		</div>
	</c:if>
	<div class="row" >
		<table class="table table-striped" >
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Firstname</th>
					<th>Email</th>
					<th>Type</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.users}" var="user" >
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.firstName}</td>
						<td>${user.email}</td>
						<td>${user.type.type}</td>
						<td><a href="EditUserAdmin?id=${user.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
						<td><a href="RemoveUser?id=${user.id}"><span class="glyphicon glyphicon-ban-circle"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="include/footer.jsp" />