<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
	function selectPersonOrganization(url) {
		var orgId = '';
		var channelType = '';
		var departmentCode = ''; 		
		$('#selectiframe').attr('src','getPersonOrganizationView.do?displayUserAccount=Y&isInvalid=Y&ignoreValid=Y&url='+url+'&departmentCode='+departmentCode+'&orgId='+orgId+'&channelType='+channelType+'&isShoppingGuide=Y'+'&d='+new Date().getTime());
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

	function selectCustAcount() {
		var orgId = $('#orgId').val();
		$('#selectiframe').attr('src','${ctx}/gotoCustAccountList.do?url=gotoUserList.do&orgId=' + orgId + '&d=' + new Date().getTime());
		$('#wDialog').window('open');
	}

	function selectStore() {
		var orgId = $('#orgId').val();
		var departmentCode = encodeURI(encodeURI($('#departmentCode').val()));
		$('#selectiframe').attr('src', '${ctx}/getStoreListByUser.do?url=gotoPersonList.do&orgId='	+ orgId + '&d=' + new Date().getTime());
		$('#wDialog').window('open');
	}

	function closeDialog() {
		$('#wDialog').window('close');
	}
	function personSet(personId, userName) {
		$('#personName').val(userName);
		$('#personId').val(personId);
	}

	function custAcountSet(custAccountId, accountName) {
		$('#accountName').val(accountName);
		$('#custAccountId').val(custAccountId);
	}

	function storeSelect(storeCode, storeName, province, city) {
		$('#storeName').val(storeName);
		$('#storeCode').val(storeCode);
	}
	
	function reSetPwd(userId) {
		var parameter = {
			userId : userId
		};
		$.post('${ctx}/reSetPwd.do', parameter, function(data) {
			if (data && data == "true") {
				alert("重置密码成功");
			} else {
				alert("重置密码失败");
			}
		});
	}
	function exportUserList(){
		var personId = "";
		var custAccountId = "";
		var storeCode = "";
		if(typeof($('#personId').val()) != "undefined"){
			personId = $('#personId').val();
		}
		if(typeof($('#custAccountId').val()) != "undefined"){
			custAccountId = $('#custAccountId').val();
		}
		if(typeof($('#storeCode').val()) != "undefined"){
			storeCode = $('#storeCode').val();
		}
		
		
		window.open('${ctx}/exportUserList.do?' + 'userName='
				+ $('#userName').val() + '&pageUserType=' + $('#pageUserType').val() + '&personId='
				+ personId + '&custAccountId=' + custAccountId+ '&storeCode=' + storeCode+'&isValid='+ $('#isValid').val()	
		);
		
	}
	
	
</script>
</head>
<body>
	<form class="form-search" action="${ctx}/viewUserIndex.do" id="inputForm" name="inputForm">
		<div style="overflow:auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<!-- 搜索条件 -->
				<div class="aurgcont">
					<div class="row-fluid">
						<div class="span12">
							<ul>
								<oaTag:responsibilityChoice  sessionId="${pageContext.session.id}" url="gotoUserList.do"/>
								<li class="rgcont_li">
									<div class="rgcont_wz">用户名</div>
									<div class="rgcont_rr">
										<input type="text" class="input1 " name="userName"
											id="userName" value="${param.userName}"/>
									</div></li>
								<li class="rgcont_li">
									<div class="rgcont_wz">用户类型</div>
									<div class="rgcont_rr">
										<select type="text" name="pageUserType" id="pageUserType"
											class="select1">
											<option value="">请选择</option>
								             <oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:employee">
								            	<option value="20" <c:if test="${'20' == param.pageUserType}">selected</c:if>>员工用户</option>
								            </oaTag:authorityCheck>
								            <oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:dealer">
								            	<option value="30" <c:if test="${'30' == param.pageUserType}">selected</c:if>>经销商用户</option>
								            </oaTag:authorityCheck>
								            <oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:store">
								            	<option value="40" <c:if test="${'40' == param.pageUserType}">selected</c:if>>门店用户</option>
								            </oaTag:authorityCheck>
										</select>
									</div></li>
								<oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:employee">
								<li class="rgcont_li">
									<div class="rgcont_wz">人员</div>
									<div class="rgcont_rr">
										<input type="text" class="input8" name="personName" readonly="readonly"
											id="personName" value="${param.personName}"/> 
											<button  type="button" class="chooes_3" onclick="selectPersonOrganization('gotoUserList.do')"></button>											
											<input type="hidden"
											class="input1 " name="personId" id="personId"
											value="${param.personId}" />
									</div></li>
								</oaTag:authorityCheck>
								<oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:dealer">
								<li class="rgcont_li">
									<div class="rgcont_wz">经销商</div>
									<div class="rgcont_rr">
										<input type="text" class="input8" name="accountName" readonly="readonly"
											id="accountName" value="${param.accountName}"/>
											<button  type="button" class="chooes_3" onclick="selectCustAcount();"></button>			
											 <input
											type="hidden" class="input1 " name="custAccountId"
											id="custAccountId" value="${param.custAccountId}" />
									</div></li>
									</oaTag:authorityCheck>
								<oaTag:authorityCheck url="gotoUserList.do"	sessionId="${pageContext.session.id}" resourceCode="usertype:store">
								<li class="rgcont_li">
									<div class="rgcont_wz">门店</div>
									<div class="rgcont_rr">
										<input type="text" class="input8" name="storeName"
											readonly="readonly" id="storeName" value="${param.storeName}" />
										<button type="button" class="chooes_3"
											onclick="selectStore();"></button>
										<input type="hidden" class="input1 " name="storeCode"
											id="storeCode" value="${param.storeCode}" />
									</div>
								</li>
								</oaTag:authorityCheck>
								<li class="rgcont_li">
									<div class="rgcont_wz">用户是否有效</div>
									<div class="rgcont_rr">
										<select name="isValid" class="select1" id="isValid">
											<option value=""><spring:message code="pleaseSelect" /></option>
											<option value="Y"
												<c:if test="${'Y' eq param.isValid}">selected="selected"</c:if>>是</option>
											<option value="N"
												<c:if test="${'N' eq param.isValid}">selected="selected"</c:if>>否</option>
										</select>
									</div>
								</li>

								<li class="rgcont_long">
									<button type="submit" class="btn buttom1">查询</button>
									 <button type="button" onclick="clearForm();" class="btn buttom1">清空</button>
									</li>
									
							</ul>
						</div>
					</div>
				</div>

				<!-- 搜索条件 -->
				<ul class="zjbf">
					<oaTag:authorityCheck url="gotoUserList.do" sessionId="${pageContext.session.id}" resourceCode="user:add"> 
					<li class="zjbf_li"><button class="btn  btn-primary"
							type="button" onclick="showMenuPage2('${ctx}/gotoAddUser.do')">新增</button>
					</li>
					</oaTag:authorityCheck>
						<oaTag:authorityCheck url="gotoUserList.do" sessionId="${pageContext.session.id}" resourceCode="user:export"> 
						<li class="zjbf_li"><button class="btn  btn-primary"
						
							type="button" onclick="exportUserList();">导出</button>
					</li>
					</oaTag:authorityCheck>
				</ul>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="auhng_1">
					<tr>
						<td><b><img src="${ctx}/static/images/a_33.jpg"> </b>用户列表</td>
					</tr>
				</table>
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed table-hover">
					<tr class="tbth ">
						<td>用户名</td>
						<td>用户类型</td>
						<td>人员姓名</td>
						<td>关联经销商</td>
						<td>关联门店</td>
						<td>是否EBS用户</td>
						<td>帐号生效时间</td>
						<td>帐号失效时间</td>
						<td>操作</td>
					</tr>
					<c:forEach var="list" items="${v_userList}" varStatus="vstatus">
						<tr>
							<td>${list.userName}</td>
							<td>${list.userTypeName}</td>
							<td>${list.personName}</td>
							<td>${list.custAccountName}</td>
							<td>${list.storeCode}</td>
							<td>${list.isEbsUser}</td>
							<td>${list.beginDate}</td>
							<td>${list.endDate}</td>
							<td><a
								onclick="showMenuPage3('gotoViewUser.do?userId=${list.userId}')">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
								
								<oaTag:authorityCheck url="gotoUserList.do" sessionId="${pageContext.session.id}" resourceCode="user:edit"> 
								<c:if test="${!empty(list.org_status) && list.org_status !='' && list.org_status=='Y' && list.userType =='20' && list.person_user_type=='PERSON' && responsibilityId!='1017'}">
								<a
								onclick="showMenuPage3('gotoUpdateUser.do?userId=${list.userId}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:if test="${list.userType =='30' || list.userType =='40' || list.person_user_type=='GUIDE' || responsibilityId=='1017'}">
								<a
								onclick="showMenuPage3('gotoUpdateUser.do?userId=${list.userId}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								
								</oaTag:authorityCheck>
								
								<oaTag:authorityCheck url="gotoUserList.do" sessionId="${pageContext.session.id}" resourceCode="user:resetpwd"> 
								<c:if test="${!empty(list.org_status) && list.org_status !='' && list.org_status=='Y' && list.userType =='20' && list.person_user_type=='PERSON' && responsibilityId!='1017'}">
								<a onclick="reSetPwd('${list.userId}')">重置密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:if test="${list.userType =='30' || list.userType =='40' || list.person_user_type=='GUIDE' || responsibilityId=='1017'}">
								<a onclick="reSetPwd('${list.userId}')">重置密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								</oaTag:authorityCheck>
								
								<oaTag:authorityCheck url="gotoUserList.do" sessionId="${pageContext.session.id}" resourceCode="user:responsibility"> 
								<a
								onclick="showMenuPage3('gotoUserResponsibility.do?userId=${list.userId}')">分配职责</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</oaTag:authorityCheck>
							   <oaTag:authorityCheck url="gotoUserList.do" sessionId="${pageContext.session.id}" resourceCode="user:diaryAuthority"> 
								<c:if test="${list.userType =='20'&& list.org_status=='Y' && responsibilityId!='1017'}">
								<a
								onclick="showMenuPage3('gotoUserDiaryAuthority.do?userId=${list.userId}')">分配全局日志权限</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:if test="${list.userType =='20'&& responsibilityId=='1017'}">
								<a
								onclick="showMenuPage3('gotoUserDiaryAuthority.do?userId=${list.userId}')">分配全局日志权限</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								</oaTag:authorityCheck>
							</td>
						</tr>
					</c:forEach>
				</table>
				<tags:pagination />
			</div>
		</div>
	</form>
	<!-- Modal -->
	<div id="wDialog" class="easyui-window " title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:800px;height:500px;overflow-x:hidden;display:none;">
 		<iframe name="selectiframe" id="selectiframe" src='' style="width:840px;height:600px;border:0px;"></iframe>
 	</div>
</body>
</html>