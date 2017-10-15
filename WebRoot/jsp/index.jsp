<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
 <head> 
  
  <script type="text/javascript"> 
			$(document).ready(function() {
				
				 var Sys1 = {};
					var ua1 = navigator.userAgent.toLowerCase();
					var s1;
					(s1 = ua1.match(/msie ([\d.]+)/)) ? Sys1.ie = s1[1] :0;
				   if ((Sys1.ie && Sys1.ie<8.0)){
					   $("#infoMessageText1").html("<font style='font-weight:bold;color:red'><spring:message code='alarm_message'/></font>");
						$('#InfoMessage1').window('open'); 
				   }
				
				
				jQuery.ajax({
					type : "POST",
					url : "${ctx}/initEmailSession.do",
					dataType : "json",
					cache : false,
					async : true,
					data : "",
					success : function(result) {  
						if(result!=null && result!="null" && result!='')
							$("#indexPageEmailCount").html(result);
					}
				});
				
				var orgId = '${orgId}';
				if(orgId !='81'){
				jQuery.ajax({
					type : "POST",
					url : "${ctx}/initPersonTask.do",
					dataType : "json",
					cache : false,
					async : true,
					data : "",
					success : function(result) {
						if (result.Manger_PersonTask_dx != null) { 
							$("#Manger_PersonTask_dx").html(result.Manger_PersonTask_dx);
						} 
						if (result.Manger_PersonTask_dx_num != null) { 
							$("#Manger_PersonTask_dx_num").html(result.Manger_PersonTask_dx_num);
						} 
						if (result.Manger_PersonTask_dx_Percent_w != null) { 
							$("#Manger_PersonTask_dx_Percent").html(result.Manger_PersonTask_dx_Percent);
							$("#Manger_PersonTask_dx_Percent_w").attr("style","width:"+result.Manger_PersonTask_dx_Percent_w);
						} 
 
						if (result.Manger_PersonTask_xk != null) { 
							$("#Manger_PersonTask_xk").html(result.Manger_PersonTask_xk);
						} 
						if (result.Manger_PersonTask_xk_Percent_w != null) { 
							$("#Manger_PersonTask_xk_Percent").html(result.Manger_PersonTask_xk_Percent);
							$("#Manger_PersonTask_xk_Percent_w").attr("style","width:"+result.Manger_PersonTask_xk_Percent_w);
						} 
						if(result.Manger_PersonTask_dx != null || result.Manger_PersonTask_dx_num != null || result.Manger_PersonTask_dx_Percent_w != null
								|| result.Manger_PersonTask_xk != null || result.Manger_PersonTask_xk_Percent_w != null)
							{ 
								$("#manger_report").attr("style","");
							}
						
						
						 
						if (result.PersonTask_dx != null) { 
							$("#PersonTask_dx").html(result.PersonTask_dx);
						} 
						if (result.PersonTask_dx_num != null) { 
							$("#PersonTask_dx_num").html(result.PersonTask_dx_num);
						} 
						if (result.PersonTask_dx_Percent_w != null) { 
							$("#PersonTask_dx_Percent").html(result.PersonTask_dx_Percent);
							$("#PersonTask_dx_Percent_w").attr("style","width:"+result.PersonTask_dx_Percent_w);
						} 
 
						if (result.PersonTask_xk != null) { 
							$("#PersonTask_xk").html(result.PersonTask_xk);
						} 
						if (result.PersonTask_xk_Percent_w != null) { 
							$("#PersonTask_xk_Percent").html(result.PersonTask_xk_Percent);
							$("#PersonTask_xk_Percent_w").attr("style","width:"+result.PersonTask_xk_Percent_w);
						} 
						
						
						if(result.PersonTask_dx != null || result.PersonTask_dx_num != null || result.PersonTask_dx_Percent_w != null
								|| result.PersonTask_xk != null || result.PersonTask_xk_Percent_w != null)
							{ 
								$("#my_report").attr("style","");
							}
						
						
						if (result.PersonTask_hk != null) { 
							$("#PersonTask_hk").html(result.PersonTask_hk);
						} 
						if (result.PersonTask_hk_Percent_w != null) { 
							$("#PersonTask_hk_Percent").html(result.PersonTask_hk_Percent);
							$("#PersonTask_hk_Percent_w").attr("style","width:"+result.PersonTask_hk_Percent_w);
						} 
						
						if (result.PersonTask_dh != null) { 
							$("#PersonTask_dh").html(result.PersonTask_dh);
						} 
						if (result.PersonTask_dh_Percent_w != null) { 
							$("#PersonTask_dh_Percent").html(result.PersonTask_dh_Percent);
							$("#PersonTask_dh_Percent_w").attr("style","width:"+result.PersonTask_dh_Percent_w);
						} 
					}
				});
				}
				
				setInterval("refreshIndex();", 300000);
			});
			function refreshIndex() {
				location.reload();
			}
			function showMoreBackLog() {
			}

			function gotoApprovePage() {

			}
		</script>

