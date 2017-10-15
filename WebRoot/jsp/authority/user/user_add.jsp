<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>新增用户</title>
<script type="text/javascript">
	$(document).ready(function() {
		    changeUserType($("#userType"));
			$("#userName").focus();
			$("#inputForm").validate({
				rules: {
					userName: {
						remote: "${ctx}/checkUserName.do"
					}
				},
				messages: {
					userName: {
						remote: "用户登录名已存在"
					}
				}
			});
	});
	
	function selectCustAcount() {
		var orgId = $('#orgId').val();
		$('#selectiframe').attr('src','${ctx}/gotoCustAccountList.do?url=gotoUserList.do&orgId='+81+'&d='+new Date().getTime());
    	$('#wDialog').window('open');
	}

	//function selectPerson() {
	//	var orgId = $('#orgId').val();
	//	var channelType = $('#channelType').val();
	//	var departmentCode = encodeURI(encodeURI($('#departmentCode').val()));
	//	var personId  = $('#personId').val();
	//	$('#selectiframe').attr('src','${ctx}/gotoPersonList.do?url=gotoUserList.do&province='+departmentCode+'&orgId='+81+'&channelType='+channelType+'&d='+new Date().getTime());
    //	$('#wDialog').window('open');
	//}
	
	
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
	<form class="form-search" action="${ctx}/saveUser.do" id="inputForm" >
	
	   <div style="overflow:auto" id="area-overflow">
        <div class="main-box" id="area-right"> 
               
        <div class="add_top" style><b></b>新增用户
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
							    	<input type="text" class="input1 required" name="userName" id="userName"/>
							    <span>*</span>	
							    </div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">用户类型</div>
							    <div class="rgcont_rr">
							      <select type="text" name="userType" id="userType" class="select1">
							        <oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:employee">
						            	<option value="20">员工用户</option>
						            </oaTag:authorityCheck>						    
							      </select></div>
							  </li>
							  <li id="personli" class="rgcont_li">
							    <div class="rgcont_wz">人员</div>
							    <div class="rgcont_rr">
							      <input type="text" class="input8 required" name="personName" id="personName" readonly="readonly"/>
							      	<button  type="button" class="chooes_3" onclick="selectPersonOrganization('gotoUserList.do')"></button>	
								  <input type="hidden" class="input1 " name="personId" id="personId"/>
							   </div>
							   <span style="color:red;font-size: 16px;margin-left: -12px;vertical-align: -6px;">*</span>
							  </li>
							  <li class="rgcont_li">
								 <div class="rgcont_wz">生效日期</div>
								 <div class="rgcont_rr">
								 		<input type="text" class="input1 input-large required" name="beginDate" id="beginDate" value="${beginTime}" onClick="WdatePicker({onpicked:function(){getDiaryWeek()},startDate:'%y-%M-01',
								    		 dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"/>
								  <span>*</span>	
								 </div>
							  </li>
							  <li class="rgcont_li">
							    <div class="rgcont_wz">失效日期</div>
							    <div class="rgcont_rr">
							    		<input type="text" class="input1 input-large required" name="endDate" id="endDate" value="${endTime}"  onClick="WdatePicker({onpicked:function(){},startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd',
							    			alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'beginDate\')}'})"/>							    	
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
				<input id="submit_btn" class="btn buttom1 " type="submit" value="保存"/>&nbsp;	
				<input id="cancel_btn" class="btn buttom1" type="button" value="取消" onclick="history.back()"/>
			</div>
    	</div>
    </div>
 </form>
<!-- Modal -->
<div id="wDialog" class="easyui-window " title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:800px;height:500px;overflow-x:hidden;display:none;">
	<iframe name="selectiframe" id="selectiframe" src='' style="width:840px;height:600px;border:0px;"></iframe>
</div> 
</body>
</html>