<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
<script src="/p2pstock/static/js/uploadify/ajaxfileupload.js"></script>
<script type="text/javascript">

</script>
		<style type="text/css">
			body{
				margin: 0;
			}
			#header{
				background: #393a3f;
				width: 100%;
				height: 100px;
				padding: 0 40px;
				box-sizing: border-box;
			}
			#leftImg,#headerTitle{
				float: left;
			}
			#leftImg{
				width: 38px;
				margin-top: 31px;
			}
			#headerTitle{
				color: white;
				font-size: 33px;
				height: 100px;
				line-height: 100px;
				margin-left: 40px;
			}
			#rightImg{
				width: 10px;
				float: right;
				margin-top: 30px;
			}
			#blank{
				height: 32px;
				border-bottom: 1px solid #E5E5E5;
				width: 100%;
			}
			#currentMode,#currentEquip{
				height: 86px;
				width: 100%;
				box-sizing: border-box;
				padding-left: 33px;
				padding-right: 30px;
				border-bottom: 1px solid #E5E5E5;
			}
			#currentMode1,#currentEquip1{
				float: left;
			}
			#currentMode2,#currentMode3,#currentEquip2,#currentEquip3{
				float: right;
			}
			#currentMode1,#currentEquip1{
				font-size: 36px;
				color: black;
				height: 86px;
				line-height: 86px;
			}
			#currentMode2,#currentEquip2{
				margin-top: 24px;
			}
			#currentMode3,#currentEquip3{
				font-size: 36px;
				color: #999999;
				height: 86px;
				line-height: 86px;
				margin-right: 34px;
			}
			#readyStart{
				width: 100%;
				font-size: 36px;
				height: 36px;
				line-height: 36px;
				text-align: center;
				margin-top: 172px;
				color: #666666;
			}
			#bottom{
				width: 100%;
				padding: 34px 0 200px;
			}
			#bottomImg{
				width: 615px;
				height: 615px;
				background: url(/p2pstock/static/images/ty-1@3x.png) no-repeat 0 0;
				margin: 0 auto;
				color: white;
				font-size: 56px;
				line-height: 615px;
				text-align: center;
			}
		</style>
</head>
	<body>
		<div id="blank"></div>
		<div id="currentMode">
			<div id="currentMode1">当前模式</div>
			<div id="currentMode2"><img src="/p2pstock/static/images/Path@3x.png"/></div>
			<div id="currentMode3">普通模式</div>
		</div>
		<div id="currentEquip">
			<div id="currentEquip1">当前设备</div>
			<div id="currentEquip2"><img src="/p2pstock/static/images/Path@3x.png"/></div>
			<div id="currentEquip3">家用设备</div>
		</div>
		<div id="readyStart">准备开始</div>
		<div id="bottom">
			<div id="bottomImg">
				<a href="/p2pstock/train/trainRuning_trainMgrAction.action">
            		<span>开始</span>
            	</a>
			</div>
		</div>
	</body>

	
