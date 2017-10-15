<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>产品选择</title>
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/json2.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_cookie_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.SuperSlide.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/js/pagination.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("#chk_all").click(function(){
		 
		if($("#selectPersonItemIdList").val().split(",").length>20)
		{
			alert("当次选择数量最大为20!");
			this.checked=false;
		 	return;
		}
		
		 $("input[name='chk_list']").prop("checked",$(this).prop("checked"));
		 var arrChk=$("input[name='chk_list']"); 
		 $(arrChk).each(function(){ 
			 selectPersonItem(this);
		 }); 
 });
 
	if(typeof($("#selectPersonItemIdList").val()) != "undefined" && $("#selectPersonItemIdList").val()!='' && $("#selectPersonItemIdList").val()!=null) 
	{
		var personIds=$("#selectPersonItemIdList").val(); 
		var arrayObj = personIds.split(","); 
		for(var i=0;i<arrayObj.length;i++)
		{
			$("#"+arrayObj[i]).attr("checked","true");
		}
	}
	
	$("#searchForm").validate( {
		submitHandler : function(form) { 
		 
			var pageNumTag=$("#pageNumTag").val();
			 if(typeof(pageNumTag) != "undefined")
			{
				 $("#pageNumTag").val("1");
			}
			form.submit();
		}
	});
});
	 
	 
	function ObjStory(id,name,score,oid,uom,uomname){ //use constructor 
		this.id=id;
		this.score=score;
		this.uom=uom;
		this.oid=oid;
		this.name=name; 
		this.uomname=uomname; 
		return this; 
	 } 
	
	function select_click(){
		 //输出选中的值   
		var Arrayids=[];
		if(typeof($("#selectPersonItemIdList").val()) != "undefined" && $("#selectPersonItemIdList").val()!='' && $("#selectPersonItemIdList").val()!=null) 
			{
				 idstr = $("#selectPersonItemIdList").val().split(","); 
				 namestr = $("#selectPersonItemNameList").val().split(","); 
			 
				idstr = unique(idstr);
				for(var j=0;j<idstr.length;j++){
					if(idstr[j]!='') 
					{ 
						var ns=namestr[j].split("|"); 
					 
						Arrayids.push(new ObjStory(idstr[j],ns[0],ns[1],ns[2],ns[3],ns[4]));
					}
				} 
				  
			}   
		 
			 parent.scoreSetExchangeItem(Arrayids);
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
		if($("#selectPersonItemIdList").val().split(",").length>20)
		{
			alert("当次选择数量最大为20!");
			obj.checked=false;
		}
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
<style>
.open_srk{width:200px;height:25px!important;overflow:hidden;border:1px solid #ccc;text-indent:0.5em;font-size:12px!important;line-height:22px;}
.open_xlk{width:200px;height:25px;overflow:hidden;border:1px solid #ccc;margin-left:8px\9;font-size:12px!important;}
.input7_open { width:40px!important;  background-color: #ffffff;  border: 1px solid #cccccc;font-size:12px!important;height:20px!important;line-height:28px!important;}

</style>
</head>
<body style="border:0px;overflow:auto;height:400px;">
 <form id='searchForm' class="form-search" action="${ctx}/getCuxCdmTransferItemList.do">
 <input type="hidden" name="organization_id" value="${param.organization_id}"> 
 				<input type="hidden" id="selectPersonItemIdList" name="selectPersonItemIdList" value="${param.selectPersonItemIdList}"/>
				<input type="hidden" id="selectPersonItemNameList" name="selectPersonItemNameList" value="${param.selectPersonItemNameList}"/>
		<div class="add_top" style>&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;产品选择</div>
		<!--搜索部分begin-->	
		 <div class="row-fluid aufour mar_2"> 
		  
				<input type="hidden" name="url" value="${param.url}"/>
			     <table  class="tableopen"  width="100%">
			    	<tr>
			    	   <td class="text_rg title_font bzh "> 产品品规</td>
			    		<td class="text_lf">
			    			<input   type="text" class="open_srk  " name="segment1" id="segment1" value="${param.segment1}">		
			    		</td>
			    	    <td class="text_rg title_font bzh">产品名称</td>
			    		<td class="text_lf">
			    			<input   type="text" class="open_srk  " name="description" id="description" value="${param.description}">
			    	   </td>
			    	</tr>
		    	</table>
		    	 <div class="rgcont_long">		 
				  	<button type="submit" class="btn buttom1">查询</button>
			   </div>
		   
		 </div>
		 <!--搜索部分over-->
		 	<ul class="zjbf">
	  <li class="zjbf_li"><button class="btn  btn-primary" type="button" onclick="select_click();">确定</button></li>
	</ul>
		 <!--列表部分begin-->	
		 <div class="row-fluid aufive mar_2"> 
		   <div class="aulist_3" ><b></b>产品列表</div>  
	    	<div class="table_auto scroll_auto" style="overflow-x:auto;overflow-y:hidden;">
		 		<table id="contentTable" width="90%" class="table table-striped table-bordered table-condensed table-hover ">
			    	<tr class="tbth">
			    		<td class="bzh"><input  name="chk_all" type="checkbox"   id="chk_all" /> </td>
			    		<td class="bzh">产品编码</td>
			    		<td class="bzh">产品名称</td>
			    		<td class="bzh">产品单位</td> 
			    		
			    	</tr>
			    	<c:forEach var="list"  items="${itemList}" varStatus="vstatus">
				    	<tr> 
		   <td width="10px;">
  
 			<input  name="chk_list" id="${list.segment1}" value="${list.description}|1|${list.inventory_item_id}|${list.primary_uom_code}|${list.unit_of_measure}" type="checkbox"  onclick="selectPersonItem(this)"  /> 
    		</td>
				    		<td>${list.segment1}</td>
				    		<td>${list.description}</td>
				    		<td>${list.unit_of_measure}&nbsp;</td> 
				    	</tr>
				    </c:forEach> 
			    </table>    
		   		<tags:paginationOpen/>
		   		
	    	</div>
		 </div>
		 </form>
	</body>
</html>