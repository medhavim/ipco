<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:forEach items="${quiz.quizAnswers}" var="quizAnswer">
<c:if test="${quizAnswer.quizOption.activity.activityTemplate.activityTemplateId eq 6}">
<div class="jumbotron tile gray title">
	<div class="container-fluid text-left activity-content">
	<h2 class="title text-center">${quizAnswer.quizOption.activity.activityText}</h2>
	</div>
	<div>
		<div class="container-fluid text-center video-desc activity-content">
			<div class="row form-group">
				<div class="col-sm-6">
					<c:forEach items="${quizAnswer.quizOption.correctAnswers}" var="option">
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
			<div class="row">
				<div class="col-sm-4">
					<h3 class="text-center">Your answer</h3>
					<div id="idealAnswer" class="pull-left">
						<c:forEach items="${quizAnswer.userAnswers}" var="answer">
						<c:if test="${answer.orderNo eq 1}">
						<label class="option-text h3">${answer.optionText}</label>
						</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="col-sm-4-offset-4">
					<h3 class="text-center">What we think</h3>
					<div id="idealAnswer" class="pull-left">
						<c:forEach items="${quizAnswer.quizOption.correctAnswers}" var="option">
						<c:if test="${option.orderNo eq 2}">
						<label class="option-text h3">${option.optionText}</label>
						</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</c:if>
</c:forEach>