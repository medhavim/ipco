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
		  	<li class="breadcrumb-item active"><span class="h3">Manage Tutorial</span></li>
		</ol>
		<div class="container text-center">
			<h1>Manage Tutorial</h1>
			<p>Add-Remove-Edit Topics, Modules and Activities, all at one place.</p>
		</div>
	</div>

	<div class="container-fluid text-left">
		<div class="row">
			<div class="col-sm-12">
				<div class="jumbotron topic_holder">
					<div class="row">
						<div class="col-sm-4">
						<a class="btn btn-info btn-block" data-toggle="modal"
							data-target="#addNewTopic" role="button">Add Topic</a>
						</div>
					</div>
					<c:if test="${not (fn:length(allTopics)>0)}">
					<br>
					<div class="row">
						<div class="col-sm-12">
						<span class="h2">No topics available. You might want to add the first one.</span>
						</div>
					</div>
					</c:if>
				</div>
				<c:if test="${fn:length(allTopics)>0}">
				<c:forEach items="${allTopics}" var="topic" varStatus="topicNo">
					<div class="jumbotron topic_holder">
						<div class="row">
							<div class="col-sm-6">
							<span class="container_name h2 collapsed span-topic-${topic.topicId}" 
								data-toggle="collapse" data-target="#modules_for-${topic.topicId}" id="topic_name_${topic.topicId}">${topic.topicName}</span>
							<span class="h3">${topic.topicType.typeId == 1?'(Basic)':''}</span>
							</div>
							<div class="col-sm-2">
							<button class="btn btn-info btn-block" data-toggle="collapse" data-target="#viewTopicDesc_${topic.topicId}">View Description</button>
							</div>
							<div class="col-sm-2">
							<button class="btn btn-warning btn-block" id="${topic.topicId}" name="${topic.topicName}" onclick="renameTopic(this)">Rename</button>
							</div>
							<div class="col-sm-2">
							<a class="btn btn-danger btn-block" id="deleteId_${topic.topicId}" role="button" onclick="deleteTopic(this)">Delete</a>
							</div>
							<input type="hidden" id="topicNotEmpty_${topic.topicId}" value="${fn:length(topic.modules)>0}" />
						</div>
						
						<div class="panel-collapse collapse" id="viewTopicDesc_${topic.topicId}">
							<div class="container-fluid topicDescContainer_${topic.topicId}">
								<div id="topicDescContent_${topic.topicId}" class="desc-body">${topic.topicDesc}</div>
								<div class="row"><div class="col-sm-2 pull-right">
									<button class="btn btn-warning btn-block form-control" onclick="editTopicDesc(${topic.topicId})">Edit</button>
								</div></div>
							</div>
							<div class="container-fluid form-group topicDescEditor_${topic.topicId} desc-body" style="display: none">
								<textarea id="topicDescEdit_${topic.topicId}" name="topicDesc" rows="5" placeholder="Enter Topic Description here." >${topic.topicDesc}</textarea>
								<div class="row">
								<div class="col-sm-2 pull-right desc-body">
									<button class="btn btn-success btn-block" onclick="updateTopicDesc(${topic.topicId})">Update</button>
								</div>
								</div>
							</div><hr>
						</div>
					
						<div class="panel-collapse collapse" id="modules_for-${topic.topicId}">
							<div class="jumbotron module_holder">
								<div class="row">
									<div class="col-sm-4">
									<a class="btn btn-info btn-block" data-toggle="modal"
										data-target="#addNewModule" role="button" id="new_module_under-${topic.topicId}" onclick="addModule(this)">Add Module</a>
									</div>
								</div>
								<c:if test="${not (fn:length(topic.modules)>0)}">
								<br>
								<div class="row">
									<div class="col-sm-12">
									<span class="h2">No modules available. You might want to add the first one.</span>
									</div>
								</div>
								</c:if>
							</div>
							<c:if test="${fn:length(topic.modules)>0}">
							<c:forEach items="${topic.modules}" var="module" varStatus="moduleNo">
							<div class="jumbotron module_holder">
								<div class="row">
									<div class="col-sm-8">
									<span class="container_name h3 collapsed span-module-${module.moduleId}" 
										data-toggle="collapse" data-target="#activities_for-${module.moduleId}" id="moduleName_${module.moduleId}">${module.moduleName}</span>
									</div>
									<div class="col-sm-2">
									<button class="btn btn-warning btn-block" id="renameModule_${module.moduleId}" name="${module.moduleName}" onclick="renameModule(this)">Rename</button>
									</div>
									<div class="col-sm-2">
									<a class="btn btn-danger btn-block" id="deleteId_${module.moduleId}" role="button" onclick="deleteModule(this)">Delete</a>
									</div>
									<input type="hidden" id="moduleNotEmpty_${module.moduleId}" value="${fn:length(module.activityOptions)>0}" />
								</div>
						
								<div class="panel-collapse collapse" id="activities_for-${module.moduleId}">
									<div class="jumbotron activity_holder">
										<div class="row">
											<div class="col-sm-4">
											<form action="gotoAddActivity.action" method="post">
												<input type="hidden" name="moduleId" value="${module.moduleId}"/>
												<input type="submit" class="btn btn-info btn-block" role="button" value="Add Activity"/>
											</form>
											</div>
											<c:if test="${not (fn:length(module.activityOptions)>0)}">
											<br>
											<div class="row">
												<div class="col-sm-12">
												<span class="h2">No activities available. You might want to add the first one.</span>
												</div>
											</div>
											</c:if>
										</div>
									</div>
									<c:if test="${fn:length(module.activityOptions)>0}">
									<c:forEach items="${module.activityOptions}" var="activityOption" varStatus="activityOptionNo">
									<div class="jumbotron activity_holder">
										<div class="row">
											<div class="col-sm-8">
											<span class="h4" id="activityName_${activityOption.activityOptionId}">${activityOption.activity.activityTitle}</span>
											</div>
											<div class="col-sm-2">
											<a class="btn btn-warning btn-block" role="button" 
												id="activityId_${activityOption.activityOptionId}" onclick="editActivity(this)">Edit</a>
											</div>
											<div class="col-sm-2">
											<a class="btn btn-danger btn-block" data-toggle="modal" data-target="#confirmationDialog" id="deleteId_${activityOption.activityOptionId}" role="button" onclick="deleteActivity(this)">Delete</a>
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
				</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
	<jsp:include page="manageTutorialModal.jsp"></jsp:include>
</body>
</html>