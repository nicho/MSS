<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/static/css/common.css" type="text/css"
			rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap-responsive.min.css"
			type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}"
			type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap.css" type="text/css"
			rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js"
			type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"
			type="text/javascript">
</script>
<link rel="stylesheet" type="text/css"
			href="${ctx}/static/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="${ctx}/static/easyui/themes/icon.css" />
		<script src="${ctx}/static/easyui/jquery.easyui.min.js"
			type="text/javascript">
</script>

<html>
<head> 
<title>密码找回</title>
</head>
<body>

<form id="inputForm" action="${ctx}/findPwdByEmail.do" method="post" class="form-horizontal">
		<fieldset>
		  
  <div class="row-fluid autwo mar_2" style="height: 180px; width:40%!important">  
        <table  class="table2" style="">
    	
    	<tr style="text-align:center">
    		<td class="text_rg title_font bzh" style="font-size:14px;">请输入用户名:</td>    		
    		<td class="text_lf" style="">
    		  <input type="text" id="userName" name="userName" value="" class="input8 required" style="width:150px;width:50%!important;padding:4px 6px;height:27px;font-size:12px;"/>    		  
    		</td> 
    	</tr>   	   	
    	<br/>
    	<br/>
    	<tr><td></td></tr>
    	<tr><td></td></tr>
    	<tr style="text-align:center">
    	
    	<td class="text_lf" colspan="2" rowspan="2" style="text-align:middle">
    		<li class="rgcont_long">
    		<input  id="submit_btn" class="btn buttom1" type="submit" value="<spring:message code='confirm'/>"/></td></li></tr>
    		
    	</table>
  </div>
			
					
			<div class=" rgcont_long">
			&nbsp;	 &nbsp;	 &nbsp;	 &nbsp;	 &nbsp;	 &nbsp;	 
				
			</div>
		</fieldset>
	</form>
	
	
	<div id="InfoMessage" class="easyui-window ui_bccg" title="&nbsp;" style="width: 312px!important;height: 163px!important;display:none;"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="overflow-x: hidden;height:280px;" >
		<div class="wz_tp2"></div>
		<div class="wz_wxbig" id="infoMessageText"
			style="text-align: center;">
			${InfoMessage}
		</div>
		<div class="wz_db">
			<input id="submit_btn2" class="btn buttom1 "  type="button"
				value="确定" onclick="$('#InfoMessage').window('close')" />
			&nbsp;
		</div>
	</div>
	
	<script language="javascript" type="text/javascript">

		
		$(function() {
		
			if ('${InfoMessage}' != '') {
				$('#InfoMessage').window('open');
			}
			
			$(".panel-body").css("overflow","visible");
		});
	</script>
</body>
</html>