<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Fattura Passiva</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/FatturaPassiva/ListaFatture" role="button">Indietro</a>

<body>
	<div class="container">
		<spring:url value="/FatturaPassiva/AddPrimaFatturaPassivaDettaglio" var="saveURL" />
		<h2>Nuovo Fattura Passiva</h2>
		<form:form modelAttribute="oggettoFattura" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idFatturaPassiva" />
			<form:hidden path="dettagli"/>
			<div class="form-group">
				<label>Descrizione</label>
				<form:input path="descrizione" cssClass="form-control" id="descrizione" />
				<form:errors path="descrizione" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Numero</label>
				<form:input path="numero" cssClass="form-control" id="numero" />
				<form:errors path="numero" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Data</label>
				<form:input type = "date" path="data" cssClass="form-control" id="data" />
				<form:errors path="data" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Fornitore</label>
				<form:select path="oFornitore" cssClass="form-control" id="oFornitore">
					<form:option value="0">Seleziona un fornitore:</form:option>
					<form:options items="${elencoFornitori}" itemValue="idFornitore" itemLabel="ragioneSociale" />
				</form:select>
				<form:errors path="oFornitore" cssClass="error" />
			</div>
			<table class="table table-striped">
	   			<thead>
			    	<th scope="row">Non ci sono dettagli!</th>
			   	</thead>
		   	</table>
		   	<br><button type="submit" class="btn btn-primary">Aggiungi dettaglio</button>
		</form:form>
	</div>
</body>
</html>