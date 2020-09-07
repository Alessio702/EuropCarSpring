<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menu</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<spring:url value = "/logout" var = "logoutUrl"/>
<form:form method="post" class="form-inline my-2 my-lg-0" action="${logoutUrl}" style="margin-left:94.4%;">
	<input type="submit" class="btn btn-outline-danger my-2 my-sm-0" value = "Logout"/>
</form:form>
<spring:url value="/" var="addURL" />
<a href="${addURL}" role="button" class="btn btn-primary">Indietro</a>
<body>
	<div class="container" align="center">
		<h3>EuropCar | Anno: ${anno}</h3>
		<br>
		<h4>${messageAnnoGenerato}</h4>
		
		<div class="btn-group-vertical">
			<a class="btn btn-primary" href="http://localhost:8080/Archivio/Lista" role="button">Archivio</a>
			<c:if test="${isAdmin == true}">
				<a class="btn btn-secondary" href="http://localhost:8080/Impostazioni/Lista" role="button">Impostazioni</a>
			</c:if>
			<a class="btn btn-danger" href="http://localhost:8080/Venditore/ListaVenditori" role="button">Gestione Venditore</a>
			<c:if test="${isAdmin == true}">
				<a class="btn btn-dark" href="http://localhost:8080/Utente/ListaUtenti" role="button">Gestione Utenti</a>
			</c:if>
			<a class="btn btn-success" href="http://localhost:8080/Preventivo/ListaPreventivi" role="button">Gestione Preventivi</a> 
			<a class="btn btn-info" href="http://localhost:8080/Budget/Lista" role="button">Budget</a> 
			<a class="btn btn-warning" href="http://localhost:8080/FatturaPassiva/ListaFatture" role="button">Gestione Fatture</a> 
			<a class="btn btn-primary" href="http://localhost:8080/ImportaFatture/Importa" role="button">Importa Fatture</a> 
			<a class="btn btn-secondary" href="http://localhost:8080/OrdineAcquisto/Menu" role="button">Gestione Ordini D'acquisto</a>
			<a class="btn btn-success" href="http://localhost:8080/GeneraAnno" role="button">Genera Anno Contabile</a>
			<a class="btn btn-info" href="http://localhost:8080/Previsione/ListaPrevisioni" role="button">Gestione Previsioni</a>
		</div>
	</div>
</body>
</html>