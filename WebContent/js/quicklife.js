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

/**
* @Param JQuery对象，验证失败数据对应的组件
*/
function invalidEffect(component) {
	component.effect("pulsate", 500);
}

/**
* 图片预览
* @param prevView 用来预览图片的窗口id
* @param choice 选择框的id
*/
function image_prev(prevView, choice) {
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
				var view = $("#" + prevView);
				view.attr("src", e.target.result);
				image_measure(view);
			};
		})(f);
		reader.readAsDataURL(f);
	}
	if (!select) {
		$("#" + choice).val(null);
		invalidEffect($("#" + choice));
	}
}

/**
* 图片预览大小设置
* @param w 图片显示最大的宽度，默认400
* @param h 图片显示最大的高度， 默认260
*/
function image_measure(prevView, w, h) {
	var height = prevView.height();
	var width = prevView.width();
	var scaleW = (w == undefined ? 400 : w) / width;
	var scaleH = (h == undefined ? 260 : h) / height;
	var scale = scaleW < scaleH ? scaleW : scaleH;
	prevView.css("width", (scale * width));
}

/*
 * html 通过 Ajax 上传表单数据
 * @param formData 表单数据对象，FormData类型对象可以用append方法添加表单数据
 * @param url 要上传的位置
 * @param success 上传完成后调用的方法
 */
function ajaxUpload(formData, url, success) {
	// XMLHttpRequest 对象构建
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.onload = success;
	xhr.send(formData);
}

/*
* 加载框 
* Ajax overlay 1.0
* Author: Simon Ilett @ aplusdesign.com.au
* Descrip: Creates and inserts an ajax loader for ajax calls / timed events 
* Date: 03/08/2011 
*/
function ajaxLoader (el, options) {
	// Becomes this.options
	var defaults = {
		bgColor 		: '#fff',
		duration		: 800,
		opacity			: 0.7,
		classOveride 	: false
	};
	this.options 	= jQuery.extend(defaults, options);
	this.container 	= $(el);
	
	this.init = function() {
		var container = this.container;
		// Delete any other loaders
		this.remove(); 
		// Create the overlay 
		var overlay = $('<div></div>').css({
				'background-color': this.options.bgColor,
				'opacity':this.options.opacity,
				'width':container.width(),
				'height':container.height(),
				'position':'absolute',
				'top':'0px',
				'left':'0px',
				'z-index':99999
		}).addClass('ajax_overlay');
		// add an overiding class name to set new loader style 
		if (this.options.classOveride) {
			overlay.addClass(this.options.classOveride);
		}
		// insert overlay and loader into DOM 
		container.append(
			overlay.append(
				$('<div></div>').addClass('ajax_loader')
			).fadeIn(this.options.duration)
		);
    };
	
	this.remove = function(){
		var overlay = this.container.children(".ajax_overlay");
		if (overlay.length) {
			overlay.fadeOut(this.options.classOveride, function() {
				overlay.remove();
			});
		}	
	};

    this.init();
}	
	