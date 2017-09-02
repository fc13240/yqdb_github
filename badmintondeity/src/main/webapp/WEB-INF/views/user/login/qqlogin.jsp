<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<script type="text/javascript"
			src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"
			data-appid="101255166" data-callback="true" charset="utf-8">	
		</script>
		<script type="text/javascript">  
		   //调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中  
		   QC.Login({  
		       //btnId：插入按钮的节点id，必选  
		       btnId:"qqLoginBtn",      
		       //用户需要确认的scope授权项，可选，默认all  
		       scope:"all",  
		       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S  
		       size: "A_M"  
		   }, function(reqData, opts){//登录成功  
		        //根据返回数据，更换按钮显示状态方法  
		        var dom = document.getElementById(opts['btnId']),  
		        _logoutTemplate=[  
		            //头像  
		            '<span><img src="{figureurl}" class="{size_key}"/></span>',  
		            //昵称  
		            '<span>{nickname}</span>',  
		            //退出  
		            '<span><a href="javascript:QC.Login.signOut();">退出</a></span>'      
		        ].join("");  
		        dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {  
		           nickname : QC.String.escHTML(reqData.nickname), //做xss过滤  
		           figureurl : reqData.figureurl  
		        }));  
		          
		          QC.Login.getMe(function(openId, accessToken){    
		         alert(["当前登录用户的", "openId为："+openId, "accessToken为："+accessToken].join("\n"));    
		        });   
		  
		        //这里可以调用自己的保存接口  
		        //...  
		  
		   }, function(opts){//注销成功  
		         alert('QQ登录 注销成功');  
		   }
		);
		</script>
	</head>

	<body>
	<div class="main">
		<div class="container">
			<br/>
			<span id="qqLoginBtn"></span>		
		</div>
		<div class="container">
			未完善的页面，使用QQ登录后续验证及绑定功能未开发	
		</div>
	</div>
	</body>
</html>