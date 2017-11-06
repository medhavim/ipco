<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Topics</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/userActivity.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/userActivity.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="jumbotron title">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
		<li class="breadcrumb-item"><a href="loadInstance.action?id=${instance.instanceId}">${instance.instanceName}</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${quiz.quiz.quizName} Report</span></li>
	</ol>
</div>
<div class="content">
	<!-- <div class="clear"></div> -->
	<div class="jumbotron">
	
		<div class="row">
			<div class="col-sm-6 text-left">
				<h2>Quiz Name: <b>${quiz.quiz.quizName}</b></h2>
			</div>
			<div class="col-sm-6 text-center">
				<h2>Score: <b>${quiz.score}/${fn:length(quiz.quizAnswers)}</b></h2>
			</div>
		</div>
		<jsp:include page="quizReportPages/templateMCQ.jsp"></jsp:include>
		<jsp:include page="quizReportPages/templateYESNO.jsp"></jsp:include>
		<jsp:include page="quizReportPages/templateImageDesc.jsp"></jsp:include>
		<jsp:include page="quizReportPages/templateImageMCQ.jsp"></jsp:include>
		<jsp:include page="quizReportPages/templateImageYESNO.jsp"></jsp:include>
		<jsp:include page="quizReportPages/templateVideoDesc.jsp"></jsp:include>
		<jsp:include page="quizReportPages/templateVideoMCQ.jsp"></jsp:include>
		<jsp:include page="quizReportPages/templateVideoYESNO.jsp"></jsp:include>
	</div>
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>

</body>
</html>