<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	submitData();

}); 
	function submitData(){
		showdiv();
	    sleep(2000);
	    hidediv();
		$("#formbiee").submit();
		
		
	}
	function sleep(numberMillis) {
		var now = new Date();
		
		var exitTime = now.getTime() + numberMillis;
		while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
		return;
		}
	}	
</script>
</head>
<body>
	<div style="overflow: auto" id="area-overflow">
		<div class="main-box" id="area-right">
			<span class="label label-info">已跳转（新开页面）到BI系统！</span>
		</div>
	</div>

	<form id='formbiee' name='formbiee' target="_blank" action="${url}" method="post">
<input type="hidden" name="Cmd" value="Answers"> 
<input type="hidden" name="nqUser" value="${nqUser}"> 
<input type="hidden" name="nqPassword" value="${nqPassword}">
<input type="hidden" name="nqRandom" value="${nqRandom}">
<iframe id="preview" name="preview"  height="0%" width="100%" frameborder="1" src="http://bi.ausnutria.com:9704/analytics/saw.dll?Logoff" style="display:none;" >
</iframe>  
<input type="button" value="xxx" onclick="submitData()" style="display:none;">
</form>
</body>
</html>