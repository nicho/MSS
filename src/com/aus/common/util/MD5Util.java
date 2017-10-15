package com.aus.common.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5Util {
	private static Logger logger = Logger.getLogger(MD5Util.class);
	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.error(e);
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = new StringBuilder();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {
		String key = Constant.MD5_KEY; 
		String mi="";
		try { 
			mi = DesUtil.encrypt(inStr, key); 
		} catch (Exception e) { 
			logger.error(e);
		} 
		return mi;

	}
	
	public static String decryptMD5(String inStr) {
		String key = Constant.MD5_KEY; 
		String mi="";
		try { 
			mi = DesUtil.decrypt(inStr, key); 
		} catch (Exception e) { 
			logger.error(e);
		} 
		return mi;

	}
	
	// 测试主函数
	public static void main(String args[]) {
		String s = new String("AfD0i_4VbaoYr2k1qpvtkqIN6yJR1LOheLCgCsShqu6OqyHYegT8pqqCBi20FlDp");

		String key = Constant.MD5_KEY; 
		try {
			System.out.println(DesUtil.encrypt(s, key));

			System.out.println(DesUtil.decrypt(DesUtil.encrypt(s, key), key));
			String mi=DesUtil.encrypt(s, key);
			System.out.println("原始：" + s);
			System.out.println("加密的：" + mi);
			System.out.println("解密的：" + DesUtil.decrypt("Pp9rnK/LWBH9m7ag7gJA1rkTnioZq+CCfoQgaSQDrUhkllBXbK/8pf7lVp+25n8dzBMGcTMqzf2GdX7zwP2VFh66/cB4Mzp1", key));
			
			
			
		//	System.out.println("解密的：" + DesUtil.decrypt(DesUtil.encrypt("", key), key));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

}
