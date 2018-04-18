package com.zheng.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关的操作
 * @author houqingheng
 * email:hqh1689@163.com
 *  
 */

public class DateUtil {
	public static Date getDate(String pattern){
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
		Date today = null;
		try {
			today = sdf.parse(getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return today;
	}
	public static String getDate()
	{
		String DATE_TIME_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			DATE_TIME_FORMAT);
		String time = simpleDateFormat.format(new Date(System
			.currentTimeMillis()));
		return time;
	}
	/**
	 * 获取当前时间字符串yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateTime()
	{
		String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			DATE_TIME_FORMAT);
		String time = simpleDateFormat.format(new Date(System
			.currentTimeMillis()));
		return time;
	}
	/**
	 * 获取多少天之前日期
	 * @param beforDay
	 * @return
	 */
	public static String getAfterDateToString(int beforDay,String pattern){
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		long currTime = System.currentTimeMillis();
		long time = currTime/1000 + beforDay*24*60*60;
		String str  = DateUtil.toString(time, pattern);
		return str;
	}
	public static Date getAfterDate(int beforDay,String pattern){
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		long currTime = System.currentTimeMillis();
		long time = currTime/1000 + beforDay*24*60*60;
		String str  = DateUtil.toString(time, pattern);
		
		return toDate(str,pattern);
	}
	public static Date getAfterDate(Date date,int beforDay,String pattern){
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		long currTime = date.getTime();
		long time = currTime/1000 + beforDay*24*60*60;
		String str  = DateUtil.toString(time, pattern);
		
		return toDate(str,pattern);
	}

	/**
	 * 将一个字符串转换成日期格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if((""+date).equals("")){
			return null;
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 把日期转换成字符串型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern){
		if(date == null){
			return "";
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}
	
	public static String toString(Long time,String pattern){
		if(time>0){
			if(time.toString().length()==10){
				time = time*1000;
			}
			Date date = new Date(time);
			String str  = DateUtil.toString(date, pattern);
			return str;
		}
		return "";
	}

	/**
	 * 获取上个月的开始结束时间
	 * @return
	 */
	public static String[] getLastMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH) + 1;
		   
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		   // 输出上月最后一天日期
		   int day = cal.get(Calendar.DAY_OF_MONTH);

		   String months = "";
		   String days = "";

		   if (month > 1) {
		    month--;
		   } else {
		    year--;
		    month = 12;
		   }
		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(day).length() > 1)) {
		    days = "0" + day;
		   } else {
		    days = String.valueOf(day);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay = "" + year + "-" + months + "-" + days;

		   String[] lastMonth = new String[2];
		   lastMonth[0] = firstDay;
		   lastMonth[1] = lastDay;

		 //  System.out.println(lastMonth[0] + "||" + lastMonth[1]);
		   return lastMonth;
		}
	
	
	/**
	 * 获取当月的开始结束时间
	 * @return
	 */
	public static String[] getCurrentMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH)+1 ;
		   
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		   // 输出上月最后一天日期
		   int day = cal.get(Calendar.DAY_OF_MONTH);

		   String months = "";
		   String days = "";


		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(day).length() > 1)) {
		    days = "0" + day;
		   } else {
		    days = String.valueOf(day);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay = "" + year + "-" + months + "-" + days;

		   String[] currentMonth = new String[2];
		   currentMonth[0] = firstDay;
		   currentMonth[1] = lastDay;

		 //  System.out.println(lastMonth[0] + "||" + lastMonth[1]);
		   return currentMonth;
		}
		
	
	
	public static int getDateline(){
		
		return (int)(System.currentTimeMillis()/1000);
	}
	

	/**
	 * 返回10位当前时间戳
	 * @return
	 */
	public static long getDatelineLong(){
		
		return System.currentTimeMillis()/1000;
	}
	
	/**
	 * 返回某时刻的13时间戳
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static long getDatelineLong(String date,String pattern){
//		Date date1 = toDate(date, pattern);  
//        Date date2 = toDate("1970-01-01", "yyyy-MM-dd");  
//        long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime()  
//        - date2.getTime() : date2.getTime() - date1.getTime();  
		return toDate(date, pattern).getTime();
	}
	
	public static int getDatelineForTime(String date){
		return (int)(toDate(date, "yyyy-MM-dd HH:mm:ss").getTime()/1000);
	}
	public static int getDateline(String date){
		return (int)(toDate(date, "yyyy-MM-dd").getTime()/1000);
	}
	public static int getDateLong(String date){
		return (int)(toDate(date, "yyyy-MM").getTime()/1000);
	}
	public static int getDateline(String date,String pattern){
		return (int)(toDate(date, pattern).getTime()/1000);
	}
	
	/**
	 * 获取时间戳  日期范围：2011-09-01 08:00:00到当前  时间范围为8-23点
	 * @return
	 */
	public static long getRandomTimeMillis(){
		long s = DateUtil.toDate("2011-09-01 08:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
		long e = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			long t = (long)(Math.random()*(e-s));
			Long r = s+t;
			String str = DateUtil.toString(r, "HH");
			int h = Integer.valueOf(str);
			if(h>8 && h<23){
//				System.out.println(DateUtil.toString(r, "yyyy-MM-dd HH:mm:ss"));
				return r.longValue();
			}
		}
		long day = (long)(10*Math.random());
		return e-day*24*60*60*1000;
	}
	/**
	 * 检查当前时间是否包含在区间内
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean containsCurrTime(Long startTime,Long endTime){
		if(startTime==null || endTime==null){
			return false;
		}
		Long curr = DateUtil.getDatelineLong();
		if(curr.longValue()>startTime.longValue() && curr.longValue()<endTime.longValue())
			return true;
		return false;
	}
	/**
	 * 检查当前时间是否包含在区间内
	 * @param startTime
	 * @param endTime
	 * @return 0 时间区间错误  1 在区间内 2 当前时间小于开始区间值 3 当前时间大于结束区间值
	 */
	public static int CurrTimeStatus(Long startTime,Long endTime){
		if(startTime==null || endTime==null){
			return 0;
		}
		Long curr = DateUtil.getDatelineLong();
		if(curr.longValue()>startTime.longValue() && curr.longValue()<endTime.longValue())
			return 1;
		else if(curr.longValue()<startTime.longValue())
			return 2;
		else if(curr.longValue()>endTime.longValue())
			return 3;
		return 0;
	}
	
	
	/**
	 * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
        return Integer.parseInt(String.valueOf(between_days));           
    }   
	
	public static String getDataTime() {
		String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			DATE_TIME_FORMAT);
		String time = simpleDateFormat.format(new Date(System
			.currentTimeMillis()));
		return time;
	    }
	
	public static void main(String[] args) throws ParseException{
		System.out.println(toString(new Date(), null));
		
		
	}
	
	public static int daysBetween(Date smdate,Date bdate,String pattern) throws ParseException    
	{    
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);  
		smdate=sdf.parse(sdf.format(smdate));  
		bdate=sdf.parse(sdf.format(bdate));  
		Calendar cal = Calendar.getInstance();    
		cal.setTime(smdate);    
		long time1 = cal.getTimeInMillis();                 
		cal.setTime(bdate);    
		long time2 = cal.getTimeInMillis();         
		long between_days=(time2-time1)/(1000*3600*24);  
		
		return Integer.parseInt(String.valueOf(between_days));           
	} 
	
	public static int getCurrentDayofMonth(String date,String pattern){
		Date date1 = toDate(date,pattern);
		Calendar now = Calendar.getInstance(); 
		now.setTime(date1);
		int i = now.get(Calendar.DAY_OF_MONTH);
		return i;
	}
}
