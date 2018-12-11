<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Login</title>
</head>
<body>
	<div align="center">
		<h1>Login</h1>
		<table>
			<form:form commandName="userData"
				action="${pageContext.request.contextPath}/user/login" method="post">
				<tr>
					<td>Username:</td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Login" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
			</form:form>
			<tr>
				<td></td>
				<td><a href="${pageContext.request.contextPath}/user/register">If
						you don't have account, click here</a></td>
			</tr>
		</table>
		<div></div>
		<div>
			<p style="color: red;">${failed }</p>
		</div>
	</div>
</body>
</html>