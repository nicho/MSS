<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title><spring:message code='view_notice' />
	</title>
	<script language="javascript" type="text/javascript">
	</script>
</head>
	<body>
		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<fieldset>
					<div class="add_top" style>
						&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;
						<spring:message code='view_notice' />
						<div class="list_an">
							<button id="cancel_btn" class="btn btn-primary" type="button"
								onClick="returnUrl()">
								<spring:message code='button_return' />
							</button>
						</div>
					</div>
					<!-- 上半部分 -->
					<div class="row-fluid autwo mar_2">
						<div class="view_box">
   							 <ul>
   							    <li>
									${notificationDto.processName},单据号：<a onclick="showMenuPage3('${ctx}/gotoChoiceBussinessIndex.do?notificationId=${notificationId}')">${notificationDto.billId}</a>
								</li>
						        <li>
									${notificationDto.notificationContent}
								</li>
							<ul>
						</div>
					</div>
			</div>
		</div>
	</body>
</html>