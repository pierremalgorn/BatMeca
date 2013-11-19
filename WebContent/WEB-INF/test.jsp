<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<%@ page import="java.io.File"%>
<jsp:include page="include/header.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dygraph-combined.js"></script>
  
<div class="container">
	<div class="row">
		<h1>
			Test Details - ${requestScope.test.name } <a
				href="ExecTest?idTest=${requestScope.test.id }"
				class="btn btn-info pull-right">Play</a>

		</h1>
		<a class="btn btn-info"
			href="IndexTest">Return Test
			List</a>
	</div>
	<div class="row">
		<ul class="nav nav-tabs">
			<li><a href="#details" data-toggle="tab">Details</a></li>
			<li><a href="#curve" data-toggle="tab">Curve</a></li>

		</ul>
		<div id="test"></div>

		<div class="tab-content">
			<div class="tab-pane active" id="details">

				<table class="table">
					<tbody>
						<tr>
							<td>ID</td>
							<td>${requestScope.test.id }</td>
						</tr>
						<tr>
							<td>Name</td>
							<td>${requestScope.test.name }</td>
						</tr>
						<tr>
							<td>Path</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
			

			<div class="tab-pane" id="curve">
			
				<div id="graphdiv3" style="width: 80%; height: 500px;"></div>
				<script charset="UTF-8" >
				
				
				var data  = '${requestScope.data }';
				//console.log(data);
				g3 = new Dygraph(document.getElementById("graphdiv3"),data,{});
				</script>
				
			</div>
		</div>
	</div>
</div>

<jsp:include page="include/footer.jsp" />