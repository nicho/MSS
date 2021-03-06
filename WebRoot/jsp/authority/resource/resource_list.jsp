<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>资源管理</title>
<script type="text/javascript">
</script>
</head>
<body> 
	<c:if test="${not empty InfoMessage}">
		<div id="InfoMessage" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${InfoMessage}</div>
	</c:if>
	<form class="form-search" action="${ctx}/viewResourceIndex.do"  >
	  <input type="hidden" name="menuName" value="${param.menuName}"/>
	  <input type="hidden" name="h1_id" value="${param.h1_id}"/>
	  <input type="hidden" name="h1_index" value="${param.h1_index}"/>
	  <input type="hidden" name="h2_id" value="${param.h2_id}"/>
	  <input type="hidden" name="h2_index" value="${param.h2_index}"/>
	
	
	   <div style="overflow:auto" id="area-overflow">
        <div class="main-box" id="area-right">        
 <!-- 搜索条件 -->
  <div class="aurgcont"> 
  <div class="row-fluid"> 
   <div class="span12">
      <ul>
      <li class="rgcont_li">
	    <div class="rgcont_wz">资源名称</div>
	    <div class="rgcont_rr">
	   	 <input type="text" class="input1 " name="resourceName" id="resourceName" />
	   </div>
	  </li> 
      <li class="rgcont_li">
	    <div class="rgcont_wz">资源编码</div>
	    <div class="rgcont_rr">
			<input type="text" class="input1 " name="resourceCode" id="resourceCode" />
	    </div>
	  </li>
	  <li class="rgcont_li form-search">
		<button type="submit" class="btn buttom1">查询</button>	 		 
	  </li>
 </ul>
  </div>   
  </div>
 </div> 
   <!-- 搜索条件 -->
<ul class="zjbf">
  <li class="zjbf_li"><button class="btn  btn-primary" type="button" onclick="showMenuPage2('${ctx}/gotoAddResource.do')">新增</button></li>
  <li class="zjbf_li"><button class="btn  btn-primary" type="button" onclick="showMenuPage2('${ctx}/businessTripAdd.do')">导出</button></li>
</ul>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="auhng_1">
	<tr> <td><b><img src="${ctx}/static/images/a_33.jpg" ></b>资源列表</td></tr>
	</table>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover ">
    	<tr>
    		<td>资源名称</td>
    		<td>资源编码</td>
    		<td>资源描述</td>
    		<td>功能名称</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="list"  items="${v_resource_list}" varStatus="vstatus">
    		<tr>
	    		<td>${list.resourceName}</td>
	    		<td>${list.resourceCode}</td>
	    		<td>${list.resourceDesc}</td>
	    		<td>${list.menuNodeName}</td>  	  		
	    		<td>
		    		<a onclick="showMenuPage3('gotoViewResource.do?resourceId=${list.resourceId}')">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a onclick="showMenuPage3('gotoUpdateResource.do?resourceId=${list.resourceId}')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a onclick="showMenuPage3('deleteResource.do?resourceId=${list.resourceId}')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;  
	    		</td>
    		</tr>
    	</c:forEach> 
    </table>    
   <tags:pagination/> 
   </div></div>
 </form>
 
</body>
</html>