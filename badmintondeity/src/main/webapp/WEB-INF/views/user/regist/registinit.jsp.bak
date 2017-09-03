<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>注册页面</title>
<script type="text/javascript" src="/p2pstock/static/js/user/regist/regist.js"></script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" action="p2pstock/regist/regist_registAction.action" method="post"
			theme="simple" id="registform" >
			<h3><b>用户登录</b></h3>
			<div class="form-group has-feedback">
				<label class="control-label col-sm-3" for="mobile">手机号码</label>
				<div class="col-sm-9" id="mobileDiv">
					<input id="mobile" class="form-control" type="text" placeholder="手机号码" 
						name="registvo.cusMobile" maxlength="11" 
						onkeyup="this.value=this.value.replace(/[^\d]/g,'') "/>
					<span id="mobileSpan" class="glyphicon form-control-feedback"
					aria-hidden="true">
				</span> 
				</div>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label col-sm-3" for="cptUser">验证码</label> 
				<div class="col-sm-9" id="cptDiv">
				<input id="cptUser" type="text" class="form-control" placeholder="短信验证码" > 					
				<span id="cptSpan" class="glyphicon form-control-feedback"
					aria-hidden="true">
				</span>
				<input type="hidden" id="cptBase" /><input type="hidden" id="cptTime" />
				</div>
			</div>
			
			<div class="form-group has-feedback">
				<div class="col-sm-12">
					<input type="button" id="CptBtn" onclick="sendCpt();"
						class="btn btn-large btn-block btn-default" value="获取验证码">
				</div>
			</div>

			<div class="form-group has-feedback">
				<div class="col-sm-12">
					<input type="submit" id="regist" disabled="disabled"
						class="btn btn-large btn-block btn-success" value="登录">
				</div>
			</div>
			
		</form>
	</div>

</body>
</html>