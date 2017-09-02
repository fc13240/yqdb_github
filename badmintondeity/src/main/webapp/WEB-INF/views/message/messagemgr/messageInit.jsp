<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
	<title>我的消息</title> 
</head>
<body>	
	<div class="container" onclick="location='/p2pstock/message/queryMyMessage_messageMgrAction.action'">
		<div style="padding:10px 0px"><img src="/p2pstock/static/images/rrd/systeme_message.png"><span style="margin-left:10px;padding:5px">系统公告</span></div>
	</div>
	<div class="pinkline"></div>
	
	<div class="container" onclick="location='/p2pstock/message/queryMyMessage_messageMgrAction.action'">
		<div style="padding:10px 0px"><img src="/p2pstock/static/images/rrd/my_message.png"><span style="margin-left:10px;padding:5px">站内信</span></div>
	</div>
	<div class="pinkline"></div>
	
	<div class="container" onclick="location='/p2pstock/message/publishInit_messageMgrAction.action'">
		<div style="padding:10px 0px"><img src="/p2pstock/static/images/rrd/write_message.png"><span style="margin-left:10px;padding:5px">发消息</span></div>
	</div>	
</body>