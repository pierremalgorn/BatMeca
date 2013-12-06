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

function lisser(url, data) {
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
				//addOnglet(test, nbCurve);
				//addOnglet(title, nbCurve);
				refreshCurve(json.data);
				console.log("NB Curve = "+json.nbCurve);
				
			},
			error : function(error) {
				console.log(error);
			}
		});
	});
}

function addOnglet(title, nbCurve) {
	$('<li><a href="#curve' + nbCurve + '" >' + title + '</a></li>').appendTo(
			"#navCurve");
}

function addContent(nbCurve) {
	$(
			'<div class="tab-pane" id="curve' + nbCurve + '" >'
					+ '<div id="graph' + nbCurbe + '" ></div>' + '</div>')
			.appendTo('#contentCurves')
}
