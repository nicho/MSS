<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>物料选择</title>
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
 
	function btn_click(id,code,name,uom_code_new){
		parent.priceCatalogueItemCodeSet(id,code,name,uom_code_new);
		parent.closeDialog();
	}
</script>
<style>
.open_srk{width:200px;height:25px!important;overflow:hidden;border:1px solid #ccc;text-indent:0.5em;font-size:12px!important;line-height:22px;}
.open_xlk{width:200px;height:25px;overflow:hidden;border:1px solid #ccc;margin-left:8px\9;font-size:12px!important;}
.input7_open { width:40px!important;  background-color: #ffffff;  border: 1px solid #cccccc;font-size:12px!important;height:20px!important;line-height:28px!important;}

</style>
</head>
<body style="border:0px;overflow:auto;height:400px;">
 <form id='searchForm' class="form-search" action="${ctx}/queryPriceCatalogueItemCode.do">
 	 
		<div class="add_top" style>&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;物料选择</div>
		<!--搜索部分begin-->	
		 <div class="row-fluid aufour mar_2"> 
		  
				<input type="hidden" name="url" value="${param.url}"/>
			     <table  class="tableopen"  width="100%">
			    	<tr>
			    	    <td class="text_rg title_font bzh">物料编码</td>
			    		<td class="text_lf">
			    			<input   type="text" class="open_srk  " name="item_code" id="item_code" value="${param.item_code}">
			    	    <td class="text_rg title_font bzh "> </td>
			    		<td class="text_lf">
			    			 
			    		</td>
			    	</tr>
		    	</table>
		    	 <div class="rgcont_long">		 
				  	<button type="submit" class="btn buttom1">查询</button>
			   </div>
		   
		 </div>
		 <!--搜索部分over-->
		 
		 <!--列表部分begin-->	
		 <div class="row-fluid aufive mar_2"> 
		   <div class="aulist_3" ><b></b>物料列表</div>  
	    	<div class="table_auto scroll_auto" style="overflow-x:auto;overflow-y:hidden;">
		 		<table id="contentTable" width="90%" class="table table-striped table-bordered table-condensed table-hover ">
			    	<tr class="tbth">
			    		<td class="bzh">选择</td>
			    		<td class="bzh">物料编码</td>
			    		<td class="bzh">物料名称</td>
			    		<td class="bzh">主单位</td>
			    		
			    	</tr>
			    	<c:forEach var="list"  items="${list}" varStatus="vstatus">
				    	<tr>
				    		<td>
						    	<a onclick="btn_click('${list.id}','${list.code}','${list.name}','${list.uom_code_new}')">选择</a>
						    </td>
				    		<td>${list.code}&nbsp;</td>
				    		<td>${list.name}&nbsp;</td>
				    		<td>${list.uom_code_new}&nbsp;</td>
		 
				    	</tr>
				    </c:forEach> 
			    </table>    
		   		<tags:paginationOpen/>
		   		
	    	</div>
		 </div>
		 </form>
	</body>
</html>