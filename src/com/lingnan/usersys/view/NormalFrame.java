package com.lingnan.usersys.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

import com.lingnan.common.exception.DateException;
import com.lingnan.common.util.DBUtils;
import com.lingnan.common.util.TypeUtils;
import com.lingnan.usersys.business.dao.UserDAOImpl;
import com.lingnan.usersys.controller.UserController;
import com.lingnan.usersys.domain.UserVO;

/**
 * 用于用户管理：权限分为普通用户和管理员
 * 这个是普通用户
 * @author krisW
 *
 */
public class NormalFrame extends IndexFrame{

	//声明缓冲处理流对象，用于接收控制台输入的数据
	BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
	//用户对象
	UserVO uv = null;
	
	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param user 登录的用户
	 */
	public NormalFrame(UserVO user){
		this.uv = user;
	}

	public void show(){
		//循环操作
		while(true){
			//显示普通用户登录成功后的操作界面
			System.out.println("===================主界面！==================");
			System.out.println("1、查询信息");
			System.out.println("2、更新信息");
			System.out.println("0、退出");
			System.out.println("请输入你要进行操作的序号：");
			int i = -1;
			//读取用户控制台输入，如果输入值正确则中断循环，否则提示错误信息重新输入
			while(true){
				try {
					//读取用户输入操作选项的数字，同时转换为int型
					i = Integer.parseInt(bfr.readLine());
					//中断该循环，进行下一步操作：i值判断
					break;
				} catch (Exception e) {
					//出现异常时，提示错误信息，再重新输入
					System.out.println("输入有误，请重新输入！（只能输入0~2的数字）");
				} 
			}
			
			/**
			 * 判断输入值
			 * 值为1，则进行查询信息操作
			 * 值为2，则进行更新信息操作
			 * 值为0，则退出程序
			 */
			switch(i){
			case 1:
				this.findOneShow();
				break;
			case 2:
				this.updateOneShow();
				break;//中断switch
			case 0:
				new IndexFrame().show();//返回登录注册界面
				
			default://输入0/1/2以外的数字
				System.out.println("输入有误，请重新输入！");
			}
		}
	}
	
	private void findOneShow(){
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		Vector<UserVO> u = new Vector<UserVO>();
		u = ua.findUserByID(uv.getID());
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("你的信息如下：");
		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
		for(UserVO s:u)
			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());	
		System.out.println();
	}	
	
	private void updateOneShow() {
		System.out.println();
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		UserVO u = new UserVO();
		boolean flag = false;			
		System.out.println("--------------------------------------------------");
		try {
			u.setID(uv.getID());
			System.out.println("请输入要更新的用户姓名：");
			u.setName(bfr.readLine());
			System.out.println("请输入要更新的用户性别：");
			u.setSex(bfr.readLine());
			System.out.println("请输入要更新的用户年龄：");
			u.setAge(Integer.parseInt(bfr.readLine()));
			System.out.println("请输入要更新的用户生日（YYYY-MM-DD）：");
			u.setBirth(TypeUtils.StrToDate(bfr.readLine()));
			while(true){
				System.out.println("请输入要更新的用户邮箱：");
				String Mail = bfr.readLine();
				if(TypeUtils.CheckMail(Mail)){
					u.setMail(Mail);
					break;
				}
			}
			System.out.println("请输入要更新的用户密码：");
			u.setPassword(bfr.readLine());
			System.out.println("请输入要更新的用户权限：");
			u.setSuperuser(bfr.readLine());
			//调用用户控制器中的updateUser方法，进行更新用户信息操作
			UserController uc = new UserController();
			boolean update = uc.updateUser(u);
			if(update)
				System.out.println("更新成功！");
			else
				System.out.println("更新失败！");
		} catch (DateException e) {
			//显示异常信息
			System.out.println("更新用户时出错"+e.getMessage());
		} catch (Exception e) {
			System.out.println("更新用户时出错"+e.getMessage());
		}
		System.out.println();
	}
}
