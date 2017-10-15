<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <style>
.progress-bar {
    float: left;
    width: 0px;
    height: 100%;
    font-size: 12px;
    line-height: 20px;
    color: #FFF;
    text-align: center;
    background-color: #428BCA;
    box-shadow: 0px -1px 0px rgba(0, 0, 0, 0.15) inset;
    transition: width 0.6s ease 0s;
    }
.progress-bar-success {
    background-color: #5CB85C;
} 
.datagrid-header-inner{
  float: left;
  width: 0px; 
}    
</style> 
<!--  <link href="http://cdn.bootcss.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/bootstrap/3.1.1/js/bootstrap.min.js"></script> -->
<%-- <link href="${ctx}/static/stream/css/stream-v1.css" rel="stylesheet" type="text/css"> --%>
<%-- <script type="text/javascript" src="${ctx}/static/stream/js/stream-v1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ajax-upload.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/static/stream/js/stream-v1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ajax-upload.js"></script> --%>
<script language="javascript" type="text/javascript">
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;s
var config1 = null;
var _t1 =null;
var config2 = null;
var _t2 =null;
var config3 = null;
var _t3 =null;
var functionId1 ='${functionId1}';
var functionId2 ='${functionId2}';
var functionId3 ='${functionId3}';
var project = '${ctx}';
if(functionId1==''){
	 functionId1 ='0'; 
}
if(functionId2==''){
	 functionId2 ='0'; 
}
if(functionId3==''){
	 functionId3 ='0'; 
}
$("#functionId1").val(functionId1);
$("#functionId2").val(functionId2);
$("#functionId3").val(functionId3);
var businessId1 = '${businessId1}';
var businessId2 = '${businessId2}'; 
var businessId3 = '${businessId3}'; 
var extensions = "";
window.onload = function(){
	$.ajax({
		type : 'POST',
		async: false,
		url : "${ctx}/getNoAuthExtension.do",
		success : function(data) {
			extensions = data;          
		}
	});	
	if ((Sys.firefox && Sys.firefox<4.0)  || (Sys.safari && Sys.safari<6.0)||(Sys.ie && Sys.ie<10.0)){	

	 new AjaxUpload("#importFile1",{
			action : '${ctx}/importEasyFile.do?', //提交的url
			autoSubmit : true, //不自动提交
			responseType : "json", //服务器返回的数据格式
			name : 'file', //提交到后台的文件参数名称
			data:{functionId:functionId1,
			      project:project	
			},
			onSubmit : function(file, extension) { // 文件选择后使用,file--上传文件名, extension--文件扩展名
				var status = true;
				var exts = extensions.split(',');
				for(var i=0;i<exts.length;i++){
					if(extension==exts[i]){
						status = false;
						break;
				}
				}
				if(status ==true){
					
					   parent.parent.$.messager.progress({
							title : '<spring:message code="toolTip"/>',
							text : '<spring:message code="content"/>....'
						});	
					   return true;
				}else{
					$("#infoMessageText").html("文件类型为可执行文件或非法文件,不符合上传规则<br>");
					$('#InfoMessage').window('open');
					return false;	
				}
				
			//验证文件后缀名
			/* extension = extension.toLowerCase(); */
			/* if (extension&& /^(csv)$/.test(extension)) {
			
				return true;
			} else {
				return false;
			} */
			//后缀名的验证（未做）

			},onComplete : function(file, data) {
				parent.parent.$.messager.progress('close');
				if(data){
					if(data.importStatus ==true){
						$('#contentTable_import_file1').datagrid('appendRow',{'fileId':data.fileId,
							'sourceFileName':data.sourceFileName,'fileSizes':data.fileSizes,'personName':data.personName,
							'importDate':data.importDate,'fileDesc':data.fileDesc,'fileType':data.fileType,"isPerview":data.isPerview});
							var fileIds = $("#fileIds1").val();
							fileIds = fileIds+data.fileId+",";
							$("#fileIds1").val(fileIds);
							$("#infoMessageText").html("<spring:message code='import_success'/>");
							$('#InfoMessage').window('open');	
					}else{
						$("#infoMessageText").html("<spring:message code='import_failure_size'/>");
						$('#InfoMessage').window('open');	
					}
					
				}else{
					$("#infoMessageText").html("<spring:message code='import_failure'/>");
					$('#InfoMessage').window('open');
				}
				
			} 
		});	
	 new AjaxUpload("#importFile2",{
			action : '${ctx}/importEasyFile.do?', //提交的url
			autoSubmit : true, //不自动提交
			responseType : "json", //服务器返回的数据格式
			name : 'file', //提交到后台的文件参数名称
			data:{functionId:functionId2,
			      project:project	
			},
			onSubmit : function(file, extension) { // 文件选择后使用,file--上传文件名, extension--文件扩展名
				var status = true;
				var exts = extensions.split(',');
				for(var i=0;i<exts.length;i++){
					if(extension==exts[i]){
						status = false;
						break;
				}
				}
				if(status ==true){
					
					   parent.parent.$.messager.progress({
							title : '<spring:message code="toolTip"/>',
							text : '<spring:message code="content"/>....'
						});	
					   return true;
				}else{
					$("#infoMessageText").html("文件类型为可执行文件或非法文件,不符合上传规则<br>");
					$('#InfoMessage').window('open');
					return false;	
				}
				
			//验证文件后缀名
			/* extension = extension.toLowerCase(); */
			/* if (extension&& /^(csv)$/.test(extension)) {
			
				return true;
			} else {
				return false;
			} */
			//后缀名的验证（未做）

			},onComplete : function(file, data) {
				parent.parent.$.messager.progress('close');
				if(data){
					if(data.importStatus ==true){
						$('#contentTable_import_file2').datagrid('appendRow',{'fileId':data.fileId,
							'sourceFileName':data.sourceFileName,'fileSizes':data.fileSizes,'personName':data.personName,
							'importDate':data.importDate,'fileDesc':data.fileDesc,'fileType':data.fileType,"isPerview":data.isPerview});
							var fileIds = $("#fileIds2").val();
							fileIds = fileIds+data.fileId+",";
							$("#fileIds2").val(fileIds);
							$("#infoMessageText").html("<spring:message code='import_success'/>");
							$('#InfoMessage').window('open');	
					}else{
						$("#infoMessageText").html("<spring:message code='import_failure_size'/>");
						$('#InfoMessage').window('open');	
					}
					
				}else{
					$("#infoMessageText").html("<spring:message code='import_failure'/>");
					$('#InfoMessage').window('open');
				}
				
			} 
		});	
	 new AjaxUpload("#importFile3",{
			action : '${ctx}/importEasyFile.do?', //提交的url
			autoSubmit : true, //不自动提交
			responseType : "json", //服务器返回的数据格式
			name : 'file', //提交到后台的文件参数名称
			data:{functionId:functionId3,
			      project:project	
			},
			onSubmit : function(file, extension) { // 文件选择后使用,file--上传文件名, extension--文件扩展名
				var status = true;
				var exts = extensions.split(',');
				for(var i=0;i<exts.length;i++){
					if(extension==exts[i]){
						status = false;
						break;
				}
				}
				if(status ==true){
					
					   parent.parent.$.messager.progress({
							title : '<spring:message code="toolTip"/>',
							text : '<spring:message code="content"/>....'
						});	
					   return true;
				}else{
					$("#infoMessageText").html("文件类型为可执行文件或非法文件,不符合上传规则<br>");
					$('#InfoMessage').window('open');
					return false;	
				}
				
			//验证文件后缀名
			/* extension = extension.toLowerCase(); */
			/* if (extension&& /^(csv)$/.test(extension)) {
			
				return true;
			} else {
				return false;
			} */
			//后缀名的验证（未做）

			},onComplete : function(file, data) {
				parent.parent.$.messager.progress('close');
				if(data){
					if(data.importStatus ==true){
						$('#contentTable_import_file3').datagrid('appendRow',{'fileId':data.fileId,
							'sourceFileName':data.sourceFileName,'fileSizes':data.fileSizes,'personName':data.personName,
							'importDate':data.importDate,'fileDesc':data.fileDesc,'fileType':data.fileType,"isPerview":data.isPerview});
							var fileIds = $("#fileIds3").val();
							fileIds = fileIds+data.fileId+",";
							$("#fileIds3").val(fileIds);
							$("#infoMessageText").html("<spring:message code='import_success'/>");
							$('#InfoMessage').window('open');	
					}else{
						$("#infoMessageText").html("<spring:message code='import_failure_size'/>");
						$('#InfoMessage').window('open');	
					}
					
				}else{
					$("#infoMessageText").html("<spring:message code='import_failure'/>");
					$('#InfoMessage').window('open');
				}
				
			} 
		});	 
	 
	 
	 
}else{
	
		config1 = {
				enabled: true, /** 是否启用文件选择，默认是true */
				browseFileId : "i_select_files1", /** 文件选择的Dom Id，如果不指定，默认是i_select_files */
				browseFileBtn : "<div>请选择文件</div>", /** 选择文件的按钮内容，非自定义UI有效(customered:false) */
				dragAndDropArea: "i_stream_dropzone1", /** 拖拽上传区域，Id（字符类型"i_select_files"）或者DOM对象, 默认: `i_select_files` */
				customered: true,
				multipleFiles: true, /** 是否允许同时选择多个文件，默认是false */	
				autoRemoveCompleted: false, /** 是否自动移除已经上传完毕的文件，非自定义UI有效(customered:false)，默认是false */
				autoUploading: true, /** 当选择完文件是否自动上传，默认是true */
				fileFieldName: "FileData1", /** 相当于指定<input type="file" name="FileData">，默认是FileData */
				maxSize: 104857600, /** 当_t.bStreaming = false 时（也就是Flash上传时），2G就是最大的文件上传大小！所以一般需要 */
				simLimit: 100, /** 允许同时选择文件上传的个数（包含已经上传过的） */
				extFilters: [".txt", ".gz",".aac",".ace",".ai",".ain",".amr",".app",".arj",".asf",
				             ".asp",".aspx",".jsp",".av",".avi",".bin",".bmp",".cab",".cad",".cat",
				             ".cdr",".chm",".com",".css",".js",".cur",".dat",".db",".dmv",".doc",
				             ".docx",".dot",".dps",".dpt",".dwg",".dxf",".emf",".eps",".et",".ett",".fla",
				             ".ftp",".gif",".hlp",".icl",".ico",".img",".inf",".ini",".iso",".m3u",".max",
				             ".mdb",".mde",".mht",".mid",".midi",".mov",".mp3",".mp4",".mpeg",".mpg",".msi",
				             ".nrg",".ocx",".ogg",".ogm",".pdf",".png",".pot",".ppt",".pptx",".psd",".pub",
				             ".qt",".ra",".ram",".rar",".rm",".rmvb",".rtf",".swf",".tar",".tif",".tiff",
				             ".unknown",".url",".vbs",".vsd",".vss",".vst",".wav",".wave",".wm",".wma",".wmd",
				             ".wmv",".wps",".wpt",".xls",".xlsx",".xlt",".xml",".zip",
				             ".jpg", ".png", ".jpeg", ".gif", ".html", ".htm"], /** 默认是全部允许，即 [] */
				filesQueueId : "i_stream_files_queue", /** 文件上传进度显示框ID，非自定义UI有效(customered:false) */
				filesQueueHeight : 450, /** 文件上传进度显示框的高，非自定义UI有效(customered:false)，默认450px */
				messagerId : "i_stream_message_container", /** 消息框的Id，当没有自定义onXXX函数，系统会显示onXXX的部分提示信息，如果没有i_stream_message_container则不显示 */
//				frmUploadURL : "http://customers.duapp.com/fd;", /** Flash上传的URI */
//		      uploadURL : "http://customers.duapp.com/upload",
	           postVarsPerFile:{functionId:functionId1,businessId:businessId1,project:project},
				onSelect: function(files) {
					//console && console.log("-------------onSelect-------------------");
					//console && console.log(files);
					//console && console.log("-------------onSelect-------------------End");
					/*  var status = true;
						var exts = extension.split(',');
						for(var i=0;i<exts.length;i++){
						for(var j=0;j<files.length;j++)	
							var b=test(files[j].name);
							if(b[0]=="." + exts[i]){
								status = false;
								break;
							}
						}
						if(status == false){
							$("#infoMessageText").html("文件类型存在可执行文件或非法文件,不符合上传规则<br>");
							$('#InfoMessage').window('open');
							_t.cancel();
						} */
						
				},
				onMaxSizeExceed: function(file) {
					//console && console.log("-------------onMaxSizeExceed-------------------");
					//console && console.log(file);
					//$("#i_error_tips > span.text-message").append("文件[name="+file.name+", size="+file.formatSize+"]超过文件大小限制‵"+file.formatLimitSize+"‵，将不会被上传！<br>");
					//console && console.log("-------------onMaxSizeExceed-------------------End");
					$("#infoMessageText").html("文件超过大小限制"+file.formatLimitSize+"，不会被上传！<br>");
					$('#InfoMessage').window('open');
				},
				onFileCountExceed : function(selected, limit) {
					//console && console.log("-------------onFileCountExceed-------------------");
					//console && console.log(selected + "," + limit);
					//$("#i_error_tips > span.text-message").append("同时最多上传<strong>"+limit+"</strong>个文件，但是已选择<strong>"+selected+"</strong>个<br>");
					//console && console.log("-------------onFileCountExceed-------------------End");
					$("#infoMessageText").html("同时最多上传<strong>"+limit+"</strong>个文件，但是已选择<strong>"+selected+"</strong>个<br>");
					$('#InfoMessage').window('open');
				},
				onExtNameMismatch: function(info) {
					//console && console.log("-------------onExtNameMismatch-------------------");
					//console && console.log(info);
					//$("#i_error_tips > span.text-message").append("<strong>"+info.name+"</strong>文件类型不匹配[<strong>"+info.filters.toString() + "</strong>]<br>");
					//console && console.log("-------------onExtNameMismatch-------------------End");		
					$("#infoMessageText").html("文件类型为可执行文件或非法文件,不符合上传规则<br>");
					$('#InfoMessage').window('open');
				},
				onAddTask: function(file) {

						 var file = '<tr id="' + file.id + '" class="template-upload fade in">' +
					     '<td><p class="name">' + file.name + '</p>' +
					     '    <div><span class="label label-info">进度：</span> <span class="message-text"></span></div>' +
					     '    <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">' +
						'			<div class="progress-bar progress-bar-success" title="" style="width: 0%;"></div>' +
						'		</div>' +
					     '</td>' +
					     '<td><p class="size">' + file.formatSize + '</p>' +
					     '</td>' +
					     '<td><button  class="close" onClick="javascript:_t1.cancelOne(\'' + file.id + '\')">x</button>' +
					     '</td></tr>';
						
						$("#bootstrap-stream-container").append(file);	
				},
				onUploadProgress: function(file) {
					//console && console.log("-------------onUploadProgress-------------------");
					//console && console.log(file);
					if(file.formatSize !='0B'){
						var $bar = $("#"+file.id).find("div.progress-bar");
						$bar.css("width", file.percent + "%");
						var $message = $("#"+file.id).find("span.message-text");
						$message.text("已上传:" + file.formatLoaded + "/" + file.formatSize + "(" + file.percent + "%" + ") 速  度:" + file.formatSpeed);	
					}else{
						
					}
					/* var $total = $("#stream_total_progress_bar");
					$total.find("div.progress-bar").css("width", file.totalPercent + "%");
					$total.find("span.stream_total_size").html(file.formatTotalLoaded + "/" + file.formatTotalSize);
					$total.find("span.stream_total_percent").html(file.totalPercent + "%"); */
					
					//console && console.log("-------------onUploadProgress-------------------End");
				},
				onStop: function() {
					//console && console.log("-------------onStop-------------------");
					//console && console.log("系统已停止上传！！！");
					//console && console.log("-------------onStop-------------------End");
				},
				onCancel: function(file) {
					//console && console.log("-------------onCancel-------------------");
					//console && console.log(file);
					
					$("#"+file.id).remove();
					
					var $total = $("#stream_total_progress_bar");
					$total.find("div.progress-bar").css("width", file.totalPercent + "%");
					$total.find("span.stream_total_size").text(file.formatTotalLoaded + "/" + file.formatTotalSize);
					$total.find("span.stream_total_percent").text(file.totalPercent + "%");
					//console && console.log("-------------onCancel-------------------End");
				},
				onCancelAll: function(numbers) {
					//console && console.log("-------------onCancelAll-------------------");
					//console && console.log(numbers + " 个文件已被取消上传！！！");
					$("#i_error_tips > span.text-message").append(numbers + " 个文件已被取消上传！！！");
					
					//console && console.log("-------------onCancelAll-------------------End");
				},
				onComplete: function(file) {
					//console && console.log("-------------onComplete-------------------");
					//console && console.log(file);
					
					/** 100% percent */
					var $bar = $("#"+file.id).find("div.progress-bar");
					$bar.css("width", file.percent + "%");
					var $message = $("#"+file.id).find("span.message-text");
					$message.text("已上传:" + file.formatLoaded + "/" + file.formatSize + "(" + file.percent + "%" + ")");
					/** remove the `cancel` button */
					var $cancelBtn = $("#"+file.id).find("td:last > span");
					$cancelBtn.remove();
					setTimeout("closeAd1("+"\'"+file.id+"\'"+")",2000);	
					var data =eval("("+file.msg+")");   
	               if(data.status =="true"){	
						$('#contentTable_import_file1').datagrid('appendRow',{'fileId':data.fileId,
						'sourceFileName':data.sourceFileName,'fileSizes':data.fileSizes,'personName':data.personName,
						'importDate':data.importDate,'fileDesc':data.fileDesc,'fileType':data.fileType,"isPerview":data.isPerview});
						var fileIds = $("#fileIds1").val();
						fileIds = fileIds+data.fileId+",";
						$("#fileIds1").val(fileIds);
					}else if(data.status ==undefined){
						$("#infoMessageText").html("<spring:message code='import_failure_size'/>");
						$('#InfoMessage').window('open');	
					}else{
						$("#infoMessageText").html("<spring:message code='import_failure'/>");
						$('#InfoMessage').window('open');
					}
					
					
					/** modify the total progress bar */
					/* var $total = $("#stream_total_progress_bar");
					$total.find("div.progress-bar").css("width", file.totalPercent + "%");
					$total.find("span.stream_total_size").text(file.formatTotalLoaded + "/" + file.formatTotalSize);
					$total.find("span.stream_total_percent").text(file.totalPercent + "%"); */
					
					//console && console.log("-------------onComplete-------------------End");
				},
				onQueueComplete: function(msg) {
					//console && console.log("-------------onQueueComplete-------------------");
					//console && console.log(msg);
					//console && console.log("-------------onQueueComplete-------------------End");
				},
				onUploadError: function(status, msg) {
					//console && console.log("-------------onUploadError-------------------");
					//console && console.log(msg + ", 状态码:" + status);
					
					$("#i_error_tips > span.text-message").append(msg + ", 状态码:" + status);
					
					//console && console.log("-------------onUploadError-------------------End");
				}
			};
			 _t1 = new Stream(config1);
			/** 不支持拖拽，隐藏拖拽框 */
			if (!_t1.bDraggable) {
				$("#i_stream_dropzone1").hide();
			}
			/** Flash最大支持2G */
			if (!_t1.bStreaming) {
				_t1.config.maxSize = 104857600;
			}
			
			
			config2 = {
					enabled: true, /** 是否启用文件选择，默认是true */
					browseFileId : "i_select_files2", /** 文件选择的Dom Id，如果不指定，默认是i_select_files */
					browseFileBtn : "<div>请选择文件</div>", /** 选择文件的按钮内容，非自定义UI有效(customered:false) */
					dragAndDropArea: "i_stream_dropzone2", /** 拖拽上传区域，Id（字符类型"i_select_files"）或者DOM对象, 默认: `i_select_files` */
					customered: true,
					multipleFiles: true, /** 是否允许同时选择多个文件，默认是false */	
					autoRemoveCompleted: false, /** 是否自动移除已经上传完毕的文件，非自定义UI有效(customered:false)，默认是false */
					autoUploading: true, /** 当选择完文件是否自动上传，默认是true */
					fileFieldName: "FileData2", /** 相当于指定<input type="file" name="FileData">，默认是FileData */
					maxSize: 104857600, /** 当_t.bStreaming = false 时（也就是Flash上传时），2G就是最大的文件上传大小！所以一般需要 */
					simLimit: 100, /** 允许同时选择文件上传的个数（包含已经上传过的） */
					extFilters: [".txt", ".gz",".aac",".ace",".ai",".ain",".amr",".app",".arj",".asf",
					             ".asp",".aspx",".jsp",".av",".avi",".bin",".bmp",".cab",".cad",".cat",
					             ".cdr",".chm",".com",".css",".js",".cur",".dat",".db",".dmv",".doc",
					             ".docx",".dot",".dps",".dpt",".dwg",".dxf",".emf",".eps",".et",".ett",".fla",
					             ".ftp",".gif",".hlp",".icl",".ico",".img",".inf",".ini",".iso",".m3u",".max",
					             ".mdb",".mde",".mht",".mid",".midi",".mov",".mp3",".mp4",".mpeg",".mpg",".msi",
					             ".nrg",".ocx",".ogg",".ogm",".pdf",".png",".pot",".ppt",".pptx",".psd",".pub",
					             ".qt",".ra",".ram",".rar",".rm",".rmvb",".rtf",".swf",".tar",".tif",".tiff",
					             ".unknown",".url",".vbs",".vsd",".vss",".vst",".wav",".wave",".wm",".wma",".wmd",
					             ".wmv",".wps",".wpt",".xls",".xlsx",".xlt",".xml",".zip",
					             ".jpg", ".png", ".jpeg", ".gif", ".html", ".htm", ".eml"], /** 默认是全部允许，即 [] */
					filesQueueId : "i_stream_files_queue", /** 文件上传进度显示框ID，非自定义UI有效(customered:false) */
					filesQueueHeight : 450, /** 文件上传进度显示框的高，非自定义UI有效(customered:false)，默认450px */
					messagerId : "i_stream_message_container", /** 消息框的Id，当没有自定义onXXX函数，系统会显示onXXX的部分提示信息，如果没有i_stream_message_container则不显示 */
//					frmUploadURL : "http://customers.duapp.com/fd;", /** Flash上传的URI */
//			      uploadURL : "http://customers.duapp.com/upload",
		           postVarsPerFile:{functionId:functionId2,businessId:businessId2,project:project},
					onSelect: function(files) {
						//console && console.log("-------------onSelect-------------------");
						//console && console.log(files);
						//console && console.log("-------------onSelect-------------------End");
						/*  var status = true;
							var exts = extension.split(',');
							for(var i=0;i<exts.length;i++){
							for(var j=0;j<files.length;j++)	
								var b=test(files[j].name);
								if(b[0]=="." + exts[i]){
									status = false;
									break;
								}
							}
							if(status == false){
								$("#infoMessageText").html("文件类型存在可执行文件或非法文件,不符合上传规则<br>");
								$('#InfoMessage').window('open');
								_t.cancel();
							} */
							
					},
					onMaxSizeExceed: function(file) {
						//console && console.log("-------------onMaxSizeExceed-------------------");
						//console && console.log(file);
						//$("#i_error_tips > span.text-message").append("文件[name="+file.name+", size="+file.formatSize+"]超过文件大小限制‵"+file.formatLimitSize+"‵，将不会被上传！<br>");
						//console && console.log("-------------onMaxSizeExceed-------------------End");
						$("#infoMessageText").html("文件超过大小限制"+file.formatLimitSize+"，不会被上传！<br>");
						$('#InfoMessage').window('open');
					},
					onFileCountExceed : function(selected, limit) {
						//console && console.log("-------------onFileCountExceed-------------------");
						//console && console.log(selected + "," + limit);
						//$("#i_error_tips > span.text-message").append("同时最多上传<strong>"+limit+"</strong>个文件，但是已选择<strong>"+selected+"</strong>个<br>");
						//console && console.log("-------------onFileCountExceed-------------------End");
						$("#infoMessageText").html("同时最多上传<strong>"+limit+"</strong>个文件，但是已选择<strong>"+selected+"</strong>个<br>");
						$('#InfoMessage').window('open');
					},
					onExtNameMismatch: function(info) {
						//console && console.log("-------------onExtNameMismatch-------------------");
						//console && console.log(info);
						//$("#i_error_tips > span.text-message").append("<strong>"+info.name+"</strong>文件类型不匹配[<strong>"+info.filters.toString() + "</strong>]<br>");
						//console && console.log("-------------onExtNameMismatch-------------------End");		
						$("#infoMessageText").html("文件类型为可执行文件或非法文件,不符合上传规则<br>");
						$('#InfoMessage').window('open');
					},
					onAddTask: function(file) {

							 var file = '<tr id="' + file.id + '" class="template-upload fade in">' +
						     '<td><p class="name">' + file.name + '</p>' +
						     '    <div><span class="label label-info">进度：</span> <span class="message-text"></span></div>' +
						     '    <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">' +
							'			<div class="progress-bar progress-bar-success" title="" style="width: 0%;"></div>' +
							'		</div>' +
						     '</td>' +
						     '<td><p class="size">' + file.formatSize + '</p>' +
						     '</td>' +
						     '<td><button  class="close" onClick="javascript:_t2.cancelOne(\'' + file.id + '\')">x</button>' +
						     '</td></tr>';
							
							$("#bootstrap-stream-container").append(file);	
					},
					onUploadProgress: function(file) {
						//console && console.log("-------------onUploadProgress-------------------");
						//console && console.log(file);
						if(file.formatSize !='0B'){
							var $bar = $("#"+file.id).find("div.progress-bar");
							$bar.css("width", file.percent + "%");
							var $message = $("#"+file.id).find("span.message-text");
							$message.text("已上传:" + file.formatLoaded + "/" + file.formatSize + "(" + file.percent + "%" + ") 速  度:" + file.formatSpeed);	
						}else{
							
						}
						/* var $total = $("#stream_total_progress_bar");
						$total.find("div.progress-bar").css("width", file.totalPercent + "%");
						$total.find("span.stream_total_size").html(file.formatTotalLoaded + "/" + file.formatTotalSize);
						$total.find("span.stream_total_percent").html(file.totalPercent + "%"); */
						
						//console && console.log("-------------onUploadProgress-------------------End");
					},
					onStop: function() {
						//console && console.log("-------------onStop-------------------");
						//console && console.log("系统已停止上传！！！");
						//console && console.log("-------------onStop-------------------End");
					},
					onCancel: function(file) {
						//console && console.log("-------------onCancel-------------------");
						//console && console.log(file);
						
						$("#"+file.id).remove();
						
						var $total = $("#stream_total_progress_bar");
						$total.find("div.progress-bar").css("width", file.totalPercent + "%");
						$total.find("span.stream_total_size").text(file.formatTotalLoaded + "/" + file.formatTotalSize);
						$total.find("span.stream_total_percent").text(file.totalPercent + "%");
						//console && console.log("-------------onCancel-------------------End");
					},
					onCancelAll: function(numbers) {
						//console && console.log("-------------onCancelAll-------------------");
						//console && console.log(numbers + " 个文件已被取消上传！！！");
						$("#i_error_tips > span.text-message").append(numbers + " 个文件已被取消上传！！！");
						
						//console && console.log("-------------onCancelAll-------------------End");
					},
					onComplete: function(file) {
						//console && console.log("-------------onComplete-------------------");
						//console && console.log(file);
						
						/** 100% percent */
						var $bar = $("#"+file.id).find("div.progress-bar");
						$bar.css("width", file.percent + "%");
						var $message = $("#"+file.id).find("span.message-text");
						$message.text("已上传:" + file.formatLoaded + "/" + file.formatSize + "(" + file.percent + "%" + ")");
						/** remove the `cancel` button */
						var $cancelBtn = $("#"+file.id).find("td:last > span");
						$cancelBtn.remove();
						setTimeout("closeAd2("+"\'"+file.id+"\'"+")",2000);	
						var data =eval("("+file.msg+")");   
		               if(data.status =="true"){	
							$('#contentTable_import_file2').datagrid('appendRow',{'fileId':data.fileId,
							'sourceFileName':data.sourceFileName,'fileSizes':data.fileSizes,'personName':data.personName,
							'importDate':data.importDate,'fileDesc':data.fileDesc,'fileType':data.fileType,"isPerview":data.isPerview});
							var fileIds = $("#fileIds2").val();
							fileIds = fileIds+data.fileId+",";
							$("#fileIds2").val(fileIds);
						}else if(data.status ==undefined){
							$("#infoMessageText").html("<spring:message code='import_failure_size'/>");
							$('#InfoMessage').window('open');	
						}else{
							$("#infoMessageText").html("<spring:message code='import_failure'/>");
							$('#InfoMessage').window('open');
						}
						
						
						/** modify the total progress bar */
						/* var $total = $("#stream_total_progress_bar");
						$total.find("div.progress-bar").css("width", file.totalPercent + "%");
						$total.find("span.stream_total_size").text(file.formatTotalLoaded + "/" + file.formatTotalSize);
						$total.find("span.stream_total_percent").text(file.totalPercent + "%"); */
						
						//console && console.log("-------------onComplete-------------------End");
					},
					onQueueComplete: function(msg) {
						//console && console.log("-------------onQueueComplete-------------------");
						//console && console.log(msg);
						//console && console.log("-------------onQueueComplete-------------------End");
					},
					onUploadError: function(status, msg) {
						//console && console.log("-------------onUploadError-------------------");
						//console && console.log(msg + ", 状态码:" + status);
						
						$("#i_error_tips > span.text-message").append(msg + ", 状态码:" + status);
						
						//console && console.log("-------------onUploadError-------------------End");
					}
				};
				 _t2 = new Stream(config2);
				/** 不支持拖拽，隐藏拖拽框 */
				if (!_t2.bDraggable) {
					$("#i_stream_dropzone2").hide();
				}
				/** Flash最大支持2G */
				if (!_t2.bStreaming) {
					_t2.config.maxSize = 104857600;
				}
			
				config3 = {
						enabled: true, /** 是否启用文件选择，默认是true */
						browseFileId : "i_select_files3", /** 文件选择的Dom Id，如果不指定，默认是i_select_files */
						browseFileBtn : "<div>请选择文件</div>", /** 选择文件的按钮内容，非自定义UI有效(customered:false) */
						dragAndDropArea: "i_stream_dropzone3", /** 拖拽上传区域，Id（字符类型"i_select_files"）或者DOM对象, 默认: `i_select_files` */
						customered: true,
						multipleFiles: true, /** 是否允许同时选择多个文件，默认是false */	
						autoRemoveCompleted: false, /** 是否自动移除已经上传完毕的文件，非自定义UI有效(customered:false)，默认是false */
						autoUploading: true, /** 当选择完文件是否自动上传，默认是true */
						fileFieldName: "FileData3", /** 相当于指定<input type="file" name="FileData">，默认是FileData */
						maxSize: 104857600, /** 当_t.bStreaming = false 时（也就是Flash上传时），2G就是最大的文件上传大小！所以一般需要 */
						simLimit: 100, /** 允许同时选择文件上传的个数（包含已经上传过的） */
						extFilters: [".txt", ".gz",".aac",".ace",".ai",".ain",".amr",".app",".arj",".asf",
						             ".asp",".aspx",".jsp",".av",".avi",".bin",".bmp",".cab",".cad",".cat",
						             ".cdr",".chm",".com",".css",".js",".cur",".dat",".db",".dmv",".doc",
						             ".docx",".dot",".dps",".dpt",".dwg",".dxf",".emf",".eps",".et",".ett",".fla",
						             ".ftp",".gif",".hlp",".icl",".ico",".img",".inf",".ini",".iso",".m3u",".max",
						             ".mdb",".mde",".mht",".mid",".midi",".mov",".mp3",".mp4",".mpeg",".mpg",".msi",
						             ".nrg",".ocx",".ogg",".ogm",".pdf",".png",".pot",".ppt",".pptx",".psd",".pub",
						             ".qt",".ra",".ram",".rar",".rm",".rmvb",".rtf",".swf",".tar",".tif",".tiff",
						             ".unknown",".url",".vbs",".vsd",".vss",".vst",".wav",".wave",".wm",".wma",".wmd",
						             ".wmv",".wps",".wpt",".xls",".xlsx",".xlt",".xml",".zip",
						             ".jpg", ".png", ".jpeg", ".gif", ".html", ".htm"], /** 默认是全部允许，即 [] */
						filesQueueId : "i_stream_files_queue", /** 文件上传进度显示框ID，非自定义UI有效(customered:false) */
						filesQueueHeight : 450, /** 文件上传进度显示框的高，非自定义UI有效(customered:false)，默认450px */
						messagerId : "i_stream_message_container", /** 消息框的Id，当没有自定义onXXX函数，系统会显示onXXX的部分提示信息，如果没有i_stream_message_container则不显示 */
//						frmUploadURL : "http://customers.duapp.com/fd;", /** Flash上传的URI */
//				      uploadURL : "http://customers.duapp.com/upload",
			           postVarsPerFile:{functionId:functionId3,businessId:businessId3,project:project},
						onSelect: function(files) {
							//console && console.log("-------------onSelect-------------------");
							//console && console.log(files);
							//console && console.log("-------------onSelect-------------------End");
							/*  var status = true;
								var exts = extension.split(',');
								for(var i=0;i<exts.length;i++){
								for(var j=0;j<files.length;j++)	
									var b=test(files[j].name);
									if(b[0]=="." + exts[i]){
										status = false;
										break;
									}
								}
								if(status == false){
									$("#infoMessageText").html("文件类型存在可执行文件或非法文件,不符合上传规则<br>");
									$('#InfoMessage').window('open');
									_t.cancel();
								} */
								
						},
						onMaxSizeExceed: function(file) {
							//console && console.log("-------------onMaxSizeExceed-------------------");
							//console && console.log(file);
							//$("#i_error_tips > span.text-message").append("文件[name="+file.name+", size="+file.formatSize+"]超过文件大小限制‵"+file.formatLimitSize+"‵，将不会被上传！<br>");
							//console && console.log("-------------onMaxSizeExceed-------------------End");
							$("#infoMessageText").html("文件超过大小限制"+file.formatLimitSize+"，不会被上传！<br>");
							$('#InfoMessage').window('open');
						},
						onFileCountExceed : function(selected, limit) {
							//console && console.log("-------------onFileCountExceed-------------------");
							//console && console.log(selected + "," + limit);
							//$("#i_error_tips > span.text-message").append("同时最多上传<strong>"+limit+"</strong>个文件，但是已选择<strong>"+selected+"</strong>个<br>");
							//console && console.log("-------------onFileCountExceed-------------------End");
							$("#infoMessageText").html("同时最多上传<strong>"+limit+"</strong>个文件，但是已选择<strong>"+selected+"</strong>个<br>");
							$('#InfoMessage').window('open');
						},
						onExtNameMismatch: function(info) {
							//console && console.log("-------------onExtNameMismatch-------------------");
							//console && console.log(info);
							//$("#i_error_tips > span.text-message").append("<strong>"+info.name+"</strong>文件类型不匹配[<strong>"+info.filters.toString() + "</strong>]<br>");
							//console && console.log("-------------onExtNameMismatch-------------------End");		
							$("#infoMessageText").html("文件类型为可执行文件或非法文件,不符合上传规则<br>");
							$('#InfoMessage').window('open');
						},
						onAddTask: function(file) {

								 var file = '<tr id="' + file.id + '" class="template-upload fade in">' +
							     '<td><p class="name">' + file.name + '</p>' +
							     '    <div><span class="label label-info">进度：</span> <span class="message-text"></span></div>' +
							     '    <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">' +
								'			<div class="progress-bar progress-bar-success" title="" style="width: 0%;"></div>' +
								'		</div>' +
							     '</td>' +
							     '<td><p class="size">' + file.formatSize + '</p>' +
							     '</td>' +
							     '<td><button  class="close" onClick="javascript:_t3.cancelOne(\'' + file.id + '\')">x</button>' +
							     '</td></tr>';
								
								$("#bootstrap-stream-container").append(file);	
						},
						onUploadProgress: function(file) {
							//console && console.log("-------------onUploadProgress-------------------");
							//console && console.log(file);
							if(file.formatSize !='0B'){
								var $bar = $("#"+file.id).find("div.progress-bar");
								$bar.css("width", file.percent + "%");
								var $message = $("#"+file.id).find("span.message-text");
								$message.text("已上传:" + file.formatLoaded + "/" + file.formatSize + "(" + file.percent + "%" + ") 速  度:" + file.formatSpeed);	
							}else{
								
							}
							/* var $total = $("#stream_total_progress_bar");
							$total.find("div.progress-bar").css("width", file.totalPercent + "%");
							$total.find("span.stream_total_size").html(file.formatTotalLoaded + "/" + file.formatTotalSize);
							$total.find("span.stream_total_percent").html(file.totalPercent + "%"); */
							
							//console && console.log("-------------onUploadProgress-------------------End");
						},
						onStop: function() {
							//console && console.log("-------------onStop-------------------");
							//console && console.log("系统已停止上传！！！");
							//console && console.log("-------------onStop-------------------End");
						},
						onCancel: function(file) {
							//console && console.log("-------------onCancel-------------------");
							//console && console.log(file);
							
							$("#"+file.id).remove();
							
							var $total = $("#stream_total_progress_bar");
							$total.find("div.progress-bar").css("width", file.totalPercent + "%");
							$total.find("span.stream_total_size").text(file.formatTotalLoaded + "/" + file.formatTotalSize);
							$total.find("span.stream_total_percent").text(file.totalPercent + "%");
							//console && console.log("-------------onCancel-------------------End");
						},
						onCancelAll: function(numbers) {
							//console && console.log("-------------onCancelAll-------------------");
							//console && console.log(numbers + " 个文件已被取消上传！！！");
							$("#i_error_tips > span.text-message").append(numbers + " 个文件已被取消上传！！！");
							
							//console && console.log("-------------onCancelAll-------------------End");
						},
						onComplete: function(file) {
							//console && console.log("-------------onComplete-------------------");
							//console && console.log(file);
							
							/** 100% percent */
							var $bar = $("#"+file.id).find("div.progress-bar");
							$bar.css("width", file.percent + "%");
							var $message = $("#"+file.id).find("span.message-text");
							$message.text("已上传:" + file.formatLoaded + "/" + file.formatSize + "(" + file.percent + "%" + ")");
							/** remove the `cancel` button */
							var $cancelBtn = $("#"+file.id).find("td:last > span");
							$cancelBtn.remove();
							setTimeout("closeAd3("+"\'"+file.id+"\'"+")",2000);	
							var data =eval("("+file.msg+")");   
			               if(data.status =="true"){	
								$('#contentTable_import_file3').datagrid('appendRow',{'fileId':data.fileId,
								'sourceFileName':data.sourceFileName,'fileSizes':data.fileSizes,'personName':data.personName,
								'importDate':data.importDate,'fileDesc':data.fileDesc,'fileType':data.fileType,"isPerview":data.isPerview});
								var fileIds = $("#fileIds3").val();
								fileIds = fileIds+data.fileId+",";
								$("#fileIds3").val(fileIds);
							}else if(data.status ==undefined){
								$("#infoMessageText").html("<spring:message code='import_failure_size'/>");
								$('#InfoMessage').window('open');	
							}else{
								$("#infoMessageText").html("<spring:message code='import_failure'/>");
								$('#InfoMessage').window('open');
							}
							
							
							/** modify the total progress bar */
							/* var $total = $("#stream_total_progress_bar");
							$total.find("div.progress-bar").css("width", file.totalPercent + "%");
							$total.find("span.stream_total_size").text(file.formatTotalLoaded + "/" + file.formatTotalSize);
							$total.find("span.stream_total_percent").text(file.totalPercent + "%"); */
							
							//console && console.log("-------------onComplete-------------------End");
						},
						onQueueComplete: function(msg) {
							//console && console.log("-------------onQueueComplete-------------------");
							//console && console.log(msg);
							//console && console.log("-------------onQueueComplete-------------------End");
						},
						onUploadError: function(status, msg) {
							//console && console.log("-------------onUploadError-------------------");
							//console && console.log(msg + ", 状态码:" + status);
							
							$("#i_error_tips > span.text-message").append(msg + ", 状态码:" + status);
							
							//console && console.log("-------------onUploadError-------------------End");
						}
					};
					 _t3 = new Stream(config3);
					/** 不支持拖拽，隐藏拖拽框 */
					if (!_t3.bDraggable) {
						$("#i_stream_dropzone3").hide();
					}
					/** Flash最大支持2G */
					if (!_t3.bStreaming) {
						_t3.config.maxSize = 104857600;
					}
		
	}

}


