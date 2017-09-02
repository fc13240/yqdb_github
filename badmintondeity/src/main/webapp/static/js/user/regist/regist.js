//按钮事件
$(function($) {
	$('#mobile').blur(function(){
		var mobile = $("#mobile").val();
		if(checkPhoneRule(mobile)){
			$("#mobileDiv").removeClass("has-error").addClass("has-success");
			$("#mobileSpan").removeClass("glyphicon-remove").addClass("glyphicon-ok");	
		}else{
			$("#mobileDiv").removeClass("has-success").addClass("has-error");
			$("#mobileSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");
		}
	});
	
	$('#cptUser').blur(function(){
		if($("#cptTime").val()!=""){
			var now = new Date();		
			var activetime = new Date($("#cptTime").val());		
			if(now < activetime){
				var cptBase = $("#cptBase").val();
				var cptUser = $("#cptUser").val();
				if(cptBase==cptUser){
					$("#regist").removeAttr("disabled");
					$("#cptDiv").removeClass("has-error").addClass("has-success");
					$("#cptSpan").removeClass("glyphicon-remove").addClass("glyphicon-ok");		
				}else{
					$("#regist").attr("disabled","true");
					$("#cptDiv").removeClass("has-success").addClass("has-error");
					$("#cptSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");		
				}			
			}else{				
				$("#regist").attr("disabled","true");
				$("#cptDiv").removeClass("has-success").addClass("has-error");
				$("#cptSpan").removeClass("glyphicon-ok").addClass("glyphicon-remove");
				showMyModal("验证失败","验证码超时");
			}
		}		
	});
});