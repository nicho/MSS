package com.aus.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ClassUtils {

	@SuppressWarnings("unchecked")
	public static String getSearchParamsByObject(Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String searchParams = "";
		if (model instanceof Map) {
			for (Map.Entry<String, String> entry : ((Map<String, String>) model).entrySet()) {
				String val="";
				if(!StringUtils.isEmpty(entry.getValue())){
					val=entry.getValue();
					val = val.replace("'", "");
				}
				searchParams += "&" + entry.getKey() + "=" + val;
			}
		} else {
			Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name_lowercase = field[j].getName(); // 获取属性的名字

				// System.out.println("attribute name:" + name);
				String name = name_lowercase.substring(0, 1).toUpperCase() + name_lowercase.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型4
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = model.getClass().getMethod("get" + name);
					String value = (String) m.invoke(model); // 调用getter方法获取属性值
					if (!StringUtils.isEmpty(value)) {
						if(StringUtils.equals(name_lowercase, "stroeIdList")){
							value = value.replace("'", "");
						}
						// System.out.println("attribute value:" + name);
						// System.out.println("attribute value:" + value);
						searchParams += "&" + name_lowercase + "=" + value;
					}
				}
				if (type.equals("class java.lang.Integer")) {
					Method m = model.getClass().getMethod("get" + name);
					Integer value = (Integer) m.invoke(model);
					if (value != null) {
						searchParams += "&" + name_lowercase + "=" + value;
					}
				}
				if (type.equals("class java.lang.Short")) {
					Method m = model.getClass().getMethod("get" + name);
					Short value = (Short) m.invoke(model);
					if (value != null) {
						searchParams += "&" + name_lowercase + "=" + value;
					}
				}
				if (type.equals("class java.lang.Double")) {
					Method m = model.getClass().getMethod("get" + name);
					Double value = (Double) m.invoke(model);
					if (value != null) {
						searchParams += "&" + name_lowercase + "=" + value;
					}
				}
				if (type.equals("class java.lang.Boolean")) {
					Method m = model.getClass().getMethod("get" + name);
					Boolean value = (Boolean) m.invoke(model);
					if (value != null) {
						searchParams += "&" + name_lowercase + "=" + value;
					}
				}
				if (type.equals("class java.util.Date")) {
					Method m = model.getClass().getMethod("get" + name);
					Date value = (Date) m.invoke(model);
					if (value != null) {
						searchParams += "&" + name_lowercase + "=" + value;
					}
				}
			}
		}

		return searchParams;
	}
}
