<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><spring:message code='indexPageTitle' />:<sitemesh:title />
</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="renderer" content="webkit" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<style>
.window .window-header {
	background: transparent none repeat scroll 0% 0%;
	padding: 0px 0px 6px;
	width: 302px;
}
</style>


<script language="javascript" type="text/javascript">
function showdiv() {            
            document.getElementById("bg").style.display ="block";
            document.getElementById("show").style.display ="block";
        }
function hidediv() {
            document.getElementById("bg").style.display ='none';
            document.getElementById("show").style.display ='none';
        }
</script>
<style type="text/css">
#bg {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.3;
	opacity: 0.3;
	filter: alpha(opacity = 70);
}

#show {
	display: none;
	position: absolute;
	top: 35%;
	left: 41%;
	padding: 8px;
	color: white;
	font-size: 14px;
	z-index: 1002;
	overflow: auto;
}
</style>
<link href="${ctx}/static/css/donghua.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/common.css" type="text/css"
	rel="stylesheet" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico"
	rel="shortcut icon" />
<link href="${ctx}/static/css/index.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/static/css/style.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap.css" type="text/css"
	rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js"
	type="text/javascript">
</script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"
	type="text/javascript">
</script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.js"
	type="text/javascript">
</script>
<script src="${ctx}/static/jquery/json2.js" type="text/javascript">
</script>
<script src="${ctx}/static/jquery/jquery.collapse.js"
	type="text/javascript">
</script>
<script src="${ctx}/static/jquery/jquery.collapse_storage.js"
	type="text/javascript">
</script>
<script src="${ctx}/static/jquery/jquery.collapse_cookie_storage.js"
	type="text/javascript">
</script>
<script src="${ctx}/static/jquery/jquery.SuperSlide.js"
	type="text/javascript">
</script>
<script language="javascript" type="text/javascript"
	src="${ctx}/static/My97DatePicker/WdatePicker.js">
</script>
<script src="${ctx}/<spring:message code='jQueryValidatorjs'/>"
	type="text/javascript">
</script>

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/easyui/themes/icon.css" />
<script src="${ctx}/static/easyui/jquery.easyui.min.js"
	type="text/javascript">
</script>
<script type="text/javascript"
	src="${ctx}/static/stream/js/stream-v1.js"></script>
<script src="${ctx}/static/js/validate.js" type="text/javascript"></script>
	
<script type="text/javascript" src="${ctx}/static/js/ajax-upload.js"></script>
<script src="${ctx}/static/js/jquery.form.js" type="text/javascript"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${ctx}/static/bootstrap/css/bootstrap-responsive.min.css"
	type="text/css" rel="stylesheet" />
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript">   
DD_belatedPNG.fix('.logo,img');   </script>
<![endif]-->
<script language="javascript" type="text/javascript">

$.messager.defaults = {
	ok : "是",
	cancel : "否"
};

