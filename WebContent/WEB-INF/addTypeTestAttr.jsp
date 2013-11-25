<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Config</h1>
	</div>
	<div class="row">
		<form action="AddTypeTestAttr" method="post" >
		
			<div class="form-group" >
				<input class="form-control" type="text" name="inputName" placeholder="Name" id="inputName" >
			</div>
			
		
			<button class="btn btn-primary" type="submit" >Add</button>
		</form>
	
	</div>
</div>
<jsp:include page="include/footer.jsp" />