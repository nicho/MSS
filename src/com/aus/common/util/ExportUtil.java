package com.aus.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ExportUtil {
	private static Logger logger = Logger.getLogger(ExportUtil.class);

	/**
	 * 导出文件,支持自定义行和列的开始值
	 * 
	 * @param request
	 * @param response
	 * @param list
	 * @param excelName
	 * @param columnPropertys
	 * @param row
	 * @param col
	 * @return
	 */
	public static String exportFileCommonNew(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys, int row, int col,
			String expFlieName) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + expFlieName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			ExcelExecuter.exporterCommonPage(is, os, list, col, row, columnPropertys);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	public static String exportFile(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			ExcelExecuter.exporterCommon(is, os, list, 1, 0, columnPropertys);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	/**
	 * 支持导出数据大于65535行，按每6w行分页。 Title: exportFilePage Description: exportFilePage
	 * 
	 * @author Gzg
	 * @date 2017年8月16日 上午10:40:57
	 */
	public static String exportFilePage(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			ExcelExecuter.exporterCommonPage(is, os, list, 1, 0, columnPropertys);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	public static String exportFileQA(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			ExcelExecuter.exporterCommonQA(is, os, list, 1, 0, columnPropertys);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	public static String exportFileBDR(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			ExcelExecuter.exporterCommonBDR(is, os, list, 1, 0, columnPropertys);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	public static String exportFile2(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			if ((excelName.equals("BenefitList_Accrued")) || (excelName.equals("BenefitList_Payable"))) {
				ExcelExecuter.exporterCommon2(is, os, list, 2, 0, columnPropertys, excelName);

			} else {
				ExcelExecuter.exporterCommon2(is, os, list, 1, 0, columnPropertys, excelName);
			}
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	/**
	 * 导出excel部分字段转换为数值型
	 *
	 * @author lichaofang
	 *
	 * @date 2016年7月28日
	 *
	 * @param request
	 * @param response
	 * @param list
	 * @param excelName
	 * @param columnPropertys
	 * @return
	 */
	public static String exportFileIsNum(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			ExcelExecuter.exporterCommonIsNum(is, os, list, 1, 0, columnPropertys);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	public static String exportFileCVS(HttpServletRequest request, HttpServletResponse response, List<?> list, LinkedHashMap<String, String> contextMap) {
		response.setHeader("Content-disposition", "attachment; filename=门店历史动销明细.cvs");
		response.setContentType("application/csv");
		InputStream is = null;
		OutputStream os = null;
		try {
			ExportCSVFile file = new ExportCSVFile();
			file.createCSVFileCommon(response, contextMap, list);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

	public static void exportFileWord(HttpServletRequest request, HttpServletResponse response, String fileName, String orgId) {
		InputStream is = null;
		OutputStream os = null;

		String realName = "";
		try {
			if (!"81".equals(orgId)) {
				if (!StringUtils.isEmpty(fileName) && "contact".equals(fileName)) {
					realName = "经销商解除合同模板.doc";
				} else if (!StringUtils.isEmpty(fileName) && "refund".equals(fileName)) {
					realName = "退款申请模板.doc";
				} else {
					realName = fileName + ".doc";
				}

				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".doc");
				response.setContentType("application/x-download");
				response.setCharacterEncoding("utf-8");
				if (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) {
					response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(realName + "", "UTF-8"));// 设置输出头，即要输出的文件名称
				} else {
					response.setHeader("Content-disposition", "attachment;filename=" + new String(realName.getBytes(), "iso-8859-1"));// 设置输出头，即要输出的文件名称
				}
				is = ExportUtil.class.getResourceAsStream("/template/" + fileName + ".doc");
				os = response.getOutputStream();
				byte[] buffer = new byte[8 * 1024];
				int read;
				while ((read = is.read(buffer)) > 0) {
					os.write(buffer, 0, read);
				}
			} else {

				if (!StringUtils.isEmpty(fileName) && "contact".equals(fileName)) {
					realName = "今年签订合同经销商-终止合作资料.zip";
				} else if (!StringUtils.isEmpty(fileName) && "refund".equals(fileName)) {
					realName = "今年未签订合同经销商-终止合作资料.zip";
				} else {
					realName = fileName + ".zip";
				}

				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".zip");
				response.setContentType("application/x-download");
				response.setCharacterEncoding("utf-8");
				if (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) {
					response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(realName + "", "UTF-8"));// 设置输出头，即要输出的文件名称
				} else {
					response.setHeader("Content-disposition", "attachment;filename=" + new String(realName.getBytes(), "iso-8859-1"));// 设置输出头，即要输出的文件名称
				}
				is = ExportUtil.class.getResourceAsStream("/template/" + fileName + ".zip");
				os = response.getOutputStream();
				byte[] buffer = new byte[8 * 1024];
				int read;
				while ((read = is.read(buffer)) > 0) {
					os.write(buffer, 0, read);

				}
			}
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}

	/**
	 * 导出excel数字转数值型 columnPropertys2 为需要转换为数值的列 Title: exportFileIsNumPart
	 * Description: exportFileIsNumPart
	 * 
	 * @author Gzg
	 * @date 2017年9月11日 上午8:45:33
	 */
	public static String exportFileIsNumPart(HttpServletRequest request, HttpServletResponse response, List<?> list, String excelName, String columnPropertys,
			String columnPropertys2) {
		InputStream is = null;
		OutputStream os = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");

			is = ExportUtil.class.getResourceAsStream("/template/" + excelName + ".xls");
			os = response.getOutputStream();
			ExcelExecuter.exporterCommonIsNumPart(is, os, list, 1, 0, columnPropertys, columnPropertys2);
		} catch (final Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return null;

	}

}
