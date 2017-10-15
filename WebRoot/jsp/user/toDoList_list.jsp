<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>我的工作</title>
<link href="${ctx}/static/dtree/dtreeck.css" type="text/css"
	rel="stylesheet" />
<script src="${ctx}/static/dtree/dtreeck.js"
	type="text/javascript">
</script>
<script language="javascript" type="text/javascript">
$(document).ready(function() {
	
	var subject = decodeURIComponent('${param.subject}'); 
	if(subject !=null && subject !=''){
		$("#subject").val(subject);
	}
	var commitUserName = decodeURIComponent('${param.commitUserName}'); 
	if(commitUserName !=null && commitUserName !=''){
		$("#commitUserName").val(commitUserName);
	}
	
});


function viewWorkApprovalInst(notificationId,processCode,billId) {
	  
	$('#selectiframe').attr('src','${ctx}/workApprovalInstPopover.do?operationCode='+notificationId+'&processCode='+processCode+'&docId='+billId+'');
	$('#wDialog').window('open');
}
</script>
<script type="text/javascript">

		//生成弹出层的代码
		var xOffset = 0;//向右偏移量
		var yOffset = 25;//向下偏移量
		
		function functionsClick() {
			
			$("#treediv")
			.css("position", "absolute")
			.css("left", $("#flowtypeName").position().left+xOffset + "px")
			.css("top", $("#flowtypeName").position().top+yOffset +"px").show();
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
				$("#infoMessageText").html("请选择流程节点");
				$('#InfoMessage').window('open');
				return;
			}
			$("#flowtype").val(id);
			$("#flowtypeName").val(name);
			$("#treediv").hide();
		}
		
		function hiddenTreeDiv(){
			$("#flowtype").val('');
			$("#flowtypeName").val('');
			$("#treediv").hide();
		}

