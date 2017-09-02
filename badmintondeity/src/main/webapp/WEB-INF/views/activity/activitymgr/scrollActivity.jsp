<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript">
	var myScroll;
	function loaded() {
		myScroll = new IScroll('#wrapper', {
			preventDefault:false,
			scrollX: false,
			scrollY: true,
			momentum: false,
			scrollbars: true,
			snap: true
		});
		myScroll.on('scrollEnd', calPosition);
	}

	function calPosition() {
		if (this.maxScrollY != 0) {
			if (this.y == this.maxScrollY) {
				currentCount = currentCount + addCount;
				ajaxCommonSubmit('queryActivityform', 'queryActivityok',"/p2pstock/ajax/queryActivityByTimeArea_activityMgrAction.action?beginDate="+$("#beginDate").val()+"&areacode="+$("#area").val()+"&count="+currentCount);
				setTimeout(function () {
			        myScroll.refresh();
			    }, 500);
			}
			//alert("有滚动条且没滚动到底");
		}
		currentCount = currentCount + addCount;
		ajaxCommonSubmit('queryActivityform', 'queryActivityok',"/p2pstock/ajax/queryActivityByTimeArea_activityMgrAction.action?beginDate="+$("#beginDate").val()+"&areacode="+$("#area").val()+"&count="+currentCount);
		setTimeout(function () {
	        myScroll.refresh();
	    }, 500);
	}

</script>
</head>
<body >
	<div class="grayline"></div>

	<div id="wrapper" >
		<div id="scroller">
			<ul id="scrollerUl">
				<s:if test="allActivity.size()>0">
					<s:iterator value="allActivity" status="s">
						<li onclick="moreActivityinfo('${activity_id}');">
							<div>
								<span><b><s:property value="activity_name" /></b></span>
								<span style="float:right">
									<span style="color:#FF8C00;">￥<b><s:property value="enroll_roll_id" /></b></span>
									<span style="font-size:12px;">已报名<s:property value="enroll_roll_id" />人</span>
								</span>
							</div>
							<div style="font-size:12px;padding:1px">
								<span><i class="fa fa-clock-o" style="color:#6495ED"></i>
									<s:date name="activity_begin" format="MM.dd" />&nbsp;&nbsp;<s:date name="activity_begin" format="HH:mm" />&nbsp;-&nbsp;<s:date name="activity_end" format="HH:mm" />				
								</span>
								<span style="float:right;width:60%">
									<span><i class="fa fa-map-marker" style="color:#6495ED"></i></span>
									<span><s:property value="site_name" /></span>
									<span style="float:right">xxkm</span>
								</span>
							</div>
						</li>
					</s:iterator>
				</s:if>
				<s:else>
					<li><div>今日没有比赛哦</div></li>
				</s:else>
			</ul>
		</div>
	</div>

</body>
