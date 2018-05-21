package com.warzone.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public final static int HOUR_MINUTE_SECOND=1;
	public final static int MINUTE_SECOND=2;
	public final static int SECOND=4;
	
	
	/**
	 * 时间加
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date datePlus(Date date,int year,int month,int day,int hour,int minute,int second){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DAY_OF_MONTH+1, day);
		c.add(Calendar.HOUR_OF_DAY, hour);
		c.add(Calendar.MINUTE, minute);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}
	
	/**
	 * 根据日期字符串和日期格式构造日期
	 * @param dateStr 日期字符串，如2010-01-01
	 * @param pattern 日期格式，如yyyy-MM-dd
	 * @return
	 */
	public static Date parseDate(String dateStr,String pattern){
		DateFormat df=new SimpleDateFormat(pattern);
		Date date=null;
		try {
			date=df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 根据日期格式格式化日期
	 * @param date
	 * @param formatPattern
	 * @return
	 */
	public static String formatDate(Date date,String formatPattern){
		DateFormat dateFormat=new SimpleDateFormat(formatPattern);
		return dateFormat.format(date);
	}
	/**
	 * 格式化时间间隔
	 * @param interval 时间间隔(秒),如70s,格式化后为01:10
	 * @param pattern DateUtil常量
	 * @return 返回格式化后的字符串
	 */
	public static String formatTimeInterval(int interval,int pattern){
		
		StringBuilder sb=new StringBuilder();
		switch (pattern) {
		case HOUR_MINUTE_SECOND:
			sb.append(getFieldByInterval(interval, HOUR_MINUTE_SECOND))
				.append(getFieldByInterval(interval, MINUTE_SECOND))
				.append(getFieldByInterval(interval, SECOND));
			break;
		case MINUTE_SECOND:
			sb.append(getFieldByInterval(interval, MINUTE_SECOND))
			.append(getFieldByInterval(interval, SECOND));
			break;
		case SECOND:
			sb.append(getFieldByInterval(interval, SECOND));
			break;
		default:
			throw new IllegalArgumentException("pattern="+pattern+"不在DateUtil的常量内!");
		}
		if(sb.length()>0&&sb.charAt(sb.length()-1)==':'){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	
	
	private static String getFieldByInterval(int interval,int field){
		String ret;
		switch (field) {
		case HOUR_MINUTE_SECOND:
			int hour=interval/60/60;
			ret=String.format("%02d:", hour);
			break;
		case MINUTE_SECOND:
			int minute=interval%(60*60)/60;
			ret=String.format("%02d:", minute);
			break;
		case SECOND:
			int second=interval%60;
			ret=String.format("%02d:", second);
			break;
		default:
			throw new IllegalArgumentException("field="+field+"不在DateUtil的常量内!");
		}
		return ret;
	}
	
}
