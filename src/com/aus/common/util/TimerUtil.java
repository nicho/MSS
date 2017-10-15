package com.aus.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimerUtil {
	/**   
	 * 得到本月的第一天   
	 * @return   
	 */    
	public static String getMonthFirstDay() {     
	    Calendar calendar = Calendar.getInstance();     
	    calendar.set(Calendar.DAY_OF_MONTH, calendar     
	            .getActualMinimum(Calendar.DAY_OF_MONTH));     
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    return df.format(calendar.getTime());     
	}     
	    
	/**   
	 * 得到本月的最后一天   
	 *    
	 * @return   
	 */    
	public static String getMonthLastDay() {     
	    Calendar calendar = Calendar.getInstance();     
	    calendar.set(Calendar.DAY_OF_MONTH, calendar     
	            .getActualMaximum(Calendar.DAY_OF_MONTH));     
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    return df.format(calendar.getTime());      
	}  
	
	/**   
	 * 得到本月的最后一天   
	 *    
	 * @return   
	 */    
	public static String getMonthNowDay() {     
	    Date date = new Date();   
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    return df.format(date.getTime());      
	}  
}
