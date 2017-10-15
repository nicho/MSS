<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/oaTag" prefix="oaTag" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head> 
<title>职责管理</title>
<script language="javascript" type="text/javascript">

function downFile(){
	window.open('${ctx}/exportResponsibility.do?responsibilityName='+ $('#responsibilityName').val()
	+'&responsibilityCode='+ $('#responsibilityCode').val()		
	);
	
}

</script>
</head>
<body> 

	<form class="form-search" action="${ctx}/viewResponsibilityIndex.do" method="post" id="inputForm" name="inputForm">
	   <div style="overflow:auto" id="area-overflow">
	       <div class="main-box" id="area-right">        
	 		<!-- 搜索条件 -->
			    <div class="aurgcont"> 
				  <div class="row-fluid"> 
					   <div class="span12">
						   <ul>
						      <oaTag:responsibilityChoice  sessionId="${pageContext.session.id}" url="gotoResponsibilityList.do"/>
						      <li class="rgcont_li">
							    <div class="rgcont_wz">职责名称</div>
							    <div class="rgcont_rr">  <input type="text" class="input1 " name="responsibilityName" id="responsibilityName" value="${param.responsibilityName}"></div>
							  </li> 
							  <li class="rgcont_li">
							    <div class="rgcont_wz">职责编码</div>
							    <div class="rgcont_rr">  <input type="text" class="input1 " name="responsibilityCode" id="responsibilityCode" value="${param.responsibilityCode}"></div>
							  </li>
							  <input type="hidden" class="input1 " name="isValid" id="isValid" value="1">
							  					  						 
							  <li class="rgcont_long">		 
								  <button type="submit" class="btn buttom1">查询</button>
								   <button type="button" onclick="clearForm();" class="btn buttom1">清空</button>
							  </li>
							  
						 </ul>
					  </div>   
				  </div>
			   </div> 
	   <!-- 搜索条件 -->
		<ul class="zjbf">
		  <li class="zjbf_li"><button class="btn  btn-primary" type="button" onclick="showMenuPage2('${ctx}/gotoAddResponsibility.do')">新增</button></li>
		  <li class="zjbf_li"><button class="btn  btn-primary" type="button" onclick="downFile()">导出</button></li>
		</ul>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="auhng_1">
				<tr> 
					<td><b><img src="${ctx}/static/images/a_33.jpg" ></b>职责列表</td>
				</tr>
			</table>
			<div class="table_auto scroll_auto">
		 		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped table-bordered table-condensed table-hover ">
	    			<tr class="tbth">
			    		<td>职责名称</td>
			    		<td>职责编码</td>
			    		<td>职责描述</td>
			    		<td>生效日期</td>
			    		<td>失效日期</td>     		
			    		<td>操作</td>
			    	</tr>
			    	<c:forEach var="list"  items="${v_responsibility_list}" varStatus="vstatus">
				    	<tr>
				    		<td>${list.responsibilityName}</td>
				    		<td>${list.responsibilityCode}</td>
				    		<td>${list.responsibilityDesc}</td>
				    		<td>${list.beginDate}</td>
	    					<td>${list.endDate}</td>			
				    		<td>
				    			<a href="gotoViewResponsibility.do?responsibilityId=${list.responsibilityId}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
				    			<a href="gotoUpdateResponsibility.do?responsibilityId=${list.responsibilityId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
				    		</td>
				    	</tr>
			    	</c:forEach> 
		    	</table>
		    	<tags:pagination /> 
		    </div>
	    </div>
    </div>
 </form>   
</body>
</html>