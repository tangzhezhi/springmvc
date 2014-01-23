package org.tang.jpa;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.joda.time.DateTime;

public class Test {

	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		DateTime dt = new DateTime(new Date());
		System.out.println(dt.toString("yyyyMMddHHmmss"));
		String str = dt.toString("yyyyMMddHHmmss");
		
		System.out.println(str.substring(8));
		
	}

}