$(function() {
	
	if ('${InfoMessage}' != '') { 
		
		
			if('${InfoMessage}'=='审批成功')
			{
				$('#messageAlertSuccessHead').attr("style","");
				setTimeout(function(){$("#messageAlertSuccessHead").attr("style","display: none;")},2000);
			}else
			{
			$('#InfoMessage').window('open');
			}
	}

	$(".panel-tool-close").bind("click",function(){
		$('#wDialog').window('close'); 
		$('#selectiframe').attr('src','');
	});
	
	
	$("form input").each(function(i,obj){		
		if($(obj).attr("onkeyup")) {
			$(obj).blur(function(){
				$(obj).trigger("onkeyup");
			});			
		}
	});



	$("#inputForm").validate( {
		submitHandler : function(form) {
			 showdiv();
	   			if(typeof($("#save_btn")) != "undefined"){
	   				$("#save_btn").attr('disabled', 'true');	
	   			}
	   			if(typeof($("#submit_btn")) != "undefined"){
	   				$("#submit_btn").attr('disabled', 'true');	
	   			}
			$("input[type=submit]").attr('disabled', 'true');
			var pageNumTag=$("#pageNumTag").val();
			 if(typeof(pageNumTag) == "undefined")
			{
				 $("#pageNumTag").val("1");
			} 
			 
				$("form input").each(function(i,obj){		 
					if($(obj).attr("onkeyup")){  
					    var re=new RegExp("limitLength*");    
			            if(re.test($(obj).attr("onkeyup")))
		            	{ 
			            //	$(obj).blur(function(){
								$(obj).trigger("onkeyup");
						//	});
		            	} 		
					}
				});
			 
			form.submit();
		}
	});
	
	$("#approveForm").validate( {
		submitHandler : function(form) {
			 showdiv();
	   			if(typeof($("#save_btn")) != "undefined"){
	   				$("#save_btn").attr('disabled', 'true');	
	   			}
	   			if(typeof($("#retrial_btn")) != "undefined"){
	   				$("#retrial_btn").attr('disabled', 'true');	
	   			}
	   			if(typeof($("#submit_btn")) != "undefined"){
	   				$("#submit_btn").attr('disabled', 'true');	
	   			}
			$("input[type=submit]").attr('disabled', 'true');
			var pageNumTag=$("#pageNumTag").val();
			 if(typeof(pageNumTag) == "undefined")
			{
				 $("#pageNumTag").val("1");
			} 
			 
				$("form input").each(function(i,obj){		 
					if($(obj).attr("onkeyup")){  
					    var re=new RegExp("limitLength*");    
			            if(re.test($(obj).attr("onkeyup")))
		            	{ 
			            //	$(obj).blur(function(){
								$(obj).trigger("onkeyup");
						//	});
		            	} 		
					}
				});
			 
			form.submit();
		}
	});

	$("#inputForm2").submit( function(){
		$("form input").each(function(i,obj){		 
			if($(obj).attr("onkeyup")){  
				var re=new RegExp("limitLength*");    
	            if(re.test($(obj).attr("onkeyup")))
            	{
	            	//$(obj).blur(function(){
						$(obj).trigger("onkeyup");
					//});
            	} 				
			}
		});
	});
	
	$("#inputForm").submit( function(){
		$("form input").each(function(i,obj){		 
			if($(obj).attr("onkeyup")){  
				var re=new RegExp("limitLength*");    
	            if(re.test($(obj).attr("onkeyup")))
            	{
	            	//$(obj).blur(function(){
						$(obj).trigger("onkeyup");
					//});
            	} 				
			}
		});
	});
 
	$("#searchForm").validate( {
		submitHandler : function(form) { 
			 showdiv();
			var pageNumTag=$("#pageNumTag").val();
			 if(typeof(pageNumTag) == "undefined")
			{
				 $("#pageNumTag").val("1");
			}
			form.submit();
		}
	});

	//面包屑加载............ 
	if ('<%=request.getSession().getAttribute("menuName")%>' != "" && '<%=request.getSession().getAttribute("menuName")%>' != "null") {
		$("#menuTop")
				.html(
						decodeURI(decodeURI('<%=request.getSession().getAttribute("menuName")%>')));

		ShowMenu(
				document
						.getElementById('<%=request.getSession().getAttribute("h1_id")%>'),
				'<%=request.getSession().getAttribute("h1_index")%>','<%=request.getSession().getAttribute("aNode")%>');
		
		ShowMenu(
				document
						.getElementById('<%=request.getSession().getAttribute("h2_id")%>'),
				'<%=request.getSession().getAttribute("h2_index")%>','<%=request.getSession().getAttribute("aNode")%>');
	}

	var now = (new Date()).toLocaleDateString();
	var week = "";
		if (new Date().getDay() == 0)
		week = "<spring:message code='Sunday'/>";
	 	else if (new Date().getDay() == 1)
		week = "<spring:message code='Monday'/>";
		 else if  (new Date().getDay() == 2)
		week = "<spring:message code='Tuesday'/>";
		 else if  (new Date().getDay() == 3)
		week = "<spring:message code='Wednesday'/>";
		 else if  (new Date().getDay() == 4)
		week = "<spring:message code='Thursday'/>";
		 else if  (new Date().getDay() == 5)
		week = "<spring:message code='Friday'/>";
		 else if  (new Date().getDay() == 6)
		week = "<spring:message code='Saturday'/>";

	$('#current-time').html(now + ' ' + week);

	var doc_height = pageHeight();
	var doc_width = pageWidth();
	$("#doc").height(doc_height - 80);
	$("#area-overflow").height(doc_height - 142);
	$("#area-overflow").width(doc_width - 220);
	$(window).resize(function() {
		$("#doc").height(pageHeight() - 80);
		$("#area-overflow").height(pageHeight() - 142);
		$("#area-overflow").width(pageWidth() - 220);
	});

	changeMyFold();
});
function pageHeight() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight
				: document.body.clientHeight;
	} else {
		return self.innerHeight;
	}
};
function pageWidth() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth
				: document.body.clientWidth;
	} else {
		return self.innerWidth;
	}
};
var t = null;
$(document).ready(function($){
	
	//待办事务提醒
	t = setInterval('initNocbox()',30000);
});
var timer = null;
var count =0;
var orititle = $(document).attr("title");
function initNocbox(){
	var alertStatus = '<%=request.getSession().getAttribute("alertStatus")%>';	
	if(alertStatus =='null'){
	var alertDate = '<%=request.getSession().getAttribute("alertTime")%>';
	alertDate = alertDate.replace(/-/g,"/");
	var alert = new Date(alertDate);	
	var date = new Date();
	var between = date.getTime() - alert.getTime();   //时间差的毫秒数
	  //计算相差的年数
    var years = Math.floor(between / (12 * 30 * 24 * 3600 * 1000));
    //计算相差的月数
    var leave = between % (12 * 30 * 24 * 3600 * 1000);
    var months = Math.floor(leave / (30 * 24 * 3600 * 1000));
    //计算出相差天数
    var leave0 = leave % (30 * 24 * 3600 * 1000);
    var days = Math.floor(leave0 / (24 * 3600 * 1000));
    //计算出小时数
    var leave1 = leave0 % (24 * 3600 * 1000);     //计算天数后剩余的毫秒数
    var hours = Math.floor(leave1 / (3600 * 1000));
    //计算相差分钟数
    var leave2 = leave1 % (3600 * 1000);         //计算小时数后剩余的毫秒数
    var minutes = Math.floor(leave2 / (60 * 1000));
    //计算相差秒数
    var leave3 = leave2 % (60 * 1000);       //计算分钟数后剩余的毫秒数
    var seconds = Math.round(leave3 / 1000);
	if(minutes>=1){
		if(timer !=null){
			clearInterval(timer);	
		}		 
		$.ajax({
			type : "post", // 使用post方法访问后台
			async : false,
			dataType : "json", // 返回JSON格式的数据
			url : '${ctx}/getWaitingThing.do',
		    success:function(result) {
		    	if(result.haveData){
		    	$("#body1 tr:not(:first)").remove();
		        var list = eval(result.list);
		        for(var i=0;i<list.length;i++){
		        	var appNo = i+1;
		        	if((list[i].status=='A' || list[i].status=='B') && list[i].endDate ==null){
		        		/* $("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a onclick=showMenuPage3("+"\""+"${ctx}/gotoBussnessApprovePage.do?notificationId="+list[i].notificationId+"&approveFlag=Y&processCode="+list[i].processCode+"&billId="+list[i].billCode+"\")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td></tr>");	  */	 				        	
		        		$("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a href=javascript:link("+"\""+list[i].notificationId+"\","+"\""+list[i].processCode+"\","+"\""+list[i].billId+"\","+"\""+'Y'+"\""+")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td>"+"<td align='center'><font color='red'>"+list[i].betweenTime+"</font></td>"+"</tr>");	
		        	}else{
		        		/* $("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a onclick=showMenuPage3("+"\""+"${ctx}/gotoBussnessApprovePage.do?notificationId="+list[i].notificationId+"&processCode="+list[i].processCode+"&billId="+list[i].billCode+"\")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td></tr>");	 */		
		        		$("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a href=javascript:link("+"\""+list[i].notificationId+"\","+"\""+list[i].processCode+"\","+"\""+list[i].billId+"\","+"\""+'N'+"\""+")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td>"+"<td align='center'><font color='red'>"+list[i].betweenTime+"</font></td>"+"</tr>");
		        	}
		        }
		        count = list.length;	
		        $("#new_noc_panel").show();
		    	}else{
		        $("#new_noc_panel").hide();	
		    	}
		    }
		    });
		if(count>0){
	        timer = setInterval(function(){
	        	var title = $(document).attr("title");
	    		if(title=="　　　　　　　　"){
	    			 $(document).attr("title","<spring:message code='no_solve'/>" );	
	    		}else{
	    			$(document).attr("title","　　　　　　　　");		
	    		}	
	        	
	        },1000);	
		}

	}
	}
	/* //待办事项清除message监听
	
	function setDataMessage(){
		var title = $(document).attr("title");
		if(title==""){
			 $(document).attr("title","<spring:message code='no_solve'/>" );	
		}else{
			$(document).attr("title","");		
		}	
	}
	 */

	}
	function closeFloat(){
		$("#new_noc_panel").hide();
		 $.ajax({
			type : "post", // 使用post方法访问后台
			dataType : "json", // 返回JSON格式的数据
			url : '${ctx}/setAlertTime.do',
		    success:function(result) {
		    	clearInterval(t);
		    	clearInterval(timer); 
		    	$(document).attr("title",orititle);	
		    }
		    });	
	}
	function allRead(){
		 parent.parent.$.messager.progress({
				title : '<spring:message code="toolTip"/>',
				text : '<spring:message code="content_clear"/>....'
			});	
		 $.ajax({
				type : "post", // 使用post方法访问后台
				dataType : "json", // 返回JSON格式的数据
				timeout :20000, 
				url : '${ctx}/updateAllNotificationStatus.do?d='+new Date(),
				  error:function(jqXHR, textStatus, errorThrown){
					   parent.parent.$.messager.progress('close');
		               $("#infoMessageText1").html('ESS服务器无响应,请按F5刷新查看是否系统已退出或已断网');
		               $('#InfoMessage1').window('open');	        
		        },
			    success:function(result) {
			    parent.parent.$.messager.progress('close');
			    if(result){
			    	$.ajax({
						type : "post", // 使用post方法访问后台
						async : false,
						timeout :20000, 
						dataType : "json", // 返回JSON格式的数据
						url : '${ctx}/getWaitingThing.do?d='+new Date(),
						  error:function(jqXHR, textStatus, errorThrown){
				               $("#infoMessageText1").html('ESS服务器无响应,请按F5刷新查看是否系统已退出或已断网');
				               $('#InfoMessage1').window('open');	        
				        },
					    success:function(result) {
					    	if(result.haveData){
					    	$("#body1 tr:not(:first)").remove();
					        var list = eval(result.list);
					        for(var i=0;i<list.length;i++){
					        	var appNo = i+1;
					        	if(list[i].status=='OPEN' && list[i].endDate ==null){
					        		/* $("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a onclick=showMenuPage3("+"\""+"${ctx}/gotoBussnessApprovePage.do?notificationId="+list[i].notificationId+"&approveFlag=Y&processCode="+list[i].processCode+"&billId="+list[i].billCode+"\")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td></tr>");	  */	 				        	
					        		$("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a href=javascript:link("+"\""+list[i].notificationId+"\","+"\""+list[i].processCode+"\","+"\""+list[i].billId+"\","+"\""+'Y'+"\""+")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td>"+"<td align='center'><font color='red'>"+list[i].betweenTime+"</font></td>"+"</tr>");	
					        	}else if(list[i].status !='OPEN' || list[i].endDate !=null){
					        		/* $("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a onclick=showMenuPage3("+"\""+"${ctx}/gotoBussnessApprovePage.do?notificationId="+list[i].notificationId+"&processCode="+list[i].processCode+"&billId="+list[i].billCode+"\")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td></tr>");	 */		
					        		$("#body1").append("<tr class='TableLine1'><td align='center'>"+appNo+"</td><td align='center'><a href=javascript:link("+"\""+list[i].notificationId+"\","+"\""+list[i].processCode+"\","+"\""+list[i].billId+"\","+"\""+'N'+"\""+")"+">"+list[i].subject+"</a>"+"<td align='center'>"+list[i].beginDate+"</td>"+"<td align='center'><font color='red'>"+list[i].betweenTime+"</font></td>"+"</tr>");
					        	}
					        }
					        count = list.length;	
					        $("#new_noc_panel").show();
					    	}else{
					        $("#new_noc_panel").hide();	
					    	}
					    }
					    });
			    	$("#infoMessageText1").html("<spring:message code='clear_success'/>");
					$('#InfoMessage1').window('open');		
			    }else{
			    	$("#infoMessageText1").html("<spring:message code='clear_failure'/>");
					$('#InfoMessage1').window('open');		
			    }
			    }
			    });	
		
		
	}
	
	function link(notificationId,processCode,billId,approveFlag){
		$('#notificationId').val(notificationId);
		$('#processCode').val(processCode);
		$('#billId').val(billId);
		$('#approveFlag').val(approveFlag);
		$('#confirmFrame').window('open');
		
	}
	function locationView(){
		var approvalFlag = $('#approveFlag').val();
		var notificationId = $('#notificationId').val();
		var processCode = $('#processCode').val();
		var billId = $('#billId').val();
		if(approvalFlag=='Y'){
			window.location = '${ctx}/gotoApprovePage.do?notificationId='+notificationId+'&approveFlag=Y&processCode='+processCode+'&billId='+billId;
		}else{
			window.location = '${ctx}/gotoApprovePage.do?notificationId='+notificationId+'&processCode='+processCode+'&billId='+billId;
		}

	}
	
	
