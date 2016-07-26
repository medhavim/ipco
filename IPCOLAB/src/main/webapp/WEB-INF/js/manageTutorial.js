function renameTopic(button) {
	var topicName = button.name;
	var topicId = button.id;
	$('#renameTopic input[name=renameTopicName]').val(topicName);
	$('#renameTopic input[name=renameTopicId]').val(topicId);
	$("#renameTopic").modal("toggle");
}
	
function addModule(button) {
	var topicId = button.id.split("-")[1];
	$('#topicId').val(topicId);
}
	
function deleteTopic(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var topicNotEmpty = $("#topicNotEmpty_"+deleteId).val();
	
	if(topicNotEmpty=="true"){
		$("#warningDialog").modal("toggle");
	}else{
		var form = document.getElementById("confirmationForm");
		form.action = "deleteTopic.action";
		$("#deletableId").val(deleteId);
		$("#confirmationDialog").modal("toggle");
	}
}
	
//helper functions for module

function deleteModule(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var moduleNotEmpty = $("#moduleNotEmpty_"+deleteId).val();
	
	if(moduleNotEmpty == "true"){
		$("#warningDialog").modal("toggle");
	}else{
		var form = document.getElementById("confirmationForm");
		form.action = "deleteModule.action";
		$("#deletableId").val(deleteId);
		$("#confirmationDialog").modal("toggle");
	}
}

function editActivity(button){
	
	var form = document.getElementById("editForm");
	form.action="gotoEditActivity.action";
	form.children.namedItem("id").value=button.id.split("_")[1];
	form.submit();
}


function renameModule(button) {
	var moduleName = button.name;
	var moduleId = button.id.split("_")[1];
	$('#renameModule input[name=renameModule]').val(moduleName);
	$('#renameModule input[name=renameModuleId]').val(moduleId);
	$("#renameModule").modal("toggle");
}

function deleteActivity(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var form = document.getElementById("confirmationForm");
	form.action = "deleteActivity.action";
	$("#deletableId").val(deleteId);
}

$(document).ready(function() {
// 		Ajax for renaming the topic name
	$("#changeTopicName").click(function() {
		topicName = $('#renameTopic input[name=renameTopicName]').val();
		topicId = $('#renameTopic input[name=renameTopicId]').val();
		$("#loadingDiv").modal("toggle");
		$("#renameTopic").modal("toggle");
		$.ajax({
			type : "POST",
			url : "renameTopic.action",
			data : "topicName=" + topicName + "&topicId=" + topicId,
			success : function(data) {
				$("#loadingDiv").modal("toggle");
				$("#topic_name_" + topicId)[0].innerHTML = topicName;
				$('#'+topicId).attr('name', topicName);
			}
		});
	});
	
	
	$(".optionRadioLabel").unbind('click').on('click', function(e){
		e.stopPropagation();
		e.preventDefault();
		$(this).parent().parent().find("label").each(function(){
			$(this).removeClass('btn-primary').addClass('btn-default');
			$(this).find('input').each(function(){
				$(this).prop('checked', false);
			});
		});
		$(this).removeClass('btn-default').addClass('btn-primary');
		$(this).find('input').each(function(){
			$(this).prop('checked', true);
		});
	});
	
//	helper functions for module
	
//		Ajax for renaming the activity container name
	$("#changeModuleName").click(function() {
		moduleName = $('#renameModule input[name=renameModule]').val();
		moduleId = $('#renameModule input[name=renameModuleId]').val();
		$("#loadingDiv").modal("toggle");
		$("#renameModule").modal("toggle");
		$.ajax({
			type : "POST",
			url : "renameModule.action",
			data : "moduleName=" + moduleName + "&moduleId=" + moduleId,
			success : function(data) {
				$("#loadingDiv").modal("toggle");
				$("#moduleName_" + moduleId)[0].innerHTML = moduleName;
				$('#renameModule_'+moduleId).attr('name', moduleName);
			}
		});
	});
	
	if($('#moduleTopicId').val() != ""){
		var topicId = $('#moduleTopicId').val();
		$('.span-topic-'+topicId).removeClass('collapsed');
		$('#modules_for-'+topicId).addClass('in');
	}
	
	if($('#activityModuleId').val() != ""){
		var moduleId = $('#activityModuleId').val();
		$('.span-module-'+moduleId).removeClass('collapsed');
		$('#activities_for-'+moduleId).addClass('in');
	}
	
});

