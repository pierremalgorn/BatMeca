<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">
			Test List <a href="AddTest?idSub=${requestScope.sub.id }"
				class="btn btn-primary pull-right">Add Test</a>
		</h1>
	</div>
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="Material?idMaterial=${requestScope.sub.material.id }">${requestScope.sub.material.name }</a></li>
			<li class="active">${requestScope.sub.name }</li>
		</ol>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.tests }" var="test">
					<tr>
						<td>${test.id }</td>
						<td><a href="ShowTest?idTest=${test.id }" >${test.name }</a></td>
						<td>${test.date }</td>
						<td>
							<ul class="nav nav-pills">
								<li><a href="RemoveTest?id=${test.id }&idSub=${requestScope.sub.id}"><i
										class="glyphicon glyphicon-remove"></i></a></li>
								<li><a href="ExecTest" ><i class="glyphicon glyphicon-play" ></i></a></li>
							</ul>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="include/footer.jsp" />