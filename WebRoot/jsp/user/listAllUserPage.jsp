<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>所有数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    所有结果<br/>
    
    <div class="visible-desktop">
   <table id="contentTable" class="table table-striped table-bordered table-condensed table-hover ">
    	<tr>
    		<td>序号</td>
    		<td>登陆名</td>
    		<td>用户名</td>
    		<td>密码</td>
    		<td>SALT</td>
    		<td>角色</td>
    		<td>注册日期</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="list"  items="${userLists}" varStatus="vstatus">
    	<tr>
    		<td>${vstatus.index+1}</td>
    		<td>${list.login_Name}</td>
    		<td>${list.name}</td>
    		<td>${list.password}</td>
    		<td>${list.salt}</td>
    		<td>${list.roles}</td>
    		<td><fmt:formatDate value="${list.register_Date}" pattern="yyyy-MM-dd  HH-mm-ss" /></td>
    		<td><a href="userModify.do?tid=${list.id}">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="del.do?tid=${list.id}">删除</a></td>
    	</tr>
    	</c:forEach> 
    </table> 
    </div>
    
     <div class="visible-tablet">
   <table id="contentTable" class="table table-striped table-bordered table-condensed table-hover ">
    	<tr>
    		<td>序号</td>
    		<td>登陆名</td>
    		<td>用户名</td> 
    		<td>角色</td>
    		<td>注册日期</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="list"  items="${userLists}" varStatus="vstatus">
    	<tr>
    		<td>${vstatus.index+1}</td>
    		<td>${list.login_Name}</td>
    		<td>${list.name}</td> 
    		<td>${list.roles}</td>
    		<td><fmt:formatDate value="${list.register_Date}" pattern="yyyy-MM-dd  HH-mm-ss" /></td>
    		<td><a href="userModify.do?tid=${list.id}">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="del.do?tid=${list.id}">删除</a></td>
    	</tr>
    	</c:forEach> 
    </table> 
    </div>
    
    
    <div class="visible-phone">
    
     <table id="contentTable" class="table table-striped table-bordered table-condensed table-hover ">
    	<tr>
    		<td>序号</td> 
    		<td>登陆名</td> 
    		<td>角色</td>
    		<td>注册日期</td> 
    	</tr>
    	<c:forEach var="list"  items="${userLists}" varStatus="vstatus">
    	<tr>
    		<td>${vstatus.index+1}</td> 
    		<td>${list.name}</td>  
    		<td>${list.roles}</td>
    		<td><fmt:formatDate value="${list.register_Date}" pattern="yyyy-MM-dd  HH-mm-ss" /></td> 
    	</tr>
    	</c:forEach> 
    </table> 
    </div>
    
  </body>
</html>
