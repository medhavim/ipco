$(function() {

    $('#login-form-link').click(function(e) {
    	hideValidations();
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#forgot-password').click(function(e) {
		$('#login-view').addClass('hide');
		$('#login-form-view').addClass('hide');
		$('#reset-view').removeClass('hide');
		$('#reset-form-view').removeClass('hide');
		$("#email-form").delay(100).fadeIn(100);
 		$("#reset-form").fadeOut(100);
		$('#reset-form-link').removeClass('active');
		$('#email-form-link').addClass('active');
		e.preventDefault();
	});
	
	$('.cancel-reset').click(function(e) {
		$('#reset-view').addClass('hide');
		$('#reset-form-view').addClass('hide');
		$('#login-view').removeClass('hide');
		$('#login-form-view').removeClass('hide');
		$("#login-form").delay(100).fadeIn(100);
		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$('#login-form-link').addClass('active');
		e.preventDefault();
	});
	
	$('#email-form-link').click(function(e) {
		hideValidations();
		$("#email-form").delay(100).fadeIn(100);
		$("#reset-form").fadeOut(100);
		$('#reset-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
});

$(document).ready(function() {
	hideValidations();
	
	if ($("#errorMsg")[0].value == 'usernameerr') {
		$('.errorMsg').text('Incorrect username or password.');
		$('.errorMsg').css('display', 'block');
	}
	
//	Check if the email entered while reseting the credentials exists
	$("#reset-next").on('click', function(e) {
		email = $('#resetEmail').val();
		$.ajax({
			type : "POST",
			url : "checkAdminEmailReset.action",
			data : "email=" + email,
			success : function(data) {
				if(data == "false"){
					$('.errorMsg').html('The email is not registered: '+email);
					$('.errorMsg').css('display', 'block');
					$('#resetEmail').val('')
					$("#resetEmail").parent().removeClass('has-success');
					$("#resetEmail").parent().addClass('has-error');
					e.preventDefault();
				}else{
					$('#reset-credentialId').val(data.split(" ")[0]);
					$('#resetUsername').val(data.split(" ")[1]);
					hideValidations();
					$("#reset-form").delay(100).fadeIn(100);
					$("#email-form").fadeOut(100);
					$('#email-form-link').removeClass('active');
					$('#reset-form-link').addClass('active');
					e.preventDefault();
				}
			}
		});
	});

//	Check the password to be 8 characters while resetting
	$("#resetPassword").on('change', function() {
		password = $('#resetPassword').val().trim();
		if(password.length < 8){
			$('.errorMsg').text('The password should be atleast 8 characters long.');
			$('.errorMsg').css('display', 'block');
			$('#resetPassword').parent().removeClass('has-success');
			$("#resetPassword").parent().addClass('has-error');
		}else{
			$('.errorMsg').css('display', 'none');
			$('#resetPassword').parent().removeClass('has-error');
			$("#resetPassword").parent().addClass('has-success');
		}
	});

//	Check the confirm password same as password while resetting
	$("#resetConfirm-password").on('change', function() {
		password = $('#resetPassword').val().trim();
		confirmPassword = $('#resetConfirm-password').val().trim();
		if(password != confirmPassword){
			$('.errorMsg').text('Both the passwords do not match.');
			$('.errorMsg').css('display', 'block');
			$('#resetConfirm-password').parent().removeClass('has-success');
			$("#resetConfirm-password").parent().addClass('has-error');
		}else{
			$('.errorMsg').css('display', 'none');
			$('#resetConfirm-password').parent().removeClass('has-error');
			$("#resetConfirm-password").parent().addClass('has-success');
		}
	});

	
});


function hideValidations(){
	$('.errorMsg').innerHTML = '';
	$('.errorMsg').css('display', 'none');
	$('.email-check').css('display', 'none');
	$('.username-check').css('display', 'none');
}

function validateRegister(formId){
	var result = true;
	$.each($('#'+formId)[0].elements, function(){
		if($(this).parent().hasClass('has-error')){
			result = false;
		}
	});
	return result;
}
