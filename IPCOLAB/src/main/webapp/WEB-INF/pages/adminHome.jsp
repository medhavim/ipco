<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <meta charset="ISO-8859-1"> -->
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="description" content="IPCOLab">
    <meta name="author" content="NEU CCIS Dept">
    
    <link rel="icon" type="image/png" href="https://png.icons8.com/law/ultraviolet/16/000000" />
	
	<title>Home</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<style type="text/css">
		<%@include file="../css/header.css" %>
		<%@include file="../css/adminHome.css" %>
	</style>
	<script type="text/javascript">
		<%@include file="../js/adminHome.js" %>
		<%@include file="../js/progress.js" %>
	</script>
	<script type="text/javascript">
		setInterval("checkLoad()",1000);
	</script>
</head>
<body>
	<div id="preLoaderDiv"></div>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="jumbotron">
	<div class="container-fluid option-content">
		<div class="row">
			<div class="col-sm-3 col-centered">
				<button id="manageTutorial.action" class="option btn btn-lg btn-circle btn-raised ripple-effect btn-primary">
				<h3><span class="glyphicon glyphicon-pencil"></span>&nbsp;Tutorial</h3>
				</button>
			</div>
			<div class="col-sm-3 col-centered">
				<button id="manageDiagnostic.action" class="option btn btn-lg btn-circle btn-raised ripple-effect btn-primary">
				<h3><span class="glyphicon glyphicon-pencil"></span>&nbsp;Diagnostic</h3>
				</button>
			</div>
			<div class="col-sm-3 col-centered">
				<button id="manageQuiz.action" class="option btn btn-lg btn-circle btn-raised ripple-effect btn-primary">
				<h3><span class="glyphicon glyphicon-pencil"></span>&nbsp;Quiz</h3>
				</button>
			</div>
			<div class="col-sm-3 col-centered">
				<button id="manageUser.action" class="option btn btn-lg btn-circle btn-raised ripple-effect btn-primary">
				<h3><span class="glyphicon glyphicon-pencil"></span>&nbsp;User</h3>
				</button>
			</div>
		</div>
		<form action="#" id="customForm" method="post"></form>
	</div>
	</div>
	<div id="bottom"></div>
</body>
</html>