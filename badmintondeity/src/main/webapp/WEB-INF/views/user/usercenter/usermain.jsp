<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<head>
<title>用户中心</title>

</head>
<body>	
	<div class="topic">
		<div style="text-align:center;padding-top:10px">
			<s:if test="userInfoVo.user_headimgurl !=''">
				<img src="${userInfoVo.user_headimgurl}" class="img-circle">
			</s:if>
			<s:else>
				<img src="/p2pstock/static/images/rrd/ucenter-man.png" class="img-circle">
			</s:else>			
		</div>
		
		<div style="text-align:center;padding-bottom:5px">
			<p><h4>${userInfoVo.user_nickname}</h4></p>			
		</div>
	</div>
	
	<div style="background-color:#FFF;">
	
		<div style="padding:10px">
			<div class="container" onclick="location='/p2pstock/message/myMessageInit_messageMgrAction.action'">
			<span><i class="fa fa-fw fa-commenting-o" style="width:20px;color:#32CD32"></i></span>
			<span>我的消息</span>
			<span style="float: right">
				<span style="color:#FFA500"><s:property value="unreadMessage"/></span>
				<i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i>
			</span>
			</div>
		</div>
		<div class="pinkline"></div>
		<div style="padding:10px"  onclick="location='/p2pstock/usercenter/queryUserCredit_userCenterAction.action'">
			<div class="container">
			<span><img src="/p2pstock/static/images/rrd/feather.png" width=20px/></i></span>
			<span>我的成绩</span>
			<span style="float: right">
				<span style="color:#FFA500">${userCreditVo.acc_balance}</span>
				<i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i>
			</span>
			</div>
		</div>
		<div class="pinkline"></div>
		<div style="padding:10px">
			<div class="container">
			<span><i class="fa fa-fw fa-balance-scale" style="width:20px;color:#4682B4"></i></span>
			<span>我的账户</span>
			<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i></span>
			</div>
		</div>
		<div class="pinkline"></div>
		<div style="padding:10px">
			<div class="container">
			<span><i class="fa fa-fw fa-heart" style="width:20px;color:#FF1493"></i></span>
			<span>我的关注</span>
			<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i></span>
			</div>
		</div>
		<div class="pinkline"></div>
		<div style="padding:10px" onclick="location='/p2pstock/group/myGroup_groupMgrAction.action'">
			<div class="container">
			<span><i class="fa fa-fw fa-users" style="width:20px;color:#BA55D3"></i></span>
			<span>我的战队</span>
			<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i></span>
			</div>
		</div>
	</div>

	<div style="background-color:#FFF;">	
		<div style="padding:10px" onclick="location='/p2pstock/usercenter/myStartPlayingTime_userCenterAction.action'">
			<div class="container">
			<span><i class="fa fa-fw fa-trophy" style="width:20px;color:#FFD700"></i></span>
			<span>我的球龄</span>
			<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i></span>
			</div>
		</div>
		<div class="pinkline"></div>
		
		<div style="padding:10px">
			<div class="container">
			<span><i class="fa fa-fw fa-gift" style="width:20px;color:#F00"></i></span>			
			<span>我的设备</span>
			<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i></span>
			</div>
		</div>
		<div class="pinkline"></div>
		
		<div style="padding:10px">
			<div class="container">
			<span><i class="fa fa-fw fa-magic" style="width:20px;color:#7FFF00"></i></span>
			<span>我的技能</span>			
			<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i></span>
			</div>
		</div>
	</div>

	<div style="background-color:#FFF;">
		<div style="padding:10px" onclick="location='/p2pstock/usercenter/userEdit_userCenterAction.action'">
			<div class="container">
			<span><i class="fa fa-fw fa-cog" style="width:20px;color:#1E90FF"></i></span>
			<span>个人信息</span>
			<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding:4px 0px;color:#FF7F50"></i></span>
			</div>
		</div>
	</div>	
</body>
