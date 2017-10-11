<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:forEach items="${currInstanceModule.activityAnswerList}" var="activityAnswer">
<c:if test="${activityAnswer.activityOption.activity.activityTemplate.activityTemplateId eq 5}">
<div class="jumbotron tile black title">
	<div class="container-fluid text-left activity-content">
	<h2 class="title text-center">${activityAnswer.activityOption.activity.activityText}</h2>
	</div>
	<div>
		<div class="container-fluid text-center image-yesno activity-content bg-warning">
			<div class="row form-group">
				<div class="col-sm-6">
					<c:forEach items="${activityAnswer.activityOption.options}" var="option">
					<c:if test="${option.orderNo eq 1}">
					<img src="${option.optionText}" style="width:100%; height:100%;">
					</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="row image-options  form-group">
				<c:forEach items="${activityAnswer.answers}" var="answer">
				<div class="col-sm-5 yesnoOption <c:forEach items="${activityAnswer.activityOption.options}" var="option">${option.orderNo eq answer.orderNo 
						and option.isCorrect eq 'true'?'correctOption':'incorrectOption'}</c:forEach>">
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
</div>
</c:if>
</c:forEach>