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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<style type="text/css">
		<%@include file="../../css/header.css" %>
		<%@include file="../../css/adminActivity.css" %>
		<%@include file="../../css/userTopic.css" %>
		<%@include file="../../css/userActivity.css" %>
	</style>
	<script type="text/javascript">
		<%@include file="../../js/manageUser.js" %>
		<%-- <%@include file="../../js/userTopic.js" %> --%>
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<%-- <div class="jumbotron title admin">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="manageUserProfile.action">Back to <strong><i>${reviewUser.firstName}'s</i></strong> Profile</a></li>
		<li class="breadcrumb-item"><a href="manageUserInstance.action">${instance.instanceName}</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${instanceTopic.topic.topicName}</span></li>
	</ol>
	<div class="container text-center">
		<h1>Reviewing topic progress for: <strong><i>${reviewUser.firstName}</i></strong></h1>
		<p>You can see all the available modules, activities and quiz under this topic. Review them all by clicking on one of the modules and quiz.</p>
	</div>
</div> --%>

<div class="jumbotron content">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="manageUserProfile.action">Back to <strong><i>${reviewUser.firstName}'s</i></strong> Profile</a></li>
		<li class="breadcrumb-item"><a href="manageUserInstance.action">${instance.instanceName}</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${instanceTopic.topic.topicName}</span></li>
	</ol>
	<div class="container-fluid text-center">
		<span class="h2">Reviewing Tutorials for: <strong><i>${reviewUser.firstName} ${reviewUser.lastName}</i></strong></span>
		<p>You can see all the available topics under this tutorial. Review them all by clicking on one of the updated module.</p>
	</div>
	<div class="jumbotron">
		<div class="container-fluid text-left desc-body">
			<ul class="nav nav-pills nav-justified actTemplate">
				<c:forEach items="${instanceTopic.instanceModuleList}" var="instanceModule">
				<li role="presentation" ${instanceModule.instanceModuleId==currInstanceModuleId?'class="active"':'' }>
					<a class="topicComponent" id="#module_${instanceModule.instanceModuleId}" >${instanceModule.module.moduleName}</a></li>
				</c:forEach>
				<c:if test="${instanceTopic.quiz ne null}">
				<li role="presentation" ${instanceTopic.quiz.instanceQuizId==currInstanceQuizId?'class="active"':'' }>
					<a class="topicComponent" id="#quiz_${instanceTopic.quiz.instanceQuizId}" >${instanceTopic.quiz.quiz.quizName}</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<div class=" content">
		<div class="container-fluid">
			<c:forEach items="${instanceTopic.instanceModuleList}" var="instanceModule">
			<c:set var="currInstanceModule" scope="request" value="${instanceModule}"/>
			<div class="topicComponentContent" style="display: ${instanceModule.instanceModuleId==currInstanceModuleId?'block':'none'}" id="module_${instanceModule.instanceModuleId}">
			<jsp:include page="../manageUserTemplatePages/templateInfo.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateMCQ.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateYESNO.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateImageDesc.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateImageMCQ.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateImageYESNO.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateVideoDesc.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateVideoMCQ.jsp"></jsp:include>
			<jsp:include page="../manageUserTemplatePages/templateVideoYESNO.jsp"></jsp:include>
			</div>
			</c:forEach>
			<c:if test="${instanceTopic.quiz ne null}">
			<div class="topicComponentContent" style="display: ${instanceTopic.quiz.instanceQuizId==currInstanceQuizId?'block':'none'}" id="quiz_${instanceTopic.quiz.instanceQuizId}">
			<c:set var="quiz" scope="request" value="${instanceTopic.quiz}"/>
				<div class="row">
					<div class="col-sm-6 text-left">
						<h2>Quiz Name: <b>${quiz.quiz.quizName}</b></h2>
					</div>
					<div class="col-sm-6 text-center">
						<h2>Score: <b>${quiz.score}/${fn:length(quiz.quizAnswers)}</b></h2>
					</div>
				</div>
				<jsp:include page="../quizReportPages/templateMCQ.jsp"></jsp:include>
				<jsp:include page="../quizReportPages/templateYESNO.jsp"></jsp:include>
				<jsp:include page="../quizReportPages/templateImageDesc.jsp"></jsp:include>
				<jsp:include page="../quizReportPages/templateImageMCQ.jsp"></jsp:include>
				<jsp:include page="../quizReportPages/templateImageYESNO.jsp"></jsp:include>
				<jsp:include page="../quizReportPages/templateVideoDesc.jsp"></jsp:include>
				<jsp:include page="../quizReportPages/templateVideoMCQ.jsp"></jsp:include>
				<jsp:include page="../quizReportPages/templateVideoYESNO.jsp"></jsp:include>
			</div>
			</c:if>
		</div>
	</div>
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>
</body>
</html>