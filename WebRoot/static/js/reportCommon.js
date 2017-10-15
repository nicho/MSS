function getDateDiff() {
	var date1 = $('#submitDate').val() + "-01";
	var date2 = $('#endDate').val() + "-31";
	var day = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '')
		day = dateDiff(date1, date2);
	if (day <= 0 || day > 366) {
		$('#w4').window('open');
	} else {
		$("#inputForm").submit();
	}
}

function getDateDiffThreeMonth() {
	var date1 = $('#submitDate').val() + "-01";
	var date2 = $('#endDate').val() + "-31";
	var day = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '')
		day = dateDiff(date1, date2);
	if (day <= 0 || day >= 93) {
		$('#w4').window('open');
	} else {
		$("#inputForm").submit();
	}
}

function getDateDiff2() {
	var date1 = $('#submitDate').val();
	var date2 = $('#endDate').val();
	var day = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '')
		day = dateDiff(date1, date2);
	if (day < 0 || day >= 31) {
		$('#w4').window('open');
	} else {
		$("#inputForm").submit();
	}
}
function getDateDiff3() {
	var date1 = $('#ordered_date_s').val();
	var date2 = $('#ordered_date_e').val();


	var date3 = $('#booked_date_s').val();
	var date4 = $('#booked_date_e').val();

	var date5 = $('#confirm_date_s').val();
	var date6 = $('#confirm_date_e').val();

	var date7 = $('#receive_date_s').val();
	var date8 = $('#receive_date_e').val();
	if((date1!='' && date2=='') ||(date2!='' && date1=='') ||(date3!='' && date4=='')||(date4!='' && date3=='') ||(date5!='' && date6=='')||(date6!='' && date5=='') ||(date7!='' && date8=='')||(date8!='' && date7==''))
		{
			$('#w8').window('open');
			return;
		}
	var day = 0;
	var fal = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '') {
		day = dateDiff(date1, date2);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (date3 != null && date3 != '' && date4 != null && date4 != '') {
		day = dateDiff(date3, date4);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (date5 != null && date5 != '' && date6 != null && date6 != '') {
		day = dateDiff(date5, date6);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (date7 != null && date7 != '' && date8 != null && date8 != '') {
		day = dateDiff(date7, date8);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (fal == 1) {
		$("#inputForm").submit();
	} else { 
		$('#w5').window('open');
	}

}
function dateDiff(date1, date2) {
	var strSeparator = "-"; // 日期分隔符
	var oDate1;
	var oDate2;
	var iDays;
	oDate1 = date1.split(" ")[0].split(strSeparator);
	oDate2 = date2.split(" ")[0].split(strSeparator);

	var dt1 = new Date(oDate1[0], oDate1[1] - 1, oDate1[2]);
	var dt2 = new Date(oDate2[0], oDate2[1] - 1, oDate2[2]);

	try {
		return Math.round((dt2.getTime() - dt1.getTime()) / (1000 * 60 * 60 * 24));
	} catch (e) {
		return e.message;
	}
}


function getDateDiffS() {
	var date1 = $('#submitDate').val() + "-01";
	var date2 = $('#endDate').val() + "-31";
	var day = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '')
		day = dateDiff(date1, date2);
	if (day < 0 || day >= 365) {
		$('#w4').window('open');
	} else {
		return true;
	}
}
function getDateDiffSThreeMonth() {
	var date1 = $('#submitDate').val() + "-01";
	var date2 = $('#endDate').val() + "-31";
	var day = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '')
		day = dateDiff(date1, date2);
	if (day < 0 || day >= 93) {
		$('#w4').window('open');
	} else {
		return true;
	}
}


function getDateDiffS2() { 
	var date1 = $('#submitDate').val();
	var date2 = $('#endDate').val();
	var day = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '')
		day = dateDiff(date1, date2);
	if (day < 0 || day >= 31) { 
		$('#w4').window('open');
	} else {
		return true;
	}
}
function getDateDiffS3() {
	var date1 = $('#ordered_date_s').val();
	var date2 = $('#ordered_date_e').val();

	var date3 = $('#booked_date_s').val();
	var date4 = $('#booked_date_e').val();

	var date5 = $('#confirm_date_s').val();
	var date6 = $('#confirm_date_e').val();

	var date7 = $('#receive_date_s').val();
	var date8 = $('#receive_date_e').val();
	if((date1!='' && date2=='') ||(date2!='' && date1=='') ||(date3!='' && date4=='')||(date4!='' && date3=='') ||(date5!='' && date6=='')||(date6!='' && date5=='') ||(date7!='' && date8=='')||(date8!='' && date7==''))
	{
		$('#w8').window('open');
		return;
	}
	var day = 0;
	var fal = 0;
	if (date1 != null && date1 != '' && date2 != null && date2 != '') {
		day = dateDiff(date1, date2);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (date3 != null && date3 != '' && date4 != null && date4 != '') {
		day = dateDiff(date3, date4);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (date5 != null && date5 != '' && date6 != null && date6 != '') {
		day = dateDiff(date5, date6);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (date7 != null && date7 != '' && date8 != null && date8 != '') {
		day = dateDiff(date7, date8);
		if (day <= 0 || day >= 31) {
			$('#w4').window('open');
			return;
		} else {
			fal = 1;
		}
	}
	if (fal == 1) {
		return true;
	} else { 
		$('#w5').window('open');
	}

}