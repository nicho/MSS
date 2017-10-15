<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>用户查看</title>
</head>
<body> 
	<c:if test="${not empty InfoMessage}">
		<div id="InfoMessage" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${InfoMessage}</div>
	</c:if>
	<form class="form-search" action="${ctx}/updateUser.do" id="inputForm" >
	  <input type="hidden" name="menuName" value="${param.menuName}"/>
	  <input type="hidden" name="h1_id" value="${param.h1_id}"/>
	  <input type="hidden" name="h1_index" value="${param.h1_index}"/>
	  <input type="hidden" name="h2_id" value="${param.h2_id}"/>
	  <input type="hidden" name="h2_index" value="${param.h2_index}"/>
	  <input type="hidden" name="userId" value="${user.userId}"/>
	
	   <div style="overflow:auto" id="area-overflow">
        <div class="main-box" id="area-right"> 
               
        <div class="add_top" style><b></b>用户查看
		     <div class="list_an">
		     	<button id="cancel_btn"  class="btn btn-primary" type="button" onClick="location.href='gotoUserList.do'">返回</button>
		     </div>
        </div> 
               
 				<!-- 搜索条件 -->
  				<div class="aurgcont bcnone"> 
  					<div class="row-fluid"> 
   						<div class="span12">
      						<ul>
						      <li class="rgcont_li">
							    <div class="rgcont_wz">用户名</div>
							    <div class="rgcont_rr">
							    	<input type="text" class="input1" disabled="disabled" name="userName" id="userName" value="${user.userName}"/>
							    </div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">用户类型</div>
							    <div class="rgcont_rr">
							      <select type="text" name="userType" id="userType" disabled="disabled" class="select1">
						            <option value="20" <c:if test="${user.userType == '20'}">selected="selected"</c:if>>员工用户</option>
						            <option value="30" <c:if test="${user.userType == '30'}">selected="selected"</c:if>>经销商用户</option>
						            <option value="40" <c:if test="${user.userType == '40'}">selected="selected"</c:if>>门店用户</option>
							      </select></div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">人员</div>
							    <div class="rgcont_rr">
							      <input type="text" class="input1 " name="personName" id="personName" disabled="disabled" value="${user.personName}"/>
								  <input type="hidden" class="input1 " name="personId" id="personId" value="${user.personId}"/>
							   </div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">关联经销商</div>
							    <div class="rgcont_rr">
							      <input type="text" class="input1 " name="accountName" id="accountName" disabled="disabled" value="${user.custAccountName}"/>
	    						  <input type="hidden" class="input1 " name="custAccountId" id="custAccountId" value="${user.custAccountId}"/>
							   </div>
							  </li>
							  <li class="rgcont_li">
								 <div class="rgcont_wz">关联门店</div>
								 <div class="rgcont_rr">
								      <input type="text" class="input1 " name="storeCode" id="storeCode" disabled="disabled" />
								 </div>
							  </li>
							  <li class="rgcont_li">
								 <div class="rgcont_wz">生效日期</div>
								 <div class="rgcont_rr">
								 		<input type="text" class="input1 input-large required" disabled="disabled" name="beginDate" value="${user.beginDate}" id="beginDate"/>
								 </div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">失效日期</div>
							    <div class="rgcont_rr">
							    		<input type="text" class="input1 input-large required" disabled="disabled" name="endDate" value="${user.endDate}" id="endDate"/>							    	
								</div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">是否EBS用户</div>
							    <div class="rgcont_rr">
							    	<input type="radio" disabled="disabled" name="internalUser"	value="Y" <c:if test="${user.internalUser eq 'Y'}">checked='checked'</c:if>/>
							    	是&nbsp;
							    	<input type="radio" disabled="disabled" name="internalUser" value="N" <c:if test="${user.internalUser eq 'N'}">checked='checked'</c:if>/>
		 							否&nbsp;							    							    	
								</div>
							  </li>
						 </ul>
  					</div>   
  				</div>
 			</div>
   <!-- 搜索条件 -->
			<div class="dbzn " >
				<input id="cancel_btn" class="btn buttom1" type="button" value="取消" onclick="history.back()"/>
			</div>
    	</div>
    </div>
 </form>
  
</body>
</html>