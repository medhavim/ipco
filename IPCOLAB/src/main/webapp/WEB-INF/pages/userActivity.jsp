<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Topics</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/userActivity.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/userTopic.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="jumbotron">
	<div class="nav-bar">
		<div class="btn-group btn-group-justified" role="group" >
			<c:forEach items="${instanceTopic.instanceModuleList}" var="instModule">
			<div class="btn-group" role="group">
		    	<button type="button" 
		    	class="btn <c:if test="${instModule.status.statusId eq 1}">btn-default</c:if><c:if test="${instModule.status.statusId eq 2}">btn-info</c:if><c:if test="${instModule.status.statusId eq 3}">btn-success</c:if> <c:if test="${instModule.instanceModuleId eq instanceTopic.currModule.instanceModuleId}">current-complete-module</c:if>"
		    	<c:if test="${instModule.status.statusId eq 1}">disabled</c:if>>${instModule.module.moduleName}</button>
		  	</div>
			</c:forEach>
		</div>
	</div>
	<div class="clear"></div>
	<div class="jumbotron">
		<div class="nav-bar">
			<div class="btn-group btn-group-justified" role="group" >
				<c:forEach items="${instanceModule.activityAnswerList}" var="activityAnswer">
				<div class="btn-group" role="group">
			    	<button type="button" 
	    			class="btn <c:if test="${instanceModule.status.statusId eq 2}">btn-info</c:if><c:if test="${instanceModule.status.statusId eq 3}">btn-success</c:if> <c:if test="${activityAnswer.activityAnswerId eq instanceModule.currActivity.activityAnswerId}">current-complete-module</c:if>"
			    	<c:if test="${activityAnswer.status.statusId eq 1}">disabled</c:if>>${activityAnswer.activity.activityTitle}</button>
			  	</div>
				</c:forEach>
			</div>
		</div>
		<div class="jumbotron">
			<div class="container-fluid text-center">
			<h2>${instanceModule.currActivity.activityOption.activity.activityText}</h2>
			</div>
		</div>
		<form action="saveActivity.action">
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 1}">
		<div class="jumbotron">
			<div class="container-fluid text-center">
				<div class="row mcqOptions form-group">
					<c:forEach items="${instanceModule.currActivity.answers}" var="answer">
					<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
					   	<span style="white-space: nowrap;"> 
						   	<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}">
							<label class="option-text h3">${answer.optionText}</label>
						</span>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 2}">
		<div class="jumbotron">
			<div class="container-fluid text-center">
				<div class="row form-group">
					<div class="col-md-2">
					</div>
					<div class="col-md-4 form-group">
						<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
							<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio1" value="Yes"> Yes
						</label>
					</div>
					<div class="col-md-4 form-group">
						<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
							<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio2" value="No"> No
						</label>
					</div>
					<div class="col-md-4">
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 3}">
		<div class="jumbotron">
			<div cla ss="container-fluid text-center image-desc">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<img src="${option.optionText}" style="width:100%; height:100%;">
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="form-group image-options">
							<textarea class="form-control" rows="5"
								placeholder="Enter Answer Here." required></textarea>
						</div>
						<a class="btn btn-info pull-right" data-toggle="collapse"
							data-target="#idealAnswer">Check</a>
						<div class="clear"></div>
						<div id="idealAnswer" class="collapse pull-left">
							<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
							<c:if test="${option.orderNo eq 2}">
							<label class="option-text h3">${option.optionText}</label>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 4}">
		<div class="jumbotron">
			<div class="container-fluid text-center image-mcq">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<img src="${option.optionText}" style="width:100%; height:100%;">
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row mcqOptions form-group image-options">
							<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
							<c:if test="${option.orderNo ne 1}">
							<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
							   	<span style="white-space: nowrap;"> 
								   	<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}">
									<label class="option-text h3">${answer.optionText}</label>
								</span>
							</div>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 5}">
		<div class="jumbotron">
			<div class="container-fluid text-center image-mcq">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<img src="${option.optionText}" style="width:100%; height:100%;">
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row form-group image-options">
							<div class="col-md-2">
							</div>
							<div class="col-md-4 form-group">
								<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
									<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio1" value="Yes"> Yes
								</label>
							</div>
							<div class="col-md-4 form-group">
								<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
									<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio2" value="No"> No
								</label>
							</div>
							<div class="col-md-2">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 6}">
		<div class="jumbotron">
			<div cla ss="container-fluid text-center video-desc">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<video width="600" controls>
							<source src="${option.optionText}" type="video/mp4">
							<source src="${option.optionText}" type="video/ogg">
							<ins>Your browser does not support the video tag.</ins>
						</video>
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="form-group image-options">
							<textarea class="form-control" rows="5"
								placeholder="Enter Answer Here." required></textarea>
						</div>
						<a class="btn btn-info pull-right" data-toggle="collapse"
							data-target="#idealAnswer">Check</a>
						<div class="clear"></div>
						<div id="idealAnswer" class="collapse pull-left">
							<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
							<c:if test="${option.orderNo eq 2}">
							<label class="option-text h3">${option.optionText}</label>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 7}">
		<div class="jumbotron">
			<div class="container-fluid text-center video-mcq">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<video width="600" controls>
							<source src="${option.optionText}" type="video/mp4">
							<source src="${option.optionText}" type="video/ogg">
							<ins>Your browser does not support the video tag.</ins>
						</video>
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row mcqOptions form-group image-options">
							<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
							<c:if test="${option.orderNo ne 1}">
							<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
							   	<span style="white-space: nowrap;"> 
								   	<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}">
									<label class="option-text h3">${answer.optionText}</label>
								</span>
							</div>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 8}">
		<div class="jumbotron">
			<div class="container-fluid text-center video-yesno">
				<div class="row form-group">
					<div class="col-sm-6">
						<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
						<c:if test="${option.orderNo eq 1}">
						<video width="600" controls>
							<source src="${option.optionText}" type="video/mp4">
							<source src="${option.optionText}" type="video/ogg">
							<ins>Your browser does not support the video tag.</ins>
						</video>
						</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-6">
						<div class="row form-group image-options">
							<div class="col-md-2">
							</div>
							<div class="col-md-4 form-group">
								<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
									<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio1" value="Yes"> Yes
								</label>
							</div>
							<div class="col-md-4 form-group">
								<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
									<input style="display:none;" class="optionInput" type="radio" name="yesno-option" id="inlineRadio2" value="No"> No
								</label>
							</div>
							<div class="col-md-2">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<div class="jumbotron">
			<div class="container-fluid text-center">
				<div class="row">
					<div class="col-sm-4">
						<button class="btn btn-primary pull-left btn-prev">Prev</button>
					</div>
					<div class="col-sm-4 col-sm-offset-4">
						<button class="btn btn-primary pull-right btn-next">Next</button>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>
</body>
</html>