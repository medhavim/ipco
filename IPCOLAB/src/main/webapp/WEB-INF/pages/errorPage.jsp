<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="description" content="IPCOLab">
    <meta name="author" content="NEU CCIS Dept">
    
    <link rel="icon" type="image/png" href="https://png.icons8.com/law/ultraviolet/16/000000" />
	
	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<style type="text/css">
		<%@include file="../css/manageTutorial.css" %>
	</style>
	<title>Error Page</title>
<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<script>
		$(document).ready(function() {
			 window.open("userAuth.action", "_self");
		}); 
	</script>
</head>
<body>
<div class="container" id="errorPage">
<!--  style="position: center"-->
	<div class="jumbotron" id="errorPage">
		<!-- <h2 style="text-align: center">Oops!!</h2> -->
		<h3 id="errorPageText">Something went wrong. <br/>Please try again later.</h3>
	</div>
</div>
</body>
</html>