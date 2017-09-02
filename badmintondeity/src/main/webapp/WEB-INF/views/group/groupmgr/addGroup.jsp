<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>建立战队</title>
<script src="/p2pstock/static/js/uploadify/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function() {
		$('#logoPic').change(function(event) {
			// 根据这个 <input> 获取文件的 HTML5 js 对象
			var files = event.target.files, file;
			if (files && files.length > 0) {
				// 获取目前上传的文件
				file = files[0];
				var type= file.type.substr(0,file.type.indexOf("/"));
				if(type!="image"){
					showMyModal("创建失败","请选择图片类型文件");
					return false;
				}
				// 那么我们可以做一下诸如文件大小校验的动作
				if (file.size > 1024 * 1024 * 2) {
					showMyModal("创建失败","图片大小不能超过 2MB!");
					return false;
				}
				// !!!!!!
				// 下面是关键的关键，通过这个 file 对象生成一个可用的图像 URL
				// 获取 window 的 URL 工具
				var URL = window.URL || window.webkitURL;
				// 通过 file 生成目标 url
				var imgURL = URL.createObjectURL(file);
				// 用这个 URL 产生一个 <img> 将其显示出来
				$("#previewImage").attr('src', imgURL);
				// 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
				// URL.revokeObjectURL(imgURL);
			}
		});
	});

	function addGroup() {
		if ($("#groupname").val() == "") {
			$("#nameDiv").addClass("has-error");
			showMyModal("创建失败","请输入战队名称");
		} else {
			if($("#logoPic").val()!=""){
				$.ajaxFileUpload({
					url : "/p2pstock/fileupload",// 要访问的后台地址
					secureuri : false,//一般设置为false
					fileElementId : "logoPic",
					dataType : 'json',//返回值类型 一般设置为json					
					success : function(msg) {
						var result=msg.result;
						if(result=="0"){
							$("#logo").val(msg.picUrl);
							$("#addGroupform").submit();
						}else{
							showMyModal("创建失败","Logo上传失败");
						}
					}
				});
			}else{
				$("#addGroupform").submit();
			}
		}
	}
</script>
</head>

<body>
	<s:form action="/group/addGroup_groupMgrAction.action" method="post" theme="simple" id="addGroupform">
		<div class="topic">战队Logo</div>
		<div class="content">
			<div class="container" style="text-align: center">
				<img id="previewImage"
					style="width: 80px; height: 80px; border: 1px solid #FFE4E1"
					src="/p2pstock/static/images/mainpageIcon/logo.png" />
			</div>
			<div class="container" style="padding:10px">
				<input type="hidden" id="logo" name="groupVo.group_logo" value="/p2pstock/static/images/mainpageIcon/logo.png"> 
				<input accept="image/*" type="file" id="logoPic" name="logoPic" value="" style="font-size: 12px; width: 100%">
			</div>
		</div>
		<div class="topic">战队名称</div>
		<div class="content">
			<div class="container" id="nameDiv">
				<input type="text" id="groupname" name="groupVo.group_name" style="width: 100%" class="form-control" />
			</div>
		</div>
		<div class="topic">战队简介</div>
		<div class="content">
			<div class="container">
				<textarea id="remark" name="groupVo.remark" placeholder="战队简介"
					style="resize: none; width: 100%; text-align: center;" class="form-control" ></textarea>
			</div>
		</div>

		<input type="hidden" id="group_type" name="groupVo.group_type" value="0" />
		<input type="hidden" id="bind_no" name="groupVo.bind_no" value="0" />
		<input type="hidden" id="group_manage_user_id" name="groupVo.group_manage_user_id" value="${session.userid}" />

	</s:form>
	<div class="container" style="margin-top:50px">
		<button class="btn btn-large btn-block btn-info" onclick="addGroup();">确定</button>
	</div>
</body>
</html>

