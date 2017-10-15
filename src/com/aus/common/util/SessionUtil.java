package com.aus.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.aus.common.model.ToDoListDto;
import com.aus.user.util.Constant;

public class SessionUtil {

	public static void initDomainCSSSession(HttpServletRequest request) {
		// 获取域名
		StringBuffer url = request.getRequestURL();
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
		// 获取带部署环境上下文的域名
		// String tempContextUrl = url.delete(url.length() -
		// request.getRequestURI().length(),
		// url.length()).append(request.getServletContext().getContextPath()).append("/").toString();

		String initDomainCSS = Constant.INIT_DOMAINCSS_DEFAULT;
		Iterator<Entry<String, String>> it = ReadProperties.getDomainMap().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) it.next();
			if (tempContextUrl.equals(entry.getValue())) {
				initDomainCSS = entry.getKey() + ".css";
			}
		}

		request.getSession().setAttribute("initDomainCSS", initDomainCSS);
	}

	public static void cleanNavigationSession(HttpServletRequest request) {
		request.getSession().setAttribute("menuName", "");
		request.getSession().setAttribute("h1_id", "");
		request.getSession().setAttribute("h1_index", "");
		request.getSession().setAttribute("h2_id", "");
		request.getSession().setAttribute("h2_index", "");
	}

	 

	public static ToDoListDto putWorkFlowZeroValue(ToDoListDto toDoListDto) {
		if (toDoListDto == null) {
			toDoListDto = new ToDoListDto();
		}

		if (toDoListDto.getStartDate() == null) {
			toDoListDto.setStartDate("");
		}
		if (toDoListDto.getEndDate() == null) {
			toDoListDto.setEndDate("");
		}
		if (toDoListDto.getFlowstate() == null) {
			toDoListDto.setFlowstate("A,B,M");
		}
		if (toDoListDto.getFlowtype() == null) {
			toDoListDto.setFlowtype("");
		}
		if (toDoListDto.getFlowtype() == null) {
			toDoListDto.setFlowtype("");
		}
		if (toDoListDto.getSubject() == null) {
			toDoListDto.setSubject("");
		} else {
			try {
				toDoListDto.setSubject(URLEncoder.encode(toDoListDto.getSubject().toString(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (toDoListDto.getBillCode() == null) {
			toDoListDto.setBillCode("");
		}
		if (toDoListDto.getCommitUserName() == null) {
			toDoListDto.setCommitUserName("");
		} else {
			try {
				toDoListDto.setCommitUserName(URLEncoder.encode(toDoListDto.getCommitUserName().toString(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (toDoListDto.getHasNotice() == null) {
			toDoListDto.setHasNotice("");
		}
		if (toDoListDto.getHasNotice() == null) {
			toDoListDto.setHasNotice("");
		}
		if (toDoListDto.getWpageNum() == null) {
			toDoListDto.setWpageNum("1");
		}
		if (toDoListDto.getWpageSize() == null) {
			toDoListDto.setWpageSize("10");
		}
		return toDoListDto;
	}

}
