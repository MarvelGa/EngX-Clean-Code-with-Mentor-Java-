package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public Date changeToMidnight(Date date, boolean up) {
		Calendar calendar = Calendar.getInstance();
		adjustingDate(date, up, calendar);
		return calendar.getTime();
	}

	private void adjustingDate(Date date, boolean up, Calendar calendar) {
		calendar.setTime(date);
		calendar.add(Calendar.DATE, up ? 1 : -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

}
