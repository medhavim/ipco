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

//jQuery to handle popover for Answer Summary when "Check Answers" button is clicked
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});

// Navigating from one activity/module to next/prev Start
$(document).on('click', '.btn-nav', function(e) {
	$('#navType').val($(this)[0].id);
	$('#activityForm').submit();
});
$(document).on('click', '.btn-nav.btn-prev', function(e) {
	$('#navType').val($(this)[0].id);
	$('#activityForm').attr('action', 'navigateActivity.action');
	$('#activityForm').submit();
});

// Added by Medhavi
$(document).on('click', '.btn-next', function(e) {
	var instance = $(this).data('id');
	console.log(instance);
	 $(".p #summary").innerHTML(instance);
});


// Navigating from one activity/module to next/prev End

// Navigating to module by clicking on the module button Start

$(document).on("click", ".instanceModule", function(e){
	if($(this)[0].disabled == true){
	    e.preventDefault();
	    alert("You do not have access to modules you have not started. \nYou can only access this module once you have finished all the modules beofre this.");
	}else{
		id = $(this)[0].id.split("_")[1];
		$('#customForm').attr('action', "gotoModule.action");
		$("#customForm input[id=id]").val(id);
		$('#customForm').submit();
	}
});

// Navigating to module by clicking on the module button End

// Navigating to activity answer by clicking on the activity button Start

$(document).on("click", ".activityAnswer", function(e){
	id = $(this)[0].id.split("_")[1];
	$('#customForm').attr('action', "gotoActivityAnswer.action");
	$("#customForm input[id=id]").val(id);
	$('#customForm').submit();
});

// Navigating to activity answer by clicking on the activity button End

// Display correct answers to the user Start
$(document).on("click", ".checkAnswers", function(e){
	if($(".showAnswer").length !=0 || $(".showIncorrectAnswer").length !=0){
		$(".showAnswer").each(function(){
			$(this).addClass("correctOption");
			$(this).addClass("hideAnswer");
			$(this).removeClass("showAnswer");
		});
		$(".showIncorrectAnswer").each(function(){
			$(this).addClass("incorrectOption");
			$(this).addClass("hideIncorrectAnswer");
			$(this).removeClass("showIncorrectAnswer");
		});
	}else if($(".hideIncorrectAnswer").length !=0 || $(".hideAnswer").length !=0){
		$(".hideIncorrectAnswer").each(function(){
			$(this).removeClass("incorrectOption");
			$(this).removeClass("hideIncorrectAnswer");
			$(this).addClass("showIncorrectAnswer");
		});
		$(".hideAnswer").each(function(){
			$(this).removeClass("correctOption");
			$(this).removeClass("hideAnswer");
			$(this).addClass("showAnswer");
		});
	}
	e.stopPropagation();
	e.preventDefault();
});
// Display correct answers to the user End
