<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<spring:url value="/resources/css/login_style.css" var="loginCss" />
		<link href="${loginCss}" rel="stylesheet" />
	</head>
<body>
<h1>BookFace</h1>
<h2>Sign up</h2>
<c:url var="addAction" value="add" ></c:url>
<form:form action="${addAction}" commandName="user">
	<h2>User Name</h2>
	<form:label path="name"></form:label>
	<form:input path="name" id="username" />		
	<form:label path="password"></form:label>
	<br>
	<h2>Password</h2>
	<form:input path="password" type="password"/>
	<br>
	<input type="submit" value="<spring:message text="Sign Up"/>" />
</form:form>
<p>${nameError}</p>
<p>To return the login page click <a href="http://localhost:8080/BookFace/">here</a></p>
</body>
</html>