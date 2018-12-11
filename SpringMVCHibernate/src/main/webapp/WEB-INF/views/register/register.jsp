<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Register</title>
</head>
<body>
	<div align="center">
		<h1>Register User</h1>
		<table>
			<form:form commandName="userData"
				action="${pageContext.request.contextPath}/user/register"
				method="post">
				<tr>
					<td>Username:</td>
					<td><form:input path="username" /></td>
					<td><form:errors path="username" cssStyle="color:red;"></form:errors></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" cssStyle="color:red;"></form:errors></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" /></td>
					<td><form:errors path="password" cssStyle="color:red;"></form:errors></td>
				</tr>
				<tr>
					<td>ConfirmPassword:</td>
					<td><form:password path="confirmPassword" /></td>
					<td><form:errors path="confirmPassword" cssStyle="color:red;"></form:errors></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Register" /></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>