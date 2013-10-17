<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加盟快来</title>
<jsp:include page="../head.html"></jsp:include>
<script src="http://api.map.baidu.com/api?v=2.0&ak=4240c46004ed8c3857422ff59dd1d221"></script>

<script>

	var step = 1;
	/**
	* 必需验证的表单数据是否都正确 
	*/
	var parameterValidate = {"username": false, "password": false, "repassword" : false, "business_name": false, "phone" : true};
	/**
	* 百度地图API
	*/
	var map;
	var marker;
	var geo;

	$(function() {
		$(".prev_step_button, #step2, #step3, #step4").hide();
		resizeInvoke();
		$(window).resize(resizeInvoke);
		// 设定类型值
		selectDataObtain();
		// 地图初始化
		initMap();
	});
	
	function initMap() {
		// 初始化百度地图API
		map = new BMap.Map("map");
		// 设定地图位置
		var point = new BMap.Point(119.270185, 26.085099);
		map.centerAndZoom(point, 16);
		// 初始化标注
		marker = new BMap.Marker(point);
		marker.enableDragging();
		marker.addEventListener("dragend", markerEnsure);
		map.addOverlay(marker);
		// 创建地址解析器
		geo = new BMap.Geocoder();
		// 地图点击事件监听
		map.addEventListener("click", addressConfirm);
		map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	}
	
	/**
	* 鼠标点击地址确定
	*/
	function addressConfirm(e) {
		if (e.point) {
			marker.setPosition(e.point);
			markerEnsure();
		}
	}
	
	function markerEnsure() {
		geo.getLocation(marker.getPosition(), function(addr) {
			marker.setTitle(addr.address);
			var opts = {"width" : 130, "title" : "请确定您的公司在这里："};
			var infoWindow = new BMap.InfoWindow(addr.address, opts);
			map.openInfoWindow(infoWindow, marker.getPosition());
			$("#address").val(addr.address);
		});
	}
	
	/**
	* 地址设定
	*/
	function addressSet(e) {
		var addr = $("#address").val().trim();
		if (addr.length > 0) {
			// 解析地址
			geo.getPoint(addr, function(point) {
				if (point) {
					map.centerAndZoom(point, 16);
					marker.setPosition(point);
					markerEnsure();
				}
			}, "福州市");
		}
	}
	
	function submit() {
		if (!canStep2()) {
			alert("请检查所有的步骤是否都是正确的");
			return false;
		}
		geo.getLocation(marker.getPosition(), function(attr) {
			
		});
		togglePosition(5);
	}
	
	/**
	* 在页面大小重新设定的时候调用，主要是为上一步和下一步按钮进行定位
	*/
	function resizeInvoke() {
		var fieldsetWidth = $("fieldset").innerWidth();
		var windowWidth = $(window).innerWidth();
		// 防止窗口小到比注册窗口还小进行的校正
		windowWidth = windowWidth > $("fieldset").outerWidth() + 30 ? 
				windowWidth : ($("fieldset").outerWidth() + $(".prev_step_button").outerWidth() + 10);
		var prevButtonLeft = ((windowWidth - fieldsetWidth) >> 1) + 10;
		var nextButtonLeft = prevButtonLeft  + fieldsetWidth - 20 - $(".next_step_button").width();
		$(".prev_step_button").css("left" , prevButtonLeft + "px");
		$(".next_step_button").css("left", nextButtonLeft + "px");
		$(".map").css($(".prev_step_button").css("left") + 20);
	}
	
	function nextStep() {
		if (step < 4) {
			/*
			if (step == 1) {
				if (!canStep2()) {
					return ;
				}
			}
			*/
			$("#step" + step).hide("blind", {"direction": "left"}, 300);
			step ++;
			$("#step" + step).show("blind", {"direction": "right"}, 300);
			if (step == 4) {
				$(".next_step_button").attr("onClick", "submit()");
				$(".next_step_button").attr("src", "../images/finish.png");
			} else if (step == 2) {
				$(".prev_step_button").show("fade", 500);
			}
			togglePosition(step - 1);
		}
	}
	
	function prevStep() {
		if (step > 1) {
			$("#step" + step).hide("blind", {"direction": "right"}, 300);
			step --;
			$("#step" + step).show("blind", {"direction": "left"}, 300);
			if (step == 1) {
				$(".prev_step_button").hide("fade", 500);
			} else if (step == 3) {
				$(".next_step_button").attr("onClick","nextStep()");
				$(".next_step_button").attr("src", "../images/next.png");;
			}
			togglePosition(step + 1);
		}
	}
	
	/**
	* 步骤改变，步骤对应下标风格改变
	*/
	function togglePosition(pos) {
		if (pos) {
			$("#step" + pos + "_position").toggleClass("notNow", 500);
		}
		$("#step" + step + "_position").toggleClass("notNow", 500);
	}
	
	function selectDataObtain() {
		var data = ["餐饮", "休闲", "服饰", "酒店", "奶茶", "书店", "牛排", "自助餐", "娱乐"];
		selectDataSet(data);
	}
	
	function selectDataSet(data) {
		for (var i = 0; i < data.length; i ++) {
			$("#type").append("<option value='" + data[i] + "'>" + data[i] + "</option>");
		}
	}
	
	/**
	* 检查是否可可以跳转到步骤2
	*/
	function canStep2() {
		for (var param in parameterValidate) {
			if (!parameterValidate[param]) {
				invalidEffect($("#" + param));
				return false;
			}
		}
		return true;
	}
	
	function checkUsername() {
		var username = $("#username").val().trim();
		if (username.length < 3) {
			$("#username_validate").html("<span style='color: red;'>用户名长度应该大于3</span>");
			invalidEffect($("#username"));
		} else {
			$.post("", {"username" : username}, function() {
				
			});
			$("#username_validate").html("<span style='color: green;'>用户名可用</span>");
			parameterValidate.username = true;
		}
	}
	
	function checkPassword() {
		var password = $("#password").val().trim();
		if (password.length < 6) {
			$("#password_validate").html("<span style='color: red;'>密码长度应该大于6</span>");
			invalidEffect($("#password"));
			parameterValidate.password = false;
		} else {
			$("#password_validate").html("<span style='color: green;'>密码长度满足</span>");
			parameterValidate.password = true;
		}
	}
	
	function checkRepassword() {
		var repassword = $("#repassword").val().trim();
		if (repassword != $("#password").val().trim()) {
			$("#repassword_validate").html("<span style='color: red;'>两次密码不一致</span>");
			invalidEffect($("#repassword"));
			invalidEffect($("#password"));
			parameterValidate.repassword = false;
		} else if (repassword.length > 0){
			$("#repassword_validate").html("<span style='color: green;'>密码验证通过</span>");
		parameterValidate.repassword = true;
		}
	}
	
	function checkBusinessName() {
		var businessName = $("#business_name").val().trim();
		if (businessName.length == 0) {
			$("#business_validate").html("<span style='color: red;'>请输入商户名</span>");
			invalidEffect($("#business_name"));
			parameterValidate.business_name = false;
		} else {
			$("#business_validate").html("<span style='color: green;'>商户名验证通过</span>");
			parameterValidate.business_name = true;
		}
	}
	
	function checkPhone() {
		var phone = $("#phone").val().trim();
		if (phone.length > 0) {
			var reg = new RegExp("^[0-9]+$");
			if (reg.test(phone)) {
				$("#phone_validate").html("<span style='color: green;'>电话号码验证通过</span>");
				parameterValidate.phone = true;
			} else {
				invalidEffect($("#phone"));
				$("#phone_validate").html("<span style='color: red;'>电话号码格式错误</span>");
				parameterValidate.phone = false;
			}
		}
	}
	
	/**
	* @Param JQuery对象，验证失败数据对应的组件
	*/
	function invalidEffect(component) {
		component.effect("pulsate", 500);
	}
	
	/**
	* 图片预览
	*/
	function image_prev(evt) {
		var select = false;
		// 遍历选择的文件
		var files = event.target.files;
		for (var i = 0, f; f = files[i]; i ++) {
			// 排除非图片选择
			if (!f.type.match('image.*')) {
				continue;
			}
			// 标志图片选择
			select = true;
			var reader = new FileReader();
			reader.onload = (function(theFile) {
				return function(e) {
					$("#prevView").attr("src", e.target.result);
					image_measure();
				};
			})(f);
			reader.readAsDataURL(f);
		}
		if (!select) {
			$("#logo_upload").val(null);
			invalidEffect($("#logo_upload"));
		}
	}
	
	/**
	* 图片预览大小设置
	*/
	function image_measure() {
		var height = $("#prevView").height();
		var width = $("#prevView").width();
		var scaleW = 400 / width;
		var scaleH = 260 / height;
		var scale = scaleW < scaleH ? scaleW : scaleH;
		$("#prevView").css("width", (scale * width));
	}
	
