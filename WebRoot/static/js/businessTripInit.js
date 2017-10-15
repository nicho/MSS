 function getDateDiff()
{
 
var date1=$('#travelBeginDateStr').val();
var date2=$('#travelEndDateStr').val();
if(date1!=null && date1!='' && date2!=null && date2!='' )
var day=dateDiff(date1, date2)+1;
if(day>=0)
	{
		$('#travelDays').val(day); 
		$("#travelDays").rules("add",{max:day});
	}

}
  
 function getDateDiffById(d1,d2,d3)
 {
 
 var date1=$('#'+d1).val();
 var date2=$('#'+d2).val();
 if(date1!=null && date1!='' && date2!=null && date2!='' )
 var day=dateDiff(date1, date2); 
 if(day>=0)
	 {
		 $('#'+d3).val(day); 
		 $('#'+d3).rules("add",{max:day});
	 }
  
 }
function dateDiff(date1, date2)
{ 
	   var strSeparator = "-"; //日期分隔符
	   var oDate1;
	   var oDate2;
	   var iDays;
	   oDate1= date1.split(" ")[0].split(strSeparator);
	   oDate2= date2.split(" ")[0].split(strSeparator);
	   
	   var dt1 = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
	   var dt2 = new Date(oDate2[0], oDate2[1]-1, oDate2[2]); 

   try
   { 
      return Math.round((dt2.getTime() - dt1.getTime()) / (1000 * 60 * 60 * 24));
    }
    catch (e)
    {
      return e.message;
    }
}
function selectPerson() {
	var orgId = '';
	var channelType = '';
	var departmentCode = ''; 
	
	$('#selectiframe').attr('src','gotoPersonList.do?url=businessTripListView.do&departmentCode='+departmentCode+'&orgId='+orgId+'&channelType='+channelType+'&d='+new Date().getTime());
	$('#wDialog').window('open');
}

var selectPersonOrg_userName='';
var selectPersonOrg_userId='';
function selectPersonOrganization(url,userName,userId) {
	var selectPersonOrg_userName='';
	var selectPersonOrg_userId='';
	var orgId = $('#ouOrganizationId').val();
	var channelType = '';
	var departmentCode = ''; 
	if(userName!=null && userName!='') 
		selectPersonOrg_userName=userName; 
	if(userId!=null && userId!='') 
		selectPersonOrg_userId=userId; 
	$('#selectiframe').attr('src','getPersonOrganizationView.do?url='+url+'&ignorePositionRelation=Y&levelTypeUp=N&levelTypeDown=N&departmentCode='+departmentCode+'&orgId='+orgId+'&channelType='+channelType+'&d='+new Date().getTime());
	$('#wDialog').window('open');
} 
function selectPersonMulitple(url) {
	var orgId = '';
	var channelType = '';
	var departmentCode = ''; 
	
	$('#selectiframe').attr('src','gotoPersonMultipleList.do?url='+url+'&ignorePositionRelation=Y&departmentCode='+departmentCode+'&orgId='+orgId+'&channelType='+channelType+'&d='+new Date().getTime());
	$('#wDialog').window('open');
}
function cleanPersonMulitple()
{
	$('#otherPersonId').val('');
	$('#otherPersonName').val('');
}
function personSetMulitple(personId,userName){ 
	if(personId!=null && personId!='')
	{
		var old_Add_otherPersonId="";
		var old_otherPersonId=$('#otherPersonId').val();
		var old_otherPersonId_arr = new Array();
		if(old_otherPersonId!='')
		{
			old_otherPersonId_arr=old_otherPersonId.split(",");
		
			for(var i=0;i<old_otherPersonId_arr.length;i++)
			{
				if(old_otherPersonId_arr[i]!=null && old_otherPersonId_arr[i]!='')
					{
						if(personId.indexOf(old_otherPersonId_arr[i]+",")==-1)
						{
							old_Add_otherPersonId+=old_otherPersonId_arr[i]+",";
						}
					}
				
			}
		} 
		var old_Add_otherPersonName="";
		var old_otherPersonName=$('#otherPersonName').val();
		var old_otherPersonName_arr = new Array();
		if(old_otherPersonName!='')
		{
			old_otherPersonName_arr=old_otherPersonName.split(",");
		 
			for(var i=0;i<old_otherPersonName_arr.length;i++)
			{
				if(old_otherPersonName_arr[i]!=null && old_otherPersonName_arr[i]!='')
					{ 
						if(userName.indexOf(old_otherPersonName_arr[i]+",")==-1)
						{
							old_Add_otherPersonName+=old_otherPersonName_arr[i]+",";
						}
					}
				
			}
		} 
		$('#otherPersonId').val(old_Add_otherPersonId+personId);
		$('#otherPersonName').val(old_Add_otherPersonName+userName);
	}
	
}
function closeDialog(){
	$('#wDialog').window('close'); 
}
function personSet(personId,userName,departmentCode,positionId,positionName){
	$('#userName').val(userName);
	$('#personId').val(personId); 
	$('#hrOrganizationId').val(departmentCode);
	$('#hrOrganizationName').text(departmentCode);
	$('#positionId').val(positionId);
	$('#positionName').text(positionName);
}
function personSetOrganization(personId,userName,departmentCode,departmentName,positionId,positionName){  

	$('#userName').val(userName);
	$('#personId').val(personId); 
	$('#hrOrganizationId').val(departmentCode);
	$('#hrOrganizationName').text(departmentName);
	$('#positionId').val(positionId);
	$('#positionName').text(positionName);
}
	
