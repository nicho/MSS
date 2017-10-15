<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>密码编辑</title>
</head>
<body>

<form id="inputForm" action="${ctx}/updateUserPwd.do" method="post" class="form-horizontal">
		<fieldset>
		<input type="hidden" name="userId" value="${user.userId}"/>
		
		   <div class="add_top" style="">
						&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;密码编辑
		  </div>
		   
		  
  
  <div class="row-fluid autwo mar_2">  
        <table  class="table2" style="margin-left:20%;">
    	<tr>
    	    <td class="text_rg title_font bzh">员工帐号</td>
    		<td class="text_lf">
    		    ${user.userName}
			</td>
    	</tr>
    	<tr>    	  
    		<td class="text_rg title_font bzh">帐号类型</td>
    		<td class="text_lf">
    		 	<c:if test="${user.userType == '20'}">员工用户</c:if>
    		 	<c:if test="${user.userType == '30'}">经销商用户</c:if>
    		 	<c:if test="${user.userType == '40'}">门店用户</c:if>
			</td>
    	</tr> 
    	<tr>
    		<td class="text_rg title_font bzh">关联信息</td>    		
    		<td class="text_lf">
    		   <c:if test="${user.userType == '20'}">${user.personName}</c:if>
    		   <c:if test="${user.userType == '30'}">${user.custAccountName}</c:if>
    		   <c:if test="${user.userType == '40'}">${user.userType}</c:if>
    		</td> 
    	</tr>
    	<tr>    	  
    		<td class="text_rg title_font bzh">邮箱</td>
    		<td class="text_lf">
    		 	${user.email}
			</td>
    	</tr> 
    	<tr>    	  
    		<td class="text_rg title_font bzh">电话</td>
    		<td class="text_lf">
    		 	${user.phone}
			</td>
    	</tr> 
    	<tr>
    		<td class="text_rg title_font bzh">密码</td>    		
    		<td class="text_lf">
    		  <input type="password" id="password" name="password" value="" class="  input-large required"/>    		  
    		</td> 
    	</tr>
    	<tr>
    		<td class="text_rg title_font bzh">新密码</td>    		
    		<td class="text_lf">
    		  <input type="password" id="newPwd" name="newPwd" value="" class="  input-large required"/>    		  
    		</td> 	
    	</tr>
		<tr>
    		<td class="text_rg title_font bzh">确认密码</td>    		
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
</body>
</html>