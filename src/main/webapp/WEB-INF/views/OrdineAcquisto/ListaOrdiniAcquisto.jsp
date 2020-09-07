<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix = "spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Lista Ordine Acquisto</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<script type = "text/javascript">
		function deleteConfirm() {

			if (confirm("Eliminare l'ordine acquisto selezionato?")) {
				return true;
  			} else {
   				return false;
  			}
 	}
	</script>
</head>
<a class="btn btn-light" href="http://localhost:8080/Menu/List" role="button">Home</a>
<a class="btn btn-light" href="http://localhost:8080/OrdineAcquisto/Menu" role="button">Indietro</a>
<body>
	<div class="container">
		<spring:url value="/OrdineAcquisto/CercaOrdinePerFornitore" var="saveURL" />
		<form:form modelAttribute="oggettoFornitore" method="post" action="${saveURL}" cssClass="form">
			<h3>Scegli fornitore</h3>
			<form:select path="idFornitore" cssClass="form-control" id="ragioneSociale">
				<form:option value="0">Seleziona un fornitore:</form:option>
				<form:options items="${elencoFornitori}" itemValue="idFornitore" itemLabel="ragioneSociale" />
			</form:select>
			<form:errors path="idFornitore" cssClass="error" />
			<br>
			<form:button type= "submit" role="button" class="btn btn-primary">Cerca</form:button>
		</form:form>
	</div>
	<c:if test = "${oggettoFornitore.getIdFornitore() != 0}">
		<c:if test="${elencoOrdiniAcquisto.size() == 0}">
			<br>
			<table class="table table-striped">
		   		<thead>
			    	<th scope="row">Non ci sono ordini di acquisto!</th>
			   	</thead>
		   	</table>
		   	<spring:url value="/OrdineAcquisto/AddPrimoOrdineAcquisto" var="addURL" />
	  		<a href="${addURL}" role="button" class="btn btn-primary">Nuovo Ordine Acquisto</a>
		</c:if>
		<br><br>
		<c:if test="${elencoOrdiniAcquisto.size() != 0}">
			<div class="container">
			  	<h2>Lista Ordine Acquisto</h2>
			  	<table class="table table-striped">
			   		<thead>
				    	<th scope="row">Ordine Acquisto</th>
				    	<th scope="row">Importo</th>
				    	<th scope="row">Data</th>
				    	<th scope="row">Fornitore</th>
				    	<th></th>
				    	<th></th>
				   	</thead>
				   	<tbody>
				    	<c:forEach items="${elencoOrdiniAcquisto}" var="elenco">
				     	<tr>
				     		<td>${elenco.ordineAcquisto}</td>
				     		<td>${elenco.importo}</td>
				     		<td>${elenco.data}</td>
				      		<td>${elenco.oFornitore.ragioneSociale}</td>
				      		<td>
				      			<spring:url value="/OrdineAcquisto/EditOrdineAcquisto/${elenco.idOrdineAcquisto}" var="editURL" />
				      			<a href="${editURL}" role="button" class="btn btn-primary">Modifica</a>
				      		</td>
				      		<td>
				      			<spring:url value="/OrdineAcquisto/DeleteOrdineAcquisto/${elenco.idOrdineAcquisto}" var="deleteURL" />
				      			<a href="${deleteURL}" role="button" class="btn btn-primary" onclick="return deleteConfirm()">Elimina</a>
				      		</td>
				     	</tr>
				    	</c:forEach>
				   	</tbody>
			  	</table>
				
			  	<spring:url value="/OrdineAcquisto/AddPrimoOrdineAcquisto" var="addURL" />
			  	<a href="${addURL}" role="button" class="btn btn-primary">Nuovo</a>
		 	</div>
		</c:if>
	</c:if>
</body>
</html>