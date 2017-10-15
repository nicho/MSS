<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<title>编辑角色</title>
		<link href="${ctx}/static/ligerUI/skins/Aqua/css/ligerui-tree.css"
			rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/ligerUI/js/core/base.js"
			type="text/javascript">
</script>
		<script src="${ctx}/static/ligerUI/js/plugins/ligerTree.js"
			type="text/javascript">
</script>
		<script language="javascript" type="text/javascript">
var node;

var manager = null;

$(function ()
       {
           $("#tree1").ligerTree({
           	 parentIcon: '',
        	 childIcon: '',
           	 treeLine:true,
          		 data: ${jsontree},
          	 	 checkbox: true,
          	 	 autoCheckboxEven: false
           });
           manager = $("#tree1").ligerGetTreeManager(); 
           manager.expandAll();
           node = null;

           $("#tree1").css('width','300px'); 
});

function addRole() {
	   var form = document.forms['inputForm'];    
	   var nodes = manager.getChecked();
        var funs = "";
        for (var i = 0; i < nodes.length; i++)
        {            
            funs += nodes[i].data.id + ",";
    	}
        $("#funs").val(funs);
        
        $("#inputForm").validate({

    		submitHandler : function(form) {
    			$("input[type=submit]").attr('disabled', 'true');
    			form.submit();
    		},
    		showErrors : function(errorMap, errorList) {
    			this.defaultShowErrors();
    		}
    	});
	
}
</script>
	</head>
	<body>
		<form class="form-search" action="${ctx}/updateRole.do" id="inputForm">
			<input type="hidden" id="funs" name="funs" />
			<input type="hidden" id="roleId" name="roleId" value="${role.roleId}" />
			<div style="overflow: auto" id="area-overflow">
				<div class="main-box" id="area-right">
					<div class="add_top" style>
						&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;
						编辑角色
						<div class="list_an">
							<button id="cancel_btn" class="btn btn-primary" type="button"
								onClick="location.href='gotoRoleList.do'">
								返回
							</button>
						</div>
					</div>
					<div class="zz_box" style="height: 450px;">
						<div class="zz_left" style="width:30%;">
							<div class="zz_lfbox" style="height: 440px;">
								<ul id="tree1"></ul>
							</div>
						</div>
						<div class="zz_right" style="width:69.8%;">
							<div class="zz_anbf">
								<div class="aulist_3 zz_rgtop">
									<b></b>
									角色信息
								</div>
								<li class="rgcont_long">
										<div class="rgcont_wz">
											角色编码
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="roleCode"
												id="roleCode" value="${role.roleCode}"/><span >*</span>
										</div>
									</li>
									<li class="rgcont_long">
										<div class="rgcont_wz">
											角色名称
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="roleName"
												id="roleName" value="${role.roleName}"/><span >*</span>
										</div>
									</li>
									<li class="rgcont_long">
										<div class="rgcont_wz">
											角色描述
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 " name="roleDesc"
												id="roleDesc" value="${role.roleDesc}"/>
										</div>
									</li>
							</div>
						</div>
					</div>	
				</div>
				<div class="dbzn ">
								<input id="submit_btn" class="btn buttom1 " type="submit"
									value="保存" onclick="addRole();" />
								&nbsp;
							</div>
		</form>
	</body>
</html>