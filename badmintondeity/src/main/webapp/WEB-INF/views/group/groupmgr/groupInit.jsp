<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
	<title>战队列表</title>	
	<script type="text/javascript">
		function moreGroupinfo(group_id){
			$("#group_id").val(group_id);
			$("#moreGroupInfoform").submit();			
		}
	</script>
</head>
<body>
	<s:form action="/group/queryGroupDetailInfo_groupMgrAction.action" method="post" theme="simple" id="moreGroupInfoform">
		<s:hidden id="group_id" name="groupVo.group_id"></s:hidden>
	</s:form>
	<div class="topic">战队列表</div>
	<div style="background-color:#fefefe;padding:5px 0px">
		<div class="container">			
			<s:iterator value="groupVoList" status="st">
				<div class="container" style="padding:5px 0px" onclick="moreGroupinfo('${group_id}');">
					<div style="float:left;padding:5px;margin-right:10px">
						<img type="text" id="logo" name="groupVo.group_logo" src="${group_logo}" style="width:50px;height:50px;" class="img-circle"/>
					</div>
					<div style="float:left;padding:5px">
						<div><b><s:property value="group_name"/></b></div>						
						<div>
							<span>成员数：<s:property value="member_count"/></span>
						</div>
					</div>
				</div>
				<s:if test="#st.count != groupVoList.size()">				
					<div class="pinkline"></div>
				</s:if>
			</s:iterator>			
		</div>
	</div>
</body>

	
