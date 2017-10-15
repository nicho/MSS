package com.aus.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/*****
 * 分页控件
 * 
 * @author j
 *
 */
public class PageHelperUtil {

	public static void PageHelperStartPage(HttpServletRequest request, Object searchParamObj) throws Exception {

		String str_pageNum = request.getParameter("pageNum");
		String str_pageSize = request.getParameter("pageSize");
		String isFromPage = request.getParameter("isFromPage");

		if (StringUtils.isEmpty(str_pageNum))
			str_pageNum = "1";
		if (str_pageNum.equals("null"))
			str_pageNum = "1";
		if (StringUtils.isEmpty(str_pageSize))
			str_pageSize = "10";
		if ("0".equals(str_pageNum))
			str_pageNum = "1";
		int pageNum = 0;
		int pageSize = 0;

		if (!StringUtils.equals(isFromPage, "Y")) {
			str_pageNum = "1";
		}

		try {
			pageNum = Integer.parseInt(str_pageNum);
		} catch (NumberFormatException e) {
			throw new Exception("页码只能是大于0的整数，请重新输入!");
		}
		try {
			pageSize = Integer.parseInt(str_pageSize);
		} catch (NumberFormatException e) {
			throw new Exception("页面大小只能是大于0的整数，请重新输入!");
		}

		PageHelper.startPage(pageNum, pageSize);
		String searchParams = "";
		if (searchParamObj != null) {
			searchParams = ClassUtils.getSearchParamsByObject(searchParamObj);
		}
		request.setAttribute("searchParams", searchParams);
	}

	public static void PageHelperStartPageAjax(HttpServletRequest request, Object searchParamObj) throws Exception {
		String page_index = request.getParameter("page_index");
		String page_size = request.getParameter("page_size");
		int pageIndex = 1;
		int pageSize = 10;
		if (!StringUtils.isEmpty(page_index)) {
			pageIndex = Integer.parseInt(page_index) + 1;
		}
		if (!StringUtils.isEmpty(page_size)) {
			pageSize = Integer.parseInt(page_size);
		}
		PageHelper.startPage(pageIndex, pageSize);
		String searchParams = "";
		if (searchParamObj != null) {
			searchParams = ClassUtils.getSearchParamsByObject(searchParamObj);
		}
		request.setAttribute("ajaxQuery", searchParams);
	}

	public static void PageHelperStartPageBnft(HttpServletRequest request, Object searchParamObj) throws Exception {

		String str_pageNum = request.getParameter("pageNum");
		String str_pageSize = request.getParameter("pageSize");
		String payClass = request.getParameter("payClass");
		String oid = request.getParameter("oid");

		if (StringUtils.isEmpty(str_pageNum))
			str_pageNum = "1";
		if (str_pageNum.equals("null"))
			str_pageNum = "1";
		if (StringUtils.isEmpty(str_pageSize))
			str_pageSize = "10";
		if ("0".equals(str_pageNum))
			str_pageNum = "1";

		int pageNum = 0;
		int pageSize = 0;
		try {
			pageNum = Integer.parseInt(str_pageNum);
		} catch (NumberFormatException e) {
			throw new Exception("页码只能是大于0的整数，请重新输入!");
		}
		try {
			pageSize = Integer.parseInt(str_pageSize);
		} catch (NumberFormatException e) {
			throw new Exception("页面大小只能是大于0的整数，请重新输入!");
		}

		PageHelper.startPage(pageNum, pageSize);
		String searchParams = "";
		if (searchParamObj != null) {
			searchParams = ClassUtils.getSearchParamsByObject(searchParamObj);
			if (!StringUtils.isEmpty(payClass)) {
				searchParams += "&" + "payClass" + "=" + payClass;
			}
			if (!StringUtils.isEmpty(oid)) {
				searchParams += "&" + "oid" + "=" + oid;
			}

		}
		request.setAttribute("searchParams", searchParams);
	}

	public static void PageHelperNextStartPage(HttpServletRequest request, Object searchParamObj, boolean status) throws Exception {

		String str_pageNum = request.getParameter("pageNum");
		if (status) {
			str_pageNum = (String) request.getAttribute("pNum");
		}
		String str_pageSize = request.getParameter("pageSize");

		if (StringUtils.isEmpty(str_pageNum))
			str_pageNum = "1";
		if (StringUtils.isEmpty(str_pageSize))
			str_pageSize = "10";

		int pageNum = 0;
		int pageSize = 0;
		try {
			pageNum = Integer.parseInt(str_pageNum);
		} catch (NumberFormatException e) {
			throw new Exception("页码只能是大于0的整数，请重新输入!");
		}
		try {
			pageSize = Integer.parseInt(str_pageSize);
		} catch (NumberFormatException e) {
			throw new Exception("页面大小只能是大于0的整数，请重新输入!");
		}

		PageHelper.startPage(pageNum, pageSize);
		String searchParams = "";
		if (searchParamObj != null) {
			searchParams = ClassUtils.getSearchParamsByObject(searchParamObj);
		}
		request.setAttribute("searchParams", searchParams);
	}

	public static void PageHelperStartPage2(HttpServletRequest request, Object searchParamObj) throws Exception {

		String str_pageNum = request.getParameter("pageNum2");
		String str_pageSize = request.getParameter("pageSize2");

		if (StringUtils.isEmpty(str_pageNum))
			str_pageNum = "1";
		if (StringUtils.isEmpty(str_pageSize))
			str_pageSize = "10";

		int pageNum = 0;
		int pageSize = 0;
		try {
			pageNum = Integer.parseInt(str_pageNum);
		} catch (NumberFormatException e) {
			throw new Exception("页码只能是大于0的整数，请重新输入!");
		}
		try {
			pageSize = Integer.parseInt(str_pageSize);
		} catch (NumberFormatException e) {
			throw new Exception("页面大小只能是大于0的整数，请重新输入!");
		}

		PageHelper.startPage(pageNum, pageSize);
		String searchParams = "";
		if (searchParamObj != null) {
			searchParams = ClassUtils.getSearchParamsByObject(searchParamObj);
		}
		request.setAttribute("searchParams2", searchParams);

	}

	public static void PageHelperCondition(HttpServletRequest request, Object query, PageInfo<?> page) throws Exception {
		String querySearchParamsCondition = ClassUtils.getSearchParamsByObject(query).replaceAll("&", "|");
		querySearchParamsCondition += "|pageNum=" + page.getPageNum() + "|pageSize=" + page.getPageSize() + "|isFromPage=Y";
		request.setAttribute("querySearchParamsCondition", querySearchParamsCondition);

	}

	public static String PageHelperConditionChange(String querySearchParamsCondition) {
		if (!StringUtils.isEmpty(querySearchParamsCondition))
			return querySearchParamsCondition.substring(1, querySearchParamsCondition.length()).replace("|", "&");
		else
			return "";
	}
}
