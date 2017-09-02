<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>比赛详情</title>
<link rel="stylesheet" href="/p2pstock/static/css/jqueryui/jquery.ui.all.css">
<link rel="stylesheet" href="/p2pstock/static/css/jqueryui/jquery.spinner.css" />

<script src="/p2pstock/static/js/jquery/jquery-1.10.2.js"></script>
<script src="/p2pstock/static/js/jquery/jquery.ui.core.js"></script>
<script src="/p2pstock/static/js/jquery/jquery.ui.widget.js"></script>
<script src="/p2pstock/static/js/jquery/jquery.ui.mouse.js"></script>
<script src="/p2pstock/static/js/jquery/jquery.ui.slider.js"></script>
<script src="/p2pstock/static/js/jquery/jquery.spinner.js"></script>

<script type="text/javascript">

	function addEnroll() {
		
		if(isNaN($("#offsetPrice").text())){//如果折扣是NaN的情况
			$("#offset").addClass("has-error");
		}else{
			$("#original_cost").val($("#cost").text());
			$("#enrollVoCost").val($("#total").text());
			$("#enrollNumber").val($("#count").val());
			$("#discount").val($("#offset").val());	
			$("#payEnrollform").submit();
		}			
	}	

	function cancelEnroll() {
		$("#cancelEnrollform").submit();
	}

	function queryEnroll() {
		$("#enrollInitform").submit();
	}
	
	function calTotal() {
		var t = Subtr($("#cost").text(),$("#offsetPrice").text());
		$("#total").text(t);
	}
	
	function caloffset() {		
		if( ($("#offset").val()-0) > ($("#accBalance").text()-0)){
			$("#offset").val($("#accBalance").text());
		}else if(($("#offset").val()-0) < 0){
			$("#offset").val(0);
		}

		var offset;
		offset = accDiv($("#offset").val(),10);
		$("#offsetPrice").text(offset);
		calTotal();
	}
	
