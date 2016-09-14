// adding formatter to the textarea Start

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
// adding formatter to the textarea End

$(document).ready(function(){
	
//jQuery to handle yes/no activity Start
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
//jQuery to handle yes/no activity End
});


// Navigating from one activity/module to next/prev Start
$(document).on('click', '.btn-nav', function(e) {
	$('#navType').val($(this)[0].id);
	$('#activityForm').submit();
});

// Navigating from one activity/module to next/prev End
