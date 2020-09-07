<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Edit Azienda</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Azienda/ListaAziende" role="button">Indietro</a>

<body>
	<div class="container">
		<spring:url value="/Azienda/SaveAzienda" var="saveURL" />
		<h2>Gestione Azienda</h2>
		<form:form modelAttribute="oggettoAzienda" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idAzienda" />
			<div class="form-group">
				<label>ContractId</label>
				<form:input path="contractId" cssClass="form-control" id="contractId" />
				<form:errors path="contractId" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Indirizzo</label>
				<form:input path="indirizzo" cssClass="form-control" id="indirizzo" />
				<form:errors path="indirizzo" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Ragione Sociale</label>
				<form:input path="ragioneSociale" cssClass="form-control" id="ragioneSociale" />
				<form:errors path="ragioneSociale" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Gruppo</label>
				<form:select path="oGruppo" cssClass="form-control" id="oGruppo">
					<form:option value="0">Seleziona un gruppo:</form:option>
					<form:options items="${elencoGruppi}" itemValue="idGruppo" itemLabel="nomeGruppo" />
				</form:select>
				<form:errors path="oGruppo" cssClass="error" />
			</div>

			<button type="submit" class="btn btn-primary">Salva</button>
		</form:form>
	</div>
</body>
</html>