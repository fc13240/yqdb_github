<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tag" uri="/tags/mytags" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html;charset=utf-8" http-equiv="Content-Type" />
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta name="renderer" content="webkit" />
		<meta name="keywords" content=" " />
		<meta name="description" content=" " />
		<link href="/p2pstock/public/css/style.css?v=20141212" rel="stylesheet" type="text/css" />
		<link href="/p2pstock/public/css/wegene/wegene.css" rel="stylesheet" type="text/css" />
	</head>

<body>
	<!--top menu -->
	<div class="top-menu">
		<div class="container container-custom">
			<div class="row">
				<div>
					<!-- 导航菜单 -->
					<div class="aw-top-nav navbar">
						<div class="navbar-header">
							<button class="navbar-toggle pull-left">
								<span class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>
					</div>
					<s:if test='%{#session.username != null && #session.username!=""}'>
						您好，<s:property value="#session.username"/>
					    <a href="/p2pstock/login/logout_loginAction.action" style="padding:0px 10px 0px 0px;" >[退出]</a>
				    </s:if>
				    <s:else>
				        <!-- 导航菜单 -->
						<a class="btn btn-gray btn-login pull-right visible-xs"
							href="/p2pstock/login/loginInit_loginAction.action">登录</a>
				    </s:else>
				</div>
			</div>
		</div>
	</div>
	<!-- end top menu -->
</body>
</html>
