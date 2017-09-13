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
	<script>
		$(document).ready(function() {
				$("#searchButton").click(function () {
			    var search= $("#searchName").val();
			    location.href = "http://localhost:8080/BookFace/friendSearch/"+search;
			});
		});
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
<h1>BookFace</h1>
<br>
<h2>Hello ${userSession}, enjoy BookFace</h2>
<h2>${friendPage}</h2>
<div id="friends">
	<c:forEach items="${friends}" var="friendList">
		<p> <a href="http://localhost:8080/BookFace/FriendPage/${friendList.friendName}">${friendList.friendName}</a></p>
	</c:forEach>
</div>

<br>
<div id="wall">



	<c:url var="addAction" value="post" ></c:url>
	<form:form action="${addAction}" commandName="post" id="userPost">
	<form:input path="post" type="text" />
	<br>
	<input type="submit" value="<spring:message text="Post"/>" />
	</form:form>


	<c:forEach items="${wall}" var="post">
		<p>${post.name}</p>
		<textarea readonly rows="8" cols="80">${post.post}</textarea>
		<br>
		<p>Date of post: ${post.posttime}<p>
	</c:forEach>
</div>

</body>
<html>