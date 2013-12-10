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
 */
function lisser(url, data) {
	$.ajax({
		url : url,
		type : 'get',
		data : data + "&file=" + listFile[focus - 1],
		dataType : 'json',
		success : function(data) {
			console.log(data);
			// refreshCurve(json);
			console.log("Succes");
			console.log("focus = " + focus);
			tabGraph[focus - 1] = new Dygraph(document.getElementById("graph"
					+ focus), data, {});
		},
		error : function(error) {
			console.log(error);
		}

	});
}

function cut(url, data) {
	console.log("URL = " + url);
	console.log(data);
	$.ajax({
		url : url,
		type : 'get',
		data : 'cut=true&' + data + "&file=" + listFile[focus - 1],
		dataType : 'json',
		success : function(data) {

			tabGraph[focus - 1] = new Dygraph(document.getElementById("graph"
					+ focus), data, {});
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
		data : data + "&file=" + listFile[focus - 1],
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

function selectRow(url) {
	$('#btnSelectRow').on('click', function() {

		$.ajax({
			url : $('#formSelect').attr('action'),
			type : $('#formSelect').attr('method'),
			data : $('#formSelect').serialize(),
			dataType : 'json',
			success : function(json) {
				console.log(json);
				if (json.content == null) {

					addOnglet(url, "Curve " + json.nbCurve, json.nbCurve);
					addContent(json.nbCurve, json.data);

					// refreshCurve(json.data);
					console.log("NB Curve = " + json.nbCurve);
				} else {
					console.log("Already Exist");
				}

			},
			error : function(error) {
				console.log(error);
			}
		});
	});
}

function addOnglet(url, title, nbCurve) {
	$(
			'<li><a href="#curve' + nbCurve + '" data-toggle="tab" >' + title
					+ '</a><button onclick="deleteCurve(' + url
					+ ');" class="close pull-right">&times;</button></li>')
			.appendTo("#navCurve");
}

function addContent(nbCurve, data) {

	$(
			'<div class="tab-pane" id="curve' + nbCurve + '" >'
					+ '<div id="graph' + nbCurve
					+ '" style="width: 100%; height: 500px;" ></div>'
					+ '</div>').appendTo('#contentCurves');
	g = new Dygraph(document.getElementById("graph" + nbCurve), data, {});
}

function getGraph(tabGraph, id) {
	return tabGraph[id];
}

function getPoint(tabGraph, id) {
	console.log("taille = " + tabGraph.length);
	console.log("ID = " + id);
	var graph = getGraph(id - 1);
	console.log(graph);
	return graph.getSelection();
}

function factorCol() {
	$('#btnFormFactor').on(
			'click',
			function() {
				console.log('FACTOR');
				// $('#formFactor').submit();
				$.ajax({
					url : $('#formFactor').attr('action'),
					type : $('#formFactor').attr('method'),
					data : $('#formFactor').serialize() + "&file="
							+ listFile[focus - 1],
					dataType : 'json',
					success : function(json) {
						console.log('form factor');
						$('#myModal').modal('hide')
						tabGraph[focus - 1] = new Dygraph(document
								.getElementById("graph" + focus), data, {});
					}
				});
			});
}

function cutCurve(url, id) {
	$('#btnCut').on('click', function() {

		var nbPlot = 1;
		var plot1 = 0;
		var plot2 = 0;
		$("#graph" + focus).on('click', function() {
			if (nbPlot == 1) {
				plot1 = tabGraph[focus - 1].getSelection();
				nbPlot = 2;
			} else {
				plot2 = tabGraph[focus - 1].getSelection();
				nbPlot = 1;

			}
			if (plot1 != 0 && plot2 != 0) {
				console.log('Courbe cut');

				cut(url, 'id=' + id + '&start=' + plot1 + '&end=' + plot2);
			}
			console.log('NB PLOT = ' + nbPlot);
		});

	});

}

function coeffDir() {
	var nbPlot = 1;
	var pointAx = null;
	var pointAy = null;
	var pointBx = null;
	var pointBy = null;
	$('#graph' + focus).on(
			'click',
			function() {
				if (nbPlot == 1) {
					pointAx = tabGraph[focus - 1].getValue(tabGraph[focus - 1]
							.getSelection(), 0);
					pointAy = tabGraph[focus - 1].getValue(tabGraph[focus - 1]
							.getSelection(), 0);
					nbPlot = 2;
				} else {
					pointBx = tabGraph[focus - 1].getValue(tabGraph[focus - 1]
							.getSelection(), 0);
					pointBy = tabGraph[focus - 1].getValue(tabGraph[focus - 1]
							.getSelection(), 0);
					nbPlot = 1;

					var coef = (pointBy - pointAy) / (pointBx - pointBy);
					console.log("Coefficient = " + coef);

				}
			});

}

function listCol() {
	$('#btnFactor').on('click', function() {
		console.log('test');
		var file = listFile[focus - 1];
		var tab = file.split('.');
		tab = tab[0].split('/');
		tab = tab[tab.length - 1].split('-');
		$('.elemList').hide();
		$('#elem' + tab[0]).show();
		$('#elem' + tab[1]).show();
		console.log('A = ' + tab);
	});

}

function deleteCurve(url) {
	console.log("delete Curve");
	$.ajax({
		url : url,
		type : 'get',
		data : "file=" + listFile[focus - 1],

		success : function(json) {

			$('#curve' + focus).remove();
			$('#ongletCurve' + focus).remove();
		},
		error : function(error) {
			console.log("error delete Curve");

		}

	});
}

function cutBefore(url,id) {
	console.log("CUT BEFOR");
	var end;
	$('#graph'+focus).on('click',function(){
		end = tabGraph[focus - 1].getSelection();
		cut(url, 'id=' + id + '&end=' + end+"&before=true");
		$(this).unbind('click');
	});
	$('#modalCut').modal('hide');
}

function cutAfter(url,id) {
	console.log("CUT AFTER");
	// selection du point de dépard
	var start;
	$('#graph' + focus).on('click', function() {
		start = tabGraph[focus - 1].getSelection();
		console.log("start =  " + start);
		cut(url, 'id=' + id + '&start=' + start+"&after=true");
		$(this).unbind('click');
	});

	$('#modalCut').modal('hide');
}
