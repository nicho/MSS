package com.aus.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	public static String fmtMonth(int month) {
		if (month < 10)
			return "0" + month;
		else
			return month + "";
	}

	public static String getFirstDayMM() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();

	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();

	}

	public static String getFirstDay(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM");
		Date theDate = null;
		try {
			theDate = dfs.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();
	}

	/**
	 * 当月最后一天
	 * 
	 * @return
	 */
	public static String getLastDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s);
		return str.toString();

	}

	/**
	 * 当月最后一天(减一天)
	 * 
	 * @return
	 */
	public static String getLastDayMinusOneDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.DATE) != 1) {
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		}
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s);
		return str.toString();

	}

	public static String getLastDay(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM");
		Date theDate = null;
		try {
			theDate = dfs.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);

		// 上个月最后一天
		gcLast.add(Calendar.MONTH, 1); // 加一个月
		gcLast.set(Calendar.DATE, 1); // 设置为该月第一天
		gcLast.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天

		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();
	}

	/**
	 * 获取当前日期,传入参数加减天数
	 * 
	 * @param day
	 * @return
	 */
	public static String getNowDateAddSubtractDay(int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s);
		return str.toString();

	}
	

	public static Date strToDate(String str,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);//小写的mm表示的是分钟 
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/** 
     * 获取当年的第一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearFirst(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearFirst(currentYear);  
    } 
    
    /** 
     * 获取当年的最后一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearLast(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearLast(currentYear);  
    } 
    
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    } 
    
    /**
	 * 比较两个日期大小
	 * @param DATE
	 * @return
	 */
	public static int compareDate(Date d1,Date d2) {
		try {
			if (d1.getTime() > d2.getTime()) {
				return 1;
			} else if (d1.getTime() < d2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	
	public static String dateToStr(Date d,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);//小写的mm表示的是分钟 
		return sdf.format(d);
	}
	/**
	 * 计算两个日期差
	 *Title: checkDate
	 *Description: checkDate
	 *@author Gzg
	 *@date 2017年6月9日 上午11:37:47
	 */
	public static boolean checkDate(String d1,String d2,int num){
		boolean flag = false;
		Date dd1 = DateUtils.strToDate(d1, "yyyy-MM-dd");
		Date dd2 = DateUtils.strToDate(d2, "yyyy-MM-dd");
		int days = (int) ((dd2.getTime() - dd1.getTime()) / (1000*3600*24));
		if(Math.abs(days)>num){
			flag = true;
		}
		return flag;
	}

 
	/** 
     * 得到两个日期相差的天数 
     */  
    public static int getBetweenDay(Date date1, Date date2) {  
        Calendar d1 = new GregorianCalendar();  
        d1.setTime(date1);  
        Calendar d2 = new GregorianCalendar();  
        d2.setTime(date2);  
        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
        int y2 = d2.get(Calendar.YEAR);  
        if (d1.get(Calendar.YEAR) != y2) {  
            do {  
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
                d1.add(Calendar.YEAR, 1);  
            } while (d1.get(Calendar.YEAR) != y2);  
        }  
        return days;  
    }  

	/**
	 * 取当前季度前一个季度
	 * 
	 * @return
	 */
	public static String getCurrentQuarterPrevious() {
		Calendar c = Calendar.getInstance();
		String now = "";
		int currentMonth = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		String month = "";
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				month = "Q4";
				year = year - 1;
			} else if (currentMonth >= 4 && currentMonth <= 6)
				month = "Q1";
			else if (currentMonth >= 7 && currentMonth <= 9)
				month = "Q2";
			else if (currentMonth >= 10 && currentMonth <= 12)
				month = "Q3";
			now = year + "-" + month;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}
	public static String getCurrentYearPrevious() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR)-1;
		return year+"";
	}
	
	public static String getCurrentMonthPrevious() {
		Calendar c = Calendar.getInstance();
		String now = "";
		int currentMonth = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		int month = 0;
		try {
			if (currentMonth == 1) {
				month = 12;
				year = year - 1;
			} else {
				month=currentMonth-1;
			}
			now = year + "-" + month;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}
}
