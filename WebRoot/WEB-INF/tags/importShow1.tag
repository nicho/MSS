<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script language="javascript" type="text/javascript">
$(document).ready(function() {
	/* var pagesize = '${page.pageSize}';   */
/* 	 $("#pageSizeTag").find("option[value='"+pagesize+"']").attr("selected",true); */
	 
	 
   /*  $('#pageNumTag').bind('keypress',function(event){
         if(event.keyCode == "13")    
         {
           
             if($('#pageNumTag').val()<0 || $('#pageNumTag').val()>'${page.pages}')
            {
            	 alert('你输入的页码不正确');
            }  
         }
     });*/
	 $.ajax({
			type : 'POST',
			url : "${ctx}/getDefultFilePath.do",
			success : function(data) {
                $("#savePath").val(data);           
			}
		});
     /*   $.extend($.fn.datagrid.defaults.editors, {   
    	     text: {   
    	          init: function(container, options){   
    	             var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);   
    	            return input;   
    	        },   
    	         getValue: function(target){   
    	            return $(target).val();   
    	        },   
    	       setValue: function(target, value){   
    	            $(target).val(value);   
    	       },   
    	         resize: function(target, width){   
    	            var input = $(target);   
    	            if ($.boxModel == true){   
    	               input.width(width - (input.outerWidth() - input.width()));   
    	             } else {   
    	              input.width(width);   
    	       }   
    	       }   
    	   }   
    	  });  
     
     
     $.extend($.fn.datagrid.methods, {
    	 editCell: function(jq,param){
    	 return jq.each(function(){
    	 var opts = $(this).datagrid('options');
    	 var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
    	 for(var i=0; i<fields.length; i++){
    	 var col = $(this).datagrid('getColumnOption', fields[i]);
    	 col.editor1 = col.editor;
    	 if (fields[i] != param.field){
    	 col.editor = null;
    	 }
    	 }
    	 $(this).datagrid('beginEdit', param.index);
    	 var ed = $(this).datagrid('getEditor', param);
    	 if (ed){
    	 if ($(ed.target).hasClass('textbox-f')){
    	 $(ed.target).textbox('textbox').focus();
    	 } else {
    	 $(ed.target).focus();
    	 }
    	 }else{
    		  var row = $(this).datagrid('getData').rows[param.index];
        		 $.ajax({
        				type : 'POST',
        				data:{
        				fileId:	row['fileId'],
        				desc :row['fileDesc']
        				},
        				url : "${ctx}/updateFileComment.do",
        				success : function(data) {
        	                if(!data){
        	                window.alert("更新文件说明失败");	
        	                }         
        				}
        			});    	  
    	 }
    	 for(var i=0; i<fields.length; i++){
    	 var col = $(this).datagrid('getColumnOption', fields[i]);
    	 col.editor = col.editor1;
    	 }
    	 });
    	 },
    	 enableCellEditing: function(jq){
    	 return jq.each(function(){
    	 var dg = $(this);
    	 var opts = dg.datagrid('options');
    	 opts.oldOnClickCell = opts.onClickCell;
    	 opts.onClickCell = function(index, field){
    	 if (opts.editIndex != undefined){
    	 if (dg.datagrid('validateRow', opts.editIndex)){
    	 dg.datagrid('endEdit', opts.editIndex);
    	 opts.editIndex = undefined;
    	 } else {
    	 return;
    	 }
    	 }
    	 dg.datagrid('selectRow', index).datagrid('editCell', {
    	 index: index,
    	 field: field
    	 });
    	 opts.editIndex = index;
    	 opts.oldOnClickCell.call(this, index, field);
    	 }
    	 });
    	 }
    	 });
    	  
    	 $(function(){
    	 $('#contentTable_import_file').datagrid().datagrid('enableCellEditing');
    	 }) */
       
     var functionId1 ='${functionId1}';
     var businessId1 = '${businessId1}';
     var functionId2='${functionId2}';
     var businessId2 = '${businessId2}';
     var functionId3 ='${functionId3}';
     var businessId3 = '${businessId3}';
     
/* 	 new AjaxUpload("#importFile",{
			action : '${ctx}/importFile.do?', //提交的url
			autoSubmit : true, //不自动提交
			responseType : "json", //服务器返回的数据格式
			name : 'file', //提交到后台的文件参数名称
			data:{functionId:functionId},
			onSubmit : function(file, extension) { // 文件选择后使用,file--上传文件名, extension--文件扩展名
			//验证文件后缀名
			/* extension = extension.toLowerCase(); 
			/* if (extension&& /^(csv)$/.test(extension)) {
			
				return true;
			} else {
				return false;
			} 
			//后缀名的验证（未做）
			   parent.parent.$.messager.progress({
					title : '提示',
					text : '文件正在上传中，请稍后....'
				});
			},onComplete : function(file, data) {
				parent.parent.$.messager.progress('close');
				if(data){
					
					$('#contentTable_import_file').datagrid('appendRow',{'fileId':data.fileId,
					'sourceFileName':data.sourceFileName,'filesize':data.fileSizes,
					'fileType':data.fileType,'creationDate':data.importDate,'fileDesc':data.fileDesc});
					var fileIds = $("#fileIds").val();
					fileIds = fileIds+data.fileId+",";
					$("#fileIds").val(fileIds);
					window.alert("上传成功");
				}else{
					window.alert("上传失败");
				}
				
			} 
		}); */
	 /* $('#contentTable_import_file').datagrid({
			url : '${ctx}/getImportFile.do?functionId='+functionId+'&businessId='+businessId,
			border : false,
			fitColumns : true,
			fit:true,
			idField : 'fileId',
			nowrap : false,
			sortOrder : 'desc',
			nowrap : false,
			loadMsg:'',
			columns : [ [ 
			    {
					field : 'sourceFileName',
					title : '<spring:message code="file_name"/>',
					width : 150
				}, {
					field : 'fileSizes',
					title : '<spring:message code="file_size"/>',
					width : 150
				},{
					field : 'importDate',
					title : '<spring:message code="import_date"/>',
					width : 150

				},{
					field : 'fileDesc',
					title : '<spring:message code="file_desc1"/>',
					width : 150,
					editor:'text'
				},
				{
					field : 'action',
					title : '<spring:message code="operate"/>',
					width : 200,
					align:'center',
					formatter : function(value, row, index) {
						var str = '';
						str+='<a href="${ctx}/downloadFile.do?fileId='+row.fileId+'"><spring:message code="view_upload"/></a>';				
						str += '&nbsp;';
						
						return str; 
					}
				} 
			
			] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				//$(this).datagrid('selectRow', 0);
			}
		}); */
		
		$.ajax({
			type : 'POST',
			data:{
				functionId:functionId1,
				businessId:businessId1,
				project:'${ctx}'
			},
			url : '${ctx}/getImportFile.do',
			success : function(returnData) {
             if(returnData){
            	 returnData = eval(returnData);
            	 for(var i =0;i<returnData.length;i++){
            		 //<a onclick="showMenuPage3('${ctx}/downloadFile.do?fileId=${list.fileId}')"><spring:message code="view_upload"/></a>&nbsp;&nbsp;&nbsp;&nbsp; 
            		 var desc =returnData[i].fileDesc;
            		 if(typeof(desc) == 'undefined'){
            			 desc=''; 
            		 }
            		 if(returnData[i].isPerview ==true){
            			 if(returnData[i].fileType=="jpg" || returnData[i].fileType=="png" || returnData[i].fileType=="bmp" || returnData[i].fileType=="gif" ||returnData[i].fileType=="JPG" || returnData[i].fileType=="PNG" || returnData[i].fileType=="BMP" || returnData[i].fileType=="GIF" ){	 
            		     $("#importTable1").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a href="+"\"${ctx}/waitPreviewImage.do?fileId="+returnData[i].fileId+"\""+" target=_blank"+">预览 | </a><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	  	 
            			 }else{
            				 $("#importTable1").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a href="+"\"${ctx}/waitPreviewFile.do?fileId="+returnData[i].fileId+"\""+" target=_blank"+">预览 | </a><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	 	 
            			 }
        				
            		 }else{
            			 $("#importTable1").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	  
            		 }
            		
            	 }
            	
            	 
            	 
             }         
	        }
		}); 
		$.ajax({
			type : 'POST',
			data:{
				functionId:functionId2,
				businessId:businessId2,
				project:'${ctx}'
			},
			url : '${ctx}/getImportFile.do',
			success : function(returnData) {
             if(returnData){
            	 returnData = eval(returnData);
            	 for(var i =0;i<returnData.length;i++){
            		 //<a onclick="showMenuPage3('${ctx}/downloadFile.do?fileId=${list.fileId}')"><spring:message code="view_upload"/></a>&nbsp;&nbsp;&nbsp;&nbsp; 
            		 var desc =returnData[i].fileDesc;
            		 if(typeof(desc) == 'undefined'){
            			 desc=''; 
            		 }
            		 if(returnData[i].isPerview ==true){
            			 if(returnData[i].fileType=="jpg" || returnData[i].fileType=="png" || returnData[i].fileType=="bmp" || returnData[i].fileType=="gif" ){	 
            		     $("#importTable2").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a href="+"\"${ctx}/waitPreviewImage.do?fileId="+returnData[i].fileId+"\""+" target=_blank"+">预览 | </a><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	  	 
            			 }else{
            				 $("#importTable2").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a href="+"\"${ctx}/waitPreviewFile.do?fileId="+returnData[i].fileId+"\""+" target=_blank"+">预览 | </a><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	 	 
            			 }
        				
            		 }else{
            			 $("#importTable2").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	  
            		 }
            		
            	 }
            	
            	 
            	 
             }         
	        }
		}); 
		$.ajax({
			type : 'POST',
			data:{
				functionId:functionId3,
				businessId:businessId3,
				project:'${ctx}'
			},
			url : '${ctx}/getImportFile.do',
			success : function(returnData) {
             if(returnData){
            	 returnData = eval(returnData);
            	 for(var i =0;i<returnData.length;i++){
            		 //<a onclick="showMenuPage3('${ctx}/downloadFile.do?fileId=${list.fileId}')"><spring:message code="view_upload"/></a>&nbsp;&nbsp;&nbsp;&nbsp; 
            		 var desc =returnData[i].fileDesc;
            		 if(typeof(desc) == 'undefined'){
            			 desc=''; 
            		 }
            		 if(returnData[i].isPerview ==true){
            			 if(returnData[i].fileType=="jpg" || returnData[i].fileType=="png" || returnData[i].fileType=="bmp" || returnData[i].fileType=="gif" ){	 
            		     $("#importTable3").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a href="+"\"${ctx}/waitPreviewImage.do?fileId="+returnData[i].fileId+"\""+" target=_blank"+">预览 | </a><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	  	 
            			 }else{
            				 $("#importTable3").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a href="+"\"${ctx}/waitPreviewFile.do?fileId="+returnData[i].fileId+"\""+" target=_blank"+">预览 | </a><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	 	 
            			 }
        				
            		 }else{
            			 $("#importTable3").append("<tr><td>"+returnData[i].sourceFileName+"</td><td>"+returnData[i].fileSizes+"</td><td>"+returnData[i].personName+"</td><td>"+returnData[i].importDate+"</td><td>"+desc+"</td><td><a onclick="+"\'"+"showMenuPage3("+"\"${ctx}/downloadFile.do?fileId="+returnData[i].fileId+"\""+")"+"\'"+"><spring:message code='view_upload'/></a></td></tr>")	  
            		 }
            		
            	 }
            	
            	 
            	 
             }         
	        }
		}); 
		
		
/* 	 $.ajax({
			type : 'POST',
			data:{
				functionId:functionId,
				businessId:businessId
			},
			url : '${ctx}/getImportFile.do',
			success : function(returnData) {
       if(returnData){
    	 $('#contentTable_import_file').datagrid('reload', returnData);
       }         
	   }
		});    */
		
	});
