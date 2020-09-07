<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Lista Anni</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${elencoAnni.size() == 0}">
		<br>
		<table class="table table-striped">
	   		<thead>
		    	<th scope="row">Non ci sono anni contabili!</th>
		   	</thead>
	   	</table>
	   	<spring:url value="/GeneraPrimoAnnoContabile" var="addURL" />
  		<a href="${addURL}" role="button" class="btn btn-primary">Genera</a>
	</c:if>
	<c:if test="${elencoAnni.size() != 0}">
		<div class="container">
			<h2>Lista Anni Contabili</h2>
			<table class="table table-striped">
				<thead>
					<th scope="row">Descrizione</th>
					<th scope="row">Data Inizio</th>
					<th scope="row">Data Fine</th>
					<th></th>
				</thead>
				<tbody>
					<c:forEach items="${elencoAnni}" var="elenco">
						<tr>
							<td>${elenco.descrizione}</td>
							<td>${elenco.dataInizio}</td>
							<td>${elenco.dataFine}</td>
							<td>
								<spring:url value="/SelezionaAnno/${elenco.idAnno}" var="selectURL" /> 
								<a href="${selectURL}" role="button" class="btn btn-primary">Seleziona</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</body>