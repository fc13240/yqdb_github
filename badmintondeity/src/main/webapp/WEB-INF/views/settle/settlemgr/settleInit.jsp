<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>比赛一览</title>
<script type="text/javascript">
	function enrollInfo(activity_id) {
		$("#enrollInfoform").attr("action","/p2pstock/enroll/queryAllEnroll_enrollMgrAction.action?activityId="+activity_id);
		$("#enrollInfoform").submit();
	}
</script>

</head>
<body>
	<s:form action="/enroll/queryAllEnroll_enrollMgrAction.action" method="post" theme="simple" id="enrollInfoform">
	</s:form>
	<div class="topic">未结算的比赛</div>
	<div class="content">
		<s:iterator value="notSettledList" status="st">
		<div class="container" style="padding-top:5px" onclick="enrollInfo('${activity_id}');">			
			<div>
				<span><s:property value="activity_name" /></span>				
			</div>
			<div>
				<span><i class="fa fa-fw fa-clock-o" style="width:15px;color:#6495ED"></i></span>
				<s:date name="activity_begin" format="MM-dd HH:mm" />&nbsp;-&nbsp;<s:date name="activity_end" format="HH:mm" />
			</div>
			<s:if test="#st.count != notSettledList.size()">
				<div class="pinkline"></div>
			</s:if>
		</div>
		</s:iterator>
	</div>

	<div class="topic">已结算的比赛</div>
	<div class="content">
		<s:iterator value="settledList" status="std">
		<div class="container" style="padding-top:5px" onclick="enrollInfo('${activity_id}');">			
			<div>
				<span><s:property value="activity_name" /></span>				
			</div>
			<div>
				<span><i class="fa fa-fw fa-clock-o" style="width:15px;color:#6495ED"></i></span>
				结算时间:&nbsp;<s:date name="update_date" format="yyyy-MM-dd HH:mm" />
			</div>
			<s:if test="#std.count != settledList.size()">				
				<div class="grayline"></div>
			</s:if>
		</div>		
		</s:iterator>
	</div>
</body>


