<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<c:forEach items="${v_navigationList}" var="v_navigation"
	varStatus="status">
	<c:if test="${v_navigation.nodeType == '10'}">
		<h1 onClick="javascript:ShowMenu(this,${status.index})"
			id='menu_${v_navigation.nodeCode}_h1'>
			<a href="javascript:void(${status.index})"><div class="icon">
					<img src="${ctx}/static/images/${v_navigation.icon}">
				</div>${v_navigation.text}</a><b></b>
		</h1>
		<span class="no"> <c:forEach items="${v_navigation.children}"
				var="v_navigation_two" varStatus="status_two">
				<c:if test="${v_navigation_two.nodeType == '10'}">
					<h2 onClick="javascript:ShowMenu(this,${status_two.index})"
						id='menu_${v_navigation_two.nodeCode}_h2_${status_two.index+1}'>
						<a><b></b>${v_navigation_two.text}</a></a>
					</h2>
					<ul class="no">
						<c:forEach items="${v_navigation_two.children}"
							var="v_navigation_three" varStatus="status_three">
							<li class="li1">
								<a
									id="aNode_${status.index}_${status_two.index}_${status_three.index}"
									onclick="showMenuPage('${ctx}/${v_navigation_three.url}',
													' ${v_navigation.text} > ${v_navigation_two.text}> ${v_navigation_three.text} ',
							    					'menu_${v_navigation.nodeCode}_h1','${status.index}',
							    					'menu_${v_navigation_two.nodeCode}_h2_${status_two.index+1}','${status_two.index}',
							    					'aNode_${status.index}_${status_two.index}_${status_three.index}')">${v_navigation_three.text}</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${v_navigation_two.nodeType == '20'}">
					<ul class="fix"
						id='menu_${v_navigation_two.nodeCode}_h2_${status_two.index+1}'>
						<li class="li1" class="fix">
							<a id="aNode_${status.index}_${status_two.index}"
								onclick="showMenuPage('${ctx}/${v_navigation_two.url}',' ${v_navigation.text} > ${v_navigation_two.text} ',
								'menu_${v_navigation.nodeCode}_h1','${status.index}',
								'menu_${v_navigation_two.nodeCode}_h2_${status_two.index+1}','${status_two.index}',
								'aNode_${status.index}_${status_two.index}')">${v_navigation_two.text}</a>
						</li>
					</ul>
				</c:if>
			</c:forEach> </span>
	</c:if>
	<c:if test="${v_navigation.nodeType == '20'}">
		<ul class="fix">
			<li class="li1">
				<a id="aNode_${status.index}"
					onclick="showMenuPage('${ctx}/${v_navigation.url}',' ${v_navigation.text} ',null,'6',null,'0',
					'aNode_${status.index}')">${v_navigation.text}</a>

				<%--<a onclick="javascript:window.location.href ='${ctx}/${v_navigation.url}'">${v_navigation.text}</a>
			--%>
			</li>
		</ul>
	</c:if>
</c:forEach>
