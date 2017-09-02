<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
	<link href="/p2pstock/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/p2pstock/static/css/bmdt.css" rel="stylesheet"type="text/css">
	<script type="text/javascript" charset="utf-8"src="/p2pstock/static/js/jquery/jquery-1.10.2.min.js"></script>
	<title>请选择城市</title>
	<script type="text/javascript">
		function setCity(obj){
			var cityName = obj.innerHTML;
			$.ajax({
				type : "post",// 使用get方法访问后台
				url : "/p2pstock/city/setCity_cityMgrAction.action?cityName="+cityName,// 要访问的后台地址
				success : function(msg) {
					window.location.reload();					
				}
			});
		}
	
	</script>
</head>
<body style="background-color:#f2f1f2">
	<div class="topic">定位城市</div>
	<div class="content">
		<div id="locateCity" class="row" style="width:100%;margin:0 auto">
			<span style="float:left" id="city" class="col-xs-3 col-sm-2 col-md-1" ><a href="#">北京</a></span>
			<span style="float: right" class="col-xs-3 col-sm-2 col-md-1"><a href="/p2pstock/city/getLocation_cityMgrAction.action">GPS定位</a></span>
		</div>
	</div>
	<div class="topic">当前城市</div>
	<div class="content">
		<div id="NowCity" class="row" style="width:100%;margin:0 auto">
			<span style="float:left" class="col-xs-3 col-sm-2 col-md-1" ><a href="#" style="color:orange"><%=session.getAttribute("city")%></a></span>
			<span style="float: right" class="col-xs-3 col-sm-2 col-md-1"><a href="/p2pstock/init/index.action?menucode=INDEXPAGE">确定</a></span>
		</div>
	</div>
	<div class="topic">热门城市</div>
	<div class="content">
		<div id="hotCity" class="row" style="width:100%;margin:0 auto">
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">北京</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">上海</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">广州</a></span>							
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">天津</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">南京</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">杭州</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">深圳</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">成都</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">重庆</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">苏州</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">厦门</a></span>
			<span class="col-xs-3 col-sm-2 col-md-1" ><a href="#" onclick="setCity(this)">三亚</a></span>
		</div>
	</div>
	<div class="topic">全部</div>	
	<div id="cityList" style="background-color:#fff;" class="container">
		<div id="city-A">
			<div >A</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">鞍山</a> <a href="#" onclick="setCity(this)">安顺</a> <a href="#" onclick="setCity(this)">阿坝</a> <a href="#" onclick="setCity(this)">阿拉善</a>
				<a href="#" onclick="setCity(this)">阿里</a> <a href="#" onclick="setCity(this)">安康</a> <a href="#" onclick="setCity(this)">阿克苏</a> <a href="#" onclick="setCity(this)">安庆</a> 
				<a href="#" onclick="setCity(this)">阿勒泰</a> <a href="#" onclick="setCity(this)">安阳</a> <a href="#" onclick="setCity(this)">澳门</a>
			</div>
		</div>
		<div id="city-B">
			<div >B</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">北京</a> <a href="#" onclick="setCity(this)">包头</a> <a href="#" onclick="setCity(this)">保定</a> <a href="#" onclick="setCity(this)">巴中</a>
				<a href="#" onclick="setCity(this)">蚌埠</a> <a href="#" onclick="setCity(this)">白银</a> <a href="#" onclick="setCity(this)">白城</a> <a href="#" onclick="setCity(this)">白山</a>
				<a href="#" onclick="setCity(this)">北海</a> <a href="#" onclick="setCity(this)">巴彦淖尔</a> <a href="#" onclick="setCity(this)">宝鸡</a> <a href="#" onclick="setCity(this)">百色</a>
				<a href="#" onclick="setCity(this)">本溪</a> <a href="#" onclick="setCity(this)">保山</a> <a href="#" onclick="setCity(this)">毕节</a> <a href="#" onclick="setCity(this)">博尔塔拉</a> 
				<a href="#" onclick="setCity(this)">滨州</a> <a href="#" onclick="setCity(this)">亳州</a> <a href="#" onclick="setCity(this)">巴州</a>

			</div>
		</div>
		<div id="city-C">
			<div >C</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">成都</a> <a href="#" onclick="setCity(this)">重庆</a> <a href="#" onclick="setCity(this)">常州</a> <a href="#" onclick="setCity(this)">长沙</a>
				<a href="#" onclick="setCity(this)">长春</a> <a href="#" onclick="setCity(this)">赤峰</a> <a href="#" onclick="setCity(this)">楚雄</a> <a href="#" onclick="setCity(this)">长治</a>
				<a href="#" onclick="setCity(this)">巢湖</a> <a href="#" onclick="setCity(this)">崇左</a> <a href="#" onclick="setCity(this)">潮州</a> <a href="#" onclick="setCity(this)">昌吉</a>
				<a href="#" onclick="setCity(this)">沧州</a> <a href="#" onclick="setCity(this)">郴州</a> <a href="#" onclick="setCity(this)">常德</a> <a href="#" onclick="setCity(this)">滁州</a>
				<a href="#" onclick="setCity(this)">朝阳</a> <a href="#" onclick="setCity(this)">昌都</a> <a href="#" onclick="setCity(this)">池州</a> <a href="#" onclick="setCity(this)">承德</a>
			</div>
		</div>
		<div id="city-D">
			<div >D</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">东莞</a> <a href="#" onclick="setCity(this)">大连</a> <a href="#" onclick="setCity(this)">东营</a> <a href="#" onclick="setCity(this)">大庆</a>
				<a href="#" onclick="setCity(this)">大同</a> <a href="#" onclick="setCity(this)">大理</a> <a href="#" onclick="setCity(this)">德阳</a> <a href="#" onclick="setCity(this)">迪庆</a>
				<a href="#" onclick="setCity(this)">达州</a> <a href="#" onclick="setCity(this)">德州</a> <a href="#" onclick="setCity(this)">丹东</a> <a href="#" onclick="setCity(this)">大兴安岭</a>
				<a href="#" onclick="setCity(this)">德宏</a> <a href="#" onclick="setCity(this)">定西</a>
			</div>
		</div>
		<div id="city-E">
			<div >E</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">恩施</a><a href="#" onclick="setCity(this)">鄂州</a><a href="#" onclick="setCity(this)">鄂尔多斯</a>
			</div>
		</div>
		<div id="city-F">
			<div >F</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">福州</a> <a href="#" onclick="setCity(this)">佛山</a> <a href="#" onclick="setCity(this)">抚顺</a> <a href="#" onclick="setCity(this)">阜阳</a>
				<a href="#" onclick="setCity(this)">抚州</a> <a href="#" onclick="setCity(this)">防城港</a> <a href="#" onclick="setCity(this)">阜新</a>
			</div>
		</div>
		<div id="city-G">
			<div >G</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">广州</a> <a href="#" onclick="setCity(this)">贵阳</a> <a href="#" onclick="setCity(this)">桂林</a> <a href="#" onclick="setCity(this)">赣州</a>
				<a href="#" onclick="setCity(this)">广元</a> <a href="#" onclick="setCity(this)">果洛</a> <a href="#" onclick="setCity(this)">固原</a> <a href="#" onclick="setCity(this)">甘南</a>
				<a href="#" onclick="setCity(this)">甘孜</a> <a href="#" onclick="setCity(this)">广安</a> <a href="#" onclick="setCity(this)">贵港</a>
			</div>
		</div>
		<div id="city-H">
			<div >H</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">杭州</a> <a href="#" onclick="setCity(this)">哈尔滨</a> <a href="#" onclick="setCity(this)">合肥</a> <a href="#" onclick="setCity(this)">邯郸</a> 
				<a href="#" onclick="setCity(this)">惠州</a> <a href="#" onclick="setCity(this)">海口</a> <a href="#" onclick="setCity(this)">呼和浩特</a> <a href="#" onclick="setCity(this)">衡阳</a> 
				<a href="#" onclick="setCity(this)">湖州</a> <a href="#" onclick="setCity(this)">淮北</a> <a href="#" onclick="setCity(this)">鹤岗</a> <a href="#" onclick="setCity(this)">海北</a> 
				<a href="#" onclick="setCity(this)">海东</a> <a href="#" onclick="setCity(this)">黄南</a> <a href="#" onclick="setCity(this)">菏泽</a> <a href="#" onclick="setCity(this)">海南州</a> 
				<a href="#" onclick="setCity(this)">黑河</a> <a href="#" onclick="setCity(this)">和田</a> <a href="#" onclick="setCity(this)">哈密</a> <a href="#" onclick="setCity(this)">淮安</a> 
				<a href="#" onclick="setCity(this)">淮南</a> <a href="#" onclick="setCity(this)">黄山</a> <a href="#" onclick="setCity(this)">海西</a> <a href="#" onclick="setCity(this)">贺州</a> 
				<a href="#" onclick="setCity(this)">怀化</a> <a href="#" onclick="setCity(this)">河池</a> <a href="#" onclick="setCity(this)">呼伦贝尔</a> <a href="#" onclick="setCity(this)">衡水</a> 
				<a href="#" onclick="setCity(this)">河源</a> <a href="#" onclick="setCity(this)">红河</a> <a href="#" onclick="setCity(this)">汉中</a> <a href="#" onclick="setCity(this)">黄冈</a> 
				<a href="#" onclick="setCity(this)">黄石</a> <a href="#" onclick="setCity(this)">鹤壁</a> <a href="#" onclick="setCity(this)">葫芦岛</a>
			</div>
		</div>
		<div id="city-J">
			<div >J</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">济南</a> <a href="#" onclick="setCity(this)">济宁</a> <a href="#" onclick="setCity(this)">嘉兴</a> <a href="#" onclick="setCity(this)">金华</a>
				<a href="#" onclick="setCity(this)">焦作</a> <a href="#" onclick="setCity(this)">荆州</a> <a href="#" onclick="setCity(this)">吉林</a> <a href="#" onclick="setCity(this)">锦州</a>
				<a href="#" onclick="setCity(this)">江门</a> <a href="#" onclick="setCity(this)">景德镇</a> <a href="#" onclick="setCity(this)">吉安</a> <a href="#" onclick="setCity(this)">佳木斯</a> 
				<a href="#" onclick="setCity(this)">酒泉</a> <a href="#" onclick="setCity(this)">金昌</a> <a href="#" onclick="setCity(this)">鸡西</a> <a href="#" onclick="setCity(this)">济源</a> 
				<a href="#" onclick="setCity(this)">晋城</a> <a href="#" onclick="setCity(this)">揭阳</a> <a href="#" onclick="setCity(this)">晋中</a> <a href="#" onclick="setCity(this)">荆门</a> 
				<a href="#" onclick="setCity(this)">九江</a>
			</div>
		</div>
		<div id="city-K">
			<div >K</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">昆明</a> <a href="#" onclick="setCity(this)">昆山</a> <a href="#" onclick="setCity(this)">喀什</a> <a href="#" onclick="setCity(this)">克拉玛依</a>
				<a href="#" onclick="setCity(this)">开封</a> <a href="#" onclick="setCity(this)">克州</a>
			</div>
		</div>
		<div id="city-L">
			<div >L</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">兰州</a> <a href="#" onclick="setCity(this)">临沂</a> <a href="#" onclick="setCity(this)">连云港</a> <a href="#" onclick="setCity(this)">聊城</a> 
				<a href="#" onclick="setCity(this)">临汾</a> <a href="#" onclick="setCity(this)">柳州</a> <a href="#" onclick="setCity(this)">洛阳</a> <a href="#" onclick="setCity(this)">廊坊</a> 
				<a href="#" onclick="setCity(this)">龙岩</a> <a href="#" onclick="setCity(this)">六盘水</a> <a href="#" onclick="setCity(this)">凉山</a> <a href="#" onclick="setCity(this)">六安</a> 
				<a href="#" onclick="setCity(this)">丽江</a> <a href="#" onclick="setCity(this)">临沧</a> <a href="#" onclick="setCity(this)">陇南</a> <a href="#" onclick="setCity(this)">拉萨</a> 
				<a href="#" onclick="setCity(this)">辽源</a> <a href="#" onclick="setCity(this)">辽阳</a> <a href="#" onclick="setCity(this)">莱芜</a> <a href="#" onclick="setCity(this)">漯河</a> 
				<a href="#" onclick="setCity(this)">吕梁</a> <a href="#" onclick="setCity(this)">丽水</a> <a href="#" onclick="setCity(this)">临夏</a> <a href="#" onclick="setCity(this)">林芝</a> 
				<a href="#" onclick="setCity(this)">娄底</a> <a href="#" onclick="setCity(this)">来宾</a> <a href="#" onclick="setCity(this)">泸州</a> <a href="#" onclick="setCity(this)">乐山</a>
			</div>
		</div>
		<div id="city-M">
			<div >M</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">马鞍山</a><a href="#" onclick="setCity(this)">绵阳</a><a href="#" onclick="setCity(this)">茂名</a><a href="#" onclick="setCity(this)">牡丹江</a>
				<a href="#" onclick="setCity(this)">梅州</a><a href="#" onclick="setCity(this)">眉山</a>
			</div>
		</div>
		<div id="city-N">
			<div >N</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">南京</a> <a href="#" onclick="setCity(this)">宁波</a> <a href="#" onclick="setCity(this)">南通</a> <a href="#" onclick="setCity(this)">南宁</a>
				<a href="#" onclick="setCity(this)">南昌</a> <a href="#" onclick="setCity(this)">南充</a> <a href="#" onclick="setCity(this)">宁德</a> <a href="#" onclick="setCity(this)">内江</a>
				<a href="#" onclick="setCity(this)">怒江</a> <a href="#" onclick="setCity(this)">南平</a> <a href="#" onclick="setCity(this)">南阳</a> <a href="#" onclick="setCity(this)">那曲</a>
			</div>
		</div>
		<div id="city-P">
			<div >P</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">萍乡</a><a href="#" onclick="setCity(this)">平顶山</a><a href="#" onclick="setCity(this)">莆田</a> <a href="#" onclick="setCity(this)">濮阳</a>
				<a href="#" onclick="setCity(this)">攀枝花</a><a href="#" onclick="setCity(this)">平凉</a><a href="#" onclick="setCity(this)">普洱</a><a href="#" onclick="setCity(this)">盘锦</a>
			</div>
		</div>
		<div id="city-Q">
			<div >Q</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">青岛</a> <a href="#" onclick="setCity(this)">泉州</a> <a href="#" onclick="setCity(this)">秦皇岛</a> <a href="#" onclick="setCity(this)">齐齐哈尔</a> 
				<a href="#" onclick="setCity(this)">庆阳</a> <a href="#" onclick="setCity(this)">衢州</a> <a href="#" onclick="setCity(this)">黔西南</a> <a href="#" onclick="setCity(this)">钦州</a> 
				<a href="#" onclick="setCity(this)">黔南</a> <a href="#" onclick="setCity(this)">曲靖</a> <a href="#" onclick="setCity(this)">黔东南</a> <a href="#" onclick="setCity(this)">七台河</a> 
				<a href="#" onclick="setCity(this)">清远</a>
			</div>
		</div>
		<div id="city-R">
			<div >R</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">日照</a><a href="#" onclick="setCity(this)">日喀则</a>
			</div>
		</div>
		<div id="city-S">
			<div >S</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">上海</a> <a href="#" onclick="setCity(this)">深圳</a> <a href="#" onclick="setCity(this)">沈阳</a> <a href="#" onclick="setCity(this)">苏州</a>
				<a href="#" onclick="setCity(this)">石家庄</a> <a href="#" onclick="setCity(this)">绍兴</a> <a href="#" onclick="setCity(this)">顺德</a> <a href="#" onclick="setCity(this)">三亚</a> 
				<a href="#" onclick="setCity(this)">韶关</a> <a href="#" onclick="setCity(this)">绥化</a> <a href="#" onclick="setCity(this)">松原</a> <a href="#" onclick="setCity(this)">上饶</a> 
				<a href="#" onclick="setCity(this)">十堰</a> <a href="#" onclick="setCity(this)">三门峡</a> <a href="#" onclick="setCity(this)">山南</a> <a href="#" onclick="setCity(this)">邵阳</a> 
				<a href="#" onclick="setCity(this)">遂宁</a> <a href="#" onclick="setCity(this)">商丘</a> <a href="#" onclick="setCity(this)">朔州</a> <a href="#" onclick="setCity(this)">随州</a> 
				<a href="#" onclick="setCity(this)">汕尾</a> <a href="#" onclick="setCity(this)">四平</a> <a href="#" onclick="setCity(this)">三峡</a> <a href="#" onclick="setCity(this)">宿迁</a> 
				<a href="#" onclick="setCity(this)">三明</a> <a href="#" onclick="setCity(this)">石嘴山</a> <a href="#" onclick="setCity(this)">双鸭山</a> <a href="#" onclick="setCity(this)">汕头</a> 
				<a href="#" onclick="setCity(this)">宿州</a> <a href="#" onclick="setCity(this)">商洛</a>
			</div>
		</div>
		<div id="city-T">
			<div >T</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">天津</a> <a href="#" onclick="setCity(this)">太原</a> <a href="#" onclick="setCity(this)">泰安</a> <a href="#" onclick="setCity(this)">台州</a>
				<a href="#" onclick="setCity(this)">唐山</a> <a href="#" onclick="setCity(this)">泰州</a> <a href="#" onclick="setCity(this)">塔城</a> <a href="#" onclick="setCity(this)">铜陵</a>
				<a href="#" onclick="setCity(this)">铜川</a> <a href="#" onclick="setCity(this)">台北</a> <a href="#" onclick="setCity(this)">铜仁</a> <a href="#" onclick="setCity(this)">吐鲁番</a>
				<a href="#" onclick="setCity(this)">天水</a> <a href="#" onclick="setCity(this)">通辽</a> <a href="#" onclick="setCity(this)">铁岭</a> <a href="#" onclick="setCity(this)">通化</a>
			</div>
		</div>
		<div id="city-W">
			<div >W</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">武汉</a> <a href="#" onclick="setCity(this)">无锡</a> <a href="#" onclick="setCity(this)">温州</a> <a href="#" onclick="setCity(this)">芜湖</a>
				<a href="#" onclick="setCity(this)">威海</a> <a href="#" onclick="setCity(this)">潍坊</a> <a href="#" onclick="setCity(this)">乌鲁木齐</a> <a href="#" onclick="setCity(this)">梧州</a> 
				<a href="#" onclick="setCity(this)">吴忠</a> <a href="#" onclick="setCity(this)">武威</a> <a href="#" onclick="setCity(this)">渭南</a> <a href="#" onclick="setCity(this)">乌兰察布</a> 
				<a href="#" onclick="setCity(this)">文山</a> <a href="#" onclick="setCity(this)">乌海</a>
			</div>
		</div>
		<div id="city-X">
			<div >X</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">西安</a> <a href="#" onclick="setCity(this)">厦门</a> <a href="#" onclick="setCity(this)">徐州</a> <a href="#" onclick="setCity(this)">襄阳</a>
				<a href="#" onclick="setCity(this)">西宁</a> <a href="#" onclick="setCity(this)">孝感</a> <a href="#" onclick="setCity(this)">西双版纳</a> <a href="#" onclick="setCity(this)">新余</a> 
				<a href="#" onclick="setCity(this)">湘潭</a> <a href="#" onclick="setCity(this)">锡林郭勒</a> <a href="#" onclick="setCity(this)">兴安</a> <a href="#" onclick="setCity(this)">邢台</a> 
				<a href="#" onclick="setCity(this)">新乡</a> <a href="#" onclick="setCity(this)">湘西</a> <a href="#" onclick="setCity(this)">忻州</a> <a href="#" onclick="setCity(this)">咸阳</a> 
				<a href="#" onclick="setCity(this)">宣城</a> <a href="#" onclick="setCity(this)">香港</a> <a href="#" onclick="setCity(this)">信阳</a> <a href="#" onclick="setCity(this)">咸宁</a> 
				<a href="#" onclick="setCity(this)">许昌</a>
			</div>
		</div>
		<div id="city-Y">
			<div >Y</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">扬州</a> <a href="#" onclick="setCity(this)">烟台</a> <a href="#" onclick="setCity(this)">盐城</a> <a href="#" onclick="setCity(this)">运城</a>
				<a href="#" onclick="setCity(this)">义乌</a> <a href="#" onclick="setCity(this)">岳阳</a> <a href="#" onclick="setCity(this)">宜昌</a> <a href="#" onclick="setCity(this)">玉林</a>
				<a href="#" onclick="setCity(this)">银川</a> <a href="#" onclick="setCity(this)">鹰潭</a> <a href="#" onclick="setCity(this)">雅安</a> <a href="#" onclick="setCity(this)">伊犁</a>
				<a href="#" onclick="setCity(this)">玉树</a> <a href="#" onclick="setCity(this)">宜春</a> <a href="#" onclick="setCity(this)">营口</a> <a href="#" onclick="setCity(this)">永州</a>
				<a href="#" onclick="setCity(this)">宜宾</a> <a href="#" onclick="setCity(this)">益阳</a> <a href="#" onclick="setCity(this)">玉溪</a> <a href="#" onclick="setCity(this)">阳泉</a>
				<a href="#" onclick="setCity(this)">延安</a> <a href="#" onclick="setCity(this)">榆林</a> <a href="#" onclick="setCity(this)">云浮</a> <a href="#" onclick="setCity(this)">延边</a>
				<a href="#" onclick="setCity(this)">阳江</a> <a href="#" onclick="setCity(this)">伊春</a>
			</div>
		</div>
		<div id="city-Z">
			<div >Z</div>
			<div class="row" style="margin-left:10px">
				<a href="#" onclick="setCity(this)">郑州</a> <a href="#" onclick="setCity(this)">镇江</a> <a href="#" onclick="setCity(this)">中山</a> <a href="#" onclick="setCity(this)">淄博</a>
				<a href="#" onclick="setCity(this)">珠海</a> <a href="#" onclick="setCity(this)">遵义</a> <a href="#" onclick="setCity(this)">株洲</a><a href="#" onclick="setCity(this)">自贡</a>
				<a href="#" onclick="setCity(this)">舟山</a><a href="#" onclick="setCity(this)">湛江</a> <a href="#" onclick="setCity(this)">肇庆</a><a href="#" onclick="setCity(this)">漳州</a>
				<a href="#" onclick="setCity(this)">张掖</a><a href="#" onclick="setCity(this)">昭通</a> <a href="#" onclick="setCity(this)">张家界</a><a href="#" onclick="setCity(this)">周口</a>
				<a href="#" onclick="setCity(this)">驻马店</a><a href="#" onclick="setCity(this)">张家口</a> <a href="#" onclick="setCity(this)">资阳</a><a href="#" onclick="setCity(this)">中卫</a>
				<a href="#" onclick="setCity(this)">枣庄</a>
			</div>
		</div>
	</div>
</body>
</html>