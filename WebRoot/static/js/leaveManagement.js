$(function(){
	
	// 给请假类型注册时间，当为年假的时候调用年假的时间验证
	$("#timeOffType").change(function(){
		var typeVal = $(this).val();
		if( "ANNUAL_LEAVE" == typeVal ){
			var date1=$('#timeOffBeginDate').val();
			var date2=$('#timeOffEndDate').val();
			if(date1!=''&&date2!='') {
				var timeOffDayAnnual = calculateDays("","");
				askForLeaveAnnualRules(timeOffDayAnnual);
			}
		}else if( "DAYS_OFF" == typeVal ){
			var date1=$('#timeOffBeginDate').val();
			var date2=$('#timeOffEndDate').val();
			if(date1!=''&&date2!='') {
				var timeOffDayAnnual = calculateDays_DAYS_OFF("","");
				askForLeaveAnnualRules_DAYS_OFF(timeOffDayAnnual);
			}
		} else {
			var date1=$('#timeOffBeginDate').val();
			var date2=$('#timeOffEndDate').val();
			if(date1!=''&&date2!='') {
				var timeOffDaysf = dateDiff(date1, date2)+1;
				askForLeaveNoAnnualRules(timeOffDaysf);
			}
		}
	});
	
});

// 重新绑定请假时长验证规则，有年假
function askForLeaveAnnualRules(timeOffDayAnnual){
	 $("#timeOffDays").rules("remove");
	 $("#timeOffDays").rules("add", {
		      required:true,
		 	  timeOffDaysVal:true,
		 	  timeOffDaysPas:[timeOffDaysf],
			  timeOffDayAnnual:[timeOffDayAnnual]
	});
}

//重新绑请假时长验证规则，无年假
function askForLeaveNoAnnualRules(timeOffDaysf){
	 $("#timeOffDays").rules("remove");
	 $("#timeOffDays").rules("add", {
		      required:true,
		 	  timeOffDaysVal:true,
		 	  timeOffDaysPas:[timeOffDaysf]
	});
}

//重新绑定请假时长验证规则，有调休
function askForLeaveAnnualRules_DAYS_OFF(timeOffDayAnnualDAYS_OFF){
	 $("#timeOffDays").rules("remove");
	 $("#timeOffDays").rules("add", {
		      required:true,
		 	  timeOffDaysVal:true,
		 	  timeOffDaysPas:[timeOffDaysf],
		 	  timeOffDayAnnual_DAYS_OFF:[timeOffDayAnnualDAYS_OFF]
	});
}

// 人员选择框
function selectPerson() {
	var orgId = $('#ouOrganizationId').val();
	var channelType = '';
	var departmentCode = '';
	$('#selectiframe').attr('src', 'getPersonOrganizationView.do?url=askForLeaveList.do&ignorePositionRelation=Y&levelTypeUp=N&levelTypeDown=N&departmentCode='
					+ departmentCode + '&orgId=' + orgId + '&channelType='
					+ channelType + '&d=' + new Date().getTime());
	$('#wDialog').window('open');
}

// 赋值函数
function personSetOrganization(personId,userName,departmentCode,departmentName,positionId,positionName){
	var typeVal = $("#timeOffType").val();
	$('#timeOffPersonName').val(userName);
	$('#timeOffPersonId').val(personId); 
	$('#hrOrganizationId').val(departmentCode);
	$('#hrOrganizationName').text(departmentName);
	$('#positionId').val(positionId);
	$('#positionName').text(positionName);
	if ( "ANNUAL_LEAVE" == typeVal ) {
	   	calculateDays("","");
	}
}

function closeDialog(){
	$('#wDialog').window('close'); 
}

// 计算两个日期之间的间隔
function dateDiff(date1, date2) {
	var dt1 = new Date(Date.parse(date1.replace(/-/g, '/')));
	var dt2 = new Date(Date.parse(date2.replace(/-/g, '/')));

	try {
		return Math.round((dt2.getTime() - dt1.getTime())
				/ (1000 * 60 * 60 * 24));
	} catch (e) {
		return e.message;
	}
}

// 点击开始日期之后调用方法
function onpickedBeginDate() {
	$dp.$("timeOffEndDate").click();
	getDateDiff();
}

/**
 * 提交表单
 * @param status
 */
function attendTimeOffsubmit(status) {
	// 提交之前把文件的ID关联到对应的字段
	var fileIds = $("#fileIds").val();
	$("#attendTimeOfffileIds").val(fileIds);
	$("#status").val(status);
	$("#askForLeaveForm").submit();
}

/**
 * ajax请求计算年假天数
 * @returns {Number}
 */
function calculateDays(pId,pName) {
	if(pId == "" && pName == ""){
		var pName = $("#timeOffPersonName").val();
		var pId = $("#timeOffPersonId").val();
	}
	var day = 0;
	if (pName != "" && pId != "") {
		var url = "calculateAnnualleave.do";
		var params = {
			pId : pId
		};
		jQuery.ajax({
			type : "POST",
			url : url,
			dataType : "json",
			cache : false,
			async : false,
			data : params,
			success : function(result) {
				if (result.timeOffDays != null) {
					day = result.timeOffDays;
				} else {
					$("#myModel").window("open");
				}
			}
		});
	} else {
		$("#askForLeaveForm").submit();
		$("#timeOffType").val("AFFAIR_LEAVE");
	}
	return day;
}


/**
 * ajax请求计算调休天数
 * @returns {Number}
 */
function calculateDays_DAYS_OFF(pId,pName) { 
	var dayNumber = $("#dayNumber").val();
	var dayAm = $("#dayAm").val();
	if(pId == "" && pName == ""){
		var pName = $("#timeOffPersonName").val();
		var pId = $("#timeOffPersonId").val();

	}
	
	var day = 0;
	if (pName != "" && pId != "") {
		var url = "calculateAnnualleaveDaysOff.do";
		var params = {
			pId : pId,
			dayNumber:dayNumber,
			dayAm:dayAm
		};
		jQuery.ajax({
			type : "POST",
			url : url,
			dataType : "json",
			cache : false,
			async : false,
			data : params,
			success : function(result) {
				if (result.timeOffDays != null) {
					day = result.timeOffDays;
				} else {
					$("#myModel").window("open");
				}
			}
		});
	} else {
		$("#askForLeaveForm").submit();
		$("#timeOffType").val("DAYS_OFF");
	}
	return day;
}