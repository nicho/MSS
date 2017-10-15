package com.aus.common.util;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 生成pdf
 * 
 * @author zcr
 * 
 */
public class CreatePdf {
	private static Logger logger = Logger.getLogger(CreatePdf.class);
	Document document = new Document();// 建立一个Document对象

	private static Font keyfont;// 设置字体大小
	private static Font headfont;// 设置字体大小
	private static Font textfont;// 设置字体大小
	private static Font titlefont;// 设置字体大小
	private static Object object = new Object();
	static {
		// 中文格式
		BaseFont bfChinese;
		try {
			// 设置中文显示
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			headfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
			keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
			textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
			titlefont = new Font(bfChinese, 18, Font.NORMAL);// 设置字体大小
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 文成文件
	 * 
	 * @param file
	 *            待生成的文件名
	 */
	public CreatePdf(File file) {
		document.setPageSize(PageSize.A4);// 设置页面大小
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public CreatePdf() {

	}

	public void initFile(File file) {
		document.setPageSize(PageSize.A4);// 设置页面大小
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	int maxWidth = 520;

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            占多少列
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            占多少列
	 * @param boderFlag
	 *            是否有有边框
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setPadding(3.0f);
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(15.0f);
		}
		return cell;
	}

	/**
	 * 创建一个表格对象
	 * 
	 * @param colNumber
	 *            表格的列数
	 * @return 生成的表格对象
	 */
	public PdfPTable createTable(int colNumber) {
		PdfPTable table = new PdfPTable(colNumber);
		try {
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return table;
	}

	public PdfPTable createTable(float[] widths) {
		PdfPTable table = new PdfPTable(widths);
		try {
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return table;
	}

	public PdfPTable createBlankTable() {
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(0);
		table.addCell(createCell("", keyfont));
		table.setSpacingAfter(20.0f);
		table.setSpacingBefore(20.0f);
		return table;
	}

}