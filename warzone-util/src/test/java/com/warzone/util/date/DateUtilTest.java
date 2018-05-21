package com.warzone.util.date;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.warzone.util.date.DateUtil;

public class DateUtilTest {

	
	@Test
	public void testDatePlus(){
		Date date1=DateUtil.parseDate("2015-02-01 11:11:11", "yyyy-MM-dd HH:mm:ss");
		Date date=DateUtil.datePlus(date1, 2015, 02, 01, 11, 11, 11);
		Assert.assertEquals(date, DateUtil.parseDate("4030-04-02 22:22:22", "yyyy-MM-dd HH:mm:ss"));
	}
	
}