$(document).ready(function() {	
	//以下进行测试
	if ((Sys.firefox && Sys.firefox<4.0)  || (Sys.safari && Sys.safari<6.0)||(Sys.ie && Sys.ie<10.0)){		
		$("#list2").show();
		$("#list4").show();
		$("#list6").show();
		$("#list1").hide();
		$("#list3").hide();
		$("#list5").hide();
		
	}else{
		$("#list1").show();
		$("#list3").show();
		$("#list5").show();
		$("#list2").hide();
		$("#list4").hide();
		$("#list6").hide();
	}
	
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
	 /* $.ajax({
			type : 'POST',
			url : "${ctx}/getDefultFilePath.do",
			success : function(data) {
                $("#savePath").val(data);           
			}
		}); */
     $.extend($.fn.datagrid.defaults.editors, {   
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
    	 $('#contentTable_import_file1').datagrid().datagrid('enableCellEditing');
    	 $('#contentTable_import_file2').datagrid().datagrid('enableCellEditing');
    	 $('#contentTable_import_file3').datagrid().datagrid('enableCellEditing');
    	 })
 
	 var url1='';
     var url2='';
     var url3='';
	 if(businessId1 !=''){
		 url1 = '${ctx}/getImportFile.do?functionId='+functionId1+'&businessId='+businessId1+'&project='+project;
	 }
	 if(businessId2 !=''){
		 url2 = '${ctx}/getImportFile.do?functionId='+functionId2+'&businessId='+businessId2+'&project='+project;
	 }
	 if(businessId3 !=''){
		 url3 = '${ctx}/getImportFile.do?functionId='+functionId3+'&businessId='+businessId3+'&project='+project;
	 }
	 $('#contentTable_import_file1').datagrid({
			url : url1,
			border : false,
			fitColumns : true,
			//fit:true,
			width:fixWidth(1.0),
			idField : 'fileId',
			nowrap : false,
			sortOrder : 'desc',
			nowrap : false,
			loadMsg:'',
			columns : [ [ 
			   /*  {
				      field : 'fileId',
					  title : '文件Id',
					  width : 150
				},  */
			    {
					field : 'sourceFileName',
					title : '<spring:message code="file_name"/>',
					width:fixWidth(0.4)
				}, {
					field : 'fileSizes',
					title : '<spring:message code="file_size"/>',
					width:fixWidth(0.2)
				}, {
					field : 'personName',
					title : '上传用户',
					width:fixWidth(0.2)
				} ,{
					field : 'importDate',
					title : '<spring:message code="import_date"/>',
					width:fixWidth(0.4)
				},{
					field : 'fileDesc',
					title : '<spring:message code="file_desc"/>',
					width:fixWidth(0.4),
					editor:'text'
				},
				{
					field : 'action',
					title : '<spring:message code="operate"/>',
					width:fixWidth(0.4),
					align:'center',
					formatter : function(value, row, index) {
						var str = '';
						if(row.isPerview ==true){
							if(row.fileType=="jpg" || row.fileType=="png" || row.fileType=="bmp" || row.fileType=="gif" ||row.fileType=="JPG" || row.fileType=="PNG" || row.fileType=="BMP" || row.fileType=="GIF" ){
								str+='<a href="${ctx}/waitPreviewImage.do?fileId='+row.fileId+'" target=_blank>预览</a>';
								str += '&nbsp;&nbsp;|&nbsp;'		
							}else{
								str+='<a href="${ctx}/waitPreviewFile.do?fileId='+row.fileId+'" target=_blank>预览</a>';
								str += '&nbsp;&nbsp;|&nbsp;'	
							}
						
						}
						
					    str+='<a href="${ctx}/downloadFile.do?fileId='+row.fileId+'"><spring:message code="view_upload"/></a>';						
						str += '&nbsp;&nbsp;|&nbsp;'
						str+='<a href="javascript:void(0)" onclick="deleteFile1('+row.fileId+')"><spring:message code="delete"/></a>';
						return str;
					}
				} 
			
			] ],
			onLoadSuccess : function() {
				//parent.$.messager.progress('close');
				//$(this).datagrid('selectRow', 0);
				$("#fileIds1").val('');
				var data =$('#contentTable_import_file1').datagrid('getData').rows;
				var fileIds = $("#fileIds1").val();
				for(var i =0;i<data.length;i++){
				  fileIds = fileIds+data[i].fileId+",";
				}
				$("#fileIds1").val(fileIds);
			}
		});
	   
	 $('#contentTable_import_file2').datagrid({
			url : url2,
			border : false,
			fitColumns : true,
			//fit:true,
			width:fixWidth(1.0),
			idField : 'fileId',
			nowrap : false,
			sortOrder : 'desc',
			nowrap : false,
			loadMsg:'',
			columns : [ [ 
			   /*  {
				      field : 'fileId',
					  title : '文件Id',
					  width : 150
				},  */
			    {
					field : 'sourceFileName',
					title : '<spring:message code="file_name"/>',
					width:fixWidth(0.4)
				}, {
					field : 'fileSizes',
					title : '<spring:message code="file_size"/>',
					width:fixWidth(0.2)
				},{
					field : 'personName',
					title : '上传用户',
					width:fixWidth(0.2)
				} ,{
					field : 'importDate',
					title : '<spring:message code="import_date"/>',
					width:fixWidth(0.4)
				},{
					field : 'fileDesc',
					title : '<spring:message code="file_desc"/>',
					width:fixWidth(0.4),
					editor:'text'
				},
				{
					field : 'action',
					title : '<spring:message code="operate"/>',
					width:fixWidth(0.4),
					align:'center',
					formatter : function(value, row, index) {
						var str = '';
						if(row.isPerview ==true){
							if(row.fileType=="jpg" || row.fileType=="png" || row.fileType=="bmp" || row.fileType=="gif" ){
								str+='<a href="${ctx}/waitPreviewImage.do?fileId='+row.fileId+'" target=_blank>预览</a>';
								str += '&nbsp;&nbsp;|&nbsp;'		
							}else{
								str+='<a href="${ctx}/waitPreviewFile.do?fileId='+row.fileId+'" target=_blank>预览</a>';
								str += '&nbsp;&nbsp;|&nbsp;'	
							}
						
						}
						
					    str+='<a href="${ctx}/downloadFile.do?fileId='+row.fileId+'"><spring:message code="view_upload"/></a>';						
						str += '&nbsp;&nbsp;|&nbsp;'
						str+='<a href="javascript:void(0)" onclick="deleteFile2('+row.fileId+')"><spring:message code="delete"/></a>';
						return str;
					}
				} 
			
			] ],
			onLoadSuccess : function() {
				//parent.$.messager.progress('close');
				//$(this).datagrid('selectRow', 0);
				$("#fileIds2").val('');
				var data =$('#contentTable_import_file2').datagrid('getData').rows;
				var fileIds = $("#fileIds2").val();
				for(var i =0;i<data.length;i++){
				  fileIds = fileIds+data[i].fileId+",";
				}
				$("#fileIds2").val(fileIds);
			}
		});
	 
	 $('#contentTable_import_file3').datagrid({
			url : url3,
			border : false,
			fitColumns : true,
			//fit:true,
			width:fixWidth(1.0),
			idField : 'fileId',
			nowrap : false,
			sortOrder : 'desc',
			nowrap : false,
			loadMsg:'',
			columns : [ [ 
			   /*  {
				      field : 'fileId',
					  title : '文件Id',
					  width : 150
				},  */
			    {
					field : 'sourceFileName',
					title : '<spring:message code="file_name"/>',
					width : fixWidth(0.4)
				}, {
					field : 'fileSizes',
					title : '<spring:message code="file_size"/>',
					width : fixWidth(0.2)
				},{
					field : 'personName',
					title : '上传用户',
					width : fixWidth(0.2)
				} ,{
					field : 'importDate',
					title : '<spring:message code="import_date"/>',
					width : fixWidth(0.4)
				},{
					field : 'fileDesc',
					title : '<spring:message code="file_desc"/>',
					width : fixWidth(0.4),
					editor:'text'
				},
				{
					field : 'action',
					title : '<spring:message code="operate"/>',
					width : fixWidth(0.4),
					align:'center',
					formatter : function(value, row, index) {
						var str = '';
						if(row.isPerview ==true){
							if(row.fileType=="jpg" || row.fileType=="png" || row.fileType=="bmp" || row.fileType=="gif" ){
								str+='<a href="${ctx}/waitPreviewImage.do?fileId='+row.fileId+'" target=_blank>预览</a>';
								str += '&nbsp;&nbsp;|&nbsp;'		
							}else{
								str+='<a href="${ctx}/waitPreviewFile.do?fileId='+row.fileId+'" target=_blank>预览</a>';
								str += '&nbsp;&nbsp;|&nbsp;'	
							}
						
						}
						
					    str+='<a href="${ctx}/downloadFile.do?fileId='+row.fileId+'"><spring:message code="view_upload"/></a>';						
						str += '&nbsp;&nbsp;|&nbsp;'
						str+='<a href="javascript:void(0)" onclick="deleteFile3('+row.fileId+')"><spring:message code="delete"/></a>';
						return str;
					}
				} 
			
			] ],
			onLoadSuccess : function() {
				//parent.$.messager.progress('close');
				//$(this).datagrid('selectRow', 0);
				$("#fileIds3").val('');
				var data =$('#contentTable_import_file3').datagrid('getData').rows;
				var fileIds = $("#fileIds3").val();
				for(var i =0;i<data.length;i++){
				  fileIds = fileIds+data[i].fileId+",";
				}
				$("#fileIds3").val(fileIds);
			}
		});
	 
	 
	 
	});
	