//时间格式化
Date.prototype.format = function (format) {
if (!format) {
format = "yyyy-MM-dd hh:mm:ss";
}
var o = {
"M+": this.getMonth() + 1, // month
"d+": this.getDate(), // day
"h+": this.getHours(), // hour
"m+": this.getMinutes(), // minute
"s+": this.getSeconds(), // second
"q+": Math.floor((this.getMonth() + 3) / 3), // quarter
"S": this.getMilliseconds()
// millisecond
};
if (/(y+)/.test(format)) {
format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
}
for (var k in o) {
if (new RegExp("(" + k + ")").test(format)) {
format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
}
}
return format;
};
function fomatDateTime(str) {
return (new Date(parseInt(str.substring(str.indexOf('(') + 1, str.indexOf(')'))))).format("yyyy-MM-dd hh:mm:ss");
}

function fomatDate(str) {
return (new Date(parseInt(str.substring(str.indexOf('(') + 1, str.indexOf(')'))))).format("yyyy-MM-dd");
}
function changeAttach2(){
	
	var name = $("#importTable2").attr("name");
	if(name=="importTable_open2"){
		$("#importTable2").attr("name","importTable_closed2");
		$("#battach2").css("background-image","url('${ctx}/static/images/a_35.jpg')");
		$('#importTable2').hide();
		
	}else{
		$("#importTable2").attr("name","importTable_open2");
		$('#importTable2').show();
		$("#battach2").css("background-image","url('${ctx}/static/images/a_36.jpg')");
	}
}
function changeAttach1(){
	
	var name = $("#importTable1").attr("name");
	if(name=="importTable_open1"){
		$("#importTable1").attr("name","importTable_closed1");
		$("#battach1").css("background-image","url('${ctx}/static/images/a_35.jpg')");
		$('#importTable1').hide();
		
	}else{
		$("#importTable1").attr("name","importTable_open1");
		$('#importTable1').show();
		$("#battach1").css("background-image","url('${ctx}/static/images/a_36.jpg')");
	}
}
function changeAttach3(){
	
	var name = $("#importTable3").attr("name");
	if(name=="importTable_open3"){
		$("#importTable3").attr("name","importTable_closed1");
		$("#battach3").css("background-image","url('${ctx}/static/images/a_35.jpg')");
		$('#importTable3').hide();
		
	}else{
		$("#importTable3").attr("name","importTable_open1");
		$('#importTable3').show();
		$("#battach3").css("background-image","url('${ctx}/static/images/a_36.jpg')");
	}
}
/* function addFile(){
	  var _len = $("#contentTable_travel_plan tr").length; 
	    $("#contentTable_travel_plan").append("<tr id='trip_table_Tr_"+_len+"' align='left'>"
	    		            +"<td  rowspan='2'>"+_len+"</td>"
	                        +"<td>"+
'<input type="text" id="leaveDate'+_len+'" name="leaveDate" class="input3" onFocus="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true})"/> <img onclick="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true,el:leaveDate'+_len+'})" src="static/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">'
							+"</td>"
							+"<td>"+
'<input type="text" autocomplete="off"  id="leaveCitySName_'+_len+'" name="leaveCitySName" class="input3 " Onchange="checkLeaveCityS(this)"/><input type="hidden" id="leaveCityS_'+_len+'" name="leaveCityS" class="input3 " /><input type="hidden" id="leaveProvinceS_'+_len+'" name="leaveProvinceS" class="input3 " /> '
		                    +"</td>"
	                        +"<td>"+
'<input type="text"  id="arrivalDate'+_len+'" name="arrivalDate" class="input3" onFocus="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true})"/> <img onclick="WdatePicker({startDate:\'%y-%M-01 00:00:00\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true,el:arrivalDate'+_len+'})" src="static/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">'
	                        +"</td>"
	                        +"<td>"+
'<input type="text" autocomplete="off"  id="arrivalCitySName_'+_len+'" name="arrivalCitySName" class="input3 " Onchange="checkLeaveCityS(this)"/><input type="hidden" id="arrivalCityS_'+_len+'" name="arrivalCityS" class="input3 " /><input type="hidden" id="arrivalProvinceS_'+_len+'" name="arrivalProvinceS" class="input3 " /> '
	                        +"</td>"
	                        +"<td rowspan='2'>"+
"<a onclick=\"deltr('trip_table_Tr_"+_len+"')\" href=\"#\">删除</a></td>"
	                    +"</tr>");    
	
	
} */
function batchImport(){
	window.open("${ctx}/batchImportFile.do?functionId=${functionId3}&businessId=${businessId3}");
}




