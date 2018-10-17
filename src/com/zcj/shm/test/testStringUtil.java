package com.zcj.shm.test;

import java.util.Calendar;

import org.junit.Test;

import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.StringUtil;

public class testStringUtil {

	@Test
	public void testCutString() {
		int l = StringUtil.calculatePlaces("ti()n（）s，,huo听说名字太长会有问题", 100);
		String cutString1 = StringUtil.cutString("ti(sd)n（）)n（）s，,huos，,huo听说名uo听说名d)n（）s字太)n（）s，,d)n（）s字太)n（）s，,huo长会有问题", 1000);
		String cutString2 = StringUtil.cutString("to听说名字太长i()n（）uo听说名d)n（）s字太)n（）s，,s，,huo)n（）s，,huo听说名字太长会有问题", 10);
		String cutString3 = StringUtil.cutString("to听说名字太)n（）s，,huo长i()n（）o听说名字d)n)n（）s，,huo（）s太长s，,huo听说名字太长会有问题", 10);
		String cutString4 = StringUtil.cutString("ti()no听说名字太长（uo听说名d)n字太)n（听说名d)n（）s字太)n（）s，,）s，,）s，,)n（）s，,huohuo听d)n（）s说名字太长会有问题", 10);
		String cutString5 = StringUtil.cutString("o听说名字太长ti()n（）s，,hd)n（）suo听说uo听说名d)n（）s字太)n（）s，,名字太长)n（）s，,huo会有问题", 10);
		System.out.println(l);
		System.out.println(cutString1);
		System.out.println(cutString2);
		System.out.println(cutString3);
		System.out.println(cutString4);
		System.out.println(cutString5);
	}

	@Test
	public void test() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 2, 4, 21, 54, 57);
		DateUtil.getTimeDifference(cal);
	}

	@Test
	public void test1() {
		try {
			int i = 5 / 0;
			System.out.println(i);
		} catch (Exception e) {
			System.out.println("错误");
			// TODO: handle exception
		}
	}
	
	

}
