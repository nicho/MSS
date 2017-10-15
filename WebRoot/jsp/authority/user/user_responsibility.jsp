<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<html>
<head>
<title>用户职责分配</title>
<script type="text/javascript">
$(document).ready(function() {
	var hanghao = $("#contentTable_responsibilitys tr").length;
	$("#add_responsibility_But").click(function(){
		/* $('#contentTable_responsibilitys').append($('#responsibilityRow').children().children().html()); */
		 $("#contentTable_responsibilitys").append(
		    		"<tr class='tbth'>"
					+"<td>"
				    +"<input type='text' class='input8 required' name='responsibilityNames'  readonly='readonly' id=responsibilityNames"+(hanghao+1)+" />"
					+'<button  type="button" class="chooes_3" onclick="selectResponsibility('+(hanghao+1)+')"></button>'					        
					+"<input type='hidden' name='responsibilityIds' id=responsibilityIds"+(hanghao+1)+" value=''/>"
					+"</td>"
					+"<td><div id=responsibilityCodes"+(hanghao+1)+"></div></td>"
					+"<td><div id=responsibilityDescs"+(hanghao+1)+"></div></td>"
					+"<td></td>"
					+"<td>"
					+"<a onclick=\"deleteRow(this,'');\" href=\"#\">删除</a>"
					+"</td>"
					+"</tr>"
		    	 );  
		        hanghao = hanghao+1;
	});
	
});

/* 
function showResponsibilityInfo(obj) {
	var parameter = {responsibilityId:$(obj).val()};
	
   	$.post('${ctx}/findResponsibilityInfo.do', parameter, function (data) {
   		
	   	var data = jQuery.parseJSON(data);
	   	
	   	if(data.success != 'true') {	    	
	    	alert(data.message);
	    	return;
	    }
	   	
	   	$(obj).parent().parent().find("td").each(function(i,j){
	   		
	   		if(i==1)
	   			$(this).html(data.responsibilityCode);
	   		if(i==2)
	   			$(this).html(data.responsibilityDesc);
	   		if(i==3)
	   			$(this).html(data.beginDate);
	   	if(i==4)
	   			$(this).html(data.endDate); 
	   	})    	
	});
} */

function deleteRow(obj,responsibilityId) {
	var deleteUserResponsibility = $("#deleteUserResponsibility").val();
	deleteUserResponsibility = deleteUserResponsibility+responsibilityId+",";
	$("#deleteUserResponsibility").val(deleteUserResponsibility);
	$(obj).parent().parent().remove();
}
var index =0;

function selectResponsibility(hanghao){
	index = hanghao;
	var url="gotoUserList.do";
	var userId = '${user.userId}';
	$('#selectiframe').attr('src','${ctx}/getUserResponsibilityList.do?isValid=Y&url='+url+'&d='+new Date().getTime());
	$('#wDialog').window('open');
	
}

function closeDialog(){
	$('#wDialog').window('close'); 
}

function responsibilitySet(responsibilityId,responsibilityCode,responsibilityName,responsibilityDesc)
{		
	$('#responsibilityNames'+index).val(responsibilityName);
	$('#responsibilityIds'+index).val(responsibilityId);
	$('#responsibilityCodes'+index).html(responsibilityCode);
	$('#responsibilityDescs'+index).html(responsibilityDesc);
}	


</script>
</head>

	<body>
		<div id="errMessage">

		</div>
		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<form id="inputForm" action="${ctx}/saveUserResponsibility.do"
					method="post" class="form-horizontal">
					<fieldset>

						<div class="add_top">
							&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;用户职责分配
							<div class="list_an">
								<button id="cancel_btn" class="btn btn-primary" type="button"
									onClick="location.href='gotoUserList.do'">
									<spring:message code='button_return' />
								</button>
							</div>
						</div>

						<!-- 第二部分 -->
						<div class="row-fluid autwo mar_2">
							<table class="table2">
								<tr>
									<td class="text_rg title_font bzh">
										用户帐号
										<input type="hidden" name="userId" id="userId" value="${user.userId}"/> 
									   	<input type="hidden" name="deleteUserResponsibility" id="deleteUserResponsibility" value=""/> 		
									</td>
									<td class="text_lf">
										${user.userName}
									</td>
									<td class="text_rg title_font bzh">
										用户类型
									</td>
									<td class="text_lf">
										<c:if test="${user.userType == '10'}">全局用户</c:if>
						    		 	<c:if test="${user.userType == '20'}">员工用户</c:if>
						    		 	<c:if test="${user.userType == '30'}">经销商用户</c:if>
						    		 	<c:if test="${user.userType == '40'}">门店用户</c:if>
						    		 	<c:if test="${user.userType == '50'}">外部引用用户</c:if>
									</td>
									<td class="text_rg title_font bzh">
										人员
									</td>
									<td class="text_lf">
										${user.personName}
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
								关联职责
								<div class="list_an">
									<button id="add_responsibility_But" class="btn btn-primary" type="button">
										增加职责
									</button>
								</div>
							</div>
							<table id="contentTable_responsibilitys"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth ">
									<td>
										职责名称
									</td>
									<td>
										职责编码
									</td>
									<td>
										职责描述
									</td>
									<td>
										分配日期
									</td>
								<!-- 	<td>
										失效日期
									</td> -->
									<td>
										<spring:message code='Operation' />
									</td>
								</tr>
								<c:forEach var="userResponsibility"  items="${userResponsibilitys}" varStatus="vstatus">
									<tr class="tbth ">
										<td>
							           	 	${userResponsibility.responsibilityName}
							           	 	<input type="hidden" name="responsibilityIds" id="responsibilityIds${vstatus.index+1}" value="${userResponsibility.responsibilityId}"/>
							           	</td>
							           	<td>
											${userResponsibility.responsibilityCode}
										</td>
										<td>
											${userResponsibility.responsibilityDesc}
										</td>
										<td>
											${userResponsibility.beginDate}
										</td>
										<%-- <td>
											${userResponsibility.endDate}
										</td> --%>
										<td>
										 <c:if test="${userResponsibility.isDisplay=='Y'}">
											<a onclick="deleteRow(this,'${userResponsibility.responsibilityId}');">删除</a>
										</c:if>	
										</td>
							        </tr>
						        </c:forEach>
							</table>
						</div>

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
		
		<div id="responsibilityRow" name="roleRow" style="display: none;">
			<table>
				<tr class="tbth">
					<td>
					<%-- 	<select name="responsibilityIds" class="select2 required" style="width:300px;" onchange="showResponsibilityInfo(this)">
				           <option value="">请选择</option>
				           <c:forEach var="responsibility"  items="${responsibilitys}" varStatus="vstatus">
				           		<option value="${responsibility.responsibilityId}">${responsibility.responsibilityName}</option>
				           </c:forEach>
				        </select> --%>
				         <input type="text" class="input8 required" name="responsibilityNames"  readonly="readonly" onchange="showResponsibilityInfo(this)"/>
							      	<button  type="button" class="chooes_3" onclick="selectResponsibility(this,'gotoUserList.do')"></button>					        
					<input type="hidden" name="responsibilityIds" value=""/>
					</td>
					<td></td>
					<td></td>
					<td></td>
				<!-- 	<td></td> -->
					<td>
						<a onclick="deleteRow(this,'');">删除</a>
					</td>
				</tr>
			</table>
		</div>
	<!-- Modal -->
<div id="wDialog" class="easyui-window " title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:800px;height:500px;overflow-x:hidden;display:none;">
	<iframe name="selectiframe" id="selectiframe" src='' style="width:840px;height:600px;border:0px;"></iframe>
</div> 
	</body>
	

</html>