</script>
    <!-- 上传通用组件 -->
  <c:if test="${attachSize1>0}">
<div class="list_box mar_2">
   <div class="aulist_3"><b onclick="changeAttach1()" id="battach1" style="background-image:url('${ctx}/static/images/a_36.jpg');height:10px"></b>${title1}
     <div class="list_an" style="padding-right: 0px;margin-top: 2px;margin-left: -90px;">
 <!--    <button class="btn  btn-primary zz_rging" type="button" id="batchImport" name="batchImport" onclick="batchImportFile()">批量下载</button>  -->
      <a id="importFileButton1" class="btn  btn-primary zz_rging" style="font-weight:normal;" onclick="window.open('${ctx}/batchImportFile.do?functionId=${functionId1}&businessId=${businessId1}')">批量下载</a>
     </div>
   </div>
   <div>
   <input type="hidden" id="fileIds1" value=""/>
   <input type="hidden" id="fileDescs1" value=""/>
   </div>
   <!--  <div style="width:100%;height:10px;background:#fff;"></div> 
     <table id="contentTable_import_file" class="easyui-datagrid" style="height:50px">
    </table>  -->
    <div class="table_auto scroll_auto">
	 <table id="importTable1" name="importTable_open1" width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-condensed table-hover ">
    <tr class="tbth ">
    		<td  class="bzh"><spring:message code="file_name"/></td> 
    		<td  class="bzh"><spring:message code="file_size"/></td>
    		<td  class="bzh">上传用户</td>
    		<td  class="bzh"><spring:message code="import_date"/></td>
    		<td  class="bzh"><spring:message code="file_desc1"/></td>     		 
    	    <td  class="bzh"><spring:message code='Operation'/></td>
    	</tr>
