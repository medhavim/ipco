<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:forEach items="${currInstanceModule.activityAnswerList}" var="activityAnswer">
<c:if test="${activityAnswer.activityOption.activity.activityTemplate.activityTemplateId eq 2}">
<div class="jumbotron tile gray title">
	<div class="container-fluid text-left activity-content">
	<h2 class="title text-center">${activityAnswer.activityOption.activity.activityText}</h2>
	</div>
	<div class="container-fluid text-center activity-content bg-warning">
		<div class="row form-group">
			<c:forEach items="${activityAnswer.answers}" var="answer">
			<div class="col-sm-5 yesnoOption <c:forEach items="${activityAnswer.activityOption.options}" var="option">${option.orderNo eq answer.orderNo 
					and option.isCorrect eq 'true'?'correctOption':''}</c:forEach>">
			   	<label class="btn btn-block ${answer.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
					<input style="display:none;" class="optionInput" type="radio" 
						name="yesno-option" id="inlineRadio1" value="yesno_${answer.optionId}" 
						${answer.isCorrect=='true'?'checked':''}
						disabled/>${answer.optionText}
				</label>
			</div>
			</c:forEach>
		</div>
	</div>
</div>
</c:if>
</c:forEach>