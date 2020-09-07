<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
	<div class = "container">
		<div style="width:600px;margin-left: auto;margin-right: auto;margin-top:24px;padding: 24px;">
			<div class="card">
				<div class="card-header" align = "center" style="font-weight:bold; font-size:1.2em;">
                	<i class="fa fa-user"></i>LOGIN
            	</div>
				<div class = "card-block" style="padding: 20px; margin-top:auto;">
					<spring:url value = "/login" var = "loginUrl"/>
					<form:form method = "post" class = "form" action = "${loginUrl}">
						<br>
						<c:if test = "${param.error != null}">
							<div class = "alert alert-danger">
								<p><spring:message code = "login.form.errmsg"/></p>
							</div>	
						</c:if>
						<br>
						<c:if test = "${param.forbidden != null}">
							<div class = "alert alert-danger">
								<p><spring:message code = "login.form.forbiddenmsg"/></p>
							</div>	
						</c:if>
						<br>
						<c:if test = "${param.logout != null}">
							<div class = "alert alert-success">
								<p><spring:message code = "login.form.logoutmsg"/></p>
							</div>	
						</c:if>
						<br><br>
						<div class = "form-group" style="margin-top:-70px;">
							<label for="username">Username</label>
							<input type="text" class="form-control" id="username" name="username" placeholder="Nome Utente" required/>
						</div>
						
						<div class = "form-group">
							<label for="password">Password</label>
							<input type="password" class="form-control" id="password" name="password" placeholder="Password" required/>
						</div>
						
						<div class = "form_check" style = "padding:20px;">
							<input type = "checkbox" class="form-check-input" id="remember" name="remember-me">
							<label class="form-check-label" for="remember-me">Ricordami</label>
						</div>
						
						<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
						
						<div align="center">
							<input type = "submit" class = "btn btn-primary" style="width:100px;" value = "Log In"/>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>