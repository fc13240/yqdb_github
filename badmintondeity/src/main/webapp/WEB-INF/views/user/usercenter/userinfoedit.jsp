<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercenter.js"></script>
<title>用户中心</title>
</head>
<body>

	<form data-name="userbasic" enctype="multipart/form-data" method="post" id="userInfoForm" class="form-horizontal" 
		action="/p2pstock/usercenter/userSave_userCenterAction.action">
		<div id="disdiv" style="display: block">
			<div class="topic">
				<div style="text-align:center;padding:10px">
					<s:if test="userInfoVo.user_headimgurl !=''">
						<img src="${userInfoVo.user_headimgurl}" class="img-circle">
					</s:if>
					<s:else>
						<img src="/p2pstock/static/images/rrd/ucenter-man.png" class="img-circle">
					</s:else>
				</div>
			</div>
			<div class="container">
				<div style="padding:10px">
					<span><i class="fa fa-fw fa-user" style="width:20px;color:#F00"></i></span>
					<span>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>	
					<span style="float:right"><s:property value="userInfoVo.user_nickname" /></span>
				</div>
				<div class="pinkline"></div>
				
				<div style="padding:10px">
					<span><i class="fa fa-fw fa-mobile-phone" style="width:20px;color:#1E90FF"></i></span>
					<span>手机号码</span>
					<span style="float:right"><s:property value="userInfoVo.phone" /></span>
				</div>
				<div class="pinkline"></div>
				
				<div style="padding:10px">
					<span><i class="fa fa-fw fa-envelope" style="width:20px;color:#32CD32"></i></span>
					<span>邮箱地址 </span>					
					<span style="float:right"><s:property value="userInfoVo.mail" /></span>
				</div>
			</div>
			<div style="width:100%;padding:0 5%;text-align:center;position:relative;margin-top:100px"> 
				<button id="modiForm" class="btn btn-large btn-block btn-info" type="button">修改信息</button>
			</div>
		</div>
		<div id="edtdiv" style="display: none">
			<div class="userphoto" id="userphoto">
				<div style="text-align:center;padding:10px">
					<s:if test="userInfoVo.user_headimgurl !=''">
						<img src="${userInfoVo.user_headimgurl}" class="img-circle">
					</s:if>
					<s:else>
						<img src="/p2pstock/static/images/rrd/ucenter-man.png" class="img-circle">
					</s:else>
				</div>
			</div>
			<div class="container"> 
				<div class="form-group has-feedback">
					<span class="control-label col-sm-3" for="cptUser">
						<i class="fa fa-fw fa-user" style="width:20px;color:#F00"></i>昵称
					</span> 
					<div class="col-sm-9">
						<input id="userno" name="userInfoVo.user_nickname" class="form-control" value="${userInfoVo.user_nickname}" maxlength="10"> 					
					</div>
				</div>
				
				<div class="form-group has-feedback">
					<span class="control-label col-sm-3" for="cptUser">
						<i class="fa fa-fw fa-user" style="width:20px;color:#F00"></i>真实姓名
					</span> 
					<div class="col-sm-9">
						<input id="userno" name="userInfoVo.user_name" class="form-control" value="${userInfoVo.user_name}" maxlength="10"> 					
					</div>
				</div>
				
				<div class="form-group has-feedback">
					<span class="control-label col-sm-3" for="mobile">
						<i class="fa fa-fw fa-mobile-phone" style="width:20px;color:#1E90FF"></i>手机号码
					</span>
					<div class="col-sm-9" id="mobileDiv">
						<input id="mobile" class="form-control" type="text" placeholder="手机号码" 
							name="userInfoVo.phone" value="${userInfoVo.phone}" maxlength="11" 
							onkeyup="this.value=this.value.replace(/[^\d]/g,'') "/>
						<span id="mobileSpan" class="glyphicon form-control-feedback" aria-hidden="true"></span>
					</div>
				</div>
				
				<div class="form-group has-feedback">
					<span class="control-label col-sm-3" for="cptUser">
						<i class="fa fa-fw fa-key" style="width:20px;color:#1E90FF"></i>验证码
					</span> 
					<div class="col-sm-9" id="cptDiv">
						<input id="cptUser" type="text" class="form-control" placeholder="短信验证码" > 					
						<span id="cptSpan" class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<input type="hidden" id="cptBase" /><input type="hidden" id="cptTime" />
					</div>
				</div>			
				
				<div class="form-group has-feedback">
					<span class="control-label col-sm-3" for="mail">
						<i class="fa fa-fw fa-envelope" style="width:20px;color:#32CD32"></i>邮箱
					</span> 
					<div class="col-sm-9" id="mailDiv">
						<input id="mail" name="userInfoVo.mail" value="${userInfoVo.mail}" type="text" class="form-control" placeholder="邮箱" maxlength="30"> 					
						<span id="mailSpan" class="glyphicon form-control-feedback" aria-hidden="true"></span>
					</div>
				</div>				
				
				<div class="form-group has-feedback">
					<div class="col-sm-12">
						<input type="button" class="btn btn-large btn-block btn-default" value="更换绑定手机">
						<input type="button" id="CptBtn" class="btn btn-large btn-block btn-default" value="获取验证码">
					</div>
				</div>
				
				<div class="form-group has-feedback">
					<div class="col-sm-12">
						<button id="savebtn" disabled="disabled" class="btn btn-large btn-block btn-success" type="button">保 存</button>
					</div>
				</div>				
			</div>			
		</div>
	</form>

</body>
</html>