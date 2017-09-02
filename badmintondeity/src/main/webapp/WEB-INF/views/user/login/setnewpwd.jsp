<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<script type="text/javascript"
			src="/p2pstock/static/js/user/login/setpwd.js"></script>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="/p2pstock/bootstrap-3.3.5-dist/css/bootstrap.min.css"
			rel="stylesheet">
		<link href="/p2pstock/font-awesome-4.3.0/css/font-awesome.min.css"
			rel="stylesheet">
		<link href="/p2pstock/font-awesome-4.3.0/css/font-awesome.min.css"
			rel="stylesheet">
		<link href="/p2pstock/pub_footer/css/pub_footer_2014.css"
			rel="stylesheet">
		<link href="/p2pstock/static/css/login.css" rel="stylesheet">
		<title>设置新密码</title>
	</head>
	<body>
		<div style="height: 50px;"></div>
		<div class="container container-custom loginbackgroundcolor">
			<div class="row wrap-login">
				<div class="login-user col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="login-part">
						<h3>
							找回密码>设置登录密码
						</h3>
						<div class="line"></div>
						<div class="user-info">
							<div class="user-pass">
								<table width="100%">
									<tr>
										<td width="30%" class="hidden-xs">
										</td>
										<td width="40%">
											<div class="input-group margin-bottom-sm">
												<label id="lerror" class="errmsg ">
													<tag:msg msgcode="${msgCode}"></tag:msg>
												</label>
											</div>
											<s:form action="/login/setnewpwd_loginAction.action"
												method="post" theme="simple" id="loginform">
												<input type="hidden" id="userno" name="loginvo.userno"
													value="${loginvo.userno}" />
												<fieldset style="border: 0px">
													<div class="input-group margin-bottom-sm">
														<table width=100%>
															<tr>
																<td>
																	<div class="input-group margin-bottom-sm">
																		<span class="icon1"><span class="hidden-xs">*登录密码</span><i
																			class="fa fa-key fa-fw"></i> </span>
																		<input id="passwd" name="loginvo.passwd"
																			onblur="checkpass1();" maxlength="12"
																			placeholder="密码"
																			onKeyUp="CheckIntensity(this.value,'pwd_Weak','pwd_Medium','pwd_Strong')"
																			class="pass-word" type="password" name="password"
																			data-is="isPassWord isPassNotAllNum isPassNotRepeat"
																			autocomplete="off" onpaste="return false" />
																	</div>
																</td>
																<td class="hidden-xs">
																	<div
																		class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12"
																		style="margin-left: 2px">
																		<table border="1" cellpadding="0" cellspacing="0">
																			<tr align="center">
																				<td id="pwd_Weak" class="pwd pwd_c">
																					&nbsp;
																				</td>
																				<td id="pwd_Medium" class="pwd pwd_c pwd_f">
																					无
																				</td>
																				<td id="pwd_Strong" class="pwd pwd_c pwd_c_r">
																					&nbsp;
																				</td>
																			</tr>
																		</table>
																	</div>
																</td>
															</tr>
														</table>
													</div>
													<div class="input-group margin-bottom-sm">
														<table width=100%>
															<tr>
																<td width=80%>
																	<div
																		class="input-group margin-bottom-sm col-xs-12 col-sm-12 col-md-12 col-lg-12">
																		<span class="icon1"><span class="hidden-xs">*确认密码</span><i
																			class="fa fa-key fa-fw"></i> </span>
																		<input id="cusPass2" name="password"
																			onblur="checkpass2();" maxlength="12"
																			placeholder="确认密码"
																			onKeyUp="CheckIntensity(this.value,'pwd_Weak2','pwd_Medium2','pwd_Strong2')"
																			class="pass-word" type="password" name="password"
																			data-is="isPassWord isPassNotAllNum isPassNotRepeat"
																			autocomplete="off" onpaste="return false" />
																	</div>
																</td>
																<td class="hidden-xs">
																	<div
																		class="input-group col-xs-12 col-sm-12 col-md-12 col-lg-12"
																		style="margin-left: 2px">
																		<table border="1" cellpadding="0" cellspacing="0">
																			<tr align="center">
																				<td id="pwd_Weak" class="pwd pwd_c">
																					&nbsp;
																				</td>
																				<td id="pwd_Medium" class="pwd pwd_c pwd_f">
																					无
																				</td>
																				<td id="pwd_Strong" class="pwd pwd_c pwd_c_r">
																					&nbsp;
																				</td>
																			</tr>
																		</table>
																	</div>
																</td>
															</tr>
														</table>
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