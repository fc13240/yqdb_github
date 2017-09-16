<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript">
	function queryAllActivity() {
		$("#allActivityform").submit();
	}
	function moreActivityinfo(t){
		var i="/p2pstock/activity/queryActivityDetailInfo_activityMgrAction.action?activityId="+t;
		$("#activity_id").val(t);
		$("#moreActivityInfoform").attr("action",i);
		$("#moreActivityInfoform").submit();
	}
</script>
</head>
	<s:form action="/activity/queryActivityDetailInfo_activityMgrAction.action" method="post" theme="simple" id="moreActivityInfoform">
		<s:hidden id="activity_id" name="activityVo.activity_id"></s:hidden>
	</s:form>
	<s:form action="/activity/queryActivityInit_activityMgrAction.action" method="post" theme="simple" id="allActivityform">
		<s:hidden name="activityVo.activity_id"></s:hidden>
	</s:form>
	
	<div class="container" >	
	<s:iterator value="newActivity" status="st">
		<div class="container" style="padding:5px 0px" onclick="moreActivityinfo('${activity_id}');">			
			<div>
				<span><b><s:property value="activity_name" /></b></span>
				<span style="float:right">
					<!--<span style="color:#FF8C00;">￥<b><s:property value="enroll_roll_id" /></b></span>-->
					<span style="font-size:12px;">缺赛扣除积分</span><span style="color:#FF8C00;"><b><s:property value="enroll_roll_id" /></b></span>
					<!--<span style="font-size:12px;">已报名  xx/xx人</span>-->
				</span>
			</div>
			<div style="font-size:12px;padding:1px">
				<span><i class="fa fa-clock-o" style="color:#6495ED"></i>
					<s:date name="activity_begin" format="MM.dd" />&nbsp;&nbsp;<s:date name="activity_begin" format="HH:mm" />&nbsp;-&nbsp;<s:date name="activity_end" format="HH:mm" />				
				</span>
				<span style="float:right;width:60%">
					<span><i class="fa fa-map-marker" style="color:#6495ED"></i></span>
					<span><s:property value="site_name" /></span>
					<!--<span style="float:right">xxkm</span>-->
				</span>
			</div>
		</div>
		<s:if test="#st.index != 2">
			<div class="grayline"></div>
		</s:if>
	</s:iterator>
	</div>
	