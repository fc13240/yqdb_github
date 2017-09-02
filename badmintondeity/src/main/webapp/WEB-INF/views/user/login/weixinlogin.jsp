<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tag" uri="/tags/mytags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

</head>



<body>

<script type="text/javascript">
(function(){
var p = {
url:'http://www.bigpot.cn/', /*获取URL，可加上来自分享到QQ标识，方便统计*/
desc:'', /*分享理由(风格应模拟用户对话),支持多分享语随机展现（使用|分隔）*/
title:'分享标题', /*分享标题(可选)*/
summary:'分享摘要', /*分享摘要(可选)*/
pics:'http://www.bigpot.cn/p2pstock/static/images/rrd/mylogo.bmp', /*分享图片(可选)*/
flash: '', /*视频地址(可选)*/
site:'P2P', /*分享来源(可选) 如：QQ分享*/
style:'101',
width:96,
height:24
};
var s = [];
for(var i in p){
s.push(i + '=' + encodeURIComponent(p[i]||''));
}
document.write(['<a class="qcShareQQDiv" href="http://connect.qq.com/widget/shareqq/index.html?',s.join('&'),'" target="_blank">分享到QQ</a>'].join(''));
})();
</script>
<script src="http://connect.qq.com/widget/loader/loader.js" widget="shareqq" charset="utf-8"></script>




</body>
</html>