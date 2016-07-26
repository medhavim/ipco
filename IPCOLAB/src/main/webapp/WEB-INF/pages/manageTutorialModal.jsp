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

<!-- 			Renaming the topic pop up modal  START-->
<div class="modal fade" id="renameTopic" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Rename Topic</h2>
			</div>
			<div class="modal-body">
				<input type="text" class="form-control" id="renameTopicName"
					name="renameTopicName" placeholder="Enter new topic name" /> <input
					type="hidden" name="renameTopicId" id="renameTopicId" />
			</div>
			<div class="modal-footer">
				<input type="button" id="changeTopicName" class="btn btn-success"
					role="button" value="Change Name" />
			</div>
		</div>
	</div>
</div>
<!-- 			Renaming the topic pop up modal  END-->

<!-- Loading modal Start -->
<div id="loadingDiv" class="modal">
	<i class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></i>
</div>
<!-- Loading modal End -->

<!-- 		Cannot delete warning START -->
<div class="modal fade" id="warningDialog" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">No can do!!!</h2>
			</div>
			<div class="modal-body">
				<h2 class="modal-title">It seems that the topic is not empty. You cannot delete this topic.</h2>
			</div>
			<div class="modal-footer">
				<a class="btn btn-danger" data-dismiss="modal">OK</a>
			</div>
		</div>
	</div>
</div>
<!-- 		Cannot delete warning  END -->

<!-- 			Confirmation dialog before delete START -->
<div class="modal fade" id="confirmationDialog" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Please confirm!</h2>
			</div>
			<form id="confirmationForm" name="confirmationForm" method="post">
				<div class="modal-body">
					<h3 class="modal-title">Do you really want to remove this?</h3>
					<input type="hidden" class="form-control" id="deletableId"  name="deletableId" />
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal">No</a>
					<input type="submit" class="btn btn-success" role="button" value="Yes"/>
				</div>
			</form>
		</div>
	</div>
</div>
<input type="hidden" name="activityModuleId" id="activityModuleId" value="${activityModuleId}" />
<input type="hidden" name="moduleTopicId" id="moduleTopicId" value="${moduleTopicId}" />
<!-- 			Confirmation dialog before delete END -->

<!-- 			Adding new Module START -->
<div class="modal fade" id="addNewModule" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Add New Module</h2>
			</div>
			<form action="addNewModule.action" method="post">
				<div class="modal-body">
					<input type="text" class="form-control" id="moduleName"
						name="moduleName" placeholder="Enter module name" required />
					<input type="hidden" name="topicId" id="topicId" />
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
<!-- 			Adding new Module END -->

<!-- Renaming the Module pop up modal  START-->
<div class="modal fade" id="renameModule" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Rename Topic</h2>
			</div>
			<div class="modal-body">
				<input type="text" class="form-control" id="renameModule"
					name="renameModule" placeholder="Enter Module name" /> 
					<input type="hidden" name="renameModuleId" id="renameModuleId" />
			</div>
			<div class="modal-footer">
				<input type="button" id="changeModuleName" class="btn btn-success"
					role="button" value="Change Name" />
			</div>
		</div>
	</div>
</div>
<!-- Renaming the Module pop up modal  END-->

<!-- Edit Activity form Start -->
<form name="editForm" id="editForm" action="#" method="post">
	<input type="hidden" id="id" name="id" value="" />
</form>
<!-- Edit Activity form End -->

