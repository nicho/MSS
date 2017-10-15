package com.aus.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * @author chenzg 2016-4-5 改造自Struts2JsonHelper
 * Json对象工具
 */
public class JsonHelper {
	/**
	 * 日志对象
	 */
	private static Logger log = Logger.getLogger(JsonHelper.class);
	
	/**
	 * 输出json字符串
	 * @param str 
	 */
	public static void outJsonString(String str,HttpServletResponse response) {
		response.setContentType("text/javascript;charset=UTF-8");
		outString(str,response);
	}

	// modified by yaohui.luo 2011-3-24
	// IE6浏览器text/javascript会下载
	/**
	 * 输出json字符串
	 * @param str
	 */
	public static void outJsonStr(String str,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		outString(str,response);
	}

	/**
	 * JSON 时间解析器具
	 * 
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig configJson(String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { StringUtils.EMPTY });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor(datePattern));

		return jsonConfig;
	}

	/**
	 * map对象转为json
	 * @param map 
	 */
	public static void outMapToJson(Map map,HttpServletResponse response) {
		JsonConfig jsonConfig = configJson("yyyy-MM-dd HH:mm:ss");

		response.setContentType("text/html;charset=UTF-8");
		outString(JSONObject.fromObject(map, jsonConfig).toString(),response);
	}
	
	/**
	 * 输出json到安卓手机
	 * @param obj
	 */
	public static void outJsonForAndroid(Object obj,HttpServletResponse response) {
		JsonConfig jsonConfig = configJson("yyyy-MM-dd HH:mm:ss");
		response.setContentType("application/json;charset=UTF-8");
		outString(JSONObject.fromObject(obj, jsonConfig).toString(),response);
	}

	/**
	 * 输出Json
	 * @param obj
	 */
	public static void outJson(Object obj,HttpServletResponse response) {
		JsonConfig jsonConfig = configJson("yyyy-MM-dd HH:mm:ss");
		outJsonString(JSONObject.fromObject(obj, jsonConfig).toString(),response);
	}

	/**
	 * 速出Json数组
	 * @param array
	 */
	public static void outJsonArray(Object array,HttpServletResponse response) {
		outJsonString(JSONArray.fromObject(array).toString(),response);
	}

	/**
	 * 输出json字符串数组
	 * @param array
	 */
	public static void outJsonStrArray(Object array,HttpServletResponse response) {
		outJsonStr(JSONArray.fromObject(array).toString(),response);
	}

	/**
	 * 速出字符串
	 * @param str
	 */
	public static void outString(String str,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			out.write(str);
			log.info(str);
		} catch (IOException e) {
		}
	}

	/**
	 * 输出xml文件
	 * @param xmlStr
	 */
	public static void outXMLString(String xmlStr,HttpServletResponse response) {
		response.setContentType("application/xml;charset=UTF-8");
		outString(xmlStr,response);
	}

	/**
	 * 转换字符流
	 * @param instream
	 * @return
	 * @throws IOException
	 */
	public static String convertStreamToString(InputStream instream)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				instream));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String reqBody = sb.toString();
		return URLDecoder.decode(reqBody, CharEncoding.UTF_8);
	}
	
	public static String mapTJson(Map<String,Object> m){
		ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			jsonString = null;
		}
        return jsonString;
	}
}
