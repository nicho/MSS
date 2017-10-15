<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>人员选择</title>
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/common.css" type="text/css"
	rel="stylesheet" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico"
	rel="shortcut icon">
<link href="${ctx}/static/bootstrap/css/bootstrap.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css"
	type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
			href="${ctx}/static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
			href="${ctx}/static/easyui/themes/icon.css" />
<script src="${ctx}/static/easyui/jquery.easyui.min.js"
			type="text/javascript">	
	
<script
	src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/jquery/json2.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse.js"
	type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_storage.js"
	type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_cookie_storage.js"
	type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.SuperSlide.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="${ctx}/static/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
/* var editor =null;
KindEditor.ready(function() { 
	 var options = {
			   //cssPath : '${ctx}/static/kindeditor/plugins/code/prettify.css',
		       themeType : 'default',   //指定主题风格，可设置”default”、”simple”，指定simple时需要引入simple.css; 默认值: “default”  
		       resizeType: 1,           //2或1或0，2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动;默认值: 2  
		       allowFileManager:false,   //true时显示浏览远程服务器按钮 ;默认值: false  
		       allowMediaUpload:true, //true时显示视音频上传按钮。默认值: true  
		       allowFlashUpload:true, //true时显示Flash上传按钮;默认值: true  
		       uploadJson:'kind',          //指定上传文件的服务器端程序  
		       fileManagerJson:'fileManager',     //指定浏览远程图片的服务器端程序  
		       items:['source','|','undo','redo','|','preview','print','template',
		              'plainpaste','wordpaste','|','justifyleft','justifycenter','justifyright',
		              'justifyfull','insertorderedlist','insertunorderedlist','indent','outdent','subscript','superscript','clearhtml','quickformat','selectall','link','/','unlink','formatblock','fontname','fontsize','|','forecolor','hilitecolor','bold',
		              'italic','underline','strikethrough','lineheight','removeformat','|','table','hr','emoticons','baidumap','pagebreak','anchor','|','about'],
		        afterBlur: function(){this.sync();
		        },

		        afterCreate:function(){ 
		    	   //加载完成后改变皮肤  
		       }  
		         
		   };
		   editor= KindEditor.create('#comment_id',options);
  });
 */




	/**人员编号、人员姓名、部门（省区）、岗位ID、岗位名称***/
	function updateStatus() {
	    var commentDesc = $("#comment_id").val();
	    var commentId =  $("#commentId").val();
	    var pNum = $("#pNum").val();
	    var diaryId =  $("#diaryId").val();
			if(commentDesc == null || trim(commentDesc)==""){
				 $("#InfoMessageReplyText").html("<spring:message code='comment_required'/>");
				 $('#InfoMessageReply').window('open');  	
			}else{
				$('#status').val(status);
				 $.ajax({
						type : "post", // 使用post方法访问后台
						dataType : "json", // 返回JSON格式的数据
						data:{commentDesc:commentDesc,commentId:commentId,diaryId:diaryId,pNum:pNum},
						url : '${ctx}/confirmReplyComment.do',
					    success:function(result) {
					    parent.closeDialog1(result); 
					    }
					    });	
			}
	    
	    
	   
	    
	    
	   
		//parent.closeDialog1();
	}
 
 function  closeInfoMessage1()
 {
 	$('#InfoMessageReply').window('close');	
 }
	
	 function trim(str){ //删除左右两端的空格
	     return str.replace(/(^\s*)|(\s*$)/g, "");
	 }
</script>
<style>
.open_srk {
	width: 200px;
	height: 25px !important;
	overflow: hidden;
	border: 1px solid #ccc;
	text-indent: 0.5em;
	font-size: 12px !important;
	line-height: 22px;
}

.open_xlk {
	width: 200px;
	height: 25px;
	overflow: hidden;
	border: 1px solid #ccc;
	margin-left: 8px\9;
	font-size: 12px !important;
}
.window .window-header {
    background: transparent none repeat scroll 0% 0%;
    padding: 0px 0px 6px;
    width: 302px;
    }
</style>
</head>
<body
	style="border: 0px; overflow: auto; height: 500px; height: 480px\9;">
	<div class="add_top">
		&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;
		<spring:message code='reply' />
	</div>
	<!--列表部分begin-->
	<div class="row-fluid aufive mar_2">
		<form id="inputForm2" action="${ctx}/confirmReplyComment.do" method="post"
			class="form-horizontal">
			<input type="hidden" id="pNum" name="pNum" value="${pNum}">
			<input type="hidden" id="diaryId" name="diaryId" value="${comment.diaryId}">
			<input type="hidden" id="commentId" name="commentId" value="${comment.commentId}">
			<div>${comment.userName}
			&nbsp;&nbsp;&nbsp;
				<spring:message code='comment_time' />&nbsp;&nbsp;&nbsp;
				<fmt:formatDate value="${comment.creationDate}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
			<div>${comment.commentMemo}</div>
			<div class="zz_box">
				<div class="aulist_3"><b></b><spring:message code='comment_contents' />
				</div>
				<div>
					<textarea id="comment_id" name="commentDesc" class="required"
						style="width: 100%; height: 100px;text-align:left;font-size: 12px !important;"></textarea>
				</div>
			</div>
			<div class="dbzn ">
				<input id="submit_btn2" class="btn buttom1 " type="button"
					value="<spring:message code='reply_content'/>"
					onclick="updateStatus()" />&nbsp; <input
					id="cancel_btn" class="btn buttom1" type="button"
					value="<spring:message code='Cancel'/>" onclick="parent.closeDialog();" />
			</div>
		</form>
	</div>
		<div id="InfoMessageReply" class="easyui-window ui_bccg" title="&nbsp;"
			data-options="modal:true,closed:true,iconCls:'icon-save'"
			style="overflow-x: hidden;display:none;top:80.5px">
			<div class="wz_tp2"></div>
			<div class="wz_wxbig" id="InfoMessageReplyText"
				style="text-align: center;">
				${InfoMessage}
			</div>
			<div class="wz_db">
				<input id="submit_btn2" class="btn buttom1 " type="button"
					value="确定" onclick="closeInfoMessage1()" />
				&nbsp;
			</div>
		</div>
</body>
</html>