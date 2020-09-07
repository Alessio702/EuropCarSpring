<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Avanzamento</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Menu/ListaBudget" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Budget/saveDefinizione/" var="saveURL" />
		<form:form modelAttribute="oggettoSottoCategoria" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idSottoCategoria"/>
			<form:hidden path="codice"/>
			<form:hidden path="sottoCategoria"/>
			<form:hidden path="budgetSpeso"/>
			<form:hidden path="oAreaInvestimento"/>
			<h3>Definizione Budget</h3>
			<div class="form-group">
				<label>Stai modificando il budget della sottocategoria '${oggettoSottoCategoria.sottoCategoria}'
					   presente nell'area '${nomeArea}'</label>
				<form:input path="budget" cssClass="form-control" id="budget" />
				<form:errors path="budget" cssClass="error" />
			</div>
			
			<form:button type= "submit" role="button" class="btn btn-primary">Salva</form:button>
		</form:form>
	</div>
</body>
</html>