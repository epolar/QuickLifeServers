<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>快来后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="cache-control" content="no-store"/>
<meta http-equiv="expires" content="0"/>
-->
<link href="images/logo.png" rel="shortcut icon" />
<link href="css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
<script src="js/jquery-1.9.1.js"></script>
<script src="js/jquery-ui-1.10.3.custom.js"></script>
<script src="js/hex-md5.js"></script>

<script>

	$(function() {
		$(".button").button();
		$(".reset").click(reset);
	});
	
	function reset() {
		$(this).parents(".login_form").find(".input_view").val("");
	}
	
	function check() {
		if ($("#username").val().trim().length == 0) {
			$("#username").focus();
			$("#username").parent().effect("pulsate");
			return false;
		}
		if ($("#password").val().trim().length == 0) {
			$("#password").focus();
			$("#password").parent().effect("pulsate");
			return false;
		}
		if ($("#validate_code").val().trim().length == 4) {
			// 验证码验证
			$.post("validateCode", {'validateCode':$("#validate_code").val()}, function(data) {
				if (data.validateCode == false) {
					$("#validate_code").focus();
					$("#validate_code").parent().effect("pulsate");
				} else {
					// 验证码通过，服务器验证
					submit();
				}
			}, "JSON");
		} else {
			$("#validate_code").focus();
			$("#validate_code").parent().effect("pulsate");
			return false;
		}
		
	}
	
	function submit() {
		// 密码进行 md5 加密
		$("#password").val(hex_md5($("#password").val()));
		// 异步登陆，登陆完成才进行页面跳转
		$.post("login.action", 
				{"userName" : $("#username").val(), "password" : $("#password").val(), "validate" : $("#validate_code").val()},
				function(data) {
					if (data.login == true) {
						window.location=data.url;
						window.forward();
					} else {
						$("#password").val("");
						$("#login_content").effect("shake");
					}
					flushValidateCode();
				}, "JSON");
	}
	
	function flushValidateCode() {
		$("#img_vc").attr("src", "validateCode?t=" + new Date().getMilliseconds());
	}
	
</script>

<style>
	body {
		background-image: url("images/login_background.png");
		background-repeat: no-repeat;
	}
	
	.content {
		background-image: url("images/login_frame.png");
		width: 590px;
		height: 510px;
		position: absolute;
		left: 50%;
		top: 50%;
		margin-left: -295px;
		margin-top: -250px;
		text-align: center;
	}
	
	.logo {
		margin-top: 18px;
	}
	
	.login_form {
		margin-top: 10px;
	}
	
	.input_view {
		width: 10em;
		background: url("images/input_view.png");
		background-position: bottom;
		background-repeat: repeat-x;
		border: none;
		font-size: x-large;
		font-weight: bold;
	}
	
	.input_label {
		width: 100px;
		text-align: right;
		background: transparent;
		border: none;
		font-size: x-large;
		font-weight: bolder;
		font-family: "雅黑" serif;;
		color: black;
		display: inline;
	}
	
	.login_form div {
		margin-top: 30px
	}
	
	.login_form span {
		float: right;
		margin-right: 100px;
	}
	
	.forget_pwd {
		float:left; 
		margin-left: 150px; 
		margin-top: 15px; 
		font-size: large;
	}
	
	.join_in {
		float:right; 
		margin-right:150px; 
		margin-top: 15px; 
		font-size: large;
	}
	
	#img_vc {
		width: 7em;
		height: 1.7em;
		position: relative;
		top: 8px;
	}
	
	a {
		text-decoration: none;
	}
	
}
	
</style>
</head>
<body>
	<div class="content" id="login_content">
		<img class="logo" src="images/logo_text.png">
		<div class="login_form">
			<div>
				<div class="input_label">用户名：</div>
				<input class="input_view" id="username" title="请输入用户名" type="text" maxlength="15"/>
			</div>
			<div>
				<div class="input_label" >密&nbsp;&nbsp;码：</div>
				<input class="input_view" id="password" title="请输入密码" type="password" maxlength="20"/>
			</div>
			<div>
				<div class="input_label" >验证码：</div>
				<input class="input_view" id="validate_code" title="请输入右边的字母或数字" type="text" maxlength="4" style="width: 5em;"/>
				<img id="img_vc" alt="验证码丢了" src="validateCode" title="刷新验证码" onClick="flushValidateCode()"/>
			</div>
			<div>
				<input class="button reset" type="button" value="重置" style="float: left; margin-left: 150px"/>
				&nbsp;&nbsp;&nbsp;<input class="button" type="button" value="登陆" style="float: right; margin-right: 150px" onClick="check()"/>
			</div>
			<div>
				<a href="#" class="forget_pwd">忘记密码</a>
				<a href="#" class="join_in">加盟快来</a>
			</div>
		</div>
	</div>
</body>
</html>