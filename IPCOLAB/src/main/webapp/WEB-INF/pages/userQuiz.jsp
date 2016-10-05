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
		<li class="breadcrumb-item"><a href="loadInstance.action?id=${instance.instanceId}">Topic</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Activity</span></li>
	</ol>
</div>
<div class="jumbotron content">
<!-- 	<div class="nav-bar"> -->
<!-- 		<div class="btn-group btn-group-justified" role="group" > -->
<%-- 			<c:forEach items="${instanceTopic.instanceModuleList}" var="instModule"> --%>
<!-- 			<div class="btn-group" role="group"> -->
<!-- 		    	<button type="button"  -->
<%-- 		    	class="btn <c:if test="${instModule.status.statusId eq 1}">btn-default <c:if test="${instModule.instanceModuleId eq instanceTopic.currModule.instanceModuleId}">current-ongoing-module</c:if></c:if> --%>
<%-- 		    	<c:if test="${instModule.status.statusId eq 2}">btn-info <c:if test="${instModule.instanceModuleId eq instanceTopic.currModule.instanceModuleId}">current-ongoing-module</c:if></c:if> --%>
<%-- 		    	<c:if test="${instModule.status.statusId eq 3}">btn-success <c:if test="${instModule.instanceModuleId eq instanceTopic.currModule.instanceModuleId}">current-complete-module</c:if></c:if>" --%>
<%-- 		    	<c:if test="${instModule.status.statusId eq 1}">disabled</c:if>>${instModule.module.moduleName}</button> --%>
<!-- 		  	</div> -->
<%-- 			</c:forEach> --%>
<!-- 		</div> -->
<!-- 	</div> -->
	<div class="clear"></div>
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
		<div class="jumbotron tile gray title">
			<div class="container-fluid text-left activity-content">
			<h2 class="title">${currentQuizAnswer.quizOption.activity.activityText}</h2>
			</div>
		<form action="saveQuiz.action" id="activityForm" method="post">
		<input type="hidden" name="navType" id="navType"/>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 1}">
		<div class="jumbotron">
			<div class="container-fluid text-center activity-content">
				<div class="row mcqOptions form-group">
					<c:forEach items="${currentQuizAnswer.userAnswers}" var="answer">
					<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
					   	<span style="white-space: nowrap;"> 
						   	<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}" value="selectedAnswer_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>
							<label class="option-text h3">${answer.optionText}</label>
						</span>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 2}">
		<div class="jumbotron">
			<div class="container-fluid text-center activity-content">
				<div class="row form-group">
					<div class="col-md-2">
					</div>
					<c:forEach items="${currentQuizAnswer.userAnswers}" var="answer">
					<div class="col-md-4 form-group">
						<label class="btn btn-block ${answer.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
							<input style="display:none;" class="optionInput" 
								type="radio" name="yesno-option" id="inlineRadio1" 
								value="yesno_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>${answer.optionText}
						</label>
					</div>
					</c:forEach>
					<div class="col-md-4">
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 3}">
		<div class="jumbotron">
			<div class="container-fluid text-center image-desc activity-content" >
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<img src="${option.optionText}" style="width:100%; height:100%;">
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="form-group image-options">
							<textarea class="form-control" rows="5" name="userAnswer"
								placeholder="Enter Answer Here."><c:forEach items="${currentQuizAnswer.userAnswers}" var="answer" varStatus="answerNo"><c:if test="${answerNo==1}">${answer.optionText}</c:if></c:forEach></textarea>
						</div>
						<a class="btn btn-info pull-right" data-toggle="collapse"
							data-target="#idealAnswer">Check</a>
						<div class="clear"></div>
						<div id="idealAnswer" class="collapse pull-left">
							<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
							<c:if test="${option.orderNo eq 2}">
							<label class="option-text h3">${option.optionText}</label>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 4}">
		<div class="jumbotron">
			<div class="container-fluid text-center image-mcq activity-content">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<img src="${option.optionText}" style="width:100%; height:100%;">
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row mcqOptions form-group image-options">
							<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
							<c:if test="${option.orderNo ne 1}">
							<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
							   	<span style="white-space: nowrap;"> 
							   		<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}" value="selectedAnswer_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>
									<label class="option-text h3">${answer.optionText}</label>
								</span>
							</div>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 5}">
		<div class="jumbotron">
			<div class="container-fluid text-center image-yesno activity-content">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<img src="${option.optionText}" style="width:100%; height:100%;">
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row form-group image-options">
							<div class="col-md-2">
							</div>
							<c:forEach items="${currentQuizAnswer.userAnswers}" var="answer">
							<div class="col-md-4 form-group">
								<label class="btn btn-block ${answer.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
									<input style="display:none;" class="optionInput" type="radio" 
									name="yesno-option" id="inlineRadio1" value="yesno_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>${answer.optionText}
								</label>
							</div>
							</c:forEach>
							<div class="col-md-2">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 6}">
		<div class="jumbotron">
			<div cla ss="container-fluid text-center video-desc activity-content">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<video width="600" controls>
							<source src="${option.optionText}" type="video/mp4">
							<source src="${option.optionText}" type="video/ogg">
							<ins>Your browser does not support the video tag.</ins>
						</video>
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="form-group image-options">
							<textarea class="form-control" rows="5" name="userAnswer"
								placeholder="Enter Answer Here." required><c:forEach items="${currentQuizAnswer.userAnswers}" var="answer" varStatus="answerNo"><c:if test="${answerNo==1}">${answer.optionText}</c:if></c:forEach></textarea>
						</div>
						<a class="btn btn-info pull-right" data-toggle="collapse"
							data-target="#idealAnswer">Check</a>
						<div class="clear"></div>
						<div id="idealAnswer" class="collapse pull-left">
							<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
							<c:if test="${option.orderNo eq 2}">
							<label class="option-text h3">${option.optionText}</label>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 7}">
		<div class="jumbotron">
			<div class="container-fluid text-center video-mcq activity-content">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<video width="600" controls>
							<source src="${option.optionText}" type="video/mp4">
							<source src="${option.optionText}" type="video/ogg">
							<ins>Your browser does not support the video tag.</ins>
						</video>
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row mcqOptions form-group image-options">
							<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
							<c:if test="${option.orderNo ne 1}">
							<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
							   	<span style="white-space: nowrap;"> 
									<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}" value="selectedAnswer_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>
									<label class="option-text h3">${answer.optionText}</label>
								</span>
							</div>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 8}">
		<div class="jumbotron">
			<div class="container-fluid text-center video-yesno activity-content">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<video width="600" controls>
							<source src="${option.optionText}" type="video/mp4">
							<source src="${option.optionText}" type="video/ogg">
							<ins>Your browser does not support the video tag.</ins>
						</video>
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row form-group image-options">
							<div class="col-md-2">
							</div>
							<c:forEach items="${currentQuizAnswer.userAnswers}" var="answer">
							<div class="col-md-4 form-group">
								<label class="btn btn-block ${answer.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
									<input style="display:none;" class="optionInput" type="radio" 
									name="yesno-option" id="inlineRadio1" value="yesno_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>${answer.optionText}
								</label>
							</div>
							</c:forEach>
							<div class="col-md-2">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<div class="jumbotron">
			<div class="container-fluid text-center">
				<div class="row">
					<div class="col-sm-4 pull-left text-left">
