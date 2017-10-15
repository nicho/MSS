package com.aus.authority.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.aus.authority.model.TreeDTO;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.SessionUtil;
import com.aus.common.util.SpringContextHolder;


/**
 * 
 * URL权限过滤器
 * 
 * @author duzh
 *
 */
public class AuthorityFilter implements Filter {
	

	List<String> notValidationUrl;//白名单
    
	/**
	 * 
	 * 销毁
	 * 
	 */
	public void destroy() {
		
	}

	/**
	 * 
	 * URL权限验证
	 * 
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
	//	String referer = request.getHeader("referer"); 

//		if (referer == null || !referer.contains(request.getServerName())) {
//
//			request.getRequestDispatcher("/login.do").forward(servletRequest, servletResponse);
//			
//			return;
//			
//		} 
		
		if( request.getSession().getAttribute("initDomainCSS") == null ) {
			
			SessionUtil.initDomainCSSSession(request);
			
		}
		
		
		String url = request.getRequestURI();

		int beginIndex = request.getContextPath().length();

		url = url.substring(beginIndex);

		while (url.startsWith("/")) {
			url = url.substring(1);
		}

		int endIndex = url.indexOf("?");
		
		if (endIndex >= 0) {
			url = url.substring(0, endIndex);
		}
		
		endIndex = url.indexOf(";"); 
		
		if (endIndex >= 0) {
			url = url.substring(0, endIndex);
		}
		
		if (notValidationUrl.contains(url)) {
			chain.doFilter(servletRequest, servletResponse);

			return;
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
		
		AuthorityUtils.userLogin(request.getSession().getId(), shiroUser.getUserId());
		
		if(	request.getSession().getAttribute("v_navigationList")==null)
		{
			String sessionId = request.getSession().getId();
			
			List<TreeDTO> v_navigationList = AuthorityUtils.findLogonNavigationTrees(sessionId, shiroUser.getLanguage());
			
			request.getSession().setAttribute("v_navigationList", v_navigationList);
		}
		


		/**
		 * URL权限验证
		 */
		//boolean isUserResource = AuthorityUtils.isLogonResource(request.getSession().getId(), url);
		
		chain.doFilter(servletRequest, servletResponse);

		return;
	
	}

	/**
	 * 
	 * 初始化白名单
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void init(FilterConfig filterConfig) throws ServletException {
		notValidationUrl = SpringContextHolder.getBean("notValidationUrl", java.util.List.class);
	}

}
