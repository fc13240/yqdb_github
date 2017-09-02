<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<table class="tb_table_font tb_table">
	<tr>
		<th class="tb_th_color" >银行卡号</th>
		<th class="tb_th_color" >所属银行</th>
		<th class="tb_th_color" >开户行网点</th>
		<th class="tb_th_color" >是否默认</th>
		<th class="tb_th_color" >更新日期</th>
	</tr>
	<s:iterator value="userBankList" status="s">
		<tr>
			<td class="tb_td_color"><s:property value="bank_acc_code"/></td>
			<td class="tb_td_color"><s:property value="bank_name"/></td>
			<td class="tb_td_color"><s:property value="bank_branch_name"/></td>
			<td class="tb_td_color">
			<s:if test="default_flag == 0">
				否
			</s:if>
			<s:else>
				是
			</s:else>
			<td class="tb_td_color"><s:property value="update_date"/></td>	
		</tr>
	</s:iterator>
</table>