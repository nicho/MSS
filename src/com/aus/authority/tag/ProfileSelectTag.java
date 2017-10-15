package com.aus.authority.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.aus.authority.util.AuthorityUtils;


/**
 * Profile值下拉列表展示标签
 * 
 * @author duzh
 *
 */
public class ProfileSelectTag extends TagSupport {
	private static Logger logger = Logger.getLogger(ProfileSelectTag.class);
	private static final long serialVersionUID = 1L;
	protected String id;
    protected String name;
    protected String className;
    protected String onclick;
    protected String onchange;
    protected String style;
    protected String title;
    protected String selvalue;
    protected String profileName;
    protected String sessionId;
    protected String url;
    protected String condition;//包含条件 传数据以逗号隔开
    protected String disCondition;//不包含条件 传数据以逗号隔开
    protected String readonly;//只读
    protected String disabled;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSelvalue() {
		return selvalue;
	}
	public void setSelvalue(String selvalue) {
		this.selvalue = selvalue;
	}
	 
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
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
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
	public int doStartTag()
	    throws JspException
	{
		return EVAL_BODY_INCLUDE;
	}
	
	public String getDisCondition() {
		return disCondition;
	}
	public void setDisCondition(String disCondition) {
		this.disCondition = disCondition;
	}
	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspTagException
	{
		
		HttpSession session = ((HttpServletRequest) pageContext.getRequest()).getSession();
		
		Map<String, String> urlResponsibilityMap = (Map<String, String>) session.getAttribute("urlResponsibilityMap");

		String responsibilityId = urlResponsibilityMap.get(url);
		
		if ( StringUtils.isBlank(responsibilityId) ) {
			return EVAL_PAGE;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<select ");
		if( StringUtils.isNotBlank(this.id) ){
			sb.append(" id='");
			sb.append(this.id);
			sb.append("' ");
		}
		if( StringUtils.isNotBlank(this.name) ){
			sb.append(" name='");
			sb.append(this.name);
			sb.append("' ");
		}
		if( StringUtils.isNotBlank(this.style) ){
			sb.append(" style='");
			sb.append(this.style);
			sb.append("' ");
		}
		if( StringUtils.isNotBlank(this.className) ){
			sb.append(" class='");
			sb.append(this.className);
			sb.append("' ");
		}
		if( StringUtils.isNotBlank(this.onclick) ){
			sb.append(" onclick='");
			sb.append(this.onclick);
			sb.append("' ");
		}
		if( StringUtils.isNotBlank(this.onchange) ){
			sb.append(" onchange='");
			sb.append(this.onchange);
			sb.append("' ");
		}
		if(StringUtils.isNoneBlank(this.readonly)) {
			sb.append(" readonly='");
			sb.append(this.readonly);
			sb.append("' ");
		}
		if(StringUtils.isNoneBlank(this.disabled)) {
			sb.append(" disabled='");
			sb.append(this.disabled);
			sb.append("' ");
		}
		
		sb.append(" >");
		
		List<Map<String, String>> map = null;
		try {
			map = AuthorityUtils.getProDictByName(profileName);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for( Map entry : map ) {
			sb.append("<option value='"+entry.get("code")+"' ");
			if( StringUtils.isNotBlank(this.selvalue) && ((String) entry.get("code")).equalsIgnoreCase(this.selvalue)) {
				sb.append(" selected='selected' ");
			}
			sb.append(" >");
			sb.append(entry.get("text"));
			sb.append("</option>");			
		}
		
		sb.append("</select>");
		
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			logger.error(e);
		}
		
		return EVAL_PAGE;
	}
    

}
