<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="renderer" content="webkit" />
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/common/common.js"></script>

<!-- Bootstrap -->
<link href="/p2pstock/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/p2pstock/font-awesome-4.4.0/css/font-awesome.css" rel="stylesheet">
<script src="/p2pstock/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function queryNewActivity() {
		$.ajax({
			type : "get",// 使用get方法访问后台
			url : "/p2pstock/ajax/queryNewActivity_indexAction.action",// 要访问的后台地址
			success : function(msg) {
				$("#newActivityDiv").html(msg);
			}
		});
	}
	window.onload = queryNewActivity;
</script>
</head>

<body>
	<!-- 定位 搜索 个人 -->
	<div style="width:100%;padding:0px 5px;background-color:rgb(98,171,141);">
		<div style="padding:5px 0px;">
			<table width=100%>
				<tr>
					<td style="width:60px;">
						<div>
							<a href="/p2pstock/city/chooseCity_cityMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-location.png" width=15px /></a><a href="/p2pstock/city/chooseCity_cityMgrAction.action" style="font-size:14px">[<%=session.getAttribute("city")%>]</a></td>
						</div>
					<td>
						<span><input results="s" type="search" style="width:100%;border-radius:10px;border: 1px solid #F8F8F8;padding-left:10px;" />
						<a href="#"><i class="fa fa-search" style="position:absolute;right:42px;top:10px;color:#00FFFF;"></i></a></span></td>
					<td style="width: 30px; text-align:right"><a href="#">
						<img src="/p2pstock/static/images/rrd/my.png" width=20px /></a></td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 定位 搜索 个人end -->
	<!-- 热点比赛公告板 -->
	<div id="myCarousel" class="carousel slide" style="width:100%">
	   	<ol class="carousel-indicators">
	       	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	       	<li data-target="#myCarousel" data-slide-to="1"></li>
        	<li data-target="#myCarousel" data-slide-to="2"></li>
      	</ol>
		<!-- Carousel items -->
		<div class="carousel-inner">
			<div class="active item"><img src="/p2pstock/static/images/rrd/mainpageone.png" style="width:100%;height:150px"/></div>
			<div class="item"><img src="/p2pstock/static/images/rrd/mainpagetwo.gif" style="width:100%;height:150px"/></div>
			<div class="item"><img src="/p2pstock/static/images/rrd/mainpagethree.gif" style="width:100%;height:150px"/></div>
		</div>
		<!-- Carousel nav -->
			<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>
	<!-- 热点比赛公告板 -->
				
	<div style="background-color:#fefefe;">
	<!-- 会长专栏 -->
	<div style="font-size:14px">
		<div class="row" style="margin:0px auto;padding:10px 0px">
			<div style="width:8%;float:left;text-align:center;border-right:1px solid #DCDCDC;padding:6px 0px">队<br/>长</div>
			<div style="width:23%;float:left;text-align:center;border-right:1px solid #DCDCDC">	
				<div><a href="/p2pstock/group/addGroupInit_groupMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-createactive-green.png"/></a></div>
				<div>组建战队</div>
			</div>
			<div style="width:23%;float:left;text-align:center;border-right:1px solid #DCDCDC">	
				<div><a href="/p2pstock/activity/addActivityInit_activityMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-edit-green.png" /></a></div>
				<div>发起比赛</div>
			</div>
			<div style="width:23%;float:left;text-align:center;border-right:1px solid #DCDCDC">	
				<div><a href="/p2pstock/activity/addActivityInit_activityMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-contest.png" /></a></div>
				<div>管理比赛</div>
			</div>
			<div style="width:23%;float:left;text-align:center">	
				<div><a href="/p2pstock/settle/queryAllSettle_settleMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-recharge-green.png" /></a></div>
				<div>成绩管理</div>
			</div>
		</div>
	</div>
	<!-- 会长专栏 -->
			
	<!-- 会员专栏 -->
	<div style="border-top:1px solid #DCDCDC;font-size:14px">
		<div class="row" style="margin:0px auto;padding:10px 0px">
			<div style="width:8%;float:left;text-align:center;border-right:1px solid #DCDCDC;padding:6px 0px">队<br/>员</div>
			<div style="width:23%;float:left;text-align:center;border-right:1px solid #DCDCDC">
				<div><a href="/p2pstock/group/groupMgrInit_groupMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-green.png" /></a></div>
				<div>参加战队</div>
			</div>
			<div style="width:23%;float:left;text-align:center;border-right:1px solid #DCDCDC">	
				<div><a href="/p2pstock/activity/queryActivityInit_activityMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-search-green.png" /></a></div>
				<div>查找比赛</div>
			</div>
			<div style="width:23%;float:left;text-align:center;border-right:1px solid #DCDCDC">	
				<div><a href="/p2pstock/activity/queryActivityInit_activityMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-join-match.png" /></a></div>
				<div>参加比赛</div>
			</div>			
			<div style="width:23%;float:left;text-align:center">
				<div><a href="/p2pstock/enroll/queryMyEnroll_enrollMgrAction.action"><img src="/p2pstock/static/images/mainpageIcon/iconfont-refund-green.png" /></a></div>
				<div>我的成绩</div>
			</div>
		</div>
	</div>
	<!-- 会员专栏 -->
	</div>

	<!-- 最新比赛列表 -->
	<div class="topic">
		<span>最新比赛</span> 
		<span style="float:right;border-left:3px solid #0BAFE6;padding-left:1em">
			<a herf="#" onclick="queryAllActivity();">更多</a>
		</span>
	</div>
	<div style="background-color:#fefefe;padding:2px 0px;">					
		<div id="newActivityDiv"></div>
	</div>
	<!-- 最新比赛列表 -->
	</div>

</body>
</html>