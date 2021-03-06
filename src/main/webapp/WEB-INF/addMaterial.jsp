<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add material</h1>
		<p>To add a new material to the database, please enter the name and its parent material if required</p>
	</div>
	<div class="row">
		<form role="form" action="" method="post">
			<div class="form-group">
				<label for="inputName">Name</label> <input type="text"
					class="form-control" id="inputName" placeholder="Enter Name"
					name="inputName">
			</div>
			<h3>Parent material</h3>
			<c:forEach items="${requestScope.matAttrs }" var="attr">
				<div class="form-group">
					<label for="input${attr.name }">${attr.name }</label> <input
						type="text" class="form-control" id="input${attr.name }"
						name="input${attr.name }">
				</div>
			</c:forEach>
			<div class="form-group">
				<select class="form-control" id="inputMaterialParent"
					name="inputMaterialParent">
					<option value="">...</option>
					<c:forEach items="${requestScope.mats }" var="mat">
						<option value="${mat.id }">${mat.name }</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>
<jsp:include page="include/footer.jsp" />