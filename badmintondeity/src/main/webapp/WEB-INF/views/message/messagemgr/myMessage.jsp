<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
	<title>我的消息</title> 
	<script type="text/javascript">
	function messageInfo(message_id) {
		$("#message_id").val(message_id);
		$("#messageDetailInfoform").submit();
	}
</script>
</head>
<body>
	<s:form action="/message/queryMessageDetail_messageMgrAction.action"
		method="post" theme="simple" id="messageDetailInfoform">
		<s:hidden id="message_id" name="messageReVo.message_id"></s:hidden>
	</s:form>	
	
	<div class="content">
		<s:iterator value="messageReList" status="st">
		<div class="pinkline"></div>
		<div onclick="messageInfo('${message_id}');">
			<div class="container">
				<div><span>来自<s:property value="publisher_id" />:</span>
					 <span style="float:right"><s:date name="create_date" format="MM-dd HH:mm" /></span>
				</div>
				<div><p><s:property value="theme" /></p></div>
			</div>	
		</div>
				
		</s:iterator>			
	</div>
	
</body>