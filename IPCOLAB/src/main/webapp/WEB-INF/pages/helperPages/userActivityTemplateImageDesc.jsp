<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 3}">
<div class="jumbotron">
	<div class="container-fluid text-center image-desc activity-content" >
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
					<textarea class="form-control" rows="5" name="userAnswer"
						placeholder="Enter Answer Here."><c:forEach items="${instanceModule.currActivity.answers}" var="answer" varStatus="answerNo"><c:if test="${answerNo==1}">${answer.optionText}</c:if></c:forEach></textarea>
				</div>
				<a class="btn btn-info pull-right" data-toggle="collapse"
					data-target="#idealAnswer">Check</a>
				<!-- <div class="clear"></div> -->
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