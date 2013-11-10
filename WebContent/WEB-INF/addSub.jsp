<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add SubMaterial</h1>
	</div>
	<div class="row">
		<form role="form" action="addSubMaterial" method="post" >
			<input type="hidden" value="${requestScope.id }" name="idMat" />
			<div class="form-group">
				<label for="inputNameSub">Name</label> <input
					type="name" class="form-control" id="inputNameSub"
					placeholder="Enter Name" name="inputNameSub" >
			</div>
			
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>
<jsp:include page="include/footer.jsp" />