function checkLeaveCityS(obj)
{ 
 
}

var xuhao1=$("#contentTable_travel_plan lable").length;
var xuhao1_len=$("#contentTable_travel_plan lable").length+1;
var xuhao1_index=$("#contentTable_travel_plan lable").length+1;

var xuhao2=$("#contentTable_Ticket lable").length;
var xuhao2_len=$("#contentTable_Ticket lable").length+1;
var xuhao2_index=$("#contentTable_Ticket lable").length+1;

var xuhao3=$("#contentTable_hotel lable").length;
var xuhao3_len=$("#contentTable_hotel lable").length+1;
var xuhao3_index=$("#contentTable_hotel lable").length+1;

var xuhao4=$("#contentTable_expenses lable").length;
var xuhao4_len=$("#contentTable_expenses lable").length+1;
var xuhao4_index=$("#contentTable_expenses lable").length+1;

 
function businessTripInit(){ 
	
	
$("#add_trip_But").click(function(){
	
	    $("#contentTable_travel_plan").append("<tr id='trip_table_Tr_"+((2*xuhao1)+1)+"' align='left'>"
	    		            +"<td  rowspan='2'><lable>"+xuhao1_index+"</lable></td>"
	                        +"<td>"+
'<input type="text" id="leaveDate'+xuhao1_len+'" name="leaveDate" class="input4 required" onclick="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true,maxDate:\'#F{$dp.$D(\\\'arrivalDate'+xuhao1_len+'\\\',{d:0})}\'})"/> <span >*</span>'
							+"</td>"
							+"<td>"+
'<input type="text" autocomplete="off"  id="leaveCitySName_'+xuhao1_len+'" name="leaveCitySName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="leaveCityS_'+xuhao1_len+'" name="leaveCityS" class="input4 " /><input type="hidden" id="leaveProvinceS_'+xuhao1_len+'" name="leaveProvinceS" class="input4 " /> <span >*</span>'
		                    +"</td>"
	                        +"<td>"+
'<input type="text"  id="arrivalDate'+xuhao1_len+'" name="arrivalDate" class="input4 required" onclick="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true,minDate:\'#F{$dp.$D(\\\'leaveDate'+xuhao1_len+'\\\',{d:0})}\'})"/> <span >*</span>'
	                        +"</td>"
	                        +"<td>"+
'<input type="text" autocomplete="off"  id="arrivalCitySName_'+xuhao1_len+'" name="arrivalCitySName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="arrivalCityS_'+xuhao1_len+'" name="arrivalCityS" class="input4 " /><input type="hidden" id="arrivalProvinceS_'+xuhao1_len+'" name="arrivalProvinceS" class="input4 " /> <span >*</span>'
	                        +"</td>"
	                        +"<td rowspan='2'>"+
"<a onclick=\"deltr('contentTable_travel_plan','trip_table_Tr_"+((2*xuhao1)+1)+"','trip_table_Tr_"+((2*xuhao1)+2)+"')\" href=\"#\">"+deleteName+"</a></td>"
	                    +"</tr>");       
	    

	     
	    $("#contentTable_travel_plan").append("<tr id='trip_table_Tr_"+((2*xuhao1)+2)+"'><td colspan='4'><div class='title_font text_lf title_sd'>"+workingPlanName+"</div> <textarea class='textarea1 required' name='jobProject' cols='' rows='' onkeyup='limitLength(this,4000);stringReplaceAll(this,\",\",\"，\")'></textarea><span >*</span></td></tr>");     
	   
 
	    var xxx=xuhao1_len;
		$('#leaveCitySName_'+xxx).typeahead({
		    source:function (query, process) {
		        var parameter = {query: query};
		        $.post('findCity.do', parameter, function (data) {
			         var products= eval("(" + data + ")");
			         var results = _.map(products, function (product) {
	                        return product.flexValuesDesc;
	                    });
	                    process(results);
		        });
		    },
		      updater: function (item) { 
		    	  var parameter = {cityName: item};
                  $.post('findCityIdAndProvinceId.do', parameter, function (data) {
				         var products= eval("(" + data + ")");   
				        $('#leaveCitySName_'+xxx).val(item);
				        $('#leaveCityS_'+xxx).val(products.cityId);
				        $('#leaveProvinceS_'+xxx).val(products.provinceId);
			        }); 
                    return item;
                }

		    
	});
		
		$('#arrivalCitySName_'+xxx).typeahead({
		    source:function (query, process) {
		        var parameter = {query: query};
		        $.post('findCity.do', parameter, function (data) {
			         var products= eval("(" + data + ")");
			         var results = _.map(products, function (product) {
	                        return product.flexValuesDesc;
	                    });
	                    process(results);
		        });
		    },
		      updater: function (item) {
		    	  var parameter = {cityName: item};
                    $.post('findCityIdAndProvinceId.do', parameter, function (data) {
				         var products= eval("(" + data + ")");  
				        $('#arrivalCitySName_'+xxx).val(item);
				        $('#arrivalCityS_'+xxx).val(products.cityId);
				        $('#arrivalProvinceS_'+xxx).val(products.provinceId);
			        });
                    return item;
                }
	});

		
		
		xuhao1=xuhao1+1;
		xuhao1_len=xuhao1_len+1;
		xuhao1_index=xuhao1_index+1;
	    
	}); 
	


	$("#add_Ticket_But").click(function(){ 
		
		
	    $("#contentTable_Ticket").append("<tr id='ticket_table_Tr_"+((2*xuhao2)+1)+"' align='left'>"
	    		            +"<td rowspan='2'><lable>"+xuhao2_index+"</lable></td>"
	                        +"<td>"+
'<input type="text" id="leaveTime'+xuhao2_len+'" name="leaveTime" class="input4 required" onclick="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true})"/>   <span >*</span>'
							+"</td>"
							+"<td>"+
'<input type="text" autocomplete="off" id="leaveCityName_'+xuhao2_len+'" name="leaveCityName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="leaveCity_'+xuhao2_len+'" name="leaveCity" class="input4 " /><input type="hidden" id="leaveProvince_'+xuhao2_len+'" name="leaveProvince" class="input4 " /> <span >*</span>'
		                    +"</td>"
	                        +"<td>"+
'<input type="text" autocomplete="off" id="arrivalCityName_'+xuhao2_len+'" name="arrivalCityName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="arrivalCity_'+xuhao2_len+'" name="arrivalCity" class="input4 " /><input type="hidden" id="arrivalProvince_'+xuhao2_len+'" name="arrivalProvince" class="input4 " /> <span >*</span>'
                     +"</td>"
	                        +"<td>"+
'<select class="input4 required" name="transportationTypeId"><option value=""></option>'+$("#trafficListDiv").html()+'</select> 	<span >*</span> '
	                        +"</td>" 
	                        +"<td rowspan='2'>"+
"<a onclick=\"deltr('contentTable_Ticket','ticket_table_Tr_"+((2*xuhao2)+1)+"','ticket_table_Tr_"+((2*xuhao2)+2)+"')\" href=\"#\">"+deleteName+"</a></td>"
	                    +"</tr>");           
	    $("#contentTable_Ticket").append("<tr id='ticket_table_Tr_"+((2*xuhao2)+2)+"'><td colspan='4'><div class='title_font text_lf title_sd'>"+orderingRequestName+"</div> <textarea class='textarea1 required' name='bookingNoteS' cols='' rows='' onkeyup='limitLength(this,4000);stringReplaceAll(this,\",\",\"，\")'></textarea> <span >*</span></td></tr>");     
	    var xxx=xuhao2_len;
		$('#leaveCityName_'+xxx).typeahead({
		    source:function (query, process) {
		        var parameter = {query: query};
		        $.post('findCity.do', parameter, function (data) {
			         var products= eval("(" + data + ")");
			         var results = _.map(products, function (product) {
	                        return product.flexValuesDesc;
	                    });
	                    process(results);
		        });
		    },
		      updater: function (item) { 
		    	  var parameter = {cityName: item};
                  $.post('findCityIdAndProvinceId.do', parameter, function (data) {
				         var products= eval("(" + data + ")"); 
				        $('#leaveCityName_'+xxx).val(item);
				        $('#leaveCity_'+xxx).val(products.cityId);
				        $('#leaveProvince_'+xxx).val(products.provinceId);
			        }); 
                    return item;
                }
		
	});
		
		$('#arrivalCityName_'+xxx).typeahead({
		    source:function (query, process) {
		        var parameter = {query: query};
		        $.post('findCity.do', parameter, function (data) {
			         var products= eval("(" + data + ")");
			         var results = _.map(products, function (product) {
	                        return product.flexValuesDesc;
	                    });
	                    process(results);
		        });
		    },
		      updater: function (item) {
		    	  var parameter = {cityName: item};
                    $.post('findCityIdAndProvinceId.do', parameter, function (data) {
				         var products= eval("(" + data + ")"); 
				        $('#arrivalCityName_'+xxx).val(item);
				        $('#arrivalCity_'+xxx).val(products.cityId);
				        $('#arrivalProvince_'+xxx).val(products.provinceId);
			        });
                    return item;
                }
	});
		xuhao2=xuhao2+1;
		xuhao2_len=xuhao2_len+1;
		xuhao2_index=xuhao2_index+1;
	}); 
	
	
	$("#add_hotel_But").click(function(){ 
		
	    $("#contentTable_hotel").append("<tr id='expenses_table_Tr_"+((2*xuhao3)+1)+"' align='left'>"
	    		            +"<td rowspan='2' ><lable>"+xuhao3_index+"</lable></td>"
	                        +"<td>"+
'<input type="text" autocomplete="off" id="checkInCityName_'+xuhao3_len+'" name="checkInCityName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="checkInCity_'+xuhao3_len+'" name="checkInCity" class="input4 " /><input type="hidden" id="checkInProvince_'+xuhao3_len+'" name="checkInProvince" class="input4 " /> <span >*</span>'

							+"</td>"
							+"<td>"+
'<input type="text" id="checkInDate'+xuhao3_len+'" name="checkInDate" class="input4 required" onBlur="getDateDiffById(\'checkInDate'+xuhao3_len+'\',\'checkOutDate'+xuhao3_len+'\',\'numberOfNights'+xuhao3_len+'\');" onclick="WdatePicker({onpicked:function(){checkOutDate'+xuhao3_len+'.focus();checkOutDate'+xuhao3_len+'.click();getDateDiffById(\'checkInDate'+xuhao3_len+'\',\'checkOutDate'+xuhao3_len+'\',\'numberOfNights'+xuhao3_len+'\');},maxDate:\'#F{$dp.$D(\\\'checkOutDate'+xuhao3_len+'\\\',{d:0})}\'})"> <span >*</span>'
		                    +"</td>"
	                        +"<td>"+
'<input type="text" id="checkOutDate'+xuhao3_len+'" name="checkOutDate" class="input4 required" onBlur="getDateDiffById(\'checkInDate'+xuhao3_len+'\',\'checkOutDate'+xuhao3_len+'\',\'numberOfNights'+xuhao3_len+'\');"  onclick="WdatePicker({onpicked:function(){getDateDiffById(\'checkInDate'+xuhao3_len+'\',\'checkOutDate'+xuhao3_len+'\',\'numberOfNights'+xuhao3_len+'\');numberOfNights'+xuhao3_len+'.focus();},minDate:\'#F{$dp.$D(\\\'checkInDate'+xuhao3_len+'\\\',{d:0})}\'})"> <span >*</span>'
	                        +"<td>"+
'<input  class="input4 required digits" id="numberOfNights'+xuhao3_len+'" name="numberOfNights" type="text" />&nbsp;'+nightName
	                        +"<span >*</span></td>" 
	                        +"<td rowspan='2'>"+
"<a onclick=\"deltr('contentTable_hotel','expenses_table_Tr_"+((2*xuhao3)+1)+"','expenses_table_Tr_"+((2*xuhao3)+2)+"')\" href=\"#\">"+deleteName+"</a></td>"
	                    +"</tr>");  
	    
	    $("#contentTable_hotel").append("<tr id='expenses_table_Tr_"+((2*xuhao3)+2)+"'><td colspan='4'><div class='title_font text_lf title_sd'>"+orderingRequestName+"</div> <textarea class='textarea1 required' name='bookingNote' cols='' rows='' onkeyup='limitLength(this,4000);stringReplaceAll(this,\",\",\"，\")'></textarea><span >*</span></td></tr>");     
		 
	    var xxx=xuhao3_len;
		$('#checkInCityName_'+xxx).typeahead({
		    source:function (query, process) {
		        var parameter = {query: query};
		        $.post('findCity.do', parameter, function (data) {
			         var products= eval("(" + data + ")");
			         var results = _.map(products, function (product) {
	                        return product.flexValuesDesc;
	                    });
	                    process(results);
		        });
		    },
		      updater: function (item) {
		    	  var parameter = {cityName: item};
                    $.post('findCityIdAndProvinceId.do', parameter, function (data) {
				         var products= eval("(" + data + ")"); 
				        $('#checkInCityName_'+xxx).val(item);
				        $('#checkInCity_'+xxx).val(products.cityId);
				        $('#checkInProvince_'+xxx).val(products.provinceId);
			        });
                    return item;
                }
	});
		xuhao3=xuhao3+1;
		xuhao3_len=xuhao3_len+1;
		xuhao3_index=xuhao3_index+1;
	}); 
 
	$("#add_expenses_But").click(function(){ 
		
	    $("#contentTable_expenses").append("<tr id='hotel_table_Tr_"+((2*xuhao4)+1)+"' align='left'>"
	    		            +"<td ><lable>"+xuhao4_index+"</lable></td>"
	                        +"<td>"+
'<input type="text"  class="input8 required" readonly name="expenseTypeName" id="expenseTypeName'+((2*xuhao4)+1)+'" value="">'
+'<input type="hidden" class=" " name="expenseTypeId" id="expenseTypeId'+((2*xuhao4)+1)+'" value="">'
+'<button style="width:14%;"  onclick="selectExpenseAccount(\''+$('#AuthorityUrl').val()+'\',\'expenseTypeId'+((2*xuhao4)+1)+'\',\'expenseTypeName'+((2*xuhao4)+1)+'\');" class="chooes_3" type="button" ></button><span >*</span>'
							+"</td>"
							+"<td>"+
'<select type="text" name="period" id="select_period_'+xuhao4_len+'" class="input4 required"><option value=""></option></select><span >*</span>'
							+"</td>"
							+"<td>"+
'<input  class="input4 required number lrunlv" name="amount" type="text" onkeyup="limitLength(this,15)" />&nbsp;'+dollarName
		                    +"<span >*</span></td>" 
							+"<td>"+
'<input  class="input4 required" name="expenseNote" type="text" onkeyup="limitLength(this,4000);stringReplaceAll(this,\',\',\'，\')"/>&nbsp;'
									                    +"<span >*</span></td>" 
	                        +"<td>"+
"<a onclick=\"deltr('contentTable_expenses','hotel_table_Tr_"+((2*xuhao4)+1)+"','')\" href=\"#\">"+deleteName+"</a></td>"
	                    +"</tr>");  
 
	    for ( var i = 0; i < products.length; i++) {
	    	 $("#select_period_"+xuhao4_len).append("<option value='"+products[i].code+"'>"+products[i].code+"</option>");
		}
	   
		xuhao4=xuhao4+1;
		xuhao4_len=xuhao4_len+1;
		xuhao4_index=xuhao4_index+1;
	}); 
	 
	
	
	
	
}

