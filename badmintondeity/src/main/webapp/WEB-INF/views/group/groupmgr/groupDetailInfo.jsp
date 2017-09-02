<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<head>
<title>战队名片</title>
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

	function updateGroup(){
		$("#upload").show();
		$("#remark").show();
		$("#groupName").show();
		$("#previewImage").show();
		$("#saveDiv").show();
		$("#nameDiv").hide();
		$("#logoDiv").hide();
		$("#remarkDiv").hide();
		$("#updateDiv").hide();						
	}
	
	function saveUpdate(){
		if ($("#groupName").val() == "") {
		$("#editNameDiv").addClass("has-error");
		return false;
		}
		if($("#logoDiv").attr("src")==$("#previewImage").attr("src")){
			$("#updateGroupform").submit();
		}else{	
			$.ajaxFileUpload({
				url : "/p2pstock/fileupload",// 要访问的后台地址
				secureuri : false,//一般设置为false
				fileElementId : "logoPic",
				dataType : 'json',//返回值类型 一般设置为json					
				success : function(msg) {
					var result = msg.result;
					if(result=="0"){
						$("#logo").val(msg.picUrl);
						$("#updateGroupform").submit();
					}else{
						showMyModal("创建失败","Logo上传失败");
					}
				}
			});
		}			
	}
	
	function cancelUpdate(){
		$("#upload").hide();
		$("#remark").hide();
		$("#groupName").hide();
		$("#previewImage").hide();
		$("#saveDiv").hide();
		$("#nameDiv").show();
		$("#logoDiv").show();
		$("#remarkDiv").show();
		$("#updateDiv").show();
	}
	
	function joinInGroup(){
		var group_id = $("#group_id").val();
		$.ajax({
			type : "post",
			url : "/p2pstock/group/joinGroup_groupMgrAction.action?group_id="+ group_id,// 要访问的后台地址
	        dataType:"json",
			success : function(data) {
				if(data.result=="0"){
					showMyModal("操作成功","您已加入战队："+$("#groupName").val());
					setTimeout(function(){
						window.location.reload();
					},2000);
				}
			}
		});
	}
	
	function quitFromGroup(){
		var group_id = $("#group_id").val();
		$.ajax({
			type : "post",
			url : "/p2pstock/group/quitGroup_groupMgrAction.action?group_id="+ group_id,// 要访问的后台地址
	        dataType:"json",
			success : function(data) {
				if(data.result=="0"){
					showMyModal("操作成功","您已退出战队："+$("#groupName").val());
					setTimeout(function(){
						window.location.reload();
					},2000);
				}
			}
		});
	}
	
	function concernGroup(){
		var group_id = $("#group_id").val();
		$.ajax({
			type : "post",
			url : "/p2pstock/group/concernGroup_groupMgrAction.action?group_id="+ group_id,// 要访问的后台地址
	        dataType:"json",
			success : function(data) {
				if(data.result=="0"){
					$("#cancelConcern").show();
					$("#concern").hide();
					showMyModal("操作成功","您已将战队《"+$("#groupName").val()+"》添加关注");
				}
			}
		});
	}
	
	function cancelConcern(){
		var group_id = $("#group_id").val();
		$.ajax({
			type : "post",
			url : "/p2pstock/group/cancelConcern_groupMgrAction.action?group_id="+ group_id,// 要访问的后台地址
	        dataType:"json",
			success : function(data) {
				if(data.result=="0"){
					$("#cancelConcern").hide();
					$("#concern").show();
					showMyModal("操作成功","您已取消对战队《"+$("#groupName").val()+"》的关注");
				}
			}
		});
	}
	
