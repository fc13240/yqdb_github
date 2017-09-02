package com.p2p.webapp.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CalenderUtil {
	private static Log log = LogFactory.getLog(CalenderUtil.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date1 = CalenderUtil.getDate("2008-12-15");
		System.out.println(date1);
		Date date2 = CalenderUtil.nextDate(date1);

		System.out.println(date2);
		String ymd = CalenderUtil.getDateYmd(date2);
		System.out.println(ymd);
		System.out.println(date1);
		int i = CalenderUtil.getMyDateOfWeek(date2);

		System.out.println(i);
		CalenderUtil.test();
		Object [] obj = CalenderUtil.preWeek(date1);
		System.out.println(CalenderUtil.getDateYmd((Date) obj[0]));
		System.out.println(CalenderUtil.getDateYmd((Date) obj[1]));
		obj = CalenderUtil.preMonth(date1);
		System.out.println(CalenderUtil.getDateYmd((Date) obj[0]));
		System.out.println(CalenderUtil.getDateYmd((Date) obj[1]));

	}

	public static void test() {
		String ymd1 = "2008-10-06";
		String ymd2 = "2009-06-19";
		Date d1 = CalenderUtil.getDate(ymd1);
		Date d2 = CalenderUtil.getDate(ymd2);
		Date dateEnd = null;

		while (d1.before(d2)) {
			int week = CalenderUtil.getMyDateOfWeek(d1);
			System.out.println(CalenderUtil.getDateYmd(d1) + ">>" + week);
			d1 = CalenderUtil.nextDate(d1);

		}
	}

	public static String getDateYmd(Date date) {
		return getDateYmd(date, "yyyy-MM-dd");
	}

	public static String getDateYmd(Date date, String pattern) {
		String dateymd = "";

		if (date == null)
			return null;
		if (pattern == null)
			pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			dateymd = df.format(date);
		} catch (Exception e) {
			log.error("CalenderUtil.Exception.getDateYmd:" + e.toString());
		}
		return dateymd;

	}

	public static Date getDate(String ymd, String pattern) {
		Date date = null;
		if (ymd == null)
			return null;
		if (pattern == null)
			pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			date = df.parse(ymd);
		} catch (ParseException e) {
			log.error("CalenderUtil.Exception.getDate:" + e.toString());
		}
		return date;
	}

	public static Date nextDate(String ymd) {

		Date date = null;
		Calendar cdr = Calendar.getInstance();
		try {
			date = getDate(ymd);
			if (date != null) {
				cdr.setTime(date);
				cdr.add(Calendar.DATE, 1);
				date = cdr.getTime();
			}

		} catch (Exception e) {
			log.error("CalenderUtil.Exception.nextDate" + e.toString());
		}

		return date;

	}

	public static Date nextDate(Date date) {
		Date dd = null;
		Calendar cdr = Calendar.getInstance();
		try {
			if (date != null) {
				cdr.setTime(date);
				cdr.add(Calendar.DATE, 1);
				dd = cdr.getTime();
			}

		} catch (Exception e) {
			log.error("CalenderUtil.Exception.nextDate" + e.toString());
		}

		return dd;

	}

	public static Date getDate(String ymd) {
		return getDate(ymd, "yyyy-MM-dd");
	}

	public static int getMyDateOfWeek(Date date) {
		Calendar cdr = Calendar.getInstance();
		int dow = 0;
		cdr.setTime(date);
		dow = cdr.get(Calendar.DAY_OF_WEEK);
		return dow;
	}

	public static int getDateInteger(Date date) {
		String ymd = CalenderUtil.getDateYmd(date);
		StringBuffer temp = new StringBuffer();
		temp.append(ymd.substring(0, 4));
		temp.append(ymd.substring(5, 7));
		temp.append(ymd.substring(8, 10));
		int result = Integer.parseInt(temp.toString());
		return result;
	}

	public static Date preDate(Date date) {
		Date dd = null;
		Calendar cdr = Calendar.getInstance();
		try {
			if (date != null) {
				cdr.setTime(date);
				cdr.add(Calendar.DATE, -1);
				dd = cdr.getTime();
			}

		} catch (Exception e) {
			log.error("CalenderUtil.Exception.nextDate" + e.toString());
		}
		return dd;
	}
	
	public static Object[] preWeek(Date date) {
		Object [] obj = new Object[2];
		Calendar cdr = Calendar.getInstance();
		try {
			if (date != null) {
				cdr.setTime(date);
				int i = getMyDateOfWeek(date);
				if(i == 1){
					cdr.add(Calendar.DATE,-7);
					obj[1] = cdr.getTime();
					cdr.setTime((Date)obj[1]);
					cdr.add(Calendar.DATE,-6);
					obj[0] = cdr.getTime();
				} else{
					System.out.println(i);
					cdr.add(Calendar.DATE, -i+1);
					obj[1] = cdr.getTime();
					cdr.setTime((Date)obj[1]);
					cdr.add(Calendar.DATE,-6);
					obj[0] = cdr.getTime();
				}
			}

		} catch (Exception e) {
			log.error("CalenderUtil.Exception.nextDate" + e.toString());
		}
		return obj;
	}
	
	public static Object[] preMonth(Date date) {
		Object [] obj = new Object[2];
		Calendar cdr = Calendar.getInstance();
		try {
			if (date != null) {
				cdr.setTime(date);
				int i = cdr.get(5);
				cdr.add(Calendar.DATE, -i);
				obj[1] = cdr.getTime();
				cdr.setTime((Date)obj[1]);
				i = cdr.get(5);
				cdr.add(Calendar.DATE,-i+1);
				obj[0] = cdr.getTime();
			}

		} catch (Exception e) {
			log.error("CalenderUtil.Exception.nextDate" + e.toString());
		}
		return obj;
	}
	
	public static Date nextYear(Date date){
		Calendar cdr = Calendar.getInstance();
		if(date != null){
			cdr.setTime(date);
		}
		cdr.add(Calendar.YEAR, 1);
		return cdr.getTime();
	}

	public static Date nextMinute(int addMinute){
		Calendar cdr = Calendar.getInstance();
		cdr.add(Calendar.MINUTE,addMinute );
		return cdr.getTime();
	}
	

	public static Date nextDateByDay(Date date,int day){
		Calendar cdr = Calendar.getInstance();
		if(date != null){
			cdr.setTime(date);
		}
		cdr.add(Calendar.DATE, day);
		return cdr.getTime();
	}
	
}
