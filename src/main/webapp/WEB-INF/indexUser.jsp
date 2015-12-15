<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<jsp:include page="include/header.jsp" />

<div class="container" >
	<div class="row" >
		<h1 class="page-header" >Users list</h1>
		<p>Here is the list of the users able to connect to the BatMéca interface.</p>
		<p>You can edit or delete them, and add new ones. A user can be an admin (he will have the rights to access to this page), or a standard user.</p>
		<p>WARNING : Don't delete the last user, or you will not be able to connect to the interface anymore.</p>
		<a href="<c:url value="/admin/user/add" />" class="btn btn-primary pull-right" >Add user</a>
		
	</div>
	<c:if test="${param['event'] eq 'removed'}">
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			The user was successfully removed!
		</div>
	</c:if>
	<c:if test="${param['event'] eq 'edited'}">
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			The user was successfully edited!
		</div>
	</c:if>
	<div class="row" >
		<table class="table table-striped" >
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>First name</th>
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
						<td><a href="<c:url value="/admin/user/edit/${user.id}" />"><span class="glyphicon glyphicon-edit"></span></a></td>
						<td><a href="<c:url value="/admin/user/remove/${user.id}" />"><span class="glyphicon glyphicon-ban-circle"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="include/footer.jsp" />