</script>
</head>
<body>
	<div class="main">
		<div class="topic" style="text-align:center;padding:5px">				
			<img type="text" id="logo" name="groupVo.group_logo" src="${groupVo.group_logo}" class="img-circle" style="width:80px;height:80px;"/>
		</div>
		<div class="content">
			<div class="container">
			<p>
				比赛名称：
				<s:property value="activityVo.activity_name" />
			</p>
			<p>
				比赛状态：
				<s:if test="activityVo.activity_status==0">报名中</s:if>
				<s:elseif test="activityVo.activity_status==1">未结算</s:elseif>
				<s:elseif test="activityVo.activity_status==2">已结算</s:elseif>
			</p>
			<p>
				主办战队：
				<s:property value="groupVo.group_name"/>
			</p>
			</div>
		</div>
		
		<div class="topic">比赛日期</div>
		<div class="content">
			<div class="container" style="text-align:center">
				<span style="font-family:微软雅黑;font-size:20px"><s:date name="activityVo.activity_begin" format="yyyy.MM.dd" /></span>
				<s:date name="activityVo.activity_begin" format="HH:mm" />~<s:date name="activityVo.activity_end" format="HH:mm" />
			</div>			
		</div>		
		
		<div class="topic">地点规则</div>
		<div class="content">
			<div class="container">
			<div style="width:50%;float:left;border-right:5px solid #f2f1f2">
				<div>地点：</div>
				<div>
					<span><i class="fa fa-fw fa-map-marker" style="color:#00BFFF;padding-top:2px"></i></span>
					<span><s:property value="activityVo.site_name" /></span>
				</div>
			</div>
			<div style="width:50%;float:right;padding-left: 15px">
				<div>报名规则：</div>
				<div>
					人均<s:property value="activityVo.enroll_roll_id" />￥
				</div>
			</div>
			</div>
		</div>
		
		<div class="topic">比赛人员</div>
		<div class="content">
			<div class="container">
			<s:form action="/enroll/queryAllEnroll_enrollMgrAction.action" method="post" theme="simple" id="enrollInitform">
			<input type="hidden" name="enrollVo.activity_id" value="${activityVo.activity_id}" />					
			<div style="width:50%;float:left;border-right:5px solid #f2f1f2" onclick="queryEnroll();">
				<div>已报名：</div>
				<p style="text-align: center;">
					<span><img src="/p2pstock/static/images/rrd/people.png" width=20px />&nbsp;&nbsp;&nbsp;					
					<s:property value="totalNum" />人
					</span>
				</p>
			</div>
			</s:form>
			
			<div style="width:50%;float:right;padding-left:15px">
				<div>联系人：</div>				
				<p style="text-align: center;">
					<s:property value="contactVo.user_nickname" />
					<a href="tel:${contactVo.phone}"><i class="fa fa-phone" style="color:#00BFFF;float:right;padding:2px 10px 0px 0px"></i></a>
				</p>
					
			</div>
			</div>
		</div>
		
		<div class="topic">报名费</div>
		<div class="content">
			<div class="container">
			<div>
				<table style="width:100%;">
					<tr>
						<td style="width:30%">数量：</td>						
						<td align="right" style="width:70%;">
							<s:if test="activityVo.activity_status==0">
								<input id="count" type="text" class="spinnerExample" value="1"/>								
							</s:if>
						</td>
					</tr>
					<tr>
						<td>小计</td>
						<td align="right">
							<span id="cost" style="font-size:2em"><s:property value="activityVo.enroll_roll_id" /></span>￥
						</td>
					</tr>
				</table>
				<script type="text/javascript">
					var fee = $("#cost").text();
					$('.spinnerExample').spinner({});
				</script>
			</div>
			</div>			
		</div>
		<div class="topic">成绩</div>
		<div class="content">
			<div class="container">
				<div>成绩 <span style="float:right">共<span id="accBalance"><s:property value="userCreditVo.acc_balance-userCreditVo.acc_freeze" /></span>成绩可用</span></div>
				<s:if test="enrollVo.status!=1">
					<div style="margin-top:5px">使用
						<input type="tel" value="0" id="offset" oninput="caloffset();" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
							style="ime-mode:disabled;width:5em;text-align:center;margin:0px 5px;border:1px solid #DCDCDC;border-radius:2px">
						成绩<span span style="float:right">抵<span id="offsetPrice">0.00</span>元</span>
					</div>
				</s:if>
			</div>
		</div>
		
		<div class="topic">支付方式</div>
		<div class="content">
			<div class="container">
				<img src="/p2pstock/static/images/rrd/weixin_logo.png"/><span style="margin-left:5px">微信支付</span>
				<span style="float:right"><input type="radio" checked/></span>
			</div>
		</div>
		<div class="pinkline"></div>
		<!-- 比赛可报名情况 -->
		<s:if test="activityVo.activity_status==0">
			<div class="content">
				<s:form action="/enroll/addEnroll_enrollMgrAction.action" method="post" theme="simple" id="payEnrollform">
					<input type="hidden" name="userCreditDetailVo.amnt" id="discount"/>
					<input type="hidden" name="enrollVo.number"  id="enrollNumber"/>
					<input type="hidden" name="enrollVo.activity_id" value="${activityVo.activity_id}" />
					<input type="hidden" id="enrollVoCost" name="enrollVo.cost" value="" />
						<input type="hidden" id="original_cost" name="enrollVo.original_cost" value="" />
					<div class="container">
						<div style="width:50%;float:left;padding-top:5px">
							合计：<span id="total" style="font-size:20px"><s:property value="activityVo.enroll_roll_id" /></span>元	
						</div>
						<div style="width:50%;float:right">
							<button class="btn btn-large btn-block btn-info" type="button" onclick="addEnroll()">确定</button>
						</div>							
					</div>
				</s:form>
			</div>						
		</s:if>
		<s:else>
			<div class="content" style="text-align:center">报名已结束</div>
		</s:else>

		<s:if test='%{#session.userid ==activityVo.user_id}'>
			<div class="content">				
				<div class="container">
					<div style="float:left;">
					<s:form action="/activity/endActivity_activityMgrAction.action" method="post" theme="simple" id="enrollInitform">
						<input type="hidden" name="activityVo.activity_id" value="${activityVo.activity_id}" />
						<button class="btn btn-large btn-block" type="submit">结算比赛</button>
					</s:form>
					</div>
					<div style="float:left;">
					<s:form action="/activity/endActivity_activityMgrAction.action" method="post" theme="simple" id="enrollInitform">
						<input type="hidden" name="activityVo.activity_id" value="${activityVo.activity_id}" />
						<button class="btn btn-large btn-block" type="submit">取消比赛</button>
					</s:form>
					</div>
				</div>
			</div>
		</s:if>


	</div>
</body>


