<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
	<title>比赛一览</title>
	<script src="/p2pstock/static/js/iscroll/iscroll.js"></script>
	<script src="/p2pstock/static/js/activity/activity.js"></script>
	<link href="/p2pstock/static/css/iscroll.css" rel="stylesheet">
	<script type='text/javascript'>
		document.addEventListener('touchmove', function(e) {
			e.preventDefault();
		}, false);
	</script>
</head>
<body>
	
	<s:form action="/activity/queryActivityDetailInfo_activityMgrAction.action" method="post" theme="simple" id="moreActivityInfoform">
		<s:hidden id="activity_id" name="activityVo.activity_id"></s:hidden>
	</s:form>
	<div style="width:100%;overflow:hidden;position:relative">
	<input type="hidden" id="beginDate">
	<!-- 时间滚动条 -->
	<div class="plist" id="plist" style="z-index:9">
		<div id="dateul" class="dateul"></div>
	</div>
	<div class="pinkline"></div>
	<!-- 区县滚动条 -->
	<div class="alist" id="areaList" style="z-index:8">
		<div id="areaul" class="dateul">
		<s:iterator value="areaList" status="st">
			<s:if test="#st.index==0">
				<input type="hidden" id="area" value="${area_name}">
				<div class="areadiv areadivActive" name="areadiv" onclick="queryByArea('${area_name}');">${area_name}</div>
			</s:if>
			<s:else>
				<div class="areadiv" name="areadiv" onclick="queryByArea('${area_name}');">${area_name}</div>
			</s:else>
		</s:iterator>
		</div>
	</div>
	<div class="pinkline"></div>
	<!-- 比赛滚动栏 -->
	<div id="activityDiv" style="z-index:7"></div>
	</div>
</body>