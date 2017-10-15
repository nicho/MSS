<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script language="javascript" type="text/javascript">

$(document).ready(function() {
     var docId ='${docId}';
     var processCode = '${processCode}';
     var operationCode = '${operationCode}';
     
     $.ajax({
			type : 'POST',
			data:{
				docId:docId,
				processCode:processCode,
				operationCode : operationCode
			},
			url : '${ctx}/getApprovalHis.do',
			success : function(returnData) {
             if(returnData){
            	 returnData = eval("("+returnData+")");
            	 returnData = eval(returnData.rows);
            	 for(var i =0;i<returnData.length;i++){
            	 	var approvalComment = returnData[i].approvalComment;
            	 	approvalComment = typeof(approvalComment )=='undefined'?"":approvalComment;
            	 	var nextApprovalUserName = returnData[i].nextApprovalUserName;
            	 	nextApprovalUserName = typeof(nextApprovalUserName ) =='undefined'?"":nextApprovalUserName;
            	 	if(returnData[i].statusName == '审批通过')
            	 	{
                                 	 	
            	 	  $("#lastTrId").show();
            	 	  
            		 $("#contentTable_approveHis")
            		 	.append("<tr><td>"+returnData[i].approvalUserName+"</td><td>"+
            		 	returnData[i].actionName+ "</td><td>"+ 
            		 	returnData[i].statusName+"</td><td>"+
            		 	returnData[i].approvalDate+"</td><td>" +
            		 	approvalComment+ "</td><td>" +
            		 	nextApprovalUserName+ 
            		 	"</td></tr>");
            		 }
            		 //else
            	 	 //{
            		 //$("#contentTable_approveHis")
            		 //	.append("<tr><td></td><td></td><td>审批中</td><td></td><td></td><td></td></tr>");
            		 //}
            	 }
            	
            	 
            	 
             }         
	        }
		}); 
	 /**$('#contentTable_approveHis').datagrid({
			url : '${ctx}/getApprovalHis.do?docId='+docId+'&processCode='+processCode + '&operationCode=' + operationCode,
			border : false,
			fitColumns : true,
			fit : true,
			idField : 'fileId',
			nowrap : false,
			sortOrder : 'desc',
			nowrap : false,
			loadMsg : '',
			columns : [ [ 
			    {
				      field : 'approvalUserName',
					  title : '<spring:message code="approval_user_name"/>',
					  width : 150
				},{
					field : 'actionName',
					title : '<spring:message code="action_name"/>',
					width : 150
				},{
					field : 'statusName',
					title : '<spring:message code="result"/>',
					width : 150
				},{
					field : 'approvalDate',
					title : '<spring:message code="commit_approval_date"/>',
					width : 150
				},{
					field : 'approvalComment',
					title : '<spring:message code="approval_comment"/>',
					width : 150
				},{
					field : 'nextApprovalUserName',
					title : '<spring:message code="next_approval_user_name"/>',
					width : 150,
					editor:'text'
				}
			] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});**/
	});
</script>

<div class="list_box mar_2">
   <div class="aulist_3"><b></b><spring:message code="approval_history"/>
     <div class="list_an">
     </div>
   </div>
   <div>
   </div>
    <div style="width:100%;height:0px;background:#fff;"></div> 
     <div class="table_auto scroll_auto">
	     <table id="contentTable_approveHis"width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-condensed table-hover ">
	     	<tr class="tbth ">
	    		<td  class="bzh"><spring:message code="approval_user_name"/></td> 
	    		<td  class="bzh"><spring:message code="action_name"/></td>
	    		<td  class="bzh"><spring:message code="result"/></td>
	    		<td  class="bzh"><spring:message code="commit_approval_date"/></td>     		 
	    	    <td  class="bzh"><spring:message code='approval_comment'/></td>
	    	    <td  class="bzh"><spring:message code='next_approval_user_name'/></td>
	    	</tr>
	    </table> 
    </div>
  </div>
