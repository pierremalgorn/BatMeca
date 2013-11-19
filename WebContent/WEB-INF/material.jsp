<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">
			Material Details - ${requestScope.material.name } <a
				href="addSubMaterial?idMat=${requestScope.material.id }"
				class="btn btn-default pull-right">Add SubMaterial</a>
		</h1>
	</div>
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="IndexMaterial">Index</a></li>
			<li class="active">${requestScope.material.name }</li>
		</ol>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="include/footer.jsp" />