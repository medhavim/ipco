<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 1}">
<div class="jumbotron">
	<div class="container-fluid text-center activity-content">
		<div class="container-fluid  mcqOptions form-group">
			<c:forEach items="${instanceModule.currActivity.answers}" var="answer">
			<!--  <div class="mcqOption <c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">${option.orderNo eq answer.orderNo 
					and option.isCorrect eq 'true'?'showAnswer':''}</c:forEach>" id="mcqOption_${answer.optionId}">-->
			<div class="mcqOption <c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option"><c:if test="${option.orderNo eq answer.orderNo 
					and option.isCorrect eq true}">showAnswer</c:if><c:if test="${option.orderNo eq answer.orderNo 
					and option.isCorrect eq false}">showIncorrectAnswer</c:if></c:forEach>" id="mcqOption_${answer.optionId}">		
			   	<span style="white-space: nowrap;"> 
				   	<input type="radio" name="selectedAnswer" class="radioBtn" id="radioBtn_${answer.optionId}" value="selectedAnswer_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>
					<label id="labelText" class="labelText option-text h3 ">${answer.optionText}</label>
				</span>
			</div>
			</c:forEach>
		</div>
	</div>
	<div class="container-fluid text-right">
		<c:if test="${not instanceModule.currActivity.activityOption.activity.activityExplanation eq NULL}">
			<button class="btn btn-success checkAnswers" data-toggle="popover" data-placement="left" title="Explanation" data-content="${instanceModule.currActivity.activityOption.activity.activityExplanation}">Check Answers</button>
		</c:if>
		<c:if test="${instanceModule.currActivity.activityOption.activity.activityExplanation eq NULL}">
			<button class="btn btn-success checkAnswers">Check Answers</button>
		</c:if>	
	</div>
</div>
</c:if>