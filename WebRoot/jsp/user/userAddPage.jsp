<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>新增用户</title>

	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#login_Name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					login_Name: {
						remote: "${ctx}/checkLoginName.do"
					}
				},
				messages: {
					login_Name: {
						remote: "用户登录名已存在"
					}
				}
			});
		});
	</script>
  </head>
  <body>
	<form id="inputForm" action="${ctx}/addUserInfo.do" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>用户注册</small></legend>
			<div class="control-group">
				<label for="login_Name" class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" id="login_Name" name="login_Name" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label">姓名:</label>
				<div class="controls">
					<input type="text" id="name" name="name" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="password" name="password" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">确认密码:</label>
				<div class="controls">
					<input type="password" id="confirmPassword" name="confirmPassword" class="input-large required" equalTo="#password"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
</body>
 
</html>
