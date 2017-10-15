<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html style="height: auto;">

<head>
<title><spring:message code='login_page' /></title>
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.js" type="text/javascript">
<script src="${ctx}/static/jquery/json2.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_cookie_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.SuperSlide.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/<spring:message code='jQueryValidatorjs'/>" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${ctx}/static/bootstrap/css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet" />

<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css" />
<script src="${ctx}/static/easyui/jquery.easyui.min.js" type="text/javascript"></script> 
<script src="${ctx}/static/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.base64.min.js" type="text/javascript"></script>    
<style>
.alertToo {
	background-color: #fcf8e3;
	border: 1px solid #fbeed5;
	border-radius: 4px;
	margin-bottom: 20px;
	padding: 8px 35px 8px 14px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	font-size: 12px !important;
	width: 150px;
	margin-left: 22%;
}
</style>
<script lang="javascript" type="text/javascript">




	//使用jquery添加验证事件
		$(document).ready(function() {
			
		if ('<%=(String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME)%>' != 'null') {
			$('#InfoMessage').window('open');
		}
		if ($.cookie("rmbUser") == "true") { 
			$("#rememberMex").attr("checked", true); 
			$("#username").val($.cookie("userName")); 
			$("#password").val($.cookie("passWord")); 
		 } 
		$("#loginForm").validate({
			submitHandler : function(form) { //通过之后回调 
				if ($("#username2").val() != "" && $("#password2").val() != "") {
					$("#username").val($("#username2").val());
					$("#password").val($("#password2").val());					
					saveUserInfo();
					form.submit();
				} else {
					saveUserInfo();
					form.submit();
				}

			}
		});
	});
	
	
	
		function saveUserInfo() { 
			var passWord = $("#password").val();  
			if ($("#rememberMex").attr("checked") == 'checked') { 
				var userName = $("#username").val(); 				
				$.cookie("rmbUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie 
				$.cookie("userName", userName, { expires: 7 }); // 存储一个带7天期限的 cookie
				
				var entrypass = $.cookie("passWord");
				
				entrypass = $.base64.decode(entrypass);
				
				if($.base64.encode(entrypass) == passWord) {
					$.cookie("passWord",passWord, { expires: 7 });
				} else {
					$.cookie("passWord",$.base64.encode(passWord),{ expires: 7 });
					$("#password").val($.base64.encode(passWord));
				}
			
			} 
			else { 
			$.cookie("rmbUser", "false", { expires: -1 }); 
			$.cookie("userName", '', { expires: -1 }); 
			$.cookie("passWord", '', { expires: -1 });
			$("#password").val($.base64.encode(passWord));
			} 
			} 
		
		function changeLang(v)
		{ 
			if(v=="en_US")
			{
				var name=window.location.hostname;
				var newname=name.replace('ess','esse');
				window.location.href=window.location.protocol+"//"+newname+'${ctx}/login.do?locale=en_US';
			}else
			{
				window.location.href=window.location.protocol+"//"+window.location.hostname+'${ctx}/login.do?locale=zh_CN';
			} 
		}
</script>
</head>

<body>
	<form id="loginForm" action="${ctx}/login.do" method="post" class="form-horizontal">

		<div class="hidden-phone">
			<div class="dl_box">
				<div class="<spring:message code='login_logo_jpg'/>"></div>
				<div class="<spring:message code='dl_1_jpg'/>"></div>
				<div class="dl_open">
					<div class="dl_small">
						<div class="dl_title">
							<spring:message code='loginTitle' />
						</div>
						<!-- 登录内容 -->
						<div class="dl_cont">
							<div class="control-group">
								<!--  <label for="username" class="control-label" style="float:left;"><spring:message code="loginName"/></label>-->

								<div class="controls dl_dkj">

									<input type="text" id="username" name="username" value="${username}" class="dl_input input-medium required" placeholder="<spring:message code='login_Name' />" />
								</div>
							</div>
							<div class="control-group dl_dkj">
								<!--<label for="password" class="control-label"><spring:message code="passWord"/></label>-->
								<div class="controls dl_dkj">

									<input type="password" id="password" name="password" class="dl_input2 input-medium required" placeholder="<spring:message code='login_pwd' />" />
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<div class="dl_chek">
										<label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe" checked="checked" name="rememberMe" /> <spring:message code='RememberMe' />
										<a href="${ctx}/gotoPwdByEmail.do" style="float: right;margin-right: 2px;*margin-top: -20px;"><spring:message code='Retrieve_password' /></a></label>
									</div>
									<input id="submit_btn" class="dl_buttom btn " type="submit" value="<spring:message code='loginButtonName'/>" />
									<!--  <a class="btn" href="###">  <spring:message code='registerButtonName'/></a> -->

								</div>
							</div>
						</div>
						<!-- 登录内容 -->
					</div>
				</div>
			</div>
			<div class="dl_englist">
				<a href="#" onclick="changeLang('en_US')"><spring:message code='locale_en_US' /></a> | <a href="#" onclick="changeLang('zh_CN')"><spring:message code='locale_zh_CN' /></a>				
			</div>
		</div>

		<!-- 手机版begin -->
		<div class="visible-phone">
			<div class="auphone_logo">
				<img src="${ctx}/static/images/<spring:message code='p_1.jpg'/>">
			</div>
			<div class="auphone_box">
				<input type="text" id="username2" name="username2" value="${username}" class="auphone_inputer input-medium required" placeholder="User Name" /> <input type="password" id="password2"
					name="password2" class="auphone_inputer2 input-medium required" placeholder="Password" />
			</div>
			<div class="auphone_chek">
				<label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMex2" name="rememberMex2" checked="checked" value="" /><spring:message code='RememberMe' /> </label>
			</div>
			<input id="submit_btn" class="auphone_bom btn btn-primary" type="submit" value="<spring:message code='loginButtonName'/>" />
			<div class="dl_englist">
				<a href="#" onclick="changeLang('en_US')"><spring:message code='locale_en_US' /></a> | <a href="#" onclick="changeLang('zh_CN')"><spring:message code='locale_zh_CN' /></a>
			</div>

		</div>
		<!-- 手机版over -->


	</form>
	<div id="InfoMessage" class="easyui-window ui_bccg" title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="overflow-x: hidden;">
		<div class="wz_tp2"></div>
		<div class="wz_wxbig" id="infoMessageText" style="text-align: center;">
			<%
				String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
				if (error != null) {
			%>
	  <%
			    if(error.contains("DisabledAccountException")){
					out.print("帐号已失效!");
				}
				else if(error.contains("ConcurrentAccessException")){
					out.print("用户类型和系统类型不匹配,请更换登录地址.");
				} 
				else{
					out.print("用户名或密码错误,请重试.");
				}
			 
		%>		
			<%
				}
			%>
		</div>
		<div class="wz_db">
			<input id="submit_btn2" class="btn buttom1 " type="button" value="<spring:message code='confirm_error' />" onclick="$('#InfoMessage').window('close')" /> &nbsp;
		</div>
	</div>

</body>
</html>

