<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Topics</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/userProfile.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/manageUser.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="jumbotron title admin">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Manage User</span></li>
	</ol>
	<div class="container text-center">
		<h1>Manage User</h1>
		<p>Review all the users registered with the application</p>
	</div>
</div>
<div class="jumbotron content">
	<div class="container-fluid text-left">
<%-- 	<span class="h2">Welcome <strong><i>${damin.firstName},</i></strong></span> --%>
	</div>
	<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2 strong">Users</span>
		<c:if test="${empty registeredUsers}">
		<br>
		<div class="row bg-danger topic-row">
			<div class="col-sm-12">
			<h3>There are no users currently registered with the application</h3>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty registeredUsers}">
		<c:forEach items="${registeredUsers}" var="registeredUser">
			<div class="btn btn-info btn-block userProfile topic-row" id="registeredUser_${registeredUser.userId}">
			<div class="row">
				<div class="col-sm-8 text-left">
					<span class="h3">${registeredUser.lastName}, ${registeredUser.firstName}</span>
				</div>
				<div class="col-sm-4 text-right">
					<span class="deleteInstance h2" id="deleteRegUserId_${registeredUser.userId}"><i class="glyphicon glyphicon-remove-sign"></i></span>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4 pull-left text-left">
					<span class="h3"><i>Last Login: <fmt:formatDate value="${registeredUser.credential.updatedTs}" pattern="MMM dd, yyyy hh:mm a"/></i></span>
				</div>
				<div class="col-sm-4 pull-right text-left">
					<span class="h3"><i>Registered on: <fmt:formatDate value="${registeredUser.createdTs}" pattern="MMM dd, yyyy hh:mm a"/></i></span>
				</div>
			</div>
			</div>
		</c:forEach>
		</c:if>
	</div>
	</div>
	<hr>
<!-- 	<div class="container-fluid text-left"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-sm-4 pull-right text-right"> -->
<!-- 				<form action="startTutorial.action"  method="post"> -->
<!-- 					<input class="btn btn-primary button-wrapper start-tutorial" type="submit" name="Start Tutorial" value="Start Tutorial"/> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>
</body>
</html>