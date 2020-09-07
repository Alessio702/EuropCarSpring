<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Riconciliazione</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Menu/ListaBudget" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Budget/saveRiconciliazione/" var="saveURL" />
		
		<form:form modelAttribute="oggettoRiconciliazione" method="post" action="${saveURL}" cssClass="form">
			<div class="form-group">
				<label>Data Inizio</label>
				<form:input type="date" path="dataInizio" cssClass="form-control" id="dataInizio" />
				<form:errors path="dataInizio" cssClass="error" />
			</div>
			<div class="form-group">
				<label>Data Fine</label>
				<form:input type="date" path="dataFine" cssClass="form-control" id="dataFine" />
				<form:errors path="dataFine" cssClass="error" />
			</div>

			<button type="submit" class="btn btn-primary">Riconcilia</button>
		</form:form>
	</div>
	<br>
	<h3>${esito}</h3>

</body>
</html>