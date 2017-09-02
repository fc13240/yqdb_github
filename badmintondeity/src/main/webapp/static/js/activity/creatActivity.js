$(function($){
	//初始化日历控件
	var today = new Date();
	var start_date;
	var end_date;
    function assignCalendar(id){
        $('<div class="calendar" />')
                .insertAfter( $(id) )
                .multiDatesPicker({
                    dateFormat: 'yy-mm-dd',
                    minDate: new Date(),
                    maxDate: '+1y',
                    altField: id,
                    maxPicks: 2,
                    firstDay: 1,
                    showOtherMonths: true
                }).prev().hide();
    }
    assignCalendar('#date_departure');
    
    //初始化时间滚动条
    
    var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime',stepMinute: '5'};
	opt.time = {preset : 'time'};
	opt.defaultTime = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
        startYear: currYear - 5, //开始年份
        endYear: currYear + 5 //结束年份
	};

  	var optTime = $.extend(opt['time'], opt['defaultTime']);
    $("#beginTime_input").mobiscroll(optTime).time(optTime);
    $("#endTime_input").mobiscroll(optTime).time(optTime);

	var areascroll;
	//初始化区域滚动条
	areascroll = new IScroll("#areaList",{
		preventDefault:false,
		scrollX: true,
		scrollY: false,
		momentum: false,
		snap: true
	});	
	
	//区域滚动条样式
	$("div[name='areadiv']").on("click",function(){
		 $(this).addClass("areadivActive").siblings().removeClass("areadivActive");
	});	
	
	
});

var	pullType = 0;
function addNewActivity() {
	var check = true;
	if($("#actName").val()==null || $("#actName").val()==""){
		check = false;
		showMyModal("创建失败","请输入比赛名称");
		return;
	}else if($("#group_id").val()==-1){
		check = false;
		showMyModal("创建失败","请选择比赛战队");
		return;
	}else if($("#beginTime").val()==null || $("#beginTime").val()==""){
		check = false;
		showMyModal("创建失败","请选择比赛时间");
		return;
	}else if($("#endTime").val()==null || $("#endTime").val()==""){
		check = false;
		showMyModal("创建失败","请选择比赛时间");
		return;
	}else if($("#rollId").val()==null || $("#rollId").val()==""){
		check = false;
		showMyModal("创建失败","请制定比赛规则");
		return;
	}else if($("#activity_addr").val()==null || $("#activity_addr").val()==""){
		check = false;
		showMyModal("创建失败","请选择比赛地点");
		return;
	}
	$("#addNewActivityform").submit();	
}	

function showHiddenDiv(id) {
	$("#activityInfo").removeClass('pt-page-moveFromLeft').addClass('pt-page-moveToLeft').hide();		
	$("#hiddenDiv" + id).removeClass('pt-page-moveToRight').addClass('pt-page-moveFromRight').show();
}

function hideDiv(id) {
	if (id == "0") {
		$("#activityName").text($("#actName").val());
	}else if (id == "1") {
		$("#beginTime").val($("#start_date").val()+" "+ $("#beginTime_input").text());
		$("#endTime").val($("#end_date").val()+" "+ $("#endTime_input").text());
		if($("#start_date").val()!=""){
			var start = $("#beginTime").val().substr(5,$("#beginTime").val().length);
			var end = $("#endTime").val().substr(5,$("#endTime").val().length);
			$("#activityTime").text(start+"至"+end);		}		
	}else if (id == "2") {
		var addr = $('input[name="addr"]:checked').next("span").text();
		$("#activity_addr").val($('input[name="addr"]:checked').val());
		$("#activityAddr").text(addr);
	}else if (id == "3") {
		$("#activityRoll").text("人均"+$("#rollId").val()+"￥");
	}
	$("#activityInfo").removeClass('pt-page-moveToLeft').addClass('pt-page-moveFromLeft').show();
	$("#hiddenDiv" + id).removeClass('pt-page-moveFromRight').addClass('pt-page-moveToRight').hide();
}

function queryAdmin(){
	var group_id = $("#group_id").val();
	if(group_id=="-1"){
		$("#contact").empty();
	}else{
		$.ajax({
			type : "post",
			url : "/p2pstock/member/queryGroupAdmin_memberMgrAction.action?group_id="+ group_id,// 要访问的后台地址
	        dataType:"json",
			success : function(data) {
				$("#contact").empty();
				for(var i =0;i<data.memberVoList.length;i++){
					var selectedText = data.memberVoList[i].user_name;
					var selectedValue = data.memberVoList[i].user_id;
					$("#contact").append("<option value="+selectedValue+">"+selectedText+"</option>"); 
				};
			}
		});
	};
}

function querySiteByArea(areaName){
	$("#siteDiv").empty();
	var city=$("#cityName").val();
	ajaxCommonSubmit('querySiteform', 'querySiteok',"/p2pstock/site/querySite_siteMgrAction.action?city="+city+"&area="+areaName);
}

function querySiteok(msg){
	$('#siteDiv').html(msg);
	loaded();
} 
	
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

document.onreadystatechange = completeLoading;//加载状态为complete时移除loading效果
function completeLoading() {
    if (document.readyState == "complete") {
    	for(var id = 0;id <= 3; id++){
    		$("#hiddenDiv" + id).hide();
    		$("#loadingDiv").fadeOut();
    		querySiteByArea("");
    	};
    };
}
	