<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
$(document).ready(function() {
	
	if($("#ignoreJob").val()=="Y")
	{
		$("#ignoreJobTr").attr("style","");
	}
	if(typeof($("#selectPersonItemIdList").val()) != "undefined" && $("#selectPersonItemIdList").val()!='' && $("#selectPersonItemIdList").val()!=null) 
	{
		var personIds=$("#selectPersonItemIdList").val(); 
		var arrayObj = personIds.split(","); 
		for(var i=0;i<arrayObj.length;i++)
		{
			$("#"+arrayObj[i]).attr("checked","true");
		}
	}
});
	function btn_click(personId,userName,departmentCode,departmentName,positionId,positionName,userId){
	    //输出选中的值   
		parent.personSetSingle10(personId,userName,departmentCode,departmentName,positionId,positionName,userId);
		parent.closeDialog(); 
	}
	
	//去重复数组
	function unique(data){
	data = data || [];
	var a = {};
	len = data.length;
	for (var i=0; i<len;i++){
	var v = data[i];
	if (typeof(a[v]) == 'undefined'){
	a[v] = 1;
	}
	};
	data.length=0;
	for (var i in a){
	data[data.length] = i;
	}
	return data;
	} 
			
	 
	function selectPersonItem(obj)
	{
		if(obj.checked==true)
		{
			$('#selectPersonItemIdList').val($('#selectPersonItemIdList').val()+obj.id+",");
			$('#selectPersonItemNameList').val($('#selectPersonItemNameList').val()+obj.value+",");
		}else
		{
			$('#selectPersonItemIdList').val($('#selectPersonItemIdList').val().replace(obj.id+",",""));
			$('#selectPersonItemNameList').val($('#selectPersonItemNameList').val().replace(obj.value+",",""));
		}
	}
</script>
<style type="text/css">
input{width:10px;} 
.open_srk{width:200px;height:25px!important;overflow:hidden;border:1px solid #ccc;text-indent:0.5em;font-size:12px!important;line-height:22px;}
.open_xlk{width:200px;height:25px;overflow:hidden;border:1px solid #ccc;margin-left:8px\9;font-size:12px!important;}
.input7_open { width:40px!important;  background-color: #ffffff;  border: 1px solid #cccccc;font-size:12px!important;height:20px!important;line-height:28px!important;}

</style>
</head>
<body style="border:0px;overflow:auto;height:400px;">
	
	
	   <div   id="area-overflow">
        <div class="main-box" id="area-right">
        	 <div class="add_top" style><b></b>人员选择
			     <div class="list_an">
			     </div>
			  </div> 
	 
   
   <!--搜索部分begin-->	
		 <div class="row-fluid aufour mar_2"> 
		   <form id='searchForm' class="form-search" action="${ctx}/viewPersonSingleList.do">
				<input type="hidden" name="url" value="${param.url}"/>
				<input type="hidden" name="ignoreJob" id="ignoreJob" value="${ignoreJob}"/>
				<input type="hidden" name="ignoreOrg" value="${param.ignoreOrg}"/>
				<input type="hidden" name="ignorePositionRelation" value="${param.ignorePositionRelation}"/>
				<input type="hidden" name="levelTypeUp" value="${param.levelTypeUp}"/>
				<input type="hidden" name="levelTypeDown" value="${param.levelTypeDown}"/>
				<input type="hidden" name="ignoreValid" value="${param.ignoreValid}"/>
				<input type="hidden" id="selectPersonItemIdList" name="selectPersonItemIdList" value="${param.selectPersonItemIdList}"/>
				<input type="hidden" id="selectPersonItemNameList" name="selectPersonItemNameList" value="${param.selectPersonItemNameList}"/>
			     <table  class="tableopen"  width="100%">
			    	<tr>
			    	    <td class="text_rg title_font bzh">人员姓名</td>
			    		<td class="text_lf">
			    			<input  type="text" class="open_srk " name="userNameSelect" id="userNameSelect" value="${param.userNameSelect}">
			    	    <td class="text_rg title_font bzh ">部门/省区</td>
			    		<td class="text_lf">
			    			<select name="departmentCode" id="departmentCode" class="open_xlk">
						           <option value="">请选择</option>
						           <c:forEach var="list"  items="${departmemtOrganizationList}" varStatus="vstatus">
						           		<option value="${list.hrOrganizationId}" <c:if test="${param.departmentCode==list.hrOrganizationId}">selected='selected'</c:if>>${list.hrOrganizationName}</option>
						           	</c:forEach>
					          </select>
			    		</td>
			    	</tr>
			     <tr id="ignoreJobTr" style="display: none;">
			    	    <td class="text_rg title_font bzh">职务</td>
			    		<td class="text_lf"><select id="jobIdStr" name="jobIdStr"
								class="open_xlk">
									<option value="">请选择</option>
									<c:forEach items="${jobList}" var="jobStr">
										<option value="${jobStr.jobId}"
											<c:if test="${jobStr.jobId eq param.jobIdStr}">selected</c:if>>${jobStr.jobName}</option>
									</c:forEach>
							</select><td class="text_rg title_font bzh "> </td>
			    		<td class="text_lf">
			    			 
			    		</td>
			    	</tr>
			    	
			    	
		    	</table>
		    	 <div class="rgcont_long">		 
				  	<button type="submit" class="btn buttom1">查询</button>
			   </div>
		   </form>
		 </div>
		 <!--搜索部分over-->

		 <div class="row-fluid aufive mar_2"> 
		   <div class="aulist_3" ><b></b>人员列表</div>  
	    	<div class="table_auto scroll_auto" style="overflow-x:auto;overflow-y:hidden;">
	 <table id="contentTable" width="90%" class="table table-striped table-bordered table-condensed table-hover ">
			    	<tr class="tbth">
			    		<td class="bzh">选择</td>
			    		<td class="bzh">事业部</td>
			    		<td class="bzh">渠道</td>
			    		<td class="bzh">部门/省区</td>
			    		<td class="bzh">人员姓名</td>     		
			    		<td class="bzh">职位</td>
			    		
			    	</tr>
	 
    	<c:forEach var="list"  items="${personList}" varStatus="vstatus">
    	<tr>
    		<td width="10px;">
 			<a onclick="btn_click('${list.personId}','${list.userName}','${list.departmentCode}','${list.departmentName}','${list.positionId}','${list.positionName}','${list.userId}')">选择</a>
    		</td>
    		<td>${list.orgName}&nbsp;</td>
    		<td>${list.channelName}&nbsp;</td>
    		<td>${list.departmentName}&nbsp;</td>
    		<td>${list.userName}&nbsp;</td>
    		<td>${list.positionName}&nbsp;</td> 
    	</tr>
    	</c:forEach> 
    </table>    
   <tags:paginationOpen /> 
   </div>
		 </div>
</body>
</html>