<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentication</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="../css/loginRegister.css"> -->
<style type="text/css">
<%@include file="../css/loginRegister.css" %>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
<%@include file="../js/loginRegister.js" %>
</script>
<!-- <script type="text/javascript" src="../js/loginRegister.js"></script> -->
</head>
<body>
<div class="container">
   	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-login">
				<div class="panel-heading">
					<div class="row" id="login-view">
						<div class="col-xs-6">
							<a href="#" class="active" id="login-form-link">Login</a>
						</div>
						<div class="col-xs-6">
							<a href="#" id="register-form-link">Register</a>
						</div>
					</div>
					<div class="row hide" id="reset-view">
						<div class="col-xs-6">
							<a href="#" id="email-form-link">Email</a>
						</div>
						<div class="col-xs-6">
							<a href="#" id="reset-form-link">Reset</a>
						</div>
					</div>
					<hr>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<span class="errorMsg alert alert-danger"></span>
							<input type="hidden" id="errorMsg" value="${errorMsg}" /> 
						</div>
					</div>
					<div class="row" id="login-form-view">
						<div class="col-lg-12">
							<form:form id="login-form" action="login.action" modelAttribute="userLogin" method="post" role="form" style="display: block;">
								<div class="form-group input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span><form:input type="text" path="username" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" required="ture"/>
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span><form:input type="password" path="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required="ture"/>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 col-sm-offset-3">
											<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-lg-12">
											<div class="text-center">
												<a href="#" tabindex="5" class="forgot-password" id="forgot-password">Forgot Password?</a>
											</div>
										</div>
									</div>
								</div>
							</form:form>	
							<form:form id="register-form" action="signUp.action" modelAttribute="userRegister" method="post" role="form" style="display: none;" onsubmit="return validateRegister(this.id);">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span><form:input type="text" path="firstName" name="firstname" id="firstname" tabindex="1" class="form-control" placeholder="First Name" value="" required="true"/>
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span><form:input type="text" path="lastName" name="lastname" id="lastname" tabindex="1" class="form-control" placeholder="Last Name" value="" required="true"/>
										</div>
									</div>
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span><form:input type="email" path="email" name="email" id="registerEmail" tabindex="1" class="form-control" placeholder="Email Address" value="" required="true"/><span class="input-group-addon"><i class="email-check glyphicon glyphicon-refresh glyphicon-refresh-animate"></i></span>
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
									<form:input type="text" path="credential.username" name="username" id="registerUsername" tabindex="1" class="form-control" placeholder="Username" value="" required="true"/>
									<span class="input-group-addon"><i class="username-check glyphicon glyphicon-refresh glyphicon-refresh-animate"></i></span>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span><form:input type="password" path="credential.password" name="password" id="registerPassword" tabindex="2" class="form-control" placeholder="Password" required="true"/>
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span><input type="password" name="confirm-password" id="registerConfirm-password" tabindex="2" class="form-control" placeholder="Confirm Password" required="true"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 col-sm-offset-3">
											<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register">
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
					<div class="row hide" id="reset-form-view">
						<div class="col-lg-12">
							<form id="email-form" action="#" method="post" role="form" style="display: block;">
								<div class="form-group input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
									<input type="email" name="email" id="resetEmail" tabindex="1" class="form-control" placeholder="Email Address" value="" required/>
									<span class="input-group-addon"><i class="email-check glyphicon glyphicon-refresh glyphicon-refresh-animate"></i></span>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6">
											<input type="button" name="cancel-reset" id="cancel-reset" tabindex="4" class="form-control btn btn-danger" value="Cancel">
										</div>
										<div class="col-sm-6">
											<input type="submit" name="reset-next" id="reset-next" tabindex="4" class="form-control btn btn-success" value="Next">
										</div>
									</div>
								</div>
							</form>	
							<form:form id="reset-form" action="resetCredential.action" modelAttribute="newCredential" method="post" role="form" style="display: none;" onsubmit="return validateRegister(this.id);">
								<div class="form-group input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
									<form:input type="text" path="username" name="username" id="resetUsername" tabindex="1" class="form-control" placeholder="Username" value="" required="true"/>
									<span class="input-group-addon"><i class="username-check glyphicon glyphicon-refresh glyphicon-refresh-animate"></i></span>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span><form:input type="password" path="password" name="password" id="resetPassword" tabindex="2" class="form-control" placeholder="Password" required="true"/>
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span><input type="password" name="confirm-password" id="resetConfirm-password" tabindex="2" class="form-control" placeholder="Confirm Password" required/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6">
											<input type="button" name="reset-cancel" id="reset-cancel" tabindex="4" class="form-control btn btn-danger" value="Cancel">
										</div>
										<div class="col-sm-6">
											<input type="submit" name="reset-submit" id="reset-submit" tabindex="4" class="form-control btn btn-register" value="Update">
										</div>
									</div>
								</div>
								<form:input type="hidden" path="credentialId" id="reset-credentialId" />
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>