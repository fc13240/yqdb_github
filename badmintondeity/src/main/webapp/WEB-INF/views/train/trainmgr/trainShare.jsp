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
			#mainContent{
				margin: 32px;
				background: #00B588;
				height: 690px;
				padding-top: 60px;
			}
			#headPortrait{
				height: 120px;
				width: 120px;
				border-radius: 50%;
				margin: 0 auto;
				overflow: hidden;
			}
			#nickName{
				color: white;
				font-size: 26px;
				width: 100%;
				text-align: center;
				height: 26px;
				line-height: 26px;
				margin-top: 22px;
			}
			#numberTitle{
				color: white;
				width: 100%;
				font-size: 28px;
				height: 28px;
				line-height: 28px;
				margin-top: 60px;
				text-align: center;
			}
			#stepNumber{
				color: white;
				width: 100%;
				font-size: 94px;
				height: 94px;
				line-height: 94px;
				margin-top: 30px;
				text-align: center;
			}
			#dateTime{
				color: white;
				width: 100%;
				font-size: 26px;
				height: 26px;
				line-height: 26px;
				margin-top: 30px;
				text-align: center;
			}
			#sportInfo{
				margin-top: 65px;
				width: 100%;
			}
			.InfoState{
				width: 50%;
				float: left;
			}
			.InfonNumber{
				color: white;
				font-size: 56px;
				height: 56px;
				line-height: 56px;
				width: 100%;
				text-align: center;
			}
			.InforTitle{
				color: white;
				font-size: 24px;
				height: 24px;
				line-height: 24px;
				width: 100%;
				text-align: center;
				margin-top: 22px;
			}
			#twoDimenCode{
				margin-top: 50px;
				width: 100%;
			}
			#twoDimenCode div{
				width: 194px;
				height: 194px;
				margin: 0 auto;
			}
			#sportPoster{
				width: 100%;
				height: 150px;
				padding-top: 50px;
			}
			#sportPosterTxt{
				height: 90px;
				width: 500px;
				border:2px solid #00B588;
				color: #00B588;
				border-radius: 10px;
				line-height: 90px;
				text-align: center;
				font-size: 32px;
				margin: 0 auto;
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
		<div id="mainContent">
			<div id="headPortrait"><img src="images/02.jpg"/></div>
			<div id="nickName">星空520</div>
			<div id="numberTitle">运动步数</div>
			<div id="stepNumber">6951</div>
			<div id="dateTime">2017.6.21</div>
			<div id="sportInfo">
				<div class="InfoState">
					<div class="InfonNumber">3.09</div>
					<div class="InforTitle">今日消耗&nbsp;&nbsp;(卡)</div>
				</div>
				<div class="sportInfo">
					<div class="InfonNumber">1129</div>
					<div class="InforTitle">全国排名</div>
				</div>
			</div>
		</div>
		<div id="twoDimenCode">
			<div><img src="images/twodimen.png"/></div>
		</div>
		<div id="sportPoster">
			<div id="sportPosterTxt">生成今日运动海报</div>
		</div>
	</body>

	
