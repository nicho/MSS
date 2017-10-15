package com.aus.authority.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springside.modules.cache.memcached.SpyMemcachedClient;

import com.aus.authority.annotation.AccreditAnnotation;
import com.aus.authority.service.AuthorityService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.OAUtils;
import com.aus.common.util.SpringContextHolder;
import com.aus.user.util.MemcachedObjectType;

/**
 * 
 * 权限拦截器
 * 
 * @author duzh
 * 
 */

public class PermissionInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger(PermissionInterceptor.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Method method = handlerMethod.getMethod();

		AccreditAnnotation annotation = method.getAnnotation(AccreditAnnotation.class);

		if (annotation != null) { 
			
			String url = annotation.url();
			
			request.setAttribute("initUrl", url);
			 
			
			if( StringUtils.equals(method.getName()+".do", url)) {		
				AuthorityUtils.setResponsibiltyKey(request,url);				
				return true;				
			}

			String resourceCode = annotation.resourceCode();

			if (StringUtils.isBlank(url)) {
				return false;
			}
			ShiroUser shiroUser = OAUtils.getUser();
			AuthorityService authorityService = SpringContextHolder.getBean("authorityService");
			Map<String, String> urlResponsibilityMap =authorityService.getUrlResponsibilityMap(shiroUser.getUserId(), request);
			
			if(StringUtils.equals(url, "diaryMobileList.do")) {
				
				if( urlResponsibilityMap == null || StringUtils.isBlank(urlResponsibilityMap.get(url))) {
				 
					
					SpyMemcachedClient spyMemcachedClient = SpringContextHolder.getBean("spyMemcachedClient");
					
					String key = MemcachedObjectType.USER.getPrefix() + "getUrlMemcached"  + shiroUser.getPersonId();
					
					String url1 = spyMemcachedClient.get(key);
					
					if(!StringUtils.isEmpty(url1)) {
						return true;
					}
				}
				return true;
			}
			 
			if (urlResponsibilityMap==null || StringUtils.isBlank(urlResponsibilityMap.get(url))) { 
				redirectIndex(request, response);
				return false;
			}

			if (StringUtils.isBlank(resourceCode)) { 
				
				if (CollectionUtils.isEmpty(AuthorityUtils
						.findUserResponsibilitys(request.getSession().getId(),
								url))) {
					return false;
				}

				return true;
			}

			if (!AuthorityUtils.isCanOperateResource(request,resourceCode, url)) { 
				redirectIndex(request, response);
				return false;
			}

		}
		
		return true;
	}
	 
	private void redirectIndex(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + "/index.do");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
