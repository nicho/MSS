
	function cleanPersonMulitple()
	{
		$('#userGroupStr').val('');
		$('#userGroup').val('');
	}
	 function trim(str){ //删除左右两端的空格
	     return str.replace(/(^\s*)|(\s*$)/g, "");
	 }
	function personSetMulitple(personId,userName){ 
	 
		if(personId!=null && personId!='')
		{
			var old_Add_otherPersonId="";
			var old_otherPersonId=$('#userGroupStr').val();
			var old_otherPersonId_arr = new Array();
			if(old_otherPersonId!='')
			{
				old_otherPersonId_arr=old_otherPersonId.split(",");
			
				for(var i=0;i<old_otherPersonId_arr.length;i++)
				{
					if(old_otherPersonId_arr[i]!=null && old_otherPersonId_arr[i]!='')
						{
							if(personId.indexOf(trim(old_otherPersonId_arr[i])+",")==-1)
							{
								old_Add_otherPersonId+=old_otherPersonId_arr[i]+",";
							}
						}
					
				}
			} 
			var old_Add_otherPersonName="";
			var old_otherPersonName=$('#userGroup').val();
			var old_otherPersonName_arr = new Array();
			if(old_otherPersonName!='')
			{
				old_otherPersonName_arr=old_otherPersonName.split(",");
			
				for(var i=0;i<old_otherPersonName_arr.length;i++)
				{
					if(old_otherPersonName_arr[i]!=null && old_otherPersonName_arr[i]!='')
						{ 
							if(userName.indexOf(trim(old_otherPersonName_arr[i])+",")==-1)
							{
								old_Add_otherPersonName+=old_otherPersonName_arr[i]+",";
							}
						}
					
				} 
			} 
			$('#userGroupStr').val(old_Add_otherPersonId+personId);
			$('#userGroup').val(old_Add_otherPersonName+userName);
		}
		
	}
	 function parseISO8601(dateStringInRange) {  
		   var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/,  
		       date = new Date(NaN), month,  
		       parts = isoExp.exec(dateStringInRange);  
		  
		   if(parts) {  
		     month = +parts[2];  
		     date.setFullYear(parts[1], month - 1, parts[3]);  
		     if(month != date.getMonth() + 1) {  
		       date.setTime(NaN);  
		     }  
		   }  
		   return date;  
		 }  
	
	function personSet(personId,userName,departmentCode,departmentName,positionId,positionName,jobId,orgId,orgName){
		if(personId ==''){
			personId ="0";	
		}
		if(positionId ==''){
			positionId ="0";
		}
		if(jobId ==''){
			jobId ="0";
		}
		if(orgId ==''){
			orgId ="0";
		}
		$("#personId").val(personId);
		$("#userName").val(userName);
		$("#ouOrganizationId").val(orgId);
		$("#hrOrganizationId").val(departmentCode);
		$("#deparment").val(departmentName+"/"+positionName);
		$("#positionId").val(positionId);
		$("#positionName").val(positionName);
		$("#jobId").val(jobId);
		setDefaultTitle();	
	}
	function closeDialog(){
		$('#wDialog').window('close'); 
	}
	function getDiaryWeek(){
		$.ajax({
			type : "post", // 使用post方法访问后台
			async: false,
			dataType : "json", // 返回JSON格式的数据
			url : 'getDiaryWeek.do?diaryTimeStr='+$("#diaryTime").val(),
		    success:function(result) {
		    	$("#weekLabel").html(result.diaryWeekStr);
		    	$("#diaryWeek").val(result.diaryWeek);
		    	$("#diaryYear").val(result.diaryYear);
		    	setDefaultTitle();
		    }
		    });
	};
	 function setDefaultTitle(){
			var userName = $("#userName").val();
	    	var positionName = $("#positionName").val();
	    	var diaryTime =  $("#diaryTime").val();
	    	var diaryType = $('#dtId option:selected').text();
	    	var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");  
	    	var stamp = null;
	    	if(typeof(diaryTime) != "undefined"){
	    		stamp = parseISO8601(diaryTime);
	    	}else{
	    		stamp = new Date();
	    	}
	    	var templeTypeName = $("#templeTypeName").val();
	    	var diaryTypeCode = $("#diaryTypeCode").val();
	    	var weekStr = $("#weekLabel").text();
	    	if(diaryTypeCode=='DIARY_WEEK'){
	         $("#diaryTitleStr").val(userName+"("+positionName+")"+" "+weekStr+" " + templeTypeName);	
	    	}else if(diaryTypeCode=='DIARY_DAY'){
	    	$("#diaryTitleStr").val(userName+"("+positionName+")"+" "+diaryTime+" "+dayNames[stamp.getDay()]+" " + templeTypeName);	
	    	}else if(diaryTypeCode=='DIARY_MONTH'){
	    	var str = diaryTime.split('-');	
	    	var  diaryStr = str[0]+'年'+str[1]+'月';
	        $("#diaryTitleStr").val(userName+"("+positionName+")"+" "+diaryStr+" " + templeTypeName);		
	    	}
	 }
		function linkSendMessage(){
			
			$.ajax({
				type : "post", // 使用post方法访问后台
				async: false,
				dataType : "json", // 返回JSON格式的数据
				url : 'getDairyTime.do',
			    success:function(result) {
			    	
			    	}
			    });	
		}
 		
	
