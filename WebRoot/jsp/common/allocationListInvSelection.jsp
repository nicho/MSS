<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>经销商子库选择</title>
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
	function btn_click(secInvName,description,cust_account_id,organization_id){
		parent.setAllocationListInvSelection(secInvName,description,cust_account_id,organization_id);
		parent.closeDialog();
	}
</script>
<style>
.open_srk{width:200px;height:25px!important;overflow:hidden;border:1px solid #ccc;text-indent:0.5em;font-size:12px!important;line-height:22px;}
.open_xlk{width:200px;height:25px;overflow:hidden;border:1px solid #ccc;margin-left:8px\9;font-size:12px!important;}
</style>
</head>
	<body style="border:0px;overflow:auto;height:500px;height:480px\9;">
		<div class="add_top" >&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;经销商子库选择</div>
		<!--搜索部分begin-->	
		 <div class="row-fluid aufour mar_2"> 
		   	<form class="form-search" action="${ctx}/getAllocationListInvSelection.do"> 
				<input type="hidden" name="url" id="url" value="${param.url}"/>
			     <table  class="tableopen"  width="100%">
			    	<tr>
			    	    <td class="text_rg title_font bzh">经销商子库编码</td>
			    		<td class="text_lf">
			    		<input type="text" class="open_srk " name="secInvName" id="secInvName" value="${param.secInvName}" />  
				         </td>
			    	    <td class="text_rg title_font bzh ">经销商子库名称</td>
			    		<td class="text_lf">
			    			<input type="text" class="open_srk " name="description" id="description" value="${param.description}" />
			    		</td>
			    	</tr>
		    	</table>
		    	 <div class="rgcont_long">		 
				  	<button type="submit" class="btn buttom1">查询</button>
			   </div>
		   </form>
		 </div>
		 <!--搜索部分over-->
		 
		 <!--列表部分begin-->	
		 <div class="row-fluid aufive mar_2"> 
		   <div class="aulist_3" ><b></b>经销商子库列表</div>  
	    	<div class="table_auto scroll_auto" style="overflow-x:auto;overflow-y:hidden;">
		 		<table id="contentTable" width="90%" class="table table-striped table-bordered table-condensed table-hover ">
			    	<tr class="tbth">
			    		<td class="bzh">选择</td>
			    		<td class="bzh">经销商子库编码</td>     		
			    		<td class="bzh">经销商子库名称</td>
			    		<td class="bzh">省</td>
			    		<td class="bzh">市</td>
			    		<td class="bzh">渠道</td>
			    	</tr>
			    	<c:forEach var="list"  items="${list}" varStatus="vstatus">
				    	<tr>
				    		<td>
						    	<a onclick="btn_click('${list.secInvName}','${list.description}','${list.cust_account_id}','${list.organization_id}')">选择</a>
						    </td>
				    		<td title="${list.secInvName}">${list.secInvName}</td>
				    		<td title="${list.description}">${list.description}</td>     		
				    		<td title="${list.provinceName}">${list.provinceName}</td> 
				    		<td title="${list.districtName}">${list.districtName}</td>
				    		<td>
				    		<c:forEach items="${channellist}" var="channellist">
							<c:if test="${channellist.flexValue eq list.channelType}">${channellist.flexValuesDesc}
							</c:if>
							</c:forEach>  
							</td>
				    	</tr>
				    </c:forEach> 
			    </table>    
		   		<tags:paginationOpen/>
	    	</div>
		 </div>
	</body>
</html>