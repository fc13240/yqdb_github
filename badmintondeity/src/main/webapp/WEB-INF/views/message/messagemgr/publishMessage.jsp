<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript">
	function publishiMessage() {
		$("#publishMessageform").submit();
	}
	
</script>
</head>
	<s:form action="/message/publishMessage_messageMgrAction.action" method="post" theme="simple" id="publishMessageform">
	<div class="topic">消息类型</div>
	<div class="content">
		<div class="container" >
		<select name="messageVo.message_type">
			<option value="S">系统消息</option>
			<option value="G">战队消息</option>
			<option value="P">个人消息</option>
		</select>
		</div>
	</div>
	
	<div class="topic">主题</div>
	<div class="content">
		<div class="container" >
		<input type="text" name="messageVo.theme">
		</div>
	</div>
	
	<div class="topic">内容</div>
	<div class="content">
		<div class="container" >
		<input type="text" name="messageVo.content">
		</div>
	</div>
	
	<div class="content">
		<div class="container" >
			<button class="btn btn-large btn-block btn-success" type="submit">发送</button>
		</div>
	</div>
	</s:form>