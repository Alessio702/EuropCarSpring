<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Utente</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Utente/ListaUtenti" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Utente/saveUtente/" var="saveURL" />
		<h2>Gestione Utente</h2>
		<form:form modelAttribute="oggettoUtente" method="post" action="${saveURL}" cssClass="form">
			<form:hidden path="id" />
			<!-- <form:hidden path="id" /> -->
			<!-- <form:hidden path="id" /> -->
			<!-- <form:hidden path="id" /> -->
			<div class="form-group">
				<label>Username</label>
				<form:input path="username" cssClass="form-control" id="username" />
				<form:errors path="username" cssClass="error" />
			</div>
			<div class="form-group">
				<label>Password</label>
				<form:input path="password" cssClass="form-control" id="password" type="password"/>
				<form:errors path="password" cssClass="error" />
			</div>
			<div class="form-group">
				<label>Ruolo <small>(ADMIN - USER)</small></label>
				<form:select path="roles" cssClass="form-control" id="roles" >
					<form:option value="0">Seleziona un ruolo:</form:option>
					<form:option value="USER">USER</form:option>
					<form:option value="ADMIN">ADMIN</form:option>
				</form:select>
				<form:errors path="roles" cssClass="error" />
			</div>
			<div class="form-group">
				<label>Venditore</label>
				<form:select path="oVenditore" cssClass="form-control" id="oVenditore" >
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