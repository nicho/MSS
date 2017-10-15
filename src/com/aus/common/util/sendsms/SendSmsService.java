package com.aus.common.util.sendsms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Administrator
 * 
 */
public class SendSmsService {
	private static Log  log = LogFactory.getLog(SendSmsService.class);

	private static String hypId = "j00630";
	private static String hypPwd = "296500";
	
	private static String ausId = "j30199";
	private static String ausPwd = "080234";
//	private static String hypId = "";
//	private static String hypPwd = "";
//	
//	private static String ausId = "";
//	private static String ausPwd = "";
	
	public static String sendSMS(String mobile, String msg,String userName,String pwd,String serverHost,String serverPort) {
		WmgwLocator wmgwLocator = new WmgwLocator();
		String strArgs[] = new String[10]; 
		strArgs[0] = userName;
		strArgs[1] = pwd; 
		strArgs[2] = mobile;
		strArgs[3] = msg;
		strArgs[4] = "1";
		strArgs[5] = "*";
		
		String result = "";
		try {
			result = wmgwLocator.getwmgwSoapServer(serverHost,serverPort).mongateCsSendSmsEx(strArgs[0],strArgs[1], strArgs[2], strArgs[3],Integer.valueOf(strArgs[4]).intValue());
			log.info("mobile-"+mobile+"-back value is :" + result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return result;
	}
	public static void main(String[] args) {
		String xxx=sendSMS("18163629257", "OA测试短信", "J51568", "321968","61.130.7.220","8023");
		System.out.println(xxx);
	}
 
}
