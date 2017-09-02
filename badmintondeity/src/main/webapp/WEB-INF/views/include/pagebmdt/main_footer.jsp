<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<head>
	<script type="text/javascript">
		$(function($){
			var menuCode=$("#menuCode").val();
			if(menuCode=="INDEXPAGE"){
				$("#mainpageImg").attr("src","/p2pstock/static/images/mainpageIcon/iconfont-home-active.png");
				$("#indexDD").css({color:"#478e87"});
			}else if(menuCode=="ACTIVITYPAGE"){
				$("#activityImg").attr("src","/p2pstock/static/images/mainpageIcon/iconfont-badminton-active.png");
				$("#activityDD").css({color:"#478e87"});
			}else if(menuCode=="MYPAGE"){
				$("#usercenterImg").attr("src","/p2pstock/static/images/mainpageIcon/iconfont-my-active.png");
				$("#myDD").css({color:"#478e87"});
			}
		});
	</script>
</head>
<!-- footer begin -->
	<nav class="navbar navbar-default navbar-fixed-bottom" style="padding-top:5px">
		<input type="hidden" id="menuCode" value="${session.menusec}">
		<div style="width:25%; float:left;height: 50px;text-align:center;">
			<a href="/p2pstock/init/index.action?menucode=INDEXPAGE">
				<dt><img id="mainpageImg" src="/p2pstock/static/images/mainpageIcon/iconfont-home.png" width=25px/></dt>
				<span id="indexDD" style="color:#999999">首页</span>
			</a>
		</div>
		<div style="width:25%; float:left;height: 50px;text-align:center;">		
			<a href="/p2pstock/activity/queryActivityInit_activityMgrAction.action?menucode=ACTIVITYPAGE">
				<dt><img id="activityImg" src="/p2pstock/static/images/mainpageIcon/iconfont-badminton.png" width=25px/></dt>
				<span id="activityDD" style="color:#999999">比赛</span>	
			</a>
		</div>
		<div style="width:25%;float:left;height:50px;text-align:center;">
			<a href="/p2pstock/rank/rankListInit_rankMgrAction.action?menucode=RANK"> 
				<dt><img id="usercenterImg" src="/p2pstock/static/images/mainpageIcon/iconfont-contest.png" width=25px/></dt>
				<span id="myDD" style="color:#999999">榜单</span>
			</a>
		</div>
		<div style="width:25%;float:left;height:50px;text-align:center;">
			<a href="/p2pstock/usercenter/userInit_userCenterAction.action?menucode=MYPAGE"> 
				<dt><img id="usercenterImg" src="/p2pstock/static/images/mainpageIcon/iconfont-my.png" width=25px/></dt>
				<span id="myDD" style="color:#999999">我的</span>
			</a>
		</div>
	</nav>
<!-- footer end -->