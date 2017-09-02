<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/usrmgr/usrmgr.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>系统管理>用户管理>用户查询 </title>
</head>
<body>

<div class="grid_10">
	<div style="position:absolute;padding-left:250px;color:red;"><label id="lerror" class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
	<s:form action="/usrmgr/queryMoreInfo_usrMgrAction.action" method="post" theme="simple" id="moreinfoform">
		<input type="hidden" id="queryuserid" name="queryuserid" value="${queryuserid}"/>
	</s:form>
	<s:form action="usrmgr/queryUserInfo_usrMgrAction.action" method="post" theme="simple" id="userQueryForm">
	<div class="p20bs color-white-bg fn-clear">
			<div style="padding-left:300px;color:red;">
				<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
			</div>
			<fieldset style="border:0px">
				<legend class="fieldset-lgend">查询条件</legend>
				<table style="margin:auto">				 
				 	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">用户手机号</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" id="phone" name="userInfoVo.phone" value="${userInfoVo.phone}"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">用户姓名</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" id="user_name" name="userInfoVo.user_name" value="${userInfoVo.user_name}"/>
				 		</td>
				 	</tr>
				 	<tr height="20px"><td colspan="6" align="center"></td></tr>
				 	<tr>
				 		<td colspan="6" align="center">
				 			<input type="button" id="secBtn" class="ui-button-rrd-blue ui-button-rrd-blue-large" value='查询'">
				 		</td>
				 	</tr>
				</table>
			</fieldset>
		</div>
	<div class="p20bs color-white-bg fn-clear" >
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">查询列表</legend>
			<div id="baseprtdiv">
				<table class="tb_table_font tb_table">
						<tr>
							<th class="tb_th_color" >用户手机号</th>
							<th class="tb_th_color" >用户姓名</th>
							<th class="tb_th_color" >用户昵称</th>
							<th class="tb_th_color" >用户类型</th>
							<th class="tb_th_color" >用户状态</th>
							<th class="tb_th_color" >操作</th>
						</tr>
						<s:iterator value="userlist" status="s">
							<tr>
								<td class="tb_td_color"><s:property value="phone"/></td>
								<td class="tb_td_color"><s:property value="user_name"/></td>
								<td class="tb_td_color"><s:property value="user_nickname"/></td>
								<td class="tb_td_color"><s:property value="user_typename"/></td>
								<td class="tb_td_color"><s:property value="user_statusname"/></td>
								<td class="tb_td_color">
									<s:if test="user_status == C">
										<a href="javascript:unlock('${ustl_id}','${invt_product_id}');">解锁</a>
									</s:if>
									<a href="javascript:moreinfo('${user_id}');">详情</a>
								</td>
							</tr>
						</s:iterator>
				</table>
				<!-- 分页标签 -->
				<div>
					<tag:pages page="${page}" formid="userQueryForm"></tag:pages>
				</div>
			</div>
		</fieldset>
	</div>
	</s:form>
</div>
</body>
</html>