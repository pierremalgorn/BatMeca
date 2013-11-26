<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<%@ page import="java.io.File"%>
<jsp:include page="include/header.jsp" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dygraph-combined.js"></script>
<script	src="${pageContext.request.contextPath}/js/function.js"></script>
<div class="container">
	<div class="row">
		<h1>Test Details - ${requestScope.test.name }</h1>
		<a class="btn btn-info" href="IndexTest">Return Test List</a>
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
								<ul>
									<c:forEach items="${requestScope.test.testAttributs }"
										var="attr">
										<li>${attr.typeTestAttr.name}:${attr.value }</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</tbody>
				</table>
			</div>


			<div class="tab-pane active" id="curve">
				<div class="row">
					<div class="col-md-2">
						<ul class="nav nav-pills nav-stacked">
							<li><a href="#" onclick="lisser('${pageContext.request.contextPath}/Traitment',${requestScope.test.id },'lisser');" >Lisser</a></li>
							<li><a href="#" >Calcul Max</a></li>
							<li><a href="#" onclick="action('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&cut=10000');" >Cut</a></li>
						</ul>
					</div>
					<div class="col-md-10">


						<div id="graphdiv3" style="width: 80%; height: 500px;"></div>
						<script charset="UTF-8">
						$(function(){
							
							
						
							var data = '${requestScope.data }';
							//console.log(data);
							g3 = new Dygraph(document
									.getElementById("graphdiv3"), data, {});
						});
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" />