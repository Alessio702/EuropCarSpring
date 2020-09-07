<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Edit Ordine Acquisto Dettaglio</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/OrdineAcquisto/ListaOrdiniAcquisto" role="button">Annulla</a>

<body>
	<div class="container">
		<spring:url value="/OrdineAcquisto/SaveDettaglio" var="saveURL" />
		<h2>Ordine Acquisto Dettaglio</h2>
		<form:form modelAttribute="oggettoOrdineAcquistoDettaglio" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idOrdineAcquistoDettaglio" />
			<form:hidden path="oOrdineAcquisto" />
			<!-- <div class="form-group">
				<label>Ordine Acquisto</label>
				<form:select path="oOrdineAcquisto" cssClass="form-control" id="oOrdineAcquisto" disabled = "true">
					<form:option value="${oOrdineAcquisto.ordineAcquisto}"></form:option>
					<form:options items="${elencoDettagli}" itemValue="idOrdineAcquistoDettaglio" itemLabel="quantita" />
				</form:select>
				<form:errors path="oOrdineAcquisto" cssClass="error" />
			</div>-->
			
			<div class="form-group">
				<label>Spesa Investimento</label>
				<form:select path="oSpesaInvestimento" cssClass="form-control" id="oSpesaInvestimento" >
					<form:option value="0">Seleziona una spesa investimento:</form:option>
					<form:options items="${elencoSpeseInvestimento}" itemValue="idSpesaInvestimento" itemLabel="spesaInvestimento" />
				</form:select>
				<form:errors path="oSpesaInvestimento" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Progetto</label>
				<form:select path="oProgetto" cssClass="form-control" id="oProgetto">
					<form:option value="0">Seleziona un progetto:</form:option>
					<form:options items="${elencoProgetti}" itemValue="idProgetto" itemLabel="codice" />
				</form:select>
				<form:errors path="oProgetto" cssClass="error" />
			</div>
			
			
			<div class="form-group">
				<label>Importo</label>
				<form:input path="importo" cssClass="form-control" id="importo" />
				<form:errors path="importo" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Quantita</label>
				<form:input path="quantita" cssClass="form-control" id="quantita" />
				<form:errors path="quantita" cssClass="error" />
			</div>
			
			<button type="submit" class="btn btn-primary">Salva dettaglio</button>
		</form:form>
		<br>
		<!--<spring:url value="/OrdineAcquisto/EditOrdineAcquisto/" var="retryURL" />
		<a href="${retryURL}" role="button" class="btn btn-primary">Annulla</a>
		<a class="btn btn-primary" href="http://localhost:8080/OrdineAcquisto/AddOrdineAcquisto" role="button">Annulla</a>-->
	</div>
</body>
</html>