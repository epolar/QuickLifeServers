var step = 1;
/**
* 必需验证的表单数据是否都正确 
*/
var parameterValidate = 
	{	"username": false, 
		"business_name": false, 
		"password": false, 
		"repassword" : false, 
		"phone" : true};

$(function() {
	$(".prev_step_button, #step2, #step3, #step4, #step5").hide();
	resizeInvoke();
	$(window).resize(resizeInvoke);
	// 设定类型值
	selectDataObtain();
	// 地图初始化
	initMap("map", "address");
});

function submit() {
	if (!canStep2()) {
		alert("请检查所有的步骤是否都是正确的");
		return false;
	}
	$("#step" + step).hide("blind", {"direction": "left"}, 300);
	step ++;
	$("#step" + step).show("blind", {"direction": "right"}, 300);
	$(".next_step_button, .prev_step_button").hide("fade", 500);
	ajaxUpload(getFormData(), "", submitFinish);
	togglePosition(4);
}

/**
 * 得到表单对象
 * @returns {FormData}
 */
function getFormData() {
	// 密码md5加密
	$("#password").val(hex_md5($("#password").val()));
	$("#repassword").val(hex_md5($("repassword").val()));
	var formData = new FormData();
	var input_view = $("fieldset input.input_view:not('[type=file]')");
	for (var i = 0; i < input_view.length; i ++) {
		formData.append(input_view[i].id, input_view[i].value);
	}
	var select = $("fieldset select");
	for (var i = 0; i < select.length; i ++) {
		formData.append(select[i].id, formData.value);
	}
	var textarea = $("fieldset textarea");
	for (var i = 0; i < textarea.length; i ++) {
		formData.append(textarea[i].id, textarea[i].value);
	}
	// 地址
	formData.append("address", markerAddress);
	// 图片
	var logo_img = document.getElementById("logo_upload").files[0];
	formData.append("logo", logo_img);
	
	return formData;
}

function submitFinish(res) {
	$("#registering").hide();
	$("#register_success").show();
	setTimeout(forword2Main, 1000);
}

var seconds = 5;
function forword2Main() {
	if (seconds > 0) {
		seconds --;
		$("#forward_second").text(seconds);
		setTimeout(foword2Main, 1000);
	} else {
		location.href = "main.jsp";
		forward();
	}
}

function register_again() {
	step = 1;
	togglePosition(5);
	$("#step5").hide("blind", {"direction": "right"}, 300);
	$("#step1").show("blind", {"direction": "left"}, 300);
	$(".next_step_button").show("fade", 500);
	$(".next_step_button").attr({"onClick": "nextStep()", "src": "../images/next.png"});
	$(".input_view").next().next().empty();
	$("#password, #repassword").val(null);
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
		if (step == 1) {
			if (!canStep2()) {
				return ;
			}
		}
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

function findAddress() {
	addressSet($("#address").val());
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