<%--     	<c:forEach var="list"  items="${list}" varStatus="vstatus">
    	<tr>
    		<td> ${list.sourceFileName} </td> 
    		<td>${list.fileSizes}</td>
    		<td>${list.importDate}</td>
    		<td>${list.fileDesc}</td> 	
    		<td>
    		<a onclick="showMenuPage3('${ctx}/downloadFile.do?fileId=${list.fileId}')"><spring:message code="view_upload"/></a>&nbsp;&nbsp;&nbsp;&nbsp; 
    		</td>
    	</tr>
    	</c:forEach>  --%>
    </table>
</div>
    
    
  </div>
  </c:if>
   <c:if test="${attachSize2>0}">
 <div class="list_box mar_2">
   <div class="aulist_3"><b onclick="changeAttach2()" id="battach2" style="background-image:url('${ctx}/static/images/a_36.jpg');height:10px"></b>${title2}
     <div class="list_an" style="padding-right: 0px;margin-top: 2px;margin-left: -90px;">
 <!--    <button class="btn  btn-primary zz_rging" type="button" id="batchImport" name="batchImport" onclick="batchImportFile()">批量下载</button>  -->
      <a id="importFileButton2" class="btn  btn-primary zz_rging" style="font-weight:normal;" onclick="window.open('${ctx}/batchImportFile.do?functionId=${functionId2}&businessId=${businessId2}')">批量下载</a>
     </div>
   </div>
   <div>
   <input type="hidden" id="fileIds2" value=""/>
   <input type="hidden" id="fileDescs2" value=""/>
   </div>
   <!--  <div style="width:100%;height:10px;background:#fff;"></div> 
     <table id="contentTable_import_file" class="easyui-datagrid" style="height:50px">
    </table>  -->
    <div class="table_auto scroll_auto">
	 <table id="importTable2" name="importTable_open2" width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-condensed table-hover ">
    <tr class="tbth ">
    		<td  class="bzh"><spring:message code="file_name"/></td> 
    		<td  class="bzh"><spring:message code="file_size"/></td>
    		<td  class="bzh">上传用户</td>
    		<td  class="bzh"><spring:message code="import_date"/></td>
    		<td  class="bzh"><spring:message code="file_desc1"/></td>     		 
    	    <td  class="bzh"><spring:message code='Operation'/></td>
    	</tr>
