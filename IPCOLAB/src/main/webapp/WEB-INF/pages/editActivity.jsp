<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="jumbotron title">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
		<li class="breadcrumb-item"><a href="manageTutorial.action">Manage Tutorial</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Manage Activity</span></li>
	</ol>
	<div class="container text-center">
		<h1>Manage Activity</h1>
		<p>Add-Remove-Edit Activities, at one place.</p>
	</div>
</div>
<!-- div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Template</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<ul class="nav nav-pills nav-justified actTemplate">
			<li role="presentation"><a class="smpleTmpltOptn" id="#mcq" href="#">Multiple Choice</a></li>
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
</div-->
<form:form action="editActivity.action" method="post" name="mcqForm" id="mcqForm" modelAttribute="activityOption" enctype="multipart/form-data">
<form:input type="hidden" path="activityOptionId"/>
<form:input type="hidden" path="orderNo"/>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 1:</span>
		<span class="h2">Activity Title Here</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<form:input type="text" id="activityTitle" name="activityTitle" path="activity.activityTitle" class="form-control" placeholder="Enter Activity title Here." />
	</div>
</div>
<div class="jumbotron">
	<div class="container-fluid text-left">
		<span class="h2">Step 2:</span>
		<span class="h2">Activity Description Text Here</span>
	</div>
	<div class="container-fluid text-left desc-body">
		<form:textarea id="activityText" name="Question" path="activity.activityText" class="form-control" rows="5" placeholder="Enter Question Contents Here."></form:textarea>
	</div>	
