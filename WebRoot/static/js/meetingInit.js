function cleanMeetingFacility()
{
	$(".meetingFacility").remove();
	addTableTr();
}
function dataMeetingFacilityChange1(obj)
{ 
	if($('#cleanMeetingFacilityChange1').val()!='')
	{
			if(obj.value!= $('#cleanMeetingFacilityChange1').val())
			{
				cleanMeetingFacility();
			}
	}else
	{
		$('#cleanMeetingFacilityChange1').val(obj.value);
	}
}
function dataMeetingFacilityChange2(obj)
{
	if($('#cleanMeetingFacilityChange2').val()!='')
	{
			if(obj.value!= $('#cleanMeetingFacilityChange2').val())
			{
				cleanMeetingFacility();
			}
	}else
	{
		$('#cleanMeetingFacilityChange2').val(obj.value);
	}
}
function isInnerMeetingChange(v) {
		if($(v).val() == "1"){
			$("#mettingAddr").hide(); 
			$("#meetingFacilitySelect").show(); 
		}else if($(v).val()== "0"){
			$("#mettingAddr").show(); 
			$("#meetingFacilitySelect").hide(); 
		}
	}

function meetingFacilityTypeChange(t,checkval){
	 var v=$(t).parents(".meetingFacility").find(".meetingFacilityId");
 
	 if(checkval==null || checkval=='')
	{
		 $(t).parents(".meetingFacility").find(".meetingFacilityCode").val("");
		 $(t).parents(".meetingFacility").find(".meetingFacilityAddress").val("");
	}
      var meetingFacilityType = $(t).val();  
      $.get("getMeetingFacilityListByType.do", {meetingFacilityType:meetingFacilityType,checkval:checkval,meetingBeginTime:$('#meetingBeginTime').val(),meetingEndTime:$('#meetingEndTime').val()}, function(data) { 
    	 $(v).html(data);
      });
	
}

function meetingFacilityIdChange(t){
	var address= $(t).find("option:selected").attr("address") ;
	var remark= $(t).find("option:selected").attr("remark"); 
	if(address!=null && address!='' && address!="null"){
	$(t).parent().next().children().val(address);
	}else{
	$(t).parent().next().children().val('');	
	}
	if(remark!=null && remark!='' && remark!="null"){
    $(t).parent().next().next().children().val(remark);
    }else{
    $(t).parent().next().next().children().val('');	
    }
   
	
}

function delMeetingFacility(t){
	$(t).parents(".meetingFacility").remove();
	
}


 $(document).ready(function() { 
	
	 $('.meetingFacilityType').change(function() {  
		// var v=$(this).parent().next().children();
	    });  
	 
	 $('.meetingFacilityId').change(function() {  
	     
	    }); 
	 
	 $("#add_meetingFacility_But").click(addTableTr); 
 
});
 
function addTableTr()
{
	 $("#contentTable_eetingFacility_plan").append("<tr  class='meetingFacility'> <td><select id='meetingFacility' onchange='meetingFacilityTypeChange(this);'  class='input4 required meetingFacilityType'><option value=''>请选择</option></select></td><td>" +
	    		"<select onchange='meetingFacilityIdChange(this);'   name='meetingFacilityId' id='meetingFacilityId' class='input4 required meetingFacilityId'> <option value=''>请选择</option>  </select></td>" +
	    		"<td><input  type='text' class='input4 meetingFacilityCode'  readonly='readonly' /></td><td><input  type='text' class='input4 meetingFacilityAddress'  readonly='readonly' /></td> <td><a href='#' onclick='delMeetingFacility(this)'>删除</a></td>    </tr>");       
     for (var i = 0; i < facilityListJson.length; i++) {
			$("#meetingFacility").append(
					"<option value='"+facilityListJson[i].flexValue+"'>"
							+ facilityListJson[i].flexValuesDesc
							+ "</option>");
		}
}

function dateDiff(date1, date2)
{ 
   var dt1 = new Date(Date.parse(date1.replace(/-/g, '/')));
   var dt2 = new Date(Date.parse(date2.replace(/-/g, '/')));
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
	
	$('#selectiframe').attr('src','gotoPersonList.do?url=meetingList.do&departmentCode='+departmentCode+'&orgId='+orgId+'&channelType='+channelType+'&d='+new Date().getTime());
	$('#wDialog').window('open');
}