</script>
<script language="JavaScript">
function showMenuPage(url, pagetop, h1_id, h1_index, h2_id, h2_index,aNode) {
	url = (url.substr(url.lastIndexOf('/') + 1));
	location.href = "showNavigation.do?url=" + url + "&menuName="
			+ encodeURI(encodeURI(pagetop)) + "&h1_id=" + h1_id + "&h1_index="
			+ h1_index + "&h2_id=" + h2_id + "&h2_index=" + h2_index+ "&aNode=" + aNode;
}

function showMenuPage2(url) {
	url = (url.substr(url.lastIndexOf('/') + 1));
	location.href = url;
}

function showMenuPage3(url) {
	showdiv();
	url = (url.substr(url.lastIndexOf('/') + 1));
	location.href = url;
}

function showMenuPage4(url, pagetop, h1_id, h1_index, h2_id, h2_index) {
	url = (url.substr(url.lastIndexOf('/') + 1));
	location.href = url;
}

function showMenuPagePageHold(url) {
	showdiv();
	url = (url.substr(url.lastIndexOf('/') + 1));
	
	var queryParams=$('#querySearchParamsCondition').val();
	if("undefined" == typeof(queryParams)){ 
		queryParams="";
	}
	location.href = url+"&querySearchParamsCondition="+queryParams;
}

