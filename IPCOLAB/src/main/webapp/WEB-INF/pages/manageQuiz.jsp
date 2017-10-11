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
	<div class="jumbotron title admin">
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
								data-toggle="collapse" data-target="#quiz_for-${topic.topicId}" id="topic_name_${topic.topicId}">${topic.topicName}</span>
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
					
						<div class="panel-collapse collapse in" id="quiz_for-${topic.topicId}">
<!-- 							<div class="jumbotron module_holder"> -->
							<c:if test="${topic.quiz eq null or topic.quiz.quizName eq ''}">
							<br>
							<div class="row">
								<div class="col-sm-12">
								<span class="h2">No Quiz added yet.</span>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<a class="btn btn-info btn-block" data-toggle="modal"
										data-target="#addNewQuiz" onclick="addQuizForTopic(${topic.topicId})" role="button">Add Quiz</a>
								</div>
							</div>
							</c:if>
							<c:if test="${topic.quiz ne null}">
							<div class="jumbotron quiz_holder">
								<div class="row">
									<div class="col-sm-8">
									<span class="container_name h3 span-quiz-${topic.quiz.quizId}" 
										data-toggle="collapse" data-target="#quiz_options_for-${topic.quiz.quizId}" id="quizName_${topic.quiz.quizId}">${topic.quiz.quizName}</span>
									</div>
									<div class="col-sm-2">
									<button class="btn btn-warning btn-block" id="renameQuiz_${topic.quiz.quizId}" name="${topic.quiz.quizName}" onclick="renameQiuz(this)">Rename</button>
									</div>
									<div class="col-sm-2">
									<a class="btn btn-danger btn-block" id="deleteId_${topic.quiz.quizId}" role="button" onclick="deleteQuiz(this)">Delete</a>
									</div>
									<input type="hidden" id="quizNotEmpty_${topic.quiz.quizId}" value="${fn:length(topic.quiz.quizOptions)>0}" />
								</div>
								<div class="panel-collapse in collapse" id="quiz_options_for-${topic.quiz.quizId}">
									<div class="jumbotron activity_holder">
										<div class="row">
											<div class="col-sm-4">
											<form action="gotoAddQuiz.action" method="post">
												<input type="hidden" name="quizId" value="${topic.quiz.quizId}"/>
												<input type="submit" class="btn btn-info btn-block" role="button" value="Add Quiz Question"/>
											</form>
											</div>
											<c:if test="${not (fn:length(topic.quiz.quizOptions)>0)}">
											<br>
											<div class="row">
												<div class="col-sm-12">
												<span class="h2">No quiz questions available. You might want to add the first one.</span>
												</div>
											</div>
											</c:if>
										</div>
									</div>
									
									<c:if test="${fn:length(topic.quiz.quizOptions)>0}">
									<c:forEach items="${topic.quiz.quizOptions}" var="quizOption" varStatus="quizOptionNo">
									<div class="jumbotron activity_holder">
										<div class="row">
											<div class="col-sm-8">
											<span class="container_name h3 collapsed span-quizOption-${quizOption.quizOptionId}" 
												data-toggle="collapse" data-target="#activity_for-${quizOption.quizOptionId}" id="quizName_${quizOption.quizOptionId}">${quizOption.activity.activityTitle}</span>
											</div>
											<div class="col-sm-4">
											<button class="btn btn-warning" id="editQuiz_${quizOption.quizOptionId}" name="${quizOption.activity.activityTitle}" onclick="editQuizOption(id)"><i class="glyphicon glyphicon-edit">&nbsp;Edit</i></button>
											<a class="btn btn-danger" id="deleteId_${quizOption.quizOptionId}" role="button" onclick="deleteQuizOption(id)"><i class="glyphicon glyphicon-trash">&nbsp;Remove</i></a>
											</div>
										</div>
								
										<div class="panel-collapse collapse" id="activity_for-${quizOption.quizOptionId}">
											<c:if test="${quizOption.activity.activityTemplate.activityTemplateId eq 2}">
											<div class="jumbotron activity_holder">
												<div class="container-fluid">
												<div class="tile black alert alert-info diag-qstn" role="alert">
													<div class="row">
														<div class="col-sm-8">${quizOption.activity.activityText}</div>
														<c:forEach items="${quizOption.correctAnswers}" var="option">
														<div class="col-sm-2">
														<a class="btn ${option.isCorrect=='true'?'btn-success':'btn-default'} btn-block" role="button" >${option.optionText}</a>
														</div>
														</c:forEach>
													</div>
												</div>
												</div>
											</div>
											</c:if>
											<c:if test="${quizOption.activity.activityTemplate.activityTemplateId eq 1}">
											<div class="jumbotron activity_holder">
												<div class="container-fluid">
												<div class="tile black alert alert-info diag-qstn" role="alert">
													<div class="row">
														<div class="col-sm-8">${quizOption.activity.activityText}</div>
														<c:forEach items="${quizOption.correctAnswers}" var="option">
														<div class="col-sm-6  mcqOption " id="mcqOption_${option.optionId}">
														   	<span style="white-space: nowrap;"> 
															   	<input type="checkbox" name="selectedAnswer" disabled class="chkbx" id="checkBox_${option.optionId}" value="selectedAnswer_${option.optionId}" ${option.isCorrect=='true'?'checked':''}>
																<label class="option-text h3">${option.optionText}</label>
															</span>
														</div>
														</c:forEach>
													</div>
												</div>
												</div>
											</div>
											</c:if>
										</div>
									</div>
									</c:forEach>
									</c:if>
								</div>
							</div>
							</c:if>
