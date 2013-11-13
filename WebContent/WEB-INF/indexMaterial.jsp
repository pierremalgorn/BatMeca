<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />

<div class="container" >
	<div class="row" >
		<h1 class="page-header" >Material
			<a href="addMaterial" class="btn btn-primary pull-right" >Add Material</a>
		</h1>
	</div>
	<div class="row" >
		<table class="table table-striped" >

			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					
				
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.materials}" var="material" >
					<tr>
						<td>${material.id}</td>
						<td><a href="Material?idMaterial=${material.id}" >${material.name}</a></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

	

<jsp:include page="include/footer.jsp" />