</head>
  <body>  
        <div style="overflow:auto" id="area-overflow">
        <div class="main-box" id="area-right">
        	<!-- 具体内容 -->
			<ul class="au_boxul">
			 <li class="au_boxli">
			     <div class="au_line au_blue"><b><img src="${ctx}/static/images/a_10.jpg" ></b><spring:message code='daibanshixiang'/> <a class="a_more" href="${ctx}/userWorkflowToDoList.do?flowstate=A,B,M"><spring:message code='gengduo'/> >></a></div>
			     <ul class="news_box"> 
			        <c:forEach var="list"  items="${backLogList}" varStatus="vstatus">
			        	<li class="news_list">${vstatus.index + 1}、
			        	<c:choose>
			        	<c:when test="${(list.status eq 'A' || list.status eq 'B') &&   list.endDate eq null }">
			        	
			        	<a onclick="showMenuPage3('${ctx}/gotoBussnessApprovePage.do?notificationId=${list.notificationId}&approveFlag=Y&processCode=${list.processCode}&billId=${list.billId}')">${list.subject}</a>
 
			        	</c:when>
			        	<c:otherwise>
			        	<a onclick="showMenuPage3('${ctx}/gotoBussnessApprovePage.do?notificationId=${list.notificationId}&processCode=${list.processCode}&billId=${list.billId}')">${list.subject}</a>
			        	</c:otherwise>
			        	</c:choose>
                       <span>${list.beginDate}</span> </li> 
	                  </c:forEach>
			       
			     </ul>
			  </li>
	        
			 <li class="au_boxli" id="manger_report" style="display: none;">
			    <div class="au_line au_blue"><b><img src="${ctx}/static/images/a_10.jpg" ></b>管理任务进度  </div>
			    <ul class="news_box">
			        <li class="news_list"><div style="float: left;"> 月度任务&nbsp;&nbsp;</div>
				       
				   </li>	
			       <li class="news_list"><div style="float: left;"> 动销&nbsp;&nbsp;</div>
				       <div class="progress progress-success" style="  width: 65%;">
						 <div class="bar bar-success" style="width: 0%;" id="Manger_PersonTask_dx_Percent_w"></div><font id="Manger_PersonTask_dx_Percent"></font>
						</div><span style="margin-top: -40px;"><c:if test="${orgId !='181'}"><a class="a_more" href="${ctx}/DynamicSalesListViewManager.do?indexfal=Y"><font id="Manger_PersonTask_dx">0</font></a></c:if><c:if test="${orgId =='181'}"><font id="Manger_PersonTask_dx">0</font></c:if>&nbsp;元(单品数量:<font id="Manger_PersonTask_dx_num">0</font>)</span> 
				   </li>			       
			       <li class="news_list"><div style="float: left;"> 新客&nbsp;&nbsp;</div>
				       <div class="progress progress-success" style="  width: 65%;">
						 <div class="bar bar-success" style="width: 0%;" id="Manger_PersonTask_xk_Percent_w"></div><font id="Manger_PersonTask_xk_Percent"></font>
						</div><span style="margin-top: -40px;"><c:if test="${orgId !='181'}"><a class="a_more" href="${ctx}/NewGuestListViewManager.do?indexfal=Y"><font id="Manger_PersonTask_xk">0</font></a></c:if><c:if test="${orgId =='181'}"><font id="Manger_PersonTask_xk">0</font></c:if>&nbsp;人</span> 
				   </li>
			     </ul>
			  </li>
			
			  <li class="au_boxli">
			     <div class="au_line au_blue"><b><img src="${ctx}/static/images/a_10.jpg" ></b> <spring:message code='notice'/><a class="a_more" href="${ctx}/docApplyList.do?docType=1"><spring:message code='gengduo'/>>></a></div>
			     <ul class="news_box">
			      <c:forEach var="list"  items="${docAuthList}" varStatus="vstatus">
			       <li class="news_list">${vstatus.index + 1}、<a href="${ctx}/docApplyView.do?docId=${list.docId}&docType=1">${list.docTitle}</a><span><fmt:formatDate value="${list.docEffectBeginDate}" pattern="yyyy-MM-dd" /></span> </li>
			      </c:forEach> 
			     </ul>
			  </li>
			 
			 <li class="au_boxli" id="my_report" style="display: none;">
			    <div class="au_line au_blue"><b><img src="${ctx}/static/images/a_10.jpg" ></b>个人月度任务进度 </div>
			    <ul class="news_box" style="overflow: hidden !important;">
			       
			       <li class="news_list"><div style="float: left;"> 动销&nbsp;&nbsp;</div>
				       <div class="progress progress-success" style="  width: 65%;">
						 <div class="bar bar-success" style="width: 0%;" id="PersonTask_dx_Percent_w"></div><font id="PersonTask_dx_Percent"></font>
						</div><span style="margin-top: -40px;"><a class="a_more" href="${ctx}/DynamicSalesListView.do?indexfal=Y"><font id="PersonTask_dx"></font></a>&nbsp;元(单品数量:<font id="PersonTask_dx_num">0</font>)</span> 
				   </li>			       
			       <li class="news_list"><div style="float: left;"> 新客&nbsp;&nbsp;</div>
				       <div class="progress progress-success" style="  width: 65%;">
						 <div class="bar bar-success" style="width: 0%;" id="PersonTask_xk_Percent_w"></div><font id="PersonTask_xk_Percent"></font>
						</div><span style="margin-top: -40px;"><a class="a_more" href="${ctx}/NewGuestListView.do?indexfal=Y"><font id="PersonTask_xk">0</font></a>&nbsp;人</span> 
				   </li>
				   
				  <li class="news_list"><div style="float: left;"> 回款&nbsp;&nbsp;</div>
				       <div class="progress progress-success" style="  width: 65%;">
						 <div class="bar bar-success" style="width: 0%;" id="PersonTask_hk_Percent_w"></div><font id="PersonTask_hk_Percent"></font>
						</div><span style="margin-top: -40px;"><a class="a_more" href="${ctx}/ReceivedPaymentsList.do?indexfal=Y"><font id="PersonTask_hk">0</font></a>&nbsp;元</span> 
				   </li>
				   
				   <li class="news_list"><div style="float: left;"> 调货&nbsp;&nbsp;</div>
				       <div class="progress progress-success" style="  width: 65%;">
						 <div class="bar bar-success" style="width: 0%;" id="PersonTask_dh_Percent_w"></div><font id="PersonTask_dh_Percent"></font>
						</div><span style="margin-top: -40px;"><a class="a_more" href="${ctx}/WitheredCargoList.do?indexfal=Y"><font id="PersonTask_dh">0</font></a>&nbsp;元</span> 
				   </li>
			     </ul>
			  </li>
			  <li class="au_boxli">
			     <div class="au_line au_blue"><b><img src="${ctx}/static/images/a_10.jpg" ></b><spring:message code='bylaw'/><a class="a_more" href="${ctx}/approvalList.do"><spring:message code='gengduo'/>>></a></div>
			      <ul class="news_box">
			      <c:forEach var="list"  items="${systemDocAuthList}" varStatus="vstatus">
			       <li class="news_list">${vstatus.index + 1}、<a href="${ctx}/approvalView.do?docId=${list.docId}">${list.docTitle}</a><span><fmt:formatDate value="${list.docEffectBeginDate}" pattern="yyyy-MM-dd" /></span> </li>
			      </c:forEach> 
			     </ul>
			  </li>
			  <li class="au_boxli">
			    <div class="au_line au_blue"><b><img src="${ctx}/static/images/a_10.jpg" ></b>KPI <a class="a_more" href="#"><spring:message code='gengduo'/>>></a></div>
			    <ul class="news_box">
			       <li class="news_list">1、<a href="#">程序开发中，敬请期待....</a><span>2015-2-1</span> </li>			       
			     </ul>
			  </li>
			</ul>
			<!-- 具体内容 -->	
        </div>
        </div>
    
  </body>
</html>
