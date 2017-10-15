<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<html>
<head>
<title><spring:message code='Travel_list' /></title>
<script type="text/javascript">
$(document).ready(function() {
	$("#add_role_But").click(function(){
		$('#contentTable_roles').append($('#roleRow').children().children().html());
	});
	
	$("#add_profile_But").click(function(){ 
		$('#contentTable_profiles').append($('#profileRow').children().children().html());
	});
	
	$("#add_responsibilityInvOrg_But").click(function(){ 
		$('#contentTable_responsibilityInvOrg').append($('#responsibilityInvOrgRow').children().children().html());
	});
	
});

function showResponsibilityInv(obj) {
	var parameter = $(obj).val();
	
	 $(obj).parent().parent().find("td").each(function(i,j){
	   		if(i==1)
	   			$(this).html(parameter);
				   		
	 })
}

function showProfileInfo(obj) {
	var parameter = {profileId:$(obj).val()};
	
    $.post('${ctx}/findProfileInfo.do', parameter, function (data) {
    	
	    var data = jQuery.parseJSON(data);
	    
	    if(data.success != 'true') {	    	
	    	alert(data.message);
	    	return;
	    }
	    
	    $(obj).parent().parent().find("td").each(function(i,j){
	   		if(i==1)
	   			$(this).html(data.profileCode);
	   		if(i==2)
	   			$(this).html(data.profileDesc);
	   		if(i==3){
	   			var a_ =  $(this).find("select").first();
	   			
	   			a_.empty();
	   			
	   			$.each(data.profileVal,function(idx,item){   
				   $(a_).append($("<option/>").text(item.text).attr("value",item.code));   
				})
				
				a_.show();
	   		}		   		
	   	})    	
	});
}

function showRoleInfo(obj,roleIds) {
	var parameter = {roleId:roleIds};
	
   	$.post('${ctx}/findRoleInfo.do', parameter, function (data) {
   		
	   	var data = jQuery.parseJSON(data);
	   	
	   	if(data.success != 'true') {	    	
	    	alert(data.message);
	    	return;
	    }
	   	
	   	$(obj).parent().parent().find("td").each(function(i,j){
	   		
	   		if(i==1)
	   			$(this).html(data.roleCode);
	   		if(i==2)
	   			$(this).html(data.roleDesc);	   		
	   	})    	
	});
}

