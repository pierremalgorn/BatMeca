<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.epf.batmeca.entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row" >
		<h1 class="page-header" >Config Generator
			<button class="btn btn-info" id="btnPlus" type="button" >add Parameter</button>
		</h1>
		<form action="" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
		
		<table id="list" >
		</table>	
		<button class="btn btn-info" type="submit" >Submit</button>
		</form>
	</div>
</div>
<script>

	$(function() {
	var cpt = 0;
		$("#btnPlus").on('click',function(event){
			//console.log(event);
			//console.log('test');
			$('<tr class="row row_field" id="field_'+cpt+'" >'
					+'<td><h4>Param '+cpt+'</h4></td>'
					+'<td><input name="parameter_'+cpt+'" type="text" placeholder="parameter" /></td>'
					+'<td><input name="value_'+cpt+'" type="text"  placeholder="value"/></td>'
					+'<td><button type="button" onclick="test(\'#field_'+cpt+'\');" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></td>'
			+'</tr>').appendTo("#list");	
			
		cpt++;
	});
	
	});
	
	function test(strId){
		$(strId).remove();
		console.log(strId);
	}


</script>
<jsp:include page="include/footer.jsp" />