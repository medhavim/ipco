<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${instanceModule.currActivity.activityOption.activity.activityTemplate.activityTemplateId eq 8}">
<div class="jumbotron">
	<div class="container-fluid text-center video-yesno activity-content">
		<div class="row form-group">
			<div class="col-sm-6">
				<c:forEach items="${instanceModule.currActivity.activityOption.options}" var="option">
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
				<div class="row form-group image-options">
					<div class="col-md-2">
					</div>
					<c:forEach items="${instanceModule.currActivity.answers}" var="answer">
					<div class="col-md-4 form-group">
						<label class="btn btn-block ${answer.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
							<input style="display:none;" class="optionInput" type="radio" 
							name="yesno-option" id="inlineRadio1" value="yesno_${answer.optionId}" ${answer.isCorrect=='true'?'checked':''}>${answer.optionText}
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