function ShowMenu(obj, n,aNode) {
	if (obj == null || n == null) {
		return;
	}

	var Nav = obj.parentNode;
	if (!Nav.id) {
		var BName = Nav.getElementsByTagName("ul");
		var HName = Nav.getElementsByTagName("h2");
		var t = 2;
	} else {
		var BName = document.getElementById(Nav.id)
				.getElementsByTagName("span");
		var HName = document.getElementById(Nav.id).getElementsByTagName("h1");
		//  var HBName_all = document.getElementById(Nav.id).getElementsByTagName("b");
		var t = 1;
	}

	var HBName = obj.getElementsByTagName("b");

	if (HBName != null && HBName.length != 0) {
		for ( var i = 0; i < HBName.length; i++) {
			if (!hasClass(HBName[i],"fix")) {
				if (HBName[i].className == "bhover")
					HBName[i].className = "";
				else
					HBName[i].className = "bhover";
			}
		}
	}

	for ( var i = 0; i < HName.length; i++) {
		HName[i].innerHTML = HName[i].innerHTML.replace("-", "+");
		if (!hasClass(HName[i],"fix")) {
			HName[i].className = "";
		}
	}
	
	if(!hasClass(obj,"fix")) {
		obj.className = "h" + t;
	}
	for ( var i = 0; i < BName.length; i++) {
		if (i != n && !hasClass(BName[i],"fix")) {
			BName[i].className = "no";
		}
	}
	
	if (!hasClass(BName[n],"fix") && !hasClass(obj,"fix")){
		if (BName[n].className == "no") {
			BName[n].className = "";
			obj.innerHTML = obj.innerHTML.replace("+", "-");
		} else {
			BName[n].className = "no";
			obj.className = "";
			obj.innerHTML = obj.innerHTML.replace("-", "+");
		}
	}
	
	if($('#'+aNode) != null) 
	$('#'+aNode).attr("style","font-weight: bold;color: blue;");
}

