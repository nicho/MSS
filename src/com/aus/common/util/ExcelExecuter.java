package com.aus.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aus.common.util.ExcelConfigure.Column;

/**
 * @author Administrator Excel处理
 */
public class ExcelExecuter {
	private static Logger logger = Logger.getLogger(ExcelExecuter.class);
	/**
	 * 数字格式化
	 */
	public static DecimalFormat df = new DecimalFormat("#.#######");
	/**
	 * 日志处理对象
	 */
	protected final static Log log = LogFactory.getLog(Object.class);

	/**
	 * 拷贝excel分页
	 * 
	 * @param templateSheet
	 *            模板sheet
	 * @param newSheet
	 *            新sheet
	 */
	private static void copySheet(Sheet templateSheet, Sheet newSheet,int num) {
		// 拷贝设置的开始行的上面所有行
		for (int i = 0; i < num; i++) {
			Row templateRow = templateSheet.getRow(i);
			if (templateRow != null) {
				Row newRow = newSheet.createRow(i);
				for (int j = 0; j < templateRow.getLastCellNum(); j++) {
					Cell templateCell = templateRow.getCell(j);
					if (templateCell != null) {
						Cell newCell = newRow.createCell(j);
						newCell.setCellStyle(newSheet.getWorkbook()
								.createCellStyle());
						copyCell(templateCell, newCell);
					}
				}
			}
		}
	}

