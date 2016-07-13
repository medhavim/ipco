$(function() {

    $('#login-form-link').click(function(e) {
    	hideValidations();
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		hideValidations();
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
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
	
});

$(document).ready(function() {
	hideValidations();
	
	if ($("#errorMsg")[0].value == 'usernameerr') {
		$('.errorMsg').text('Incorrect username or password.');
		$('.errorMsg').css('display', 'block');
	}
	
//	Check if the email entered while registering is unique
	$("#registerEmail").on('change', function() {
		email = $('#registerEmail').val();
		$('.email-check').css('display', 'block');
		$.ajax({
			type : "POST",
			url : "checkEmail.action",
			data : "email=" + email,
			success : function(data) {
				$('.email-check').css('display', 'none');
				if(data == "false"){
					$('.errorMsg').html('Email already exists: '+email);
					$('.errorMsg').css('display', 'block');
					$('#registerEmail').val('')
					$('.email-check').parent().parent().removeClass('has-success');
					$("#registerEmail").parent().addClass('has-error');
				}else{
					$('.email-check').parent().parent().removeClass('has-error');
					$("#registerEmail").parent().addClass('has-success')
				}
			}
		})
	});
	
//	Check if the username entered while registering is unique
	$("#registerUsername").on('change', function() {
		username = $('#registerUsername').val();
		$('.username-check').css('display', 'block');
		$.ajax({
			type : "POST",
			url : "checkUsername.action",
			data : "username=" + username,
			success : function(data) {
				$('.username-check').css('display', 'none');
				if(data == "false"){
					$('.errorMsg').text('Username already exists: '+username);
					$('.errorMsg').css('display', 'block');
					$('#registerUsername').val('')
					$('.username-check').parent().parent().removeClass('has-success');
					$("#registerUsername").parent().addClass('has-error');
				}else{
					$('.username-check').parent().parent().removeClass('has-error');
					$("#registerUsername").parent().addClass('has-success')
				}
			}
		})
	});

//	Check the password to be 8 characters
	$("#registerPassword").on('change', function() {
		password = $('#registerPassword').val().trim();
		if(password.length < 8){
			$('.errorMsg').text('The password should be atleast 8 characters long.');
			$('.errorMsg').css('display', 'block');
			$('#registerPassword').parent().removeClass('has-success');
			$("#registerPassword").parent().addClass('has-error');
		}else{
			$('.errorMsg').css('display', 'none');
			$('#registerPassword').parent().removeClass('has-error');
			$("#registerPassword").parent().addClass('has-success');
		}
	});

//	Check the confirm password same as password
	$("#registerConfirm-password").on('change', function() {
		password = $('#registerPassword').val().trim();
		confirmPassword = $('#registerConfirm-password').val().trim();
		if(password != confirmPassword){
			$('.errorMsg').text('Both the passwords do not match.');
			$('.errorMsg').css('display', 'block');
			$('#registerConfirm-password').parent().removeClass('has-success');
			$("#registerConfirm-password").parent().addClass('has-error');
		}else{
			$('.errorMsg').css('display', 'none');
			$('#registerConfirm-password').parent().removeClass('has-error');
			$("#registerConfirm-password").parent().addClass('has-success');
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
