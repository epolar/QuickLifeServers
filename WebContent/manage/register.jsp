<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加盟快来</title>
<jsp:include page="../head.html"></jsp:include>
<!-- 导入百度地图API -->
<script src="http://api.map.baidu.com/api?v=2.0&ak=4240c46004ed8c3857422ff59dd1d221"></script>
<script src="../js/map_api_encapsulation.js"></script>
<!-- 本页面独有的css和js -->
<link href="../css/register.css" rel="stylesheet"/>
<script src="../js/register.js"></script>
</head>
<body>
	<div class="content_div">
		<fieldset>
			<legend><img alt="快来" src="../images/logo_text.png"/></legend>
			<img alt="上一步" class="prev_step_button" src="../images/prev.png" onClick="prevStep()"/>
			<img alt="下一步" class="next_step_button" src="../images/next.png" onClick="nextStep()"/>
			<div id="step1">
				<label class="input_label" for="username">用&nbsp;户&nbsp;名：</label>
				<input type="text" class="input_view" id="username" onBlur="checkUsername()"/>
				<br/>
				<label class="validate_msg" id="username_validate"></label>
				<br/>
				<label class="input_label" for="business_name">商&nbsp;户&nbsp;名：</label>
				<input type="text" class="input_view" id="business_name" onBlur="checkBusinessName()"/>
				<br/>
				<label class="validate_msg" id="business_validate"></label>
				<br/>
				<label class="input_label" for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				<input type="password" class="input_view" id="password" onBlur="checkPassword()"/>
				<br/>
				<label class="validate_msg" id="password_validate"></label>
				<br/>
				<label class="input_label" for="repassword">重复密码：</label>
				<input type="password" class="input_view" id="repassword" onBlur="checkRepassword()"/>
				<br/>
				<label class="validate_msg" id="repassword_validate"></label>
				<br/>
				<label class="input_label" for="type">行&nbsp;&nbsp;&nbsp;&nbsp;业：</label>
				<select class="input_view" id="type"></select>
				<br/>
				<label class="validate_msg" id="type_validate"></label>
				<br/>
				<label class="input_label" for="phone">电&nbsp;&nbsp;&nbsp;&nbsp;话：</label>
				<input type="text" class="input_view" id="phone" onBlur="checkPhone()"/>
				<br/>
				<label class="validate_msg" id="phone_validate"></label>
				<br/>
			</div>
			<div id="step2">
				<input class="input_view" type="file" id="logo_upload" onChange="image_prev('prevView', 'logo_upload')"/><br/>
				<img class="prevview" id="prevView" src="../images/default_business_logo.png">
			</div>
			<div id="step3">
				<label class="input_label" for="address">地址：</label>
				<input class="input_view" id="address" type="text"/>
				<button class="button" onClick="findAddress()">确定</button>
				<div id="map"></div>
			</div>
			<div id="step4">
				<label class="input_label" for="homepage">网址：</label>
				<input class="input_view" id="homepage" type="text"/>
				<br/>
				<label class="input_label" for="detail">详细介绍：</label>
				<br/>
				<textarea rows="15" cols="60" id="detail"></textarea>
			</div>
			<div id="step5">
				<div id="registering">
					<img id="loading" src="../images/ajax-loader_blue.gif" alt="请稍候"/>
					<h2 id="loading_text" style="margin-top: 30px">正在注册，请稍候</h2>
				</div>
				<div id="register_success">
					<h2>注册成功</h2>
					<a href="main.jsp" id="forward_main">
					<span id="forward_second">5</span>秒后跳转到管理页面，如果没有跳转请手动点击</a>
				</div>
				<div id="register_fail">
					<h2>注册失败，请尝试重新注册</h2>
					<button id="register_again" class="button" onClick="register_again()">重新注册</button>
				</div>
			</div>
		</fieldset>
		<div id="step_position">
			<div id="step1_position">
				<textarea rows="3" cols="10" id="step1_explain" readonly="readonly" onfocus="blur()">填写必需的资料，点击右上角的小箭头继续！</textarea>
				<label class="position">1/5</label>
			</div>
			<div id="step2_position" class="notNow">
				<textarea rows="3" cols="10" id="step2_explain" readonly="readonly" onfocus="blur()">选择你的Logo，使用矩形图片以达到最佳效果！</textarea>
				<label class="position">2/5</label>
			</div>
			<div id="step3_position" class="notNow">
				<textarea rows="3" cols="10" id="step3_explain" readonly="readonly" onfocus="blur()">告诉大家你的店铺在哪儿，在地图上把它点出来！</textarea>
				<label class="position">3/5</label>
			</div>
			<div id="step4_position" class="notNow">
				<textarea rows="3" cols="10" id="step4_explain" readonly="readonly" onfocus="blur()">让大家知道更多关于你的店铺的信息吧！</textarea>
				<label class="position">4/5</label>
			</div>
			<div id="step5_position" class="notNow">
				<textarea rows="3" cols="10" id="finish_explian" readonly="readonly" onfocus="blur()">等待注册完成，稍后你就可以发布你店铺的优惠啦！</textarea>
				<label class="position">5/5</label>
			</div>
		</div>
	</div>
</body>
</html>