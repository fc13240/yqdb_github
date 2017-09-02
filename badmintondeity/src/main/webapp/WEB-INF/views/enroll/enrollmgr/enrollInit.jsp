<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>报名记录</title>
<script type="text/javascript">
	function moreEnrollinfo(activity_id, user_id) {
		$("#activity_id").val(activity_id);
		$("#user_id").val(user_id);
		$("#moreEnrollInfoform").submit();
	}
</script>
</head>
<body>
	<s:form action="/enroll/queryEnrollDetailInfo_enrollMgrAction.action"
		method="post" theme="simple" id="moreEnrollInfoform">
		<s:hidden id="activity_id" name="enrollVo.activity_id"></s:hidden>
		<s:hidden id="user_id" name="enrollVo.user_id"></s:hidden>
	</s:form>
	
	
	<div class="topic">报名列表</div>
	<s:if test="enrollVoList.size()>0">
		<s:iterator value="enrollVoList" status="st">
			<div class="content">
				<div class="container">
					<table style="width:100%">
						<tr>
							<td width="20%"><s:property value="user_name" /></td>
							<td width="20%"><s:property value="original_cost" />￥</td>
							<td width="20%"><s:property value="number" />人</td>
							<td width="20%">
								<s:if test="status==0">未交费</s:if>
								<s:elseif test="status==1">已交费</s:elseif>
							</td>
							<td width="20%">
								<s:if test="status==1">
									<s:if test="pay_type==0">支付宝</s:if>
									<s:elseif test="pay_type==1">微信</s:elseif>
									<s:elseif test="pay_type==2">线下</s:elseif>
									<s:elseif test="pay_type==3">平台账户</s:elseif>
								</s:if>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<s:if test="#st.count != enrollVoList.size()">
				<div class="pinkline"></div>
			</s:if>
		</s:iterator>
	</s:if>
	<s:else>
		<div class="content">
			<div class="container">无交费记录</div>
		</div>
	</s:else>

	
	<div class="container" style="margin-top:10px;">
		<s:if test="showSettleButton">
			<div style="float:left;text-align:center;padding-top:5px;width:50%">合计:￥<span style="font-size:18px"><s:property value="total_fee"/></span></div>
			<div style="float:right;text-align:center;width:50%">
				<s:form action="/enroll/settleUp_enrollMgrAction.action" method="post" theme="simple">
				<input type="hidden" name="enrollVo.activity_id" value="${activityVo.activity_id}"/>
				<input type="hidden" name="total_fee" value="${total_fee}"/>
				<button class="btn btn-large btn-block btn-info" type="submit">结算</button>
				</s:form>
			</div>	
		</s:if>
		<s:else>
			<div style="text-align:center;padding:10px">合计:￥<span style="font-size:18px"><s:property value="total_fee"/></span></div>
		</s:else>
	</div>
	
</body>


