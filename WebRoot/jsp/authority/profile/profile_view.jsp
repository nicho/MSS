<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<title>查看Profile</title>
		<link href="${ctx}/static/ligerUI/skins/Aqua/css/ligerui-tree.css"
			rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/jquery/jquery.js" type="text/javascript">
</script>
		<script src="${ctx}/static/ligerUI/js/core/base.js"
			type="text/javascript">
</script>
		<script src="${ctx}/static/ligerUI/js/plugins/ligerTree.js"
			type="text/javascript">
</script>
<script type="text/javascript">
</script>

	</head>
	<body>
		<c:if test="${not empty InfoMessage}">
			<div id="InfoMessage" class="alert alert-success">
				<button data-dismiss="alert" class="close">
					×
				</button>
				${InfoMessage}
			</div>
		</c:if>
		<form class="form-search" action="${ctx}/updateProfile.do" id="inputForm">
			<input type="hidden" name="menuName" value="${param.menuName}" />
			<input type="hidden" name="h1_id" value="${param.h1_id}" />
			<input type="hidden" name="h1_index" value="${param.h1_index}" />
			<input type="hidden" name="h2_id" value="${param.h2_id}" />
			<input type="hidden" name="h2_index" value="${param.h2_index}" />
			<input type="hidden" name="h2_index" value="${param.h2_index}" />

			<div style="overflow: auto" id="area-overflow">
				<div class="main-box" id="area-right">

					<div class="add_top" style>
						<b></b>查看Profile
						<div class="list_an">
							<button id="cancel_btn" class="btn btn-primary" type="button"
								onClick="location.href='gotoProfileList.do'">
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
											Profile编码
										</div>
										<div class="rgcont_rr">
											${profile.profileCode}
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											Profile名称
										</div>
										<div class="rgcont_rr">
											${profile.profileName}
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											Profile描述
										</div>
										<div class="rgcont_rr">
											${profile.profileDesc}
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											SQL
										</div>
										<div class="rgcont_rr">
											<textarea class="textarea1 required" name="sourceSql" readonly="readonly"
												id="sourceSql" cols="" rows="">${profile.sourceSql}</textarea>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>