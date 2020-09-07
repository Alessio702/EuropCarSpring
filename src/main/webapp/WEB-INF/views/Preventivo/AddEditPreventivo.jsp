<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Edit Preventivo</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Preventivo/ListaPreventivi" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Preventivo/SavePreventivo/" var="saveURL" />
		<h2>Gestione Preventivo</h2>
		<form:form modelAttribute="oggettoPreventivo" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idPreventivo" />
			<div class="form-group">
				<label>Codice</label>
				<form:input path="codice" cssClass="form-control" id="codice" />
				<form:errors path="codice" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Preventivo</label>
				<form:input path="preventivo" cssClass="form-control" id="preventivo" />
				<form:errors path="preventivo" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Fornitore</label>
				<form:select path="oFornitore" cssClass="form-control" id="oFornitore">
					<form:option value="0">Seleziona un fornitore:</form:option>
					<form:options items="${elencoFornitori}" itemValue="idFornitore" itemLabel="ragioneSociale" />
				</form:select>
				<form:errors path="oFornitore" cssClass="error" />
			</div>
			
			<button type="submit" class="btn btn-primary">Salva</button>
		</form:form>
	</div>
</body>
</html>