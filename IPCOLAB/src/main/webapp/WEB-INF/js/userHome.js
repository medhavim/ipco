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
});