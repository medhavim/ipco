<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:forEach items="${currInstanceModule.activityAnswerList}" var="activityAnswer">
<c:if test="${activityAnswer.activityOption.activity.activityTemplate.activityTemplateId eq 3}">
<div class="jumbotron tile black title">
	<div class="container-fluid text-left activity-content">
	<h2 class="title text-center">${activityAnswer.activityOption.activity.activityText}</h2>
	</div>
	<div>
		<div class="container-fluid text-center image-desc activity-content" >
			<div class="row form-group">
				<div class="col-sm-8">
					<c:forEach items="${activityAnswer.activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 1}">
					<img src="${option.optionText}" style="width:100%; height:100%;">
					</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<h3 class="text-center">Your answer</h3>
					<c:forEach items="${activityAnswer.answers}" var="answer">
					<c:if test="${answer.orderNo eq 1}">
					<label class="option-text h3">${answer.optionText}</label>
					</c:if>
					</c:forEach>
				</div>
				<div class="col-sm-4-offset-4">
					<h3 class="text-center">What we think</h3>
					<c:forEach items="${activityAnswer.activityOption.options}" var="option">
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
</c:forEach>