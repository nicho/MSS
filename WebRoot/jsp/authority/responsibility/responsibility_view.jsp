<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="/oaTag" prefix="oaTag"%>
<html>
<head>
<title>查看职责</title>
</head>

	<body>
		<div id="errMessage">

		</div>
		<div style="overflow: auto" id="area-overflow">
			<div class="main-box" id="area-right">
				<form id="inputForm" action="${ctx}/addResponsibility.do"
					method="post" class="form-horizontal">
					<input type="hidden" name="responsibilityId" value="${responsibilityDto.responsibilityId}"/>
					<fieldset>

						<div class="add_top">
							&nbsp;&nbsp;&nbsp;●&nbsp;&nbsp;查看职责
							<div class="list_an">
								<button id="cancel_btn" class="btn btn-primary" type="button"
									onClick="location.href='gotoResponsibilityList.do'">
									<spring:message code='button_return' />
								</button>
							</div>
						</div>

						<!-- 第二部分 -->
						<div class="row-fluid autwo mar_2">
							<table class="table2">
								<tr>
									<td class="text_rg title_font bzh">
										职责名称
									</td>
									<td class="text_lf">
										${responsibilityDto.responsibilityName}
									</td>
									<td class="text_rg title_font bzh">
										职责编码
									</td>
									<td class="text_lf">
										${responsibilityDto.responsibilityCode}
									</td>
									<td class="text_rg title_font bzh">
										职责描述
									</td>
									<td class="text_lf">
										${responsibilityDto.responsibilityDesc}
									</td>
								</tr>
								<tr>
									<td class="text_rg title_font bzh">
										生效日期
									</td>
									<td class="text_lf">
										${responsibilityDto.beginDate}
									</td>
									<td class="text_rg title_font bzh">
										失效日期
									</td>
									<td class="text_lf">
										${responsibilityDto.endDate}
									</td>
									<td class="text_rg title_font bzh">
									</td>
									<td class="text_lf">
									</td>
								</tr>
							</table>
						</div>
						<!-- 第二部分 -->
						<!-- 列表部分begin -->

						<!--   增加角色       -->

						<div class="list_box mar_2">
							<div class="aulist_3">
								<b></b>
								关联角色
							</div>
							<table id="contentTable_roles"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth ">
									<td>
										角色名称
									</td>
									<td>
										角色编码
									</td>
									<td>
										角色描述
									</td>
								</tr>
								<c:forEach var="role"  items="${Resroles}" varStatus="vstatus">
									<tr class="tbth ">
										<td>
							           	 	${role.roleName}
							           	 	<input type="hidden" name="roleIds" value="${role.roleId}"/>
							           	</td>
							           	<td>
											${role.roleCode}
										</td>
										<td>
											${role.roleDesc}
										</td>
							        </tr>
						        </c:forEach>
							</table>
							
							
						</div>

						<!--    增加prifile                                  -->

						<div class="list_box mar_2">
							<div class="aulist_3">
								<b></b>
								关联Profile
							</div>
							<table id="contentTable_profiles"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth">
									<td>
										profile名称
									</td>
									<td>
										profile编码
									</td>
									<td>
										profile描述
									</td>
									<td>
										profile值
									</td>
								</tr>								
								<c:forEach var="profile"  items="${Resprofiles}" varStatus="vstatus">
									<tr class="tbth ">
										<td>
							           	 	${profile.profileName}
							           	 	<input type="hidden" name="roleIds" value="${role.roleId}"/>
							           	</td>
							           	<td>
											${profile.profileCode}
										</td>
										<td>
											${profile.profileDesc}
										</td>
										<td>
											<oaTag:profileSelect url="gotoResponsibilityList.do"
												profileName="${profile.profileCode}" name="profileVal" disabled="disabled"
												className="select1 required" selvalue="${profile.profileValue}"
												sessionId="${pageContext.session.id}"/>									
										</td>
							        </tr>
						        </c:forEach>
							</table>
						</div>
						
						<!--    增加 库存组织                               -->

						<div class="list_box mar_2">
							<div class="aulist_3">
								<b></b>
								关联库存组织
							</div>
							<table id="contentTable_responsibilityInvOrg"
								class="table table-striped table-bordered table-condensed table-hover ">
								<tr class="tbth">
									<td>
										库存组织
									</td>
									<td>
										编码
									</td>
								</tr>
								<c:forEach var="invList"  items="${invList}" varStatus="vstatus">
									<tr class="tbth ">
										<td width="50%">
								           	${invList.invOrganizationName}
							           	</td>
							           	<td>
											${invList.invOrganizationId}
										</td>
							        </tr>
						        </c:forEach>
								
							</table>
						</div>
						
						<!-- 列表部分over -->

						<div class="dbzn ">
							<input id="cancel_btn" class="btn buttom1" type="button"
								value="<spring:message code='Cancel'/>" onclick="history.back()" />
						</div>
					</fieldset>
				</form>
			</div>
		</div>	

	</body>
</html>