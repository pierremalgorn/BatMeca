<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />

<div class="container" >
	<div class="row" >
		<h1 class="page-header" >User
		<a href="addUser" class="btn btn-primary pull-right" >Add User</a>
		</h1>
	</div>
	<div class="row" >
		<table class="table table-striped" >

			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Firstname</th>
					<th>Email</th>
					<th>Type</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.users}" var="user" >
					<tr>
						<td>${user.id}</td>
						<td><a href="EditUser?id=${user.id}" >${user.name}</a></td>
						<td>${user.firstName}</td>
						<td>${user.email}</td>
						<td>${user.type.type}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

	

<jsp:include page="include/footer.jsp" />