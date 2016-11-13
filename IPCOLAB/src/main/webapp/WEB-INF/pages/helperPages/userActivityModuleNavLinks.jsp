<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<div class="nav-bar">
	<div class="btn-group btn-group-justified" role="group" >
		<c:forEach items="${instanceTopic.instanceModuleList}" var="instModule">
		<div class="btn-group" role="group">
	    	<button type="button" 
	    	class="instanceModule btn <c:if test="${instModule.status.statusId eq 1}">btn-default <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-ongoing-module</c:if></c:if>
	    	<c:if test="${instModule.status.statusId eq 2}">btn-info <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-ongoing-module</c:if></c:if>
	    	<c:if test="${instModule.status.statusId eq 3}">btn-success <c:if test="${instModule.instanceModuleId eq instanceModule.instanceModuleId}">current-complete-module</c:if></c:if>"
	    	<c:if test="${instModule.status.statusId eq 1}">disabled</c:if>
	    	id="moduleId_${instModule.instanceModuleId}">${instModule.module.moduleName}</button>
	  	</div>
		</c:forEach>
	</div>
</div>