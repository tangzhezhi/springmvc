package org.tang.jpa.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

public final class DateTool {

	/**
	 * 实现当前天的后amount天 返回yyyymmdd字符串
	 */
	public static String getDayBeforeCurrentDay(int amount) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, amount);
		String s = c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1) + ""
				+ c.get(Calendar.DAY_OF_MONTH);
		return s;
	}

	/**
	 * 实现当前天的前amount天 返回yyyymmdd字符串*/
	public static String getDayAfterCurrentDay(int amount) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -amount);
		String s = c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1) + ""
				+ c.get(Calendar.DAY_OF_MONTH);
		return s;
	}
	
	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	
	
	/**
	 * 根据出生日期计算年龄大小.后面不带单位
	 * 
	 * @param birthDate
	 *            出生日期
	 * @return 返回值判定：由出生日期计算得出，如果是大于1岁则直接显示岁； 小于1岁大于3个月则显示月；小于3个月则显示天；后面没有单位
	 * @author jliao
	 */
	public static String calculateAge1(Date birthDate) {

		if (birthDate == null) {
			return null;
		}

		String age = null;
		Date nowDate = new Date();
		if (nowDate.before(birthDate)) {
			// 当前日期早于出生日期
			age = "0";
		} else {
			GregorianCalendar birthDateCalendar = new GregorianCalendar();
			birthDateCalendar.setTime(birthDate);
			GregorianCalendar nowDateCalendar = new GregorianCalendar();
			nowDateCalendar.setTime(nowDate);

			// 计算两个年份之差
			int yDiff = nowDateCalendar.get(Calendar.YEAR)
					- birthDateCalendar.get(Calendar.YEAR);
			int mDiff = nowDateCalendar.get(Calendar.MONTH)
					- birthDateCalendar.get(Calendar.MONTH);
			int dDiff = nowDateCalendar.get(Calendar.DATE)
					- birthDateCalendar.get(Calendar.DATE);

			if (mDiff < 0 || (mDiff == 0 && dDiff < 0)) {
				yDiff--;
			}

			// 大于1岁直接显示岁
			if (yDiff >= 1) {
				age = yDiff + "";
			} else {
				if (dDiff < 0) {
					mDiff--;
				}

				// 大于3个月则显示月
				if (mDiff >= 3) {
					age = mDiff + "";
				}

				// find day diff
				else {
					int sDoy = birthDateCalendar.get(Calendar.DAY_OF_YEAR);
					int cDoy = nowDateCalendar.get(Calendar.DAY_OF_YEAR);
					int dayDiff = cDoy - sDoy;
					if (dayDiff < 0) {
						dayDiff += (birthDateCalendar
								.isLeapYear(birthDateCalendar
										.get(Calendar.YEAR)) ? 366 : 365)
								- sDoy;
					}

					age = dayDiff + "";
				}
			}
		}

		return age;
	}
	
	
	/**
	 * 返回日期时间字符串yyyymmdd
	 * @param date
	 * @return
	 */
	public static String getDateStringYMD(Date date){
		DateTime dt = new DateTime(date);
		return dt.toString("yyyyMMdd");
	}
	
	
	/**
	 * 返回日期时间字符串yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String getDateStringYMDHMS(Date date){
		DateTime dt = new DateTime(date);
		return dt.toString("yyyyMMddHHmmss");
	}
	
	/**
	 * 返回日期时间字符串HHmmss
	 * @param date
	 * @return
	 */
	public static String getDateStringHMS(Date date){
		DateTime dt = new DateTime(date);
		return dt.toString("yyyyMMddHHmmss").substring(8);
	}
	

	
}
