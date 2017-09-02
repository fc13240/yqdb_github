<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title>订单</title>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=qTG6bofEvW0lCLva0gkAGZrx"></script>	
	<script type="text/javascript">
	
	function moreActivityinfo(t){
		var i="/p2pstock/activity/queryActivityDetailInfo_activityMgrAction.action?activityId="+t;
		$("#activity_id").val(t);
		$("#moreActivityInfoform").attr("action",i);
		$("#moreActivityInfoform").submit();
	}
	
	function signIn(){
		$("#bg").show();
		$("#show").show();
		// 百度地图API功能
		var map = new BMap.Map("allmap");		
		/* 根据浏览器定位 */
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var pt = r.point;
				var params = $("#signInform").serialize();
				$.ajax({
					type : "post",// 使用get方法访问后台
					url : "/p2pstock/enroll/signIn_enrollMgrAction.action?lng="+pt.lng+"&lat="+pt.lat,// 要访问的后台地址
					dataType : "json",
					data : params,
					success : function(msg) {
						if(msg.result==0){							
							$("#bg").hide();
							$("#show").hide();
							showMyModal("签到成功","签到积分");
						}else{							
							$("#bg").hide();
							$("#show").hide();
							showMyModal("签到失败","您与地点距离较远，无法签到");
						}						
					},
					error: function(e){
						$("#bg").hide();
						$("#show").hide();
						showMyModal("签到失败","签到异常");
					}
				});			
			}
			else {				
				$("#bg").hide();
				$("#show").hide();
				showMyModal("签到失败","定位失败，无法签到");
			}        
		},{enableHighAccuracy: true});		
	}
	
	function refund(){
		var params = $("#refundform").serialize();
		$.ajax({
			type : "post",// 使用get方法访问后台
			url : "/p2pstock/enroll/refundCheck_enrollMgrAction.action",// 要访问的后台地址
			dataType : "json",
			data : params,
			success : function(data) {
				if(data.result=="0"){
					$.ajax({
						type : "post",// 使用get方法访问后台
						url : "/p2pstock/weixin/refund_payAction.action",// 要访问的后台地址
						dataType : "json",
						data : params,
						success : function(msg) {
							if(msg.result=="0"){
								showMyModal("退款完成","退款完成，请查看您的微信支付");
								setTimeout(function(){
									window.location.href="http://yiqidongba.cn/p2pstock/enroll/queryMyEnroll_enrollMgrAction.action";
								},2000);								
							}else{
								showMyModal("退款失败","退款功能后台异常");
							}
						}
					});
				}else{
					showMyModal("退款失败","申请时间超过退款截至时间");
				}
			}
		});
	}
	
	function payEnroll(){
		$("#bg").show();
		$("#show").show();
		var params = $("#payEnrollform").serialize();
		$.ajax({
			type : "post",// 使用get方法访问后台
			url : "/p2pstock/weixin/pay_payAction.action",// 要访问的后台地址
			dataType : "text",
			data : params,
			success : function(msg) {
				$("#bg").hide();
				$("#show").hide();
				window['getSignok'](msg);
				$.ajaxSetup({
					async : false
				});
			},
			error: function(e){				
				$("#bg").hide();
				$("#show").hide();
				showMyModal("支付失败","支付程序启动失败");
			}
		});
	}	

	function getSignok(msg){
		var obj = eval('(' + msg + ')');
		 if(parseInt(msg.agent)<5){
			 showMyModal("微信支付","您的微信版本低于5.0无法使用微信支付");
			 return;
		}
		WeixinJSBridge.invoke('getBrandWCPayRequest',{
			"appId" : obj.appId,                  //公众号名称，由商户传入
			"timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数
			"nonceStr" : obj.nonceStr,         //随机串
			"package" : obj.packageValue,      //<span style="font-family:微软雅黑;">商品包信息</span>
			"signType" : obj.signType,        //微信签名方式:
			"paySign" : obj.paySign           //微信签名
			},function(res){
			if(res.err_msg == "get_brand_wcpay_request:ok" ) {
				window.location.href=obj.sendUrl;
			}else{//支付不成功情况
				alert(res.err_code+res.err_desc+res.err_msg);
				showMyModal("微信支付","支付失败");
				setTimeout(function(){
					window.location.href="http://yiqidongba.cn/p2pstock/enroll/queryMyEnroll_enrollMgrAction.action";
				},2000);
			}
		});
		
	};
	</script>
