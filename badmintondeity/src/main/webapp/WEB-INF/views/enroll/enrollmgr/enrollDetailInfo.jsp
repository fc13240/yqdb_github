<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
	<title>订单明细</title>
	<script type="text/javascript">

	</script>
</head>
<body>
	<s:form action="/enroll/updateEnroll_enrollMgrAction.action" method="post" theme="simple">
		<input type="hidden" name="enrollVo.activity_id" value="${enrollVo.activity_id}">
		<input type="hidden" name="enrollVo.user_id" value="${enrollVo.user_id}">
	</s:form>
		
		<div class="topic">比赛名称</div>
		<div class="content">
			<div class="container">${enrollVo.activity_name}</div>
		</div>
		
		<div class="topic">金额</div>
		<div class="content">
			<div class="container"><input type="text" name="enrollVo.cost" value="${enrollVo.cost}">￥</div>
		</div>
		<div class="topic">支付状态</div>
		<div class="content">
			<div class="container">
				<s:if test="enrollVo.status==0">
				<div class="radioDiv radioOn" name="statusDiv"><span class="radioSpan"><input type="radio" name="enrollVo.status" value="0" checked="checked" class="radioClass" /></span>未交费</div>
				<div class="radioDiv" name="statusDiv"><span class="radioSpan"><input type="radio" name="enrollVo.status" value="1" class="radioClass" /></span>已交费</div>
				</s:if>
				<s:elseif test="enrollVo.status==1">
					<div class="radioDiv" name="statusDiv"><span class="radioSpan"><input type="radio" name="enrollVo.status" value="0" class="radioClass" /></span>未交费</div>
					<div class="radioDiv radioOn" name="statusDiv"><span class="radioSpan"><input type="radio" name="enrollVo.status" value="1" checked="checked" class="radioClass" /></span>已交费</div>
				</s:elseif>
			</div>
		</div>
		<div class="topic">交费方式</div>
		<div class="content">
			<div class="container">
				<img src="/p2pstock/static/images/rrd/weixin_logo.png"/><span style="margin-left:5px">微信支付</span>				
			</div>
		</div>
		<div class="topic">操作</div>
		<div class="content">
			<span style="border:1px solid #F4A460;border;border-radius:5px;padding:1px 5px;font-size:14px"><a herf="#" onclick="moreEnrollinfo('${enroll_id}');">比赛签到</a></span>
			<span style="border:1px solid #F4A460;border;border-radius:5px;padding:1px 5px;font-size:14px"><a herf="#" onclick="moreEnrollinfo('${enroll_id}');">申请退费</a></span>
		</div>
		
		<div class="container" style="margin-top:10px"><input class="btn btn-large btn-block btn-info" type="submit" value="保存"></div>
	
</body>

	