//删除<tr/>
  function deltr(table,index,index2)
 {  
	  if(index!='')
	  $("tr[id='"+index+"']").remove();//删除当前行 
	  if(index2!='')
	  $("tr[id='"+index2+"']").remove();//删除当前行 
	  
	//  var len = $('#'+table+' lable').length; 

	  var len = $('#'+table).find('lable').length; 
	  var i=0;
      for(i = 0;i<len;i++){
          $('#'+table).find('lable:eq('+i+')').text(i+1);
      }
      if(table=='contentTable_travel_plan')
    	  xuhao1_index=i+1;
      else if(table=='contentTable_Ticket')
    	  xuhao2_index=i+1;
      else if(table=='contentTable_hotel')
    	  xuhao3_index=i+1;
      else if(table=='contentTable_expenses')
    	  xuhao4_index=i+1;
      
 }



function updateStatus(status)
{
	$("#businessTripstatus").val(status);
	$("#inputForm2").submit();
}
 
function changeIsBookTicket(fal)
{
	if(fal=="Y")
	{
		$("#IsBookTicketDIV").attr("style","display: block;");
	}else if(fal=="N")
	{
		$("#IsBookTicketDIV").attr("style","display: none;");
	}
}
function changeIsBookHotel(fal)
{
	if(fal=="Y")
	{
		$("#IsBookHotelDIV").attr("style","display: block;");
	}else if(fal=="N")
	{
		$("#IsBookHotelDIV").attr("style","display: none;");
	}
}
 