<!-- 							</div> -->
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
	
	<!-- 			Adding new Quiz START -->
<div class="modal fade" id="addNewQuiz" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Add Quiz</h2>
			</div>
			<form action="addQuizName.action" method="post" role="form">
				<div class="modal-body form-group">
					<input type="text" class="form-control" id="quizName"
						name="quizName" placeholder="Enter new quiz name" required />
					<input type="hidden" name="quizForTopicId" id="quizForTopicId" />
				</div>
				<div class="modal-body">
				<div class="container-fluid text-left form-group desc-body">
					<textarea id="quizDesc" name="quizDesc" class="form-control" rows="5" placeholder="Enter Quiz Description here." ></textarea>
				</div>	
				</div>
				<div class="modal-footer">
				<div class="row">
					<div class="col-sm-5 pull-right form-group">
						<input type="submit" class="btn btn-success btn-block btn-lg" role="button"
						value="Add" />
					</div>
				</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 			Adding new Quiz END -->
<!-- 			Renaming the quiz pop up modal  START-->
<div class="modal fade" id="renameQiuz" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Rename Quiz</h2>
			</div>
			<div class="modal-body">
				<input type="text" class="form-control" id="renameQuizName"
					name="renameQuizName" placeholder="Enter new quiz name" /> <input
					type="hidden" name="renameQuizId" id="renameQuizId" />
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-sm-5 pull-right form-group">
						<input type="button" id="changeQuizName" class="btn btn-success btn-block"
						role="button" value="Change Name" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 			Renaming the quiz pop up modal  END-->

<!-- 			Confirmation dialog before delete START -->
<div class="modal fade" id="confirmationDialog" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Please confirm!</h2>
			</div>
			<form id="confirmationForm" name="confirmationForm" method="post">
				<div class="modal-body">
					<h3 class="modal-title">Do you really want to remove this?</h3>
					<input type="hidden" class="form-control" id="deletableId"  name="deletableId" />
				</div>
				<div class="modal-footer">
					<div class="row">
						<div class="col-sm-5 pull-left form-group">
							<a class="btn btn-success btn-block" data-dismiss="modal">No</a>
						</div>
						<div class="col-sm-5 pull-right form-group">
							<input type="submit" class="btn btn-default btn-block" role="button" value="Yes"/>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<input type="hidden" name="activityModuleId" id="activityModuleId" value="${activityModuleId}" />
<input type="hidden" name="moduleTopicId" id="moduleTopicId" value="${moduleTopicId}" />
<!-- 			Confirmation dialog before delete END -->

<!-- 		Cannot delete warning START -->
<div class="modal fade" id="warningDialog" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Warning!!!</h2>
			</div>
			<div class="modal-body">
				<h3 class="modal-title">The container is not empty. Do you want to go ahead?</h3>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-sm-5 pull-left form-group">
						<a class="btn btn-success btn-block" data-dismiss="modal">No</a>
					</div>
					<div class="col-sm-5 pull-right form-group">
						<input type="button" class="btn btn-danger btn-block" data-toggle="modal"
										data-target="#confirmationDialog" role="button" data-dismiss="modal" value="Yes"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 		Cannot delete warning  END -->
</body>
</html>