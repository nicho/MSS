<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>角色管理</title>
<script language="javascript" type="text/javascript">

function downFile(){
	
    window.open('${ctx}/exportRoleList.do?roleName='+ $('#roleName').val()
	+'&roleCode='+ $('#roleCode').val()		
	);
	
}

</script>
</head>
<body> 
	<form class="form-search" action="${ctx}/viewRoleIndex.do"  >

	   <div style="overflow:auto" id="area-overflow">
        <div class="main-box" id="area-right">        
 <!-- 搜索条件 -->
  <div class="aurgcont"> 
  <div class="row-fluid"> 
   <div class="span12">
      <ul>
	 <oaTag:responsibilityChoice  sessionId="${pageContext.session.id}" url="gotoRoleList.do"/>
      <li class="rgcont_li">
	    <div class="rgcont_wz">角色名称</div>
	    <div class="rgcont_rr">
			<input type="text" class="input1 " name="roleName" id="roleName"  value="${param.roleName}"/>
	    </div>
	  </li>
	  <li class="rgcont_li">
	    <div class="rgcont_wz">角色编码</div>
	    <div class="rgcont_rr">
			<input type="text" class="input1 " name="roleCode" id="roleCode" value="${param.roleCode}"/>
	    </div>
	  </li>

	  <li class="dbzn ">	  	
			<button type="submit" class="btn buttom1">查询</button>	 		 
	  </li>
 </ul>
  </div>   
  </div>
 </div> 
   <!-- 搜索条件 -->
<ul class="zjbf">
  <li class="zjbf_li"><button class="btn  btn-primary" type="button" onclick="showMenuPage2('${ctx}/gotoAddRole.do')">新增</button></li>
  <li class="zjbf_li"><button class="btn  btn-primary" type="button" onclick="downFile()">导出</button></li>
</ul>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="auhng_1">
	<tr> <td><b><img src="${ctx}/static/images/a_33.jpg" ></b>角色列表</td></tr>
	</table>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover ">
    	<tr>
    		<td>角色名称</td>
    		<td>角色编码</td>
    		<td>角色描述</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="list"  items="${v_roleList}" varStatus="vstatus">
    		<tr>
	    		<td>${list.roleName}</td>
	    		<td>${list.roleCode}</td>
	    		<td>${list.roleDesc}</td>  		
	    		<td>
		    		<a onclick="showMenuPage3('gotoViewRole.do?roleId=${list.roleId}')">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a onclick="showMenuPage3('gotoUpdateRole.do?roleId=${list.roleId}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a onclick="showMenuPage3('deleteRole.do?roleId=${list.roleId}')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp; 
	    		</td>
    		</tr>
    	</c:forEach> 
    </table>    
   <tags:pagination/> 
   </div></div>
 </form>
 
</body>
</html>