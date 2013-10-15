<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快来后台管理系统</title>
<link href="../images/logo.png" rel="shortcut icon" />
<link href="../css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
<script src="../js/jquery-1.9.1.js"></script>
<script src="../js/jquery-ui-1.10.3.custom.js"></script>
<script>

	$(function () {
		$(".button").button();
		// 菜单 hover
		$(".menu_button").hover(function() {
			$(this).finish();
			$(this).animate({backgroundColor: "green"}, 500);
		}, function() {
			$(this).animate({backgroundColor: "transparent"}, 600);
		});
		// iFrame 高度设置
		frameSizeSet();
		$(window).resize(function() {
			frameSizeSet();
		});
	});
	
	function frameSizeSet() {
		$("#frame").height(window.innerHeight - $(".menu_content").height());
		$("#frame").width(window.innerWidth - 100);
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
	
	.menu_button {
		width: 80px;
		height: 23px;
		float: left;
		display: inline;
		text-align: center;
		text-decoration: none;
		font-weight: bold;
		font-size: 15px;
		padding-top: 7px;
		color: white;
	}
	
	.menu_button:HOVER.test {
		background-color: green;
	}
	
	.menu {
		float: left;
		list-style-type: none;
		margin: 0px;
		padding: 0px;
		display: inline;
	}
	
	body {
		margin: 0px;
		padding: 0px;
	}
	
	.menu li {
		float: left;
		display: inline;
	}
	
	.logout {
		margin-top: 6px;
		margin-right: 20px;
		font-weight: bold;
		float: right;
		color: #00F;
	}
	
	.logout:HOVER {
		color: #007BAE;
	}
	
	menu_content .nick {
		padding-top: 1px;
		float: right;
	}
	
	#frame {
		overflow: auto;
		padding: 0px;
		margin: 0px;
	}
	
	.utils_content {
		width: 100px;
		background-color: #00BFF3;
	}
	
}
</style>
</head>
<body>
	<div class="menu_content">
		<ul class="menu">
			<li><a href="" class="menu_button">优惠管理</a></li>
			<li><a href="" class="menu_button">添加优惠</a></li>
			<li><a href="" class="menu_button">商户信息</a></li>
			<li><a href="" class="menu_button">查看评论</a></li>
			<li><a href="" class="menu_button">数据统计</a></li>
		</ul>
		<span class="nick">${sessionScope.user_name}</span>
		<a href="" class="logout" >安全退出</a>
	</div>
	<div class="utils_content">
	
	</div>
	<iframe id="frame" frameborder="0">
		<noframes><span>您的浏览器太旧了！！！</span></noframes>
	</iframe>
</body>
</html>