function selectPersonMulitple() {
	var orgId = '';
	var channelType = '';
	var departmentCode = ''; 
	
	$('#selectiframe').attr('src','gotoPersonMultipleList.do?ignoreOrg=Y&url=meetingList.do&departmentCode='+departmentCode+'&orgId='+orgId+'&channelType='+channelType+'&d='+new Date().getTime());
	$('#wDialog').window('open');
}
function cleanPersonMulitple()
{
	$('#otherPersonId').val('');
	$('#otherPersonName').val('');
	$('#meetingPersonNum').val('');
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
		$('#meetingPersonNum').val($('#otherPersonId').val().split(",").length-1);
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


var xuhao2=1;
var xuhao3=1;
var xuhao4=1;
function businessTripInit(){
		

	


	$("#add_Ticket_But").click(function(){
	    var _len = $("#contentTable_Ticket tr").length; 
	    if(_len>1)
	    	xuhao2=parseInt(_len/2)+1;
	    $("#contentTable_Ticket").append("<tr id='ticket_table_Tr_"+_len+"' align='left'>"
	    		            +"<td rowspan='2'>"+xuhao2+"</td>"
	                        +"<td>"+
'<input type="text" id="leaveTime'+_len+'" name="leaveTime" class="input4 required" onFocus="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true})"/> <img onclick="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true,el:leaveTime'+_len+'})" src="static/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"> <span >*</span>'
							+"</td>"
							+"<td>"+
'<input type="text" autocomplete="off" id="leaveCityName_'+_len+'" name="leaveCityName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="leaveCity_'+_len+'" name="leaveCity" class="input4 " /><input type="hidden" id="leaveProvince_'+_len+'" name="leaveProvince" class="input4 " /> <span >*</span>'
		                    +"</td>"
	                        +"<td>"+
'<input type="text" autocomplete="off" id="arrivalCityName_'+_len+'" name="arrivalCityName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="arrivalCity_'+_len+'" name="arrivalCity" class="input4 " /><input type="hidden" id="arrivalProvince_'+_len+'" name="arrivalProvince" class="input4 " /> <span >*</span>'
                     +"</td>"
	                        +"<td>"+
'<select class="input4 required" name="transportationTypeId"><option value=""></option>'+$("#trafficListDiv").html()+'</select> 	<span >*</span> '
	                        +"</td>" 
	                        +"<td rowspan='2'>"+
"<a onclick=\"deltr('ticket_table_Tr_"+_len+"','ticket_table_Tr_"+(_len+1)+"')\" href=\"#\">"+deleteName+"</a></td>"
	                    +"</tr>");           
	    $("#contentTable_Ticket").append("<tr id='ticket_table_Tr_"+(_len+1)+"'><td colspan='4'><div class='title_font text_lf title_sd'>"+orderingRequestName+"</div> <textarea class='textarea1 required' name='bookingNoteS' cols='' rows=''></textarea> <span >*</span></td></tr>");     
 
		$('#leaveCityName_'+_len).typeahead({
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
				        $('#leaveCityName_'+_len).val(item);
				        $('#leaveCity_'+_len).val(products.cityId);
				        $('#leaveProvince_'+_len).val(products.provinceId);
			        }); 
                    return item;
                }
		
	});
		
		$('#arrivalCityName_'+_len).typeahead({
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
				        $('#arrivalCityName_'+_len).val(item);
				        $('#arrivalCity_'+_len).val(products.cityId);
				        $('#arrivalProvince_'+_len).val(products.provinceId);
			        });
                    return item;
                }
	});

	}); 
	
	
	$("#add_hotel_But").click(function(){
	    var _len = $("#contentTable_hotel tr").length; 
	    if(_len>1)
	    	xuhao3=parseInt(_len/2)+1;
	    $("#contentTable_hotel").append("<tr id='expenses_table_Tr_"+_len+"' align='left'>"
	    		            +"<td rowspan='2' >"+xuhao3+"</td>"
	                        +"<td>"+
'<input type="text" autocomplete="off" id="checkInCityName_'+_len+'" name="checkInCityName" class="input4 required" Onchange="checkLeaveCityS(this)"/><input type="hidden" id="checkInCity_'+_len+'" name="checkInCity" class="input4 " /><input type="hidden" id="checkInProvince_'+_len+'" name="checkInProvince" class="input4 " /> <span >*</span>'

							+"</td>"
							+"<td>"+
'<input type="text" id="checkInDate'+_len+'" name="checkInDate" class="input4 required" onClick="WdatePicker()"> <span >*</span>'
		                    +"</td>"
	                        +"<td>"+
'<input type="text" id="checkOutDate'+_len+'" name="checkOutDate" class="input4 required" onClick="WdatePicker()"> <span >*</span>'
	                        +"<td>"+
'<input  class="input4 required digits" name="numberOfNights" type="text" />'+nightName
	                        +"<span >*</span></td>" 
	                        +"<td rowspan='2'>"+
"<a onclick=\"deltr('expenses_table_Tr_"+_len+"','expenses_table_Tr_"+(_len+1)+"')\" href=\"#\">"+deleteName+"</a></td>"
	                    +"</tr>");  
	    
	    $("#contentTable_hotel").append("<tr id='expenses_table_Tr_"+(_len+1)+"'><td colspan='4'><div class='title_font text_lf title_sd'>"+orderingRequestName+"</div> <textarea class='textarea1 required' name='bookingNote' cols='' rows=''></textarea><span >*</span></td></tr>");     
		 
	  
		$('#checkInCityName_'+_len).typeahead({
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
				        $('#checkInCityName_'+_len).val(item);
				        $('#checkInCity_'+_len).val(products.cityId);
				        $('#checkInProvince_'+_len).val(products.provinceId);
			        });
                    return item;
                }
	});

	}); 
 
	$("#add_expenses_But").click(function(){
	    var _len = $("#contentTable_expenses tr").length; 
	    if(_len>1)
	    	xuhao4=parseInt(_len/2)+1;
	    $("#contentTable_expenses").append("<tr id='hotel_table_Tr_"+_len+"' align='left'>"
	    		            +"<td rowspan='2'>"+xuhao4+"</td>"
	                        +"<td>"+
' <select class="input4 required" name="expenseTypeId"><option value="7">差旅费</option></select> <span >*</span>'
							+"</td>"
							+"<td>"+
'<select type="text" name="period" id="select_period_'+_len+'" class="input4 required"><option value=""></option></select><span >*</span>'
							+"</td>"
							+"<td>"+
'<input  class="input4 required number" name="amount" type="text" />'+dollarName
		                    +"<span >*</span></td>" 
	                        +"<td rowspan='2'>"+
"<a onclick=\"deltr('hotel_table_Tr_"+_len+"','hotel_table_Tr_"+(_len+1)+"')\" href=\"#\">"+deleteName+"</a></td>"
	                    +"</tr>");   
	    $("#contentTable_expenses").append("<tr id='hotel_table_Tr_"+(_len+1)+"'><td colspan='3'><div class='title_font text_lf title_sd'>"+budgetStatementName+"</div> <textarea class='textarea1 required' name='expenseNote' cols='' rows=''></textarea><span >*</span></td></tr>");     
		 
 
 
	    for ( var i = 0; i < products.length; i++) {
	    	 $("#select_period_"+_len).append("<option value='"+products[i].code+"'>"+products[i].code+"</option>");
		}
	   
	}); 
	 
	
	
	
	
}

