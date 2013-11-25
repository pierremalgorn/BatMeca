<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">
			Material Details - ${requestScope.material.name } <a
				class="btn btn-info"
				href="AddSubMaterial?idParent=${requestScope.material.id }">Add
				Sub-MAterial</a> <a class="btn btn-info" href="AddTest?idMat=${requestScope.material.id }">Add Test</a>
				<a href="RemoveMaterial?idMat=${requestScope.material.id }" class="btn btn-danger" ><span class="glyphicon glyphicon-trash" ></span> Remove</a>
		</h1>
	</div>
	<div class="row">
		<ol class="breadcrumb">
		
			<li><a href="IndexMaterial">Index</a></li>
			<c:if test="${requestScope.material.materialParent != null }">
				<li><a href="Material?idMat=${requestScope.material.materialParent.id }" >${requestScope.material.materialParent.name }</a></li>
			</c:if>
			<li class="active">${requestScope.material.name }</li>
		</ol>
		<h3>Material Attribute</h3>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Value</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.material.matAttrs}" var="matAttr">
					<tr>
						<td>${matAttr.typeMatAttr.name }</td>
						<td>${matAttr.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="nav nav-tabs">
			<li><a href="#tests" data-toggle="tab">Test</a></li>
			<li><a href="#subMat" data-toggle="tab">SubMaterial</a></li>

		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tests">


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
								<td><a href="ShowTest?idTest=${test.id }">${test.name }</a></td>
								<td>
									<ul class="nav nav-pills" >
										<li><a href="RemoveTest?id=${test.id }&idMat=${requestScope.material.id}" ><span class="glyphicon glyphicon-remove" ></span></a></li>
									</ul>

								</td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="tab-pane" id="subMat">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.childs }" var="child">
							<tr>
								<td>${child.id }</td>
								<td><a href="Material?idMat=${child.id}">${child.name }</a></td>
								<td><a href="RemoveMaterial?idMat=${child.id }&idParent=${requestScope.material.id}" ><span class="glyphicon glyphicon-remove" ></span></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="include/footer.jsp" />