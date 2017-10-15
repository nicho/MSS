package com.aus.user.util;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class TestUtil {
	private static Logger logger = Logger.getLogger(TestUtil.class);
	public static void main(String[] args) {
		String loginUrl = "https://entry.qiye.163.com/domain/domainEntLogin";
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(loginUrl);
		//TODO 请输入实际的信息
		NameValuePair[] data = { new NameValuePair("account_name", "it"),
				new NameValuePair("password", "au123456789"),
				new NameValuePair("domain", "ausnutria.com") };
		postMethod.addParameters(data);
		postMethod.setRequestHeader("Referer", "http://qiye.163.com/login/");
		
		try {
			httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			int statusCode = httpClient.executeMethod(postMethod);

			System.out.println(statusCode);
			postMethod.releaseConnection();// 关闭POST请求

			boolean isLogin = false;

			// 获取cookies
			Cookie[] cookies = httpClient.getState().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if ("QIYE_SESS".equals(cookies[i].getName())) {
					isLogin = true;
					break;
				}
			}

			if (isLogin) {
				System.out.println("验证成功");
			} else {
				System.out.println("验证失败");
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}
}
