package com.myclass.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	 /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static Integer getWeekOfDate(String date) {
        Integer[] weekDays = {7,1,2,3,4,5,6};
        Calendar cal = Calendar.getInstance();
        Date dt = parseToDate(date);
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    /**
     * 获取当前格式化日期
     * @param formate
     * @return
     */
    public static String getFormatDate(String formate){
    	 Date date=new Date();
    	 SimpleDateFormat dateFm = new SimpleDateFormat(formate);
    	 return dateFm.format(date);
    }
    
    public static int getDayOfMonth(Integer year,Integer month){
    	Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;
	}
    
    public static int getDateOfMonth(){
    	Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
    	return aCalendar.get(Calendar.DATE);
    }
    
    public static Date parseToDate(String date){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
    }
    
    public static Integer getCurrentDate(String type){
    	Calendar a=Calendar.getInstance();
    	if(type.equals("year")){
    		return a.get(Calendar.YEAR);
    	}else if(type.equals("month")){
    		return a.get(Calendar.MONTH)+1;
    	}else if(type.equals("day")){
    		return a.get(Calendar.DATE);
    	}
    	return null;
    }
}
