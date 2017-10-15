package com.aus.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class StringUtil {
	public static String GeneratingPrimaryKey() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static long[] convertionToLong(String[] strs) {// 将String数组转换为Long类型数组
		long[] longs = new long[strs.length]; // 声明long类型的数组
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i]; // 将strs字符串数组中的第i个值赋值给str
			long thelong = Long.valueOf(str);// 将str转换为long类型，并赋值给thelong
			longs[i] = thelong;// 将thelong赋值给 longs数组中对应的地方
		}

		return longs; // 返回long数组
	}

	public static String RandomizeNumber() {
		Date date = new Date();
		long timeMill = date.getTime();
		Random rand = new Random(timeMill);
		return rand.nextInt(999) + "";

	}

	public static String getNextRequestNum(Long nowDateLong) {
		String num = "";
		String dateStr = DateFormatUtils.format(nowDateLong, "yyyyMMddHHmmss");
		num = dateStr.substring(2) + StringUtil.RandomizeNumber();
		return num;
	}

	public static String dateToString(Date date, String dateFormat) {
		if (StringUtils.isEmpty(dateFormat)) {
			dateFormat = "yyyy-MM-dd";
		}
		String str = null;
		DateFormat format = new SimpleDateFormat(dateFormat);
		str = format.format(date);
		return str;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}
	
    // 全角转半角的 转换函数  
	  
    public static final String full2HalfChange(String QJstr)  
            throws UnsupportedEncodingException {  
  
        StringBuilder outStrBuf = new StringBuilder("");  
  
        String Tstr = "";  
  
        byte[] b = null;  
  
        for (int i = 0; i < QJstr.length(); i++) {  
  
            Tstr = QJstr.substring(i, i + 1);  
  
            // 全角空格转换成半角空格  
  
            if (Tstr.equals("　")) {  
  
                outStrBuf.append(" ");  
  
                continue;  
  
            }  
  
            b = Tstr.getBytes("unicode");  
  
            // 得到 unicode 字节数据  
  
            if (b[2] == -1) {  
  
                // 表示全角？  
  
                b[3] = (byte) (b[3] + 32);  
  
                b[2] = 0;  
  
                outStrBuf.append(new String(b, "unicode"));  
  
            } else {  
  
                outStrBuf.append(Tstr);  
  
            }  
  
        } // end for.  
  
        return outStrBuf.toString();  
  
    }
	
    public static boolean isInteger(String str) {    
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
        return pattern.matcher(str).matches();    
      } 
    public static boolean isNumeric(String str){ 
        Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$"); 
        return pattern.matcher(str).matches();    
     } 
    
    public static String getStringDateMonth(String sdate, String nd, String yf,  
            String rq, String format) {  
        Date currentTime = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String dateString = formatter.format(currentTime);  
        String s_nd = dateString.substring(0, 4); // 年份  
        String s_yf = dateString.substring(5, 7); // 月份  
        String s_rq = dateString.substring(8, 10); // 日期  
        String sreturn = "";  
        if (sdate == null || sdate.equals("") ) { // 处理空值情况  
            if (nd.equals("1")) {  
                sreturn = s_nd;  
                // 处理间隔符  
                if (format.equals("1"))  
                    sreturn = sreturn + "年";  
                else if (format.equals("2"))  
                    sreturn = sreturn + "-";  
                else if (format.equals("3"))  
                    sreturn = sreturn + "/";  
                else if (format.equals("5"))  
                    sreturn = sreturn + ".";  
            }  
            // 处理月份  
            if (yf.equals("1")) {  
                sreturn = sreturn + s_yf;  
                if (format.equals("1"))  
                    sreturn = sreturn + "月";  
                else if (format.equals("2"))  
                    sreturn = sreturn + "-";  
                else if (format.equals("3"))  
                    sreturn = sreturn + "/";  
                else if (format.equals("5"))  
                    sreturn = sreturn + ".";  
            }  
            // 处理日期  
            if (rq.equals("1")) {  
                sreturn = sreturn + s_rq;  
                if (format.equals("1"))  
                    sreturn = sreturn + "日";  
            }  
        } else {  
            // 不是空值，也是一个合法的日期值，则先将其转换为标准的时间格式  
            // sdate = roc.util.RocDate.getOKDate(sdate);  
            s_nd = sdate.substring(0, 4); // 年份  
            s_yf = sdate.substring(5, 7); // 月份  
            s_rq = sdate.substring(8, 10); // 日期  
            if (nd.equals("1")) {  
                sreturn = s_nd;  
                // 处理间隔符  
                if (format.equals("1"))  
                    sreturn = sreturn + "年";  
                else if (format.equals("2"))  
                    sreturn = sreturn + "-";  
                else if (format.equals("3"))  
                    sreturn = sreturn + "/";  
                else if (format.equals("5"))  
                    sreturn = sreturn + ".";  
            }  
            // 处理月份  
            if (yf.equals("1")) {  
                sreturn = sreturn + s_yf;  
                if (format.equals("1"))  
                    sreturn = sreturn + "月";  
                else if (format.equals("2"))  
                    sreturn = sreturn + "-";  
                else if (format.equals("3"))  
                    sreturn = sreturn + "/";  
                else if (format.equals("5"))  
                    sreturn = sreturn + ".";  
            }  
            // 处理日期  
            if (rq.equals("1")) {  
                sreturn = sreturn + s_rq;  
                if (format.equals("1"))  
                    sreturn = sreturn + "日";  
            }  
        }  
        return sreturn;  
    } 
    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为3，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为3 */
                valueLength += 3;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
    
	/***************************************************************************
	 * 参数校验
	 * 
	 * @param str
	 */
	public static boolean isValid(String str) {
		// 使用正则表达式过滤：

		// 过滤 ‘
		// ORACLE 注解 -- /**/
		// 关键字过滤 update ,delete
		if(!StringUtils.isEmpty(str))
		{
			String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
					+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

			Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			if (sqlPattern.matcher(str).find()) {

				// logger.error("未能通过过滤器：p=" + p);

				return false;
			}
			return true;
		}else
		{
			return true;
		}
		
	}


}
