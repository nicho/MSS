<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<title>资源查看</title>
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

		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">

				<div class="add_top" style>
					<b></b>查看资源
					<div class="list_an">
						<button id="cancel_btn" class="btn btn-primary" type="button"
							onClick="location.href='gotoResourceList.do'">
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
										资源编码
									</div>
									<div class="rgcont_rr">
										${resource.resourceCode}
									</div>
								</li>
								<li class="rgcont_li">
									<div class="rgcont_wz">
										资源名称
									</div>
									<div class="rgcont_rr">
										${resource.resourceName}
									</div>
								</li>
								<li class="rgcont_li">
									<div class="rgcont_wz">
										资源描述
									</div>
									<div class="rgcont_rr">
										${resource.resourceDesc}
									</div>
								</li>
								<li class="rgcont_li">
									<div class="rgcont_wz">
										关联功能
									</div>
									<div class="rgcont_rr">
										${resource.menuNodeName}
									</div>
								</li>

							</ul>
						</div>
					</div>
				</div>
				<!-- 搜索条件 -->
				<div class="dbzn ">
					<input id="cancel_btn" class="btn buttom1" type="button" value="取消"
						onclick="history.back()" />
				</div>
			</div>
	</body>
</html>