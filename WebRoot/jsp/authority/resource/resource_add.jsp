<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<html>
	<head>
		<title>新增资源</title>
		
<link href="${ctx}/static/dtree/dtreeck.css" type="text/css"
	rel="stylesheet" />
<script src="${ctx}/static/dtree/dtreeck.js"
	type="text/javascript">
</script>


<script type="text/javascript">

		//生成弹出层的代码
		var xOffset = 0;//向右偏移量
		var yOffset = 25;//向下偏移量
		
		function functionsClick() {
			
			$("#treediv")
			.css("position", "absolute")
			.css("left", $("#menuNodeName").position().left+xOffset + "px")
			.css("top", $("#menuNodeName").position().top+yOffset +"px").show();
		}
		
		function closedTree() {
			$("#treediv").hide();
		}


		//判断鼠标在不在弹出层范围内
		 function   checkIn(id){
			var yy = 20;   //偏移量
			var str = "";
			var   x=window.event.clientX;   
			var   y=window.event.clientY;   
			var   obj=$("#"+id)[0];
			if(x>obj.offsetLeft&&x<(obj.offsetLeft+obj.clientWidth)&&y>(obj.offsetTop-yy)&&y<(obj.offsetTop+obj.clientHeight)){   
				return true;
			}else{   
				return false;
			}   
		  }   
		//点击body关闭弹出层
			$(document).click(function(){
				var is = checkIn("treediv");
				if(!is){
					$("#treediv").hide();
				}
			});
		//生成弹出层的代码

		//点击菜单树给文本框赋值------------------菜单树里加此方法
		function setvalue(id,name,type){
			if(type != 20) {
				$("#infoMessageText").html("请选择功能节点");
				$('#InfoMessage').window('open');
				return;
			}
			$("#menuNodeName").val(name);
			$("#menuNodeId").val(id);
			$("#treediv").hide();
		}

</script>
	</head>
	<body>
		<form class="form-search" action="${ctx}/AddResource.do" id="inputForm">
			<input type="hidden" id="funs" name="funs" />

			<div style="overflow: auto" id="area-overflow">
				<div class="main-box" id="area-right">

					<div class="add_top" style>
						<b></b>新增资源
						<div class="list_an">
							<button id="cancel_btn" class="btn btn-primary" type="button"
								onClick="location.href='gotoResourceList.do'">
								返回
							</button>
						</div>
					</div>

					<!-- 搜索条件 -->
					<div class="aurgcont bcnone" style="min-height: 500px;">
						<div class="row-fluid">
							<div class="span12">
								<ul>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											资源编码
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="resourceCode"
												id="resourceCode" />
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											资源名称
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="resourceName"
												id="resourceName" />
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											资源描述
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="resourceDesc"
												id="resourceDesc" />
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											功能菜单
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="menuNodeName"
												id="menuNodeName" onclick="functionsClick();"/>
											<input type="hidden" class="input1 required" name="menuNodeId"
												id="menuNodeId" />
										</div>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!-- 搜索条件 -->
					<div class="dbzn ">
						<input id="submit_btn" class="btn buttom1 " type="submit"
							value="保存" />
						&nbsp;
						<input id="cancel_btn" class="btn buttom1" type="button"
							value="取消" onclick="history.back()" />
					</div>
				</div>
			</div>
		</form>
		
		<div id="treediv" style="display: none;position:absolute;overflow:scroll;  width: 280px;height:400px;  padding: 5px;background: #fff;color: #fff;border: 1px solid #cccccc"  >
			<!-- 
			<div align="right"><a href="##" id="closed" onclick="closedTree();"><font color="#000">关闭&nbsp;</font></a></div> -->
			<script type="text/JavaScript">
				mydtree = new dTree('mydtree', "${ctx}/static/dtree/imgmenu/");
				var functionJsons = eval("(" + '${functionsStr}' + ")");  
				
				mydtree.add(0,
						  -1,
						  "根目录",
						  "javascript:setvalue('0','根目录')");
				
				for (var i=0; i < functionJsons.length; i++) {
					
					mydtree.add(functionJsons[i].menuNodeId, functionJsons[i].parentNodeId, functionJsons[i].menuNodeName, "javascript:setvalue('"+functionJsons[i].menuNodeId+"','"+ functionJsons[i].menuNodeName +"','"+ functionJsons[i].menuNodeType +"')");
					//mydtree.add(11+i, 1, '11111', "javascript:setvalue('11','22')");
				}
				
				document.write(mydtree);
				$('#imydtree0').attr('style','display:none')
				$('#smydtree0').attr('style','display:none')
				
			</script>
		</div>
		
	</body>
</html>