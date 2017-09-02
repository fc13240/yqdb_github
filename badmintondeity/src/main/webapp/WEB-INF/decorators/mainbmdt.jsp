<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="UTF-8">
<head>
	<title><decorator:title default="一起动吧"/></title>
	<meta charset="utf-8">
	<meta http-equiv="Page-Enter" content="revealTrans(duration=5, transition=7)">
	<meta http-equiv="Page-Exit" content="revealTrans(duration=1, transition=8)">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta name="renderer" content="webkit" />
	<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="/p2pstock/static/js/common/common.js"></script>

	<!-- Bootstrap -->
	<link href="/p2pstock/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="/p2pstock/font-awesome-4.4.0/css/font-awesome.css" rel="stylesheet">
	<script src="/p2pstock/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript"></script>
	
	<link href="/p2pstock/static/css/animations/animations.css"	rel="stylesheet" type="text/css" />
	<link href="/p2pstock/static/css/bmdt.css" rel="stylesheet">	
	
	<decorator:head />
	<style type="text/css">
		.box1 {width:100%;position:absolute;top:0px;bottom:58px;overflow-y: auto;}
		.box2 {border-top:1px #cccccc solid; background:#f2f6fb; width:100%; height:22px; position:absolute; bottom:0;}
		.box3 {border:1px red solid;width:100%;height:60px; position:fixed;bottom:0px}
	</style>
	<script type="text/javascript">
	    function showMyModal(tittle,context){
	    	$("#myModalLabel").html(tittle);
			$("#myModalBody").html(context);
			$('#myModal').modal('show');
			setTimeout(function(){
				$('#myModal').modal('hide');
			},2000);
	    }
	</script>
</head>
<body>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id="myModalLabelDiv">
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body" style="text-align:center" id="myModalBody"></div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->	

<div class="box1">
    <decorator:body />
</div>
<page:applyDecorator name="main_footerbmdt" />
	<%-- <!--main begin-->
	<div id="mainPage"style="margin-bottom:60px">	
	<decorator:body />
	</div>
	<!--main end-->
	
	<!--footer begin-->
	<page:applyDecorator name="main_footerbmdt" />
	<!--footer end--> --%>
</body>
</html>