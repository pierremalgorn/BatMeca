<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>
<jsp:include page="include/header.jsp" />

<div class="container">
	You are now logged out!
</div>

<jsp:include page="include/footer.jsp" />
