<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<div class="nav-bar">
	<div class="btn-group btn-group-justified" role="group" >
<%-- 				<c:forEach items="${instanceModule.activityAnswerList}" var="activityAnswer"> --%>
<!-- 				<div class="btn-group" role="group"> -->
<!-- 			    	<button type="button"  -->
<%-- 	    			class="btn <c:if test="${instanceModule.status.statusId eq 2}">btn-info <c:if test="${activityAnswer.activityAnswerId eq instanceModule.currActivity.activityAnswerId}">current-ongoing-module</c:if></c:if> --%>
<%-- 	    			<c:if test="${instanceModule.status.statusId eq 3}">btn-success <c:if test="${activityAnswer.activityAnswerId eq instanceModule.currActivity.activityAnswerId}">current-complete-module</c:if></c:if>" --%>
<%-- 			    	<c:if test="${activityAnswer.status.statusId eq 1}">disabled</c:if>>${activityAnswer.activityOption.activity.activityTitle}</button> --%>
<!-- 			  	</div> -->
<%-- 				</c:forEach> --%>
		<c:forEach items="${instanceModule.prevActivity}" var="activityAnswer">
		<div class="btn-group" role="group">
	    	<button type="button" 
   			class="activityAnswer btn <c:if test="${activityAnswer.status.statusId eq 2}">btn-info</c:if>
   			<c:if test="${activityAnswer.status.statusId eq 3}">btn-success</c:if>"
   			id="activityAnswerId_${activityAnswer.activityAnswerId}">${activityAnswer.activityOption.activity.activityTitle}</button>
	  	</div>
		</c:forEach>
		<c:if test="${not empty instanceModule.currActivity}">
		<div class="btn-group" role="group">
	    	<button type="button" 
   			class="btn <c:if test="${instanceModule.currActivity.status.statusId eq 2}">btn-info current-ongoing-module</c:if>
   			<c:if test="${instanceModule.currActivity.status.statusId eq 3}">btn-success current-complete-module</c:if>">${instanceModule.currActivity.activityOption.activity.activityTitle}</button>
	  	</div>
		</c:if>
		<c:forEach items="${instanceModule.nextActivity}" var="activityAnswer">
		<div class="btn-group" role="group">
	    	<button type="button" 
   			class="activityAnswer btn <c:if test="${activityAnswer.status.statusId eq 2}">btn-info</c:if>
   			<c:if test="${activityAnswer.status.statusId eq 3}">btn-success</c:if>"
	    	<c:if test="${activityAnswer.status.statusId eq 1}">disabled</c:if>
	    	id="activityAnswerId_${activityAnswer.activityAnswerId}">${activityAnswer.activityOption.activity.activityTitle}</button>
	  	</div>
		</c:forEach>
	</div>
</div>