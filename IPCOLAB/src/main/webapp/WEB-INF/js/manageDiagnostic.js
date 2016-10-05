function renameCategory(button) {
	var categoryTitle = button.name;
	var categoryId = button.id;
	$('#renameCategory input[name=renameCategoryTitle]').val(categoryTitle);
	$('#renameCategory input[name=renameCategoryId]').val(categoryId);
	$("#renameCategory").modal("toggle");
}
	
function addModule(button) {
	var topicId = button.id.split("-")[1];
	$('#topicId').val(topicId);
}

function deleteCategory(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	var categoryNotEmpty = $("#categoryNotEmpty_"+deleteId).val();
	
	var form = document.getElementById("confirmationForm");
	form.action = "deleteCategory.action";
	$("#deletableId").val(deleteId);
	if(categoryNotEmpty=="true"){
		$("#warningDialog").modal("toggle");
	}else{
		$("#confirmationDialog").modal("toggle");
	}
}
	
function deleteDiagnostic(deletedTag){
	
	var deleteId = deletedTag.id.split("_")[1];
	
	var form = document.getElementById("confirmationForm");
	form.action = "deleteDiagnostic.action";
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

function addRelatedDiagnostic(button){
	var form = document.getElementById("editForm");
	form.action="gotoAddRelatedDiagnostic.action";
	form.children.namedItem("id").value=button.id.split("_")[1];
	form.submit();
	
}

function editRelatedDiagnostic(id){
	var form = document.getElementById("editForm");
	form.action="editRelatedDiagnostic.action";
	form.children.namedItem("id").value=id.split("_")[1];
	form.submit();
	
}

$(document).ready(function() {
// 		Ajax for renaming the topic name
	$("#changeCategoryTitle").click(function() {
		categoryTitle = $('#renameCategory input[name=renameCategoryTitle]').val();
		categoryId = $('#renameCategory input[name=renameCategoryId]').val();
		$("#loadingDiv").modal("toggle");
		$("#renameCategory").modal("toggle");
		$.ajax({
			type : "POST",
			url : "renameCategory.action",
			data : "categoryTitle=" + categoryTitle + "&categoryId=" + categoryId,
			success : function(data) {
				$("#loadingDiv").modal("toggle");
				$("#category_name_" + categoryId)[0].innerHTML = categoryTitle;
				$('#'+categoryId).attr('name', categoryTitle);
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
	
	if($('#diagnosticCategoryId').val() != ""){
		var categoryId = $('#diagnosticCategoryId').val();
		$('.span-category-'+categoryId).removeClass('collapsed');
		$('#diagnostics_for-'+categoryId).addClass('in');
	}
	
	if($('#diagnosticCategoryId').val() != ""){
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

function editCategoryDesc(categoryId){
	$('.categoryDescContainer_'+categoryId).css('display', 'none');
	$('.categoryDescEditor_'+categoryId).css('display', 'block');
}

function updateCategoryDesc(categoryId){
	
	var categoryDesc = tinymce.get('categoryDescEdit_'+categoryId).getContent();
	
	$.ajax({
		type : "POST",
		url : "updateCategoryDesc.action",
		data : "categoryDesc=" + categoryDesc + "&categoryId=" + categoryId,
		success : function(data) {
			$('#categoryDescContent_'+categoryId).html(categoryDesc);
			$('.categoryDescContainer_'+categoryId).css('display', 'block');
			$('.categoryDescEditor_'+categoryId).css('display', 'none');
		}
	});
}

// Functionality to edit the topic description End


