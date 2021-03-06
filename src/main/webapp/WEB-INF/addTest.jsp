<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add test</h1>
		<p>Please enter the name of the test you want to add, and select the files created by the machine.</p>
	</div>
	<div class="row">
		<form method="post" action="" enctype="multipart/form-data" >
		<input type="hidden" value="${requestScope.idMat }" name="idMat" id="idMat">
			<div class="form-group">
				<label for="inputNameTest">Name</label> <input
					type="text" class="form-control" id="inputNameTest"
					placeholder="Enter Name" name="inputNameTest" >
			</div>
			<div class="form-group" >
				<label for="inputDataFile" >Data file (*.txt)</label>
				<input type="file" class="form-control" id="inputDataFile" name="inputDataFile" />
			</div>
			<div class="form-group" >
				<label for="inputConfigFile" >Config file (*.par)</label>
				<input type="file" class="form-control" id="inputConfigFile" name="inputConfigFile" >
			</div>
<%-- 			<c:forEach items="${requestScope.typesTest }" var="type" > --%>
<!-- 				<div class="form-group" > -->
<%-- 					<label for="input${type.name }" >${type.name}</label> --%>
<%-- 					<input class="form-control" type="text" id="input${type.name }" name="input${type.name }" > --%>
<!-- 				</div> -->
<%-- 			</c:forEach> --%>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>
<jsp:include page="include/footer.jsp" />