</head>
<body>
	<div id="bg" style="display:none;position:absolute;top:0%;left:0%;width:100%;height:100%;background-color:black;z-index:1001;-moz-opacity:0.7;opacity:.70;filter:alpha(opacity=70);">
	<div id="show" style="display:none;position:absolute;top:20%;left:22%;width:53%;height:49%;padding:8px;border:2px solid #E8E9F7;background-color:white;z-index:1002;overflow:auto";>
		<table style="width:100%;height:100%">
			<tr>
				<td style="vertical-align:middle;text-align:center"><span id="message"></span><i id="pullDownIcon" class="fa fa-spinner fa-spin"></i>请稍候Zzz..</td>
			</tr>
		</table>
		
	</div>
	</div>
	<div style="z-index:1000">
	<div id="allmap"></div>
	
	<s:form action="/activity/queryActivityDetailInfo_activityMgrAction.action" method="post" theme="simple" id="moreActivityInfoform">
		<s:hidden id="activity_id" name="activityVo.activity_id"></s:hidden>
	</s:form>
	
	<div class="container" onclick="moreActivityinfo('${activityVo.activity_id}');">
		<div style="padding:5px">			
		<div>
			<span><b>${activityVo.activity_name}</b></span>
		</div>
		<div style="font-size:12px;padding:1px">
			<span><i class="fa fa-clock-o" style="color:#6495ED"></i>
				<s:date name="activityVo.activity_begin" format="MM.dd" />&nbsp;&nbsp;<s:date name="activityVo.activity_begin" format="HH:mm" />&nbsp;-&nbsp;<s:date name="activityVo.activity_end" format="HH:mm" />				
			</span>
			<span style="float:right;">
				<span><i class="fa fa-map-marker" style="color:#6495ED"></i></span>
				<span><s:property value="activityVo.site_name" /></span>
			</span>
		</div>
		</div>
	</div>
	<div class="pinkline"></div>
	<div style="width:100%;background:#F9F9F9;padding:10px">
		<b style="font-size:18px">订单详情</b>
	</div>
	
	<div style="border:1px solid #F5F5F5;border-width:1 0px;padding:20px 0px">
		<div class="container">			
			<div style="padding:2px">订单号：<s:property value="enrollVo.pay_id" /></div>
			<div style="padding:2px">商品名称：比赛报名</div>
			<div style="padding:2px">比赛名称：<s:property value="enrollVo.activity_name" /></div>
			<div style="padding:2px">合计金额：￥<s:property value="enrollVo.original_cost" /></div>
			<div style="padding:2px">成绩抵扣：￥<s:property value="userCreditDetailVo.amnt/10" /></div>
			<div style="padding:2px">实付金额：￥<s:property value="enrollVo.cost" /></div>
			<div style="padding:2px">报名人数：<s:property value="enrollVo.number" />人</div>
		</div>	
	</div>
	
	<div class="container" style="margin-top:10px">	
		<s:if test="enrollVo.status==0">
			<s:form action="/weixin/pay_payAction.action" method="post" theme="simple" id="payEnrollform">
				<input type="hidden" name="enrollVo.enroll_id" value="${enrollVo.enroll_id}" />
				<input type="hidden" name="enrollVo.activity_id" value="${enrollVo.activity_id}" />
				<input type="hidden" name="userCreditDetailVo.amnt" value="${userCreditDetailVo.amnt}" />
				<span style="width:49%;" class="pull-left">
					<button class="btn btn-large btn-block btn-info" type="button" onclick="payEnroll();">支付费用</button>
				</span>			
			</s:form>
			<s:form action="/enroll/cancelEnroll_enrollMgrAction.action" method="post" theme="simple" id="cancelform">
				<input type="hidden" name="enrollVo.enroll_id" value="${enrollVo.enroll_id}" />
				<span style="width:49%;" class="pull-right">
					<button class="btn btn-large btn-block btn-warning" type="submit">取消报名</button>
				</span>
			</s:form>
		</s:if>
		<s:elseif test="enrollVo.status==1">
			<s:if test="enrollVo.sign_in ==0">
			<s:form action="/enroll/signIn_enrollMgrAction.action" method="post" theme="simple" id="signInform">
				<input type="hidden" name="enrollVo.enroll_id" value="${enrollVo.enroll_id}" />
				<input type="hidden" name="enrollVo.activity_id" value="${enrollVo.activity_id}" />
				<span style="width:49%;" class="pull-left">
					<button class="btn btn-large btn-block btn-success" type="button" onclick="signIn();">比赛签到</button>
				</span>			
			</s:form>
			<s:form action="/enroll/refund_enrollMgrAction.action" method="post" theme="simple" id="refundform">
				<input type="hidden" name="enrollVo.enroll_id" value="${enrollVo.enroll_id}" />
				<span style="width:49%;" class="pull-right">
					<button class="btn btn-large btn-block btn-warning" type="button" onclick="refund();">申请退款</button>
				</span>
			</s:form>
			</s:if>
		</s:elseif>
	</div>	
	</div>
</body>
