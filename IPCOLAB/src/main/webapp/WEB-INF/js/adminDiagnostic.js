$(document).ready(function(){
	
// jQuery to handle mcq activity Start
	removeAllActiveTemplate();
	
	$('.smpleTmpltOptn').on('click', function(e){
		removeAllActiveTemplate();
		$(this).parent().addClass('active');
		var currSelected = $('.selected-tmplteOptn').children()[0];
		if(currSelected){
			currSelected = $(currSelected).detach();
			currSelected.appendTo('.unselected-tmplteOptn');
		}
		var id = $(this)[0].id.split('#')[1];
		var newSelected = $('.'+id).detach();
		newSelected.appendTo('.selected-tmplteOptn' );
		e.preventDefault(); // cancel the link itself
	});
	$('.compTmpltOptn').on('click', function(e){
		removeAllActiveTemplate();
		$(this).parent().parent().parent().addClass('active');
		$(this).parent().addClass('active');
		var currSelected = $('.selected-tmplteOptn').children()[0];
		if(currSelected){
			currSelected = $(currSelected).detach();
			currSelected.appendTo('.unselected-tmplteOptn');
		}
		var id = $(this)[0].id.split('#')[1];
		var newSelected = $('.'+id).detach();
		newSelected.appendTo('.selected-tmplteOptn');
		e.preventDefault(); // cancel the link itself
	});
// jQuery to handle mcq activity End

// jQuery to handle yes/no activity Start
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
// jQuery to handle yes/no activity End
});

//Handle adding a topic to the selected-topics container Start

$(document).on('click', '.addTopic', function() {
	var topicId = $(this)[0].id.split('_')[1];
	var topicName = $(this)[0].name;
	$(this).removeClass('addTopic');
	$(this).removeClass('btn-default');
	$($(this).children()[0]).removeClass('glyphicon-plus')
	$('#topicLabel_'+topicId).removeClass('btn-default');
	$(this).addClass('removeTopic');
	$(this).addClass('btn-info');
	$($(this).children()[0]).addClass('glyphicon-trash');
	$('#topicLabel_'+topicId).addClass('btn-info');
	var topic = '<div class="col-sm-4 form-group" id="topic_'+topicId+'">' +
	'<input type="button" class="form-control btn btn-info btn-block" value="'+topicName+'" />' +
	'<input type="hidden" name="topic_'+topicId+'" value="'+topicName+'" />' +
	'</div>';
	$('.selected-topics').append(topic);
});
//Handle adding a topic to the selected-topics container End

//Handle removing a topic from the selected-topics container Start

$(document).on('click', '.removeTopic', function() {
	var topicId = $(this)[0].id.split('_')[1];
	$(this).removeClass('removeTopic');
	$(this).removeClass('btn-info');
	$($(this).children()[0]).removeClass('glyphicon-trash');
	$('#topicLabel_'+topicId).removeClass('btn-info');
	$(this).addClass('addTopic');
	$(this).addClass('btn-default');
	$($(this).children()[0]).addClass('glyphicon-plus')
	$('#topicLabel_'+topicId).addClass('btn-default');
	var topic = $('#topic_'+topicId)[0];
	if(topic){
		topic = $(topic).detach();
	}
});
//Handle removing a topic from the selected-topics container End

//Handle adding a diagnostic to the selected-related-diagnostics container Start

$(document).on('click', '.addDiagnostic', function() {
	var diagnosticId = $(this)[0].id.split('_')[1];
	var diagnosticTitle = $(this)[0].name;
	$(this).removeClass('addDiagnostic');
	$(this).removeClass('btn-default');
	$($(this).children()[0]).removeClass('glyphicon-plus')
	$('#diagnosticLabel_'+diagnosticId).removeClass('btn-default');
	$(this).addClass('removeDiagnostic');
	$(this).addClass('btn-info');
	$($(this).children()[0]).addClass('glyphicon-trash');
	$('#diagnosticLabel_'+diagnosticId).addClass('btn-info');
	$('.diag-qstn_'+diagnosticId).addClass("alert-info");
	var diagnosticInfo = $("#diagnostic_info_for-"+diagnosticId+" .diag-qstn_"+diagnosticId)[0];
	var tmp = document.createElement("div");
	tmp.appendChild(diagnosticInfo);
	var selectedDiagnostic = '<div class="tile gray" id="selectedDiagnostic_'+diagnosticId+'">' +
	'<input type="button" class="form-control btn btn-info btn-block h2 title" data-toggle="collapse" data-target="#selectedDiagnostic_info_for-'+diagnosticId+'" value="'+diagnosticTitle+'" />' +
	'<div class="panel-collapse collapse in" id="selectedDiagnostic_info_for-'+diagnosticId+'">' +
	tmp.innerHTML +
	'<input type="hidden" name="diagnostic_'+diagnosticId+'"/>' +
	'</div>' +
	'</div>';
	$('.selected-diagnostics').append(selectedDiagnostic);
});
//Handle adding a diagnostic to the selected-related-diagnostics container End

//Handle removing a diagnostic from selected-related-diagnostics container Start