function hasClass(element, className) { 
	var reg = new RegExp('(\\s|^)'+className+'(\\s|$)');
	return element.className.match(reg); 
}

function returnUrl() {
	var redirectUrl = '${redirectUrl}';
	if (redirectUrl != null && redirectUrl != '') {
		location.href = redirectUrl;
	} else {
		history.back();
	}
}
</script>
<script type="text/javascript">
function MyFold(obj) {
	var treeTD = document.getElementById("TreeTD");
	var mainTd = document.getElementById("MainTd");
	var toolBar = document.getElementById("ToolBar");
	var fal = "N";
	if (obj.src.indexOf("${ctx}/static/images/a_left.png") > 0) {
		obj.src = "${ctx}/static/images/a_right.jpg";
		treeTD.style.display = "none";
		mainTd.style["margin"] = 0 + " " + 0 + " " + 0 + " " + 15 + "px";
		toolBar.style["left"] = 0;

		$("#area-overflow").width(pageWidth() - 15);
		fal = "N";
	} else {
		obj.src = "${ctx}/static/images/a_left.png";
		treeTD.style.display = "";
		mainTd.style["margin"] = 0 + " " + 0 + " " + 0 + " " + 220 + "px";
		toolBar.style["left"] = 190 + "px";

		$("#area-overflow").width(pageWidth() - 220);
		fal = "Y";
	}
	$.ajax( {
		type : 'POST',
		url : '${ctx}/setToolBar.do?fal=' + fal
	});
}
function changeMyFold() {
	var treeTD = $("#TreeTD");
	var mainTd = $("#MainTd");
	var toolBar = $("#ToolBar");

	if ('<%=request.getSession().getAttribute("ToolBarImage")%>' == 'N') {
		$('#ToolBarImage').attr("src", "${ctx}/static/images/a_right.jpg");
		treeTD.css("display","none");
		mainTd.css("margin",0 + " " + 0 + " " + 0 + " " + 15 + "px");
		toolBar.css("left",0);
/* 		
		treeTD.style.display = "none";
		mainTd.style["margin"] = 0 + " " + 0 + " " + 0 + " " + 15 + "px";
		toolBar.style["left"] = 0; */

		$("#area-overflow").width(pageWidth() - 15);
	} else {
		$('#ToolBarImage').attr("src", "${ctx}/static/images/a_left.png");
		treeTD.css("display","block");
		mainTd.css("margin",0 + " " + 0 + " " + 0 + " " + 220 + "px");
		toolBar.css("left",190 + "px");

		$("#area-overflow").width(pageWidth() - 220);
	}
}