<%-- 						<c:if test="${not empty instanceTopic.prevModules and empty instanceModule.prevActivity}"> --%>
<!-- 						<a class="btn btn-primary pull-left btn-nav" id="prev-module">Previous</a> -->
<%-- 						</c:if> --%>
						<c:if test="${currentQuizAnswer.quizOption.orderNo ne 1}">
						<a class="btn btn-primary pull-left btn-nav" id="prev-quiz">Previous</a>
						</c:if>
					</div>
					<div class="col-sm-4 pull-right text-right">
<%-- 						<c:if test="${not empty instanceTopic.nextModules and empty instanceModule.nextActivity}"> --%>
<!-- 						<a class="btn btn-primary pull-left" data-toggle="modal" -->
<!-- 							data-target="#nextModuleModal">Next</a> -->
<%-- 						</c:if> --%>
						<c:if test="${(fn:length(quiz.quiz.quizOptions) ne currentQuizAnswer.quizOption.orderNo)}">
						<a class="btn btn-primary pull-left btn-nav" id="next-quiz">Next</a>
						</c:if>
						<c:if test="${(fn:length(quiz.quiz.quizOptions) eq currentQuizAnswer.quizOption.orderNo)}">
						<a class="btn btn-primary pull-left btn-nav btn-finished" id="quiz-finish">Finish</a>
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
</body>
</html>