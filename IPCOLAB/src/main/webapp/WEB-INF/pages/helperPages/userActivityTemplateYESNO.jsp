<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 2}">
<div class="jumbotron">
	<div class="container-fluid text-center activity-content">
		<div class="row form-group">
			<div class="col-md-2">
			</div>
			<c:forEach items="${instanceModule.currActivity.answers}" var="answer">
			<!--  <div class="col-md-4 yesnoOption <c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">${option.orderNo eq answer.orderNo 
					and option.isCorrect eq 'true'?'showAnswer':''}</c:forEach>"> -->
			<div class="col-md-4 yesnoOption <c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option"><c:if test="${option.orderNo eq answer.orderNo  
					and option.isCorrect eq true}">showAnswer</c:if><c:if test="${option.orderNo eq answer.orderNo 
					and option.isCorrect eq false}">showIncorrectAnswer</c:if></c:forEach>">
				<label class="btn btn-block ${answer.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
					<input style="display:none;" class="optionInput" 
						type="radio" name="yesno-option" id="inlineRadio1" 
						value="yesno_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>${answer.optionText}
				</label>
			</div>
			</c:forEach>
			<div class="col-md-4">
			</div>
		</div>
	</div>
	<div class="container-fluid text-right">
		<button class="btn btn-success checkAnswers">Check Answers</button>
	</div>
</div>
</c:if>