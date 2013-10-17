/**
 * 使用前应该先导入 JQuery 和 JQueryUI
 */

$(function () {
	$(".button").button();
	// 菜单 hover
	$(".menu_button").hover(function() {
		$(this).finish();
		$(this).animate({backgroundColor: "green"}, 500);
	}, function() {
		$(this).animate({backgroundColor: "transparent"}, 600);
	});
});

// 为不足两位的数字加前面0
function addZero(num) {
	if (num < 10) {
		return "0" + num;
	} else {
		return num;
	}
}

// 重置父级 .login_form 内的所有 input_view
function resetInputView() {
	$(this).parents(".login_form").find(".input_view").val("");
}

/**
 * 
 * @param success
 * @param fail
 */
function checkValidateCode(success, fail) {
	// 验证码验证
	$.post("validateCode", {'validateCode':$("#validate_code").val()}, function(data) {
		if (data.validateCode == false) {
			return fail();
		} else {
			return success();
		}
	}, "JSON");
}

/**
 * 刷新验证码
 * @param img 验证码显示的 img
 */
function flushValidateCode(img) {
	$(img).attr("src", "validateCode?t=" + new Date().getMilliseconds());
}