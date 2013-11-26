/**
 * 
 */

function lisser(url,id,action){
	$.ajax({
		url: url,
		type: 'get',
		data: 'id='+id+"&action="+action,
		dataType: 'json',
		success: function(json){
			//console.log(json);
			refreshCurve(data);
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
			//console.log(json);
			refreshCurve(data);
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