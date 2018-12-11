
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Change Password</title>
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
<a href="${pageContext.request.contextPath}/user/logout"><b>Log Out</b></a>
<br>
<a href="${pageContext.request.contextPath}/allrecipe"><b>All recipes</b></a>
</div>
	<form:form  action="${pageContext.request.contextPath}/user/changepassword" commandName="userData" method="post">
		<table>
			<tr>
				<td>OldPasssword:</td>
				<td><form:password path="oldPassword" /></td>
			</tr>
			<tr>
				<td>NewPassword:</td>
				<td><form:password path="password"/></td>
			</tr>
			<tr>
				<td>ConfirmPasword:</td>
				<td><form:password path="confirmPassword"/></td>
			</tr>
			
			<tr>
			<td></td>
				<td><input type="submit" value="Change Password" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
