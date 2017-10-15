package com.aus.user.control;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aus.common.util.SessionUtil;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request) { 

		SessionUtil.initDomainCSSSession(request); 
		if(SecurityUtils.getSubject().getPrincipals()!=null && SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal()!=null)
			return "redirect:/index.do";
		else
			return "login";
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(String userName, Model model) {
	//	model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName); 
		if(SecurityUtils.getSubject().getPrincipals()!=null && SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal()!=null)
			return "redirect:/index.do";
		else
			return "login";
	}
 
}
