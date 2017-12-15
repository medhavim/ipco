<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    
	<title>Activity Page</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
	<style type="text/css">
		<%@include file="../css/header.css" %>
		<%@include file="../css/adminActivity.css" %>
	</style>
	<script type="text/javascript">
		<%@include file="../js/adminActivity.js" %>
		<%@include file="../js/progress.js" %>
	</script>
	<script type="text/javascript">
		setInterval("checkLoad()",1000);
	</script>
</head>
<body>
	<div id="preLoaderDiv"></div>
<jsp:include page="header.jsp"></jsp:include>
<!-- <div class="jumbotron title admin">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
		<li class="breadcrumb-item"><a href="manageTutorial.action">Manage Tutorial</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Manage Activity</span></li>
	</ol>
	<div class="container text-center">
		<h1>Manage Activity</h1>
		<p>Add-Remove-Edit Activities, at one place.</p>
	</div>
</div> -->
<div class="jumbotron content">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
		<li class="breadcrumb-item"><a href="manageTutorial.action">Manage Tutorial</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Manage Activity</span></li>
	</ol>
	<div class="container text-center">
		<span class="h2">Manage Activity</span>
		<p>Add-Remove-Edit Activities, at one place.</p>
	</div>
	<div class="jumbotron">
		<div class="container-fluid text-left">
			<span class="h2">Step 1:</span>
			<span class="h2">Choose a Template</span>
		</div>
		<div class="container-fluid text-left desc-body">
			<ul class="nav nav-pills nav-justified actTemplate">
				<li role="presentation" class="active"><a class="smpleTmpltOptn" id="#info" href="#">Information</a></li>
				<li role="presentation"><a class="smpleTmpltOptn" id="#mcq" href="#">Multiple Choice</a></li>
				<li role="presentation"><a class="smpleTmpltOptn" id="#mca" href="#">Multiple Answer</a></li>
				<li role="presentation"><a class="smpleTmpltOptn" id="#yesno" href="#">Yes or No</a></li>
				<li role="presentation" class="dropdown btn-group"><a class="dropdown-toggle" data-toggle="dropdown" role="button" href="#">Image <span class="caret"></span></a>
					<ul class="dropdown-menu nav-dropdown-menu">
						<li role="presentation"><a class="compTmpltOptn" id="#image-mcq" href="#">Multiple Choice</a></li>
						<li role="presentation"><a class="compTmpltOptn" id="#image-yesno"  href="#">Yes or No</a></li>
						<li role="presentation"><a class="compTmpltOptn" id="#image-desc"  href="#">Description</a></li>
					</ul>
				</li>
				<li role="presentation" class="dropdown btn-group"><a class="dropdown-toggle" data-toggle="dropdown" role="button" href="#">Video <span class="caret"></span></a>
					<ul class="dropdown-menu nav-dropdown-menu">
						<li role="presentation"><a class="compTmpltOptn" id="#video-mcq" href="#">Multiple Choice</a></li>
						<li role="presentation"><a class="compTmpltOptn" id="#video-yesno" href="#">Yes or No</a></li>
						<li role="presentation"><a class="compTmpltOptn" id="#video-desc" href="#">Description</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<form:form action="addActivity.action" method="post" name="mcqForm" id="mcqForm" modelAttribute="activityOption" enctype="multipart/form-data">
	<div class="jumbotron">
		<div class="container-fluid text-left">
			<span class="h2">Step 2:</span>
			<span class="h2">Activity Title</span>
		</div>
		<div class="container-fluid text-left desc-body">
			<form:input type="text" id="activityTitle" name="activityTitle" path="activity.activityTitle" class="form-control" placeholder="Enter Activity title Here." />
		</div>	
	</div>
	<div class="jumbotron">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Activity Description</span>
		</div>
		<div class="container-fluid text-left desc-body">
			<form:textarea id="activityText" name="Question" path="activity.activityText" class="form-control" rows="5" placeholder="Enter Question Contents Here." ></form:textarea>
		</div>	
	</div>
	<div class="jumbotron">
		<div class="container-fluid text-left desc-body selected-tmplteOptn">
			<div class="info">
				<input type="hidden" name="activityTemplate" value="9"/>
				<input type="hidden" name="activityExplanation" value=NULL/>
			</div>
		</div>
		<div class="container-fluid text-left desc-body">
		  		<div class="col-sm-4 addActivityBtn">
		  			<button type="submit" class="btn btn-primary btn-lg btn-block">Add</button>
		  		</div>
		</div>	
	</div>
	<form:input type="hidden" path="module.moduleId" />
	</form:form>
	<div class="unselected-tmplteOptn">
		<div class="mcq">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateMCQ.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="1"/>
		</div>
		<div class="mca">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateMCA.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="10"/>
		</div>
		<div class="yesno">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateYESNO.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="2"/>
		</div>
		<div class="image-desc">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateIMAGE.jsp"></jsp:include>
			<jsp:include page="activityTemplateDESC.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="3"/>
		</div>
		<div class="image-mcq">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateIMAGE.jsp"></jsp:include>
			<jsp:include page="activityTemplateMCQ.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="4"/>
		</div>
		<div class="image-yesno">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateIMAGE.jsp"></jsp:include>
			<jsp:include page="activityTemplateYESNO.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="5"/>
		</div>
		<div class="video-desc">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateVIDEO.jsp"></jsp:include>
			<jsp:include page="activityTemplateDESC.jsp"></jsp:include>
			
			<input type="hidden" name="activityTemplate" value="6"/>
		</div>
		<div class="video-mcq">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateVIDEO.jsp"></jsp:include>
			<jsp:include page="activityTemplateMCQ.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="7"/>
		</div>
		<div class="video-yesno">
			<div class="container-fluid text-left">
				<span class="h2">Step 4:</span>
				<span class="h2">Options</span>
			</div>
			<jsp:include page="activityTemplateVIDEO.jsp"></jsp:include>
			<jsp:include page="activityTemplateYESNO.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="8"/>
		</div>
		<!-- <div class="container-fluid text-left form-group">
			<span class="h2">Step 5:</span>
			<span class="h2">Answer Summary / Explanation</span>
		</div>
		<div class="container-fluid text-left desc-body">
			<input type="text" name="activityExplanation" placeholder="Enter Activity Explanation / Summary Here." />
		</div> -->
	</div>
</div>
<input type="hidden" id="mcqMaxOptions" value="1" />
<input type="hidden" id="mcaMaxOptions" value="1" />
<div id="bottom"></div>
</body>
</html>








