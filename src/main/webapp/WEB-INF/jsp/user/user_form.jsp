<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Form</title>
</head>
<body>

	<spring:url value="/save" var="saveURL"></spring:url>
	<form:form modelAttribute="userForm" method="post" action="${saveURL}"></form:form>
	<form:hidden path="id" />
	<table>
		<tr>
			<td>First Name:</td>
			<td><form:input path="firstname" />
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><form:input path="lastname" />
		</tr>
		<tr>
			<td>Address:</td>
			<td><form:input path="address" />
		</tr>
		<tr>
			<td></td>
			<td><button type="submit">Save</button></td>
		</tr>
	</table>
</body>
</html>