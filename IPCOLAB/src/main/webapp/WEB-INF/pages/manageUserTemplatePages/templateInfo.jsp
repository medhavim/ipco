<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:forEach items="${currInstanceModule.activityAnswerList}" var="activityAnswer">
<c:if test="${activityAnswer.activityOption.activity.activityTemplate.activityTemplateId eq 9}">
<div class="jumbotron tile black title">
	<div class="container-fluid text-left activity-content">
	<h2 class=" title text-center">${activityAnswer.activityOption.activity.activityText}</h2>
	</div>
</div>
</c:if>
</c:forEach>