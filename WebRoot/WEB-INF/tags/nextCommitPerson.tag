<%@tag pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script language="javascript" type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
function personCommitSetOrganization(personId,userAccount,userName) {
	var userNameStr = userName.replace(",", "").replace("，", "");
	$('#assistantUserName').val(userNameStr);
	$('#assistantUserAccount').val(userAccount);
	$('#assistantPersonId').val(personId);

}

function closeCommitDialog(){
	$('#wCommitDialog').window('close'); 
}

function selectCommitPerson() {

	$('#selectCommitiframe').attr('src','getCommitPersonView.do?levelTypeUp=N&levelTypeDown=N'+'&d='+new Date().getTime());
	$('#wCommitDialog').window('open');
 }
 
 function operateAssistant(){
	 var assistantButton = $("#assistantButton").val();
	 if(assistantButton=='点击增加助审人'){
		$("#assistantUserDiv").show(); 
		 $("#assistantButton").val("点击隐藏助审人");
		 if(typeof($("#passStep")) != "undefined"){
		 $("#pass_tr").hide();
		 $("#passStep").attr("id","passStep1");
		 }
		 
	 }else{
		 $("#assistantUserDiv").hide(); 
		$("#assistantButton").val("点击增加助审人"); 
	    $("#assistantUserName").val('');
	    $("#assistantUserAccount").val('');
	    $("#assistantPersonId").val('');
	    if(typeof($("#passStep1")) != "undefined"){
		$("#pass_tr").show(); 
		$("#passStep1").attr("id","passStep");
		}
	 }
 }
 function closeWApproveDialog(){
		$('#wApproveDialog').window('close'); 
	}

	function selectApprovalComment() {

		$('#selectApproveframe').attr('src','getApprovalCommentView.do?processCode='+'${param.processCode}'+'&d='+new Date().getTime());
		$('#wApproveDialog').window('open');
	 }
</script>
	<tr>
		
			<td class="text_lf">
				<b class="title_font">快捷回复</b>&nbsp; 
		     	 <input class="btn btn-primary" style="margin-left:3px" onclick="selectApprovalComment();" value="点击查看" type="button">
		     	 
			</td>		
		</tr>
       	<c:if test="${is_muti_node=='N'}">
       	<tr>
       	<td class="text_lf" >
		<b class="title_font">助审</b>&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;    
       	<input class="btn btn-primary" onclick="operateAssistant()" value="点击增加助审人" type="button" id="assistantButton">
       	</td>
       	</tr>
		<tr>
		
			<td class="text_lf" id="assistantUserDiv" style="display:none">
			<b class="title_font">设置助审人</b>&nbsp; 
			 <input type="text" class="input9" style="width:16% !important;" name="assistantUserName"  readonly UNSELECTABLE="on" id="assistantUserName" value="" />
		     	 <button style="width:16%;"  type="button" class="chooes_3" onclick="selectCommitPerson()" ></button>
				<input type="hidden" class="input1 " name="assistantUserAccount" id="assistantUserAccount" value=""/>
			    <input type="hidden" class="input1 " name="assistantPersonId" id="assistantPersonId" value=""/>
				<b class="title_font">设置超时时间</b>&nbsp; 
			   <input type="text" id="out_date_str" name="out_date_str" class="input6 " style="width:200px !important" onClick="WdatePicker({onpicked:function(){},startDate:'%y-%M-01 00:00:00',
	    		 dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d}'})"  pattern="yyyy-MM-dd HH:mm:ss" />
			</td>		
		</tr>
         </c:if>
<div id="wCommitDialog" class="easyui-window " title="&nbsp;"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width:812px;height:500px;overflow-x:hidden;display:none;">
 		<iframe id="selectCommitiframe"  src=''
			style="width:800px;height:450px;border: 0 none;overflow:auto;"></iframe>
 	</div>
 	<div id="wApproveDialog" class="easyui-window " title="&nbsp;"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width:812px;height:500px;overflow-x:hidden;display:none;">
 		<iframe id="selectApproveframe"  src=''
			style="width:800px;height:450px;border: 0 none;overflow:auto;"></iframe>
 	</div>