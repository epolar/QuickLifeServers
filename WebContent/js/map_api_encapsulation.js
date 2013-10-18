/**
* 百度地图API
*/
var map;
var marker;
var geo;
var showAddress;
var markerAddress;

/**
* @param mapDisplay 在什么地方显示地图，控件id
* @param address 在什么地方显示标注位置的地址，控件id
*/
function initMap(mapDisplay, address) {
	if (address) {
		showAddress = $("#" + address);
	}
	// 初始化百度地图API
	map = new BMap.Map(mapDisplay);
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
		if (showAddress) {
			showAddress.val(addr.address);
			markerAddress = addr.address;
		}
	});
}
	
	/**
	* 地址设定
	*/
	function addressSet(address) {
		var addr = address.trim();
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