<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please LogIn</title>
</head>
<body>
	<form action="./Login" method="get">
		<div>
			<p>Please LogIn</p>
			<p>User: <input type="text" id="user" name="User"></p>
			<p>Password: <input type="text" id="pass" name="Password"></p> 
			<p><input type="submit" value="Submit"></p>
		</div>
		<div>
			<p>${Message}</p>
		</div>
	</form>
</body>
</html>