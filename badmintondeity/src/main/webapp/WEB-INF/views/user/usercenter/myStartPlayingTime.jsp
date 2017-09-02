<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<html>
<head>
<!-- mobiscroll -->
<script src="/p2pstock/static/js/datetime/mobiscroll.core-2.6.2.js"	type="text/javascript"></script>
<script src="/p2pstock/static/js/datetime/mobiscroll.core-2.6.2-zh.js"	type="text/javascript"></script>
<script src="/p2pstock/static/js/datetime/mobiscroll.datetime-2.6.2.js"	type="text/javascript"></script>
<script	src="/p2pstock/static/js/datetime/mobiscroll.android-ics-2.6.2.js"	type="text/javascript"></script>
<link href="/p2pstock/static/css/datetime/mobiscroll.core-2.6.2.css" rel="stylesheet" type="text/css" />
<link href="/p2pstock/static/css/datetime/mobiscroll.android-ics-2.6.2.css" rel="stylesheet" type="text/css" />
<title>我的球龄</title>

<script type="text/javascript">
$(function($){
    var currYear = (new Date()).getFullYear();    
    var optDate = {  
            preset: 'date', //日期  
            theme: 'android-ics light', //皮肤样式  
            display: 'modal', //显示方式   
            mode: 'scroller', //日期选择模式  
            lang:'zh',  
            showNow: true,  
            nowText: "今天",  
            startYear:currYear-40, //开始年份  
            endYear:currYear//结束年份  
        };  

	$("#startPlayingTime").mobiscroll(optDate);  

 });
</script>
    
</head>
<body>
	<div class="container">
	<p>开始打球时间</p>		
	<span id="startPlayingTime"
					style="border: 1px solid #DCDCDC; border-radius: 2px; padding: 2px 5px">1989-10-02</span>
	<input type="button" class="btn" value="确定">
	</div>
</body>
</html>