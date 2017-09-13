<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<!--<script type="text/javascript" src="log_in.js"></script> !-->
		<!--<link id="css" rel ="stylesheet" href="logInStyle.css">-->
		<spring:url value="/resources/css/login_style.css" var="loginCss" />
		<link href="${loginCss}" rel="stylesheet" />
		<script>
			var session=("${userSession}");
			if(session != ""){
				location.href = "http://localhost:8080/BookFace/home";
			}
		</script>
	</head>
	<body>
	<h1>Welcome to BookFace</h1>
	<h2>Log In</h2>
	<c:url var="addAction" value="login" ></c:url>
	<form:form action="${addAction}" commandName="user">
		<h2>User Name</h2>
		<form:label path="name">
		<!--<spring:message text="User Name"/>-->
		</form:label>
		<br>
		<form:input path="name" />
		<br>
		<h2> Password</h2>
		<form:label path="password">
		<!--<spring:message text="Password"/>-->
		</form:label>
		<form:input path="password" type="password"/>
		<br>
		<input type="submit" value="<spring:message text="Login"/>" />
	</form:form>
	<p>${errorLogin}</p>
	<p>If you don't have an account sign up <a href="http://localhost:8080/BookFace/signup">here</a></p>
	</body>
</html>
