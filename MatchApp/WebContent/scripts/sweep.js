var reg_obj = { user_id:"",password:"",email:""};

var login_obj = { user_id:"",password:"",email:""};

var post_obj = { user_id:"",location:"",location_desc:"",demand_desc:"",fee:""};

//dropdown
function menuDropdown() {
    var x = document.getElementById("menu_user");
    if (x.className.indexOf("w3-show") == -1) {  
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}


//PageInitial
function PageInitial(page){
	switch(page) {
	case "mainPage":
		break;
	case "postPage":
		if(checkisLogin()){
			$('#btn_post').show();
			$('#post_status').html("");
			$.when(ajax_getLocation()).done(function(data){
				$('#select_location').html("").append('<option value="" disabled selected>請選擇地區</option>');
				$.each(data,function(index,obj){
					$('#select_location').append('<option value="'+obj.location+'">' +obj.location +'</option>')
				});
			});
		}else{
			$('#btn_post').hide();
			$('#post_status').html("").html("請先登入");
		}
		break;	
	case "findPage":
		break;	
	}
}

/**取得 URL後面的參數***/
var getQueryVariable = function(variable) {
	 var query = window.location.search.substring(1);
	 var vars = query.split("&");
	 for (var i = 0; i < vars.length; i++) {
	  var pair = vars[i].split("=");
	  if (pair[0] == variable) {
	   return pair[1];
	  }
	 }
	 return (false);
	}

//Time

var myDay = new Date();
var expireTime = myDay.setMinutes(myDay.getMinutes() + 30);

function myFunctionTest() {
    var minutes = 1000 * 60;
    var hours = minutes * 60;
    var days = hours * 24;
    var years = days * 365;
    var d = new Date();
    var t= d.getTime();

    var y = Math.round(t / years);

    document.getElementById("demo").innerHTML = y;
}
//cookie

function setCookie_user(user_id) {
//    var d = new Date();
//    var minutes = 30;
//    d.setTime(d.getTime() +  (minutes * 60 * 1000));
//    var expires = "expires=" + d.toUTCString();
//    document.cookie = "user_id=" + user_id + ";" + expires ;
    var date = new Date();
    var minutes = 30;
    date.setTime(date.getTime() + (minutes * 60 * 1000));
    $.cookie("user_id", user_id, { expires: date });
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
//lock btn
function lock_btn(obj){
	$(obj).prop('disabled', true);
	$(obj).css('cursor','wait');
}

function unlock_btn(obj){
	$(obj).prop('disabled', false);
	$(obj).css('cursor','');
}


//post click function
function clickPost(element){
	
	post_obj.user_id = getCookie("user_id");
	post_obj.location = $('#select_location').val();
	
	if ($('#post_location_desc').val().length > 0 && $('#post_location_desc').val().length < 60) {		
		stateChange(true, '#post_location_desc');
		post_obj.location_desc = $('#post_location_desc').val();
	} else if($('#post_location_desc').val().length >= 60){
		stateChange(false, '#post_location_desc', "請勿超過60字");		
		return false;
	} else {
		stateChange(false, '#post_location_desc', "請輸入地址");		
		return false;
	}	
	
	
	if ($('#post_demand_desc').val().length > 0 && $('#post_demand_desc').val().length < 200) {		
		stateChange(true, '#post_demand_desc');
		post_obj.demand_desc = $('#post_demand_desc').val();
	} else if($('#post_demand_desc').val().length >= 200){
		stateChange(false, '#post_demand_desc', "請勿超過200字");		
		return false;
	} else {
		stateChange(false, '#post_demand_desc', "請輸入需求描述");		
		return false;
	}
	
	if ($('#post_fee').val().length > 0 && number_regex.test($('#post_fee').val())) {		
		stateChange(true, '#post_fee');
		post_obj.fee = $('#post_fee').val();
	} else {
		stateChange(false, '#post_fee', "請輸入數字");		
		return false;
	}
	
	lock_btn(element);
	//alert(JSON.stringify(post_obj));
	setTimeout(function(){
		$.when(ajax_CrePost()).done(function(data) {		
			unlock_btn(element);
			if(data.id == undefined){
				alert("發佈失敗,請稍後在試");
			}else{
				window.location.href = "/MatchApp/PostOK.html?postid=" + data.id;
			}
		});
	},500);
	
}
//login click function
function clickLogin(element){	
	if ($('#login_id').val().length > 0 && engnum_regex.test($('#login_id').val())) {		
		stateChange(true, '#login_id');
		login_obj.user_id = $('#login_id').val();
	} else {
		stateChange(false, '#login_id', "請輸入英文或數字");
		return false;
	}
	
	if ($('#login_pw').val().length > 0 && engnum_regex.test($('#login_pw').val())) {		
		stateChange(true, '#login_pw');
		login_obj.password = $('#login_pw').val();
	} else {
		stateChange(false, '#login_pw', "請輸入英文或數字");
		return false;
	}		
	
	lock_btn(element);
	$.when(ajax_login()).done(function(data) {
		unlock_btn(element);
		if(data.user_id == undefined){
			$('#login_status').html("帳號密碼有誤");
		}else{
			login_obj = data;
			loginOK();
		}
	});			
	
};

function clickReg(element){	
	
	if ($('#reg_id').val().length > 4 && engnum_regex.test($('#reg_id').val())) {		
		stateChange(true, '#reg_id');
		reg_obj.user_id = $('#reg_id').val();
	} else {
		stateChange(false, '#reg_id', "請輸入英文或數字,且長度至少四碼");
		return false;
	}
	
	if ($('#reg_pw').val().length > 4 && engnum_regex.test($('#reg_pw').val())) {		
		stateChange(true, '#reg_pw');

	} else {
		stateChange(false, '#reg_pw', "請輸入英文或數字,且長度至少四碼");
		return false;
	}
	
	if ($('#reg_pw_re').val().length > 0 && $('#reg_pw_re').val() == $('#reg_pw').val()) {		
		stateChange(true, '#reg_pw_re');
		reg_obj.password = $('#reg_pw').val();
	} else {
		stateChange(false, '#reg_pw_re', "請輸入相同的密碼");
		return false;
	}
	
	if ($('#reg_email').val().length > 0 && email_regex.test($('#reg_email').val())) {		
		stateChange(true, '#reg_email');
		reg_obj.email = $('#reg_email').val();
	} else {
		stateChange(false, '#reg_email', "請輸入正確的email格式");
		return false;
	}
	
	lock_btn(element);
	$.when(ajax_reg()).done(function(data) {
		unlock_btn(element);
		if(data.user_id == undefined){
			$('#reg_status').html("此帳號已有人使用");
		}else{
			$('#reg_status').html("恭喜你註冊成功:" + data.user_id + ",請至登入頁面登入").css('color','green');
			stateChange(true, '#reg_id',"","");
			stateChange(true, '#reg_pw',"","");
			stateChange(true, '#reg_pw_re',"","");
			stateChange(true, '#reg_email',"","");
		}
	});			
	
}



//bind

function bindListener(){
//	$('#menu_user,#a_loginOK').mouseleave(function(){
//		var x = document.getElementById("menu_user");
//		x.className = x.className.replace(" w3-show", "");
//	});
	
	$('#a_login').click(function() {
		iniLoginTab();
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
		//var x = document.getElementById("menu_user");
		//alert(event.target);
//		if (event.target == x && event.target != y) {
//			x.className = x.className.replace(" w3-show", "");
//		}
	}
	
	
	
	//input change login
	$('#login_id').change(function(){	
		if ($('#login_id').val().length > 0 && engnum_regex.test($('#login_id').val())) {		
			stateChange(true, '#login_id');
		} else {
			stateChange(false, '#login_id', "請輸入英文或數字");
		}
	});	
	
	$('#login_pw').change(function(){	
		if ($('#login_pw').val().length > 0 && engnum_regex.test($('#login_pw').val())) {		
			stateChange(true, '#login_pw');
		} else {
			stateChange(false, '#login_pw', "請輸入英文或數字");
		}		
	});		
			
	//input change reg
	$('#reg_id').change(function(){	
		if($('#reg_id').val().length > 0 && engnum_regex.test($('#reg_id').val())) {		
			stateChange(true, '#reg_id');
			reg_obj.user_id = $('#reg_id').val();
		} else {
			stateChange(false, '#reg_id', "請輸入英文或數字");
		}
	});	
	
	$('#reg_pw').change(function(){	
		if ($('#reg_pw').val().length > 0 && engnum_regex.test($('#reg_pw').val())) {		
			stateChange(true, '#reg_pw');
			if ($('#reg_pw_re').val().length > 0 && $('#reg_pw_re').val() == $('#reg_pw').val()) {		
				stateChange(true, '#reg_pw_re');
				reg_obj.password = $('#reg_pw').val();
			} else {
				stateChange(false, '#reg_pw_re', "請輸入相同的密碼");

			}			
		} else {
			stateChange(false, '#reg_pw', "請輸入英文或數字");
		}
	});
	
	$('#reg_pw_re').change(function(){	
		if ($('#reg_pw_re').val().length > 0 && $('#reg_pw_re').val() == $('#reg_pw').val()) {		
			stateChange(true, '#reg_pw_re');
			reg_obj.password = $('#reg_pw').val();
		} else {
			stateChange(false, '#reg_pw_re', "請輸入相同的密碼");

		}
	});
	
	$('#reg_email').change(function(){	
		if ($('#reg_email').val().length > 0 && email_regex.test($('#reg_email').val())) {		
			stateChange(true, '#reg_email');
			reg_obj.email = $('#reg_email').val();
		} else {
			stateChange(false, '#reg_email', "請輸入正確的email格式");
		}
	});
	
	
	
}
//iniLoginTab
function iniLoginTab(){
	$('#login_Modal').css('display', "block");
	document.getElementById("defaultOpen").click();
	stateChange(true, '#login_id',"","");
	stateChange(true, '#login_pw',"","");
	stateChange(true, '#reg_id',"","");
	stateChange(true, '#reg_pw',"","");
	stateChange(true, '#reg_pw_re',"","");
	stateChange(true, '#reg_email',"","");
	$('#login_status').html("");
	$('#reg_status').html("");
}

// checkisLogin
function checkisLogin(){	
	if(getCookie("user_id") != ""){
		iniLoginOK();
		return true;
	}else{
		iniLoginOut();
		return false;
	};

}
//loginOK
function loginOK(){
	$('#login_status').html("歡迎回來" + login_obj.user_id + ",3秒後自動回到主頁面..").css('color','green');					
	setTimeout(function(){window.location.href = "/MatchApp"},3000);
	setCookie_user(login_obj.user_id);	
}

function iniLoginOK(){
	$('#span_user_id').html(getCookie("user_id"));	
	$('#a_login').hide();
	$('#a_loginOK').show();
}

//loginOut
function loginOut(){
	setCookie_user("");
	iniLoginOut();
	window.location.href = "/MatchApp";
}
function iniLoginOut(){
	$('#span_user_id').html("");
	$('#a_login').show();
	$('#a_loginOK').hide();	
}

//ajax

function ajax_login(){
	return $.ajax({
		type: "POST",
		url: "WebAPI/User/Login",
		contentType: 'application/json; charset=UTF-8',
		data:JSON.stringify(login_obj),
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

function ajax_CrePost(){
	return $.ajax({
		type: 'POST',
		url: "WebAPI/Post/CrePost",
		contentType: 'application/json; charset=UTF-8',
		data:JSON.stringify(post_obj),
		dataType: "json"
	});
}

function ajax_getLocation(){
	return $.ajax({
		type: 'GET',
		url: "WebAPI/Utility/GetLocation",
		contentType: 'application/json; charset=UTF-8',
		dataType: "json"
	});
}

//資料驗證區(正規表達式)---------
number_regex = /^\d+$/;
engnum_regex = /[a-zA-Z0-9]/;
years_regex = /^([1-9]?\d|100)$/;
email_regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
function stateChange(state, objID, msg,inival) {
	if (state) {
		// $(objID).parent().removeClass('has-error').addClass('has-success');
		$(objID).removeClass('w3-border-red');
		$(objID).prev('span').html("");
		if(inival != undefined){
			$(objID).val(inival);
		};
	} else {
		$(objID).addClass('w3-border w3-border-red');
		$(objID).prev('span').html(msg).addClass('w3-text-red');
	}
}
// ---------------