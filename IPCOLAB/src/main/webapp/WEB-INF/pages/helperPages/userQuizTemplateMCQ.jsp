<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 1}">
<div class="jumbotron">
	<div class="container-fluid text-center activity-content">
		<div class="row mcqOptions form-group">
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
</c:if>