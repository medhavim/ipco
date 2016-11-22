function renameTopic(button) {
	var topicName = button.name;
	var topicId = button.id;
	$('#renameTopic input[name=renameTopicName]').val(topicName);
	$('#renameTopic input[name=renameTopicId]').val(topicId);
	$("#renameTopic").modal("toggle");
}

function renameQiuz(button) {
	var quizName = button.name;
	var quizId = button.id.split("_")[1];
	$('#renameQiuz input[name=renameQuizName]').val(quizName);
	$('#renameQiuz input[name=renameQuizId]').val(quizId);
	$("#renameQiuz").modal("toggle");
}
	
function addModule(button) {
	var topicId = button.id.split("-")[1];
	$('#topicId').val(topicId);
}

function addQuizForTopic(topicId) {
	$('#quizForTopicId').val(topicId);
}
	
function deleteTopic(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var topicNotEmpty = $("#topicNotEmpty_"+deleteId).val();
	
	var form = document.getElementById("confirmationForm");
	form.action = "deleteTopic.action";
	$("#deletableId").val(deleteId);
	if(topicNotEmpty=="true"){
		$("#warningDialog").modal("toggle");
	}else{
		$("#confirmationDialog").modal("toggle");
	}
}

function deleteQuiz(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var quizNotEmpty = $("#quizNotEmpty_"+deleteId).val();
	
	var form = document.getElementById("confirmationForm");
	form.action = "deleteQuiz.action";
	$("#deletableId").val(deleteId);
	if(quizNotEmpty=="true"){
		$("#warningDialog").modal("toggle");
	}else{
		$("#confirmationDialog").modal("toggle");
	}
}	

function deleteQuizOption(id){
	
	var deleteId = id.split("_")[1];
	
	var form = document.getElementById("confirmationForm");
	form.action = "deleteQuizOption.action";
	$("#deletableId").val(deleteId);
	$("#confirmationDialog").modal("toggle");
}	
//helper functions for module

function deleteModule(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var moduleNotEmpty = $("#moduleNotEmpty_"+deleteId).val();
	
	var form = document.getElementById("confirmationForm");
	form.action = "deleteModule.action";
	$("#deletableId").val(deleteId);
	if(moduleNotEmpty == "true"){
		$("#warningDialog").modal("toggle");
	}else{
		$("#confirmationDialog").modal("toggle");
	}
}

function editActivity(button){
	
	var form = document.getElementById("editForm");
	form.action="gotoEditActivity.action";
	form.children.namedItem("id").value=button.id.split("_")[1];
	form.submit();
}

function editQuizOption(id){
	
	var form = document.getElementById("editForm");
	form.action="gotoEditQuizOption.action";
	form.children.namedItem("id").value=id.split("_")[1];
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

function addQuiz(button){
	
	var form = document.getElementById("editForm");
	form.action="gotoAddQuiz.action";
	form.children.namedItem("id").value=button.id.split("-")[1];
	form.submit();
}

$(document).ready(function() {
// Ajax for renaming the topic name start
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
// Ajax for renaming the topic name end
// Ajax for renaming the quiz name start
	$("#changeQuizName").click(function() {
		quizName = $('#renameQiuz input[name=renameQuizName]').val();
		quizId = $('#renameQiuz input[name=renameQuizId]').val();
		$("#loadingDiv").modal("toggle");
		$("#renameQiuz").modal("toggle");
		$.ajax({
			type : "POST",
			url : "renameQuiz.action",
			data : "quizName=" + quizName + "&quizId=" + quizId,
			success : function(data) {
				$("#loadingDiv").modal("toggle");
				$("#quizName_" + quizId)[0].innerHTML = quizName;
				$('#renameQuiz_'+quizId).attr('name', quizName);
			}
		});
	});
// Ajax for renaming the quiz name end
	
	
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


//adding formatter to the textarea Start

tinymce.init({
	selector: 'textarea',
	height: 5,
	plugins: [
	 'advlist autolink lists link image charmap preview anchor',
	 'searchreplace visualblocks code',
	 'insertdatetime media table contextmenu paste code'
	],
	toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link',
	menubar: false,
	content_css: [
	 '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
	 '//www.tinymce.com/css/codepen.min.css'
	]
});
//adding formatter to the textarea End

// Functionality to edit the topic description Start

function editTopicDesc(topicId){
	$('.topicDescContainer_'+topicId).css('display', 'none');
	$('.topicDescEditor_'+topicId).css('display', 'block');
}

function updateTopicDesc(topicId){
	
	var topicDesc = tinymce.get('topicDescEdit_'+topicId).getContent();
	
	$.ajax({
		type : "POST",
		url : "updateTopicDesc.action",
		data : "topicDesc=" + topicDesc + "&topicId=" + topicId,
		success : function(data) {
			$('#topicDescContent_'+topicId).html(topicDesc)
			$('.topicDescContainer_'+topicId).css('display', 'block');
			$('.topicDescEditor_'+topicId).css('display', 'none');
		}
	});
}

// Functionality to edit the topic description End


