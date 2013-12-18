<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.*"%>
<jsp:include page="include/header.jsp" />
<div class="container">
	<div class="row">
		<h1 class="page-header">Edit user</h1>
	</div>
	<div class="row">
		<div class="col-md-4">
			<form class="form-horizontal" action="EditUserAdmin" method="POST">
			
			<div class="panel-group" id="accordion">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
			          Change profile
			        </a>
			      </h4>
			    </div>
			    <div id="collapseOne" class="panel-collapse collapse in">
			      <div class="panel-body">
			      	<div class="control-group">
					  <label class="control-label" for="name">Last Name</label>
					  <div class="controls">
					  	<input hidden="true" id="id" name="id" value="${user.id}" />
					    <input id="name" name="name" placeholder="Last Name" class="form-control" value="${user.name}" required="" type="text">
					    <p class="help-block"></p>
					  </div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="textinput">First Name</label>
					  <div class="controls">
					    <input id="firstName" name="firstName" placeholder="First Name" class="form-control" value="${user.firstName}" required="" type="text">
					    <p class="help-block"></p>
					  </div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="email">Email Address</label>
					  <div class="controls">
					    <input type="email" id="email" name="email" placeholder="Email Address" class="form-control" required="" value="${user.email}">
					    <p class="help-block"></p>
					  </div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeuser">Account Type</label>
					  <div class="controls">
					    <select id="type" name="type" class="form-control">
					      	<c:forEach items="${requestScope.types}" var="type">
											<option value="${type.id}">${type.type}</option>
							</c:forEach>
					    </select>
					  </div>
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
			          Change password
			        </a>
			      </h4>
			    </div>
			    <div id="collapseTwo" class="panel-collapse collapse">
			      <div class="panel-body">
			      	<!-- Password input-->
					<div id="changePassword">
						<div class="control-group">
						  <label class="control-label" for="password">Old Password</label>
						  <div class="controls">
						    <input id="password" name="password" placeholder="Password" class="form-control" type="password">
						    <p class="help-block"></p>
						  </div>
						</div>
						
						<!-- Password input-->
						<div class="control-group">
						  <label class="control-label" for="newpassword">New Password</label>
						  <div class="controls">
						    <input id="newpassword" name="newpassword" placeholder="New Password" class="form-control" type="password">
						    <p class="help-block"></p>
						  </div>
						</div>
						
						<!-- Password input-->
						<div class="control-group">
						  <label class="control-label" for="newpasswordconfirm">New Password (confirm)</label>
						  <div class="controls">
						    <input id="newpasswordconfirm" name="newpasswordconfirm" placeholder="New Password (confirm)" class="form-control" type="password" data-validation-match-match="newpassword" data-validation-match-message="The two passwords don't match!">
						    <p class="help-block"></p>
						  </div>
						</div>
					</div>
			      </div>
			    </div>
			  </div>
			</div>
						
				<!-- Button (Double) -->
				<div class="control-group">
				  <label class="control-label" for="Edit"></label>
				  <div class="controls">
				    <button type="submit" id="edit" name="Edit" class="btn btn-success">Edit</button>
				    <a href="IndexUser" class="btn btn-danger">Discard</a>
				  </div>
				</div>
							
				</form>
			</div>
		</div>
	</div>
	
	<script>
		var id = '<c:out value="${user.type.id}"/>';
		$("#type option[value="+id+"]").attr("selected","selected");
	</script>
<jsp:include page="include/footer.jsp" />