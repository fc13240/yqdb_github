//按钮事件
$(function($){
	//点击查询
	$('#secBtn').click(function(){
		$('#queryform').submit();
	});
});
function secTranInfoPrtStatus(){
	$("#invt_product_statusname").val($("#invt_product_status").find("option:selected").text());
}
