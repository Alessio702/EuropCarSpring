<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Edit SottoCategoria</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/SottoCategoria/ListaSottoCategorie" role="button">Indietro</a>

<body>
	<div class="container">
		<spring:url value="/SottoCategoria/SaveSottoCategoria" var="saveURL" />
		<h2>Gestione SottoCategoria</h2>
		<form:form modelAttribute="oggettoSottoCategoria" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="idSottoCategoria" />
			<div class="form-group">
				<label>Sotto Categoria</label>
				<form:input path="sottoCategoria" cssClass="form-control" id="sottoCategoria" />
				<form:errors path="sottoCategoria" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Codice</label>
				<form:input path="codice" cssClass="form-control" id="codice" />
				<form:errors path="codice" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Budget</label>
				<form:input path="budget" cssClass="form-control" id="budget" />
				<form:errors path="budget" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Budget Speso</label>
				<form:input path="budgetSpeso" cssClass="form-control" id="budgetSpeso" disabled = "true"/>
				<form:errors path="budgetSpeso" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label>Area Investimento</label>
				<form:select path="oAreaInvestimento" cssClass="form-control" id="oAreaInvestimento">
					<form:option value="0">Seleziona un area investimento:</form:option>
					<form:options items="${elencoAree}" itemValue="idAreaInvestimento" itemLabel="areaInvestimento" />
				</form:select>
				<form:errors path="oAreaInvestimento" cssClass="error" />
			</div>

			<button type="submit" class="btn btn-primary">Salva</button>
		</form:form>
	</div>
</body>
</html>