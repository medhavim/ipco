<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<%@include file="../js/progress.js" %>
	</script>
	<script type="text/javascript">
		setInterval("checkLoad()",1000);
	</script>
</head>
<body>
<div id="preLoaderDiv"></div>
<jsp:include page="header.jsp"></jsp:include>
<%-- <div class="jumbotron title">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
		<li class="breadcrumb-item"><a href="loadInstance.action?id=${instance.instanceId}">${instance.instanceName}</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${quiz.quiz.quizName}</span></li>
	</ol>
</div> --%>
<div class="jumbotron content user">
	<!-- <div class="clear"></div> -->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
		<li class="breadcrumb-item"><a href="loadInstance.action?id=${instance.instanceId}">${instance.instanceName}</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${quiz.quiz.quizName}</span></li>
	</ol>
	<div class="jumbotron">
		<div class="nav-bar">
			<div class="btn-group btn-group-justified" role="group" >
				<c:forEach items="${quiz.quizAnswers}" var="quizAnswer">
				<div class="btn-group" role="group">
			    	<button type="button" 
	    			class="btn <c:if test="${quizAnswer.status.statusId eq 2}">btn-info <c:if test="${quizAnswer.quizAnswerId eq currentQuizAnswer.quizAnswerId}">current-ongoing-module</c:if></c:if>
	    			<c:if test="${quizAnswer.status.statusId eq 3}">btn-success <c:if test="${quizAnswer.quizAnswerId eq currentQuizAnswer.quizAnswerId}">current-complete-module</c:if></c:if>"
			    	<c:if test="${quizAnswer.status.statusId eq 1}">disabled</c:if>>${quizAnswer.quizOption.activity.activityTitle}</button>
			  	</div>
				</c:forEach>
			</div>
		</div>
		<div class="jumbotron tile black title">
			<div class="container-fluid text-left activity-content">
			<h2 class="title">${currentQuizAnswer.quizOption.activity.activityText}</h2>
			</div>
		<form action="saveQuiz.action" id="activityForm" method="post">
		<input type="hidden" name="navType" id="navType"/>
		<jsp:include page="helperPages/userQuizTemplateMCQ.jsp"></jsp:include>
		<jsp:include page="helperPages/userQuizTemplateYESNO.jsp"></jsp:include>
		<jsp:include page="helperPages/userQuizTemplateImageDesc.jsp"></jsp:include>
		<jsp:include page="helperPages/userQuizTemplateImageMCQ.jsp"></jsp:include>
		<jsp:include page="helperPages/userQuizTemplateImageYESNO.jsp"></jsp:include>
		<jsp:include page="helperPages/userQuizTemplateVideoDesc.jsp"></jsp:include>
		<jsp:include page="helperPages/userQuizTemplateVideoMCQ.jsp"></jsp:include>
		<jsp:include page="helperPages/userQuizTemplateVideoYESNO.jsp"></jsp:include>
		
		<div class="jumbotron">
			<div class="container-fluid text-center">
				<div class="row">
					<div class="col-sm-4 pull-left text-left">
<%-- 						<c:if test="${not empty instanceTopic.prevModules and empty instanceModule.prevActivity}"> --%>
<!-- 						<a class="btn btn-primary pull-left btn-nav" id="prev-module">Previous</a> -->
<%-- 						</c:if> --%>
						<c:if test="${currentQuizAnswer.quizOption.orderNo ne 1}">
						<a class="btn btn-primary pull-left btn-nav btn-next" id="prev-quiz">Previous</a>
						</c:if>
					</div>
					<div class="col-sm-4 pull-right text-right">
<%-- 						<c:if test="${not empty instanceTopic.nextModules and empty instanceModule.nextActivity}"> --%>
<!-- 						<a class="btn btn-primary pull-left" data-toggle="modal" -->
<!-- 							data-target="#nextModuleModal">Next</a> -->
<%-- 						</c:if> --%>
						<c:if test="${(fn:length(quiz.quiz.quizOptions) ne currentQuizAnswer.quizOption.orderNo)}">
						<a class="btn btn-primary pull-left btn-nav btn-next" id="next-quiz">Next</a>
						</c:if>
						<c:if test="${(fn:length(quiz.quiz.quizOptions) eq currentQuizAnswer.quizOption.orderNo)}">
						<a class="btn btn-primary pull-left btn-nav btn-finished btn-next" id="quiz-finish">Finish</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	</div>
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>

<div class="modal fade" id="nextModuleModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Congratulations!!!</h2>
			</div>
			<div class="modal-body form-group">
				<h2>You are about to complete this module.</h2>
			</div>
			<div class="modal-footer">
			<div class="row">
				<div class="col-sm-5 pull-right form-group">
					<input type="button" class="btn btn-info btn-block btn-lg btn-nav" role="button"
					value="Go back to the dashboard." id="go-to-dashboard" />
				</div>
				<div class="col-sm-5 pull-left form-group">
					<input type="button" class="btn btn-success btn-block btn-lg btn-nav" role="button"
					value="Continue to next Module." id="next-module" />
				</div>
			</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="#finishQuizModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">YAY!!!</h2>
			</div>
			<div class="modal-body form-group">
				<h2>You are about to successfully complete this topic.</h2>
				<h2>Do you wanna take a Quiz?</h2>
			</div>
			<div class="modal-footer">
			<div class="row">
				<div class="col-sm-5 pull-right form-group">
					<input type="button" class="btn btn-info btn-block btn-lg btn-nav" role="button"
					value="Go back to the dashboard" id="go-to-dashboard" />
				</div>
				<div class="col-sm-5 pull-left form-group">
					<input type="button" class="btn btn-success btn-block btn-lg btn-nav" role="button"
					value="Take a Quiz" id="take-quiz" />
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<div id="bottom"></div>
</body>
</html>