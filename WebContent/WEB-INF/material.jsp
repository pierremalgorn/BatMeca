<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header col-md-offset-2">
			Material Details - ${requestScope.material.name } 
		</h1>
	</div>
	<div class="row">
		<div id="arbo" class="col-md-3">
			<div class="well">
				<ul class="nav nav-pills nav-stacked">
					<li><a href="AddSubMaterial?idParent=${requestScope.material.id }"><span class="glyphicon glyphicon-plus" ></span> Add Sub-MAterial</a></li>
					<li><a href="AddTest?idMat=${requestScope.material.id }" ><span class="glyphicon glyphicon-plus" ></span> Add Test</a></li>
					<li><a href="RemoveMaterial?idMat=${requestScope.material.id }" ><span class="glyphicon glyphicon-trash"></span>
				Remove</a></li>
					<li><a href="EditMaterial?id=${requestScope.material.id }" ><span class="glyphicon glyphicon-edit" ></span> Edit</a></li>
							
				</ul>
			</div>
		</div>
		<div id="contentMat" class="col-md-9">
			<ol class="breadcrumb">

				<li><a href="IndexMaterial">Index</a></li>
				<c:if test="${requestScope.material.materialParent != null }">
					<li><a
						href="Material?idMat=${requestScope.material.materialParent.id }">${requestScope.material.materialParent.name }</a></li>
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
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.material.tests }" var="test">
								<tr>
									<td>${test.id }</td>
									<td><a href="ShowTest?idTest=${test.id }">${test.name }</a></td>
									<td>
										<ul class="nav nav-pills">
											<li><a
												href="RemoveTest?id=${test.id }&idMat=${requestScope.material.id}"><span
													class="glyphicon glyphicon-remove"></span></a></li>
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
									<td><a
										href="RemoveMaterial?idMat=${child.id }&idParent=${requestScope.material.id}"><span
											class="glyphicon glyphicon-remove"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function toggleArbo() {
		$('#arbo').toggle();
		//$().atttr("class",'col-md-12');

	}
</script>
<jsp:include page="include/footer.jsp" />