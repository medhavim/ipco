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
		newSelected.appendTo('.selected-tmplteOptn');
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