</div>
<div class="jumbotron">
	<div class="container-fluid text-left desc-body selected-tmplteOptn">
		<form:input type="hidden" path="activity.activityTemplate.activityTemplateId"/>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId == 1}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="mcq">
			<div class="row addOption form-group">
				<button id="mcqMoreOptions" type="button" class="btn btn-default btn_lg"><i class="glyphicon glyphicon-plus"></i></button>
				<span>Add Option</span>
			</div>
			<div class="row mcqOptions form-group">
				<c:forEach items="${activityOption.options}" var="option">
				<div class="col-sm-4 input-group  mcqOption form-group" id="mcqOption_${option.orderNo}">
					<span class="input-group-addon">
				   	<input type="checkbox" name="correctAnswer" class="chkbx" id="checkBox_${option.orderNo}" value="option_${option.orderNo}" ${option.isCorrect=="true"?"checked":""}>
					</span>
					<input type="text" class="form-control option" name="option_${option.orderNo}" id="option_${option.orderNo}" value="${option.optionText}" placeholder="Content for this choice" required>
				   	<span class="input-group-btn">
					<button class="btn btn-default removeOption" id="removeOption_${option.orderNo}" type="button"><i class="glyphicon glyphicon-trash"></i></button>
				   	</span>
				</div>
				</c:forEach>
			</div>
		</div>
		</c:if>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId eq 2}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="yesno">
			<div class="row form-group">
				<div class="col-md-2">
				</div>
				<div class="col-md-2 form-group">
					<c:forEach items="${activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 1}">
					<label class="btn btn-block ${option.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
						<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio1" value="Yes" ${option.isCorrect eq "true"?"checked":""}> Yes
					</label>
					</c:if>
					</c:forEach>
				</div>
				<div class="col-md-2">
				</div>
				<div class="col-md-2 form-group">
					<c:forEach items="${activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 2}">
					<label class="btn btn-block ${option.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
						<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio2" value="No" ${option.isCorrect eq "true"?"checked":""}> No
					</label>
					</c:if>
					</c:forEach>
				</div>
				<div class="col-md-2">
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId eq 3}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="image-desc">
			<div class="row">
				<div class="col-sm-6">
					<c:if test="${fn:length(activityOption.options) > 0}">
					<c:forEach var="option" items="${activityOption.options}"
						varStatus="loopCount">
					<c:if test="${option.orderNo eq 1}">
					<img src="${option.optionText}" class="img-responsive"
						alt="Image not present" width="600">
					</c:if>
					</c:forEach>
					</c:if>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
					<ul class="list-inline">
					  <li><label class="h3" for="imageUploadMsg">Image Link:</label></li>
					  <li><p id="imageUploadMsg">Browse from computer</p></li>
					  <li><span class="btn btn-warning btn-file2 btn-block">Browse<input type="file" id="imageFile" name="uploadFile" onchange="imageUploadMsg()" required></span></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="h3" for="comment">Answer Content:</label>
				<c:if test="${fn:length(activityOption.options) > 0}">
				<c:forEach var="option" items="${activityOption.options}"
					varStatus="loopCount">
				<c:if test="${option.orderNo eq 2}">
				<textarea class="form-control" rows="5" name="idealAnswer"
					placeholder="Enter Answer Here." required>${order.orderText}</textarea>
				</c:if>
				</c:forEach>
				</c:if>
			</div>
		</div>
		</c:if>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId eq 4}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="image-mcq">
			<div class="row">
				<div class="col-sm-6">
					<c:if test="${fn:length(activityOption.options) > 0}">
					<c:forEach var="option" items="${activityOption.options}"
						varStatus="loopCount">
					<c:if test="${option.orderNo eq 1}">
					<img src="${option.optionText}" class="img-responsive"
						alt="Image not present" width="600">
					</c:if>
					</c:forEach>
					</c:if>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
					<ul class="list-inline">
					  <li><label class="h3" for="imageUploadMsg">Image Link:</label></li>
					  <li><p id="imageUploadMsg">Browse from computer</p></li>
					  <li><span class="btn btn-warning btn-file2 btn-block">Browse<input type="file" id="imageFile" name="uploadFile" onchange="imageUploadMsg()" required></span></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="row addOption form-group">
				<button id="mcqMoreOptions" type="button" class="btn btn-default btn_lg"><i class="glyphicon glyphicon-plus"></i></button>
				<span>Add Option</span>
			</div>
			<div class="row mcqOptions form-group">
				<c:forEach items="${activityOption.options}" var="option">
				<div class="col-sm-4 input-group  mcqOption form-group" id="mcqOption_${option.orderNo}">
					<span class="input-group-addon">
				   	<input type="checkbox" name="correctAnswer" class="chkbx" id="checkBox_${option.orderNo}" value="option_${option.orderNo}" ${option.isCorrect=="true"?"checked":""}>
					</span>
					<input type="text" class="form-control option" name="option_${option.orderNo}" id="option_${option.orderNo}" value="${option.optionText}" placeholder="Content for this choice" required>
				   	<span class="input-group-btn">
					<button class="btn btn-default removeOption" id="removeOption_${option.orderNo}" type="button"><i class="glyphicon glyphicon-trash"></i></button>
				   	</span>
				</div>
				</c:forEach>
			</div>
		</div>
		</c:if>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId eq 5}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="image-yesno">
			<div class="row">
				<div class="col-sm-6">
					<c:if test="${fn:length(activityOption.options) > 0}">
					<c:forEach var="option" items="${activityOption.options}"
						varStatus="loopCount">
					<c:if test="${option.orderNo eq 1}">
					<img src="${option.optionText}" class="img-responsive"
						alt="Image not present" width="600">
					</c:if>
					</c:forEach>
					</c:if>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
					<ul class="list-inline">
					  <li><label class="h3" for="imageUploadMsg">Image Link:</label></li>
					  <li><p id="imageUploadMsg">Browse from computer</p></li>
					  <li><span class="btn btn-warning btn-file2 btn-block">Browse<input type="file" id="imageFile" name="uploadFile" onchange="imageUploadMsg()" required></span></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-2">
				</div>
				<div class="col-md-2 form-group">
					<c:forEach items="${activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 1}">
					<label class="btn btn-block ${option.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
						<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio1" value="Yes" ${option.isCorrect eq "true"?"checked":""}> Yes
					</label>
					</c:if>
					</c:forEach>
				</div>
				<div class="col-md-2">
				</div>
				<div class="col-md-2 form-group">
					<c:forEach items="${activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 2}">
					<label class="btn btn-block ${option.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
						<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio2" value="No" ${option.isCorrect eq "true"?"checked":""}> No
					</label>
					</c:if>
					</c:forEach>
				</div>
				<div class="col-md-2">
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId eq 6}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="video-desc">
			<div class="row">
				<div class="col-sm-6">
					<c:if test="${fn:length(activityOption.options) > 0}">
					<c:forEach var="option" items="${activityOption.options}"
						varStatus="loopCount">
					<c:if test="${option.orderNo eq 1}">
					<video width="600" controls>
						<source src="${option.optionText}" type="video/mp4">
						<source src="${option.optionText}" type="video/ogg">
						<ins>Your browser does not support the video tag.</ins>
					</video>
					</c:if>
					</c:forEach>
					</c:if>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
					<ul class="list-inline">
					  <li><label class="h3" for="videoUploadMsg">Video Link:</label></li>
					  <li><p id="videoUploadMsg">Browse from computer</p></li>
					  <li><span class="btn btn-warning btn-file2 btn-block">Browse<input type="file" id="videoFile" name="uploadFile" onchange="videoUploadMsg()" required></span></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="h3" for="comment">Answer Content:</label>
				<c:if test="${fn:length(options) > 0}">
				<c:forEach var="option" items="${activityOption.options}"
					varStatus="loopCount">
				<c:if test="${option.orderNo eq 2}">
				<textarea class="form-control" rows="5" name="idealAnswer"
					placeholder="Enter Answer Here." required>${order.orderText}</textarea>
				</c:if>
				</c:forEach>
				</c:if>
			</div>
		</div>
		</c:if>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId eq 7}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="video-mcq">
			<div class="row">
				<div class="col-sm-6">
					<c:if test="${fn:length(activityOption.options) > 0}">
					<c:forEach var="option" items="${activityOption.options}"
						varStatus="loopCount">
					<c:if test="${option.orderNo eq 1}">
					<video width="600" controls>
						<source src="${option.optionText}" type="video/mp4">
						<source src="${option.optionText}" type="video/ogg">
						<ins>Your browser does not support the video tag.</ins>
					</video>
					</c:if>
					</c:forEach>
					</c:if>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
					<ul class="list-inline">
					  <li><label class="h3" for="videoUploadMsg">Video Link:</label></li>
					  <li><p id="videoUploadMsg">Browse from computer</p></li>
					  <li><span class="btn btn-warning btn-file2 btn-block">Browse<input type="file" id="videoFile" name="uploadFile" onchange="videoUploadMsg()" required></span></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="row addOption form-group">
				<button id="mcqMoreOptions" type="button" class="btn btn-default btn_lg"><i class="glyphicon glyphicon-plus"></i></button>
				<span>Add Option</span>
			</div>
			<div class="row mcqOptions form-group">
				<c:forEach items="${activityOption.options}" var="option">
				<div class="col-sm-4 input-group  mcqOption form-group" id="mcqOption_${option.orderNo}">
					<span class="input-group-addon">
				   	<input type="checkbox" name="correctAnswer" class="chkbx" id="checkBox_${option.orderNo}" value="option_${option.orderNo}" ${option.isCorrect=="true"?"checked":""}>
					</span>
					<input type="text" class="form-control option" name="option_${option.orderNo}" id="option_${option.orderNo}" value="${option.optionText}" placeholder="Content for this choice" required>
				   	<span class="input-group-btn">
					<button class="btn btn-default removeOption" id="removeOption_${option.orderNo}" type="button"><i class="glyphicon glyphicon-trash"></i></button>
				   	</span>
				</div>
				</c:forEach>
			</div>
		</div>
		</c:if>
		<c:if test="${activityOption.activity.activityTemplate.activityTemplateId eq 8}">
		<div class="container-fluid text-left">
			<span class="h2">Step 3:</span>
			<span class="h2">Option Description Here</span>
		</div>
		<div class="clear form-group"></div>
		<div class="video-yesno">
			<div class="row">
				<div class="col-sm-6">
					<c:if test="${fn:length(activityOption.options) > 0}">
					<c:forEach var="option" items="${activityOption.options}"
						varStatus="loopCount">
					<c:if test="${option.orderNo eq 1}">
					<video width="600" controls>
						<source src="${option.optionText}" type="video/mp4">
						<source src="${option.optionText}" type="video/ogg">
						<ins>Your browser does not support the video tag.</ins>
					</video>
					</c:if>
					</c:forEach>
					</c:if>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
					<ul class="list-inline">
					  <li><label class="h3" for="videoUploadMsg">Video Link:</label></li>
					  <li><p id="videoUploadMsg">Browse from computer</p></li>
					  <li><span class="btn btn-warning btn-file2 btn-block">Browse<input type="file" id="videoFile" name="uploadFile" onchange="videoUploadMsg()" required></span></li>
					</ul>
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-2">
				</div>
				<div class="col-md-2 form-group">
					<c:forEach items="${activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 1}">
					<label class="btn btn-block ${option.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
						<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio1" value="Yes" ${option.isCorrect eq "true"?"checked":""}> Yes
					</label>
					</c:if>
					</c:forEach>
				</div>
				<div class="col-md-2">
				</div>
				<div class="col-md-2 form-group">
					<c:forEach items="${activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 2}">
					<label class="btn btn-block ${option.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
						<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio2" value="No" ${option.isCorrect eq "true"?"checked":""}> No
					</label>
					</c:if>
					</c:forEach>
				</div>
				<div class="col-md-2">
				</div>
			</div>
		</div>
		</c:if>
	</div>
	<div class="container-fluid text-left desc-body">
		<div class="row">
			<div class="col-sm-3">
	  			<span class="h2">Lastly: Click on Update</span>
			</div>
	  		<div class="col-sm-4 addActivityBtn">
	  			<button type="submit" class="btn btn-primary btn-lg btn-block">Update</button>
	  		</div>
	  	</div>
	</div>	
</div>
<form:input type="hidden" path="module.moduleId" />
<input type="hidden" id="mcqMaxOptions" name="mcqMax" value="${fn:length(activityOption.options)}" />
</form:form>
</body>
</html>








