package com.lingnan.common.util;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class TypeUtilsTest {
	
	Scanner input = new Scanner(System.in);
	@Test
	public void testStrToDate() {
		Date date = null;
		System.out.println("请输入日期：（YYYY-MM-DD）");
		String d = input.next();
		date = TypeUtils.StrToDate(d);
		System.out.println("字符串转日期的结果："+date);
		
//		Date date = TypeUtils.StrToDate("1992-01-02");
//		System.out.println("字符串转日期的结果："+date);
		
	}

	@Test
	public void testDateToStr() {
		Date date = new Date();
		String str = TypeUtils.DateToStr(date);
		System.out.println("日期转字符串的结果："+str);
	}

	@Test
	public void testCheckMail() {
		boolean flag = TypeUtils.CheckMail("111aaa@qq.com");
		System.out.println("邮箱格式："+flag);
	}
	
	@Test
	public void teststr() {
		boolean flag = TypeUtils.str("axs");
		System.out.println("字符串是否为空："+flag);
	}

}
