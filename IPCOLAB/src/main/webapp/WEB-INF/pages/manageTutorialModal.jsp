<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!-- 			Adding new Topic START -->
<div class="modal fade" id="addNewTopic" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Add Topic</h2>
			</div>
			<form action="addNewTopic.action" method="post" role="form">
				<div class="modal-body form-group">
					<input type="text" class="form-control" id="topicName"
						name="topicName" placeholder="Enter new topic name" required />
				</div>
				<div class="modal-body">
				<div class="row">
					<div class="col-sm-3 col-sm-offset-2 form-group">
						<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel required">
							<input style="display:none;" class="optionInput form-control" type="radio" name="topicTypeId" id="topicTypeBasic" value="1" required> Overview Of IP
						</label>
					</div>
					<div class="col-sm-3 col-sm-offset-2 form-group">
						<label class="btn btn-block btn-default button-wrapper radio-inline optionRadioLabel">
							<input style="display:none;" class="optionInput form-control" type="radio" name="topicTypeId" id="topicTypeCustom" value="2">Custom
							</label>
					</div>
				</div>
				</div>
				<div class="modal-footer">
				<div class="row">
					<div class="col-sm-5 pull-right form-group">
						<input type="submit" class="btn btn-success btn-block btn-lg" role="button"
						value="Add" />
					</div>
				</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 			Adding new Topic END -->