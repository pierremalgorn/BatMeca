<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<jsp:include page="include/header.jsp" />

<div class="container" >
	<div class="row" >
		<h1 class="page-header" >Material</h1>
		<a href="<c:url value="/addMaterial" />" class="btn btn-primary pull-right" >Add material</a>
		<p> Here is the list of the materials contained into the database.</p>
		<p> You can show the details by clicking on their names, edit them or delete them by clicking on the cross. You can also add a new material by clicking on the blue button.</p>
			
	</div>
	<div class="row" >
		<table class="table table-striped" >

			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Actions</th>
				
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.materials}" var="material" >
					<tr>
						<td>${material.id}</td>
						<td><a href="<c:url value="/Material?idMat=${material.id}" />" >${material.name}</a></td>
						<td>
							<ul class="nav nav-pills" >
								<li>
									<a href="<c:url value="/EditMaterial?id=${material.id }" />" >Edit</a>
								</li>
								<li><a href="<c:url value="/RemoveMaterial?idMat=${material.id }" />" ><span class="glyphicon glyphicon-remove" ></span></a></li>
							</ul>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="include/footer.jsp" />