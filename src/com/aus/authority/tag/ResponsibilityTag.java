package com.aus.authority.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;

import com.aus.authority.exception.AuthorityException;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.service.AuthorityService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.SpringContextHolder;

/***
 * 
 * 职责选择
 * 
 * @author duzh
 * 
 */
public class ResponsibilityTag extends TagSupport {
	private static Logger logger = Logger.getLogger(ResponsibilityTag.class);
	private static final long serialVersionUID = 1L;
	protected String sessionId;
	protected String url;
	
	protected String className;
	protected String style;
	protected String projectName;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}


	public int doEndTag() throws JspTagException {

		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		
		AuthorityService authorityService = SpringContextHolder.getBean("authorityService");
		
		Map<String, String> urlResponsibilityMap = authorityService.getUrlResponsibilityMap(shiroUser.getUserId(),
				(HttpServletRequest) pageContext.getRequest());
		
		if( urlResponsibilityMap == null ) {
			return EVAL_PAGE;
		}
		
		String responsibilityId = urlResponsibilityMap.get(url);

		if (StringUtils.isBlank(responsibilityId)) {
			throw new AuthorityException("没有权限访问此功能");
		}

		List<ResponsibilityDto> responsibilitys = AuthorityUtils
				.findUserResponsibilitys(sessionId, url);
		
		StringBuilder sb = new StringBuilder();

		if (responsibilitys.size() == 1) {
			sb.append("<input type='hidden' id='responsibilityId' name='responsibilityId' value='"
				 + responsibilityId+ "' >");
			try { 
				pageContext.getOut().write(sb.toString());
			} catch (IOException e) {
				logger.error(e);
			}
			return EVAL_PAGE;
		}
		
		if(StringUtils.equals("mobile", projectName)) {
			sb.append("<li><span>职责</span>");			
		} else {
			sb.append("<li class=\"rgcont_li\">");
			sb.append("<div class=\"rgcont_wz\">职责</div>");
			sb.append("<div class=\"rgcont_rr\">");
		}
		
		sb.append("<select ");
		
		if( StringUtils.isNotBlank(this.style) ){
			sb.append(" style='");
			sb.append(this.style);
			sb.append("' ");
		}
		if( StringUtils.isNotBlank(this.className) ){
			sb.append(" class='");
			sb.append(this.className);
			sb.append("' ");
		} else {
			sb.append(" class=\"select1\"");
		}

		sb.append(" onchange=\"RefreshResponsility('" + url
				+ "',this.value)\">");

		for (ResponsibilityDto responsibility : responsibilitys) {

			sb.append("<option value='" + responsibility.getResponsibilityId()
					+ "' ");

			if (StringUtils.equals(responsibility.getResponsibilityId(),
					responsibilityId)) {
				sb.append(" selected='selected' ");
			}

			sb.append(">");

			sb.append(responsibility.getResponsibilityName());

			sb.append("</option>");

		}
		
		if(StringUtils.equals("mobile", projectName)) {
			sb.append("</select></li>");
		} else {
			sb.append("</select>");
			sb.append("</div></li>"); 
		}
		
		try { 
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			logger.error(e);
		}

		return EVAL_PAGE;
	}

}
