package com.p2p.webapp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 放置常用的与日期时间处理有关的功能
 * @author eoems@sina.com Lizhenzhong
 *
 */

public class DateTimeTool {

	public static boolean isWindow(){
    	if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS")>=0){
    		return true;
    	}
    	else{
    		return false;
    	}
	}

	public static String format(Date date , String format){
		if(date==null){
			return "";
		}
		return new SimpleDateFormat(format).format(date);
	}
	
	

	public static Date parse(String date , String format){
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	public static Date parse(String date){
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	/**
	 * yyyyMMdd
	 * HH:mm:ss
	 * yyyyMMdd HH:mm:ss 
	 * @param format
	 * @return
	 */
	public static String getToday(String format){
		return new SimpleDateFormat(format).format(new Date());
	}

	public static Date getTodayDate(){		
		return new Date();
	}

	/**
	 * 取出日期中，今天凌晨的时间
	 * @param date
	 * @return
	 */
	public static Date getBeginTime(Date date){
		if(date==null){
			return getMinDate();
		}
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
		try {
			return new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS").parse(dateStr+" 00:00:00 000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return date;
		}
	}
	
	/**
	 * 取出日期中，今天结束的时间
	 * @param date
	 * @return
	 */
	public static Date getEndTime(Date date){
		if(date==null){
			return getMaxDate();
		}
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
		try {
			return new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS").parse(dateStr+" 23:59:59 999");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return date;
		}
	}

	/**
	 * 取出今天日期中，今天凌晨的时间
	 * @param date
	 * @return
	 */
	public static Date getTodayBeginTime(){
		return getBeginTime(new Date());
	}
	
	/**
	 * 取出今天日期中，今天结束的时间
	 * @param date
	 * @return
	 */
	public static Date getTodayEndTime(){
		return getEndTime(new Date());
	}
	
	public static Date getMinDate(){
		try {
			return new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse("19000101 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static Date getMaxDate(){
		try {
			return new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse("29000101 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}
	
	/**
	 * 取出某个日期的多少个月之后的日期
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getAfterMonths(Date date,int months){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH,months);
		return cal.getTime();		
	}
	
	public static void main(String[] str){
		System.out.println(DateTimeTool.getBeginTime(new Date()));
	}
}
