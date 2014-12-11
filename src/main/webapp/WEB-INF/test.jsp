<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<%@ page import="java.io.File"%>
<jsp:include page="include/header.jsp" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dygraph-combined.js"></script>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
<div class="container">
	<div class="row">
		<h1>Test details - ${requestScope.test.name }</h1>
		<a class="btn btn-default pull-right"
			href="Material?idMat=${requestScope.test.material.id }">Return to
			test list</a>
	</div>
	<div class="row">
		<ul class="nav nav-tabs">
			<li><a href="#details" data-toggle="tab" >Details</a></li>
			<li><a href="#curve" data-toggle="tab" >Curve</a></li>
			<li><a id="ongletResult" href="#result" data-toggle="tab" >Result</a></li>
			<li><a id="ongletHist" href="#historic" data-toggle="tab" >History</a></li>
		</ul>
		<br>
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
							<th>Material attribute</th>
							<td></td>
						</tr>
						<tr>
							<th>Test attribute</th>
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
					<form id="formSelect" class="form-inline well" action="SelectRow"
						method="post" role="form">
						<h4>New curve</h4>
						<input type="hidden" id="inputId" name="inputId"
							value="${requestScope.test.id }">
						<table class="table">
							<tr>
								<td>
									<label for="inputX">X:</label>
								</td>
								<td>
									<select id="inputX" name="inputX" class="form-control">
										<c:set var="i" value="0" />
										<c:forEach items="${requestScope.colHeader[0]}" var="rowName">
						
											<option value="${i + 1 }">${rowName }</option>
											<c:set var="i" value="${i + 1}"></c:set>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>
									<label for="inputY">Y:</label>
								</td>
								<td>
									<select id="inputY" name="inputY"
										class="form-control">
										<c:set var="i" value="0" />
										<c:forEach items="${requestScope.colHeader[0]}" var="rowName">
						
											<option value="${i +1 }">${rowName }</option>
											<c:set var="i" value="${i + 1}"></c:set>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="button" class="btn btn-info pull-right" id="btnSelectRow">Create</button>
								</td>
							</tr>
						</table>
					</form>
					<script>
						selectRow("${pageContext.request.contextPath}/RemoveCurve");
					</script>
				</div>

				<div class="row">
					<form id="formHeader" class="form-inline well" action="ColValue" method="post" role="form">
						<input name="inputId" type="hidden" value="${requestScope.test.id }" >
						<h4>Edit names</h4>
						<table class="table">
							<tr>
							<th>Column</th>
							<c:set var="i" value="0" />
							<c:forEach items="${requestScope.colHeader[0]}" var="row">
							<td>
								<div class="form-group"  >
									<input name="nameCol${i }" id="" class="form-control" type="text" value="${row} " >		
								</div>
								</td>
								<c:set var="i" value="${i + 1}"></c:set>
							</c:forEach>
							</tr>
							<tr>
								<th>Unit</th>
								<c:set var="i" value="0" />
								<c:forEach items="${requestScope.colHeader[1]}" var="row" >
								<td>
								<div class="form-group"  >
									<input name="unit${i }" class="form-control" type="text" value="${row}" >
								</div>
								</td>
								<c:set var="i" value="${i + 1}"></c:set>
							</c:forEach>
							</tr>
							<tr>
								<td colspan="${i + 1}">
									<input name="nbField" type="hidden" value="${i}" />
									<button id="btnSaveHeader" type="button" class="btn btn-primary pull-right" >Save</button>
								</td>
							</tr>
						</table>
					</form>
				</div>

				<div class="row">
					<div class="col-md-2">
						<ul class="nav nav-pills nav-stacked">
							<li><a
								onclick="lisser('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&lisser=true');">Smooth</a></li>
							<li><a
								onclick="calculMax('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&calMax=2');">Calc.
									max</a></li>
							<li><a id="btnCutMode" data-toggle="modal" data-target="#modalCut" >Cut mode</a></li>
							<li><a id="btnFactor" data-toggle="modal" data-target="#myModal">Factor</a></li>
							<li><a 
								onclick="reset('${pageContext.request.contextPath}/Traitment','id=${requestScope.test.id }&reset=true');">Reset</a></li>

							<li><a onclick="coeffDir('${pageContext.request.contextPath}/Traitment','${requestScope.test.id }');" >Coef dir</a></li>
						</ul>
					</div>

					<div class="col-md-10">
						<div class="row">
							<ul id="navCurve" class="nav nav-tabs">
								<c:set var="i" value="1" />
								<c:forEach items="${requestScope.listData }" var="data">
									<li id="ongletCurve${i }" ><a class="ongletCurve" href="#curve${i }"
										data-toggle="tab" data="${i }">Curve ${i } <button onclick="deleteCurve('${pageContext.request.contextPath}/RemoveCurve');" class="close">&times;</button>
									</a></li>
									<c:set var="i" value="${i + 1}"></c:set>
								</c:forEach>
							</ul>
						</div>
						<div class="row">
						<div id="contentCurves" class="tab-content">

							<script>var tabGraph = new Array();</script>

							<c:set var="i" value="1" />
							<c:forEach items="${requestScope.listData }" var="data">
								<div class="tab-pane" id="curve${i }">
									<h3>${data[1] }</h3>
									<div id="graph${i }" style="width: 100%; height: 500px;"></div>
									<script charset="UTF-8">
									//$(function(tabGraph) {
										(function() {
										var nb = '${i}';
										var data = '${data[0] }';
										//console.log(data);
										g = new Dygraph(document
												.getElementById("graph"+nb),
												data, {
											title: 'Titre',
											xlabel:'time',
											ylabel:'value',
											strokeWidth: 1.5,
										});
										tabGraph.push(g);
										
										$("#graph"+nb).on('click',function(){
											console.log("SELECTION ="+tabGraph[focus -1 ].getSelection());
											var select = tabGraph[focus -1 ].getSelection();
									         $("#valPlot1").html(tabGraph[focus -1 ].getSelection());
									         console.log(tabGraph[focus -1 ].getValue(select, 1));
										});
										
										})()
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
			<div class="tab-pane" id="result" >
				<pre>
					<p id="contentResult" ></p>
				</pre>
			</div>
			<div class="tab-pane" id="historic" >
				<pre>
					<p id="contentHistoric" ></p>
				</pre>
			</div>
		</div>
	</div>
