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
	
	$(".instanceModule").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#customForm').attr('action', "gotoModule.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
	});
	
	$(".instanceQuiz").on("click", function(e){
		id = $(this)[0].id.split("_")[1];
		$('#customForm').attr('action', "gotoUserQuiz.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
	});
});