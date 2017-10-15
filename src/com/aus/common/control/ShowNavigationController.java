package com.aus.common.control;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aus.common.service.FlexValueService;
import com.aus.common.service.ProcessCodeSubClassService;
import com.aus.common.util.StringUtil;

@Controller
public class ShowNavigationController {
	private static Logger logger = Logger.getLogger(ShowNavigationController.class);

	@Autowired
	FlexValueService flexValueService;

	@Autowired
	ProcessCodeSubClassService processCodeSubClassService;

	@RequestMapping(value = "/showNavigation")
	public String showNavigation(HttpServletRequest request) {
		String url = request.getParameter("url");

		String menuName = request.getParameter("menuName");
		String h1_id = request.getParameter("h1_id");
		String h1_index = request.getParameter("h1_index");
		String h2_id = request.getParameter("h2_id");
		String h2_index = request.getParameter("h2_index");
		String aNode = request.getParameter("aNode");
		if (StringUtil.isValid(menuName) && StringUtil.isValid(h1_id) && StringUtil.isValid(h1_index) && StringUtil.isValid(h2_id) && StringUtil.isValid(h2_index)
				&& StringUtil.isValid(aNode)) {
			request.getSession().setAttribute("menuName", menuName);
			request.getSession().setAttribute("h1_id", h1_id);
			request.getSession().setAttribute("h1_index", h1_index);
			request.getSession().setAttribute("h2_id", h2_id);
			request.getSession().setAttribute("h2_index", h2_index);
			request.getSession().setAttribute("aNode", aNode);
			return "redirect:/" + url;
		} else {
			request.setAttribute("InfoMessage", "参数异常!");
			return "result";
		}

	}

	public FlexValueService getFlexValueService() {
		return flexValueService;
	}

	public void setFlexValueService(FlexValueService flexValueService) {
		this.flexValueService = flexValueService;
	}

	@RequestMapping(value = "setToolBar")
	@ResponseBody
	public String setToolBar(HttpServletRequest request) {
		String fal = request.getParameter("fal");
		request.getSession().setAttribute("ToolBarImage", fal);
		return fal;
	}
}
