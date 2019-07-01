package com.lingnan.usersys.business.dao;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.lingnan.common.exception.DateException;
import com.lingnan.common.util.DBUtils;
import com.lingnan.common.util.TypeUtils;
import com.lingnan.usersys.controller.UserController;
import com.lingnan.usersys.domain.UserVO;

public class UserDAOImplTest {
	
	Scanner input = new Scanner(System.in);
//	
//	@Test
//	public void testloginUser() throws Exception {
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		UserVO uv = null;
//		System.out.println("请输入账号：");
//		int ID = input.nextInt();
//		System.out.println("请输入密码：");
//		String Password = input.next();
//		uv = ua.loginUser(ID, Password);
//		System.out.println("账号："+uv.getID());
//		System.out.println("姓名："+uv.getName());
//		System.out.println("性别："+uv.getSex());
//		System.out.println("年龄："+uv.getAge());
//		System.out.println("生日："+uv.getBirth());
//		System.out.println("邮箱："+uv.getMail());
//		System.out.println("密码："+uv.getPassword());
//		System.out.println("权限："+uv.getSuperuser());
//	}
//	
//	@Test
//	public void testregisterUser() {
//		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		System.out.println("请输入账号：");
//		//实例化用户
//		try {
//			//实例化用户
//			UserVO uv = new UserVO();
//			//读取输入的各个值，赋值给用户对象的各个属性
//			uv.setID(Integer.parseInt(bfr.readLine()));
//			System.out.println("请输入姓名：");
//			uv.setName(bfr.readLine());
//			System.out.println("请输入性别：");
//			uv.setSex(bfr.readLine());
//			System.out.println("请输入年龄：");
//			uv.setAge(Integer.parseInt(bfr.readLine()));
//			System.out.println("请输入生日（YYYY-MM-DD）：");
//			uv.setBirth(TypeUtils.StrToDate(bfr.readLine()));
//			while(true){
//				System.out.println("请输入邮箱：");
//				String Mail = bfr.readLine();
//				if(TypeUtils.CheckMail(Mail)){
//					uv.setMail(Mail);
//					break;
//				}
//			}
//			System.out.println("请输入密码：");
//			uv.setPassword(bfr.readLine());
//			System.out.println("请输入权限：");
//			uv.setSuperuser(bfr.readLine());
//			//调用用户控制器中的registerUser方法，进行用户注册操作
//			UserController uc = new UserController();
//			boolean register = uc.registerUser(uv);
//			//如果返回值为true则注册成功，显示成功信息；否则注册失败，显示失败信息
//			if(register){
//				System.out.println("用户注册成功！");
//			}else{
//				System.out.println("添加用户失败！");
//			}
//		} catch (DateException e) {
//			System.out.println(e.getMessage());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	@Test
//	public void testfindAllUser() {
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		Vector<UserVO> uv = new Vector<UserVO>();
//		uv = ua.findAllUser();
//		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
//		for(UserVO s:uv)
//			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());	
//	}
//	
//	@Test
//	public void testfindUserByID() {
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		Vector<UserVO> uv = new Vector<UserVO>();
//		System.out.println("请输入要查询的ID：");
//		int ID = input.nextInt();
//		uv = ua.findUserByID(ID);
//		System.out.println("--------------------------------------------------");
//		System.out.println("该用户信息如下：");
//		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
//		for(UserVO s:uv)
//			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());	
//	}
//	
//	@Test
//	public void testfindUserByName(){
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		Vector<UserVO> uv = new Vector<UserVO>();
//		System.out.println("请输入要查询的姓名：");
//		String Name = input.next();
//		uv = ua.findUserByName(Name);
//		System.out.println("--------------------------------------------------");
//		System.out.println("该用户信息如下：");
//		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
//		for(UserVO s:uv)
//			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());	
//	}
//	
//	@Test
//	public void testdeleteUserByID(){
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		boolean flag = false;
//		System.out.println("请输入要删除的ID：");
//		int ID = input.nextInt();
//		flag = ua.deleteUserByID(ID);
//		if(flag)
//			System.out.println("删除成功！");
//		else
//			System.out.println("删除失败！");
//	}
//	
//	@Test
//	public void testupdateUser() {
//		//声明缓冲处理流对象，用于接收控制台输入的数据
//		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		UserVO uv = new UserVO();
//		System.out.println("请输入用户ID：");
//		try {
//			uv.setID(Integer.parseInt(bfr.readLine()));
//			System.out.println("请输入要更新的用户姓名：");
//			uv.setName(bfr.readLine());
//			System.out.println("请输入要更新的用户性别：");
//			uv.setSex(bfr.readLine());
//			System.out.println("请输入要更新的用户年龄：");
//			uv.setAge(Integer.parseInt(bfr.readLine()));
//			System.out.println("请输入要更新的用户生日（YYYY-MM-DD）：");
//			uv.setBirth(TypeUtils.StrToDate(bfr.readLine()));
//			while(true){
//				System.out.println("请输入要更新的用户邮箱：");
//				String Mail = bfr.readLine();
//				if(TypeUtils.CheckMail(Mail)){
//					uv.setMail(Mail);
//					break;
//				}
//			}
//			System.out.println("请输入要更新的用户密码：");
//			uv.setPassword(bfr.readLine());
//			System.out.println("请输入要更新的用户权限：");
//			uv.setSuperuser(bfr.readLine());
//			//调用用户控制器中的updateUser方法，进行更新用户信息操作
//			UserController uc = new UserController();
//			boolean update = uc.updateUser(uv);
//			if(update)
//				System.out.println("更新成功！");
//			else
//				System.out.println("更新失败！");
//		} catch (DateException e) {
//			//显示异常信息
//			System.out.println("更新用户时出错"+e.getMessage());
//		} catch (Exception e) {
//			System.out.println("更新用户时出错"+e.getMessage());
//		}
//	}
//	
//	@Test
//	public void testfindMaxAge() {
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		Vector<UserVO> uv = new Vector<UserVO>();
//		uv = ua.findMaxAge();
//		System.out.println("--------------------------------------------------");
//		System.out.println("该用户信息如下：");
//		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
//		for(UserVO s:uv)
//			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());	
//	}
//	
//	@Test
//	public void testgetCountUser() {
//		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
//		UserVO uv = null;
//		ua.getCountUser();
//	}
	
	@Test
	public void testfindUsers() {
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		Vector<UserVO> uv = new Vector<UserVO>();
		System.out.println("请输入页数：");
		int pageNo = input.nextInt();
		System.out.println("请输入条数：");
		int pageSize = input.nextInt();
		uv = ua.findUsers(pageNo, pageSize);
		System.out.println("--------------------------------------------------");
		System.out.println("该页的用户信息如下：");
		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
		for(UserVO s:uv)
			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());	
	}
}
