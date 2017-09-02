<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<html>
<head>
<title>我的账户</title>
</head>
<body>
	<div style="padding:10px"  onclick="location='/p2pstock/usercenter/queryUserCredit_userCenterAction.action'">
		<span><img src="/p2pstock/static/images/rrd/feather.png" width=20px/></i></span>
		<span>我的成绩</span>
		<span class="pull-right">
			冻结:<span style="color:blue"><s:property value="userCreditVo.acc_freeze"/></span>
			全部:<span style="color:#FFA500"><s:property value="userCreditVo.acc_balance"/></span>
		</span>
		
	</div>
	<div class="pinkline"></div>
	<div class="container">
		<s:iterator value="userCreditDetailList" status="st">			
			<div style="padding:10px">
				<table style="width:100%">
					<tr>
						<td width=50%>
							<div><s:date name="create_date" format="yyyy年MM月dd日" /></div>
						</td>
						<td width=20% align=center>
							<s:if test="bs_type==\"S\"">
								系统
							</s:if>
							<s:elseif test="bs_type==\"A\"">
								比赛
							</s:elseif>
						</td>
						<td width=30% align=right>
							<s:if test="acc_tran_type==\"I\"">
								<span style="color:red">+${amnt}</span>
							</s:if>
							<s:elseif test="acc_tran_type==\"D\"">
								<span style="color:green">-${amnt}</span>
							
							</s:elseif>
							<s:elseif test="acc_tran_type==\"F\"">
								<span style="color:blue">${amnt}</span>							
							</s:elseif>
						</td>
					</tr>				
				</table>
			</div>
			<s:if test="st!=0">
				<div class="pinkline"></div>
			</s:if>
		</s:iterator>
	</div>
</body>
</html>