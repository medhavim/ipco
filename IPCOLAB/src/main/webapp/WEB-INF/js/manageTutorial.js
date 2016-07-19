function editContainer(id) {
	var form = document.getElementById("editForm");
	form.action = "editActivityContainer.action";
	form.children.namedItem("id").value = id;
	form.submit();
}
function renameTopic(button) {
	var topicName = button.name;
	var topicId = button.id;
	$('#renameTopic input[name=renameTopicName]').val(topicName);
	$('#renameTopic input[name=renameTopicId]').val(topicId);
	$("#renameTopic").modal("toggle");
}
	
function addContainer(button) {
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
	
function deleteActivityContainer(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var form = document.getElementById("confirmationForm");
	form.action = "deleteActivityContainer.action";
	$("#deletableId").val(deleteId);
}

//helper functions for module

function editActivity(id){
	
	var form = document.getElementById("editForm");
	form.action="editActivity.action";
	form.children.namedItem("id").value=id;
	form.submit();
}


function renameModule(button) {
	var moduleName = button.name;
	var moduleId = button.id;
	$('#renameModule input[name=renameModule]').val(containerName);
	$('#renameModule input[name=renameModuleId]').val(containerId);
	$("#renameModule").modal("toggle");
}

function deleteActivity(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var form = document.getElementById("confirmationForm");
	form.action = "deleteActivity.action";
	$("#deletableId").val(deleteId);
}

function deleteActivityContainer(deletedTag){
	
	var containerNotEmpty = $("#containerNotEmpty").val();
	
	if(containerNotEmpty=="true"){
		$("#warningDialog").modal("toggle");
	}else{
		var deleteId = deletedTag.id.split("_")[1];
		var form = document.getElementById("confirmationForm");
		form.action = "deleteActivityContainer.action";
		$("#deletableId").val(deleteId);
		$("#confirmationDialog").modal("toggle");
	}
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
	
	$(".goBack").on("click",function(e) {
	    e.preventDefault(); // cancel the link itself
	    $("#editForm").attr('action', this.href);
		$("#editForm").submit();
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
	
	$("#addNewActivity").click(function() {
		$("#editForm").attr('action', 'newActivityLink.action');
		$('#id').val($("#addNewActivity").attr("name"));
		$("#editForm").submit();
	});
	
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
	
});
