<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 4}">
<div class="jumbotron">
	<div class="container-fluid text-center image-mcq activity-content">
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
					<c:forEach items="${currentQuizAnswer.userAnswers}" var="answer">
					<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
					   	<span style="white-space: nowrap;"> 
						   	<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}" 
						   			value="selectedAnswer_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}
						   			${currentQuizAnswer.status.statusId==3?'disabled':''}>
							<label class="option-text h3">${answer.optionText}</label>
						</span>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
</c:if>