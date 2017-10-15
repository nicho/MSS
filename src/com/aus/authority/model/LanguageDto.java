package com.aus.authority.model;

/**
 * 
 * ORAClE 语言配置表
 * 
 * @author duzh
 *
 */
public class LanguageDto {
	
	private String languageCode;
	private String isoLanguage;
	private String nlsLanguage;
	
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getIsoLanguage() {
		return isoLanguage;
	}
	public void setIsoLanguage(String isoLanguage) {
		this.isoLanguage = isoLanguage;
	}
	public String getNlsLanguage() {
		return nlsLanguage;
	}
	public void setNlsLanguage(String nlsLanguage) {
		this.nlsLanguage = nlsLanguage;
	}
	
	

}
