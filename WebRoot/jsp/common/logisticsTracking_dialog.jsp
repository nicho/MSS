<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>物流跟踪</title>
<link href="${ctx}/static/css/${sessionScope.initDomainCSS}" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/common.css" type="text/css" rel="stylesheet" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/json2.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.collapse_cookie_storage.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery.SuperSlide.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/js/pagination.js"></script>

<script type="text/javascript">
	
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
</style>
</head>
<body style="border: 0px; overflow: auto;height: 480px\9;">
	<div class="add_top">&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;物流跟踪</div>
	<!--搜索部分begin-->
	<div class="row-fluid aufour mar_2">
		<form class="form-search" action="${ctx}/viewPersonList.do">

			<table class="tableopen" width="100%">
				<tr>
					<td class="text_rg title_font bzh ">挑库单号</td>
					<td class="text_lf">${delivery_no}</td>
					<td class="text_rg title_font bzh">承运商</td>
					<td class="text_lf">${head.logistics_company_name}</td>
			 
				</tr>
			</table>

		</form>
	</div>
	<!--搜索部分over-->

	<!--列表部分begin-->
	<div class="row-fluid aufive mar_2">
		<div class="aulist_3">
			<b></b>物流跟踪
		</div>
		<div class="table_auto scroll_auto" style="overflow-x: auto; overflow-y: hidden;">
			<table id="contentTable" width="90%" class="table table-striped table-bordered table-condensed table-hover ">
				<tr class="tbth">
					<td class="bzh">序号</td>
					<td class="bzh">时间</td>
					<td class="bzh">站点名称</td>
					<td class="bzh">联系电话</td>
					<td class="bzh">状态</td>
					<td class="bzh">说明</td>
					<td class="bzh">异常说明</td>

				</tr>
				<c:forEach var="list" items="${track}" varStatus="vstatus">
					<tr>
						 
						<td title="${list.track_order}">${list.track_order}</td>
						<td title="${list.track_date}">${list.track_date}</td>
						<td title="${list.track_carrier}">${list.track_carrier}</td>
						<td title="${list.track_carrier_phone}">${list.track_carrier_phone}</td>
						<td title="${list.track_status}">${list.track_status}</td>
						<td title="${list.track_detail}">${list.track_detail}</td>
						<td title="${list.track_exception}">${list.track_exception}</td>

					</tr>
				</c:forEach>
			</table>
 
		</div>
	</div>
</body>
</html>