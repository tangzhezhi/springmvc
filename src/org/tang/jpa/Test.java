package org.tang.jpa;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

public class Test {
	
	// 获取img标签正则  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?/>";  
	
	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		
		
//		DateTime dt = new DateTime(new Date());
//		System.out.println(dt.toString("yyyyMMddHHmmss"));
//		String str = dt.toString("yyyyMMddHHmmss");
//		
//		System.out.println(str.substring(8));
		
		
		String test = "<div><p><img alt='' src=\"/springmvc/resources/upload_pic/news/shengtong.ico\" " +
				"style='height:48px; width:48px' /><img alt='' src='/springmvc/resources/upload_pic/news/shengtong.ico' " +
				"style='height:48px; width:48px' /></p>" +
				"";
		
		 Matcher matcher = Pattern.compile(IMGURL_REG).matcher(test);  
	        List<String> listImgUrl = new ArrayList<String>();  
	        while (matcher.find()) {  
	            listImgUrl.add(matcher.group());  
	        }  
	        
	        
	        for(String  img : listImgUrl){
	        	System.out.println(img);
	        }
		
	}

}