</script>
</head>
<body> 
	<%-- <c:if test="${not empty InfoMessage}">
		<div id="InfoMessage" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${InfoMessage}</div>
	</c:if> --%>
	<form class="form-search"  action="${ctx}/userWorkflowToDoList.do"  > 
	
	   <div style="overflow:auto" id="area-overflow">
        <div class="main-box" id="area-right">        
 <!-- 搜索条件 -->
  <div class="aurgcont"> 
  <div class="row-fluid"> 
   <div class="span12">
         <ul> 	 
	  	
	  <li class="rgcont_li">
	    <div class="rgcont_wz"><spring:message code='Application_time'/></div>
	    <div class="rgcont_rr"> 	     
	      <input type="text" id="startDate" name="startDate" class="input6" onClick="WdatePicker({onpicked:function(){endDate.focus();endDate.click();}})" value="${param.startDate}"/>
	       <spring:message code='To'/>
	      <input type="text" id="endDate" name="endDate" class="input6" onClick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:1})}'})" value="${param.endDate}"/> 
		 </div>
	  </li>	 
	  <li class="rgcont_li">
	    <div class="rgcont_wz">流程类型</div>
	    <div class="rgcont_rr">
           <c:if test="${orgId !='82'}">
       	  <select   name="flowtype" id="flowtype" >
		           <option value=""></option> 
		           <c:forEach var="flowtype"  items="${flowtypeList}"  >
		            	<option value="${flowtype.flexValue}" <c:if test="${flowtype.flexValue eq param.flowtype}">selected</c:if>>${flowtype.flexValuesDesc}</option>
		           </c:forEach>
           
          </select>
     </c:if>
      <c:if test="${orgId =='82'}">
      
      <input type="text" class="input1" name="flowtypeName" id="flowtypeName" value="${param.flowtypeName}" onclick="functionsClick();"/> 	
    	<input type="hidden" class="input1" name="flowtype" id="flowtype" value="${param.flowtype}"/> 
     </c:if>
	   </div>
	  </li>

	  <li class="rgcont_li">
	    <div class="rgcont_wz">编号</div>
	    <div class="rgcont_rr"> 	     
	      <input type="text" id="billCode" name="billCode" class="input1 input-large "  value="${param.billCode}" maxlength="20"/> 
		 </div>
	  </li>
	   <li class="rgcont_li">
	    <div class="rgcont_wz">主题</div>
	    <div class="rgcont_rr"> 	     
	      <input type="text" id="subject" name="subject" class="input1 input-large "  value="" maxlength="20"/> 
		 </div>
	  </li>	 	 
	  	  	  <li class="rgcont_li">
	    <div class="rgcont_wz">发起人</div>
	    <div class="rgcont_rr"> 	     
	      <input type="text" id="commitUserName" name="commitUserName" class="input1 input-large "  value="" maxlength="20"/> 
		 </div>
	  </li>
	   <li class="rgcont_li">
			    <div class="rgcont_wz">是否已发消息</div>
			    <div class="rgcont_rr"> 	     
					 <select name="hasNotice" id="hasNotice" class="select1">
			           <option value="">请选择</option>
			           <option value="N" <c:if test="${param.hasNotice eq 'N'}">selected</c:if>>否</option>
			           <option value="Y" <c:if test="${param.hasNotice eq 'Y'}">selected</c:if>>是</option>
			         </select>
				</div>
	  </li>	
	  	   <li class="rgcont_li" style="width:50%;margin:5px -80px">
	    <%-- <div class="rgcont_wz">状态</div>
	    <div class="rgcont_rr">
	      <select   name="flowstate" id="flowstate" class="select1"> 
	      			<option value=''>全部</option>
		           <c:forEach var="flowstate"  items="${flowstateList}"  >
		            	<option value="${flowstate.flexValue}" <c:if test="${flowstate.flexValue eq param.flowstate}">selected</c:if>>${flowstate.flexValuesDesc}</option>
		           </c:forEach>
           
          </select>
	   </div> --%>
	   <div class="rgcont_wz" >任务状态</div>
	    <div class="rgcont_rr">
	    <%--   <select   name="flowstate" id="flowstate" class="select1"> 
	      			<option value=''>全部</option>
		            <option value='A' <c:if test="${'A' eq param.flowstate}">selected</c:if>>未接收</option>
                    <option value='B' <c:if test="${'B' eq param.flowstate}">selected</c:if>>办理中</option>
                    <option value='C' <c:if test="${'C' eq param.flowstate}">selected</c:if>>已办结</option>
                    <option value='M' <c:if test="${'M' eq param.flowstate}">selected</c:if>>系统消息</option>
          </select> --%>
            <input type="checkbox" name="flowstate"  class="required" value="A" <c:if test="${'A' eq flowStateA}">checked="checked"</c:if>/>
							    		未接收&nbsp;
            <input type="checkbox" name="flowstate"  class="required" value="B" <c:if test="${'B' eq flowStateB}">checked="checked"</c:if>/>
							    		办理中&nbsp;
			<input type="checkbox" name="flowstate"  class="required" value="C" <c:if test="${'C' eq flowStateC}">checked="checked"</c:if>/>
							    		已办结&nbsp;
			<input type="checkbox" name="flowstate"  class="required" value="M" <c:if test="${'M' eq flowStateM}">checked="checked"</c:if>/>
							    		系统消息&nbsp;
           <input type="checkbox" name="flowstate"  class="required" value="X" <c:if test="${'X' eq flowStateX}">checked="checked"</c:if>/>
							    		任务创建中&nbsp;
	   </div>
	  </li> 	 	 
	  <li class="rgcont_long">		 
		  <button type="submit" class="btn buttom1"><spring:message code='Query'/></button>
	  </li>
 
 </ul>
  </div>   
  </div>
 </div> 
   <!-- 搜索条件 -->
 

	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="auhng_1">
	<tr> <td><b><img src="${ctx}/static/images/a_33.jpg" >我的工作</td></tr>
	</table>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover ">
    	<tr>
	                    <td ><spring:message code='Serial_number'/></td>
	                    <td >申请时间</td>	  
	                    <td >编号</td>	  
	                    <td ><spring:message code='zhuti'/></td> 
	                    <td ><spring:message code='faqiren'/></td>	  
	                    <td >流程类型</td>	
	                    <td >步骤</td>	  
	                    <td >状态</td>
	                    <td>已发消息</td>	 
	                    <td >操作</td>	
    	</tr>
     <c:forEach var="list"  items="${backLogList}" varStatus="vstatus">
         	<tr>                    
            <td>${vstatus.index + 1}</td>        
             <td>${list.beginDate}</td>	        
             <td>${list.billCode}</td>	           
            <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${list.subject}">
            	 <c:if test="${list.notificationId !=''&& list.notificationId !=null }">
            	<a href="${ctx}/gotoBussnessApprovePage.do?notificationId=${list.notificationId}&processCode=${list.processCode}&billId=${list.billId}<c:if test="${(list.status eq 'A' || list.status eq 'B') &&   list.endDate eq null}">&approveFlag=Y</c:if>">${list.subject}</a>
                </c:if>
                 <c:if test="${list.notificationId =='' ||list.notificationId == null}">
                 ${list.notificationId}
                 </c:if>
            </td>	 
            <td>${list.commitUserName}</td>	         
             <td> 
             	   <c:forEach var="flowtype"  items="${flowtypeList}"  >
             	   			<c:if test="${list.processCode eq flowtype.flexValue}">
		            			 ${flowtype.flexValuesDesc} 
             	   			</c:if>
		           </c:forEach>
             </td>	
              <td>${list.step}</td>      
             <td>${list.statusName}</td>
             <td>${list.hasNotice}</td>	                 
             <td>
             <c:if test="${list.notificationId !='' && list.notificationId !=null}"> 
             <c:if test="${(list.status eq 'A' || list.status eq 'B') &&   list.endDate eq null}">
             <c:if test="${!list.subject.contains('未通过')}"> 
             <a href="${ctx}/gotoBussnessApprovePage.do?notificationId=${list.notificationId}&processCode=${list.processCode}&billId=${list.billId}&approveFlag=Y">审批</a>&nbsp;&nbsp;&nbsp;&nbsp;
             </c:if> 
             </c:if>
             </c:if>
            <c:if test="${list.notificationId !='' && list.notificationId !=null}"> 
             <a href="${ctx}/gotoBussnessApprovePage.do?notificationId=${list.notificationId}&processCode=${list.processCode}&billId=${list.billId}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </c:if>
             <c:if test="${list.notificationId !='' && list.notificationId !=null}">
             <a href="#" onclick="viewWorkApprovalInst('${list.notificationId}','${list.processCode}','${list.billId}')">流程图</a>&nbsp;&nbsp;&nbsp;&nbsp;
             </c:if>
             </td>	                   
          </tr>   
     </c:forEach>
    </table>

  <tags:pagination  /> 
    </div> </div>
		<div id="treediv"
			style="display: none; position: absolute; overflow: scroll; width: 280px; height: 400px; padding: 5px; background: #fff; color: #fff; border: 1px solid #cccccc">
			<!-- 
			<div align="right"><a href="##" id="closed" onclick="closedTree();"><font color="#000">关闭&nbsp;</font></a></div> -->
			<script type="text/JavaScript">
				mydtree = new dTree('mydtree', "${ctx}/static/dtree/imgmenu/");
				var functionJsons = eval("(" + '${subListJson}' + ")");  
				
				mydtree.add(0,
						  -1,
						  "根目录",
						  "javascript:setvalue('0','根目录')");
				
				for (var i=0; i < functionJsons.length; i++) {
					
					mydtree.add(functionJsons[i].process_subclass_code, functionJsons[i].process_class_code, functionJsons[i].process_subclass_name, "javascript:setvalue('"+functionJsons[i].process_subclass_code+"','"+ functionJsons[i].process_subclass_name +"','"+ functionJsons[i].process_type +"')");
					//mydtree.add(11+i, 1, '11111', "javascript:setvalue('11','22')");
				}
				
				document.write(mydtree);
				$('#imydtree0').attr('style','display:none')
				$('#smydtree0').attr('style','display:none')
				
				
			</script>
			<div style="margin-top: 130px; margin-left: 100px;">
				<button type="button" class="btn"
					style="background: #FF9002 !important; border: 1px solid #DD7900; color: #fff !important; text-shadow: 0 0px 0px #733F00 !important; padding: 3px 18px !important; font-size: 14px !important;"
					onclick="hiddenTreeDiv()">
					<spring:message code='Clean' />
				</button>
			</div>
		</div>

	</form>  
	<div id="wDialog" class="easyui-window " title="&nbsp;" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:812px;height:350px;overflow-x:hidden;display:none;">
 		<iframe id="selectiframe" id="selectiframe" src='' style="width:800px;height:310px;border: 0 none;overflow:auto;"></iframe>
 	</div>
</body>
</html>