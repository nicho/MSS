package com.aus.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.aus.authority.service.ShiroDbRealm.ShiroUser;

public class IpUtil {
	private static Logger logger = Logger.getLogger(IpUtil.class.getName());
	public static String getRemoteHost(HttpServletRequest request){ 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){ 
	        ip = request.getRemoteAddr(); 
	    }
	    if(ip !=null){
	    String ips[] = ip.split(",");//如果存在多个IP
	    ip = ips[0];
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip; 
	} 
	
    public static String  getInputStreamMember(String urlString) {
        URL url = null;
        BufferedReader reader =null;
        InputStreamReader inputStreamReader =null;
        InputStream is =null;
        try {
            //System.out.println(urlFull + xmlURL);
            url = new URL(urlString);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection)url.openConnection();
            //System.out.println("URL = " + connection.getURL());
            connection.connect();
            is = connection.getInputStream();
            inputStreamReader = new InputStreamReader(is);
            reader = new BufferedReader(inputStreamReader);
            String tempLine = null;
            StringBuffer resultBuffer = new StringBuffer();
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
            return resultBuffer.toString();
            //return is;
        } catch (Exception e) {
        	Subject currentUser = SecurityUtils.getSubject();
    		// 获取当前登录的用户
    		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
    		logger.error(shiroUser.getName()+"连接淘宝IP地址异常"+e.getMessage());

            //System.out.println("连接超时");
            return null;
        }finally{
            try{
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (is != null) {
            	is.close();
            }
            }catch(Exception ex){
            }
        }
        
    }
}
