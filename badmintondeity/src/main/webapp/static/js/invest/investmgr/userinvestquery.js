function secPrtStatus(){
	$("#invest_statusname").val($("#invest_status").find("option:selected").text());
}

//按钮事件
$(function($){
	//点击查询
	$('#secBtn').click(function(){
		$('#queryForm').submit();
	});
	
});
function moreinfo(user_invest_id){
	$('#user_invest_id').val(user_invest_id);
	$('#moreForm').submit();
}
function sec01(){
	$("#invest_statusname").val($("#invest_status").find("option:selected").text());
}
function sec02(paramvalue,paramname){
	$('#rule_counttyp').val(paramvalue);
	$('#secdis02').html(paramname);
	$('#listdiv02').css('display','none');
}
function sec03(paramvalue,paramname){
	$('#rule_lvl').val(paramvalue);
	$('#secdis03').html(paramname);
	$('#listdiv03').css('display','none');
}

function audit(user_invest_id,auditflag){
	$('#audit_user_invest_id').val(user_invest_id);
	$('#audit_auditflag').val(auditflag);
	$('#auditForm').submit();
}