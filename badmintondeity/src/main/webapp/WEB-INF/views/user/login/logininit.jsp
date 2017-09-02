<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>登录页面</title>
		<script type="text/javascript" src="/p2pstock/static/js/user/login/login.js"></script>
	</head>	
	<body>
		<div class="main ">
			<div style="height:20px;"></div>
			<div class="container container-custom">
				<div class="row wrap-login">
					<div class="login-banner col-sm-6 col-md-7 col-lg-7 hidden-xs "></div>
					<div class="login-user col-xs-12 col-sm-6 col-md-5 col-lg-5">
						<div class="login-part">
							<div class="error_div"><label id="lerror" class="errormsg"><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
							<h3>用户登录</h3>
							<div class="user-info">
								<div class="user-pass">
									<s:form action="/login/login_loginAction.action" method="post" theme="simple" id="loginform">
										<fieldset style="border: 0px">
											<div class="input-group margin-bottom-sm">
												<span class="icon1"> <i class="fa fa-user fa-fw "></i> </span>
												<input id="userno" name="loginvo.userno" tabindex="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " 
													placeholder="请输入手机号" class="user-name" type="text" maxlength="11">
												</input>
											</div>
											<div class="input-group margin-bottom-sm">
												<span class="icon1"><i class="fa fa-key fa-fw"></i> </span>
												<input id="passwd" name="loginvo.passwd" tabindex="2"
													placeholder="请输入密码" class="pass-word" type="password"/>
											</div>
											<div class="error-mess" style="display: none;">
												<span class="error-icon"></span><span id="error-message"></span>
											</div>
											<div class="forget-password">
												<span style="float:left"> <input
														type="checkbox" name="rememberMe" id="rememberMe"
														value="true" class="auto-login" tabindex="3" /> <label
														for="rememberMe">自动登录</label> </span>
												<span style="float:right" data-mod="popu_26"> 
													<a href="/p2pstock/login/findpwdInit_loginAction.action">忘记密码</a>
												</span>
											</div>
											<input id="loginBtn" class="logging" value="登 录" type="button" />
										</fieldset>
									</s:form>
								</div>
							</div>
							<div class="line"></div>
							<div class="third-part" data-mod="popu_27">
								<div>
									<span>第三方帐号登录</span>
								</div>
								<div style="margin-bottom:11px">									
									<div style="float:left;width:30px"><a href="/p2pstock/login/qqLogin_loginAction.action" ><img src="/p2pstock/static/images/rrd/qq_20.png"/></a>&nbsp;&nbsp;</div>							
									<div ><a href="/p2pstock/login/weixinLoginInit_loginAction.action" ><img src="/p2pstock/static/images/rrd/weixin_20.png" /></a></div>
								</div>
								<div>
									<span style="float:none">还没有帐号？<a href="/p2pstock/regist/registInit_registAction.action">立即注册</a></span>
								</div>
							</div>								
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>