</script>

<style>

	body {
		background-image: url("../images/login_background.png");
		background-repeat: no-repeat;
	}

	fieldset {
		background: white;
		display: inline;
		height: 420px;
		width: 540px;
		margin: 30px;
		border-color: gray;
		border-width: 7px;
		border-style: solid;
		border-radius: 10px;
		-webkit-border-radius: 10px;
		-moz-border-radius: 10px;
		-o-border-radius: 10px;
	}
	
	legend {
		text-align: center;
		background: white;
		padding-left: 10px;
		padding-right: 10px;
		padding-top: 3px;
		border-radius: 5px;
		-webkit-border-radius: 5px;
		-moz-border-radius: 5px;
		-o-border-radius: 5px;
	}
	
	legend img {
		width: 170px;
	}
	
	option {
		width: 13em;
		display: inline-block;
	}
	
	body > .content_div {
		text-align: center;
	}
	
	.input_label {
		font-size: medium;
		width: 6em;
	}
	
	.input_view {
		width: 13em;
	}
	
	.input_view, .input_label {
		margin-top: 10px;
	}
	
	.prev_step_button, .next_step_button {
		width: 50px;
		height: 35px;
		position: absolute;
		top: 100px;
		background-color: #00BFF3;
		border-radius: 5px;
		-webkit-radius: 5px;
		-moz-radius: 5px;
		-o-radius: 5px;
	}
	
	.prev_step_button:HOVER, .next_step_button:HOVER {
		background-color: #1CFAFF; 
	}
	
	.validate_msg {
		font-size: small;
		padding: 0px;
		margin: 0px;
		height: 1em;
	}
	
	#step1, #step2, #step3, #step4 {
		position: absolute;
		top: 140px;
		left: 415px;
		display: inline;
		width: 540px;
		text-align: center;
	}
	
	.prevview {
		margin-top: 20px;
		width: 350px;
	}
	
	#map {
		width: 99%;
		height: 280px;
		margin-top: 10px;
		display: block;
	}
	
	#step3 .button {
		width: 70px;
		height: 2em;
		font-size: small;
		padding-top: 0px;
		padding-bottom: 0px;
		position: relative;
		top: -3px;
	}
	
	#logo_upload {
		width: 300px;
	}
	
	#detail {
		resize: none;
		padding: 7px;
		border-width: 3px;
		border-radius: 7px;
		-webkit-border-radius: 7px;
		-moz-border-radius: 7px;
		-o-border-radius: 7px;
	}
	
	#step_position textarea {
		resize: none;
		width: 10em;
		height: 4em;
		padding: 8px;
		background-color: white;
		border-style: solid;
		border-width: 2px;
		border-radius: 7px;
		-webkit-border-radius: 7px;
		-moz-border-radius: 7px;
		-o-border-radius: 7px;
	}
	
	#step_position div {
		display: inline;
	}
	
	#step_position .position {
		color: white;
		font-weight: 900;
		background-color: #0FC62B;
		border-color: #085F16;
		border-width: 4px;
		padding: 10px;
		position: relative;
		left: -6.5em;
		top: 3em;
		border-radius: 30px;
		-webkit-border-radius: 30px;
		-moz-border-radius: 30px;
		-o-border-radius: 30px;
	}
	
	.notNow {
		opacity: 0.3;
	}
	
</style>
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
				<input class="input_view" type="file" id="logo_upload" onChange="image_prev()"/><br/>
				<img class="prevview" id="prevView" src="../images/default_business_logo.png">
			</div>
			<div id="step3">
				<label class="input_label" for="address">地址：</label>
				<input class="input_view" id="address" type="text"/>
				<button class="button" onClick="addressSet()">确定</button>
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
		</fieldset>
		<div id="step_position">
			<div id="step1_position">
				<textarea rows="3" cols="10" id="step1_explain" readonly="readonly" onfocus="blur()">填写必需的资料，点击右上角的小箭头继续！ </textarea>
				<label class="position">1/5</label>
			</div>
			<div id="step2_position" class="notNow">
				<textarea rows="3" cols="10" id="step2_explain" readonly="readonly" onfocus="blur()">让大家知道你的店招！选择你的Logo，剩下的交给我！</textarea>
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