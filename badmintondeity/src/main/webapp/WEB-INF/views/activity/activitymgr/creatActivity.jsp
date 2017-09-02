<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>发起比赛</title>
<!-- loading -->
<link href="/p2pstock/static/css/loading/style.css" rel="stylesheet">

<!-- mobiscroll -->
<script src="/p2pstock/static/js/datetime/mobiscroll.core-2.6.2.js"	type="text/javascript"></script>
<script src="/p2pstock/static/js/datetime/mobiscroll.core-2.6.2-zh.js"	type="text/javascript"></script>
<script src="/p2pstock/static/js/datetime/mobiscroll.datetime-2.6.2.js"	type="text/javascript"></script>
<script	src="/p2pstock/static/js/datetime/mobiscroll.android-ics-2.6.2.js"	type="text/javascript"></script>
<link href="/p2pstock/static/css/datetime/mobiscroll.core-2.6.2.css" rel="stylesheet" type="text/css" />
<link href="/p2pstock/static/css/datetime/mobiscroll.android-ics-2.6.2.css" rel="stylesheet" type="text/css" />

<!-- Calendar Style CSS -->
<link href="/p2pstock/static/css/calendar/style.css" rel="stylesheet" type="text/css">
<script src="/p2pstock/static/js/calendar/jquery-ui.multidatespicker.js" type="text/javascript"></script>
<script src="/p2pstock/static/js/calendar/modernizr.min.js"></script>
<script src="/p2pstock/static/js/calendar/jquery-ui.min.js"></script>
<script src="/p2pstock/static/js/calendar/general.js" type="text/javascript"></script>

<link href="/p2pstock/static/css/animations/animations.css"	rel="stylesheet" type="text/css" />

<!-- 城市 -->
<script src="/p2pstock/static/js/iscroll/iscroll.js"></script>
<link href="/p2pstock/static/css/iscroll.css" rel="stylesheet">

