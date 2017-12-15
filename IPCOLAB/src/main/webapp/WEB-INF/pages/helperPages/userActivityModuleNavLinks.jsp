<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%-- <div class="nav-bar">
	<div class="btn-group btn-group-justified" role="group" >
		<c:forEach items="${instanceTopic.instanceModuleList}" var="instModule">
		<div class="btn-group" role="group">
	    	<button type="button" 
	    	class="instanceModule btn <c:if test="${instModule.status.statusId eq 1}">btn-default <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-ongoing-module</c:if></c:if>
	    	<c:if test="${instModule.status.statusId eq 2}">btn-info <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-ongoing-module</c:if></c:if>
	    	<c:if test="${instModule.status.statusId eq 3}">btn-success <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-complete-module</c:if></c:if>"
	    	<c:if test="${instModule.status.statusId eq 1}">disabled onclick="alert(You do not have access to modules you have not started. \nYou can only access this module once you have finished all the modules beofre this.")"</c:if>
	    	id="moduleId_${instModule.instanceModuleId}">${instModule.module.moduleName}</button>
	  	</div>
		</c:forEach>
	</div>
</div> --%>


<div class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header pull-center">
    		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#moduleNavLinks">
	        <span class="icon-bar">Contents</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
      	</button>
    	</div>
    <div class="collapse navbar-collapse" id="moduleNavLinks">
      	<!-- <ul class="nav navbar-nav pull-center" id="moduleList"> -->
      	<ul class="nav nav-pills nav-justified" id="moduleList">
      	<c:forEach items="${instanceTopic.instanceModuleList}" var="instModule">
			<li class="dropdown">
          		<a class="dropdown-toggle instanceModule btn <c:if test="${instModule.status.statusId eq 1}">btn-default <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-ongoing-module</c:if></c:if>
	    			<c:if test="${instModule.status.statusId eq 2}">btn-info <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-ongoing-module</c:if></c:if>
	    			<c:if test="${instModule.status.statusId eq 3}">btn-success <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-complete-module</c:if></c:if>"
	    			<c:if test="${instModule.status.statusId eq 1}">disabled onclick="alert(You do not have access to modules you have not started. \nYou can only access this module once you have finished all the modules beofre this.")"</c:if>
	    				id="moduleId_${instModule.instanceModuleId}"	data-toggle="dropdown" href="#">${instModule.module.moduleName} <span class="caret"></span>
	   		 	</a>
          		<div class="dropdown-content">
          	 	
          	 	<c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">
          		
            		<c:forEach items="${instanceModule.prevActivity}" var="activityAnswer">
            			<a class="activityAnswer btn <c:if test="${activityAnswer.status.statusId eq 2}">btn-info</c:if> <c:if test="${activityAnswer.status.statusId eq 3}">btn-success</c:if>"
		   				id="activityAnswerId_${activityAnswer.activityAnswerId}">
		   				${activityAnswer.activityOption.activity.activityTitle}
		   			</a>
				</c:forEach>
				
				<c:if test="${not empty instanceModule.currActivity}">
					<a class="btn <c:if test="${instanceModule.currActivity.status.statusId eq 2}">btn-info current-ongoing-module</c:if>
					<c:if test="${instanceModule.currActivity.status.statusId eq 3}">btn-success current-complete-module</c:if>">
						${instanceModule.currActivity.activityOption.activity.activityTitle}
					</a>
				</c:if>
				
				<c:forEach items="${instanceModule.nextActivity}" var="activityAnswer">
					<a class="activityAnswer btn <c:if test="${activityAnswer.status.statusId eq 2}">btn-info</c:if> <c:if test="${activityAnswer.status.statusId eq 3}">btn-success</c:if>"
					    	<c:if test="${activityAnswer.status.statusId eq 1}">disabled</c:if>
					    	id="activityAnswerId_${activityAnswer.activityAnswerId}">
					    	${activityAnswer.activityOption.activity.activityTitle}
					</a>
				</c:forEach>
				
				</c:if>
          		</div>
        		</li>
        		</c:forEach>
      	</ul>
    </div>
</div>