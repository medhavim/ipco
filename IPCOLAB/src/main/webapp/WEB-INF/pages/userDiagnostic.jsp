<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Diagnostic Page</title>
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
<div class="jumbotron title">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
		<li class="breadcrumb-item"><a href="startTutorial.action">Choose Tutorial</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Customize Tutorial</span></li>
	</ol>
	<div class="container text-center">
		<h1>Customize Tutorial</h1>
		<p>Customize your tutorial by answering these simple questions.</p>
	</div>
</div>
<form action="customizeTutorial.action" method="post">
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 1:</span>
		<span class="h2">Give a name to this instance</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<input type="text" name="instanceName" class="form-control" placeholder="Enter instance name here." required/>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 2:</span>
		<span class="h2">Choose Category and answer simple questions</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<ul class="nav nav-tabs">
			<c:forEach items="${allCategories}" var="category" varStatus="categoryNo">
			<li ${categoryNo.count==1?'class="active"':''}>
        		<a  href="#questions-for_${category.categoryId}" data-toggle="tab">${category.categoryTitle}</a>
			</li>
			</c:forEach>
		</ul>
		<div class="tab-content clearfix">
			<c:forEach items="${allCategories}" var="category" varStatus="categoryNo">
			<div class="tab-pane ${categoryNo.count==1?'active':''}" id="questions-for_${category.categoryId}">
				<c:forEach items="${category.diagnosticQuestions}" var="diagnostic">
				<div class="container-fluid text-center desc-body">
					<h2>${diagnostic.activity.activityText}</h2>
				</div>
				<div class="container-fluid text-center">
					<div class="row form-group desc-body">
						<div class="col-md-2">
						</div>
						<div class="col-md-2 form-group">
							<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
								<input style="display:none;" class="optionInput" type="radio" name="diagnostic_${diagnostic.diagnosticId}" id="inlineRadio1" value="Yes"> Yes
							</label>
						</div>
						<div class="col-md-2">
						</div>
						<div class="col-md-2 form-group">
							<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
								<input style="display:none;" class="optionInput" type="radio" name="diagnostic_${diagnostic.diagnosticId}" id="inlineRadio2" value="No"> No
							</label>
						</div>
						<div class="col-md-2">
						</div>
					</div>
				</div>
				<hr>
				</c:forEach>
			</div>
			</c:forEach>
		</div>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left desc-body">
		<div class="row">
			<div class="col-sm-3">
	  			<span class="h2">Lastly: Click when done</span>
			</div>
	  		<div class="col-sm-4 addActivityBtn">
	  			<button type="submit" class="btn btn-primary btn-lg btn-block">Done</button>
	  		</div>
	  	</div>
	</div>	
</div>
</form>
</body>
</html>








