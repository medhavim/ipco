<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mangage Tutorial</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/manageTutorial.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/manageTutorial.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="jumbotron title">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
		  	<li class="breadcrumb-item active"><span class="h3">Manage Quiz</span></li>
		</ol>
		<div class="container text-center">
			<h1>Manage Quiz</h1>
			<p>Add-Remove-Edit Quiz questions here.</p>
		</div>
	</div>

	<div class="container-fluid text-left">
		<div class="row">
			<div class="col-sm-12">
				<c:if test="${not (fn:length(allTopics)>0)}">
				<div class="jumbotron topic_holder">
					<br>
					<div class="row">
						<div class="col-sm-12">
						<span class="h2">No topics available. You might want to add them first.</span>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${fn:length(allTopics)>0}">
				<c:forEach items="${allTopics}" var="topic" varStatus="topicNo">
					<div class="jumbotron topic_holder">
						<div class="row">
							<div class="col-sm-6">
							<span class="container_name h2 span-topic-${topic.topicId}" 
								data-toggle="collapse" data-target="#quizQuestions_for-${topic.topicId}" id="topic_name_${topic.topicId}">${topic.topicName}</span>
							<span class="h3">${topic.topicType.typeId == 1?'(Basic)':''}</span>
							</div>
							<div class="col-sm-1 pull-right">
							<button class="btn btn-info btn-block" data-toggle="collapse" data-target="#viewTopicDesc_${topic.topicId}">View Description</button>
							</div>
						</div>
						
						<div class="panel-collapse collapse" id="viewTopicDesc_${topic.topicId}">
							<div class="container-fluid topicDescContainer_${topic.topicId}">
								<div id="topicDescContent_${topic.topicId}" class="desc-body">${topic.topicDesc}</div>
							</div>
							<hr>
						</div>
					
						<div class="panel-collapse collapse in" id="quizQuestions_for-${topic.topicId}">
							<div class="jumbotron module_holder">
								<div class="row">
									<div class="col-sm-4">
									<a class="btn btn-info btn-block" id="new_quizQuestion_under-${topic.topicId}" onclick="addQuiz(this)">Add Quiz Question</a>
									</div>
								</div>
								<c:if test="${not (fn:length(topic.quizQuestions)>0)}">
								<br>
								<div class="row">
									<div class="col-sm-12">
									<span class="h2">No Quiz Question added yet.</span>
									</div>
								</div>
								</c:if>
							</div>
							<c:if test="${fn:length(topic.quizQuestions)>0}">
							<c:forEach items="${topic.quizQuestions}" var="quiz">
							<div class="jumbotron diagnostic_holder">
								<div class="row">
									<div class="col-sm-8">
									<span class="container_name h3 collapsed span-diagnostic-${quiz.quizId}" 
										data-toggle="collapse" data-target="#topics_for-${quiz.quizId}" id="diagnosticName_${quiz.quizId}">${quiz.activity.activityTitle}</span>
									</div>
									<div class="col-sm-4">
									<button class="btn btn-warning" id="editQuiz_${quiz.quizId}" name="${quiz.activity.activityTitle}" onclick="editQuiz(this)"><i class="glyphicon glyphicon-edit">&nbsp;Edit</i></button>
									<a class="btn btn-danger" id="deleteId_${quiz.quizId}" role="button" onclick="deleteQuiz(this)"><i class="glyphicon glyphicon-trash">&nbsp;Remove</i></a>
									</div>
								</div>
						
								<div class="panel-collapse collapse" id="topics_for-${quiz.quizId}">
									<div class="jumbotron activity_holder">
										<div class="container-fluid">
										<div class="alert alert-info diag-qstn" role="alert">
											<div class="row">
												<div class="col-sm-8">${quiz.activity.activityText}</div>
												<c:forEach items="${quiz.correctAnswers}" var="option">
												<div class="col-sm-2">
												<a class="btn ${option.isCorrect=='true'?'btn-success':'btn-default'} btn-block" role="button" >${option.optionText}</a>
												</div>
												</c:forEach>
											</div>
										</div>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
							</c:if>
						</div>
					</div>
				</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
	<!-- Edit Activity form Start -->
	<form name="editForm" id="editForm" action="#" method="post">
		<input type="hidden" id="id" name="id" value="" />
	</form>
	<!-- Edit Activity form End -->
</body>
</html>