function clearForm() {
	$(':input', '#inputForm').not(':button, :submit, :reset').val('')
			.removeAttr('checked').removeAttr('selected');
}
/**如有两个form，可以用这个**/
function clearForm2() {
	$(':input', '#inputForm2').not(':button, :submit, :reset').val('')
			.removeAttr('checked').removeAttr('selected');
}
function clearQueryForm() {
	$(':input', '#searchForm').not(':button, :submit, :reset').val('')
			.removeAttr('checked').removeAttr('selected');
}
function closeInfoMessage()
{ 
	var redirectUrl = '${redirectUrl}';
	if (redirectUrl != null && redirectUrl != '') {
		$('#InfoMessage').window('close');
	} else {
		$('#InfoMessage').window('close');
		 
		if(typeof($('#cancel_btn').val())=="undefined")
			location.reload();
	} 
}

function  closeInfoMessage1()
{
	$('#InfoMessage1').window('close');	
}

function showTip(msg){
	$("#w3id").html(msg);
	$('#w3').window('open');
}

function showConfirm(msg,methodName) {
	$('#wDialog').window('close');
	$("#w4id").html(msg);
	$("#w4_method").attr('onclick',"$('#w4').window('close')"+";" +methodName);
	$('#w4').window('open');
}
 
function showPromote(msg,confirmmethod,cancelmethod){
	$("#w5id").html(msg);
	$("#confirm_method").attr('onclick',"$('#w5').window('close')"+";" +confirmmethod);
	$("#cancel_method").attr('onclick',"$('#w5').window('close')"+";" +cancelmethod);
	$('#w5').window('open');
}
</script>
<script type="text/javascript">
function RefreshResponsility(url, responsibilityId) {
	 showdiv();
	var parameter = {
		url : url,
		responsibilityId : responsibilityId
	};
	$.post('${ctx}/refreshResponsibility.do', parameter, function(data) {
		if (data && data == "true") {
			window.location.href = '${ctx}/' + url;
		} else {
			alert("切换职责失败,原因:网络异常,请检查网络后重试!");
			window.location.href =window.location.href ;
		}
	});
}

//获取字符串长度
function strlen(str) {	
	var len = 0;
	for (var i=0; i<str.length; i++) { 
		var c = str.charCodeAt(i); 
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
			len++; 
		}else{
			len+=3;
		}
	}
	return len;
}
//限制超长字符串的输入，obj为当前对象，length为最大长度
function limitLength(obj, length) {       
	var desc = obj.value;         
	obj.value = substr(obj.value,length);
}
//字符串替换
function stringReplaceAll(obj,replaceStr,toreplaceStr) {       
	obj.value = obj.value.replace(new RegExp(replaceStr,'gm'),toreplaceStr);  
}

//截取字符串
function substr(str,length){       
	var l=0,i=0;   
	if(str){	
		while(i<str.length){
			if(strlen(str.substring(0,i+1))>length){
				break;
			}
			i+=1;  
		}   
	}
	return str.substring(0,i);       
}  

function WKsendMsg() {
	var WFcontent = $('#WFcomment').val();
	WFcontent=WFcontent.replace(/(^\s+)|(\s+$)/g,"");
	$("#WFstep").val($("#backStep").val());
	var len=WFcontent.length;
	if(len==0){
		$("#infoMessageText1").html('发送消息不能为空');
		$('#InfoMessage1').window('open');
	}else{
		//$("#WKForm").submit();
		//$('#WKDialog').window('close');
		var parameter = {
		WFcomment : $("#WFcomment").val(),
		WFstep : $("#WFstep").val(),
		WFnotificationId : $("#WFnotificationId").val(),
		WFprocessCode : $("#WFprocessCode").val(),
		WFbillId : $("#WFbillId").val()
		};
		$.post('${ctx}/sendWFMsg.do', parameter, function(data) {
			if (data && data == "true") {
				alert("发送消息成功");
				window.location.href =window.location.href ;
			} else {
				alert("发送消息失败");
			}
		});
	}
}

function putRemarkValue(approvalComment){  
        	if(typeof($("#remark")) != "undefined"){
        		$("[name='remark']").val(approvalComment);
        		$("[name='remark']").text(approvalComment);
        		
        		}
} 


</script>

<sitemesh:head />
</head>

