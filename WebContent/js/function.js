/**
 * 
 */

function lisser(url,data){
	$.ajax({
		url: url,
		type: 'get',
		data: data,
		dataType: 'json',
		success: function(json){
			//console.log(json);
			refreshCurve(json);
		},
		error:function(error){
			console.log(error);
		}
			
	});
}

function action(url,data){
	$.ajax({
		url: url,
		type: 'get',
		data: data,
		dataType: 'json',
		success: function(json){
			console.log(json);
			//refreshCurve(data);
		},
		error:function(error){
			console.log(error);
		}
			
	});
}

function calculMax(url,data){
	$.ajax({
		url: url,
		type: 'get',
		data: data,
		dataType: 'json',
		success: function(json){
			console.log(json);
			$('#valRm').html(json);
			//refreshCurve(data);
		},
		error:function(error){
			console.log(error);
		}
			
	});
}

function refreshCurve(data){
	g3 = new Dygraph(document
			.getElementById("graphdiv3"), data, {});
}