<%--     	<c:forEach var="list"  items="${list}" varStatus="vstatus">
    	<tr>
    		<td> ${list.sourceFileName} </td> 
    		<td>${list.fileSizes}</td>
    		<td>${list.importDate}</td>
    		<td>${list.fileDesc}</td> 	
    		<td>
    		<a onclick="showMenuPage3('${ctx}/downloadFile.do?fileId=${list.fileId}')"><spring:message code="view_upload"/></a>&nbsp;&nbsp;&nbsp;&nbsp; 
    		</td>
    	</tr>
    	</c:forEach>  --%>
    </table>
</div>
    
    
  </div> 
  </c:if>
     <c:if test="${attachSize3>0}">
 <div class="list_box mar_2">
   <div class="aulist_3"><b onclick="changeAttach3()" id="battach3" style="background-image:url('${ctx}/static/images/a_36.jpg');height:10px"></b>${title3}
     <div class="list_an" style="padding-right: 0px;margin-top: 2px;margin-left: -90px;">
 <!--    <button class="btn  btn-primary zz_rging" type="button" id="batchImport" name="batchImport" onclick="batchImportFile()">批量下载</button>  -->
      <a id="importFileButton3" class="btn  btn-primary zz_rging" style="font-weight:normal;" onclick="batchImport();">批量下载</a>
     </div>
   </div>
   <div>
   <input type="hidden" id="fileIds3" value=""/>
   <input type="hidden" id="fileDescs3" value=""/>
   </div>
   <!--  <div style="width:100%;height:10px;background:#fff;"></div> 
     <table id="contentTable_import_file" class="easyui-datagrid" style="height:50px">
    </table>  -->
    <div class="table_auto scroll_auto">
	 <table id="importTable3" name="importTable_open3" width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-condensed table-hover ">
    <tr class="tbth ">
    		<td  class="bzh"><spring:message code="file_name"/></td> 
    		<td  class="bzh"><spring:message code="file_size"/></td>
    		<td  class="bzh">上传用户</td>
    		<td  class="bzh"><spring:message code="import_date"/></td>
    		<td  class="bzh"><spring:message code="file_desc1"/></td>     		 
    	    <td  class="bzh"><spring:message code='Operation'/></td>
    	</tr>
<%--     	<c:forEach var="list"  items="${list}" varStatus="vstatus">
    	<tr>
    		<td> ${list.sourceFileName} </td> 
    		<td>${list.fileSizes}</td>
    		<td>${list.importDate}</td>
    		<td>${list.fileDesc}</td> 	
    		<td>
    		<a onclick="showMenuPage3('${ctx}/downloadFile.do?fileId=${list.fileId}')"><spring:message code="view_upload"/></a>&nbsp;&nbsp;&nbsp;&nbsp; 
    		</td>
    	</tr>
    	</c:forEach>  --%>
    </table>
</div>
    
    
  </div>  
  	
      <div id="w2" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="display:none;">
		<div class="wz_tp"></div>
		<div class="wz_wx"><spring:message code='Confirm_sure_to_delete'/></div>
		<div class="wz_db" >
		 <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='confirm'/>" onclick="$('#w2').window('close');fileDelete()"/>&nbsp;	
		  <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='Cancel'/>" onclick="$('#w2').window('close')"/>&nbsp;
		</div>
	</div>  
 </c:if>
  <!-- 上传通用组件 -->
