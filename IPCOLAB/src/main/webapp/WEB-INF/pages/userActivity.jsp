<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    
	<title>Topics</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
	<style type="text/css">
		<%@include file="../css/header.css" %>
		<%@include file="../css/userActivity.css" %>
	</style>
	<script type="text/javascript">
		<%@include file="../js/userActivity.js" %>
		<%@include file="../js/progress.js" %>
	</script>
	<script type="text/javascript">
		setInterval("checkLoad()",1000);
	</script>
</head>
<body>
<div id="preLoaderDiv"></div>
<jsp:include page="header.jsp"></jsp:include>
<%-- <div class="jumbotron title">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
		<li class="breadcrumb-item"><a href="loadInstance.action?id=${instance.instanceId}">${instance.instanceName}</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${instanceModule.module.moduleName}</span></li>
	</ol>
</div> --%>
<div class="jumbotron content user">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="userProfile.action">Profile</a></li>
		<li class="breadcrumb-item"><a href="loadInstance.action?id=${instance.instanceId}">${instance.instanceName}</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">${instanceModule.module.moduleName}</span></li>
	</ol>
<!-- <div class="container-fluid content"> -->
	<jsp:include page="helperPages/userActivityModuleNavLinks.jsp"></jsp:include>
	
	<!-- <div class="clear"></div> -->
	<div class="jumbotron activityTitle">
	<!-- <div class="container-fluid"> --> 
		<%-- <jsp:include page="helperPages/userActivityNavLinks.jsp"></jsp:include> --%>
		<p class="text-center">${instanceModule.currActivity.activityOption.activity.activityTitle}<p>
		<div class="container-fluid tile black title">
			<div class="container-fluid text-left activity-content">
			<p class="title">${instanceModule.currActivity.activityOption.activity.activityText}</p>
			</div>
		<form action="saveActivity.action" id="activityForm" method="post">
			<input type="hidden" name="navType" id="navType"/>
			<jsp:include page="helperPages/userActivityTemplateMCQ.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateMCA.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateYESNO.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateImageDesc.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateImageMCQ.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateImageYESNO.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateVideoDesc.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateVideoMCQ.jsp"></jsp:include>
			<jsp:include page="helperPages/userActivityTemplateVideoYESNO.jsp"></jsp:include>
			
<!-- 			TODO: be sure to test the navigation from quiz as well because you are changing the userActivity.js which is shared among userActivity.js and userQuiz.js -->
<!-- 		<div class="jumbotron"> -->
			
			<br/>
			<div class="container-fluid text-center">
				<div class="row">
					<div class="col-sm-4 pull-left text-left">
						<c:if test="${instanceModule.module.orderNo ne 1 and empty instanceModule.prevActivity}">
						<a class="btn btn-primary pull-left btn-nav btn-prev" id="prev-module">Previous</a>
						</c:if>
						<c:if test="${not empty instanceModule.prevActivity}">
						<a class="btn btn-primary pull-left btn-nav btn-prev" id="prev-activity">Previous</a>
						</c:if>
					</div>
					<div class="col-sm-4 pull-right text-right">
						<c:if test="${instanceModule.module.orderNo ne fn:length(instanceTopic.instanceModuleList) and empty instanceModule.nextActivity}">
						<a class="btn btn-primary pull-left btn-next" data-toggle="modal"
							data-target="#nextModuleModal">Next</a>
						<%-- <a class="btn btn-primary pull-left btn-next" data-id="${instanceModule.currActivity}" data-toggle="modal"
							data-target="#nextModuleModal">Next</a> --%>
						</c:if>
						<c:if test="${not empty instanceModule.nextActivity}">
						<a class="btn btn-primary pull-left btn-nav btn-next" id="next-activity">Next</a>
						</c:if>
						<c:if test="${instanceModule.module.orderNo eq fn:length(instanceTopic.instanceModuleList) and empty instanceModule.nextActivity}">
						<a class="btn btn-primary pull-left btn-finished btn-next" data-toggle="modal"
							data-target="#finishTopicModal">Finish</a>
						</c:if>
					</div>
				</div>
			</div>
<!-- 		</div> -->
		</form>
		</div>
	</div>
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>
<jsp:include page="helperPages/userActivityModal.jsp"></jsp:include>
<div id="bottom"></div>
</body>
</html>