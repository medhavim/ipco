$(document).ready(function() {
	
	$('.option-desc').css('display', 'none');
	
//	Show/Hide suggestions on mouse hover
	
	$(".option").on('mouseover', function(e) {
		id=$(this)[0].id;
		$('#'+id+"-desc").css('display', 'block');
	});

	$(".option").on('mouseout', function(e) {
		$('.option-desc').css('display', 'none');
	});
	
	$(".option").on('click', function(e) {
		
//		action=$(this).find('a')[0].id;
//		$('#customForm').attr('action', action);
//		$('#customForm').submit();
	});
	
	$(".userProfile").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#customForm').attr('action', "manageUserProfile.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
		e.stopPropagation();
	});
	
	$(".userInstance").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#customForm').attr('action', "manageUserInstance.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
		e.stopPropagation();
	});

	$(".instanceModule").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#customForm').attr('action', "manageInstanceModule.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
	});
	
	$(".instanceQuiz").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#customForm').attr('action', "manageInstanceQuiz.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
	});
	
	// jQuery to handle visibility of the topic contents
	function removeAllTopicComponentContent(){
		$('.topicComponent').each(function(){
			$(this).parent().removeClass('active');
		});
		$('.topicComponentContent').each(function(){
			$(this).css("display", 'none');
		});
	}
	
	$(document).on('click', '.topicComponent', function() {
		removeAllTopicComponentContent();
		$(this).parent().addClass('active');
		var id = $(this)[0].id.split("#")[1];
		$('#'+id).css('display', 'block');
	});
	
	$(".sortBy").on("click", function(e){
		id = $(this)[0].id;
		$('#customForm').attr('action', "sortUsers.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
		e.stopPropagation();
	});
	
	$('#'+$('#sortByVal').attr('class')).parent().addClass("active");
	
	$(".deleteRegUser").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		var form = document.getElementById("confirmationForm");
		form.action = "deleteRegUser.action";
		$("#deletableId").val(id);
		$("#confirmationDialog").modal("toggle");
		e.stopPropagation();
	});
	
	
	$(".editUserRole").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#userRoleLabel_'+id).css("display", 'none');
		$('#userRoleEdit_'+id).css("display", 'block');
		e.stopPropagation();
	});
	
	$(".cancelEditUserRole").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#userRoleLabel_' + id).css("display", 'block');
		$('#userRoleEdit_' + id).css("display", 'none');
		$('#editUserRoleDesc_' + id).val($('#userRoleDescLabel_' + id).text());
		e.stopPropagation();
	});
});