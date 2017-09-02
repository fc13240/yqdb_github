<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta name="renderer" content="webkit" />
	
	<title>支付成功</title>
	<link href="/p2pstock/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div style="width:100%;background:#F9F9F9;text-align:center;padding:10px">
		<img src="/p2pstock/static/images/rrd/success.png" style="width:50px;height:50px;margin-right:5px"><b style="font-size:18px">恭喜您，报名成功!</b>
	</div>
	
	<div style="border:1px solid #F5F5F5;border-width:1 0px;padding:20px 0px">
		<div class="container">
			<div style="padding:2px">交易单号：<s:property value="enrollVo.settle_id" /></div>
			<div style="padding:2px">商品订单号：<s:property value="enrollVo.pay_id" /></div>
			<div style="padding:2px">付款金额：<s:property value="enrollVo.cost" /></div>
			<div style="padding:2px">报名人数：<s:property value="enrollVo.number" /></div>
		</div>	
	</div>
	<div class="container" style="margin-top:20px">
		<button class="btn btn-large btn-block btn-info" type="button" onclick="location='/p2pstock/init/index.action?menucode=INDEXPAGE'">返回首页</button>
	</div>	
</body>
