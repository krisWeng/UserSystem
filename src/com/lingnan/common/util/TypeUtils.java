package com.lingnan.common.util;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner.DateEditor;

import com.lingnan.common.exception.DateException;
import com.lingnan.common.exception.EmailException;

/**
 * 类型转换工具
 * @author krisW
 *
 */
public class TypeUtils {

	/**
	 * 字符串转换为日期
	 * @param str 字符串
	 * @return 转换后的日期
	 */
	public static Date StrToDate(String str){
		Date date = null;
		//设置日期格式
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			//利用parse方法将字符串解析成相应的日期
			date = sdFormat.parse(str);
		}catch(ParseException e){
			//将异常封装成自定义异常
			throw new DateException("字符串转日期失败！",e);
			//System.out.println("字符串转日期失败！");
			//e.printStackTrace();
		}
		//转换后的日期
		return date;
	}
	
	/**
	 * 日期转换成字符串
	 * @param date 日期
	 * @return 转换后的字符串
	 */
	public static String DateToStr(Date date){
		String str = null;
		try{
			//设置日期格式
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年MM月dd日");
			//利用format方法将日期转换成相应的字符串
			str = sdFormat.format(date);
		}catch(Exception e){
			//将异常封装成自定义异常
			throw new DateException("日期转字符串失败！",e);
			//System.out.println("日期转字符串失败！");
			//e.printStackTrace();
		}
		//转换后的字符串
		return str;
	}
	
	/**
	 * 检查邮箱格式
	 * @param mail 输入邮箱
	 * @return 邮箱格式没有错则返回true，否则返回false
	 */
	public static boolean CheckMail(String mail){
		try{
			String check = mail.replaceAll("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", "");
		
			if(check.length()==0)
				return true;
			else
				System.out.println("你的邮箱格式不正确！");
				return false;
		}catch(Exception e){
			//将异常封装成自定义异常
			throw new EmailException("邮箱检查出错！",e);
			//System.out.println("邮箱检查出错！");
		}
	}
	
	/**
	 * 字符串判空
	 * @param str 输入字符串
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean str(String str){
		if(str == null || str.length() <= 0)
			return true;
		else
			return false;
	}
}