	/**
	 * 拷贝列
	 * 
	 * @param srcCell
	 *            原列
	 * @param distCell
	 *            目标列
	 */
	private static void copyCell(Cell srcCell, Cell distCell) {
		distCell.getCellStyle().cloneStyleFrom(srcCell.getCellStyle());
		if (srcCell.getCellComment() != null) {
			distCell.setCellComment(srcCell.getCellComment());
		}
		int srcCellType = srcCell.getCellType();
		distCell.setCellType(srcCellType);
		if (srcCellType == Cell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
				distCell.setCellValue(srcCell.getDateCellValue());
			} else {
				distCell.setCellValue(srcCell.getNumericCellValue());
			}
		} else if (srcCellType == Cell.CELL_TYPE_STRING) {
			distCell.setCellValue(srcCell.getRichStringCellValue());
			// } else if (srcCellType == Cell.CELL_TYPE_BLANK) {
		} else if (srcCellType == Cell.CELL_TYPE_BOOLEAN) {
			distCell.setCellValue(srcCell.getBooleanCellValue());
		} else if (srcCellType == Cell.CELL_TYPE_ERROR) {
			distCell.setCellErrorValue(srcCell.getErrorCellValue());
		} else if (srcCellType == Cell.CELL_TYPE_FORMULA) {
			distCell.setCellFormula(srcCell.getCellFormula());
		}
	}


	/**
	 * public static void exporter(File template, OutputStream os, List dtos,
	 * ExcelConfigure config) throws Exception { FileInputStream is = new
	 * FileInputStream(template); exporter(is, os, dtos, config);
	 * 
	 * }
	 */

	/**
	 * 创建Workbook对象
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static Workbook createWorkbook(InputStream is) throws Exception {
		return WorkbookFactory.create(is);
	}

	/**
	 * 
	 * @param template
	 *            excel模板
	 * @param os
	 *            要写入的流
	 * @param dtos
	 *            要写入的数据
	 * @param firstRow
	 *            首行索引
	 * @param firstColumn
	 *            首列索引
	 * @param columnPropertys
	 *            列对应的属性名集合 ，用逗号分开。 如： “id,name,age”
	 * @throws Exception
	 */
	/**
	 * public static void exporter(File template, OutputStream os, List dtos,
	 * int firstRow, int firstColumn, String columnPropertys) throws Exception {
	 * ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,
	 * firstColumn); exporter(template, os, dtos, config); }
	 */
	/**
	 * 普通导出excel
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommon(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		exporterCommon(is, os, dtos, config,firstRow);
	}
	
	/**
	 * 
	 * @param template
	 *            excel模板
	 * @param os
	 *            要写入的流
	 * @param dtos
	 *            要写入的数据
	 * @param firstRow
	 *            首行索引
	 * @param firstColumn
	 *            首列索引
	 * @param columnPropertys
	 *            列对应的属性名集合 ，用逗号分开。 如： “id,name,age”
	 * @throws Exception
	 */
	/**
	 * public static void exporter(File template, OutputStream os, List dtos,
	 * int firstRow, int firstColumn, String columnPropertys) throws Exception {
	 * ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,
	 * firstColumn); exporter(template, os, dtos, config); }
	 */
	/**
	 * 普通导出excel--大于6w分页
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommonPage(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		exporterCommonPage(is, os, dtos, config,firstRow);
	}
	
	/**
	 * 导出excel部分转换为数值型
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommonIsNum(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		exporterCommonIsNum(is, os, dtos, config,firstRow);
	}
	
	/**
	 * 业务达成经销商门店导出excel
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommonBDR(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		exporterCommonBDR(is, os, dtos, config,firstRow);
	}
	
	/**
	 * 普通导出excel
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommonQA(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		exporterCommonQA(is, os, dtos, config,firstRow);
	}
	
	
	/**
	 * 普通导出excel多个list
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommonList(InputStream is, OutputStream os, List dtos,List dtosinfo,List dtoscodeinfo, int firstRow, int firstColumn,String columnPropertysHeld,String columnPropertysInfo,String columnPropertysCodeInfo) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertysHeld, firstRow,firstColumn);
		ExcelConfigure configInfo = new ExcelConfigure(columnPropertysInfo, firstRow+2,firstColumn);
		ExcelConfigure configCodeInfo = new ExcelConfigure(columnPropertysCodeInfo, firstRow+dtosinfo.size()+4,firstColumn);
		exporterCommonList(is, os, dtos, dtosinfo, dtoscodeinfo, config,configInfo,configCodeInfo, firstRow);
	}
	
	/**
	 * excel导出多个list数据
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommonList(InputStream is, OutputStream os, List dtos,List dtosinfo,List dtoscodeinfo, ExcelConfigure config,ExcelConfigure configInfo,ExcelConfigure configCodeInfo,int firstRow) throws Exception {
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
			Sheet sheet = newbook.createSheet("excel");
			HSSFCellStyle cellStyle = (HSSFCellStyle)newbook.createCellStyle();    
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			copySheet(oldSheet, sheet,firstRow);
			for (int i = 0; i < dtos.size(); i++) {
				Object dto = dtos.get(i);
				Row row = sheet.createRow(i + config.getFirstRowIndex());
				List<Column> columns = config.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());
					cell.setCellStyle(cellStyle);
					Object value = BeanUtils.getProperty(dto, c.getName());

					if (value == null) {
						continue;
					}
					Boolean isNum = false;//data是否为数值型
	                Boolean isPercent=false;//data是否为百分数
	                if (value != null || "".equals(value)) {
	                	String xiaoShuDianStr = value.toString().substring(0,1); 
						if(StringUtils.equals(xiaoShuDianStr, ".") && value.toString().length()>1){
							value = "0"+value;
						}
	                    //判断data是否为数值型
	                    isNum = value.toString().matches("^(-?\\d+)(\\.\\d+)?$");
	                    //判断data是否为百分数（是否包含“%”）
	                    isPercent=value.toString().contains("%");
	                }
					
					if (isNum && !isPercent) {
						// 设置单元格格式
						String oneStr = value.toString().substring(0,1);
						String[] xiaoShuStr = value.toString().split("\\.");
						String xiaoShu = "";
						if(xiaoShuStr.length>0){
							xiaoShu = xiaoShuStr[0];
						}else{
							xiaoShu = value.toString();
						}
						if(StringUtils.equals(oneStr, "0") && value.toString().length() != 1 && xiaoShuStr.length < 1){
							//第一位为0的不能转换为数值型
							cell.setCellValue(value.toString());
						}else if(xiaoShu.length()>11){
							//数值长度超过11位的不能转换为数值型
							cell.setCellValue(value.toString());
						}else{
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							// 设置单元格内容为double类型
							cell.setCellValue(Double.parseDouble(value.toString()));
						}
					} else {
						// 设置单元格内容为字符型
						cell.setCellValue(value.toString());
					}
				}
			}
			
			
			copySheet(oldSheet, sheet,firstRow+3);
			for (int i = 0; i < dtosinfo.size(); i++) {
				Object dto = dtosinfo.get(i);
				Row row = sheet.createRow(i + configInfo.getFirstRowIndex()+3);
				List<Column> columns = configInfo.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());
					cell.setCellStyle(cellStyle);
					Object value = BeanUtils.getProperty(dto, c.getName());
					if (value == null) {
						continue;
					}
					Boolean isNum = false;//data是否为数值型
	                Boolean isPercent=false;//data是否为百分数
	                if (value != null || "".equals(value)) {
	                	String xiaoShuDianStr = value.toString().substring(0,1); 
						if(StringUtils.equals(xiaoShuDianStr, ".") && value.toString().length()>1){
							value = "0"+value;
						}
	                    //判断data是否为数值型
	                    isNum = value.toString().matches("^(-?\\d+)(\\.\\d+)?$");
	                    //判断data是否为百分数（是否包含“%”）
	                    isPercent=value.toString().contains("%");
	                }
					
					if (isNum && !isPercent) {
						// 设置单元格格式
						String oneStr = value.toString().substring(0,1);
						String[] xiaoShuStr = value.toString().split("\\.");
						String xiaoShu = "";
						if(xiaoShuStr.length>0){
							xiaoShu = xiaoShuStr[0];
						}else{
							xiaoShu = value.toString();
						}
						if(StringUtils.equals(oneStr, "0") && value.toString().length() != 1 && xiaoShuStr.length < 1){
							//第一位为0的不能转换为数值型
							cell.setCellValue(value.toString());
						}else if(xiaoShu.length()>11){
							//数值长度超过11位的不能转换为数值型
							cell.setCellValue(value.toString());
						}else{
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							// 设置单元格内容为double类型
							cell.setCellValue(Double.parseDouble(value.toString()));
						}
					} else {
						// 设置单元格内容为字符型
						cell.setCellValue(value.toString());
					}
				}
			}
			
			
			copySheet(oldSheet, sheet,dtosinfo.size()+3);
			for (int i = 0; i < dtoscodeinfo.size(); i++) {
				Object dto = dtoscodeinfo.get(i);
				Row row = sheet.createRow(i + configCodeInfo.getFirstRowIndex()+dtosinfo.size()+3);
				List<Column> columns = configCodeInfo.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());

					Object value = BeanUtils.getProperty(dto, c.getName());

					if (value == null) {
						continue;
					}
					Boolean isNum = false;//data是否为数值型
	                Boolean isPercent=false;//data是否为百分数
	                if (value != null || "".equals(value)) {
	                	String xiaoShuDianStr = value.toString().substring(0,1); 
						if(StringUtils.equals(xiaoShuDianStr, ".") && value.toString().length()>1){
							value = "0"+value;
						}
	                    //判断data是否为数值型
	                    isNum = value.toString().matches("^(-?\\d+)(\\.\\d+)?$");
	                    //判断data是否为百分数（是否包含“%”）
	                    isPercent=value.toString().contains("%");
	                }
					
					if (isNum && !isPercent) {
						// 设置单元格格式
						String oneStr = value.toString().substring(0,1);
						String[] xiaoShuStr = value.toString().split("\\.");
						String xiaoShu = "";
						if(xiaoShuStr.length>0){
							xiaoShu = xiaoShuStr[0];
						}else{
							xiaoShu = value.toString();
						}
						if(StringUtils.equals(oneStr, "0") && value.toString().length() != 1 && xiaoShuStr.length < 1){
							//第一位为0的不能转换为数值型
							cell.setCellValue(value.toString());
						}else if(xiaoShu.length()>11){
							//数值长度超过11位的不能转换为数值型
							cell.setCellValue(value.toString());
						}else{
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							// 设置单元格内容为double类型
							cell.setCellValue(Double.parseDouble(value.toString()));
						}
					} else {
						// 设置单元格内容为字符型
						cell.setCellValue(value.toString());
					}
				}
			}
			
			newbook.write(os);
		} catch (Exception e) {
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	/**
	 * 导出excel 根据文件名设置公式
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommon2(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys,String excelName) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		exporterCommon2(is, os, dtos, config,firstRow,excelName);
	}
	
	/**
	 * excel导出数据  设置公式
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommon2(InputStream is, OutputStream os, List dtos, ExcelConfigure config,int firstRow,String excelName) throws Exception {
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
			Sheet sheet = newbook.createSheet("excel");
			copySheet(oldSheet, sheet,firstRow);
			if("BenefitList_Accrued".equals(excelName) || "BenefitList_Payable".equals(excelName)){
				sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
				sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
				sheet.addMergedRegion(new CellRangeAddress(0,1,2,2));
				sheet.addMergedRegion(new CellRangeAddress(0,1,3,3));
				sheet.addMergedRegion(new CellRangeAddress(0,1,4,4));
				sheet.addMergedRegion(new CellRangeAddress(0,1,5,5));
				sheet.addMergedRegion(new CellRangeAddress(0,1,6,6));
				sheet.addMergedRegion(new CellRangeAddress(0,0,7,17));
				sheet.addMergedRegion(new CellRangeAddress(0,0,18,30));
				sheet.addMergedRegion(new CellRangeAddress(0,1,31,31));
				sheet.addMergedRegion(new CellRangeAddress(0,1,32,32));
				sheet.addMergedRegion(new CellRangeAddress(0,1,33,33));
				sheet.addMergedRegion(new CellRangeAddress(0,1,34,34));
				sheet.addMergedRegion(new CellRangeAddress(0,1,35,35));
				sheet.addMergedRegion(new CellRangeAddress(0,1,36,36));		
			}
			
			for (int i = 0; i < dtos.size(); i++) {
				Object dto = dtos.get(i);
				Row row = sheet.createRow(i + config.getFirstRowIndex());
				List<Column> columns = config.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());

					Object value = BeanUtils.getProperty(dto, c.getName());

					if (value == null) {
						continue;
					}
					
					//工资需要设置公式的单元格
					if("WageList".equals(excelName))
					{
						if(c.getIndex() != 0 
						&& c.getIndex() != 1 
						&& c.getIndex() != 2 
						&& c.getIndex() != 3
						&& c.getIndex() != 4
						&& c.getIndex() != 5
						&& c.getIndex() != 9
						&& c.getIndex() != 23
								)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.valueOf(value.toString()));
						}
						 else {
						    cell.setCellValue(value.toString());
						}
						
						/*if(c.getIndex() == 16)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellFormula("SUM(J2:O2)+G2+H2*I2-P2");
						}
						
						if(c.getIndex() == 21)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellFormula("SUM(J2:O2)+G2+H2*I2-P2-R2-S2-T2-U2");
						}*/
					}
					
					if("IncentiveList".equals(excelName))
					{
						if(c.getIndex() != 0 
						&& c.getIndex() != 1 
						&& c.getIndex() != 2 
						&& c.getIndex() != 3
						&& c.getIndex() != 4 
						&& c.getIndex() != 5
						&& c.getIndex() != 7
						&& c.getIndex() != 8
						&& c.getIndex() != 9
						&& c.getIndex() != 16
								)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.valueOf(value.toString()));
						}
						 else {
						    cell.setCellValue(value.toString());
						}
						
						/*if(c.getIndex() == 14)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellFormula("G2+H2*I2+M2+N2-L2");
						}
						
						if(c.getIndex() == 16)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellFormula("G2+H2*I2+M2+N2-L2+P2");
						}*/
					}
					
					//福利需要设置公式的单元格
					if("BenefitList_Accrued".equals(excelName) || "BenefitList_Payable".equals(excelName))
					{
						
						
						if(c.getIndex() != 0 && c.getIndex() != 1&& c.getIndex() != 2&& c.getIndex() != 3&& c.getIndex() != 36)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.valueOf(value.toString()));
						}
						 else {
						    cell.setCellValue(value.toString());
						}
						
						if(c.getIndex() == 35)
						{
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellFormula("SUM(H3:AI3)");
						}
					}
					if("BonusList".equals(excelName))
					{
						if(c.getIndex()==9)
								{
									cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
									cell.setCellValue(Double.valueOf(value.toString()));
								}else{
								    cell.setCellValue(value.toString());
								}
					}
					if("WelfareList".equals(excelName))
					{
						if(c.getIndex()==8)
								{
									cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
									cell.setCellValue(Double.valueOf(value.toString()));
								}else{
								    cell.setCellValue(value.toString());
								}
					}

				}
			}
			
			newbook.write(os);
		} catch (Exception e) {
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	/**
	 * excel导出数据  设置公式
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommon3(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
			Sheet sheet = newbook.createSheet("excel");
			copySheet(oldSheet, sheet,firstRow);
			
				sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
				sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
				sheet.addMergedRegion(new CellRangeAddress(0,1,2,2));
				sheet.addMergedRegion(new CellRangeAddress(0,1,3,3));
				sheet.addMergedRegion(new CellRangeAddress(0,1,4,4));
				sheet.addMergedRegion(new CellRangeAddress(0,1,5,5));
				sheet.addMergedRegion(new CellRangeAddress(0,1,6,6));
				sheet.addMergedRegion(new CellRangeAddress(0,1,7,7));
				sheet.addMergedRegion(new CellRangeAddress(0,1,8,8));
				sheet.addMergedRegion(new CellRangeAddress(0,1,9,9));
				
				sheet.addMergedRegion(new CellRangeAddress(0,0,10,14));
				sheet.addMergedRegion(new CellRangeAddress(0,0,15,22));
				for (int i = 0; i < dtos.size(); i++) {
					Object dto = dtos.get(i);
					Row row = sheet.createRow(i + config.getFirstRowIndex());
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.createCell(c.getIndex());

						Object value = BeanUtils.getProperty(dto, c.getName());

						if (value == null) {
							continue;
						}

						if (value instanceof Double) {
							cell.setCellValue((Double) value);
						} else if (value instanceof BigDecimal) {
							cell.setCellValue(((BigDecimal) value).toString());
						} else if (value instanceof Date) {
							cell.setCellValue((Date) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} else if (value instanceof Boolean) {
							cell.setCellValue((Boolean) value);
						} else if (value instanceof Calendar) {
							cell.setCellValue((Calendar) value);
						} else if (value instanceof Long) {
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue((Long) value);
						} else {
						cell.setCellValue(value.toString());
						}
					}
				}
			
			newbook.write(os);
		} catch (Exception e) {
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	/**
	 * excel导出数据  设置公式
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommon4(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
			Sheet sheet = newbook.createSheet("excel");
			copySheet(oldSheet, sheet,firstRow);
			
				sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
				sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
				sheet.addMergedRegion(new CellRangeAddress(0,1,2,2));
				sheet.addMergedRegion(new CellRangeAddress(0,1,3,3));
				sheet.addMergedRegion(new CellRangeAddress(0,1,4,4));
				sheet.addMergedRegion(new CellRangeAddress(0,1,5,5));
				sheet.addMergedRegion(new CellRangeAddress(0,1,6,6));
				sheet.addMergedRegion(new CellRangeAddress(0,1,7,7));
				sheet.addMergedRegion(new CellRangeAddress(0,1,8,8));
				sheet.addMergedRegion(new CellRangeAddress(0,1,9,9));
				sheet.addMergedRegion(new CellRangeAddress(0,1,10,10));
				sheet.addMergedRegion(new CellRangeAddress(0,1,11,11));
				sheet.addMergedRegion(new CellRangeAddress(0,0,12,17));
				for (int i = 0; i < dtos.size(); i++) {
					Object dto = dtos.get(i);
					Row row = sheet.createRow(i + config.getFirstRowIndex());
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.createCell(c.getIndex());

						Object value = BeanUtils.getProperty(dto, c.getName());

						if (value == null) {
							continue;
						}

						if (value instanceof Double) {
							cell.setCellValue((Double) value);
						} else if (value instanceof BigDecimal) {
							cell.setCellValue(((BigDecimal) value).toString());
						} else if (value instanceof Date) {
							cell.setCellValue((Date) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} else if (value instanceof Boolean) {
							cell.setCellValue((Boolean) value);
						} else if (value instanceof Calendar) {
							cell.setCellValue((Calendar) value);
						} else if (value instanceof Long) {
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue((Long) value);
						} else {
						cell.setCellValue(value.toString());
						}
					}
				}
			
			newbook.write(os);
		} catch (Exception e) {
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	/**
	 * excel导出数据
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommon(InputStream is, OutputStream os, List dtos, ExcelConfigure config,int firstRow) throws Exception {
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
			Sheet sheet = newbook.createSheet("excel");

			copySheet(oldSheet, sheet,firstRow);
			for (int i = 0; i < dtos.size(); i++) {
				Object dto = dtos.get(i);
				Row row = sheet.createRow(i + config.getFirstRowIndex());
				List<Column> columns = config.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());

					Object value = BeanUtils.getProperty(dto, c.getName());

					if (value == null) {
						continue;
					}

					if (value instanceof Double) {
						cell.setCellValue((Double) value);
					} else if (value instanceof BigDecimal) {
						cell.setCellValue(((BigDecimal) value).toString());
					} else if (value instanceof Date) {
						cell.setCellValue((Date) value);
					} else if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					} else if (value instanceof Boolean) {
						cell.setCellValue((Boolean) value);
					} else if (value instanceof Calendar) {
						cell.setCellValue((Calendar) value);
					} else if (value instanceof Long) {
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue((Long) value);
					} else {
					cell.setCellValue(value.toString());
					}
				}
			}
			
			newbook.write(os);
		} catch (Exception e) {
			exc = e;
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	/**
	 * excel导出数据
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommonPage(InputStream is, OutputStream os, List dtos, ExcelConfigure config,int firstRow) throws Exception {
		Exception exc = null;
		int pageNum = 50000;
		try {
			Workbook workbook = createWorkbook(is);
			Sheet oldSheet = workbook.getSheetAt(0);
			int pageSize = dtos.size()/pageNum;
			int page = pageSize+1;
			Workbook newbook = new HSSFWorkbook();
			for(int ii=0;ii<page;ii++){
				
				Sheet sheet = newbook.createSheet("excel"+ii);
				int listSize = pageNum;
				if(ii==pageSize){
					listSize = dtos.size()-pageSize*pageNum;
				}
				copySheet(oldSheet, sheet,firstRow);
				int rowNum = 0;
				for (int i = ii*pageNum; i < ii*pageNum+listSize; i++) {
					Object dto = dtos.get(i);
					Row row = sheet.createRow(rowNum + config.getFirstRowIndex());
					rowNum++;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.createCell(c.getIndex());

						Object value = BeanUtils.getProperty(dto, c.getName());

						if (value == null) {
							continue;
						}

						if (value instanceof Double) {
							cell.setCellValue((Double) value);
						} else if (value instanceof BigDecimal) {
							cell.setCellValue(((BigDecimal) value).toString());
						} else if (value instanceof Date) {
							cell.setCellValue((Date) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} else if (value instanceof Boolean) {
							cell.setCellValue((Boolean) value);
						} else if (value instanceof Calendar) {
							cell.setCellValue((Calendar) value);
						} else if (value instanceof Long) {
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cell.setCellValue((Long) value);
						} else {
						cell.setCellValue(value.toString());
						}
					}
				}
				
				
			}
			newbook.write(os);
			
			
		} catch (Exception e) {
			exc = e;
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	
	/**
	 * excel导出数据部分为数值型
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommonIsNum(InputStream is, OutputStream os, List dtos, ExcelConfigure config,int firstRow) throws Exception {
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			//Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
//			Sheet sheet = newbook.createSheet("excel");
			
			XSSFWorkbook wb = new XSSFWorkbook();     
	        XSSFSheet sheet= wb.createSheet("excel");    
	        
	        for (int i = 0; i < firstRow; i++) {
				Row templateRow = oldSheet.getRow(i);
				if (templateRow != null) {
					Row newRow = sheet.createRow(i);
					for (int j = 0; j < templateRow.getLastCellNum(); j++) {
						Cell templateCell = templateRow.getCell(j);
						if (templateCell != null) {
							Cell newCell = newRow.createCell(j);
							String teaStr = templateCell.getRichStringCellValue().toString();
//							newCell.setCellStyle(((Sheet) templateCell).getWorkbook()
//									.createCellStyle());
							newCell.setCellValue(teaStr);
							XSSFCellStyle borderStyle = (XSSFCellStyle) wb.createCellStyle();   
							borderStyle.setBorderBottom(CellStyle.BORDER_THIN);   
				            borderStyle.setBorderTop(CellStyle.BORDER_THIN);   
				            borderStyle.setBorderLeft(CellStyle.BORDER_THIN);   
				            borderStyle.setBorderRight(CellStyle.BORDER_THIN); 
				            
				            XSSFFont headfont = wb.createFont();  
				            headfont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
				            headfont.setFontName("宋体");
				            borderStyle.setFont(headfont);
				            newCell.setCellStyle(borderStyle);
						}
					}
				}
			}

			//copySheet(oldSheet, sheet,firstRow);
			for (int i = 0; i < dtos.size(); i++) {
				Object dto = dtos.get(i);
				Row row = sheet.createRow(i + config.getFirstRowIndex());
				List<Column> columns = config.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());

					Object value = BeanUtils.getProperty(dto, c.getName());
					
					if (value == null) {
						continue;
					}
					Boolean isNum = false;//data是否为数值型
	                Boolean isPercent=false;//data是否为百分数
	                if (value != null || "".equals(value)) {
	                	String xiaoShuDianStr = value.toString().substring(0,1); 
						if(StringUtils.equals(xiaoShuDianStr, ".") && value.toString().length()>1){
							value = "0"+value;
						}
	                    //判断data是否为数值型
	                    isNum = value.toString().matches("^(-?\\d+)(\\.\\d+)?$");
	                    //判断data是否为百分数（是否包含“%”）
	                    isPercent=value.toString().contains("%");
	                }
					
					if (isNum && !isPercent) {
						// 设置单元格格式
						String oneStr = value.toString().substring(0,1);
						String[] xiaoShuStr = value.toString().split("\\.");
						String xiaoShu = "";
						if(xiaoShuStr.length>0){
							xiaoShu = xiaoShuStr[0];
						}else{
							xiaoShu = value.toString();
						}
						if(StringUtils.equals(oneStr, "0") && value.toString().length() != 1 && xiaoShuStr.length < 1){
							//第一位为0的不能转换为数值型
							cell.setCellValue(value.toString());
						}else if(xiaoShu.length()>11){
							//数值长度超过11位的不能转换为数值型
							cell.setCellValue(value.toString());
						}else{
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							// 设置单元格内容为double类型
							cell.setCellValue(Double.parseDouble(value.toString()));
						}
					} else {
						// 设置单元格内容为字符型
						cell.setCellValue(value.toString());
					}
				}
			}
			
			wb.write(os);
		} catch (Exception e) {
			exc = e;
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	/**
	 * excel导出数据部分为数值型
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommonIsNumPart(InputStream is, OutputStream os, List dtos, ExcelConfigure config,int firstRow,String columnPropertys2) throws Exception {
		Exception exc = null;
		int pageNum = 50000;
		try {
			Workbook workbook = createWorkbook(is);
			Sheet oldSheet = workbook.getSheetAt(0);
			int pageSize = dtos.size()/pageNum;
			int page = pageSize+1;
			Workbook newbook = new HSSFWorkbook();
			Map<String,String> map = new HashMap<String,String>();
			String[] columnPropertys2s = columnPropertys2.split(",");
			for(String s:columnPropertys2s){
				map.put(s, "1");
			}
			for(int ii=0;ii<page;ii++){
				
				Sheet sheet = newbook.createSheet("excel"+ii);
				int listSize = pageNum;
				if(ii==pageSize){
					listSize = dtos.size()-pageSize*pageNum;
				}
				copySheet(oldSheet, sheet,firstRow);
				int rowNum = 0;
				List<Integer> list = new ArrayList<Integer>();
				int count=0;
				for (int i = ii*pageNum; i < ii*pageNum+listSize; i++) {
					Object dto = dtos.get(i);
					Row row = sheet.createRow(rowNum + config.getFirstRowIndex());
					rowNum++;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.createCell(c.getIndex());

						Object value = BeanUtils.getProperty(dto, c.getName());

						if (value == null) {
							continue;
						}

						Boolean isNum = false;//data是否为数值型
						Boolean isPercent=false;//data是否为百分数
						if ((value != null || "".equals(value))&&!StringUtils.isBlank(map.get(c.getName()))) {
		                	String xiaoShuDianStr = value.toString().substring(0,1); 
							if(StringUtils.equals(xiaoShuDianStr, ".") && value.toString().length()>1){
								value = "0"+value;
							}
		                    //判断data是否为数值型
		                    isNum = value.toString().matches("^(-?\\d+)(\\.\\d+)?$");
		                    //判断data是否为百分数（是否包含“%”）
		                    isPercent=value.toString().contains("%");
		                }
						
						if (isNum && !isPercent) {
							// 设置单元格格式
							String oneStr = value.toString().substring(0,1);
							String[] xiaoShuStr = value.toString().split("\\.");
							String xiaoShu = "";
							if(xiaoShuStr.length>0){
								xiaoShu = xiaoShuStr[0];
							}else{
								xiaoShu = value.toString();
							}
							if(StringUtils.equals(oneStr, "0") && value.toString().length() != 1 && xiaoShuStr.length < 1){
								//第一位为0的不能转换为数值型
								cell.setCellValue(value.toString());
							}else if(xiaoShu.length()>11){
								//数值长度超过11位的不能转换为数值型
								cell.setCellValue(value.toString());
							}else{
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								// 设置单元格内容为double类型
								cell.setCellValue(Double.parseDouble(value.toString()));
							}
						}else if(isValidDate(value.toString())||isValidDate2(value.toString())){
							if(count==0){
								list.add(c.getIndex());
							}
							 // 定义Cell格式 
							HSSFCellStyle cellStyle = (HSSFCellStyle) newbook.createCellStyle();
							HSSFDataFormat format= (HSSFDataFormat) newbook.createDataFormat();
							if(isValidDate(value.toString())){
								cellStyle.setDataFormat(format.getFormat("yyyy/MM/dd"));
						        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						        Date temp = sdf.parse(value.toString().replaceAll("-", "/"));
						        cell.setCellValue(temp);
						        cell.setCellStyle(cellStyle); 
							}else{
								cellStyle.setDataFormat(format.getFormat("yyyy/MM/dd HH:mm:ss"));
						        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						        Date temp = sdf.parse(value.toString().replaceAll("-", "/"));
						        cell.setCellValue(temp);
						        cell.setCellStyle(cellStyle); 
							}
							
						}else{
							// 设置单元格内容为字符型
							cell.setCellValue(value.toString());
						}
					}
				}
				for(Integer i:list){
					sheet.setColumnWidth(i, 12*256);
				}
			}
			
			newbook.write(os);
			
			
		} catch (Exception e) {
			exc = e;
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	/**
	 * 往来帐excel导出数据
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommonQA(InputStream is, OutputStream os, List dtos, ExcelConfigure config,int firstRow) throws Exception {
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
			Sheet sheet = newbook.createSheet("excel");

			copySheet(oldSheet, sheet,firstRow);
			for (int i = 0; i < dtos.size(); i++) {
				Object dto = dtos.get(i);
				Row row = sheet.createRow(i + config.getFirstRowIndex());
				List<Column> columns = config.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());

					Object value = BeanUtils.getProperty(dto, c.getName());

					if (value == null) {
						continue;
					}

					if (c.getIndex() == 4 || c.getIndex() == 5 || c.getIndex() == 6) {
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.parseDouble(value.toString()));
					} else {
						cell.setCellValue(value.toString());
					}
				}
			}
			
			newbook.write(os);
		} catch (Exception e) {
			exc = e;
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	
	/**
	 * 业务达成excel导出数据
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param dtos
	 * @param config
	 * @throws Exception
	 */
	public static void exporterCommonBDR(InputStream is, OutputStream os, List dtos, ExcelConfigure config,int firstRow) throws Exception {
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Workbook newbook = new HSSFWorkbook();
			Sheet oldSheet = workbook.getSheetAt(0);
			Sheet sheet = newbook.createSheet("excel");

			copySheet(oldSheet, sheet,firstRow);
			for (int i = 0; i < dtos.size(); i++) {
				Object dto = dtos.get(i);
				Row row = sheet.createRow(i + config.getFirstRowIndex());
				List<Column> columns = config.getColumns();
				for (Column c : columns) {
					Cell cell = row.createCell(c.getIndex());

					Object value = BeanUtils.getProperty(dto, c.getName());

					if (value == null) {
						continue;
					}

					if (c.getIndex()>2) {
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.parseDouble(value.toString()));
					} else {
						cell.setCellValue(value.toString());
					}
				}
			}
			
			newbook.write(os);
		} catch (Exception e) {
			exc = e;
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				throw ex;
			}
			if (exc != null)
				throw exc;
		}
	}
	
	/**
	 * 
	 * @param is
	 * @param firstRow
	 *            首行索引
	 * @param firstColumn
	 *            首列索引
	 * @param beanClass
	 *            返回的BEAN的类
	 * @param columnPropertys
	 *            列对应的属性名集合 ，用逗号分开。 如： “id,name,age”
	 * @param title
	 * @return
	 * @throws Exception
	 */

	public static List importer(long fileSize,InputStream is, int firstRow, int firstColumn,
			Class beanClass, String columnPropertys, Title title)
			throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,
				firstColumn);
		config.setBeanClass(beanClass);
		return importer(fileSize,is, config, title);
	}

	/**
	 * 导入excel 解析只有一列的excel
	 * 
	 * @param is
	 * @param config
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static List importer(long fileSize,InputStream is, ExcelConfigure config)
			throws Exception {
		if(fileSize>5242880){
			throw new RuntimeException("文件大小不能超过5M，请分批导入"); 
		}
		List beans = new ArrayList();
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getLastRowNum()>10000){
				throw new RuntimeException("导入数据不能超过10000行，请分批导入"); 
			}
			boolean end = false;
			int index = 0;

			while (!end) {
				Row row = sheet.getRow(index++ + config.getFirstRowIndex());
				if (row != null) {
					Object bean = config.getBeanClass().newInstance();	
					boolean status = false;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.getCell(c.getIndex());
						if (cell != null) {
							int type = cell.getCellType();
							Object value = null;
							if (type == Cell.CELL_TYPE_BOOLEAN) {
								value = cell.getBooleanCellValue();
							} else if (type == Cell.CELL_TYPE_NUMERIC) {
								value = df.format(cell.getNumericCellValue());
							} else {
								value = cell.getStringCellValue();
							}
//							System.out.println(value);
							if(!StringUtils.isEmpty(String.valueOf(value))){
								status = true;
							}
							BeanUtils.setProperty(bean, c.getName(), value);
						}
					}
					if(status){
						beans.add(bean);
						}
						bean = null;
				}  else{
					end = true;
				}
			}
		} catch (Exception e) {
//			logger.error(e);
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (exc != null)
				throw exc;
		}
		return beans;
	}

	/**
	 * 导入excel
	 * 
	 * @param is
	 * @param config
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static List importer(long fileSize,InputStream is, ExcelConfigure config,
			Title title) throws Exception {
		if(fileSize>5242880){
			throw new RuntimeException("文件大小不能超过5M，请分批导入"); 
		}
		List beans = new ArrayList();
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getLastRowNum()>10000){
				throw new RuntimeException("导入数据不能超过10000行，请分批导入"); 
			}
			boolean end = false;
			int index = 0;

			if (title != null) {
				title.getTitle(sheet.getRow(0).getCell(0).getStringCellValue());
			}
			while (!end) {
				Row row = sheet.getRow(index++ + config.getFirstRowIndex());
				if (row != null) {
					Object bean = config.getBeanClass().newInstance();	
					boolean status = false;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.getCell(c.getIndex());
						if (cell != null) {
							int type = cell.getCellType();
							Object value = null;
							if (type == Cell.CELL_TYPE_BOOLEAN) {
								value = cell.getBooleanCellValue();
							} else if (type == Cell.CELL_TYPE_NUMERIC) {
								value = df.format(cell.getNumericCellValue());
							}else {
								value = cell.getStringCellValue();
							}
							if (0 == cell.getCellType()) {
								//判断是否为日期类型
								if(HSSFDateUtil.isCellDateFormatted(cell)){
								//用于转化为日期格式
								Date d = cell.getDateCellValue();
								DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
								value = formater.format(d);
								}
							 }
							if(!StringUtils.isEmpty(String.valueOf(value))){
								status = true;
							}
							BeanUtils.setProperty(bean, c.getName(), value);
						}
					}
					if(status){
					beans.add(bean);
					}
					bean = null;
				}  else{
					end = true;
				}
			}
		} catch (Exception e) {
			logger.error(e);
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (exc != null)
				throw exc;
		}
		return beans;
	}
	/**
	 * 导入excel
	 * 
	 * @param is
	 * @param config
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static List importerWage(long fileSize,InputStream is, ExcelConfigure config,
			Title title) throws Exception {
		if(fileSize>5242880){
			throw new RuntimeException("文件大小不能超过5M，请分批导入"); 
		}
		List beans = new ArrayList();
		Exception exc = null;
		try {
			Workbook workbook = createWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getLastRowNum()>10000){
				throw new RuntimeException("导入数据不能超过10000行，请分批导入"); 
			}
			boolean end = false;
			int index = 0;

			if (title != null) {
				title.getTitle(sheet.getRow(0).getCell(0).getStringCellValue());
			}
			while (!end) {
				Row row = sheet.getRow(index++ + config.getFirstRowIndex());
				if (row != null) {
					Object bean = config.getBeanClass().newInstance();	
					boolean status = false;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.getCell(c.getIndex());
						if (cell != null) {
							int type = cell.getCellType();
							Object value = null;
							if (type == Cell.CELL_TYPE_BOOLEAN) {
								value = cell.getBooleanCellValue();	
							} else if (type == Cell.CELL_TYPE_NUMERIC) {
								value = df.format(cell.getNumericCellValue());
							} else if(type == Cell.CELL_TYPE_FORMULA){
								try {
								value = String.valueOf(new java.text.DecimalFormat("#0.00").format(cell.getNumericCellValue()) + "");
								} catch (IllegalStateException e) {
								value = String.valueOf(cell.getRichStringCellValue());
								}
							}else {
								value = cell.getStringCellValue();
							}
							if(!StringUtils.isEmpty(String.valueOf(value))){
								status = true;
							}
							BeanUtils.setProperty(bean, c.getName(), value);
						}
					}
					if(status){
					beans.add(bean);
					}
					bean = null;
				} else{
					end = true;
				}
				
			}
		} catch (Exception e) {
			logger.error(e);
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (exc != null)
				throw exc;
		}
		return beans;
	}
	/**
	 * 导入excel 如果带公式
	 * 
	 * @param is
	 * @param config
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static List importer2(long fileSize,InputStream is, ExcelConfigure config,
			Title title) throws Exception {
		if(fileSize>5242880){
			throw new RuntimeException("文件大小不能超过5M，请分批导入"); 
		}
		List beans = new ArrayList();
		Exception exc = null;
		try {
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(is);
			HSSFSheet sheet = hssfworkbook.getSheetAt(0);
			if(sheet.getLastRowNum()>10000){
				throw new RuntimeException("导入数据不能超过10000行，请分批导入"); 
			}
			HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, hssfworkbook);
			
			boolean end = false;
			int index = 0;

			if (title != null) {
				title.getTitle(sheet.getRow(0).getCell(0).getStringCellValue());
			}
			while (!end) {
				HSSFRow row = sheet.getRow(index++ + config.getFirstRowIndex());
				if (row != null) {
					Object bean = config.getBeanClass().newInstance();	
					boolean status = false;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.getCell(c.getIndex());
						if (cell != null) {
							int type = cell.getCellType();
							HSSFCell hssfCellValue = row.getCell((short) c.getIndex());
							Object value = null;
							if (type == Cell.CELL_TYPE_BOOLEAN) {
								value = cell.getBooleanCellValue();
							} else if (type == Cell.CELL_TYPE_NUMERIC) {
								
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								}
								else
								{
									value = df.format(cell.getNumericCellValue());
								}
							}
							else if (type == Cell.CELL_TYPE_FORMULA) {
								CellValue tempCellValue = evaluator.evaluate(hssfCellValue);
								
								value = tempCellValue.getNumberValue();
							}
							else {
								value = cell.getStringCellValue();
							}
							if(value instanceof Double)
									{
								            BigDecimal bg = new BigDecimal((Double)value);  
								            value = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									}
							if(!StringUtils.isEmpty(String.valueOf(value))){
								status = true;
							}
								BeanUtils.setProperty(bean, c.getName(), value);
							
						}
					}
					if(status){
					beans.add(bean);
					}
					bean = null;
				}  else{
					end = true;
				}
			}
		} catch (Exception e) {
			logger.error(e);
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (exc != null)
				throw exc;
		}
		return beans;
	}
	
	/**
	 * 导入excel 如果带*号的公式（专用）
	 * 
	 * @param is
	 * @param config
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static List importer3(long fileSize,InputStream is, ExcelConfigure config,
			Title title,boolean status1) throws Exception {
		if(fileSize>5242880){
			throw new RuntimeException("文件大小不能超过5M，请分批导入"); 
		}
		List beans = new ArrayList();
		Exception exc = null;
		try {
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(is);
			HSSFSheet sheet = hssfworkbook.getSheetAt(0);
			if(sheet.getLastRowNum()>10000){
				throw new RuntimeException("导入数据不能超过10000行，请分批导入"); 
			}
			HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, hssfworkbook);
			
			boolean end = false;
			int index = 0;

			if (title != null) {
				title.getTitle(sheet.getRow(0).getCell(0).getStringCellValue());
			}
			while (!end) {
				HSSFRow row = sheet.getRow(index++ + config.getFirstRowIndex());
				if (row != null) {
					Object bean = config.getBeanClass().newInstance();	
					boolean status = false;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.getCell(c.getIndex());
						if (cell != null) {
							int type = cell.getCellType();
							HSSFCell hssfCellValue = row.getCell((short) c.getIndex());
							Object value = null;
							if (type == Cell.CELL_TYPE_BOOLEAN) {
								value = cell.getBooleanCellValue();
							} else if (type == Cell.CELL_TYPE_NUMERIC) {
								
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								}
								else
								{
									value = df.format(cell.getNumericCellValue());
								}
								 if("job_1psn_str".equals(c.getName()) || "job_2psn_str".equals(c.getName())){
									
										value = cell.getNumericCellValue();
										value = String.valueOf(Double.valueOf(((Double)value*100)).intValue() +"%");
								}
								
							}
							else if (type == Cell.CELL_TYPE_FORMULA) {
								CellValue tempCellValue = evaluator.evaluate(hssfCellValue);
								
								value = tempCellValue.getNumberValue();
							}
							else {
								if("job_1psn".equals(c.getName()) || "job_2psn".equals(c.getName()) ){
									String cellValue = cell.getStringCellValue();
								   String regex3 = "^((\\d+)|(\\d*))\\%$";
								   boolean flg25 = Pattern.matches(regex3, cellValue);
									if(flg25){
										String []str = cellValue.split("%");
										Double dValue = Double.valueOf(str[0])/100.0;
										cell.setCellValue(dValue);
										cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									}
									value = cell.getNumericCellValue();
								} else{

									value = cell.getStringCellValue();	
								}
							}
							if(value instanceof Double)
									{
								            BigDecimal bg = new BigDecimal((Double)value);  
								            value = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									}
							if(!StringUtils.isEmpty(String.valueOf(value))){
								status = true;
							}
								BeanUtils.setProperty(bean, c.getName(), value);
						
						}
					}
					if(status){
					beans.add(bean);
					}
					bean = null;
				}  else{
					end = true;
				}
			}
		} catch (Exception e) {
			logger.error(e);
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (exc != null)
				throw exc;
		}
		return beans;
	}
	
	/**
	 * 导入excel 如果带*号的公式（五险一金专用）
	 * 
	 * @param is
	 * @param config
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static List importer4(long fileSize,InputStream is, ExcelConfigure config,
			Title title) throws Exception {
		if(fileSize>5242880){
			throw new RuntimeException("文件大小不能超过5M，请分批导入"); 
		}
		List beans = new ArrayList();
		Exception exc = null;
		try {
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(is);
			HSSFSheet sheet = hssfworkbook.getSheetAt(0);
			if(sheet.getLastRowNum()>10000){
				throw new RuntimeException("导入数据不能超过10000行，请分批导入"); 
			}
			HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, hssfworkbook);
			
			boolean end = false;
			int index = 0;

			if (title != null) {
				title.getTitle(sheet.getRow(0).getCell(0).getStringCellValue());
			}
			while (!end) {
				HSSFRow row = sheet.getRow(index++ + config.getFirstRowIndex());
				if (row != null) {
					Object bean = config.getBeanClass().newInstance();	
					boolean status = false;
					List<Column> columns = config.getColumns();
					for (Column c : columns) {
						Cell cell = row.getCell(c.getIndex());
						if (cell != null) {
							int type = cell.getCellType();
							HSSFCell hssfCellValue = row.getCell((short) c.getIndex());
							Object value = null;
							if (type == Cell.CELL_TYPE_BOOLEAN) {
								value = cell.getBooleanCellValue();
							} else if (type == Cell.CELL_TYPE_NUMERIC) {
								
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								}
								else
								{
									value = df.format(cell.getNumericCellValue());
								}

								
							}
							else if (type == Cell.CELL_TYPE_FORMULA) {
								CellValue tempCellValue = evaluator.evaluate(hssfCellValue);
								
								value = tempCellValue.getNumberValue();
							}
							else {

								value = cell.getStringCellValue();	
								
							}
							if(value instanceof Double)
									{
								            BigDecimal bg = new BigDecimal((Double)value);  
								            value = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									}
							if(!StringUtils.isEmpty(String.valueOf(value))){
								status = true;
							}
								BeanUtils.setProperty(bean, c.getName(), value);
						}
					}
					if(status){
					beans.add(bean);
					}
					bean = null;
				} else{
					end = true;
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
			exc = e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (exc != null)
				throw exc;
		}
		return beans;
	}
	
	/**
	 * 获取标题
	 * 
	 * @author Administrator
	 * 
	 */
	public interface Title {
		/**
		 * 获取标题
		 * 
		 * @param string
		 */
		void getTitle(String string);
	}
	
	/**
	 * 导出excel部分转换为数值型
	 * 
	 * @param is
	 * @param os
	 * @param dtos
	 * @param firstRow
	 * @param firstColumn
	 * @param columnPropertys
	 * @throws Exception
	 */
	public static void exporterCommonIsNumPart(InputStream is, OutputStream os, List dtos, int firstRow, int firstColumn, String columnPropertys, String columnPropertys2) throws Exception {
		ExcelConfigure config = new ExcelConfigure(columnPropertys, firstRow,firstColumn);
		exporterCommonIsNumPart(is, os, dtos, config,firstRow,columnPropertys2);
	}
	
	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		if(str.length()>10){
			return false;
		}
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		} 
		return convertSuccess;
	}
	public static boolean isValidDate2(String str) {
		boolean convertSuccess=true;
		if(str.length()<19){
			return false;
		}
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		} 
		return convertSuccess;
	}
	
}
