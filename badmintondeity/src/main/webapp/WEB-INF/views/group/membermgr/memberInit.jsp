<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
	<title>战队列表</title>	
	<script type="text/javascript">
		function moreUserinfo(user_id){
			$("#user_id").val(user_id);
			$("#moreUserInfoform").submit();			
		}
	</script>
</head>
<body>
	<s:form action="/usercenter/userCard_userCenterAction.action" method="post" theme="simple" id="moreUserInfoform">
		<s:hidden id="user_id" name="userInfoVo.user_id"></s:hidden>
	</s:form>
	<div class="content">
		<div class="container" style="text-align: center">
			<input type="hidden" name="groupVo.group_id" value="${groupVo.group_id}"/>
			<img type="text" id="logo" name="groupVo.group_logo" src="${groupVo.group_logo}" style="width: 80px; height: 80px; border: 1px solid #FFE4E1"/>
		</div>
		<div class="container" style="margin-top:10px;text-align:center">
		 	<span>${groupVo.group_name}</span>
		</div>
	</div>
		
	<div class="topic">创建者</div>
	<div class="content">
		<div class="container">			
			<s:iterator value="memberVoList" status="f">
				<s:if test="bamembers_identity==\"F\"">
					<div style="padding:5px">${user_name}</div>
				</s:if>
			</s:iterator>			
		</div>
	</div>
	
	<div class="topic">管理员</div>
	<div class="content">
		<div class="container">			
			<s:iterator value="memberVoList" status="m">				
				<s:if test="bamembers_identity==\"M\"">
					<div style="padding:5px">${user_name}</div>
				</s:if>				
			</s:iterator>
		</div>
	</div>
	
	<div class="topic">成员</div>
	<div class="content">
		<div class="container">			
			<s:iterator value="memberVoList" status="o">				
				<s:if test="bamembers_identity==\"O\"">
					<div style="padding:5px">${user_name}</div>					
				</s:if>
			</s:iterator>			
		</div>
	</div>
</body>

	
