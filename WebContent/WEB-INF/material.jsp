<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">
			Material Details - ${requestScope.material.name } 
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
					<th>Details</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.material.tests }" var="test">
					<tr>
						<td>${test.id }</td>
						<td><a href="ShowTest?idTest=${test.id }" >${test.name }</a></td>
						<td>
							<ul>
<%-- 								<c:forEach items="${test.testAttributs }" var="attr"> --%>
<%-- 									<li>${attr.id }</li> --%>
<%-- 									<li>${attr.name }</li> --%>
<%-- 								</c:forEach> --%>
							</ul>

						</td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="include/footer.jsp" />