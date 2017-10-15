package com.aus.authority.tag;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import com.aus.authority.util.AuthorityUtils;

/**
 * 简单权限标签,控制页面元素显示与否
 * 
 * @author duzh
 * 
 */
public class AuthorityTag extends TagSupport {

	private static final long serialVersionUID = 6864181634741370543L;
	protected String sessionId;
	protected String url;
	protected String resourceCode;

	public int doStartTag() throws JspException {

		boolean hasPermission = AuthorityUtils.isCanOperateResource(
				(HttpServletRequest) pageContext.getRequest(), resourceCode,
				url);

		if (hasPermission)
			return EVAL_BODY_INCLUDE;
		else
			return SKIP_BODY;
	}

	public int doEndTag() throws JspTagException {
		return EVAL_PAGE;
	}

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

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

}
