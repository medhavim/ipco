<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:forEach items="${currInstanceModule.activityAnswerList}" var="activityAnswer">
<c:if test="${activityAnswer.activityOption.activity.activityTemplate.activityTemplateId eq 7}">
<div class="jumbotron tile gray title">
	<div class="container-fluid text-left activity-content">
	<h2 class="title text-center">${activityAnswer.activityOption.activity.activityText}</h2>
	</div>
	<div>
		<div class="container-fluid text-center video-mcq activity-content bg-warning">
			<div class="row form-group">
				<div class="col-sm-6">
					<c:forEach items="${activityAnswer.activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 1}">
					<video width="600" controls>
						<source src="${option.optionText}" type="video/mp4">
						<source src="${option.optionText}" type="video/ogg">
					</video>
					<ins>Your browser does not support the video tag.</ins>
					</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="row mcqOptions form-group">
				<c:forEach items="${activityAnswer.answers}" var="answer">
				<div class="col-sm-3 mcqOption <c:forEach items="${activityAnswer.activityOption.options}" var="option">${option.orderNo eq answer.orderNo 
						and option.isCorrect eq 'true'?'correctOption':''}</c:forEach>" 
					 id="mcqOption_${answer.optionId}">
				   	<span style="white-space: nowrap;"> 
					   	<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}" 
					   			value="selectedAnswer_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}
					   			${activityAnswer.status.statusId==3?'disabled':''}>
						<label class="option-text h3">${answer.optionText}</label>
					</span>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</c:if>
</c:forEach>