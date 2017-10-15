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
<title>密码修改</title>
</head>
<body>

<form id="inputForm" action="${ctx}/updateBasePwd.do" method="post" class="form-horizontal">
		<fieldset>
		<input type="hidden" name="userId" value="${user.userId}"/>
		  
  <div class="row-fluid autwo mar_2">  
        <table  class="table2" style="margin-left:20%;">
    	<tr>
    	    <td class="text_rg title_font bzh" style="font-size:14px;">员工帐号</td>
    		<td class="text_lf" style="font-size:14px;">
    		    ${user.userName}
			</td>
    	</tr>
    	<tr>    	  
    		<td class="text_rg title_font bzh" style="font-size:14px;">帐号类型</td>
    		<td class="text_lf" style="font-size:14px;">
    		 	<c:if test="${user.userType == '20'}">员工用户</c:if>
    		 	<c:if test="${user.userType == '30'}">经销商用户</c:if>
    		 	<c:if test="${user.userType == '40'}">门店用户</c:if>
			</td>
    	</tr> 
    	<tr>
    		<td class="text_rg title_font bzh" style="font-size:14px;">关联信息</td>    		
    		<td class="text_lf" style="font-size:14px;">
    		   <c:if test="${user.userType == '20'}">${user.personName}</c:if>
    		   <c:if test="${user.userType == '30'}">${user.custAccountName}</c:if>
    		   <c:if test="${user.userType == '40'}">${user.userType}</c:if>
    		</td> 
    	</tr>
    	<tr>
    		<td class="text_rg title_font bzh" style="font-size:14px;">密码</td>    		
    		<td class="text_lf" >
    		  <input type="password" id="password" name="password" value="" class="  input-large required"/>    		  
    		</td> 
    	</tr>
    	<tr>
    		<td class="text_rg title_font bzh" style="font-size:14px;">新密码</td>    		
    		<td class="text_lf">
    		  <input type="password" id="newPwd" name="newPwd" value="" class="  input-large required"/>    		  
    		</td> 	
    	</tr>
		<tr>
    		<td class="text_rg title_font bzh" style="font-size:14px;">确认密码</td>    		
    		<td class="text_lf">
    		  <input type="password" id="confirmpwd" name="confirmpwd" value="" class="  input-large required"/>    		  
    		</td> 
    	</tr>
    	</table>
  </div>
			
					
			<div class=" rgcont_long">
			&nbsp;	 &nbsp;	 &nbsp;	 &nbsp;	 &nbsp;	 &nbsp;	 
				<input style="margin-left:-20%;" id="submit_btn" class="btn buttom1 " type="submit" value="<spring:message code='Save'/>"/>
			</div>
		</fieldset>
	</form>
	
	
	<div id="InfoMessage" class="easyui-window ui_bccg" title="&nbsp;" style="width: 312px!important;height: 163px!important;display:none;"
		data-options="modal:true,closed:true,iconCls:'icon-save'" style="overflow-x: hidden;height:280px;">
		<div class="wz_tp2"></div>
		<div class="wz_wxbig" id="infoMessageText"
			style="text-align: center;">
			${InfoMessage}
		</div>
		<div class="wz_db">
			<input id="submit_btn2" class="btn buttom1 " type="button"
				value="确定" onclick="$('#InfoMessage').window('close')" />
			&nbsp;
		</div>
	</div>
	
	<script language="javascript" type="text/javascript">

		
		$(function() {
		
			if ('${InfoMessage}' != '') {
				$('#InfoMessage').window('open');
			}
			
			$("#password").val("");
			
			$(".panel-body").css("overflow","visible");
		});
	</script>
</body>
</html>