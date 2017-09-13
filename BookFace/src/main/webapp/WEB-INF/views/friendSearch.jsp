<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<spring:url value="/resources/css/home_style.css" var="homeCss" />
	<link href="${homeCss}" rel="stylesheet" />
	<script>
		var session=("${userSession}");
		if(session==""){
			location.href = "http://localhost:8080/BookFace";
		}
	</script>
</head>
<body>
<div id="NavigationBar">
<ul>
	<li><a href="http://localhost:8080/BookFace/home">Home</a></li>
	<li><a href="http://localhost:8080/BookFace/friendRequests">Friend Requests</a></li>
	<li>Friend Search: <input type="text" id="searchName">
	<input type="submit" id="searchButton" value="search"></li>
	<li><a href="http://localhost:8080/BookFace/logout">Log Out</a></li>
</ul>
</div>
<div id="Lists">
	${noResults}
	<table>
		<tr>
			<th width="120"></th>
			<th width="120"></th>
		</tr>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.name}</td>
				<td><a href="http://localhost:8080/BookFace/sendRequest/${user.name}">Send Friend Request</a><td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>