//按钮事件
$(function($) {
	// 页面修改按钮
	$("#modiForm").click(function() {
		if ($("#disdiv").css("display") == "none") {
			document.getElementById("userInfoForm").reset();
			$("#modiForm").html("修改信息");
			$("#disdiv").css("display", "block");
			$("#edtdiv").css("display", "none");
		} else {
			$("#modiForm").html("取消修改");
			$("#disdiv").css("display", "none");
			$("#edtdiv").css("display", "block");
		}
	});
	
	// 页面保存按钮
	$("#savebtn").click(function() {
		var mobile = $("#mobile").val();
		if(mobile!="" && checkPhoneRule(mobile)){
			var mail = $("#mail").val();
			if(mail==""){
				$("#userInfoForm").submit();
			}else{
				if(isEmail(mail)){
					$("#userInfoForm").submit();
				}else{					
					$("#mailDiv").removeClass("has-success").addClass("has-error");
					$("#mailSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");
					$("#mail").focus();
					return false;
				}
			}	
		}else{
			$("#mobileDiv").removeClass("has-success").addClass("has-error");
			$("#mobileSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");
			$("#mobile").focus();
			return false;
		}

	});
	
	$("#CptBtn").click(function(){
		var mobile = $("#mobile").val();		
		$.ajax({
			type : "post",
			url : "/p2pstock/regist/checkPhone_registAction.action?mobile="+ mobile,// 要访问的后台地址
	        dataType:"json",
			success : function(data) {
				if(data.result=="0"){					
					sendCpt();
				}else{
					showMyModal("绑定失败","该手机号已绑定其他用户");
				}
			}
		});
	});
	
	$("#cptUser").blur(function(){
		if($("#cptTime").val()!=""){
			var now = getCurrentDate1();
			var activetime = getFormatDate(new Date($("#cptTime").val()), 'yyyy/MM/dd hh:mm:ss');		
			if(now < activetime){
				var cptBase = $("#cptBase").val();
				var cptUser = $("#cptUser").val();
				if(cptBase==cptUser){
					$("#savebtn").removeAttr("disabled");
					$("#cptDiv").removeClass("has-error").addClass("has-success");
					$("#cptSpan").removeClass("glyphicon-remove").addClass("glyphicon-ok");					
				}else{
					$("#savebtn").attr("disabled","true");
					$("#cptDiv").removeClass("has-success").addClass("has-error");
					$("#cptSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");		
				}
			}else{
				$("#savebtn").attr("disabled","true");
				$("#cptDiv").removeClass("has-success").addClass("has-error");
				$("#cptSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");
				showMyModal("验证失败","验证码超时");
			}
		}
	});
	
	$("#mail").blur(function(){
		var mail = $("#mail").val();
		if(mail !=""){
			if(isEmail(mail)){
				$("#mailDiv").removeClass("has-error").addClass("has-success");
				$("#mailSpan").removeClass("glyphicon-remove").addClass("glyphicon-ok");
			}else{
				$("#mailDiv").removeClass("has-success").addClass("has-error");
				$("#mailSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");
			}
		}else{
			$("#mailDiv").removeClass("has-error").removeClass("has-success");
			$("#mailSpan").removeClass("glyphicon-remove").removeClass("glyphicon-ok");	
		}
		
	});
	
	$("#mobile").blur(function(){
		var mobile = $("#mobile").val();		
		if(checkPhoneRule(mobile)){
			$("#mobileDiv").removeClass("has-error").addClass("has-success");
			$("#mobileSpan").removeClass("glyphicon-remove").addClass("glyphicon-ok");	
		}else{
			$("#mobileDiv").removeClass("has-success").addClass("has-error");
			$("#mobileSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");
		}
	});
	
	
});

