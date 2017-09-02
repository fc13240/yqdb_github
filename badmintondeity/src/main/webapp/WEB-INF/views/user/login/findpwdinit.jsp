<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<script type="text/javascript" src="/p2pstock/static/js/user/login/findpwd.js"></script>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="/p2pstock/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="/p2pstock/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
		<link href="/p2pstock/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet" >
		<link href="/p2pstock/pub_footer/css/pub_footer_2014.css" rel="stylesheet">
		<link href="/p2pstock/static/css/login.css" rel="stylesheet">
		<title>找回密码</title>
	</head>
	<body>
		<div class="main ">
			<div style="height: 50px;"></div>
			<div class="container container-custom loginbackgroundcolor">
				<div class="row wrap-login">
					<div class="login-user col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="login-part">
							<h3>找回密码</h3>
							<div class="line"></div>
							<div class="user-info">
								<div class="user-pass">
								<table width="100%">
									<tr>
										<td width="30%" class="hidden-xs">
										</td>
										<td width="40%" >
											<div class="input-group margin-bottom-sm">
												<label id="lerror" class="errmsg ">
													<tag:msg msgcode="${msgCode}"></tag:msg>
												</label>
											</div>
											<s:form action="/login/findpwd_loginAction.action"
												method="post" theme="simple" id="findpwdform">
												<s:hidden id="cptcusMobile" name="cptcusMobile"></s:hidden>
												<fieldset style="border: 0px">
													<div class="input-group margin-bottom-sm">
														<span class="icon1">*手机号</span>
														<input id="userno" name="loginvo.userno" tabindex="1"
															maxlength="11" placeholder="请输入手机号" class="user-name"
															type="text">
														</input>
													</div>
													<div class="input-group margin-bottom-sm">
														<span class="icon1">*验证码</span>
														<input id="cusCpt" name="loginvo.cusCpt"
															onblur="checkcpt();" tabindex="2" placeholder="请输入验证码"
															class="extends-input" type="text">
													</div>
													<div class="input-group margin-bottom-sm">
														<input type="button" id="regist_CptBtn" value="免费获取验证码"></input>
													</div>
													<div class="input-group margin-bottom-sm">
														<input type="button" id="findBtn" value='确定'></input>
													</div>
												</fieldset>
											</s:form>
										</td>
										<td width="30%" class="hidden-xs">
										</td>
									</tr>
								</table>									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>