<body>
	<div class="container" style="width: 100%;">
		<input type="hidden" name="notificationId" id="notificationId"
			value="" /> <input type="hidden" name="processCode" id="processCode"
			value="" /> <input type="hidden" name="billId" id="billId" value="" />
		<input type="hidden" name="approveFlag" id="approveFlag" value="" />
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="doc" class="content clearfix">
			<!--缩进条bagin-->
			<div id="ToolBar" class="about_p">
				<img src="${ctx}/static/images/a_left.png" id="ToolBarImage"
					onclick="javascript:MyFold(this);" style="cursor: pointer" />
			</div>
			<!--缩进条over-->
			<div class="leftside clearfix" id="TreeTD">
				<div id="custom-show-hide-example">
					<%@ include file="/WEB-INF/layouts/left_top.jsp"%>
					<div id="menu">
						<%@ include file="/WEB-INF/layouts/left_cont.jsp"%>
					</div>
				</div>
			</div>
			<div class="main-area" id="MainTd">

				<%@ include file="/WEB-INF/layouts/rightTop.jsp"%>

				<sitemesh:body />

			</div>
		</div>
		<div id="nocbox_tips" style="display: none;"></div>
		<div id="new_noc_panel" class="dialogContainer ui-draggable"
			style="display: none; left: 500px; top: 77px; z-index: 80;">
			<%-- 			<div id="new_noc_list" style="display: block;">
				<div class="noc_iterm noc_iterm_2">
					<div class="noc_iterm_title">
						<b><img src="${ctx}/static/images/a_10.jpg"></img><spring:message code='wait_thing'/></b>
						<a class="close" href="javascript:;"/>
					</div>
					<div class="table_auto scroll_auto">
					<table id="contentTable" width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-bordered table-condensed table-hover"  style="margin:0 0 0 0">
					<tr>
					<td class="bzh"><spring:message code='app_no'/></td>
    		        <td class="bzh"><spring:message code='title'/></td>
    		        <td class="bzh"><spring:message code='createTime'/></td>
					</tr>
					</table>
					</div>
				</div>
			</div> --%>
			<table class="dialog" class="center">
				<tbody>
					<tr class="head">

						<td class="left"></td>
						<td class="center">
							<div class="title">
								<b><img src="${ctx}/static/images/a_10.jpg"></img> <spring:message
										code='wait_thing' /></b>
							</div> <a class="close" href="javascript:closeFloat()"></a>

						</td>
						<td class="right"></td>
					</tr>

					<tr class="body" style="background-color: white">

						<td width="10px"></td>
						<td class="center">
							<div id="dialog_content_5" class="msg-content"
								style="height: 250px; overflow-y: auto;">
								<table id="contentTable" class="TableList" boder="1">
									<tbody id="body1">
										<tr class="TableHeader">
											<td width="80"><spring:message code='app_no' /></td>
											<td width="420"><spring:message code='title' /></td>
											<td width="160"><spring:message code='createTime' /></td>
											<td width="100"><spring:message code='from_now' /></td>
										</tr>
										<tr class="TableLine1">
											<td align="left"></td>
											<td align="left"></td>
											<td align="left"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="button">
								<a id="allRead" class="btn-white-big"
									href="javascript:allRead();"><spring:message
										code='one_clear' /></a>&nbsp;&nbsp;&nbsp; <a id="CloseBtn"
									class="btn-white-big" href="javascript:closeFloat();"><spring:message
										code='closed' /></a>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="confirmFrame" class="easyui-window ui_bccg" title="&nbsp;"
			data-options="modal:true,closed:true,iconCls:'icon-save'"
			style="display: none;">
			<div class="wz_tp"></div>
			<div class="wz_wx">
				<spring:message code='Confirm_sure_to_link' />
			</div>
			<div class="wz_db">
				<input id="submit_btn2" class="btn buttom1 " type="button"
					value="<spring:message code='confirm'/>"
					onclick="$('#confirmFrame').window('close');locationView()" />&nbsp;
				<input id="submit_btn2" class="btn buttom1 " type="button"
					value="<spring:message code='Cancel'/>"
					onclick="$('#confirmFrame').window('close')" />&nbsp;
			</div>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
	
	<div id="w3" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="display: none">
		<div class="wz_tp"></div>
		<div class="wz_wx" id="w3id">无选中内容,请选择需要操作的数据!</div>
		<div class="wz_db">
			<input id="submit_btn2" class="btn buttom1 " type="button" value="确定" onclick="$('#w3').window('close')" />&nbsp;
		</div>
	</div>
	
	<div id="w4" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="display: none">
			<div class="wz_tp"></div>
			<div class="wz_wx" id="w4id"></div>
			<div class="wz_db">
				<input id="w4_method" class="btn buttom1 " type="button" value="确定" onclick="window.location.reload()" />&nbsp;
			</div>
	</div>
	
	<div id="w5" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="display: none">
            <div class="wz_tp"></div>
            <div class="wz_wx" id="w5id">确认要失效吗?</div>
            <div class="wz_db">
                <input id="confirm_method" class="btn buttom1 " type="button" value="确定"  />&nbsp; 
                <input id="cancel_method" class="btn buttom1 " type="button" value="取消" onclick="$('#w5').window('close')" />&nbsp;
            </div>
    </div>

	<div id="InfoMessage" class="easyui-window ui_bccg" title="&nbsp;"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="overflow-x: hidden; display: none;">
		<div class="wz_tp2"></div>
		<div class="wz_wxbig" id="infoMessageText" style="text-align: center;">
			${InfoMessage}</div>
		<div class="wz_db">
			<input id="submit_btn2" class="btn buttom1 " type="button" value="确定"
				onclick="closeInfoMessage()" /> &nbsp;
		</div>
	</div>
	<div id="InfoMessage1" class="easyui-window ui_bccg" title="&nbsp;"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="overflow-x: hidden; display: none;">
		<div class="wz_tp2"></div>
		<div class="wz_wxbig" id="infoMessageText1"
			style="text-align: center;">${InfoMessage}</div>
		<div class="wz_db">
			<input id="submit_btn2" class="btn buttom1 " type="button" value="确定"
				onclick="closeInfoMessage1()" /> &nbsp;
		</div>
	</div>
	<script src="${ctx}/static/bootstrap/css/bootstrap.min.css"
		type="text/javascript">
