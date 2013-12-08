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
		<form id="formSelect" class="form-inline well" action="SelectRow"
			method="post" role="form">
			<input type="hidden" id="inputId" name="inputId"
				value="${requestScope.test.id }"> <label for="inputX">X:
			</label> <select id="inputX" name="inputX" class="form-control">
				<c:set var="i" value="0" />
				<c:forEach items="${requestScope.colHeader[0]}" var="rowName">

					<option value="${i + 1 }">${rowName }</option>
					<c:set var="i" value="${i + 1}"></c:set>
				</c:forEach>
			</select> <label for="inputY">Y: </label> <select id="inputY" name="inputY"
				class="form-control">
				<c:set var="i" value="0" />
				<c:forEach items="${requestScope.colHeader[0]}" var="rowName">

					<option value="${i +1 }">${rowName }</option>
					<c:set var="i" value="${i + 1}"></c:set>
				</c:forEach>
			</select>
			<button type="button" class="btn btn-info" id="btnSelectRow">Submit</button>
		</form>
		<script>
			selectRow();
		</script>


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
							<li><a href="#"
								onclick="calculMax('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&calMax=2');">Calcul
									Max</a></li>
							<li><a href="#" id="btnCut">Cut</a></li>
							<li><a href="#" data-toggle="modal" data-target="#myModal">Factor</a></li>
							<li><a href="#"
								onclick="reset('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&reset=true');">Reset</a></li>


						</ul>

						<table>




							<tr>
								<th>Plot 1:</th>
								<td id="valPlot1"></td>
							</tr>
							<tr>
								<th>Plot 2:</th>
								<td id="valPlot2"></td>
							</tr>
							<tr>
								<th>Rm :</th>
								<td id="valRm"></td>
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
						<script>
						
						var tabGraph = new Array() ;
						</script>
						
						<c:set var="i" value="1" />

						<ul id="navCurve" class="nav nav-tabs">
							<c:forEach items="${requestScope.listData }" var="data">
								<li><a class="ongletCurve" href="#curve${i }" data-toggle="tab" data="${i }">Curve ${i }<button class="close pull-right" >&times;</button></a></li>
								<c:set var="i" value="${i + 1}"></c:set>
							</c:forEach>

						</ul>

						<div id="contentCurves" class="tab-content">
							<c:set var="i" value="1" />
							<c:forEach items="${requestScope.listData }" var="data">
								<div class="tab-pane" id="curve${i }">
									<h3>${data[1] }</h3>
									<div id="graph${i }" style="width: 100%; height: 500px;"></div>
									<script charset="UTF-8">
									//$(function(tabGraph) {
										var nb = '${i}';
										var data = '${data[0] }';
										//console.log(data);
										g = new Dygraph(document
												.getElementById("graph"+nb),
												data, {});
										tabGraph.push(g);
										
										$("#graph"+nb).on('click',function(){
											console.log("SELECTION ="+tabGraph[focus -1 ].getSelection());
									         $("#valPlot1").html(tabGraph[focus -1 ].getSelection());
										});
									//});

								</script>
									
								</div>
								<c:set var="i" value="${i + 1}"></c:set>
							</c:forEach>
							
							
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Formulaire pour la mul -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">

		<div class="modal-content">
			<form id="formFactor" class="form-inline" role="form"
				action="Traitment" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Apply Factor</h4>
				</div>
				<div class="modal-body">

					<input type="hidden" value="${requestScope.test.id }"
						name="inputId" id="inputId">

					<div class="form-group">
						<input class="form-control" name="inputFactor" id="inputFactor"
							placeholder="Factor">
					</div>
					<div class="form-group">
						<select class="form-control" id="selectRow" name="selectRow">
							<option value="">...</option>
							<c:set var="i" value="0" />
							<c:forEach items="${requestScope.colHeader[0]}" var="rowName">

								<option value="${i }">${rowName }</option>
								<c:set var="i" value="${i + 1}"></c:set>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="btnFormFactor" type="button" class="btn btn-primary">Save
						changes</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<button class="btn btn-info" onclick="test();" >TEST</button>
<button class="btn btn-info" onclick="printTab();" >toto</button>

<script>
var focus = 1;




factorCol();
cutCurve();
	$('#navCurve a').click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');
		});
	$('#navCurve a:first').tab('show') ;// Select first tab
	$(".ongletCurve").on('click',function(){
		focus = $(this).attr("data");
		console.log(focus);
	});
	
var listFile = ${requestScope.listFile};
console.log(listFile);
</script>
<jsp:include page="include/footer.jsp" />