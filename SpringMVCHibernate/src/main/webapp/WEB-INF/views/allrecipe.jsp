
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Recipe Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>
<div align="right">
<a href="${pageContext.request.contextPath}/user/changepassword"><b>My profile</b></a>
<br>
<a href="${pageContext.request.contextPath}/recipes"><b>My recipes</b></a>
<br>
<a href="${pageContext.request.contextPath}/user/logout"><b>Log Out</b></a>
</div>

	<h3>Recipes List</h3>
	<c:url var="filterAction" value="/filter"></c:url>
	<form:form action="${filterAction}" commandName="recipeData">
	<form:input path="titleFilter"/>
	<input type="submit" value="Search"/>
	</form:form>
	<c:if test="${!empty listAllRecipes}">
	
	
		<table class="tg">
			<tr>
				<th width="120">Recipe Tile</th>
				<th width="500">Recipe Description</th>
				<th width="120">Recipe Chef</th>
				<th width="120">Recipe Category</th>
				<th width="120">Recipe Date</th>
			</tr>
			<c:forEach items="${listAllRecipes}" var="recipe">
				<tr>
					<td>${recipe.title}</td>
					<td>${recipe.description}</td>
					<td>${recipe.username}</td>
					<td>${recipe.categoryName}</td>
					<td>${recipe.date}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
