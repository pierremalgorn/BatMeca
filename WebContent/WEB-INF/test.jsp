<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<%@ page import="java.io.File"%>
<jsp:include page="include/header.jsp" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dygraph-combined.js"></script>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
<div class="container">
	<div class="row">
		<h1>Test Details - ${requestScope.test.name }</h1>
		<a class="btn btn-info"
			href="Material?idMat=${requestScope.test.material.id }">Return
			Test List</a>
	</div>
	<div class="row">
		<ul class="nav nav-tabs">
			<li><a href="#details" data-toggle="tab">Details</a></li>
			<li><a href="#curve" data-toggle="tab">Curve</a></li>

		</ul>
		<div id="test"></div>

		<div class="tab-content">
			<div class="tab-pane" id="details">

				<table class="table">
					<tbody>
						<tr>
							<th>ID</th>
							<td>${requestScope.test.id }</td>
						</tr>
						<tr>
							<th>Name</th>
							<td>${requestScope.test.name }</td>
						</tr>
						<tr>
							<th>Material Attribute</th>
							<td></td>
						</tr>
						<tr>
							<th>Test Attribute</th>
							<td>
								<table class="table">
									<c:forEach items="${requestScope.test.testAttributs }"
										var="attr">
										<tr>
											<th>${attr.typeTestAttr.name}</th>
											<td>${attr.value }</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>


			<div class="tab-pane active" id="curve">
				<div class="row">
					<div class="col-md-2">
						<ul class="nav nav-pills nav-stacked">
							<li><a href="#"
								onclick="lisser('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&lisser=true');">Lisser</a></li>
							<li><a href="#" onclick="calculMax('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&calMax=5');" >Calcul Max</a></li>
							<li><a href="#"
								onclick="action('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&cut=10000');">Cut</a></li>
							<li><a href="#" onclick="action('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&factor=2');" >Factor</a></li>
							<li><a href="#">Echantillon</a></li>

						</ul>

						<table>




							<tr>
								<th>Plot 1:</th>
								<td id="valPlot1"></td>
							</tr>
							<tr>
								<th>Rm :</th>
								<td id="valRm" ></td>
							</tr>
						</table>
					</div>
					<div class="col-md-10">
						<form action="" method="post">
							<table class="table">
								<thead>
									<tr>
										<c:forEach items="${requestScope.colHeader[0]}" var="rowName">
											<th>
												<div class="checkbox">
													<label> ${rowName } <input name="check${rowName }"
														type="checkbox">
													</label>
												</div>
											</th>
										</c:forEach>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.colHeader[1]}" var="rowUnit">
										<td><input class="form-control" type="text"
											value="${rowUnit }"></td>
									</c:forEach>
								</tbody>
							</table>
						</form>
						<div id="graphdiv3" style="width: 100%; height: 500px;"></div>
						<script charset="UTF-8">
						$(function(){
							
							
						
							var data = '${requestScope.data }';
							//console.log(data);
							g3 = new Dygraph(document
									.getElementById("graphdiv3"), data, {});
							
							
							
						});
						
						$("#graphdiv3").on('click',function(){
							console.log("SELECTION ="+g3.getSelection());
							$("#valPlot1").html(g3.getSelection());
						});
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" />