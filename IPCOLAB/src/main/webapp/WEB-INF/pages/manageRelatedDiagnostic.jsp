<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Manage Diagnostic</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/manageDiagnostic.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/manageDiagnostic.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="jumbotron title admin">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
		  	<li class="breadcrumb-item"><a href="manageDiagnostic.action">Manage Diagnostic</a></li>
		  	<li class="breadcrumb-item active"><span class="h3">Manage Related Diagnostic</span></li>
		</ol>
		<div class="container text-center">
			<h1>Manage Related Diagnostic</h1>
			<p>Add-Remove-Edit Related Diagnostic questions here.</p>
		</div>
	</div>

	<div class="container-fluid text-left">
		<div class="row">
			<div class="col-sm-12">
				<c:if test="${not (fn:length(relatedDiagnostics)>0)}">
				<div class="jumbotron category_holder">
					<br>
					<div class="row">
						<div class="col-sm-12">
						<span class="h2"><i class="glyphicon glyphicon-warning-sign"></i>There are currently no related questions.</span>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${fn:length(relatedDiagnostics)>0}">
				<c:forEach items="${relatedDiagnostics}" var="relatedDiagnostic">
					<div class="jumbotron category_holder">
						<div class="row desc-body">
							<div class="col-sm-6">
							<span class="container_name h2 span-category-${relatedDiagnostic.relatedDiagnosticId}" 
								data-toggle="collapse" data-target="#relatedDiagnostics_for-${relatedDiagnostic.relatedDiagnosticId}" id="relatedDiagnostic_name_${relatedDiagnostic.relatedDiagnosticId}">${relatedDiagnostic.relatedDiagnosticTitle}</span>
							</div>
							<div class="col-sm-4 pull-right text-right">
								<button class="btn btn-warning" id="editRelatedDiagnostic_${relatedDiagnostic.relatedDiagnosticId}"  onclick="editRelatedDiagnostic(id)"><i class="glyphicon glyphicon-edit">&nbsp;Edit</i></button>
								<a class="btn btn-danger" id="deleteId_${relatedDiagnostic.relatedDiagnosticId}" role="button" onclick="deleteRelatedDiagnostic(this)"><i class="glyphicon glyphicon-trash">&nbsp;Remove</i></a>
							</div>
						</div>
						<div class="panel-collapse collapse in" id="relatedDiagnostics_for-${relatedDiagnostic.relatedDiagnosticId}">
						<div class="container-fluid">
						<c:forEach items="${relatedDiagnostic.diagnostics}" var="diagnostic">
						<div class="tile gray">
							<input type="button" class="form-control btn btn-info  title" data-toggle="collapse" data-target="#selectedDiagnostic_info_for-${relatedDiagnostic.relatedDiagnosticId}_${diagnostic.diagnosticId}" value="${diagnostic.activity.activityTitle}"/ >
							<div class="panel-collapse collapse in" id="selectedDiagnostic_info_for-${relatedDiagnostic.relatedDiagnosticId}_${diagnostic.diagnosticId}">
								<div class="alert alert-info diag-qstn_${diagnostic.diagnosticId}" role="alert">
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
						<div class="row">
							<c:if test="${fn:length(relatedDiagnostic.topics)>0}">
							<c:forEach items="${relatedDiagnostic.topics}" var="topic" varStatus="topicNo">
							<div class="col-sm-4">
				      			<div class="tile gray">
				        			<h3 class="title">${topic.topicName}</h3>
				       		 		<p>${topic.topicDesc}</p>
				      			</div>
				   	 		</div>
							</c:forEach>
							</c:if>
						</div>
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
	
	<jsp:include page="manageDiagnosticModal.jsp"></jsp:include>
</body>
</html>