</script>
	<div id="bg"></div>
	<div id="show">
		<svg viewBox="0 0 120 120" version="1.1"
			xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink"> <g id="circle"
			class="g-circles g-circles--v1"> <circle id="12"
			transform="translate(35, 16.698730) rotate(-30) translate(-35, -16.698730) "
			cx="35" cy="16.6987298" r="10"></circle> <circle id="11"
			transform="translate(16.698730, 35) rotate(-60) translate(-16.698730, -35) "
			cx="16.6987298" cy="35" r="10"></circle> <circle id="10"
			transform="translate(10, 60) rotate(-90) translate(-10, -60) "
			cx="10" cy="60" r="10"></circle> <circle id="9"
			transform="translate(16.698730, 85) rotate(-120) translate(-16.698730, -85) "
			cx="16.6987298" cy="85" r="10"></circle> <circle id="8"
			transform="translate(35, 103.301270) rotate(-150) translate(-35, -103.301270) "
			cx="35" cy="103.30127" r="10"></circle> <circle id="7" cx="60"
			cy="110" r="10"></circle> <circle id="6"
			transform="translate(85, 103.301270) rotate(-30) translate(-85, -103.301270) "
			cx="85" cy="103.30127" r="10"></circle> <circle id="5"
			transform="translate(103.301270, 85) rotate(-60) translate(-103.301270, -85) "
			cx="103.30127" cy="85" r="10"></circle> <circle id="4"
			transform="translate(110, 60) rotate(-90) translate(-110, -60) "
			cx="110" cy="60" r="10"></circle> <circle id="3"
			transform="translate(103.301270, 35) rotate(-120) translate(-103.301270, -35) "
			cx="103.30127" cy="35" r="10"></circle> <circle id="2"
			transform="translate(85, 16.698730) rotate(-150) translate(-85, -16.698730) "
			cx="85" cy="16.6987298" r="10"></circle> <circle id="1" cx="60"
			cy="10" r="10"></circle> </g> <use xlink:href="#circle" class="use" /> </svg>
		<div>&nbsp;&nbsp;&nbsp;正在处理中,请稍候...</div>
	</div>
	<div id="WKDialog" class="easyui-window " title="发送消息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:380px;overflow-x:hidden;display:none;">
		<form id="WKForm" name="WKForm" action="${ctx}/sendWFMsg.do" method="post" class="form-horizontal">
			<div class="row-fluid autwo mar_2">		
				<table class="table2">
					<tr>
						<td class="text_rg title_font bzh" style="width:30px;!important">
							内容
						</td>
						<td colspan="3" class="text_lf" width="200px">
							<textarea  style="width:600px;height:225px !important" onkeyup="limitLength(this,150);" id="WFcomment" name="WFcomment" class="input1 required"></textarea>
							<span>*</span>
						</td>
					</tr>
				</table>				  
			</div>
			<div class="dbzn ">
				<input type="hidden" name="WFnotificationId"  id="WFnotificationId" value="${param.notificationId}"/>
		   	    <input type="hidden" name="WFprocessCode"  id="WFprocessCode" value="${param.processCode}"/>
		   	    <input type="hidden" name="WFbillId"  id="WFbillId" value="${param.billId}"/> 
		   	    <input type="hidden" name="WFstep"  id="WFstep" value=""/> 
				<input id="submit_btn1" class="btn buttom1 " type="button" value="发送" onclick="WKsendMsg()"/>&nbsp; 
				<input id="cancel_btn" class="btn buttom1" type="button" value="<spring:message code='Cancel'/>" onclick="$('#WKDialog').window('close')" />
			</div>
		</form>
 	</div>
	
</body>
</html>