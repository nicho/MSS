package com.aus.authority.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * 初始化系统变量,语言等
 * 
 * @author duzh
 *
 */
public class AuthorityListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent event) {	
		AuthorityUtils.initSysEnvironment();
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
	}



}
