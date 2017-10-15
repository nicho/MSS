<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>日志阅读人员</title>
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

<!-- <script type="text/javascript">
	function btn_click(){
		parent.closeDialog2();
	}
</script> -->
<style>
.open_srk{width:200px;height:25px!important;overflow:hidden;border:1px solid #ccc;text-indent:0.5em;font-size:12px!important;line-height:22px;}
.open_xlk{width:200px;height:25px;overflow:hidden;border:1px solid #ccc;margin-left:8px\9;font-size:12px!important;}
</style>
</head>
	<body style="border:0px;overflow:auto;height:500px;height:480px\9;">
			<input type="hidden" id="diaryId" class="input1" name="dId" value="${dId}"/>
			<div class="add_top" style>&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;日志阅读人员</div>
			 <!--列表部分begin-->	
			 <div class="row-fluid aufive mar_2"> 
			   <div class="aulist_3" ><b></b></div>  
		    	<div class="table_auto scroll_auto" style="overflow-x:auto;overflow-y:hidden;">
			 		<table id="contentTable" width="90%" class="table table-striped table-bordered table-condensed table-hover ">
				    	<tr class="tbth">
				    		<td class="bzh">人员</td>
				    		<td class="bzh">阅读时间</td>
				    		
				    	</tr>
				    	<c:forEach var="list"  items="${readPersonList}" varStatus="vstatus">
					    	<tr>
					    		<td>${list.userName}</td>
					    		<td>${list.creationDateStr}</td>
					    	</tr>
					    </c:forEach> 
				    </table>    
			   		<tags:paginationOpen/>
		    	</div>
			 </div>
	</body>
</html>