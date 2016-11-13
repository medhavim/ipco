<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${currentQuizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 7}">
<div class="jumbotron">
	<div class="container-fluid text-center video-mcq activity-content">
		<div class="row form-group">
			<div class="col-sm-6">
				<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
				<c:if test="${option.orderNo eq 1}">
				<video width="600" controls>
					<source src="${option.optionText}" type="video/mp4">
					<source src="${option.optionText}" type="video/ogg">
				</video>
				<ins>Your browser does not support the video tag.</ins>
				</c:if>
				</c:forEach>
			</div>
			<div class="col-sm-6">
				<div class="row mcqOptions form-group image-options">
					<c:forEach items="${currentQuizAnswer.quizOption.correctAnswers}" var="option">
					<c:if test="${option.orderNo ne 1}">
					<div class="col-sm-6  mcqOption " id="mcqOption_${answer.optionId}">
					   	<span style="white-space: nowrap;"> 
							<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${answer.optionId}" 
									value="selectedAnswer_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}
									${currentQuizAnswer.status.statusId==3?'disabled':''}>
							<label class="option-text h3">${answer.optionText}</label>
						</span>
					</div>
					</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
</c:if>