function deleteRow(obj) {
	$(obj).parent().parent().remove();
}
var roleObj;
function getAllRoleInfo(obj) {
		roleObj = obj;
		$('#selectiframe').attr(
				'src','getAllRoleInfo.do?&d='
						+ new Date().getTime());
		$('#wDialog').window('open');
}
function roleInfoSet(roleId,roleName){
	$(roleObj).parent().find("input[name=roleIds]").val(roleId);
	$(roleObj).parent().find("input[name=roleNames]").val(roleName);
	showRoleInfo(roleObj,roleId);
}
function closeDialog() {
		$('#wDialog').window('close');
}
</script>
</head>

	<body>
		<div id="errMessage">

		</div>
		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<form id="inputForm" action="${ctx}/addResponsibility.do"
					method="post" class="form-horizontal">
					<fieldset>

						<div class="add_top">
							&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;新增职责
							<div class="list_an">
								<button id="cancel_btn" class="btn btn-primary" type="button"
									onClick="location.href='gotoResponsibilityList.do'">
									<spring:message code='button_return' />
								</button>
							</div>
						</div>

						<!-- 第二部分 -->
						<div class="row-fluid autwo mar_2">
							<table class="table2">
								<tr>
									<td class="text_rg title_font bzh">
										职责名称
									</td>
									<td class="text_lf">
										<input type="text" class="input1 required"
											id="responsibilityName" name="responsibilityName" value="" />
									</td>
									<td class="text_rg title_font bzh">
										职责编码
									</td>
									<td class="text_lf">
										<input type="text" class="input1 required"
											id="responsibilityCode" name="responsibilityCode" value="" />
									</td>
									<td class="text_rg title_font bzh">
										职责描述
									</td>
									<td class="text_lf">
										<input type="text" class="input1"
											id="responsibilityDesc" name="responsibilityDesc" value="" />
									</td>
								</tr>
								<tr>
									<td class="text_rg title_font bzh">
										生效日期
									</td>
									<td class="text_lf">
										<input type="text" class="input1 input-large required" name="beginDate" id="beginDate" onClick="WdatePicker()"/>
									</td>
									<td class="text_rg title_font bzh">
										失效日期
									</td>
									<td class="text_lf">
										<input type="text" class="input1 input-large required" name="endDate" id="endDate" onClick="WdatePicker()"/>		
									</td>
									<td class="text_rg title_font bzh">
									</td>
									<td class="text_lf">
									</td>
								</tr>
							</table>
						</div>
						<!-- 第二部分 -->
						<!-- 列表部分begin -->

						<!--   增加角色       -->

						<div class="list_box mar_2">
							<div class="aulist_3">
								<b></b>
								关联角色
								<div class="list_an">
									<button id="add_role_But" class="btn btn-primary" type="button">
										增加角色
									</button>
								</div>
							</div>
							<table id="contentTable_roles"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth ">
									<td>
										角色名称
									</td>
									<td>
										角色编码
									</td>
									<td>
										角色描述
									</td>
									<td>
										<spring:message code='Operation' />
									</td>
								</tr>
							</table>
						</div>

						<!--    增加prifile                                  -->

						<div class="list_box mar_2">
							<div class="aulist_3">
								<b></b>
								关联Profile
								<div class="list_an">
									<button id="add_profile_But" class="btn btn-primary"
										type="button">
										增加profile
									</button>
								</div>
							</div>
							<table id="contentTable_profiles"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth">
									<td>
										profile名称
									</td>
									<td>
										profile编码
									</td>
									<td>
										profile描述
									</td>
									<td>
										profile值
									</td>
									<td>
										<spring:message code='Operation' />
									</td>
								</tr>
							</table>
						</div>
						
						<!--    增加 库存组织                               -->

						<div class="list_box mar_2">
							<div class="aulist_3">
								<b></b>
								关联库存组织
								<div class="list_an">
									<button id="add_responsibilityInvOrg_But" class="btn btn-primary"
										type="button">
										增加库存组织
									</button>
								</div>
							</div>
							<table id="contentTable_responsibilityInvOrg"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth">
									<td>
										库存组织
									</td>
									<td>
										编码
									</td>
									<td>
										<spring:message code='Operation' />
									</td>
								</tr>
							</table>
						</div>
						<!-- 列表部分over -->

						<div class="dbzn ">
							<input id="submit_btn1" class="btn buttom1 " type="submit"
								value="<spring:message code='Save'/>"/>
							&nbsp;
							&nbsp;
							<input id="cancel_btn" class="btn buttom1" type="button"
								value="<spring:message code='Cancel'/>" onclick="history.back()" />
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		
		<div id="roleRow" name="roleRow" style="display: none;">
			<table>
				<tr class="tbth">
					<td>
						<input type="hidden" class="input8" name="roleIds" UNSELECTABLE="on" id="roleIds" value=""/>
						<input type="text" class="input8" name="roleNames" UNSELECTABLE="on" id="roleNames" value=""/>
						<button style="width: 16%;" type="button" class="chooes_3" onclick="getAllRoleInfo(this)"></button>
					</td>
					<td></td>
					<td></td>
					<td>
						<a onclick="deleteRow(this);">删除</a>
					</td>
				</tr>
			</table>
		</div>
		

		<div id="profileRow" name="profileRow" style="display: none;">
			<table>
				<tr class="tbth">
					<td>
						<select name="profileIds" class="select1 required" onchange="showProfileInfo(this)">
				           <option value="">请选择</option>
				           <c:forEach var="profile"  items="${profiles}" varStatus="vstatus">
				           		<option value="${profile.profileId}">${profile.profileName}</option>
				           </c:forEach>
				        </select>
					<td></td>
					<td></td>					
					<td>
						<select name="profileVals" class="select1 required" style="display: none;">
				        </select>
					</td>
					<td>
						<a onclick="deleteRow(this);">删除</a>
					</td>
				</tr>
			</table>		
		</div>

		<div id="responsibilityInvOrgRow" name="responsibilityInvOrgRow" style="display: none;">
			<table>
				<tr class="tbth">
					<td width="30%">
						<select name="responsibilityInvOrgs" class="select3 required" onchange="showResponsibilityInv(this)">
				           <option value="">请选择</option>
				           <c:forEach var="responsibilityInvList"  items="${responsibilityInvList}" varStatus="vstatus">
				           		<option value="${responsibilityInvList.invOrganizationId}">${responsibilityInvList.invOrganizationName}</option>
				           </c:forEach>
				        </select>
					<td></td>
					<td>
						<a onclick="deleteRow(this);">删除</a>
					</td>
				</tr>
			</table>		
		</div>
		
		<!-- Modal -->
		<div id="wDialog" class="easyui-window " title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 812px; height: 500px; overflow-x: hidden; display: none;">
			<iframe id="selectiframe" id="selectiframe" src='' style="width: 800px; height: 450px; border: 0 none; overflow: auto;"></iframe>
		</div>
	</body>
</html>