function fixWidth(percent)  
{  
	var doc_width = pageWidth();
	
    return (doc_width - 270) * percent ; //这里你可以自己做调整  
} 
	
function closeAd1(id){
	$("#"+id).hide();
}
function closeAd2(id){
	$("#"+id).hide();
}
function closeAd3(id){
	$("#"+id).hide();
}
function test1(file_name){
	var result =/\.[^\.]+$/.exec(file_name);
	return result;
	}
function test2(file_name){
	var result =/\.[^\.]+$/.exec(file_name);
	return result;
	}	
function test3(file_name){
	var result =/\.[^\.]+$/.exec(file_name);
	return result;
	}		
function deleteFile1(id){
	$("#deleteIds1").val(id);
	$('#wimport1').window('open');
}
function deleteFile2(id){
	$("#deleteIds2").val(id);
	$('#wimport2').window('open');
}
function deleteFile3(id){
	$("#deleteIds3").val(id);
	$('#wimport3').window('open');
}
function fileDelete1()
{
	var id = $("#deleteIds1").val();

	 $.ajax({
			type : 'POST',
			data:{
			fileId:id,
			},
			url : "${ctx}/deleteFile.do",
			success : function(data) {
         if(data=="false"){
        	 $("#infoMessageText").html("<spring:message code='delete_failure'/>");
 			$('#InfoMessage').window('open'); 
         }else{  
         //$("#import-window1").window("close");
         var fileIds = $("#fileIds1").val();
         fileIds = fileIds.replace(id+",","");
         $("#fileIds1").val(fileIds);
         var index = $('#contentTable_import_file1').datagrid('getRowIndex',id);
         $('#contentTable_import_file1').datagrid('deleteRow',index);
         $("#infoMessageText").html("<spring:message code='delete_success'/>");
			$('#InfoMessage').window('open'); 
         }         
			}
			}
        ); 
}
function fileDelete2()
{
	var id = $("#deleteIds2").val();

	 $.ajax({
			type : 'POST',
			data:{
			fileId:id,
			},
			url : "${ctx}/deleteFile.do",
			success : function(data) {
         if(data=="false"){
        	 $("#infoMessageText").html("<spring:message code='delete_failure'/>");
 			$('#InfoMessage').window('open'); 
         }else{  
        // $("#import-window2").window("close");
         var fileIds = $("#fileIds2").val();
         fileIds = fileIds.replace(id+",","");
         $("#fileIds2").val(fileIds);
         var index = $('#contentTable_import_file2').datagrid('getRowIndex',id);
         $('#contentTable_import_file2').datagrid('deleteRow',index);
         $("#infoMessageText").html("<spring:message code='delete_success'/>");
			$('#InfoMessage').window('open'); 
         }         
			}
			}
        ); 
}
function fileDelete3()
{
	var id = $("#deleteIds3").val();

	 $.ajax({
			type : 'POST',
			data:{
			fileId:id,
			},
			url : "${ctx}/deleteFile.do",
			success : function(data) {
         if(data=="false"){
        	 $("#infoMessageText").html("<spring:message code='delete_failure'/>");
 			$('#InfoMessage').window('open'); 
         }else{  
        // $("#import-window2").window("close");
         var fileIds = $("#fileIds3").val();
         fileIds = fileIds.replace(id+",","");
         $("#fileIds3").val(fileIds);
         var index = $('#contentTable_import_file3').datagrid('getRowIndex',id);
         $('#contentTable_import_file3').datagrid('deleteRow',index);
         $("#infoMessageText").html("<spring:message code='delete_success'/>");
			$('#InfoMessage').window('open'); 
         }         
			}
			}
        ); 
}
function cancelLoad(){
	$("#import-window").window("close");
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


</script>

<script language="javascript" type="text/javascript">

</script>
    <!-- 上传通用组件 -->
<div class="list_box mar_2">
   <!-- <div id="i_select_files"></div> -->
   <div class="aulist_3"><b></b><span style="color: red;">*</span>&nbsp;&nbsp;${title1}
   <div class="dropzone dz-clickable" id="i_stream_dropzone1" hidden="true">
			</div> 
     <div class="list_an" id="list1" role="toolbar" style="display:none;">
    

					
					<button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t1.cancel();">取消上传</button>
                    <button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t1.stop();" >停止上传</button>
					<button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t1.upload();" > 继续上传</button>
					<button type="button" class="btn  btn-primary zz_rging" id="i_select_files1">添加文件</button>		 					
     </div>
     <div class="list_an" id="list2"  style="display:block;margin-top:-33px">
      <button class="btn  btn-primary zz_rging" type="button" id="importFile1"><spring:message code="import_file"/></button>
     </div>
   </div>
   
   <div>
   <input type="hidden" id="fileIds1" name="fileIds1" value=""/>
   <input type="hidden" id="deleteIds1" name="deleteIds1" value=""/>
   <input type="hidden" id="fileDescs1" value=""/>
   <input type="hidden" id="functionId1" name="functionId1" value=""/>
   </div>
    
       <table id="contentTable_import_file1" class="easyui-datagrid" style="height:auto;border-top:1px solid #ccc;">
    	<!-- <tr class="tbth">
    		<td >文件说明</td>   		
    		<td >操作</td>
    	</tr>    	
    	<tr id="trip_table_Tr_1"> 
    		<td> <input id="" type="text"  name="leaveCity" class="input3 " /> </td>    		
    		<td> <a href="#" >查看</a> | <a href="#">删除</a></td>
    	</tr>     -->
    </table>
  </div>
    <!-- 上传通用组件 -->
<div class="list_box mar_2">
   <!-- <div id="i_select_files"></div> -->
   <div class="aulist_3"><b></b><%-- <span style="color: red;">*</span>&nbsp;&nbsp; --%>${title2}
   <div class="dropzone dz-clickable" id="i_stream_dropzone2" hidden="true">
			</div> 
     <div class="list_an" id="list3" role="toolbar" style="display:none;">
    

					
					<button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t2.cancel();">取消上传</button>
                    <button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t2.stop();" >停止上传</button>
					<button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t2.upload();" > 继续上传</button>
					<button type="button" class="btn  btn-primary zz_rging" id="i_select_files2">添加文件</button>		 					
     </div>
     <div class="list_an" id="list4"  style="display:block;margin-top:-33px">
      <button class="btn  btn-primary zz_rging" type="button" id="importFile2"><spring:message code="import_file"/></button>
     </div>
   </div>
   
   <div>
   <input type="hidden" id="fileIds2" name="fileIds2" value=""/>
   <input type="hidden" id="deleteIds2" name="deleteIds2" value=""/>
   <input type="hidden" id="fileDescs2" value=""/>
   <input type="hidden" id="functionId2" name="functionId2" value=""/>
   </div>
    
       <table id="contentTable_import_file2" class="easyui-datagrid" style="height:auto;border-top:1px solid #ccc;">
    	<!-- <tr class="tbth">
    		<td >文件说明</td>   		
    		<td >操作</td>
    	</tr>    	
    	<tr id="trip_table_Tr_1"> 
    		<td> <input id="" type="text"  name="leaveCity" class="input3 " /> </td>    		
    		<td> <a href="#" >查看</a> | <a href="#">删除</a></td>
    	</tr>     -->
    </table>
  </div>
 <div class="list_box mar_2">
   <!-- <div id="i_select_files"></div> -->
   <div class="aulist_3"><b></b>${title3}
   <div class="dropzone dz-clickable" id="i_stream_dropzone3" hidden="true">
			</div> 
     <div class="list_an" id="list5" role="toolbar" style="display:none;">
    

					
					<button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t3.cancel();">取消上传</button>
                    <button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t3.stop();" >停止上传</button>
					<button type="button" class="btn  btn-primary zz_rging" onclick="javascript:_t3.upload();" > 继续上传</button>
					<button type="button" class="btn  btn-primary zz_rging" id="i_select_files3">添加文件</button>		 					
     </div>
     <div class="list_an" id="list6"  style="display:block;margin-top:-33px">
      <button class="btn  btn-primary zz_rging" type="button" id="importFile3"><spring:message code="import_file"/></button>
     </div>
   </div>
   
   <div>
   <input type="hidden" id="fileIds3" name="fileIds3" value=""/>
   <input type="hidden" id="deleteIds3" name="deleteIds3" value=""/>
   <input type="hidden" id="fileDescs3" value=""/>
   <input type="hidden" id="functionId3" name="functionId3" value=""/>
   </div>
    
       <table id="contentTable_import_file3" class="easyui-datagrid" style="height:auto;border-top:1px solid #ccc;">
    	<!-- <tr class="tbth">
    		<td >文件说明</td>   		
    		<td >操作</td>
    	</tr>    	
    	<tr id="trip_table_Tr_1"> 
    		<td> <input id="" type="text"  name="leaveCity" class="input3 " /> </td>    		
    		<td> <a href="#" >查看</a> | <a href="#">删除</a></td>
    	</tr>     -->
    </table>
  </div> 
  
  <div>
      <table id="data_table" class="table tablesorter">
				<thead>
				</thead>
				<tbody id="bootstrap-stream-container">
				</tbody>
				<!-- <tfoot id="stream_total_progress_bar">
					<tr><th colspan="2">
							<div class="progress">
							  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
							  </div>
							</div>
						</th>
						<th colspan="2"><span class="stream_total_size"></span>
							<span class="stream_total_percent"></span>
						</th>
					</tr>
				</tfoot> -->
			</table>
  </div>
  
      <div id="wimport1" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="display:none;" >
		<div class="wz_tp"></div>
		<div class="wz_wx"><spring:message code='Confirm_sure_to_delete'/></div>
		<div class="wz_db" >
		 <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='confirm'/>" onclick="$('#wimport1').window('close');fileDelete1()"/>&nbsp;	
		  <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='Cancel'/>" onclick="$('#wimport1').window('close')"/>&nbsp;
		</div>
	</div>
	  <div id="wimport2" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="display:none;" >
		<div class="wz_tp"></div>
		<div class="wz_wx"><spring:message code='Confirm_sure_to_delete'/></div>
		<div class="wz_db" >
		 <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='confirm'/>" onclick="$('#wimport2').window('close');fileDelete2()"/>&nbsp;	
		  <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='Cancel'/>" onclick="$('#wimport2').window('close')"/>&nbsp;
		</div>
	</div>    
  <div id="wimport3" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="display:none;" >
		<div class="wz_tp"></div>
		<div class="wz_wx"><spring:message code='Confirm_sure_to_delete'/></div>
		<div class="wz_db" >
		 <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='confirm'/>" onclick="$('#wimport3').window('close');fileDelete3()"/>&nbsp;	
		  <input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='Cancel'/>" onclick="$('#wimport3').window('close')"/>&nbsp;
		</div>
	</div>   
  <!-- 上传通用组件 -->
  
  

