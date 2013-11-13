<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<%@ page import="java.io.File"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1>Test Details - ${requestScope.test.name }
			<a href="ExecTest?idTest=${requestScope.test.id }" class="btn btn-info" >Play</a>
		</h1>
	</div>
	<div class="row">
		<ul class="nav nav-tabs">
			<li><a href="#details" data-toggle="tab">Details</a></li>
			<li><a href="#folder" data-toggle="tab">Folder</a></li>

		</ul>
	<div id="test" >
	</div>

		<div class="tab-content">
			<div class="tab-pane active" id="details">

				<table class="table">
					<tbody>
						<tr>
							<td>ID</td>
							<td>${requestScope.test.id }</td>
						</tr>
						<tr>
							<td>Name</td>
							<td>${requestScope.test.name }</td>
						</tr>
						<tr>
							<td>Path</td>
							<td>ROOT/${requestScope.test.sub.material.name}/${requestScope.test.sub.name }/${requestScope.test.name }</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tab-pane" id="folder">

				<h3>Conf Folder</h3>
				<ul>
					<c:forEach items="${requestScope.filesConf }" var="file">
						<li><a href="#">${file.path }</a></li>
					</c:forEach>
				</ul>
				<h3>Data Folder</h3>
				<ul>
					<c:forEach items="${requestScope.filesData }" var="file">
						<li>${file.path }</li>
					</c:forEach>
				</ul>
				<h3>Result Folder</h3>
				<ul>
					<c:forEach items="${requestScope.filesRes }" var="file">
						<li>${file.path }</li>
					</c:forEach>
				</ul>
				<h3>Temp Folder</h3>
				<ul>
					<c:forEach items="${requestScope.filesTemp }" var="file">
						<li>${file.path }</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" />