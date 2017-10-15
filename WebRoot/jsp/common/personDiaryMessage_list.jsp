<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>人员选择</title>
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/json2.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_cookie_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.SuperSlide.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/js/pagination.js"></script>

<script type="text/javascript">
	/**人员编号、人员姓名、部门（省区）、岗位ID、岗位名称***/
	function btn_click(personId,userName,departmentCode,departmentName,positionId,positionName,jobId,orgId,orgName){
		parent.personSet(personId,userName,departmentCode,departmentName,positionId,positionName,jobId,orgId,orgName);
		parent.closeDialog();
	}
</script>
<style>
.open_srk{width:200px;height:25px!important;overflow:hidden;border:1px solid #ccc;text-indent:0.5em;font-size:12px!important;line-height:22px;}
.open_xlk{width:200px;height:25px;overflow:hidden;border:1px solid #ccc;margin-left:8px\9;font-size:12px!important;}
</style>
</head>
	<body style="border:0px;overflow:auto;height:500px;height:480px\9;">
		<div class="add_top">&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;<spring:message code='position_select'/></div> 
		 <!--列表部分begin-->	
		 <div class="row-fluid aufive mar_2"> 
		   <div class="aulist_3" ><b></b><spring:message code='position_list'/></div>  
	    	<div class="table_auto scroll_auto" style="overflow-x:auto;overflow-y:hidden;">
		 		<table id="contentTable" width="90%" class="table table-striped table-bordered table-condensed table-hover ">
			    	<tr class="tbth">
			    		 <td class="bzh"><spring:message code='select'/></td>
			    		  <td class="bzh"><spring:message code='bus_department'/></td>
				    	  <td class="bzh"><spring:message code='channcel'/></td>
				    	  <td class="bzh"><spring:message code='Department'/></td>
				    	  <td class="bzh"><spring:message code='person_name'/></td>     		
				    	  <td class="bzh"><spring:message code='person_account'/></td>
				    	  <td class="bzh"><spring:message code='position'/></td>
				    	  <td class="bzh"><spring:message code='primaryFlag'/></td>
			    	</tr>
			    	<c:forEach var="list"  items="${list}" varStatus="vstatus">
				    	<tr>
				    		<td>
						     <a onclick="btn_click('${list.personId}','${list.userName}','${list.departmentCode}','${list.departmentName}','${list.positionId}','${list.positionName}','${list.jobId}','${list.orgId}','${list.orgName}')">选择</a>
						    </td>
				    		<td title="${list.orgName}">${list.orgName}</td>
				    		<td title="${list.channelName}">${list.channelName}</td>
				    		<td title="${list.departmentName}">${list.departmentName}</td>
				    		<td title="${list.userName}">${list.userName}</td>
				    		<td title="${list.userAccount}">${list.userAccount}</td>     		
				    		<td title="${list.positionName}">${list.positionName}</td>
				    		<td title="${list.primaryFlag}">${list.primaryFlag}</td>
				    	</tr>
				    </c:forEach> 
			    </table>    
		   		<tags:paginationOpen/>
	    	</div>
		 </div>
	</body>
</html>