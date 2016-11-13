<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 5}">
<div class="jumbotron">
	<div class="container-fluid text-center image-yesno activity-content">
		<div class="row form-group">
			<div class="col-sm-6">
				<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
				<c:if test="${option.orderNo eq 1}">
				<img src="${option.optionText}" style="width:100%; height:100%;">
				</c:if>
				</c:forEach>
			</div>
			<div class="col-sm-6">
				<div class="row form-group image-options">
					<div class="col-md-2">
					</div>
					<c:forEach items="${currentQuizAnswer.userAnswers}" var="answer">
					<div class="col-md-4 form-group">
						<label class="btn btn-block ${answer.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
							<input style="display:none;" class="optionInput" type="radio" 
									name="yesno-option" id="inlineRadio1" value="yesno_${answer.optionId}" 
									${answer.isCorrect=='true'?'checked':''}
									${currentQuizAnswer.status.statusId==3?'disabled':''}>${answer.optionText}
						</label>
					</div>
					</c:forEach>
					<div class="col-md-2">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</c:if>