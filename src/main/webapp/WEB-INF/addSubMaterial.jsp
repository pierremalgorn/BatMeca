<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add Material</h1>
	</div>
	<div class="row">
		<form role="form" action="" method="post">
			<div class="form-group">
				<label for="inputName">Name</label> <input type="text"
					class="form-control" id="inputName" placeholder="Enter Name"
					name="inputName">
			</div>
	</div>
			<h3>Material Attribute</h3>
			<c:forEach items="${requestScope.matAttrs }" var="attr" >
				<div class="form-group" >
					<label form="input${attr.name }" >${attr.name }</label>
					<input type="text" class="form-control" id="input${attr.name }" name="input${attr.name }" >
				</div>
			</c:forEach>
			
			<input type="hidden" id="inputMaterialParent"
				name="inputMaterialParent" value="${requestScope.idParent }" >



			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>

<script>
	
</script>
<jsp:include page="include/footer.jsp" />