<script src="/p2pstock/static/js/activity/creatActivity.js"></script>
</head>
<body>
	<!-- Loading -->
	<div id="loadingDiv">
		<div id="loader-container">
			<p id="loadingText">Loading</p>
		</div>
	</div>
	<!-- Loading -->
	
	<div style="width:100%;overflow:hidden;position:relative">
	
	<s:form action="/activity/addActivity_activityMgrAction.action"
		method="post" theme="simple" id="addNewActivityform">
		
		<div id="activityInfo"
			style="background-color: #FFF; margin-top: 5px;">
			<div class="container">
				<input type="hidden" id="cityName" value="${session.city}" /> <input
					type="hidden" name="activityVo.activity_status" value="0" /> <input
					type="hidden" name="activityVo.user_id" value="1" />
				<div>
					<div style="padding: 15px 0px" onclick="showHiddenDiv(0);">
						<span><i class="fa fa-fw fa-tags" style="width: 15px; color: #BA55D3"></i></span> 
						<span>名称：<span id="activityName"></span></span> 
						<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding: 4px 0px; color: #FF7F50"></i></span>
					</div>
					<div class="pinkline"></div>

					<div style="padding: 15px 0px" onclick="showHiddenDiv(1);">
						<span><i class="fa fa-fw fa-calendar" style="width: 15px; color: #1E90FF"></i></span> 
						<span>时间：<span id="activityTime"></span></span> 
						<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding: 4px 0px; color: #FF7F50"></i></span>
					</div>
					<div class="pinkline"></div>

					<div style="padding: 15px 0px" onclick="showHiddenDiv(2);">
						<span><i class="fa fa-fw fa-bank" style="width: 15px; color: #DC143C"></i></span> 
						<span>地点：<span id="activityAddr"></span></span> 
						<span style="float: right"><i class="fa fa-fw fa-chevron-right" style="padding: 4px 0px; color: #FF7F50"></i></span>
						<input type="hidden" name="activityVo.activity_addr" id="activity_addr" />
					</div>
					<div class="pinkline"></div>

					<div style="padding: 15px 0px" onclick="showHiddenDiv(3);">
						<span><i class="fa fa-fw fa-pencil-square-o" style="width: 15px; color: #FF7F50"></i></span> 
						<span>规则：<span id="activityRoll"></span></span> 
						<span style="padding: 5px 0px; float: right"><i class="fa fa-fw fa-chevron-right" style="color: #FF7F50"></i></span>
					</div>										
				</div>
			</div>

			<div class="container" style="margin-top: 5px;">
				<textarea style="resize: none; width: 100%; text-align: center;" class="form-control" 
					placeholder="备注"></textarea>
			</div>

			<div class="container" style="margin-top:50px">
				<button class="btn btn-large btn-block btn-info" type="button"
					onclick="addNewActivity();">创建比赛</button>
			</div>

		</div>
		
		<!-- 战队 -->
		<div id="hiddenDiv0">
			<div>
				<img src="/p2pstock/static/images/rrd/newIndex.png" style="width:100%;height:150px"/>
			</div>
			<div class="topic">比赛名称</div>
			<div class="content">
				<div class="container" style="text-align:center">
					<input type="text" id="actName" name="activityVo.activity_name" class="form-control" >
				</div>
			</div>
			
			<div class="topic">比赛类型</div>
			<div class="content">
				<div class="container" style="text-align:center">
					<label class="radio-inline">
						<input type="radio" name="activityVo.activity_type" value="0" checked="checked" />男生5分钟					  
					</label>
					<label class="radio-inline">
						<input type="radio" name="activityVo.activity_type" value="1" />男生10分钟				  
					</label>	
					<label class="radio-inline">
						<input type="radio" name="activityVo.activity_type" value="2" />男生15分钟					  
					</label>
					<label class="radio-inline">
						<input type="radio" name="activityVo.activity_type" value="3" />女生5分钟				  
					</label>
					<label class="radio-inline">
						<input type="radio" name="activityVo.activity_type" value="4" />女生10分钟					  
					</label>
					<label class="radio-inline">
						<input type="radio" name="activityVo.activity_type" value="5" />女生15分钟				  
					</label>				
				</div>
			</div>			
			
			<div class="topic">举办战队</div>
			<div class="content">
				<div class="container" style="text-align:center">
					<s:select name="activityVo.group_id" id="group_id" value="activityVo.group_id" onchange="queryAdmin()"
						list="groupVoList" listValue="group_name" listKey="group_id" headerKey="-1" headerValue="--请选择--">
					</s:select>
				</div>
			</div>			
			
			<div class="topic">管理者</div>
			<div class="content" style="text-align: center">
				<div class="container" style="text-align:center">
					<select id="contact" name="activityVo.manage_id"></select>					
				</div>
			</div>
			
			<div class="container" style="margin-top:50px">
			<button class="btn btn-large btn-block btn-info" type="button"
				onclick="hideDiv(0)">确定</button>
			</div>
		</div>		

		<!-- 比赛时间 -->
		<div class="container" id="hiddenDiv1" style="padding-top: 10px;">
			<div style="text-align:center">
				<div class="widget-container widget-calendar boxed styled">
					<div class="inner">
						<input type="text" name="date_departure" value="" id="date_departure">
					</div>
					<input type="hidden" id="start_date" />
					<input type="hidden" id="end_date" />
					<input type="text" id="inputShowDate"style="width: 90%; text-align: center; margin-bottom: 10px" />
				</div>
			</div>
			<div style="margin-top:20px; text-align: center">
				<span id="beginTime_input"
					style="border: 1px solid #DCDCDC; border-radius: 2px; padding: 2px 5px">00:00</span>
				至 <span id="endTime_input"
					style="border: 1px solid #DCDCDC; border-radius: 2px; padding: 2px 5px">24:00</span>
			</div>

			<input type="hidden" id="beginTime" name="activityVo.activity_begin" />
			<input type="hidden" id="endTime" name="activityVo.activity_end" />
			<button class="btn btn-large btn-block btn-info" type="button"
				onclick="hideDiv(1)" style="margin-top: 20px">确定</button>
		</div>

		<!-- 比赛地点 -->
		<div id="hiddenDiv2" style="padding-top:10px;overflow:hidden">
			<div class="container">
			<!-- 区县滚动条 -->
			<div class="alist" id="areaList">
				<div id="areaul" class="dateul">
					<div class="areadiv areadivActive" name="areadiv"
								onclick="querySiteByArea('');">全部</div>
					<s:iterator value="areaList" status="st">
						<div class="areadiv" name="areadiv"
								onclick="querySiteByArea('${area_name}');">${area_name}</div>
					</s:iterator>
				</div>
			</div>
			</div>
			<div class="pinkline"></div>
			<div class="container">
				<div id="siteDiv" style="height:330px;margin:10px 0px;overflow:auto"></div>
				<div><a href="/p2pstock/site/addSiteInit_siteMgrAction.action">新增地点</a></div>
				<button class="btn btn-large btn-block btn-info" type="button" onclick="hideDiv(2)">确定</button>
			</div>
		</div>
		
		<!-- 报名规则 -->
		<div id="hiddenDiv3">
			<div class="topic">报名规则</div>
			<div class="content">
				<div class="container">
					报名结束时间：比赛开始前 <input type="text" value="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " readonly
						style="width: 2em; ime-mode: disabled; text-align: center; margin: 0px 5px; border: 1px solid #DCDCDC; border-radius: 2px" />小时
				</div>
			</div>

			<div class="topic">退赛</div>
			<div class="content">
				<div class="container">
					申请退赛截止时间：比赛开始前 <input type="text" value="2" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " readonly
						style="width: 2em; ime-mode: disabled; text-align: center; margin: 0px 5px; border: 1px solid #DCDCDC; border-radius: 2px" />小时
				</div>
			</div>
			<div class="topic">缺席比赛扣除积分</div>
			<div class="content">
				<div class="container">
					人均 <input type="text" id="rollId" name="activityVo.enroll_roll_id"
						value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
						style="width: 2em; ime-mode: disabled; text-align: center; margin: 0px 5px; border: 1px solid #DCDCDC; border-radius: 2px" />￥
				</div>
			</div>
			<div class="topic">备注</div>
			<div class="content">
				<div class="container">比赛签到后返还积分</div>
			</div>
			<div class="container" style="margin-top: 20px">
				<button class="btn btn-large btn-block btn-info" type="button"
					onclick="hideDiv(3)">确定</button>
			</div>
		</div>
		
	</s:form>
	</div>
</body>
