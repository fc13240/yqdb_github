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
				border-bottom: 2px solid #E5E5E5;
				width: 100%;
			}
			#currentMode,#currentEquip{
				height: 86px;
				width: 100%;
				box-sizing: border-box;
				padding-left: 33px;
				padding-right: 30px;
				border-bottom: 2px solid #E5E5E5;
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
			#sportTime{
				height: 30px;
				margin-top: 100px;
			}
			#sportTimeBox{
				height: 30px;
				width: 100px;
				margin: 0 auto;
			}
			#sportTimeImg,#sportTimeDate{
				float: left;
			}
			#sportTimeImg{
				height: 30px;
				width: 30px;
			}
			#sportTimeDate{
				color: red;
				font-size: 30px;
				width: 60px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				margin-left: 10px;
			}
			#sportEnd{
				width: 100%;
				font-size: 36px;
				height: 36px;
				line-height: 36px;
				text-align: center;
				margin-top: 32px;
				color: #666666;
			}
			#bottom{
				width: 100%;
			}
			#bottomImg{
				width: 410px;
				height: 410px;
				background: url(/p2pstock/static/images/ty-1@2x.png) no-repeat 0 0;
				margin: 0 auto;
				color: white;
				font-size: 57px;
				line-height: 410px;
				text-align: center;
			}
			#detailedInfo{
				width: 100%;
				height: 220px;
				background: #F5F5F5;
				padding: 12px 50px 12px 36px;
				box-sizing: border-box;
			}
			.detailedInfoTxt{
				width: 100%;
				height: 65px;
			}
			.detailedInfoTxtTitle{
				float: left;
				color: black;
				height: 65px;
				line-height: 65px;
				font-size: 32px;
			}
			.detailedInfoTxtUnits,.detailedInfoTxtNumber{
				float: right;
				color: #999999;
				height: 65px;
				line-height: 65px;
			}
			.detailedInfoTxtUnits{
				font-size: 32px;
			}
			.detailedInfoTxtNumber{
				font-size: 26px;
			}
			#bottomTutton{
				width: 100%;
				height: 175px;
				padding-top: 75px;
			}
			#bottomTuttonTxt{
				height: 90px;
				width: 500px;
				color: white;
				border-radius: 10px;
				line-height: 90px;
				text-align: center;
				font-size: 32px;
				margin: 0 auto;
				background: #F9615C;
			}
		</style>
</head>
	<body>
		<div id="header">
			<div id="leftImg">
				<img src="images/left.png"/>
			</div>
			<div id="headerTitle">一起动</div>
			<div id="rightImg"><img src="images/option.png"/></div>
		</div>
		<div id="blank"></div>
		<div id="currentMode">
			<div id="currentMode1">当前模式</div>
			<div id="currentMode2"><img src="images/Path@3x.png"/></div>
			<div id="currentMode3">运动模式</div>
		</div>
		<div id="currentEquip">
			<div id="currentEquip1">当前设备</div>
			<div id="currentEquip2"><img src="images/Path@3x.png"/></div>
			<div id="currentEquip3">家用设备</div>
		</div>
		<div id="sportTime">
			<div id="sportTimeBox">
				<div id="sportTimeImg"><img src="images/sj@2x.png"/></div>
				<div id="sportTimeDate">2:59</div>
			</div>
		</div>
		<div id="sportEnd">运动步数</div>
		<div id="bottom">
			<div id="bottomImg">6530</div>
		</div>
		<div id="detailedInfo">
			<div class="detailedInfoTxt">
				<div class="detailedInfoTxtTitle">累计时长</div>
				<div class="detailedInfoTxtUnits">分钟</div>
				<div class="detailedInfoTxtNumber">3</div>
			</div>
			<div class="detailedInfoTxt">
				<div class="detailedInfoTxtTitle">累计消耗</div>
				<div class="detailedInfoTxtUnits">卡路里</div>
				<div class="detailedInfoTxtNumber">1200</div>
			</div>
			<div class="detailedInfoTxt">
				<div class="detailedInfoTxtTitle">平均速度</div>
				<div class="detailedInfoTxtUnits">步/s</div>
				<div class="detailedInfoTxtNumber">5</div>
			</div>
		</div>
		<div id="bottomTutton">
			<div id="bottomTuttonTxt">结束运动</div>
		</div>
	</body>

	
