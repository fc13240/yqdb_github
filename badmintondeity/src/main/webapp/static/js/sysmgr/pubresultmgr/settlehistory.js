//按钮事件
$(function($){
	//点击查询
	$('#secBtn').click(function(){
		$('#productQueryForm').submit();
	});
	
});
function moreinfo(invt_product_id){
	$('#invt_product_id').val(invt_product_id);
	$('#moreinfoForm').submit();
}