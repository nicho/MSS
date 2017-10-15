<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>编辑用户</title>
<script type="text/javascript">
	$(document).ready(function() {
		    changeUserType($("#userType"));
	});

	function selectCustAcount() {
		var orgId = $('#orgId').val();
		$('#selectiframe').attr('src','${ctx}/gotoCustAccountList.do?url=gotoUserList.do&orgId='+81+'&d='+new Date().getTime());
    	$('#wDialog').window('open');
	}

	function selectPersonOrganization(url) {
		var orgId = '';
		var channelType = '';
		var departmentCode = ''; 		
		$('#selectiframe').attr('src','getPersonOrganizationView.do?displayUserAccount=Y&isShoppingGuide=Y&ignoreValid=Y&url='+url+'&departmentCode='+departmentCode+'&orgId='+orgId+'&channelType='+channelType+'&d='+new Date().getTime());
		$('#wDialog').window('open');
	}	
	
	function closeDialog(){
		$('#wDialog').window('close'); 
	}
	
	function personSetOrganization(personId,userName,departmentCode,departmentName,positionId,positionName)
	{		
		$('#personName').val(userName);
		$('#personId').val(personId);
	}
	
	function custAcountSet(custAccountId,accountName){
		$('#accountName').val(accountName);
		$('#custAccountId').val(custAccountId);
	}
	
	function commitForm(commitType){
		var form = document.forms['inputForm'];
		if(commitType == 'UPDATE') {
			form.action="${ctx}/updateUser.do";	
		} else {
			form.action="${ctx}/releaseBoundUser.do";	
		}

		$("#inputForm").submit();     
	}
	
	function chearUserForm() {
		$('#personName').val("");
		$('#personId').val("");
		$('#accountName').val("");
		$('#custAccountId').val("");
		$('#storeCode').val("");
		$('#beginDate').val("");
		$('#endDate').val("");
	}
	
	function changeUserType(userType) {
		
		if($(userType).val() == '20') {
			$("#personli").css("display","");
			$("#custli").css("display","none");
			$("#storeli").css("display","none");
		}
		
		if($(userType).val() == '30') {
			$("#personli").css("display","none");
			$("#custli").css("display","");
			$("#storeli").css("display","none");
		}
				
		if($(userType).val() == '40') {
			$("#personli").css("display","none");
			$("#custli").css("display","none");
			$("#storeli").css("display","");
		}
		
	}
	
</script>
</head>
<body> 
	<form class="form-search" action="${ctx}/updateUser.do" id="inputForm" method="post" class="form-horizontal">
	  <input type="hidden" name="userId" value="${user.userId}"/>
	
	   <div style="overflow:auto" id="area-overflow">
        <div class="main-box" id="area-right"> 
               
        <div class="add_top" style><b></b>编辑用户
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
							    	<input type="text" class="input1 required" readonly="readonly" name="userName" id="userName" value="${user.userName}"/>
							        <span>*</span>	
							    </div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">用户类型</div>
							    <div class="rgcont_rr">
							    <!-- 
							      <select type="text" name="userType" id="userType" class="select1">
							      	<oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:employee">
						            	<option value="20" <c:if test="${user.userType == '20'}">selected="selected"</c:if>>员工用户</option>
						            </oaTag:authorityCheck>
						            <oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:dealer">
						            	<option value="30" <c:if test="${user.userType == '30'}">selected="selected"</c:if>>经销商用户</option>
						            </oaTag:authorityCheck>
						            <oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:store">
						            	<option value="40" <c:if test="${user.userType == '40'}">selected="selected"</c:if>>门店用户</option>
						            </oaTag:authorityCheck>
							      </select></div>
							      
							       -->
							       <c:if test="${user.userType == '20'}">员工用户</c:if>
							       <c:if test="${user.userType == '30'}">经销商用户</c:if>
							       <c:if test="${user.userType == '40'}">门店用户</c:if>
							       
							       <input type="hidden" name="userType" id="userType" value="${user.userType}"/>
							       
							  </li>
							  <li id="personli" class="rgcont_li">
							    <div class="rgcont_wz">人员</div>
							    <div class="rgcont_rr">
							      <input type="text" class="input8 required" name="personName" id="personName" readonly="readonly" value="${user.personName}"/>
								  <button  type="button" class="chooes_3" onclick="selectPersonOrganization('gotoUserList.do')"></button>
								  <input type="hidden" class="input1 " name="personId" id="personId" value="${user.personId}"/>
							   </div>
							     <span style="color:red;font-size: 16px;margin-left: -12px;vertical-align: -6px;">*</span>
							  </li>							 
							  <li class="rgcont_li">
								 <div class="rgcont_wz">生效日期</div>
								 <div class="rgcont_rr">
								 		<input type="text" class="input1 input-large required" name="beginDate" value="${user.beginDate}" id="beginDate" onClick="WdatePicker()"/>
								    <span>*</span>	
								 </div>
								  
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">失效日期</div>
							    <div class="rgcont_rr">
							    		<input type="text" class="input1 input-large required" name="endDate" value="${user.endDate}" id="endDate" onClick="WdatePicker()"/>							    	
								   <span>*</span>	
								</div>
							  </li>
							   <input type="hidden" name="internalUser"	value="Y"/>
						 </ul>
  					</div>   
  				</div>
 			</div>
   <!-- 搜索条件 -->
			<div class="dbzn " >
				<input id="submit_btn" class="btn buttom1 " type="button" value="保存" onclick="commitForm('UPDATE');"/>&nbsp;
				<c:if test="${showRealaseFlag eq 'Y' }">
					<input id="submit_btn1" class="btn buttom1 " type="button" value="解除绑定" onclick="commitForm('RELEASE');"/>&nbsp;
				</c:if>
			</div>
    	</div>
    </div>
 </form>
 <!-- Modal -->
 	<div id="wDialog" title="选择框"  class="easyui-window" title="My Window" closed="true" style="width:880px;height:600px;padding-left: 5px;display:none;">
 		<iframe id="selectiframe" id="selectiframe" src='' style="width:840px;height:600px;"></iframe>
 	</div>   
</body>
</html>