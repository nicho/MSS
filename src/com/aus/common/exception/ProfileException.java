package com.aus.common.exception;

import java.util.Map;

public class ProfileException extends RuntimeException {
	
	/**
	 * map 集合对象
	 */
	private Map<String , Object> map;
	
	private String errorCode;


	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * 序列化标识
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * 默认异常函数
	 */
	public ProfileException() {
	}

	/**
	 * 处理消息机制的异常函数
	 * @param message
	 */
	public ProfileException(String message) {
		super(message);
	}
	
	/**
	 * map集合异常信息函数
	 * @param message  
	 * @param map
	 */
	public ProfileException(String message , Map<String , Object> map) {
		super(message);
		this.map = map;
	}
	
	/**
	 * map集合异常信息函数
	 * @param message  
	 * @param map
	 */
	public ProfileException(String message , String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
