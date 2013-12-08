/**
 * 
 */

function reset(url, data) {
	$.ajax({
		url : url,
		type : 'get',
		data : data,
		dataType : 'json',
		success : function(json) {
			// console.log(json);
			refreshCurve(json);
		},
		error : function(error) {
			console.log(error);
		}

	});
}
/**
 * Fonction permettant de lisser une courbe
 * */
function lisser(url, data) {
	$.ajax({
		url : url,
		type : 'get',
		data : data+"&file="+listFile[focus - 1 ],
		dataType : 'json',
		success : function(data) {
			console.log(data);
			//refreshCurve(json);
			console.log("Succes");
			console.log("focus = "+focus);
			tabGraph[focus - 1] = new Dygraph(document.getElementById("graph"+focus), data, {});
		},
		error : function(error) {
			console.log(error);
		}

	});
}

function cut(url, data) {
	$.ajax({
		url : url,
		type : 'get',
		data : 'cut=true&' + data,
		dataType : 'json',
		success : function(json) {
			// console.log(json);
			refreshCurve(json);
		},
		error : function(error) {
			console.log(error);
		}

	});
}

function action(url, data) {
	$.ajax({
		url : url,
		type : 'get',
		data : data,
		dataType : 'json',
		success : function(json) {
			console.log(json);
			// refreshCurve(data);
		},
		error : function(error) {
			console.log(error);
		}

	});
}

function calculMax(url, data) {
	$.ajax({
		url : url,
		type : 'get',
		data : data,
		dataType : 'json',
		success : function(json) {
			console.log(json);
			$('#valRm').html(json);
			// refreshCurve(data);
		},
		error : function(error) {
			console.log(error);
		}

	});
}

function refreshCurve(data) {
	g3 = new Dygraph(document.getElementById("graphdiv3"), data, {});
}

function selectRow() {
	$('#btnSelectRow').on('click', function() {

		$.ajax({
			url : $('#formSelect').attr('action'),
			type : $('#formSelect').attr('method'),
			data : $('#formSelect').serialize(),
			dataType : 'json',
			success : function(json) {
				console.log(json);
				if (json.content == null) {

					addOnglet("Curve " + json.nbCurve, json.nbCurve);
					addContent(json.nbCurve, json.data);

					// refreshCurve(json.data);
					console.log("NB Curve = " + json.nbCurve);
				}else{
					console.log("Already Exist");
				}

			},
			error : function(error) {
				console.log(error);
			}
		});
	});
}

function addOnglet(title, nbCurve) {
	$(
			'<li><a href="#curve' + nbCurve + '" data-toggle="tab" >' + title
					+ '</a></li>').appendTo("#navCurve");
}

function addContent(nbCurve, data) {

	$(
			'<div class="tab-pane" id="curve' + nbCurve + '" >'
					+ '<div id="graph' + nbCurve + '" ></div>' + '</div>')
			.appendTo('#contentCurves');
	g = new Dygraph(document.getElementById("graph" + nbCurve), data, {});
}




function getGraph(tabGraph,id){
	return tabGraph[id];
}

function getPoint(tabGraph,id){
	console.log("taille = "+tabGraph.length);
	console.log("ID = "+id);
	var graph = getGraph(id - 1);
	console.log(graph);
	return graph.getSelection();
}

function factorCol(){
	$('#btnFormFactor').on('click', function() {
		console.log('FACTOR');
		//$('#formFactor').submit();
		$.ajax({
			url : $('#formFactor').attr('action'),
			type : $('#formFactor').attr('method'),
			data : $('#formFactor').serialize(),
			dataType : 'json',
			success : function(json) {
				console.log('form factor');
				$('#myModal').modal('hide')
				refreshCurve(json);
			}
		});
	});
}

function cutCurve(){
	$('#btnCut')
	.on(
			'click',
			function() {
				var nbPlot = 1;
				var plot1 = 0;
				var plot2 = 0;
				$("#graphdiv3")
						.on(
								'click',
								function() {
									if (nbPlot == 1) {
										plot1 = g3.getSelection();
										nbPlot = 2;
									} else {
										plot2 = g3.getSelection();
										nbPlot = 1;

									}
									if (plot1 != 0 && plot2 != 0) {
										console.log('Courbe cut');
										cut(
												'${pageContext.request.contextPath}/Traitment',
												'id=${requestScope.test.id }&start='
														+ plot1
														+ '&end='
														+ plot2);
									}
									console.log('NB PLOT = ' + nbPlot);
								});

			});
}