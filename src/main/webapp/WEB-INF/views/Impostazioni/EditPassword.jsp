<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Update Password</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Impostazioni/Lista" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Impostazioni/savePassword" var="saveURL" />
		<form:form modelAttribute="oggettoUtente" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="username"/>
			<form:hidden path="active"/>
			<form:hidden path="roles"/>
			<form:hidden path="permissions"/>
			<form:hidden path="oVenditore"/>
			<h3 align = "center">Cambia Password</h3>
			<h6 align = "center"><b>Attenzione!</b>&nbsp;Stai modificando la password dell'utente '${userUsername}'!</h6>
			<br>
			<div class="form-group">
				<label>Nuova Password</label>
				<form:input path="password" cssClass="form-control" id="password" />
				<form:errors path="password" cssClass="error" />
			</div>
			
			<form:button type= "submit" role="button" class="btn btn-primary">Salva</form:button>
		</form:form>
	</div>
</body>
</html>