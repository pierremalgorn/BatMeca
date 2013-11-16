<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />

    
    <div class="container">
      <form action="Login" method="post" id="formLogin" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <div class="control-group">
        	<div class="controls">
	        	<input type="email" id="inputEmail" name="login" class="form-control" placeholder="Email address" required autofocus/>	        	
	        	<p class="help-block"></p>
        	</div>
        </div>
        <div class="control-group">
        	<div class="controls">
		        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required/>
		        <p class="help-block"></p>
		    </div>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	  	</div>
      </form>
    </div> <!-- /container -->
    
<jsp:include page="include/footer.jsp" />
