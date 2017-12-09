var reg_obj = { user_id:"",password:"",email:""};


function bindListener(){
	$('#a_login').click(function() {
		$('#login_Modal').css('display', "block");
		document.getElementById("defaultOpen").click();
		stateChange(true, '#login_account');
		stateChange(true, '#login_password');
	});

	// When the user clicks on <span> (x), close the modal
	$('#close_login').click(function() {
		$('#login_Modal').css('display', "none");
	});

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
//		var modal = document.getElementById('login_Modal');
//		if (event.target == modal) {
//			$('#login_Modal').css('display', "none");
//		}
	}
	
	$('#btn_login').click(function() {
		
		var aflag = false;
		var bflag = false
		
		if ($('#login_account').val().length > 0 && number_regex.test($('#login_account').val())) {		
			stateChange(true, '#login_account');
			aflag = true;
		} else {
			stateChange(false, '#login_account', "請輸入數字");
			aflag = false
		}
		
		if ($('#login_password').val().length > 0 && number_regex.test($('#login_password').val())) {		
			stateChange(true, '#login_password');
			bflag = true;
		} else {
			stateChange(false, '#login_password', "請輸入數字");
			bflag = false
		}		
		
		if(aflag && bflag){
			$.when(ajax_login()).done(function(data) {
				
			});			
		}
	});
	
	$('#btn_reg').click(function(){
		var aflag = false;
		var bflag = false;
		var cflag = false;
		
		if ($('#reg_id').val().length > 0 && engnum_regex.test($('#reg_id').val())) {		
			stateChange(true, '#reg_id');
			reg_obj.user_id = $('#reg_id').val();
			aflag = true;
		} else {
			stateChange(false, '#reg_id', "請輸入英文或數字");
			aflag = false
		}
		
		if ($('#reg_pw').val().length > 0 && engnum_regex.test($('#reg_pw').val())) {		
			stateChange(true, '#reg_pw');

		} else {
			stateChange(false, '#reg_pw', "請輸入英文或數字");
		}
		
		if ($('#reg_pw_re').val().length > 0 && $('#reg_pw_re').val() == $('#reg_pw').val()) {		
			stateChange(true, '#reg_pw_re');
			reg_obj.password = $('#reg_pw').val();
			bflag = true;
		} else {
			stateChange(false, '#reg_pw_re', "請輸入相同的密碼");
			bflag = false
		}
		
		if ($('#reg_email').val().length > 0 && email_regex.test($('#reg_email').val())) {		
			stateChange(true, '#reg_email');
			reg_obj.email = $('#reg_email').val();
			cflag = true;
		} else {
			stateChange(false, '#reg_email', "請輸入正確的email格式");
			cflag = false
		}
		
		
		if(aflag && bflag && cflag){
			$.when(ajax_reg()).done(function(data) {
				alert("恭喜你註冊成功" + data.user_id);
			});			
		}
		
	})
	
}




function ajax_login(){
	return $.ajax({
		type: "GET",
		url: "WebAPI/member/login",
		dataType: "json"
	});
}

function ajax_reg(){
	return $.ajax({
		type: 'POST',
		url: "WebAPI/User/Create",
		contentType: 'application/json; charset=UTF-8',
		data:JSON.stringify(reg_obj),
		dataType: "json"
	});
}


//資料驗證區(正規表達式)---------
number_regex = /^\d+$/;
engnum_regex = /[a-zA-Z0-9]/;
years_regex = /^([1-9]?\d|100)$/;
email_regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
function stateChange(state, objID, msg) {
	if (state) {
		// $(objID).parent().removeClass('has-error').addClass('has-success');
		$(objID).removeClass('w3-border w3-border-red');
		$(objID).prev('span').html("");
	} else {
		$(objID).addClass('w3-border w3-border-red');
		$(objID).prev('span').html(msg).addClass('w3-text-red');
	}
}
// ---------------