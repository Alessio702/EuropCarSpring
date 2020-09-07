<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Lista Utenti</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function deleteConfirm() {
	
			if (confirm("Eliminare l'utente selezionato?")) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Indietro</a>
<body>
	<div class="container">
		<h2>Lista Utenti</h2>
		<table class="table table-striped">
			<thead>
				<th scope="row">Username</th>
				<th scope="row">Password</th>
				<th scope="row">Ruolo</th>
				<th></th>
			</thead>
			<tbody>
				<c:forEach items="${elencoUtenti}" var="elenco">
					<tr>
						<td>${elenco.username}</td>
						<td>${elenco.password}</td>
						<td>${elenco.roles}</td>
						<td>
							<spring:url value="/Utente/DeleteUtente/${elenco.id}" var="deleteURL" /> 
							<a href="${deleteURL}" role="button" class="btn btn-primary" onclick="return deleteConfirm()">Elimina</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<spring:url value="/Utente/AddUtente/" var="addURL" />
		<a href="${addURL}" role="button" class="btn btn-primary">Nuovo Utente</a>
		<br><br>
		<h4>${errorMsg}</h4>
	</div>
</body>