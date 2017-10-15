<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<title>角色查看</title>
		<link href="${ctx}/static/ligerUI/skins/Aqua/css/ligerui-tree.css"
			rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/jquery/jquery.js" type="text/javascript">
</script>
		<script src="${ctx}/static/ligerUI/js/core/base.js"
			type="text/javascript">
</script>
		<script src="${ctx}/static/ligerUI/js/plugins/ligerTree.js"
			type="text/javascript">
</script>
		<script type="text/javascript">
var node;

var manager = null;

$(function ()
       { 
	       var data = [];
           <c:forEach items="${v_navigationList_role}" var="v_navigation" varStatus="status">
				data.push({ id: ${v_navigation.menuNodeId}, pid: ${v_navigation.parentNodeId}, text: '${v_navigation.menuNodeName}' });
			</c:forEach>
           $("#tree1").ligerTree({
           	 parentIcon: '',
        	 childIcon: '',
           	 treeLine:true,
          		 data: data,
          		 idFieldName :'id',
        		 parentIDFieldName :'pid',
          	 	 checkbox: false
           });
           manager = $("#tree1").ligerGetTreeManager(); 
           manager.expandAll();
           node = null;

           $("#tree1").css('width','240px'); 
});
       
</script>

	</head>
	<body>
		<c:if test="${not empty InfoMessage}">
			<div id="InfoMessage" class="alert alert-success">
				<button data-dismiss="alert" class="close">
					×
				</button>
				${InfoMessage}
			</div>
		</c:if>
		<form class="form-search" action="${ctx}/saveRole.do" id="inputForm">
			<input type="hidden" name="menuName" value="${param.menuName}" />
			<input type="hidden" name="h1_id" value="${param.h1_id}" />
			<input type="hidden" name="h1_index" value="${param.h1_index}" />
			<input type="hidden" name="h2_id" value="${param.h2_id}" />
			<input type="hidden" name="h2_index" value="${param.h2_index}" />
			<input type="hidden" name="h2_index" value="${param.h2_index}" />

			<div style="overflow: auto" id="area-overflow">
				<div class="main-box" id="area-right">

					<div class="add_top" style>
						<b></b>角色查看
						<div class="list_an">
							<button id="cancel_btn" class="btn btn-primary" type="button"
								onClick="location.href='gotoRoleList.do'">
								返回
							</button>
						</div>
					</div>

					<!-- 搜索条件 -->
					<div class="aurgcont bcnone" style="min-height: 500px;">
						<div class="row-fluid">
							<div class="span12">
								<ul>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											角色编码
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 " name="roleCode"
												id="roleCode" value="${role.roleCode}" disabled="disabled"/>
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											角色名称
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 " name="roleName"
												id="roleName" value="${role.roleName}" disabled="disabled"/>
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											角色描述
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 " name="roleDesc"
												id="roleDesc" value="${role.roleDesc}" disabled="disabled"/>
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											分配功能
										</div>
										<div class="zz_lfbox">
											<ul id="tree1" style="width: 220px;"></ul>
										</div>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!-- 搜索条件 -->
					<div class="dbzn ">
						&nbsp;
						<input id="cancel_btn" class="btn buttom1" type="button"
							value="取消" onclick="history.back()" />
					</div>
				</div>
			</div>
		</form>
		<!-- Modal -->
	</body>
</html>