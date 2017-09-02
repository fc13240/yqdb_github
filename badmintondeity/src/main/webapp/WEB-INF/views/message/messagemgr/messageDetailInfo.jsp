<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>消息</title>
</head>
<body>
	<div class="topic">发送者</div>
	<div class="content">
		<div class="container" >
			<s:property value="messageReVo.publisher_id" />
		</div>
	</div>
	
	<div class="topic">时间</div>
	<div class="content">
		<div class="container">
			<s:date name="messageReVo.create_date" format="MM-dd HH:mm" />
		</div>
	</div>
	
	<div class="topic">主题</div>
	<div class="content">
		<div class="container" >
			<s:property value="messageReVo.theme" />
		</div>
	</div>
	
	<div class="topic">内容</div>
	<div class="content">
		<div class="container" >
			<s:property value="messageReVo.content" />
		</div>
	</div>
	
	
</body>


