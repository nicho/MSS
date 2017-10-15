package com.aus.common.util;

public class GBKEncoded {
	 public static String getEncodedRTFString(String sourceStr)  {  
	        try{  
	            return java.net.URLEncoder.encode(sourceStr, "GBK").replaceAll("%", "\\\\'").replaceAll("\\+", " ");  
	        }catch(Exception ex){  
	            return sourceStr;  
	        }  
	    }  
}
