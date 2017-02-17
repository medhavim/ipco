<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Topics</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/metro-bootstrap/3.1.1.2/css/metro-bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
<%@include file="../css/header.css" %>
<%@include file="../css/userProfile.css" %>
</style>
<script type="text/javascript">
<%@include file="../js/manageUser.js" %>
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="jumbotron title admin">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="adminHome.action">Home</a></li>
	  	<li class="breadcrumb-item active"><span class="h3">Manage User</span></li>
	</ol>
	<div class="container text-center">
		<h1>Manage User</h1>
		<p>Review all the users registered with the application</p>
	</div>
</div>
<div class="jumbotron content">
	<div class="jumbotron">
	<div class="container-fluid text-left">
		<div class="row">
			<span class="container_name h2" 
				data-toggle="collapse" data-target="#manageUserRole" >Manage User Role</span>
			<div class="panel-collapse collapse in" id="manageUserRole">
				<div class="container-fluid text-left">
					<div class="row">
						<form action="addUserRole.action" method="post">
							<div class="col-sm-6 pull-left text-left">
								<input type="text" class="form-control" name="userRoleDesc" />
							</div>
							<div class="col-sm-6 pull-left text-left">
								<input class="btn btn-primary button-wrapper" type="submit" name="Add" value="Add User Role"/>
							</div>
						</form>
					</div>
				</div>
				<c:if test="${empty userRoles}">
				<br>
				<div class="row bg-danger topic-row">
					<div class="col-sm-12">
					<h3>There are no user roles currently with the application</h3>
					</div>
				</div>
				</c:if>
				<c:if test="${not empty userRoles}">
				<c:forEach items="${userRoles}" var="userRole">
				<div class="btn btn-info btn-block userRole topic-row">
					<div class="row" id="userRoleLabel_${userRole.userRoleId}">
						<div class="col-sm-8 text-left">
							<span class="h3" id="userRoleDescLabel_${userRole.userRoleId}">${userRole.userRoleDesc}</span>
						</div>
						<div class="col-sm-4 text-right">
							<span class="editUserRole h2" id="editUserRoleId_${userRole.userRoleId}"><i class="glyphicon glyphicon-pencil"></i></span>
						</div>
					</div>
					<div class="row" id="userRoleEdit_${userRole.userRoleId}" style="display:none">
						<form action="updateUserRole.action" method="post">
							<input type="hidden" class="form-control" name="userRoleId" value="${userRole.userRoleId}" />
							<div class="col-sm-6 pull-left text-left">
								<input type="text" class="form-control" id="editUserRoleDesc_${userRole.userRoleId}" name="userRoleDesc" value="${userRole.userRoleDesc}" />
							</div>
							<div class="col-sm-4 pull-left text-left">
								<input class="btn btn-primary button-wrapper" type="submit" name="update" value="Update User Role"/>
							</div>
							<div class="col-sm-2 text-right">
							<span class="cancelEditUserRole h2" id="cancelEditUserRoleId_${userRole.userRoleId}"><i class="glyphicon glyphicon-remove-sign"></i></span>
						</div>
						</form>
					</div>
				</div>
				</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
	</div>
	<div class="jumbotron">
	<div class="container-fluid text-left">
	<span class="h2" >Review User Progress</span>
		<div class="row">
			<div class="col-sm-2">
				<span class="h3">Sort By:</span>
			</div>
			<div class="col-sm-10">
				<div id="sortByVal" class="${sortBy}"></div>
				<ul class="nav nav-pills nav-justified actTemplate">
					<li role="presentation"><a class="sortBy" id="firstName">First name</a></li>
					<li role="presentation"><a class="sortBy" id="lastName">Last name</a></li>
					<li role="presentation" ${sortBy eq 'lastLogin'?'class="activte"':''}><a class="sortBy" id="lastLogin">Last login</a></li>
					<li role="presentation" ${sortBy eq 'registered'?'class="activte"':''}><a class="sortBy" id="registered">Registered</a></li>
				</ul>
			</div>
		</div>
		<h2 class="strong">Users</h2>
		<c:if test="${empty registeredUsers}">
		<br>
		<div class="row bg-danger topic-row">
			<div class="col-sm-12">
			<h3>There are no users currently registered with the application</h3>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty registeredUsers}">
		<c:forEach items="${registeredUsers}" var="registeredUser">
			<div class="btn btn-info btn-block userProfile topic-row" id="registeredUser_${registeredUser.userId}">
			<div class="row">
				<div class="col-sm-8 text-left">
					<span class="h3">${registeredUser.lastName}, ${registeredUser.firstName}</span>
				</div>
				<div class="col-sm-4 text-right">
					<span class="deleteRegUser h2" id="deleteRegUserId_${registeredUser.userId}"><i class="glyphicon glyphicon-remove-sign"></i></span>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4 pull-left text-left">
					<span class="h3"><i>Last Login: <fmt:formatDate value="${registeredUser.credential.updatedTs}" pattern="MMM dd, yyyy hh:mm a"/></i></span>
				</div>
				<div class="col-sm-4 pull-right text-left">
					<span class="h3"><i>Registered on: <fmt:formatDate value="${registeredUser.createdTs}" pattern="MMM dd, yyyy hh:mm a"/></i></span>
				</div>
			</div>
			</div>
		</c:forEach>
		</c:if>
	</div>
	</div>
	<hr>
<!-- 	<div class="container-fluid text-left"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-sm-4 pull-right text-right"> -->
<!-- 				<form action="startTutorial.action"  method="post"> -->
<!-- 					<input class="btn btn-primary button-wrapper start-tutorial" type="submit" name="Start Tutorial" value="Start Tutorial"/> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>
<form action="#" id="customForm" method="post">
	<input type="hidden" name="id" id="id"/>
</form>

<!-- 			Confirmation dialog before delete START -->
<div class="modal fade" id="confirmationDialog" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Please confirm!</h2>
			</div>
			<form id="confirmationForm" name="confirmationForm" method="post">
				<div class="modal-body">
					<h3 class="modal-title">Do you really want to remove this?</h3>
					<input type="hidden" class="form-control" id="deletableId"  name="deletableId" />
				</div>
				<div class="modal-footer">
					<div class="row">
						<div class="col-sm-5 pull-left form-group">
							<a class="btn btn-success btn-block" data-dismiss="modal">No</a>
						</div>
						<div class="col-sm-5 pull-right form-group">
							<input type="submit" class="btn btn-default btn-block" role="button" value="Yes"/>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 			Confirmation dialog before delete END -->

</body>
</html>