//删除<tr/>
  function deltr(index,index2)
    {
 
	  $("tr[id='"+index+"']").remove();//删除当前行 
 
	  $("tr[id='"+index2+"']").remove();//删除当前行 
    }



function updateStatus(status)
{
	$("#status").val(status);
	$("#inputForm").submit();
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

function initDate()
{
 
var travel_length=$("#contentTable_travel_plan tr").length;
var ticket_length=$("#contentTable_Ticket tr").length;
var hotel_length=$("#contentTable_hotel tr").length;



if(travel_length>0)
{
	for (var i = 1; i < travel_length; i++) {
		$('#leaveCitySName_'+i).typeahead({
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
				        $('#leaveCitySName_'+i).val(item);
				        $('#leaveCityS_'+i).val(products.cityId);
				        $('#leaveProvinceS_'+i).val(products.provinceId);
			        });
	                return item;
	            }
	});

	$('#arrivalCitySName_'+i).typeahead({
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
			        $('#arrivalCitySName_'+i).val(item);
			        $('#arrivalCityS_'+i).val(products.cityId);
			        $('#arrivalProvinceS_'+i).val(products.provinceId);
		        });
	            return item;
	        }
	});
	}
}


if(ticket_length>0)
{
	for (var _len = 1; _len < ticket_length; _len++) {

		$('#leaveCityName_'+_len).typeahead({
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
				        $('#leaveCityName_'+_len).val(item);
				        $('#leaveCity_'+_len).val(products.cityId);
				        $('#leaveProvince_'+_len).val(products.provinceId);
			        }); 
		            return item;
		        }

		});

		$('#arrivalCityName_'+_len).typeahead({
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
				        $('#arrivalCityName_'+_len).val(item);
				        $('#arrivalCity_'+_len).val(products.cityId);
				        $('#arrivalProvince_'+_len).val(products.provinceId);
			        });
		            return item;
		        }
		});
	}
}
	
if(hotel_length>0)
{
	for (var _len = 1; _len < hotel_length; _len++) {

		$('#checkInCityName_'+_len).typeahead({
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
				        $('#checkInCityName_'+_len).val(item);
				        $('#checkInCity_'+_len).val(products.cityId);
				        $('#checkInProvince_'+_len).val(products.provinceId);
			        });
		            return item;
		        }
		});
	}
}
 
}
 

 