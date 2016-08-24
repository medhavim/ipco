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
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
		<li class="breadcrumb-item"><a href="manageDiagnostic.action">Manage Diagnostic</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Add-Edit Diagnostic</span></li>
	</ol>
	<div class="container text-center">
		<h1>Add-Edit Diagnostic</h1>
		<p>Add-Remove-Edit Diagnostic Questions here.</p>
	</div>
</div>
<form:form action="addDiagnostic.action" method="post" modelAttribute="diagnostic" enctype="multipart/form-data">
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 1:</span>
		<span class="h2">Diagnostic Question Title</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<form:input type="text" id="activityTitle" name="activityTitle" path="activity.activityTitle" class="form-control" placeholder="Enter Diagnostic question title Here." />
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 2:</span>
		<span class="h2">Diagnostic Description Text</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<form:textarea id="activityText" name="Question" path="activity.activityText" class="form-control" rows="5" placeholder="Enter Diagnostic Question Content Here." ></form:textarea>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left desc-body selected-tmplteOptn">
		<div class="yesno">
			<div class="container-fluid text-left">
				<span class="h2">Step 3:</span>
				<span class="h2">Diagnostic Options</span>
			</div>
			<jsp:include page="activityTemplateYESNO.jsp"></jsp:include>
			<input type="hidden" name="activityTemplate" value="2"/>
		</div>
	</div>
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 4:</span>
		<span class="h2">Associate Topics to the question</span>
	</div>
	<div class="container-fluid text-center desc-body" >
		<div class="row" style="overflow-y: auto; height:200px;">
			<c:forEach items="${customTopics}" var="topic">
		  	<div class="col-sm-6 input-group">
		  		<input type="button" class="form-control btn btn-default btn-block" id="topicLabel_${topic.topicId}" value="${topic.topicName}"/>
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
		<div class="row selected-topics" style="overflow-y: auto; height:100px;">
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
<form:input type="hidden" path="category.categoryId" />
</form:form>
<div class="unselected-tmplteOptn">
	<div class="mcq">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateMCQ.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="1"/>
	</div>
	<div class="yesno">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateYESNO.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="2"/>
	</div>
	<div class="image-desc">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateIMAGE.jsp"></jsp:include>
		<jsp:include page="activityTemplateDESC.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="3"/>
	</div>
	<div class="image-mcq">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateIMAGE.jsp"></jsp:include>
		<jsp:include page="activityTemplateMCQ.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="4"/>
	</div>
	<div class="image-yesno">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateIMAGE.jsp"></jsp:include>
		<jsp:include page="activityTemplateYESNO.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="5"/>
	</div>
	<div class="video-desc">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateVIDEO.jsp"></jsp:include>
		<jsp:include page="activityTemplateDESC.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="6"/>
	</div>
	<div class="video-mcq">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateVIDEO.jsp"></jsp:include>
		<jsp:include page="activityTemplateMCQ.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="7"/>
	</div>
	<div class="video-yesno">
		<div class="container-fluid text-left">
			<span class="h2">Step 4:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<jsp:include page="activityTemplateVIDEO.jsp"></jsp:include>
		<jsp:include page="activityTemplateYESNO.jsp"></jsp:include>
		<input type="hidden" name="activityTemplate" value="8"/>
	</div>
</div>
<input type="hidden" id="mcqMaxOptions" value="1" />
</body>
</html>








