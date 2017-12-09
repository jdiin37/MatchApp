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
		var modal = document.getElementById('login_Modal');
		if (event.target == modal) {
			$('#login_Modal').css('display', "none");
		}
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
}




function ajax_login(){
	return $.ajax({
		method: "GET",
		url: "WebAPI/member/login",
		dataType: "json"
	});
}


//資料驗證區(正規表達式)---------
number_regex = /^\d+$/;
years_regex = /^([1-9]?\d|100)$/;
function stateChange(state, objID, msg) {
	if (state) {
		// $(objID).parent().removeClass('has-error').addClass('has-success');
		$(objID).removeClass('w3-border w3-border-red');
		$(objID).next().html("");
	} else {
		$(objID).addClass('w3-border w3-border-red');
		$(objID).next().html(msg).addClass('w3-text-red');
	}
}
// ---------------