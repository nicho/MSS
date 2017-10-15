package com.aus.common.util;

import org.springframework.context.i18n.LocaleContextHolder;

public class LocaleUtil {
	public static String getLocaleLanguage() {
		String language = "ZHS";// US
		try {
			String locale = LocaleContextHolder.getLocale().toString();
			if ("en_US".equals(locale)) {
				language = "US";
			}
		} catch (Exception e) {
			language = "ZHS";
		}
		return language;
	}
}
