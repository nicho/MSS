<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
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
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
<script src="${ctx}/static/easyui/jquery.easyui.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {
	var rId ='${rId}';
	var docType = '${docType}';
	var url = '';
	if(docType !=null && docType !=''&& docType !='KNOWLEDGE'){
		url = '${ctx}/initDocTreeByDocType.do?docType='+docType+'&rId='+rId;
	}else if(docType !=null && docType !=''&& docType =='KNOWLEDGE'){
		url = '${ctx}/initKnowTreeByDocType.do?docType='+docType+'&rId='+rId;	
	}else{
		url = '${ctx}/initDocCfgTree.do?rId='+rId;
	}
	$('#dirTree').tree({
		cascadeCheck: false,
		checkbox:true,
	    url:url ,   
	    onClick:function(node){ 
			/* if(node.id !=0){
				$.ajax({
					type : "post", // 使用post方法访问后台
					async:false,
					dataType : "json", // 返回JSON格式的数据
					url : '${ctx}/getDocCfgDirOrder.do?nodeid='+node.id,
				    success:function(result) { 	
					 $('#dirOrder').val(result);	 
					 }
				 });		
			}else{
				 $('#dirOrder').val('1');	
			} */
			$('#dirTree').tree('select', node.target);
			$('#dirTree').tree('check', node.target);
			},
			onSelect:function(node){
			/* 	if(node.id !=0){
					$.ajax({
						type : "post", // 使用post方法访问后台
						dataType : "json", // 返回JSON格式的数据
						url : '${ctx}/getDocCfgDirOrder.do?nodeid='+node.id,
					    success:function(result) { 	
						 $('#dirOrder').val(result);	 
						 }
					 });		
				}else{
					 $('#dirOrder').val('1');	
				}	 */	
				
			},
			onCheck:function(node,checked){
				var nodes = $('#dirTree').tree('getChecked'); 
				if(checked ==true){
					if(nodes.length>0){
						for(var i=0;i<nodes.length;i++){
							if(nodes[i].id != node.id){
						     $('#dirTree').tree("uncheck", nodes[i].target);
							}
						}
					}
					
				}
				$('#dirTree').tree('select', node.target);	
			},
			onBeforeExpand :function(node,param){
				var nodes = $('#dirTree').tree('getChecked'); 
				if(nodes.length!=0){
				$("#checkDir").val(nodes[0].id);
				}
				$('#dirTree').tree('options').url = '${ctx}/getDocCfgTree.do?categoryid='+node.id;	 
			},
			onLoadSuccess:function (node,data){
				if(rId!=''){
					var snode = $('#dirTree').tree('find',rId);	
					$('#dirTree').tree('select', snode.target);	
					$('#dirTree').tree('check', snode.target);	
				}else{
					if($('#checkDir').val()==""){
						var root = $('#dirTree').tree('getRoot');
						$('#dirTree').tree('select', root.target);	
						$('#dirTree').tree('check', root.target);
					}else{
						var node = $('#dirTree').tree('find',$('#checkDir').val());
						$('#dirTree').tree('select', node.target);	
						$('#dirTree').tree('check', node.target);	
					}
				}
				

				
			}
		});
	});
	
		
function select_click(){
	var nodes = $('#dirTree').tree('getChecked');
	if(nodes.length!=0){
		parent.dirSet(nodes[0].id,nodes[0].text);
		parent.closeDialog();
	}
}		
</script>
<style type="text/css">
	input{width:10px;}
</style>
</head>
<body style="border:0px;overflow:auto;height:500px;height:480px\9;">
<div class="add_top" style>&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;<spring:message code='category_select'/></div>
     <div class="row-fluid aufour mar_2"> 
                        
        
         <ul style="height:auto;overflow-x:hidden;overflow-y:auto;" id="dirTree">
                   
                   
         </ul>
 
          <div> <input type="hidden" class="input1 " name="checkDir" id="checkDir" value=""></div>
        
    </div>
     <div  class="rgcont_long ">
	     <button class="btn buttom1" type="button" onclick="select_click();"><spring:message code='confirm'/></button>
	 </div>
  
</body>
</html>