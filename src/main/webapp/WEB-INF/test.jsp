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
		<p>Here is a test page, you can create several curves with the data you want. Select your X and Y axis below and click on Create to add a Curve, at the bottom of the page.</p>
		<p>If you need to edit the names of the axis, you can do it below, under Edit names</p> 
		<a class="btn btn-default pull-right"
			href="Material?idMat=${requestScope.test.material.id }">Return to
			test list</a>
	</div>
	<div class="row">
		<ul class="nav nav-tabs">
			<li><a href="#details" data-toggle="tab" >Details</a></li>
			<li><a href="#curve" data-toggle="tab" >Curves</a></li>
<!-- 			DESACTIVES CAR NON UTILISABLES -->
<!-- 			<li><a id="ongletResult" href="#result" data-toggle="tab" >Result</a></li> -->
<!-- 			<li><a id="ongletHist" href="#historic" data-toggle="tab" >History</a></li> -->
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
<!-- 						COMMENTAIRES DES PARTIES NON UTILISEES -->
<!-- 						<tr> -->
<!-- 							<th>Material attribute</th> -->
<!-- 							<td></td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<th>Test attribute</th> -->
<!-- 							<td> -->
<!-- 								<table class="table"> -->
<%-- 									<c:forEach items="${requestScope.test.testAttributs }" --%>
<%-- 										var="attr"> --%>
<!-- 										<tr> -->
<%-- 											<th>${attr.typeTestAttr.name}</th> --%>
<%-- 											<td>${attr.value }</td> --%>
<!-- 										</tr> -->
<%-- 									</c:forEach> --%>
<!-- 								</table> -->
<!-- 							</td> -->
<!-- 						</tr> -->
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
					<div class="col-md-12">
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
									<h4>You can zoom on the curve by selecting a portion of it with your mouse. You can then reset the view by double-clicking on the curve.<h4>
									<div id="graph${i }" style="width: 100%; height: 500px;"></div>
									<script charset="UTF-8">
									//$(function(tabGraph) {
										(function() {
										var nb = '${i}';
										var data = '${data[0] }';
										//console.log(data);
										
										//On parse les numéros dans le nom du fichier pour récupérer les noms d'axes...
										var filePath = "${data[1] }".split("/");
										filePath = filePath[filePath.length - 1].split(".");
										filePath = filePath[0].split("-");
										
										g = new Dygraph(document
												.getElementById("graph"+nb),
												data, {
											xlabel: $("#inputX > option[value=" + filePath[0] + "]").html(),
											ylabel: $("#inputY > option[value=" + filePath[1] + "]").html(),
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
<!-- 			DESACTIVES CAR NON UTILISABLES -->
<!-- 			<div class="tab-pane" id="result" > -->
<!-- 				<pre> -->
<!-- 					<p id="contentResult" ></p> -->
<!-- 				</pre> -->
<!-- 			</div> -->
<!-- 			<div class="tab-pane" id="historic" > -->
<!-- 				<pre> -->
<!-- 					<p id="contentHistoric" ></p> -->
<!-- 				</pre> -->
<!-- 			</div> -->
		</div>
	</div>
</div>


<script type="text/javascript">
var focus = 1;

//BUGFIX la courbe ne s'affiche pas si on appelle pas resize
setTimeout(function(){
	tabGraph[focus - 1].resize();
}, 50);

// var url = '${pageContext.request.contextPath}/ShowResult';
// var urlHist = '${pageContext.request.contextPath}/ShowHistoric';
// var id =${requestScope.test.id } ;

//fonctions non utilisées et désactivées
// $("#ongletHist").on('click',function(){
// 	getResult(url,id);
// });
// $("#ongletResult").on('click',function(){
// 	getHistoric(urlHist,id);
// });





	
	$('#navCurve a:first').tab('show') ;// Select first tab
	$(".ongletCurve").on('click',function(){
		focus = $(this).attr("data");
		tabGraph[focus - 1].resize();
		//BUGFIX la courbe ne s'affiche pas si on appelle pas resize 2 fois : conflit Bootstrap ?
		setTimeout(function(){
			tabGraph[focus - 1].resize();
		}, 50);
		

	});

		

var listFile = ${requestScope.listFile};
console.log(listFile);
saveHeader();


</script>
<jsp:include page="include/footer.jsp" />