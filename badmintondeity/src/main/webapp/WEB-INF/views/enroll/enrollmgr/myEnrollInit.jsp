<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>我参加的比赛</title>
<script type="text/javascript">
	function moreEnrollinfo(enroll_id) {
		$("#enroll_id").val(enroll_id);
		$("#moreEnrollInfoForm").submit();
	}

	//获取滚动条当前的位置
	function getScrollTop() {
		var scrollTop = 0;
		if (document.documentElement && document.documentElement.scrollTop) {
			scrollTop = document.documentElement.scrollTop;
		} else if (document.body) {
			scrollTop = document.body.scrollTop;
		}
		return scrollTop;
	}

	//获取当前可是范围的高度
	function getClientHeight() {
		var clientHeight = 0;
		if (document.body.clientHeight && document.documentElement.clientHeight) {
			clientHeight = Math.min(document.body.clientHeight,
					document.documentElement.clientHeight);
		} else {
			clientHeight = Math.max(document.body.clientHeight,
					document.documentElement.clientHeight);
		}
		return clientHeight;
	}

	//获取文档完整的高度
	function getScrollHeight() {
		return Math.max(document.body.scrollHeight,
				document.documentElement.scrollHeight);
	}
</script>
</head>
<body>
	<s:form action="/enroll/queryEnrollDetailInfo_enrollMgrAction.action"
		method="post" theme="simple" id="moreEnrollInfoForm">
		<s:hidden id="enroll_id" name="enrollVo.enroll_id"></s:hidden>
	</s:form>
	<div style="margin-top:5px">
		<ul id="myTab" class="nav nav-tabs" style="width: 100%">
			<li class="active" style="width: 25%; text-align: center"><a href="#all" data-toggle="tab">全部</a></li>
			<li style="width: 25%; text-align: center"><a href="#payed" data-toggle="tab">已付款</a></li>
			<li style="width: 25%; text-align: center"><a href="#unpay" data-toggle="tab">待付款</a></li>
			<li style="width: 25%; text-align: center"><a href="#refund" data-toggle="tab">已取消</a></li>
		</ul>
	</div>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="all">			
			<s:if test="enrollVoList.size()>0">
				<s:iterator value="enrollVoList" status="all">					
					<div onclick="moreEnrollinfo('${enroll_id}');" style="margin:15px;border:1px solid #C0C0C0">
						<div style="background-color:#F5F5F5;padding:5px">订单号：${pay_id}</div>	
						<div class="container" style="padding:5px">
							<s:property value="activity_name" /><span style="color: #696969;" class="pull-right">金额：￥<s:property value="cost" /></span> 											
						</div>						
					</div>					
				</s:iterator>
			</s:if>
			<s:else>
				<div class="container" style="padding:5px 15px">
					还没参加过比赛？快来 <a href="/p2pstock/activity/queryActivityInit_activityMgrAction.action">找比赛</a>吧
				</div>
			</s:else>			
		</div>
		<div class="tab-pane fade" id="payed">
			<s:if test="payedList.size()>0">
				<s:iterator value="payedList" status="payed">
					<div onclick="moreEnrollinfo('${enroll_id}');" style="margin:15px;border:1px solid #C0C0C0">
						<div style="background-color:#F5F5F5;padding:5px">订单号：${pay_id}</div>	
						<div class="container" style="padding:5px">
							<s:property value="activity_name" /><span style="color: #696969;" class="pull-right">实付款：￥<s:property value="cost" /></span> 											
						</div>						
					</div>					
				</s:iterator>
			</s:if>
			<s:else>
				<div class="container" style="padding:5px 15px">
					没有符合条件的订单
				</div>
			</s:else>
		</div>
		<div class="tab-pane fade" id="unpay">			
			<s:if test="unpayList.size()>0">				
				<s:iterator value="unpayList" status="unpay">
					<div onclick="moreEnrollinfo('${enroll_id}');" style="margin:15px;border:1px solid #C0C0C0">
						<div style="background-color:#F5F5F5;padding:5px">订单号：${pay_id}</div>	
						<div class="container" style="padding:5px">
							<s:property value="activity_name" /><span style="color: #696969;" class="pull-right">待付款：￥<s:property value="cost" /></span> 											
						</div>						
					</div>	
				</s:iterator>
			</s:if>
			<s:else>
				<div class="container" style="padding:5px 15px">
					没有符合条件的订单
				</div>
			</s:else>
		</div>
		<div class="tab-pane fade" id="refund">			
			<s:if test="refundList.size()>0">
				<s:iterator value="refundList" status="refund">
					<div onclick="moreEnrollinfo('${enroll_id}');" style="margin:15px;border:1px solid #C0C0C0">
						<div style="background-color:#F5F5F5;padding:5px">订单号：${pay_id}</div>	
						<div class="container" style="padding:5px">
							<s:property value="activity_name" /><span style="color: #696969;" class="pull-right">金额：￥<s:property value="cost" /></span> 											
						</div>						
					</div>
				</s:iterator>
			</s:if>
			<s:else>
				<div class="container" style="padding:5px 15px">
					没有符合条件的订单
				</div>
			</s:else>
		</div>
	</div>


	<script>
		window.onscroll = function() {
			if (getScrollTop() + getClientHeight() == getScrollHeight()) {

			}
		};
	</script>
</body>


