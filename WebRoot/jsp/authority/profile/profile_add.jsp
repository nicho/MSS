<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html> 
	<head>
		<title>新增Profile</title>
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
		<form class="form-search" action="${ctx}/saveProfile.do" id="inputForm">
			<input type="hidden" id="funs" name="funs" />

			<div style="overflow: auto" id="area-overflow">
				<div class="main-box" id="area-right">

					<div class="add_top" style>
						<b></b>新增Profile
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
											<input type="text" class="input1 required" name="profileCode"
												id="profileCode" />
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											Profile名称
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="profileName"
												id="profileName" />
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											Profile描述
										</div>
										<div class="rgcont_rr">
											<input type="text" class="input1 required" name="profileDesc"
												id="profileDesc" />
										</div>
									</li>
									<li class="rgcont_li">
										<div class="rgcont_wz">
											SQL
										</div>
										<div class="rgcont_rr">
											<textarea class="textarea1 required" name="sourceSql"
												id="sourceSql" cols="150" rows="150"></textarea>
										</div>
									</li>

								</ul>
							</div>
						</div>
					</div>
					<!-- 搜索条件 -->
					<div class="dbzn ">
						<input id="submit_btn" class="btn buttom1 " type="submit"
							value="保存" />
						&nbsp;
						<input id="cancel_btn" class="btn buttom1" type="button"
							value="取消" onclick="history.back()" />
					</div>
				</div>
			</div>
		</form>
	</body>
</html>