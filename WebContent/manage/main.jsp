<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快来后台管理系统</title>
<jsp:include page="../head.html"></jsp:include>
<script>

	$(function () {
		// 菜单点击的都跑到在跳转到 frame 里
		$(".menu_button").attr("target", "frame");
		// iFrame 高度设置
		frameSizeSet();
		$(window).resize(function() {
			frameSizeSet();
		});
		// 时间设置
		timeShow();
		// utils 栏伸缩按钮
		$(".toggle_utils").click(utilToggle);
		$(".toggle_utils").text("<");
	});
	
	var utilsClose = false;
	function utilToggle() {
		if (utilsClose) {
			$(".utils_content" ).animate({ "left": "+=170px" }, 300, function() {
				$(".toggle_utils").text("<");
			});
		} else {
			$(".utils_content" ).animate({ "left": "-=170px" }, 300, function() {
				$(".toggle_utils").text(">");
			});
		}
		utilsClose = !utilsClose;
	}
	
	function frameSizeSet() {
		// frame框的大小也根据 utils_content而定
		var height = $(".utils_content").outerHeight();
		height = height > $(window).innerHeight() ? height : $(window).innerHeight();
		$("#frame").height(height - $(".menu_content").height());
		$("#frame").width(window.innerWidth - $(".utils_content").width() - 20);
		// utils_content 高度确定
		if ( $(".utils_content").height() < $(window).innerHeight() ) {
			$(".utils_content").height(window.innerHeight - $(".menu_content").height());
		} else {
			$(".utils_content").height("100%");
		}
		// 伸缩按钮确定
		$(".toggle_utils").css("top", $(window).innerHeight() / 5 * 2);
	}
	
	function timeShow() {
		var date = new Date();
		var weekText = ["日", "一", "二", "三", "四", "五", "六"];
		var tWeek = "星期" + weekText[date.getDay()];
		$("#date_time").html( date.getFullYear() + "/" + addZero(date.getMonth()) + "/" + addZero(date.getDate()) + "&nbsp;"
				 + tWeek  + "<br/>" + addZero(date.getHours()) + ":" + addZero(date.getMinutes()) + ":" + addZero(date.getSeconds()));
		setTimeout('timeShow()', 1000);
	}
	
</script>
<style>
	.menu_content {
		background-color: #00BFF3;
		width: 100%;
		height: 30px;
		padding-left: 10px;
		padding: 0px;
		marging: 0px;
	}
	
	body {
		margin: 0px;
		padding: 0px;
	}
	
	.menu_button {
		width: 80px;
		height: 23px;
	}
	
	.menu li {
		float: left;
		display: inline;
	}
	
	.logout {
		margin-top: 12px;
		margin-left: 10px;
		font-size: 14px;
		font-weight: bold;
		color: #00F;
	}
	
	.logout:HOVER {
		color: #007BAE;
	}
	
	.nick {
		margin-top: 10px;
		font-weight: bold;
	}
	
	.utils_text {
		margin-left: 10px;
		font-weight: bold;
	}
	
	#frame {
		overflow: auto;
		padding: 0px;
		margin: 0px;
		float: right;
		display: inline;
	}
	
	.utils_content {
		width: 200px;
		padding: 0px;
		margin: 0px;
		position: relative;
		background-color: #00BFF3;
		float:left;
		display: inline;
		text-align: center;
	}
	
	.logo {
		width: 200px;
		background: silver;
	}
	
	fieldset {
		margin-top: 30px;
		margin-bottom: 30px;
		margin-left: 20px;
		margin-right: 20px;
		padding: 10px;
		padding-bottom: 20px;
		/*圆角*/
		border-radius: 10px;
		-webkit-border-radius: 10px;
		-moz-border-radius: 10px;
		-o-border-radius: 10px;
	}
	
	fieldset ul {
		list-style-type: none;
	}
	
	fieldset li {
		float: left;
		display: block;
	}
	
	.toggle_utils {
		position: absolute;
		top: 270px;
		left: 185px;
		float: right;
		margin: 0px;
		padding: 5px;
	}
	
}
</style>
</head>
<body>
	<div class="menu_content">
		<ul class="menu">
			<li><a href="/WebDemo/song_table.jsp" class="menu_button">优惠管理</a></li>
			<li><a href="" class="menu_button">添加优惠</a></li>
			<li><a href="" class="menu_button">商户信息</a></li>
			<li><a href="" class="menu_button">查看评论</a></li>
			<li><a href="" class="menu_button">数据统计</a></li>
		</ul>
	</div>
	<div class="utils_content">
		<img class="logo" src="../images/logo_text.png" alt="快来"/>
		<br>
		<br>
		<span class="nick">${sessionScope.user_name}超人组</span>
		<a href="" class="logout" >注销</a>
		<br/>
		<fieldset>
			<ul>
				<li>公司名称</li>
				<li>联系电话</li>
				<li>公司地址</li>
			</ul>
		</fieldset>
		<fieldset>
			<ul>
				<li>优惠总数</li>
				<li>商户评分</li>
				<li>光顾人数</li>
			</ul>
		</fieldset>
		<br/>
		<span class="utils_text" id="date_time"></span>
		<br/>
		<p class="button toggle_utils"></p>
	</div>
	<iframe id="frame" name="frame" frameborder="0">
		<noframes><span>您的浏览器太旧了！！！</span></noframes>
	</iframe>
</body>
</html>