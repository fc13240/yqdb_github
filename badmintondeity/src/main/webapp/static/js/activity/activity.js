
$(function($){
		var myDate = new Date();
		var y,m,d,t,week="";
		var beginDate = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
		$("#beginDate").val(beginDate);
		var areacode =$("#area").val();
		for(var i = 0; i < 19;i++){
			y = myDate.getFullYear();
			m = myDate.getMonth()+1;//获取当前月份的日期
			d = myDate.getDate();
			t = myDate.getDay();
			if(t==0){
				week="周日";
			}else if(t==1){
				week="周一";
			}else if(t==2){
				week="周二";
			}else if(t==3){
				week="周三";
			}else if(t==4){
				week="周四";
			}else if(t==5){
				week="周五";
			}else if(t==6){
				week="周六";
			}			
			if(i==0){
				week="今天";
				$("#dateul").append("<div name='dateDiv' onclick='queryByDate("+y+","+m+","+d+")' class='datediv divActive'>"+m+"月"+d+"日"+week+"</div>");
			}else{
				if(i==1){
					week="明天";
				}
				$("#dateul").append("<div name='dateDiv' onclick='queryByDate("+y+","+m+","+d+")' class='datediv'>"+m+"月"+d+"日"+week+"</div>");
			}
			
			myDate.setDate(myDate.getDate()+1);//获取1天后的日期
		}
		
		$("div[name='dateDiv']").on("click",function(){
			 $(this).addClass("divActive").siblings().removeClass("divActive");
		});
		
		$("div[name='areadiv']").on("click",function(){
			 $(this).addClass("areadivActive").siblings().removeClass("areadivActive");
		});
		
		datescroll = new IScroll("#plist",{
			preventDefault:false,
			scrollX: true,
			scrollY: false,
			momentum: false,
			snap: true
			});
		areascroll = new IScroll("#areaList",{
			preventDefault:false,
			scrollX: true,
			scrollY: false,
			momentum: false,
			snap: true
		});
		$(window).load(function(e) {
			loaded();
	    });
		
		//ajax加载最新比赛信息 初始化加载默认查询最新比赛 10条记录
		ajaxCommonSubmit('queryActivityform', 'queryActivityok',"/p2pstock/ajax/queryActivityByTimeArea_activityMgrAction.action?beginDate="+beginDate+"&areacode="+areacode+"&count=5");
	});
	var baseCount = 2;
	var addCount = 2;
	var currentCount = 2;
	//点击时间查询
	function queryByDate(y,m,d){
		beginDate = y+"-"+m+"-"+d;
		$("#beginDate").val(beginDate);
		areacode = $("#area").val();
		ajaxCommonSubmit('queryActivityform', 'queryActivityok',"/p2pstock/ajax/queryActivityByTimeArea_activityMgrAction.action?beginDate="+beginDate+"&areacode="+areacode+"&count=2");
	}
	//点击区县查询
	function queryByArea(areacode){
		$("#area").val(areacode);
		beginDate = $("#beginDate").val();
		ajaxCommonSubmit('queryActivityform', 'queryActivityok',"/p2pstock/ajax/queryActivityByTimeArea_activityMgrAction.action?beginDate="+beginDate+"&areacode="+areacode+"&count=2");
	}
	
	function queryActivityok(msg){
		$('#activityDiv').html(msg);
		loaded();
	}
	
	function moreActivityinfo(t){
		var i="/p2pstock/activity/queryActivityDetailInfo_activityMgrAction.action?activityId="+t;
		$("#activity_id").val(t);
		$("#moreActivityInfoform").attr("action",i);
		$("#moreActivityInfoform").submit();
	}
