<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="description" content="IPCOLab">
    <meta name="author" content="NEU CCIS Dept">
    
    <link rel="icon" type="image/png" href="https://png.icons8.com/law/ultraviolet/16/000000" />
	<title>Manage Diagnostic</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
	<style type="text/css">
		<%@include file="../css/header.css" %>
		<%@include file="../css/manageDiagnostic.css" %>
	</style>
	<script type="text/javascript">
		<%@include file="../js/manageDiagnostic.js" %>
		<%@include file="../js/progress.js" %>
	</script>
	<script type="text/javascript">
		setInterval("checkLoad()",1000);
	</script>
</head>
<body>
<div id="preLoaderDiv"></div>
<jsp:include page="header.jsp"></jsp:include>
<div class="jumbotron content">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
	  	<li class="breadcrumb-item"><a href="manageDiagnostic.action">Manage Diagnostic</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Manage Related Diagnostic</span></li>
	</ol>
	<div class="container text-center">
		<span class="h2">Manage Related Diagnostic</span>
		<p>Add-Remove-Edit Related Diagnostic questions here.</p>
	</div>
	<div class="jumbotron">
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
							<div class="tile black">
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
									<span class="w3-tag w3-padding w3-round-large w3-black w3-center">${topic.topicName}</span>
					      			<%-- <div class="tile black">
					        			<h3 class="title">${topic.topicName}</h3>
					       		 		<p>${topic.topicDesc}</p>
					      			</div> --%>
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
	</div>
</div>
<!-- Edit Activity form Start -->
<form name="editForm" id="editForm" action="#" method="post">
	<input type="hidden" id="id" name="id" value="" />
</form>
<!-- Edit Activity form End -->

<jsp:include page="manageDiagnosticModal.jsp"></jsp:include>
<div id="bottom"></div>
</body>
</html>