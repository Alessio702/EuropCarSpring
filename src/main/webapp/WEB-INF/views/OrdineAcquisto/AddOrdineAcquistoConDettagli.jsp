<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Ordine Acquisto</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/OrdineAcquisto/ListaOrdiniAcquisto" role="button">Indietro</a>

<body>
	<div class="container">
		<spring:url value="/OrdineAcquisto/SaveOrdineAcquisto" var="saveURL" />
		<h2>Nuovo Ordine Acquisto</h2>
		<form:form modelAttribute="oggettoOrdineAcquisto" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idOrdineAcquisto" />
			<form:hidden path="dettagli"/>
			<div class="form-group">
				<label>Ordine Acquisto </label>
				<form:input path="ordineAcquisto" cssClass="form-control" id="ordineAcquisto" />
				<form:errors path="ordineAcquisto" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Importo</label>
				<form:input path="importo" cssClass="form-control" id="importo" />
				<form:errors path="importo" cssClass="error" />
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
			    	<th scope="row">Spesa Investimento</th>
			    	<th scope="row">Progetto</th>
			    	<th scope="row">Importo</th>
			    	<th scope="row">Quantita</th>
			    	<th></th>
			   	</thead>
			   	<tbody>
			   		<c:forEach items="${elencoDettagli}" var="elenco">
		   				<tr>
		   					<td>${elenco.oSpesaInvestimento.spesaInvestimento}</td>
		   					<td>${elenco.oProgetto.codice}</td>
		   					<td>${elenco.importo}</td>
		   					<td>${elenco.quantita}</td>
		   					<td>
				      			<spring:url value="/OrdineAcquisto/DeleteOrdineAcquistoDettaglio/0/${elenco.identifier}" var="deleteURL" />
				      			<a href="${deleteURL}" role="button" class="btn btn-primary" onclick="return deleteConfirm()">Elimina</a>
				      		</td>
		   				</tr>
		   			</c:forEach>
		   		</tbody>
		   	</table>
		   	<br>
		   	<!--<spring:url value="/OrdineAcquisto/SaveOrdineAcquisto" var="saveURL" />
			<a href="${saveURL}" role="button" class="btn btn-primary">Salva</a>-->&nbsp;
			<button type="submit" class="btn btn-primary">Salva</button>
			<spring:url value="/OrdineAcquisto/AddOrdineAcquistoDettaglio" var="addURL" />
			<a href="${addURL}" role="button" class="btn btn-primary">Aggiungi Dettaglio</a>
		</form:form>
	</div>
</body>
</html>