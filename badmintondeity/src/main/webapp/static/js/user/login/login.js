//按钮事件
$(function($){
	//限制只能输入数字
	function onlyNumber(){
		alert("1");
	    var keyCode = event.keyCode;
	    if(keyCode<48 || keyCode>57){
	        event.keyCode = 0;
	    }
	}
	
	//光标离开
	$("#userno").blur(function(){
		var phone = $("#userno").val();
		var len = phone.length;
		if(len == "11"){
			$("#lerror").text("");
		} else{
			$("#lerror").text("请输入合法手机号");
		} 

	});
	//光标进入
	$("#passwd").blur(function(){
		if($("#passwd").val() == ""){
			$("#lerror").text("密码不能为空");
		}else{
			$("#lerror").text("");
		}
	});
	
	//点击登录
	$('#loginBtn').click(function(){
		if(formchk()){
			$("#loginform").submit();
		}
	});
});
function formchk(){
	//表单验证
	if($("#userno").val() == "手机号" || $("#userno").val() == ""){
		$("#lerror").text("用户名不能为空");
		$("#lerror").css("display","block");
		return false;
	}
	if($("#passwd").val() == ""){
		$("#lerror").text("密码不能为空");
		$("#lerror").css("display","block");
		return false;
	}
	return true;
}

/**
 * 通过QQ登录
 */
function loginByQQ(){
	alert("通过QQ登录");
}

/**
 * 通过微信登录
 */
function loginByWeixin(){
	alert("通过微信登录");
}