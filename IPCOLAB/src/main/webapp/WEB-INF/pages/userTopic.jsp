<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<%@include file="../css/userTopic.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/userTopic.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%-- <div class="jumbotron title">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${instance.instanceName}</span></li>
	</ol>
	<div class="container text-center">
		<h1>Tutorial</h1>
		<p>You can see all the available topics under this tutorial. Complete them all by clicking on one of the available module.</p>
	</div> 
</div> --%>
<div class="jumbotron content">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${instance.instanceName}</span></li>
	</ol>
	<div class="container-fluid text-center">
		<span class="h2">Tutorial</span>
		<p>You can see all the available topics under this tutorial. Complete them all by clicking on one of the available module.</p>
	</div>
	<div class="container-fluid">
		<c:forEach items="${instance.instanceTopicList}" var="instanceTopic">
		<div class="row topic-row <c:choose><c:when test="${instanceTopic.status.statusId eq 1}">bg-info</c:when><c:when test="${instanceTopic.status.statusId eq 2}">bg-danger</c:when><c:otherwise>bg-success</c:otherwise></c:choose>"
			 data-toggle="collapse" data-target="#topicDesc_${instanceTopic.topic.topicId}">
			<div class="col-lg-12 col-sm-12 col-md-12">
<!-- 				<span class="h2 collapsed"  -->
<%-- 					  data-toggle="collapse" data-target="#topicDesc_${topic.topicId}" id="topic_name_${topic.topicId}">${topic.topicName}</span>   --%>
				<h2>${instanceTopic.topic.topicName}</h2>
				<div class="progress" style="width:100%">
					<div class="progress-bar <c:choose><c:when test="${instanceTopic.status.statusId eq 1}">progress-bar-info</c:when><c:when test="${instanceTopic.status.statusId eq 2}">progress-bar-danger active</c:when><c:otherwise>progress-bar-success</c:otherwise></c:choose> progress-bar-striped" 
					 	 role="progressbar" style="width:${instanceTopic.progress}%; min-width:2.5em;">${instanceTopic.progress}% </div>
				</div>
				<div id="moduleBtnAlign" class="row">
					<!--<div class="col-md-2">
					</div>
					  <div class="col-md-10">
						<div id="moduleBtnAlign" class="row" >-->
							<c:forEach items="${instanceTopic.instanceModuleList}" var="instanceModule">
							<!--  <div class="col-md-2"> -->
							<div id="moduleBtn" class="btn-group">
								<button class="instanceModule btn <c:choose><c:when test="${instanceModule.status.statusId eq 1}">btn-info</c:when><c:when test="${instanceModule.status.statusId eq 2}">btn-danger</c:when><c:otherwise>btn-success</c:otherwise></c:choose> button-wrapper btn-block"
									    <c:if test="${instanceModule.status.statusId eq 1 and instanceModule.module.orderNo ne 1 or fn:length(instanceModule.activityAnswers)<=0}">disabled onclick="alert(You do not have access to modules you have not started. \nYou can only access this module once you have finished all the modules beofre this.")"</c:if>
									    id="moduleId_${instanceModule.instanceModuleId}">${instanceModule.module.moduleName}</button>
							</div>
							</c:forEach>
							<c:if test="${instanceTopic.quiz ne null}">
							<!--  <div class="col-md-2"> -->
							<div id="moduleBtn" class="btn-group">
								<button class="instanceQuiz btn <c:choose><c:when test="${instanceTopic.quiz.status.statusId eq 1}">btn-info</c:when><c:when test="${instanceTopic.quiz.status.statusId eq 2}">btn-danger</c:when><c:otherwise>btn-success</c:otherwise></c:choose> button-wrapper btn-block"
									    <c:if test="${instanceTopic.quiz.status.statusId eq 1 or fn:length(instanceTopic.quiz.quizAnswers)<=0}">disabled onclick="alert(You do not have access to modules you have not started. \nYou can only access this module once you have finished all the modules beofre this.")"</c:if>
									    id="quizId_${instanceTopic.quiz.instanceQuizId}">${instanceTopic.quiz.quiz.quizName}</button>
							</div>
							</c:if>
						<!--</div>
					</div>-->
				</div>	
			</div>
			<div class="panel-collapse collapse" id="topicDesc_${instanceTopic.topic.topicId}">
				<div class="container-fluid">${instanceTopic.topic.topicDesc}</div>
			</div>
		</div>
		<br>
		</c:forEach>	
	</div>
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>
</body>
</html>