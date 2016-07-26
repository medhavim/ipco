<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/userHome.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/userHome.js" %>
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="jumbotron">
	<div class="container-fluid option-content">
		<div class="row">
			<div class="col-sm-3">
				<div class="thumbnail tile tile-large tile-turquoise option-desc" id="option-1-desc">
					<a href="#">
					<h4 class="tile-text text-center">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</h4>
					</a>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="thumbnail tile tile-large tile-green-sea option" id="option-1">
				<a href="#">
				<h1 class="tile-text text-center">Overview of IP</h1>
				</a>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="thumbnail tile tile-large tile-peter-river option" id="option-2">
				<a href="#">
					<h1  class="tile-text text-center" >Customize Tutorial</h1>
					</a>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="thumbnail tile tile-large tile-belize-hole option-desc" id="option-2-desc">
					<a href="#">
					<h4 class="tile-text text-center">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</h4>
					</a>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>