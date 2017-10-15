package com.aus.common.util;

import java.util.Map;

public class CreateRtfToPdf {
	public static void rtfToPdf(Map<String, Object> contextMap, String address1, String address2) {
		try {

			RTFGenerator generator = new RTFGenerator();
			generator.setContextMap(contextMap);
			generator.run(CreateRtfToPdf.class.getResource(address1).getPath(), address2);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
