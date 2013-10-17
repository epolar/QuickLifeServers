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
<jsp:include page="head.html"></jsp:include>

<script>

	$(function() {
		$(".input_view").keypress(function(event) {
			if (event.keyCode == 13) {
				event.preventDefault();
				if (this.id == "validate_code") {
					$("#submit").trigger("click");
				} else {
					$(".input_view")[$(".input_view").index(this) + 1].focus();
				}
			}
		});
	});
	
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
			checkValidateCode(submit, validateCodeFalse);
		} else {
			validateCodeFalse();
			return false;
		}
		
	}
		
	function validateCodeFalse() {
		$("#validate_code").focus();
		$("#validate_code").parent().effect("pulsate");
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
	
</script>

<style>
	body {
		background-image: url("images/login_background.png");
		background-repeat: no-repeat;
	}
	
	.content {
		background-image: url("images/login_frame.png");
		width: 410px;
		height: 356px;
		position: absolute;
		left: 50%;
		top: 50%;
		margin-left: -205px;
		margin-top: -178px;
		text-align: center;
	}
	
	.input_view {
		width: 10em;
	}
	
	.input_label {
		width: 100px;
	}
	
	.logo {
		margin-top: 25px;
		width: 150px;
	}
	
	.login_form {
		margin-top: 10px;
	}
	
	.login_form div {
		margin-top: 13px
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
				<img id="img_vc" alt="验证码丢了" src="validateCode" title="刷新验证码" onClick="flushValidateCode(this)"/>
			</div>
			<div>
				<a class="button" href="">忘记密码</a>
				<button class="button" id="submit" onClick="check()">登陆</button>
				<a href="" class="button">加盟快来</a>
			</div>
		</div>
	</div>
</body>
</html>