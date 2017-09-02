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
	opt.default = {
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

  	var optTime = $.extend(opt['time'], opt['default']);
    $("#beginTime_input").mobiscroll(optTime).time(optTime);
    $("#endTime_input").mobiscroll(optTime).time(optTime);

	var areascroll;
	//初始化区域滚动条
	areascroll = new iScroll("areaList",{
		snap: true,
		momentum: false,
		vScroll:false,
		hScroll:true,
		hScrollbar:false,
	});	
	
	//区域滚动条样式
	$("div[name='areadiv']").on("click",function(){
		 $(this).addClass("areadivActive").siblings().removeClass("areadivActive");
	});
	
	function arearesize(){
		var w = $(".plist").width();
		$(".dateli").width(w);
		areascroll.refresh();
	}	
	
});

var	pullType = 0;
function addNewActivity() {
	var check = true;
	if($("#actName").val()==""){
		check = false;
		$("#errormsg").text("请填写比赛名称");
	}else if($("#activityTime").text()==""){
		check = false;
		$("#errormsg").text("请选择比赛时间");
	}else if($("#rollId").val()==""){
		check = false;
		$("#errormsg").text("请制定比赛规则");
	}else if($("#activity_addr").val()==""){
		check = false;
		$("#errormsg").text("请选择比赛地点");			
	}
	
	if(check){
		$("#addNewActivityform").submit();
	}else{
		$("#errorDiv").show();
		setTimeout(function(){
			$("#errorDiv").hide();
		},2000);
	};		
}	

function showHiddenDiv(id) {
	$("#activityInfo").removeClass('pt-page-moveFromLeft').addClass('pt-page-moveToLeft').hide();		
	$("#hiddenDiv" + id).removeClass('pt-page-moveToRight').addClass('pt-page-moveFromRight').show();
}

function hideDiv(id) {		
	if (id == "0") {
		var chkObjs = document.getElementsByName("activityVo.activity_type");
		/*if (chkObjs[0].checked) {
			$("#activityType").text("娱乐");
		} else {
			$("#activityType").text("比赛");
		}*/
		if (chkObjs[0].checked) {
			$("#activityType").text("男生5分钟");
		} 
		else if (chkObjs[1].checked){
			$("#activityType").text("男生10分钟");
		}
		else if (chkObjs[2].checked){
			$("#activityType").text("男生15分钟");
		}
		else if (chkObjs[3].checked) {
			$("#activityType").text("女生5分钟");
		} 
		else if (chkObjs[4].checked){
			$("#activityType").text("女生10分钟");
		}
		else if (chkObjs[5].checked){
			$("#activityType").text("女生15分钟");
		}
	}else if (id == "1") {
		$("#beginTime").val($("#start_date").val()+" "+ $("#beginTime_input").val());
		$("#endTime").val($("#end_date").val()+" "+ $("#endTime_input").val());
		$("#activityTime").text($("#beginTime").val()+"至"+$("#endTime").val());
	}else if (id == "2") {
		var addr = $('input[name="addr"]:checked').next("span").text();
		$("#activity_addr").val($('input[name="addr"]:checked').val());
		$("#activityAddr").text(addr);
	}else if (id == "3") {
		$("#activityAddrField").text($("#addrField").val());
	}else if (id == "4") {
		$("#activityContact").text($("#contact").val());
	}else if (id == "5") {
		$("#activityRoll").text("人均"+$("#rollId").val()+"￥");
	}else if (id == "6") {
		$("#activityName").text($("#actName").val());
	}
	$("#activityInfo").removeClass('pt-page-moveToLeft').addClass('pt-page-moveFromLeft').show();
	$("#hiddenDiv" + id).removeClass('pt-page-moveFromRight').addClass('pt-page-moveToRight').hide();
}

function querySiteByArea(areacode){
	var city=$("#citycode").val();
	ajaxCommonSubmit('areaForm', 'querySiteok',"/p2pstock/site/querySite_siteMgrAction.action?city="+city+"&area="+areacode);
}

function querySiteok(msg){
	$('#siteDiv').html(msg);
} 
	
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

document.onreadystatechange = completeLoading;//加载状态为complete时移除loading效果
function completeLoading() {
    if (document.readyState == "complete") {
    	for(var id=0;id<=6;id++){
    		$("#hiddenDiv" + id).hide();
    	};
    }
}
	