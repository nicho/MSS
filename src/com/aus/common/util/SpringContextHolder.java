package com.aus.common.util;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext
 * @author duzh
 *
 */
public class SpringContextHolder implements ApplicationContextAware {

   private static ApplicationContext applicationContext;


   public void setApplicationContext(ApplicationContext applicationContext) {
       SpringContextHolder.applicationContext = applicationContext;
   }


   public static ApplicationContext getApplicationContext() {
       checkApplicationContext();
       return applicationContext;
   }
   
	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}


   @SuppressWarnings("unchecked")
   public static <T> T getBean(String name) {
       checkApplicationContext();
       return (T) applicationContext.getBean(name);
   }
   
	public static <T> T getBean(String name, Class<T> c) {
		assertContextInjected();
	
		return (T) applicationContext.getBean(name, c);
	}


   @SuppressWarnings("unchecked")
   public static <T> T getBean(Class<T> clazz) {
       checkApplicationContext();
       Map beanMaps = applicationContext.getBeansOfType(clazz);
       if (beanMaps!=null && !beanMaps.isEmpty()) {
           return (T) beanMaps.values().iterator().next();
       } else{
           return null;
       }
   }

   private static void checkApplicationContext() {
       if (applicationContext == null) {
           throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
       }
   }



}
