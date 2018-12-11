
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
<a href="${pageContext.request.contextPath}/allrecipe"><b>All recipes</b></a>
<br>
<a href="${pageContext.request.contextPath}/user/logout"><b>Log Out</b></a>
</div>
	<h1>Add a Recipe</h1>

	<c:url var="addAction" value="/recipe/add"></c:url>

	<form:form action="${addAction}" commandName="recipe">
		<table>
			<tr>
				<td><form:hidden path="id" /></td>
			</tr>
			<tr>
				<td><form:label path="title">
						<spring:message text="Title" />
					</form:label></td>
				<td><form:input path="title" /></td>
			</tr>
			<tr>
				<td><form:label path="description">
						<spring:message text="Description" />
					</form:label></td>
				<td><form:textarea path="description" rows="7" /></td>
			</tr>
			<c:if test="${empty recipe.title}">
				<tr>
					<td><form:label path="categoryId">
							<spring:message text="Category" />
						</form:label></td>
					<td><form:select path="categoryId" itemLabel="categoryName"
							itemValue="categoryId">
							<form:options items="${listCategorys}" itemLabel="name"
								itemValue="id" />
						</form:select></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2"><c:if test="${!empty recipe.title}">
						<input type="submit" value="<spring:message text="Edit Recipe"/>" />
					</c:if> <c:if test="${empty recipe.title}">
						<input type="submit" value="<spring:message text="Add Recipe"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<br>
	<h3>Recipes List</h3>
	<c:if test="${!empty listRecipes}">
		<table class="tg">
			<tr>
				<th width="120">Recipe Tile</th>
				<th width="500">Recipe Description</th>
				<th width="120">Recipe Chef</th>
				<th width="120">Recipe Category</th>
				<th width="120">Recipe Date</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listRecipes}" var="recipe">
				<tr>
					<td>${recipe.title}</td>
					<td>${recipe.description}</td>
					<td>${recipe.username}</td>
					<td>${recipe.categoryName}</td>
					<td>${recipe.date}</td>
					<td><a href="<c:url value='/recipeEdit/${recipe.id}' />">Edit</a></td>
					<td><a href="<c:url value='/recipeRemove/${recipe.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
