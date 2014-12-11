<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Config</h1>
	</div>
	<div class="row">
		<a class="btn btn-info" href="<c:url value="/AddTypeAttrMat" />">Add Type Attribute Material</a> 
		<a class="btn btn-info" href="<c:url value="/AddTypeTestAttr" />">Add
			Type Attribute Test</a>

		<ul class="nav nav-tabs">
			<li><a href="#matAttr" data-toggle="tab" >Type Material Attribute</a></li>
			<li><a href="#testAttr" data-toggle="tab" >Type Test Attribute</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="matAttr">
				<h3>Type Material</h3>
				<table class="table table-striped" >
					<thead>
						<tr>
							<th>Name</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.typeMats }" var="type">
							<tr>
								<td>${type.name }</td>
								<td>
									<ul class="nav nav-pills" >
										<li><a href="<c:url value="/RemoveTypeMatAttr?id=${type.id }" />" >Remove</a></li>
										<li><a href="#" >Edit</a></li>
									</ul>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>



			</div>
			<div class="tab-pane" id="testAttr">
				<h3>Test Attribute</h3>
				<table class="table table-striped" >
					<thead>
						<tr>
							<th>Name</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.typeTests }" var="type" >
							<tr>
								<td>${type.name }</td>
								<td>
									<ul class="nav nav-pills" >
										<li><a href="<c:url value="/RemoveTypeTestAttr?id=${type.id }" />" >Remove</a></li>
										<li><a href="#" >Edit</a></li>
									</ul>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


	</div>
</div>
<jsp:include page="include/footer.jsp" />