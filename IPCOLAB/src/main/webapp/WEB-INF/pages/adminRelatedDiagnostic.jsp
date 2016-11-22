<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Related Diagnostic Page</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/adminDiagnostic.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/adminDiagnostic.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="jumbotron title admin">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
		<li class="breadcrumb-item"><a href="manageDiagnostic.action">Manage Diagnostic</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Add-Edit Related Diagnostic</span></li>
	</ol>
	<div class="container text-center">
		<h1>Add-Edit Related Diagnostic</h1>
		<p>Add-Remove-Edit Related Diagnostic Questions here.</p>
	</div>
</div>
<form action="addRelatedDiagnostic.action" method="post" onsubmit="return validateRelatedDiagnostic(this)">

<div class="jumbotron">
	<div class="container-fluid text-left">
	<div class="row">
		<div class="col-lg-12">
			<span class="errorMsg alert alert-danger h4 " role="alert" style="display: none"></span>
		</div>
	</div>
		<span class="h2">Step 1:</span>
		<span class="h2">Diagnostic Question Title</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<input type="text" id="relatedDiagnosticTitle" name="relatedDiagnosticTitle" class="form-control" placeholder="Enter Related Diagnostic question title Here." />
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 2:</span>
		<span class="h2">Select Related Questions</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<div class="row" style="overflow-y: auto; max-height:600px;">
			<c:forEach items="${allDiagnostics}" var="diagnostic">
	  		<div class="diagnostic_holder">
	  			<div class="row">
		  		<div class="col-sm-6 input-group">
			  		<input type="button" class="${diagnostic.diagnosticId==currentDiagnostic.diagnosticId?'removeDiagnosticLabel':'addDiagnosticLabel'} form-control btn ${diagnostic.diagnosticId==currentDiagnostic.diagnosticId?'btn-info':'btn-default'} btn-block" 
			  			id="diagnosticLabel_${diagnostic.diagnosticId}" value="${diagnostic.activity.activityTitle}"/>
				   	<span class="input-group-btn"><button type="button" class="${diagnostic.diagnosticId==currentDiagnostic.diagnosticId?'removeDiagnostic':'addDiagnostic'} btn ${diagnostic.diagnosticId==currentDiagnostic.diagnosticId?'btn-info':'btn-default'}" id="diagnosticBtn_${diagnostic.diagnosticId}" name="${diagnostic.activity.activityTitle}"><i class="glyphicon ${diagnostic.diagnosticId==currentDiagnostic.diagnosticId?'glyphicon-trash':'glyphicon-plus'}"></i></button></span>
			   	</div>
			   	</div>
		   		<div class="panel-collapse collapse" id="diagnostic_info_for-${diagnostic.diagnosticId}">
		   			<div class="tile gray alert ${diagnostic.diagnosticId==currentDiagnostic.diagnosticId?'alert-info':''} diag-qstn_${diagnostic.diagnosticId}" role="alert">
						<div class="row">
							<div class="col-sm-8">${diagnostic.activity.activityText}</div>
							<c:forEach items="${diagnostic.options}" var="option">
							<div class="col-sm-2">
							<a class="btn ${option.isCorrect=='true'?'btn-success':'btn-default'} btn-block" role="button" >${option.optionText}</a>
							</div>
							</c:forEach>
						</div>
					</div>
		   		</div>
 			</div>
			</c:forEach>
		</div>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 3:</span>
		<span class="h2">Verify Related Questions</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<div class="row selected-diagnostics" style="overflow-y: auto; max-height:600px;">
			<div class="tile gray" id="selectedDiagnostic_${currentDiagnostic.diagnosticId}">
				<input type="button" class="form-control btn btn-info  title" data-toggle="collapse" data-target="#selectedDiagnostic_info_for-${currentDiagnostic.diagnosticId}" value="${currentDiagnostic.activity.activityTitle}" />
				<div class="panel-collapse collapse in" id="selectedDiagnostic_info_for-${currentDiagnostic.diagnosticId}">
					<div class="alert alert-info diag-qstn_${currentDiagnostic.diagnosticId}" role="alert">
						<div class="row">
							<div class="col-sm-8">${currentDiagnostic.activity.activityText}</div>
							<c:forEach items="${currentDiagnostic.options}" var="option">
							<div class="col-sm-2">
							<a class="btn ${option.isCorrect=='true'?'btn-success':'btn-default'} btn-block" role="button" >${option.optionText}</a>
							</div>
							</c:forEach>
						</div>
					</div>
					<input type="hidden" name="diagnostic_${currentDiagnostic.diagnosticId}"/>
				</div>
			</div>
		</div>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 4:</span>
		<span class="h2">Associate Topics to the related questions</span>
	</div>
	<div class="container-fluid text-center desc-body" >
		<div class="row" style="overflow-y: auto; max-height:300px;">
			<c:forEach items="${allTopics}" var="topic">
		  	<div class="col-sm-6 input-group">
		  		<input type="button" class="addTopicLabel form-control btn btn-default btn-block" id="topicLabel_${topic.topicId}" value="${topic.topicName}"/>
			   	<span class="input-group-btn"><button type="button" class="addTopic btn btn-default" id="topicBtn_${topic.topicId}" name="${topic.topicName}"><i class="glyphicon glyphicon-plus"></i></button></span>
	 		</div>
			</c:forEach>
		</div>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 5:</span>
		<span class="h2">Verify Associated Topics</span>
	</div>
	<div class="container-fluid text-center desc-body" >
		<div class="row selected-topics" style="overflow-y: auto; max-height:200px;">
		</div>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left desc-body">
		<div class="row">
			<div class="col-sm-3">
	  			<span class="h2">Lastly: Click on Add</span>
			</div>
	  		<div class="col-sm-4 addActivityBtn">
	  			<button type="submit" class="btn btn-primary btn-lg btn-block">Add</button>
	  		</div>
	  	</div>
	</div>	
</div>
</form>
</body>
</html>