function queryDataSource()  
{
}

function initxuhao1(xuhao1_len)
{
    var xxx=xuhao1_len;
$('#leaveCitySName_'+xxx).typeahead({
	    source:function (query, process) {
	        var parameter = {query: query};
	        $.post('findCity.do', parameter, function (data) {
		         var products= eval("(" + data + ")");
		         var results = _.map(products, function (product) {
                        return product.flexValuesDesc;
                    });
                    process(results);
	        });
	    },
	      updater: function (item) { 
	    	  var parameter = {cityName: item};
              $.post('findCityIdAndProvinceId.do', parameter, function (data) {
			         var products= eval("(" + data + ")");    
			        $('#leaveCitySName_'+xxx).val(item);
			        $('#leaveCityS_'+xxx).val(products.cityId);
			        $('#leaveProvinceS_'+xxx).val(products.provinceId);
		        }); 
                return item;
            }

	    
});

$('#arrivalCitySName_'+xxx).typeahead({
    source:function (query, process) {
        var parameter = {query: query};
        $.post('findCity.do', parameter, function (data) {
	         var products= eval("(" + data + ")");
	         var results = _.map(products, function (product) {
                    return product.flexValuesDesc;
                });
                process(results);
        });
    },
      updater: function (item) {
    	  var parameter = {cityName: item};
            $.post('findCityIdAndProvinceId.do', parameter, function (data) {
		         var products= eval("(" + data + ")");  
		        $('#arrivalCitySName_'+xxx).val(item);
		        $('#arrivalCityS_'+xxx).val(products.cityId);
		        $('#arrivalProvinceS_'+xxx).val(products.provinceId);
	        });
            return item;
        }
});
}

