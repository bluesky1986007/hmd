package common.frame.helper;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Utils {
	
	
	/**
	 * 获取系统当前日期，格式yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDate(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		
		String currentDate =  df.format(new Date());//new Date()为获取系统当前时间
		
		System.out.println("系统当前日期为: " + currentDate);
				
		return currentDate;
	}


}