$(document).on('click', '.removeDiagnostic', function() {
	var diagnosticId = $(this)[0].id.split('_')[1];
	$(this).removeClass('removeDiagnostic');
	$(this).removeClass('btn-info');
	$($(this).children()[0]).removeClass('glyphicon-trash');
	$('#diagnosticLabel_'+diagnosticId).removeClass('btn-info');
	$('.diag-qstn_'+diagnosticId).removeClass("alert-info");
	$(this).addClass('addDiagnostic');
	$(this).addClass('btn-default');
	$($(this).children()[0]).addClass('glyphicon-plus')
	$('#diagnosticLabel_'+diagnosticId).addClass('btn-default');
	var selectedDiagnostic = $('#selectedDiagnostic_'+diagnosticId)[0];
	if(selectedDiagnostic){
		selectedDiagnostic = $(selectedDiagnostic).detach();
	}
});
//Handle removing a diagnostic from selected-related-diagnostics container ENds

// Validating the form for adding related diagnostic questions Start

function validateRelatedDiagnostic(form){
	if($('.selected-diagnostics').children().length < 2){
		$('.errorMsg').text('Relate atleast one question to another question.');
		$('.errorMsg').css('display', 'block');
		return false;
	}else if($('.selected-topics').children().length < 1){
		$('.errorMsg').text('Associate atleast one topic to the questions.');
		$('.errorMsg').css('display', 'block');
		return false;
	}else
		return true;
}
// Validating the form for adding related diagnostic questions End

// jQuery to handle mcq activity
function removeAllActiveTemplate(){
	$('.actTemplate').find('li').each(function(){
		$(this).removeClass("active");
// 		var id = $(this).children()[0].id;
// 		if(id != ""){
// 			$('.'+id.split("#")[1]).css("display", "none");
// 		}
	});
}


//mcq template, update checkbox value with the input of user
$(document).on('change', '#mcqOptions .option', function() {
	var num = this.id.split('_')[1];
	$('#mcqOptions #checkBox_'+num).val(this.name);
});

//adding more options inside the mcq template
$(document).on('click', '#mcqMoreOptions', function() {
	var maxVal = $('#mcqMaxOptions').val();
	var nxtVal = parseInt(maxVal)+1;
	$('.mcqOptions').append(
		'<div class="col-sm-4 input-group  mcqOption form-group" id="mcqOption_'+nxtVal+'" > '
			+ '<span class="input-group-addon"> '
			+ '<input type="checkbox" name="correctAnswer" class="chkbx" id="checkBox_'+nxtVal+'" value="option_'+nxtVal+'"/> '
			+ '</span>'
			+ '<input type="text" class="form-control option" name="option_'+nxtVal+'" id="option_'+nxtVal+'" placeholder="Content for this choice" required> '
			+ '<span class="input-group-btn"> '
			+ '<button class="btn btn-default removeOption" id="removeOption_'+nxtVal+'" type="button"><i class="glyphicon glyphicon-trash"></i></button> '
			+ '</span> '
			+ '</div>');
	$('#mcqMaxOptions').val(nxtVal);
	if(nxtVal == 5){
		$('.addOption').css("display", "none");
	}
});

//		mcq template, remove option
$(document).on('click', ".mcqOptions .removeOption", function() {
	var removeId = this.id.split("_")[1];
	$('.mcqOptions #mcqOption_'+removeId).remove();
	var maxVal = $('#mcqMaxOptions').val();
	var newVal = parseInt(maxVal)-1;
	var initVal = 0;
	$(".mcqOptions .mcqOption").each(function() {
		initVal = initVal +1;
		this.id = "mcqOption_"+initVal;
		$('#'+this.id+' .chkbx')[0].id = "checkBox_"+initVal;
		$('#'+this.id+' .option')[0].id = "option_"+initVal;
		$('#'+this.id+' .option')[0].name = "option_"+initVal;
		$('#'+this.id+' .removeOption')[0].id = "removeOption_"+initVal;
	});
	$('.addOption').css("display", "block");
	$('#mcqMaxOptions').val(newVal);
});
// jQuery to handle mcq activity End

// jQuery to handle video activity Start

// 	Image upload messages
function imageUploadMsg() {
	showUploadMsg("imageFile", "imageUploadMsg");
}
	
// 	Video upload messages
function videoUploadMsg() {
	showUploadMsg("videoFile", "videoUploadMsg");
}
	
// 	Flip Card 1 upload messages
function card1UploadMsg() {
	showUploadMsg("card1File", "card1UploadMsg");
}
// 	Flip Card 2 upload messages
function card2UploadMsg() {
	showUploadMsg("card2File", "card2UploadMsg");
}
// 	Flip Card 3 upload messages
function card3UploadMsg() {
	showUploadMsg("card3File", "card3UploadMsg");
}
	
// 	Common upload message
function showUploadMsg(id, msgContainerId){
	var fileHolder = document.getElementById(id);
    var msg = "";
    if ('files' in fileHolder) {
        if (fileHolder.files.length == 0) {
            msg = "Browse from computer";
        } else {
            var file = fileHolder.files[0];
            if ('name'	 in file) {
                msg += "File Name: " + file.name + "<br>";
            }
            if ('size' in file) {
                msg += "File Size: " + file.size + " bytes <br>";
            }
        }
    } else {
        if (fileHolder.value == "") {
            msg += "Browse from computer";
        } else {
            msg += "The files property is not supported by your browser!";
            msg += "<br>The path of the selected file: " + fileHolder.value;
        }
    }
    document.getElementById(msgContainerId).innerHTML = msg;
}
// jQuery to handle video activity ENd

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
