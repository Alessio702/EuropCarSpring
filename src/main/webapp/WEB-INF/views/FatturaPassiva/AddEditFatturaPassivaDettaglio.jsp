<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Edit Fattura Passiva Dettaglio</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/FatturaPassiva/ListaFatture" role="button">Annulla</a>

<body>
	<div class="container">
		<spring:url value="/FatturaPassiva/SaveDettaglio" var="saveURL" />
		<h2>Fattura Passiva Dettaglio</h2>
		<form:form modelAttribute="oggettoFatturaDettaglio" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idDettaglioFattura" />
			<form:hidden path="oFatturaPassiva" />
			
			<div class="form-group">
				<label>Aliquota Iva</label>
				<form:select path="oAliquotaIva" cssClass="form-control" id="oAliquotaIva">
					<form:option value="0">Seleziona un'aliquota:</form:option>
					<form:options items="${elencoAliquote}" itemValue="idaliquotaIva" itemLabel="codice" />
				</form:select>
				<form:errors path="oAliquotaIva" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Dettaglio Fattura</label>
				<form:input path="dettaglioFattura" cssClass="form-control" id="dettaglioFattura" />
				<form:errors path="dettaglioFattura" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Importo</label>
				<form:input path="importo" cssClass="form-control" id="importo" />
				<form:errors path="importo" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Preventivo</label>
				<form:select path="oPreventivo" cssClass="form-control" id="oPreventivo" >
					<form:option value="0">Seleziona un preventivo:</form:option>
					<form:options items="${elencoPreventivi}" itemValue="idPreventivo" itemLabel="preventivo" />
				</form:select>
				<form:errors path="oPreventivo" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Spesa Investimento</label>
				<form:select path="oSpesaInvestimento" cssClass="form-control" id="oSpesaInvestimento" >
					<form:option value="0">Seleziona una spesa investimento:</form:option>
					<form:options items="${elencoSpeseInvestimento}" itemValue="idSpesaInvestimento" itemLabel="spesaInvestimento" />
				</form:select>
				<form:errors path="oSpesaInvestimento" cssClass="error" />
			</div>
			
			<button type="submit" class="btn btn-primary">Salva dettaglio</button>
		</form:form>
		<br>
	</div>
</body>
</html>