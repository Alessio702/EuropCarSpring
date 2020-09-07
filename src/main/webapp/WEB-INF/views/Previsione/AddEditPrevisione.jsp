<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Edit Previsione</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Previsione/ListaPrevisioni" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Previsione/SavePrevisione/" var="saveURL" />
		<h2>Gestione Previsione</h2>
		<form:form modelAttribute="oggettoPrevisione" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idPrevisione" />
			<form:hidden path="annoDiRiferimento" />
			<form:hidden path="dataRegistrazione" />
			<form:hidden path="potenzialeEuropCar" />
			<form:hidden path="shareAnte" />
			<form:hidden path="sharePost" />
			<form:hidden path="shareAvis" />
			<form:hidden path="shareHertz" />
			<form:hidden path="shareMaggiore" />
			<form:hidden path="shareSixt" />
			<div class="form-group">
				<label>Anno Di Riferimento</label>
				<form:input path="annoDiRiferimento" cssClass="form-control" id="annoDiRiferimento" disabled = "true"/>
				<form:errors path="annoDiRiferimento" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Confidenza</label>
				<form:input path="confidenza" cssClass="form-control" id="confidenza" />
				<form:errors path="confidenza" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Potenziale Azienda</label>
				<form:input path="potenzialeAzienda" cssClass="form-control" id="potenzialeAzienda" />
				<form:errors path="potenzialeAzienda" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Data Visita</label>
				<form:input type = "date" path="dataVisita" cssClass="form-control" id="dataVisita" />
				<form:errors path="dataVisita" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Area Geografica</label>
				<form:select path="oAreaGeo" cssClass="form-control" id="oAreaGeo">
					<form:option value="0">Seleziona un'area:</form:option>
					<form:options items="${elencoAreeGeo}" itemValue="idarea" itemLabel="area" />
				</form:select>
				<form:errors path="oAreaGeo" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Azienda</label>
				<form:select path="oAzienda" cssClass="form-control" id="oAzienda">
					<form:option value="0">Seleziona un'azienda:</form:option>
					<form:options items="${elencoAziende}" itemValue="idAzienda" itemLabel="ragioneSociale" />
				</form:select>
				<form:errors path="oAzienda" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>SottoCategoria</label>
				<form:select path="oSottoCategoria" cssClass="form-control" id="oSottoCategoria">
					<form:option value="0">Seleziona una sottocategoria:</form:option>
					<form:options items="${elencoSottoCategorie}" itemValue="idSottoCategoria" itemLabel="sottoCategoria" />
				</form:select>
				<form:errors path="oSottoCategoria" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Venditore</label>
				<form:select path="oVenditore" cssClass="form-control" id="oVenditore">
					<form:option value="0">Seleziona un venditore:</form:option>
					<form:options items="${elencoVenditori}" itemValue="idVenditore" itemLabel="cognome" />
				</form:select>
				<form:errors path="oVenditore" cssClass="error" />
			</div>
			
			<button type="submit" class="btn btn-primary">Salva</button>
		</form:form>
	</div>
</body>
</html>