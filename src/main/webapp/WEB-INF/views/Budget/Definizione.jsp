<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Definizione</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/Menu/ListaBudget" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/Budget/showDefinizione/" var="saveURL" />
		
		<form:form modelAttribute="oggettoArea" method="post" action="${saveURL}" cssClass="form">
			<h3>Scegli un'area:</h3>
			<form:select path="idAreaInvestimento" cssClass="form-control" id="areaInvestimento">
				<form:option value="0">Seleziona un'area:</form:option>
				<form:options items="${elencoAree}" itemValue="idAreaInvestimento" itemLabel="areaInvestimento" />
			</form:select>
			<form:errors path="idAreaInvestimento" cssClass="error" />
			<br>
			<form:button type="submit" role="button" class="btn btn-primary">Cerca</form:button>
		</form:form>
	</div>
	<br>
	<c:if test="${oggettoArea.getIdAreaInvestimento() != 0}">
		<c:if test="${elencoSottoCategoriePerArea.size() == 0}">
			<br>
			<table class="table table-striped">
		   		<thead>
			    	<th scope="row">Non ci sono sottocategorie per quest'area investimento!</th>
			   	</thead>
		   	</table>
		   	<!--<spring:url value="/SottoCategoria/AddPrimaSottoCategoria" var="addURL" />
	  		<a href="${addURL}" role="button" class="btn btn-primary">Nuova SottoCategoria</a>-->
		</c:if>
		<br><br>
		<c:if test="${elencoSottoCategoriePerArea.size() != 0}">
			<div class="container">
			  	<h2>Lista SottoCategorie</h2>
			  	<table class="table table-striped">
			   		<thead>
				    	<th scope="row">Codice</th>
				    	<th scope="row">SottoCategoria</th>
				    	<th scope="row">Area Investimento</th>
				    	<th scope="row">Budget</th>
				    	<th scope="row">BudgetSpeso</th>
				    	<th></th>
				   	</thead>
				   	<tbody>
				    	<c:forEach items="${elencoSottoCategoriePerArea}" var="elenco">
				     	<tr>
				     		<td>${elenco.codice}</td>
				     		<td>${elenco.sottoCategoria}</td>
				     		<td>${elenco.oAreaInvestimento.areaInvestimento}</td>
				      		<td>${elenco.budget}</td>
				      		<td>${elenco.budgetSpeso}</td>
				      		<td>
				      			<spring:url value="/Budget/EditBudget/${elenco.idSottoCategoria}" var="editURL" />
				      			<a href="${editURL}" role="button" class="btn btn-primary">Definisci</a>
				      		</td>
				     	</tr>
				    	</c:forEach>
				   	</tbody>
			  	</table>
			  	<br>
		 	</div>
		</c:if>
	</c:if>
</body>
</html>