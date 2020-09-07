<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Edit Venditore</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Venditore/ListaVenditori" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Venditore/SaveVenditore/" var="saveURL" />
		<h2>Gestione Venditore</h2>
		<form:form modelAttribute="oggettoVenditore" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idVenditore" />
			<div class="form-group">
				<label>Cognome</label>
				<form:input path="cognome" cssClass="form-control" id="cognome" />
				<form:errors path="cognome" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Nome</label>
				<form:input path="nome" cssClass="form-control" id="nome" />
				<form:errors path="nome" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Indirizzo</label>
				<form:input path="indirizzo" cssClass="form-control" id="indirizzo" />
				<form:errors path="indirizzo" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Numero di telefono</label>
				<form:input path="numeroTelefono" cssClass="form-control" id="numeroTelefono" />
				<form:errors path="numeroTelefono" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Tipo Venditore</label>
				<form:select path="oTipoVenditore" cssClass="form-control" id="oTipoVenditore">
					<form:option value="0">Seleziona un tipo:</form:option>
					<form:options items="${elencoTipoVenditore}" itemValue="idTipoVenditore" itemLabel="tipoVenditore" />
				</form:select>
				<form:errors path="oTipoVenditore" cssClass="error" />
			</div>
			<button type="submit" class="btn btn-primary">Salva</button>
		</form:form>
	</div>
</body>
</html>