</script>
</head>
	<body>
		<s:form action="/group/updateGroup_groupMgrAction.action" method="post" theme="simple" id="updateGroupform">
	
		<div class="content">
			<div class="container" style="text-align: center">
				<input id="group_id" type="hidden" name="groupVo.group_id" value="${groupVo.group_id}"/>
				<img id="logoDiv" type="text" src="${groupVo.group_logo}" style="width: 80px; height: 80px; border: 1px solid #FFE4E1"/>
				<img type="text" id="previewImage" name="groupVo.group_logo" src="${groupVo.group_logo}" style="display:none;width: 80px; height: 80px; border: 1px solid #FFE4E1"/>
			</div>
			<div class="container" id="upload" style="padding:5px;display:none">
				<input type="hidden" id="logo" name="groupVo.group_logo" value="/p2pstock/static/images/mainpageIcon/logo.png"> 
				<input accept="image/*" type="file" id="logoPic" name="logoPic" value="" style="font-size: 12px; width: 100%">
			</div>
			<div class="container" style="margin-top:10px;text-align: center">
				<div id="nameDiv"><s:property value="groupVo.group_name" /></div>
				<div id="editNameDiv">
				<input type="text" class="form-control" id="groupName" name="groupVo.group_name" value="${groupVo.group_name}" style="display:none;width:100%;text-align:center;">	 	
				</div>
			</div>
		</div>
		
		<div class="topic">成立时间</div>
		<div class="content">
			<div class="container">
			<span><i class="fa fa-fw fa-calendar" style="width:15px;color:#1E90FF"></i></span>
			<span><s:date  name="groupVo.create_date" format="yyyy-MM-dd"/></span>
			<s:if test="memberVo.bamembers_identity==\"I\"">
				<span style="float:right">
					<i id="cancelConcern" class="fa fa-heart" style="color:red;padding-top:3px;" onclick="cancelConcern();"></i>
					<i id="concern" class="fa fa-heart-o" style="color:red;padding-top:3px;display:none" onclick="concernGroup();"></i>		
				</span>
			</s:if>
			<s:elseif test="memberVo.bamembers_identity==null">
				<span style="float:right">
					<i id="cancelConcern" class="fa fa-heart" style="color:red;padding-top:3px;display:none" onclick="cancelConcern();"></i>
					<i id="concern" class="fa fa-heart-o" style="color:red;padding-top:3px;" onclick="concernGroup();"></i>				
				</span>
			</s:elseif>
			</div>
		</div>
		
		<div class="topic">成员数</div>
		<div class="content" onclick="location='/p2pstock/member/memberMgrInit_memberMgrAction.action?group_id=${groupVo.group_id}'">
			<div class="container">
				<span>${memberCount}</span>				
			</div>
		</div>
		
		<div class="topic">比赛数</div>
		<div class="content">
			<div class="container">
				<span>${activityCount}</span>				
			</div>
		</div>
		
		<div class="topic">战队说明</div>
		<div class="content">
			<div class="container">
				<div id="remarkDiv"><s:property value="groupVo.remark" /></div>
				<textarea id="remark" class="form-control" name="groupVo.remark" style="display:none;resize:none;width:100%;text-align:center;"
					placeholder="战队简介"><s:property value="groupVo.remark" /></textarea>
			</div>
		</div>
		<div class="grayline"></div>
		<div class="content">
			<div class="container">
			<s:if test="memberVo.bamembers_identity==\"I\" || memberVo.bamembers_identity==null">
				<input type="button" class="btn btn-large btn-block btn-info" id="joinGroup" onclick="joinInGroup();" value="加战队">
				<input type="button" class="btn btn-large btn-block btn-info" id="quitGroup" onclick="quitFromGroup();" value="退出战队" style="display:none">
			</s:if>
			<s:elseif test="memberVo.bamembers_identity==\"O\" || memberVo.bamembers_identity==\"M\"">				
				<input type="button" class="btn btn-large btn-block btn-info" id="quitGroup" onclick="quitFromGroup();" value="退出战队" >
				<input type="button" class="btn btn-large btn-block btn-info" id="joinGroup" onclick="joinInGroup();" value="加战队" style="display:none">
			</s:elseif>
			</div>
		</div>
		
		</s:form>
		<div class="content">
			<div class="container">
				<s:if test='%{#session.userid ==groupVo.group_manage_user_id}'>
				<div id="updateDiv" style="margin-top:5px;padding:10px 0px">
					<span><input type="button" class="btn btn-large btn-block" id="update" onclick="updateGroup();" value="修改"></span>
				</div>
				<div id="saveDiv" style="display:none;margin-top:5px;padding:10px 0px">
					<input class="btn btn-success" id="update" onclick="saveUpdate();"value="保存" style="width:50%;float:left">
					<input class="btn btn-warning" id="cancel" onclick="cancelUpdate();"value="取消" style="width:50%;float:right">
				</div>
				</s:if>
			</div>
		</div>
	</body>

	
