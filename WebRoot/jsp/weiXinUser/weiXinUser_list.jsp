<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>微信人员同步</title> 
<script type="text/javascript">
	$(document).ready(function() {
		 

	}); 
	function addWeiXinUser(orgName,departmentName,userName,userAccount,positionName,personIndexId,personId,obja)
	{
		if(window.confirm("确认同步该用户吗？")) {  
			obja.removeAttribute('onclick');   
 		   $.ajax({
					type:"POST",
					url:"${ctx}/addWeiXinUser.do",
					data:{orgName:orgName,departmentName:departmentName,userName:userName,userAccount:userAccount,positionName:positionName,personId:personId},
					success:function(data){ 
						$('#'+personIndexId).html(data);
					}
			   });		   
 	   }
	}
	function deleteWeiXinUser(userAccount,orgName,obja)
	{
		if(window.confirm("确认删除该用户吗？")) {  
			obja.removeAttribute('onclick');   
 		   $.ajax({
					type:"POST",
					url:"${ctx}/deleteWeiXinUser.do",
					data:{userAccount:userAccount,orgName:orgName},
					success:function(data){ 
						$('#'+personIndexId).html(data);
					}
			   });		   
 	   }
	}
	
</script>
</head> 
<body>
	<form class="form-search" action="${ctx}/queryWeiXinUserListView.do" id="inputForm" name="inputForm">
		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<!-- 搜索条件 -->
				<div class="aurgcont">
					<div class="row-fluid">
						<div class="span12">
							<ul>
							<oaTag:responsibilityChoice sessionId="${pageContext.session.id}" url="weiXinUserListView.do" />
								<li class="rgcont_li">
									<div class="rgcont_wz">人员姓名</div>
									<div class="rgcont_rr">
										<input type="text" class="input1 " name="userNameSelect" id="userNameSelect" value="${param.userNameSelect}">
									</div>
								</li> 
								<li class="rgcont_li">
									<div class="rgcont_wz">部门/省区</div>
									<div class="rgcont_rr">
										<select name="departmentCode" id="departmentCode" class="open_xlk">
											<option value="">请选择</option>
											<c:forEach var="list" items="${departmemtOrganizationList}" varStatus="vstatus">
												<option value="${list.hrOrganizationId}" <c:if test="${param.departmentCode==list.hrOrganizationId}">selected='selected'</c:if>>${list.hrOrganizationName}</option>
											</c:forEach>
										</select>
									</div>
								</li>
							  <li class="rgcont_li">
									<div class="rgcont_wz">状态</div>
									<div class="rgcont_rr">
										<select name="status" id="status" class="open_xlk">
											<option value="">请选择</option>
											<option value="Y" <c:if test="${param.status=='Y'}">selected='selected'</c:if>>已同步</option>
											<option value="N" <c:if test="${param.status=='N'}">selected='selected'</c:if>>未同步</option> 
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
					<li class="zjbf_li"> </li>
				</ul>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="auhng_1">
					<tr>
						<td><b><img src="${ctx}/static/images/a_33.jpg"> </b>用户列表</td>
					</tr>
				</table>
				<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
					<tr class="tbth ">
						<td>序号</td>
						<td>事业部</td> 
						<td>部门/省区</td>
						<td>人员姓名</td>
						<td>人员账号</td>
						<td>职位</td>
						<td>邮箱</td>
						<td>手机号</td>
						<td>性别</td>
						<td>微信帐号</td>
						<td>操作</td>

					</tr>
					<c:forEach var="list" items="${personList}" varStatus="vstatus">
						<tr>
							<td>${vstatus.index+1}</td>
							<td>${list.orgName}&nbsp;</td> 
							<td>${list.departmentName}&nbsp;</td>
							<td>${list.userName}&nbsp;</td>
							<td>${list.userAccount}&nbsp;</td>
							<td>${list.positionName}&nbsp;</td>
							<td>${list.emailAddress}&nbsp;</td>
							<td>${list.mobilePhone}&nbsp;</td>
							<td><c:if test="${list.sex eq 'M'}">男</c:if><c:if test="${list.sex eq 'F'}">女</c:if>&nbsp;</td>
							<td><c:if test="${weixinUserMap[list.userAccount]!=null}"><font color="red">已导入</font></c:if>
								 <font color="red" id="person${vstatus.index+1}"></font>&nbsp;</td>
							<td>
							<c:if test="${weixinUserMap[list.userAccount]==null}">
							<a href="#" onclick="addWeiXinUser('${list.orgName}','${list.departmentName}','${list.userName}','${list.userAccount}','${list.positionName}','person${vstatus.index+1}','${list.personId}',this)">同步</a>
							</c:if>
							<c:if test="${weixinUserMap[list.userAccount]!=null}">
							<a href="#" onclick="deleteWeiXinUser('${list.userAccount}','${list.orgName}',this)">删除</a>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</table> 
			</div>
		</div>
	</form>

</body>





</html>