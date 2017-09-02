<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript">	
	function changeArea(obj,areaCode){
		$('div a').each(function(){
			$(this).removeClass("btn-info");
		});
		area = areaCode;		
		$(obj).addClass("btn-info");
		ajaxCommonSubmit('areaForm', 'querySiteok',"/p2pstock/site/querySite_siteMgrAction.action?province=110000&city="+city+"&area="+area);
	}
	
	function querySiteok(msg){
		$('#siteDiv').html(msg);
	} 
</script>
</head>
<fieldset style="width:100%;border-radius:2px;background-color:#F8F8F8;padding:2px;">
	<div>
		<s:iterator value="areaList" status="st">
			<div style="float:left;margin:2px;"><a herf="#" style="padding:2px 4px;" onclick="changeArea(this,'${area_code}');">${area_name}</a></div>
		</s:iterator>
	</div>
</fieldset>


