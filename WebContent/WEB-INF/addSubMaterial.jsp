<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Add Material</h1>
	</div>
	<div class="row">
		<form role="form" action="AddSubMaterial" method="post">
			<div class="form-group">
				<label for="inputName">Name</label> <input type="name"
					class="form-control" id="inputName" placeholder="Enter Name"
					name="inputName">
			</div>

			<table>
				<tbody>
					<tr>
						<td>Material Attribut</td>
						<td>
							<div class="form-group">

								<select class="form-control" id="inputTypeAttr"
									name="inputTypeAttr">
									<option value="value1">Valeur 1</option>
								</select>

							</div>
						</td>
						<td>

							<div class="form-group">
								<input type="text" class="form-control" id="inputNameAttr"
									name="inputNameAttr" placeholder="Value">
							</div>
						</td>
					</tr>
				</tbody>

			</table>
			<input type="hidden" id="inputMaterialParent"
				name="inputMaterialParent" value="${requestScope.idParent }" >



			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>

<script>
	
</script>
<jsp:include page="include/footer.jsp" />