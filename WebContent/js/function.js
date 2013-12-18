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
			tabGraph[focus -1].resize();
		},
		error : function(error) {
			console.log(error);
		}

	});
}
/*
 * Permet de couper une courbe
 * 
 * */
function cut(url, data) {
	console.log("URL = " + url);
	console.log(data);
	$.ajax({
		url : url,
		type : 'get',
		data : 'cut=true&' + data + "&file=" + listFile[focus - 1],
		dataType : 'json',
		success : function(data) {
			console.log(data);
			tabGraph[focus - 1] = new Dygraph(document.getElementById("graph"
					+ focus), data, {});
			tabGraph[focus -1].resize();
		},
		error : function(error) {
			console.log(error);
		}

	});
}

/**
 * 
 * */
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
/**
 * Permet de calculer le max d'une colonne
 * */
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


/*
 * Permet de selectionner une courbe 
 * */
function selectRow(url) {
	
	$('#btnSelectRow').on('click', function() {
		console.log("select row");
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
					tabGraph.push(json.nameFile);
					tabGraph[focus -1].resize();
					// refreshCurve(json.data);
					
				} else {
					console.log("Already Exist");
				}

			},
			error : function(error) {
				console.log("ERROR ");
			}
		});
	});
}

/**
 * Permet d'ajouter  un onglet
 * */
function addOnglet(url, title, nbCurve) {
	$(
			'<li><a href="#curve' + nbCurve + '" data-toggle="tab" >' + title
					+ '</a><button onclick="deleteCurve(' + url
					+ ');" class="close pull-right">&times;</button></li>')
			.appendTo("#navCurve");
}
/*
 * ajout div contenu
 * */
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

/**
 * Permet de calculer un coefficient directeur
 * */
function coeffDir(url,id) {
	var ax;
	var ay;
	var bx;
	var by;
	var cpt = 1;
	$('#graph'+focus).on('click',function(){
		if(cpt == 1){
			console.log("point 1");
			ax = tabGraph[focus-1].getValue(tabGraph[focus-1].getSelection(),0);
			ay = tabGraph[focus-1].getValue(tabGraph[focus-1].getSelection(),1);
			cpt++;
		}else{
			console.log("point 2");
			bx = tabGraph[focus-1].getValue(tabGraph[focus-1].getSelection(),0);
			by = tabGraph[focus-1].getValue(tabGraph[focus-1].getSelection(),1);
			
			var coef = (by-ay)/(bx-ax);
			console.log("COEF = "+coef);
			
			$.ajax({
				url:url,
				type:'get',
				data: 'id='+id+'&coef='+coef+'&file='+ listFile[focus - 1],
				success: function(data){
					console.log(data);
				}
			});
			
			$(this).unbind('click');
		}
		

		
	});
	
}

/**
 * Permet d'avoir la liste des colonnes
 * */
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

/**
 * Permet de supprimer une courbes
 * */
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

/**
 * Permet de couper une courbe avant un point
 * */
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

function saveHeader(){
	$('#btnSaveHeader').on('click',function(){
		console.log('data = '+$('#formHeader').serialize());
	console.log('save header');
	$.ajax({
		url:$('#formHeader').attr('action'),
		type: $('#formHeader').attr('method'),
		data: $('#formHeader').serialize(),
		
		success: function(data){
			console.log('C est la fete');
			console.log(data);
		},
		error: function(){
			console.log("ERROR");
		}
		
		
	});
	
	});
}

function getResult(url,id){
	
	$.ajax({
		url:url,
		data:'id='+id,
		type:'get',
		dataType:'json',
		success: function(data){
	
			$("#contentResult").html(data);
		},
		error: function (){
			console.log("error");
		}
	
	});
}

function getHistoric(url,id){
	$.ajax({
		url:url,
		data:'id='+id,
		type:'get',
		dataType:'json',
		success: function(data){
			console.log("DATA = "+data);
			$('#contentHistoric').html(data);
		}
		
	});
}
