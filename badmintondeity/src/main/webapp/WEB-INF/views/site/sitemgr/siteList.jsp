<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link href="/p2pstock/static/css/iscroll.css" rel="stylesheet">
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
	}

</script>

</head>
<body onload="loaded()">
	<div class="grayline"></div>
	<div id="wrapper" style="height:320px">
		<div id="scroller" >
			<s:if test="siteVoList.size()>0">
				<s:iterator value="siteVoList" status="s">
					<div style="padding:2px 0px 2px 10px">
						<label style="font-weight:normal"><input type="radio" name="addr" value="${site_addr_id}"><span>${site_name}</span></label>
					</div>
				</s:iterator>
			</s:if>
			<s:else>
				<li>没找到地点</li>
			</s:else>			
		</div>
	</div>
</body>
