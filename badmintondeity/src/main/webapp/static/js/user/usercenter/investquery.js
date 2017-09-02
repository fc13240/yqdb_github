
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
function editinfo(user_invest_id){
	$('#euser_invest_id').val(user_invest_id);
	$('#editForm').submit();
}

function secInvestMgrStatus(){
	$("#invest_statusname").val($("#invest_status").find("option:selected").text());
}