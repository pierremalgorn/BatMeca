<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add Material</h1>
	</div>
	<div class="row">
		<form role="form" action="addMaterial" method="post" >
			<div class="form-group">
				<label for="inputName">Email address</label> <input
					type="name" class="form-control" id="inputName"
					placeholder="Enter Name" name="inputName" >
			</div>
			
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>
<jsp:include page="include/footer.jsp" />