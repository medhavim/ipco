<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${templateActivity.activityTemplate.activityTemplateId eq 2}">
<div class="jumbotron">
	<div class="container-fluid text-center activity-content">
		<div class="row form-group">
			<div class="col-md-2">
			</div>
			<c:forEach items="${templateOptions}" var="option">
			<div class="col-md-4 form-group">
				<label class="btn btn-block ${option.isCorrect=='true'?'btn-primary':'btn-default'} button-wrapper radio-inline optionRadioLabel">
					<input style="display:none;" class="optionInput" 
						type="radio" name="yesno-option" id="inlineRadio1" 
						value="${option.optionText}" ${option.isCorrect=='true'?'checked':''}>${option.optionText}
				</label>
			</div>
			</c:forEach>
			<div class="col-md-4">
			</div>
		</div>
	</div>
</div>
</c:if>