</div>

<!-- MODAL CHOSE CUT MODE -->
<div class="modal fade" id="modalCut" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">

		<div class="modal-content">
			
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Apply factor</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-offset-3" >
						<button onclick="cutAfter('${pageContext.request.contextPath}/Traitment',${requestScope.test.id });" class="btn btn-info" >Cut after</button>
						<button onclick="cutBefore('${pageContext.request.contextPath}/Traitment',${requestScope.test.id });" class="btn btn-info" >Cut before</button>
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				</div>
		
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


<!-- Formulaire de multiplication par un facteur -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">

		<div class="modal-content">
			<form id="formFactor" class="form-inline" role="form"
				action="Traitment" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Apply factor</h4>
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
							<c:set var="i" value="1" />
							<c:forEach items="${requestScope.colHeader[0]}" var="rowName">

								<option class="elemList" id="elem${i }" value="${i }">${rowName }</option>
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

<script>

var focus = 1;
var url = '${pageContext.request.contextPath}/ShowResult';
var urlHist = '${pageContext.request.contextPath}/ShowHistoric';
var id =${requestScope.test.id } ;

$("#ongletHist").on('click',function(){
	getResult(url,id);
});
$("#ongletResult").on('click',function(){
	getHistoric(urlHist,id);
});




listCol();

factorCol();

	
	$('#navCurve a:first').tab('show') ;// Select first tab
	$(".ongletCurve").on('click',function(){
		focus = $(this).attr("data");
		tabGraph[focus - 1].resize();

	});

		

var listFile = ${requestScope.listFile};
console.log(listFile);
saveHeader();


</script>
<jsp:include page="include/footer.jsp" />