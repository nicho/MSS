package com.aus.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author excel配置控制类
 */
public class ExcelConfigure {

	/**
	 * 初始化类实例
	 */
	private Class beanClass;

	/**
	 * @return
	 */
	public Class getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass
	 */
	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * excel 列集合
	 */
	private List<Column> columns;

	/**
	 * 首行定义
	 */
	private int firstRowIndex;

	/**
	 * @return
	 */
	public int getFirstRowIndex() {
		return firstRowIndex;
	}

	/**
	 * @param firstRowIndex
	 */
	public void setFirstRowIndex(int firstRowIndex) {
		this.firstRowIndex = firstRowIndex;
	}

	/**
	 * @return
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * @param firstRowIndex
	 */
	public ExcelConfigure(int firstRowIndex) {
		columns = new ArrayList<Column>();
		this.firstRowIndex = firstRowIndex;
	}

	/**
	 * “，”分割列数据
	 * 
	 * @param columnPropertys
	 * @param firstRowIndex
	 * @param firstColumn
	 */
	public ExcelConfigure(String columnPropertys, int firstRowIndex,
			int firstColumn) {
		this(firstRowIndex);
		String[] cps = columnPropertys.split(",");
		for (int i = 0; i < cps.length; i++) {
			this.add(cps[i], i + firstColumn);
		}
	}

	/**
	 * 获取记录行
	 * 
	 * @param index
	 * @return
	 */
	public Column getColumn(int index) {
		return this.columns.get(index);
	}

	/**
	 * 增加
	 * 
	 * @param name
	 * @param index
	 */
	public void add(String name, int index) {
		Column c = new Column();
		c.name = name.trim();
		c.index = index;
		this.columns.add(c);
	}

	/**
	 * 定义列类
	 * 
	 * @author Administrator
	 * 
	 */
	class Column {
		/** 名称 **/
		String name;

		/**
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * @param index
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/** 次序 **/
		int index;
	}
}
