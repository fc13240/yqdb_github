<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		#allmap{height:400px;width:100%;}
		#r-result{width:100%; font-size:14px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=qTG6bofEvW0lCLva0gkAGZrx"></script>
	<title>城市名定位</title>
<body>
	<div id="allmap"></div>
	<div id="r-result"></div>
</body>
</html>
<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap");
		var point = new BMap.Point(116.331398,39.897445);
		map.centerAndZoom(point,12);
		map.enableScrollWheelZoom(true);
		var myCity = new BMap.LocalCity();
		myCity.get(myFun);
		
		/* 根据IP定位城市 */
		function myFun(result){
			var cityName = result.name;
			map.setCenter(cityName);
		}
		
		
		/* 根据浏览器定位 */
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				var pt = r.point;
				var geoc = new BMap.Geocoder();   
				geoc.getLocation(pt, function(rs){
					var addComp = rs.addressComponents;
					alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber
							 + "  (" +pt.lng + ", " + pt.lat+ ")");
				});
				
			}
			else {
				alert('定位失败，请确认是否开启浏览器定位功能'+this.getStatus());
			}        
		},{enableHighAccuracy: true}); 
		
	</script>
