package com.aus.common.util;

import java.text.Collator;
import java.util.Comparator;

import com.aus.common.model.FlexValueDto; 

/**
 * @Title： SortChineseName.java
 * 
 * @Description: 中文字符排序
 * @Function: 中文字符排序
 * @Version 0.1
 */
public class SortChineseName implements Comparator<FlexValueDto> {
	Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

	@Override
	public int compare(FlexValueDto o1, FlexValueDto o2) {
		if (cmp.compare(o1.getFlexValuesDesc(), o2.getFlexValuesDesc()) > 0) {
			return 1;
		} else if (cmp.compare(o1.getFlexValuesDesc(), o2.getFlexValuesDesc()) < 0) {
			return -1;
		}
		return 0;
	}
}
