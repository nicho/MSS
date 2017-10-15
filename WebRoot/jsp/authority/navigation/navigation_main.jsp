<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<title>导航管理</title>
		<link href="${ctx}/static/ligerUI/skins/Aqua/css/ligerui-tree.css"
			rel="stylesheet" type="text/css" />
</script>
		<script src="${ctx}/static/ligerUI/js/core/base.js"
			type="text/javascript">
</script>
		<script src="${ctx}/static/ligerUI/js/plugins/ligerTree.js"
			type="text/javascript">
</script>
		<script type="text/javascript">
var node;

var manager = null;

$(function ()
       {
           $("#tree1").ligerTree({
           	 parentIcon: '',
        	 childIcon: '',
           	 treeLine:true,
          		 data: ${jsontree},
          	 	 onClick: onClick,
          	 	 checkbox: false
           });
           manager = $("#tree1").ligerGetTreeManager(); 
           manager.collapseAll();
           node = null;

           $("#tree1").css('width','240px');
       });             
       
       function onClick(note)
       {   
           node = note;
       }    

       var node_status;
       
       function add(){
       	node_status = "add";
       	if( node ) {
       		if( node.data.level == 3) {
   				alert("系统不支持四级节点");
   				return;
   			}  
       		$("#parentNodeId option[value='"+node.data.id+"']").attr("selected","selected");
       		$("#menuNodeCode").val("");
       		$("#orderNum").val("");
       		$("#url").val("");
       		$("#menuNodeName").val("");
       		$("#menuNodeDesc").val("");
       		$("#icon").val("");
       		$("#parentId").val(node.data.id);	        		
       		var form = document.forms['inputForm'];
       		form.action="${ctx}/saveNavigation.do";	
       	} else {       		
       		var form = document.forms['inputForm'];
       		form.action="${ctx}/saveNavigation.do";	
       	
       	}
       }
       
       function check(){
       	if( $("#menuNodeName").val() == "" ) {
       		alert(" 名称不能为空 ");
       		
       		return false;
       	}
       	if( $("#menuNodeName").val().length > 50 ) {
       		alert(" 名称长度不能超过50个字符 ");
       		
       		return false;
       	}
       	
       	if( $("#orderNum").val() == "" ) {
       		alert(" 排序号不能为空 ");
       		
       		return false;
       	}
       	
       	var reg= /^\d+$/;
       	if( !$("#orderNum").val().match(reg)) {
       		alert(" 排序号必须为整数");
       		
       		return false;
       	} 
       	
       	if( $("#orderNum").val() > 100) {
       		alert(" 排序号过大,不要超过100");
       	
       		return false;
       	}
       	
       	return true;      	
     
       }
       
       function del() {   	      	   
    	   if(node) {
	    	   if(manager.hasChildren(node)) {  		   
	    		  alert("该节点下有子节点不能删除");   		  
	    		  return;
	    	   }
	    	   if(window.confirm("确认删除该节点吗？")) {
	    		   $.ajax({
						type:"POST",
						url:"${ctx}/deleteNavigation.do",
						data:{menuNodeId:node.data.id},
						success:function(data){
							var arr = $.parseJSON(data);		
							if(arr.state == 1) {								
							    manager.remove(node.target);
							}else {								
								alert("删除失败");
							}						
						}
				   });		   
	    	   }
    	   }  
       }
       
       function modify(){
       	node_status =  "modify";
   		if( node ) {
   			var form = document.forms['inputForm'];      		
     		$("#parentNodeId option[value='"+manager.getParentBydzh(node)+"']").attr("selected","selected");
      		$("#orderNum").val(node.data.sort);
      		$("#menuNodeName").val(node.data.text);
      		$("#menuNodeCode").val(node.data.nodeCode);
      		$("#menuNodeDesc").val(node.data.nodeDesc);
      		$("#icon").val(node.data.icon);
      		$("#menuNodeType option[value='"+node.data.nodeType+"']").attr("selected","selected");
      		$("#OriginalSource option[value='"+node.data.originalSource+"']").attr("selected","selected");
      		$("#url").val(node.data.url);
      		form.action="${ctx}/modifyNavigation.do";		
     	}	        
       }
              
       function save() {
	       	var form = document.forms['inputForm'];	        	        	
	       	if( node ) {
	       		$("#menuNodeId").val(node.data.id);	
	       	}      		
	        if(check()) {
		        form.submit();     
	       	}
        }
		</script>

		
</script>

	</head>
	<body>

		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<div class="zz_box">
					<!--左侧 -->
					<div class="zz_left">
						<div class="demo">
							<div class="zz_treebox">
								<div class="treeeditpage">
									<ul id="tree1" style="width: 240px;"></ul>
								</div>
							</div>
						</div>
					</div>
					<!--左侧 -->
					<!--右侧 -->
					<form action="" method="post" name="inputForm">
						<input type="hidden" name="menuNodeId" id="menuNodeId" />
						<div class="zz_right">
						 <div class="zz_anbf">
				           <div class="aulist_3 zz_rgtop">
				            <button class="btn  btn-primary zz_rging" type="button" onclick="del()">删除</button>	
						    <button class="btn  btn-primary zz_rging" type="button" onclick="modify()">编辑</button>	
						    <button class="btn  btn-primary zz_rging" type="button" onclick="add()">添加</button>				    	
				           </div>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									名称
								</div>
								<div class="rgcont_rr">
									<input type="text" class="input1 " name="menuNodeName"
										id="menuNodeName">
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									父级节点
								</div>
								<div class="rgcont_rr">
									<select name="parentNodeId" id="parentNodeId" class="select1">
										<option value="0">
											根节点
										</option>
										<c:forEach items="${v_mapList}" var="map" varStatus="status">
											<option value="${map.id}">
												${map.text}
											</option>
										</c:forEach>
									</select>
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									排序号
								</div>
								<div class="rgcont_rr">
									<input type="text" class="input1 " name="orderNum"
										id="orderNum">
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									URL
								</div>
								<div class="rgcont_rr">
									<input type="text" class="input1 " name="url" id="url">
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									节点类型
								</div>
								<div class="rgcont_rr">
									<select name="menuNodeType" id="menuNodeType" class="select1">
										<option value="10">
											菜单
										</option>
										<option value="20">
											功能
										</option>
									</select>
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									图标
								</div>
								<div class="rgcont_rr">
									<input type="text" class="input1 " name="icon" id="icon">
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									节点编码
								</div>
								<div class="rgcont_rr">
									<input type="text" class="input1 " name="menuNodeCode" id="menuNodeCode">
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									节点描述
								</div>
								<div class="rgcont_rr">
									<input type="text" class="input1 " name="menuNodeDesc" id="menuNodeDesc">
								</div>
							</li>
							<li class="rgcont_li">
								<div class="rgcont_wz">
									系统来源
								</div>
								<div class="rgcont_rr">
									<select name="OriginalSource" id="OriginalSource" class="select1">
										<option value="1">
											PC
										</option>
										<option value="2">
											手机
										</option>
									</select>
								</div>
							</li>
						</div>
						<!--右侧 -->
					</form>
				</div>
			</div>
			<div class="dbzn ">
				<input id="submit_btn" class="btn buttom1 " type="submit" value="保存"
					onclick="save()" />
				&nbsp;
			</div>
		</div>

	</body>
</html>