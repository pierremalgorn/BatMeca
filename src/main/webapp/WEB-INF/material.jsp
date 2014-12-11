<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header col-md-offset-2">Material details -
			${requestScope.material.name }</h1>
	</div>
	<div class="row">
		<div id="arbo" class="col-md-3">
			<div class="well">
				<ul class="nav nav-pills nav-stacked">
					<li><a
						href="<c:url value="/AddSubMaterial?idParent=${requestScope.material.id }" />"><span
							class="glyphicon glyphicon-plus"></span> Add sub-material</a></li>
					<li><a href="<c:url value="/AddTest?idMat=${requestScope.material.id }" />"><span
							class="glyphicon glyphicon-plus"></span> Add test</a></li>
					<li><a
						href="<c:url value="/RemoveMaterial?idMat=${requestScope.material.id }" />"><span
							class="glyphicon glyphicon-trash"></span> Remove</a></li>
					<li><a href="<c:url value="/EditMaterial?id=${requestScope.material.id }" />"><span
							class="glyphicon glyphicon-edit"></span> Edit</a></li>

				</ul>
			</div>
		</div>
		<div id="contentMat" class="col-md-9">
			<%
				Material mat = (Material) request.getAttribute("material");
				List<Material> list = new ArrayList<Material>();
					while(mat.getMaterialParent() != null){
						
						list.add(mat);
						mat = mat.getMaterialParent();
						
					}
					list.add(mat);
					
			%>
			<ol class="breadcrumb">

 				<li><a href="<c:url value="/IndexMaterial" />">Material index</a></li>
				<c:set var="i" value="${ list.size() }" />
				<c:forEach items="${ list }" var="data">
					<c:set var="i" value="${i - 1}" />
					<li><a href="<c:url value="/Material?idMat=${list.get(i).getId()}" />">${list.get(i).getName()}</a></li>
				</c:forEach>

			</ol>




			<h3>Material attributes</h3>

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
				<li><a href="#subMat" data-toggle="tab">Sub-material</a></li>
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
									<td><a href="<c:url value="/ShowTest?idTest=${test.id }" />">${test.name }</a></td>
									<td>
										<ul class="nav nav-pills">
											<li><a
												href="<c:url value="/RemoveTest?id=${test.id }&idMat=${requestScope.material.id}" />"><span
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
									<td><a href="<c:url value="/Material?idMat=${child.id}" />">${child.name }</a></td>
									<td><a
										href="<c:url value="/RemoveMaterial?idMat=${child.id }&idParent=${requestScope.material.id}" />"><span
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