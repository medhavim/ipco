$(document).ready(function() {
	
	$(".option").on('click', function(e) {
		$('#customForm').attr('action', $(this)[0].id);
		$('#customForm').submit();
	});
});