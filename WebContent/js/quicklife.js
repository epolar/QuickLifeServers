/**
 * ʹ��ǰӦ���ȵ��� JQuery �� JQueryUI
 */

$(function () {
	$(".button").button();
	// �˵� hover
	$(".menu_button").hover(function() {
		$(this).finish();
		$(this).animate({backgroundColor: "green"}, 500);
	}, function() {
		$(this).animate({backgroundColor: "transparent"}, 600);
	});
});

// Ϊ������λ�����ּ�ǰ��0
function addZero(num) {
	if (num < 10) {
		return "0" + num;
	} else {
		return num;
	}
}

// ���ø��� .login_form �ڵ����� input_view
function resetInputView() {
	$(this).parents(".login_form").find(".input_view").val("");
}

/**
 * 
 * @param success
 * @param fail
 */
function checkValidateCode(success, fail) {
	// ��֤����֤
	$.post("validateCode", {'validateCode':$("#validate_code").val()}, function(data) {
		if (data.validateCode == false) {
			return fail();
		} else {
			return success();
		}
	}, "JSON");
}

/**
 * ˢ����֤��
 * @param img ��֤����ʾ�� img
 */
function flushValidateCode(img) {
	$(img).attr("src", "validateCode?t=" + new Date().getMilliseconds());
}