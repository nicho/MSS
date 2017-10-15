package com.aus.authority.annotation;

import java.lang.annotation.*;

/**
 * 
 * 权限注解
 * 
 * @author duzh
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccreditAnnotation{
 
	public String url();
	
	public String resourceCode();
	
}
