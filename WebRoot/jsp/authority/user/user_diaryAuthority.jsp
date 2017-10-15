<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<html>
<head>
<title>用户日志全局权限分配</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#add_userAuthority_But").click(function(){
		$('#contentTable_userAuthority').append($('#userAuthorityRow').children().children().html());
	});
});
function deleteRow(obj) {
	$(obj).parent().parent().remove();
}

</script>
</head>

	<body>
		<div id="errMessage">

		</div>
		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<form id="inputForm" action="${ctx}/saveUserAuthority.do"
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
										<input type="hidden" name="user_id" id="user_id" value="${user.userId}"/> 
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
								关联部门/省区查看权限
								<div class="list_an">
									<button id="add_userAuthority_But" class="btn btn-primary" type="button">
										增加
									</button>
								</div>
							</div>
							<table id="contentTable_userAuthority"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth ">
									<td>
										部门/省区
									</td>
									
									<td>
										<spring:message code='Operation' />
									</td>
								</tr>
							<c:forEach var="diary" items="${myDiaryList}" varStatus="vstatus">
								<tr class="tbth ">
									<td><select name="departmemtIds" class="select1 required"
										style="width: 400px;">
											<option value="">请选择</option>
											<c:forEach var="depart" items="${departmemtList}"
												varStatus="vstatus">
												<option value="${depart.hr_organization_id}"
													<c:if test="${depart.hr_organization_id == diary.hr_organization_id}">selected</c:if>>${depart.hr_organization_name}</option>
											</c:forEach>
									</select></td>
									<td><a onclick="deleteRow(this);">删除</a></td>
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
		
		<div id="userAuthorityRow" name="roleRow" style="display: none;">
			<table>
				<tr class="tbth">
					<td>
						<select name="departmemtIds"  class="select1 required" style="width:400px;">
				           <option value="">请选择</option>
				           <c:forEach var="departmemtList"  items="${departmemtList}" varStatus="vstatus">
				           		<option value="${departmemtList.hr_organization_id}">${departmemtList.hr_organization_name}</option>
				           </c:forEach>
				        </select>
					</td>
					<td>
						<a onclick="deleteRow(this);">删除</a>
					</td>
				</tr>
			</table>
		</div>

	</body>
</html>