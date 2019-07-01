package com.lingnan.usersys.domain;

import java.util.Date;

public class UserVO {

	private int ID;			 //编号，主键
	private String Name;	 //姓名
	private String Sex;		 //性别
	private int Age;		 //年龄
	private Date Birth;		 //出生年月日
	private String Mail;	 //邮箱
	private String Password; //密码
	private String Superuser;//权限
	
	/**
	 * 获取编号
	 * @return ID
	 */
	public int getID() {
		return ID;
	}
	/**
	 * 输入编号
	 * @param iD 编号
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * 获取姓名
	 * @return Name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * 输入姓名
	 * @param name 姓名
	 */
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * 获取性别
	 * @return Sex
	 */
	public String getSex() {
		return Sex;
	}
	/**
	 * 输入性别
	 * @param sex 性别
	 */
	public void setSex(String sex) {
		Sex = sex;
	}
	
	/**
	 * 获取年龄
	 * @return Age
	 */
	public int getAge() {
		return Age;
	}
	/**
	 * 输入年龄
	 * @param age 年龄
	 */
	public void setAge(int age) {
		Age = age;
	}
	
	/**
	 * 获取出生日期
	 * @return Birth
	 */
	public Date getBirth() {
		return Birth;
	}
	/**
	 * 输入出生日期
	 * @param birth 出生日期
	 */
	public void setBirth(Date birth) {
		Birth = birth;
	}
	
	/**
	 * 获取邮箱
	 * @return Mail
	 */
	public String getMail() {
		return Mail;
	}
	/**
	 * 输入邮箱
	 * @param mail 邮箱
	 */
	public void setMail(String mail) {
		Mail = mail;
	}
	
	/**
	 * 获取密码
	 * @return Password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * 输入密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		Password = password;
	}
	
	/**
	 * 获取权限
	 * @return Superuser
	 */
	public String getSuperuser() {
		return Superuser;
	}
	/**
	 * 输入权限
	 * @param superuser 权限
	 */
	public void setSuperuser(String superuser) {
		Superuser = superuser;
	}
}
