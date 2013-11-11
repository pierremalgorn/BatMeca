<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add Test
		</h1>
	</div>
	<div class="row">
		<form method="post" action="AddTest" enctype="multipart/form-data" >
		<input type="hidden" value="${requestScope.idSub }" name="idSub" id="idSub">
			<div class="form-group">
				<label for="inputNameTest">Name</label> <input
					type="text" class="form-control" id="inputNameTest"
					placeholder="Enter Name" name="inputNameTest" >
			</div>
			<div class="form-group" >
				<label for="inputDataFile" >Data file</label>
				<input type="file" class="form-control" id="inputDataFile" name="inputDataFile" />
			</div>
			<div class="form-group" >
				<label for="inputConfigFile" >Config File</label>
				<input type="file" class="form-control" id="inputConfigFile" name="inputConfigFile" >
			</div>
			
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>
<jsp:include page="include/footer.jsp" />