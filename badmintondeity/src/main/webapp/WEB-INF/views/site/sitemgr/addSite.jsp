<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery-1.10.2.min.js"></script>
<link href="/p2pstock/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=qTG6bofEvW0lCLva0gkAGZrx"></script>
<title>城市名定位</title>

<script type="text/javascript">
	function addSite() {
		$("#title").val($("#spantitle").text());
		$("#province").val($("#spanprovince").text());
		$("#city").val($("#spancity").text());
		$("#district").val($("#spandistrict").text());
		$("#lng").val($("#spanlng").text());
		$("#lat").val($("#spanlat").text());
		var params = $("#addSiteform").serialize();
		$.ajax({
			type : "post",// 使用get方法访问后台
			url : "/p2pstock/site/addSite_siteMgrAction.action",// 要访问的后台地址
			dataType : "json",
			data : params,
			success : function(msg) {
				alert(msg.result);
			}
		});
	}
</script>
</head>
<body>
	<input type="hidden" id="mycity" value="${session.city}">	
	<div class="container" style="margin:10px">
		<input type="text" id="location" style="width:60%">
		<input type="button" value="搜索" onclick="searchSite();" style="width:30%;float:right">
	</div>
	<div id="allmap" style="height:50%;width:100%;"></div>
	<div id="r-result" style="padding:5px">	
		<s:form action="/site/addSite_siteMgrAction.action" method="post" theme="simple" id="addSiteform">
		<div id="address" style="display:none;font-size:14px;padding:10px 5px">
			<input type="radio" checked/>
			<span id="spantitle"></span>
			<span id="spanprovince"></span>
			<span id="spancity"></span>
			<span id="spandistrict"></span>
			<span id="spanstreet"></span>
			<span id="spanstreetNumber"></span>
			<span id="spanlng" style="display:none"></span>
			<span id="spanlat" style="display:none"></span>
		</div>
				
		<div class="container" style="margin-top:10px">
			<button id="addBtn" disabled class="btn btn-large btn-block" type="button" onclick="addSite();">新增地点</button>
		</div>
		<input type="hidden" id="title" name="siteVo.site_name" readonly/>
      	<input type="hidden" id="province" name="siteVo.site_addr_province" readonly/>
      	<input type="hidden" id="city" name="siteVo.site_addr_city" readonly/>
      	<input type="hidden" id="district" name="siteVo.site_addr_district" readonly/>
      	<input type="hidden" id="street" readonly/>
      	<input type="hidden" id="streetNumber" readonly/>
      	<input type="hidden" id="lng" name="siteVo.site_lng" readonly/>
      	<input type="hidden" id="lat" name="siteVo.site_lat" readonly/>
      	</s:form>
  	</div>
	
</body>

</html>
<script type="text/javascript">
	var mycity = $("#mycity").val();
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398, 39.897445);
	map.centerAndZoom(point, 12);
	map.enableScrollWheelZoom(true);
	map.setCenter("mycity");

	//搜索地点 新增用
	function searchSite() {
		var name = $("#location").val();
		var local = new BMap.LocalSearch(map, {
			renderOptions : {
				map : map
			}
		});
		local.search(name);
		local.setMarkersSetCallback(function(pois) {
			if (pois.length == 0) {
				map.clearOverlays();
				alert("对不起，没有找到相关信息");
				$("#address").hide();
				$("#addBtn").attr("disabled","disabled"); 
			} else {
				for ( var i = pois.length; i--;) {
					var marker = pois[i].marker;
					if (i == 0) {
						var title = marker.getTitle();
						var pt = marker.getPosition();
						var geoc = new BMap.Geocoder();
						geoc.getLocation(pt, function(rs) {
							var addComp = rs.addressComponents;
							$("#spantitle").text(title);
							$("#spanprovince").text(addComp.province);
							$("#spancity").text(addComp.city);
							$("#spandistrict").text(addComp.district);
							$("#spanstreet").text(addComp.street);
							$("#spanstreetNumber").text(addComp.streetNumber);
							$("#spanlng").text(pt.lng);
							$("#spanlat").text(pt.lat);
							$("#addBtn").removeAttr("disabled");
							$("#address").show();
						});
					}

					marker.addEventListener("click", function(e) {
						var title = this.getTitle();
						var pt = this.getPosition();
						var geoc = new BMap.Geocoder();
						geoc.getLocation(pt, function(rs) {
							var addComp = rs.addressComponents;
							$("#spantitle").text(title);
							$("#spanprovince").text(addComp.province);
							$("#spancity").text(addComp.city);
							$("#spandistrict").text(addComp.district);
							$("#spanstreet").text(addComp.street);
							$("#spanstreetNumber").text(addComp.streetNumber);
							$("#spanlng").text(pt.lng);
							$("#spanlat").text(pt.lat);
						});
					});
				}
			}
		});
	}
</script>