function initxuhao2(xuhao2_len)
{
	 var xxx=xuhao2_len;
		$('#leaveCityName_'+xxx).typeahead({
		    source:function (query, process) {
		        var parameter = {query: query};
		        $.post('findCity.do', parameter, function (data) {
			         var products= eval("(" + data + ")");
			         var results = _.map(products, function (product) {
	                        return product.flexValuesDesc;
	                    });
	                    process(results);
		        });
		    },
		      updater: function (item) { 
		    	  var parameter = {cityName: item};
               $.post('findCityIdAndProvinceId.do', parameter, function (data) {
				         var products= eval("(" + data + ")"); 
				        $('#leaveCityName_'+xxx).val(item);
				        $('#leaveCity_'+xxx).val(products.cityId);
				        $('#leaveProvince_'+xxx).val(products.provinceId);
			        }); 
                 return item;
             }
		
	});
		
		$('#arrivalCityName_'+xxx).typeahead({
		    source:function (query, process) {
		        var parameter = {query: query};
		        $.post('findCity.do', parameter, function (data) {
			         var products= eval("(" + data + ")");
			         var results = _.map(products, function (product) {
	                        return product.flexValuesDesc;
	                    });
	                    process(results);
		        });
		    },
		      updater: function (item) {
		    	  var parameter = {cityName: item};
                 $.post('findCityIdAndProvinceId.do', parameter, function (data) {
				         var products= eval("(" + data + ")"); 
				        $('#arrivalCityName_'+xxx).val(item);
				        $('#arrivalCity_'+xxx).val(products.cityId);
				        $('#arrivalProvince_'+xxx).val(products.provinceId);
			        });
                 return item;
             }
	});
}
function initxuhao3(xuhao3_len)
{
	var xxx=xuhao3_len;
	$('#checkInCityName_'+xxx).typeahead({
	    source:function (query, process) {
	        var parameter = {query: query};
	        $.post('findCity.do', parameter, function (data) {
		         var products= eval("(" + data + ")");
		         var results = _.map(products, function (product) {
                        return product.flexValuesDesc;
                    });
                    process(results);
	        });
	    },
	      updater: function (item) {
	    	  var parameter = {cityName: item};
                $.post('findCityIdAndProvinceId.do', parameter, function (data) {
			         var products= eval("(" + data + ")"); 
			        $('#checkInCityName_'+xxx).val(item);
			        $('#checkInCity_'+xxx).val(products.cityId);
			        $('#checkInProvince_'+xxx).val(products.provinceId);
		        });
                return item;
            }
});
}
function initDate()
{ 
if(xuhao1>0)
{ 
	for (var i = 1; i <= xuhao1; i++) { 
		initxuhao1(i);  
	}
}
if(xuhao2>0)
{ 
	for (var i = 1; i <= xuhao2; i++) {  
		initxuhao2(i);  
	}
}
if(xuhao3>0)
{ 
	for (var i = 1; i <= xuhao3; i++) {  
		initxuhao3(i); 
	}
}
 
}
 

var expenseId;
var expenseName;
function selectExpenseAccount(url,p1,p2) {
	var orgId = '';
	var channelType = '';
	expenseId=p1;
	expenseName=p2;
	$('#selectiframe').attr('src','gotoExpenseAccountLov.do?url='+url+'&needEncumbrance=Y&orgId='+orgId+'&channelType='+channelType+'&d='+new Date().getTime());
	$('#wDialog').window('open');
}

function expenseAccountSet(expenseAccountId,expenseAccountName){
	$('#'+expenseId).val(expenseAccountId);
	$('#'+expenseName).val(expenseAccountName);
}
 