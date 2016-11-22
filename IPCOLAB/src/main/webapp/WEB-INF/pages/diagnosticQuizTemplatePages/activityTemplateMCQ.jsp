<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:if test="${templateActivity.activityTemplate.activityTemplateId eq 1}">
<div class="jumbotron">
	<div class="container-fluid text-center activity-content">
		<div class="row mcqOptions form-group">
			<c:forEach items="${templateOptions}" var="option">
			<div class="col-sm-6  mcqOption " id="mcqOption_${option.optionId}">
			   	<span style="white-space: nowrap;"> 
				   	<input type="checkbox" name="selectedAnswer" class="chkbx" id="checkBox_${option.optionId}" value="selectedAnswer_${option.optionId}" ${option.isCorrect=='true'?'checked':''}>
					<input type="hidden"name="selectedAnswer_${option.optionId}" id="selectedAnswer_${option.optionId}" value="${option.optionText}"/>
   					<label class="option-text h3">${option.optionText}</label>
				</span>
			</div>
			</c:forEach>
		</div>
	</div>
</div>
</c:if>