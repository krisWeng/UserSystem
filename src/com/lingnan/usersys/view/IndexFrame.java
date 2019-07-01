package com.lingnan.usersys.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lingnan.common.exception.DateException;
import com.lingnan.common.util.TypeUtils;
import com.lingnan.usersys.controller.UserController;
import com.lingnan.usersys.domain.UserVO;

public class IndexFrame implements BaseFrame{

	//声明缓冲处理流对象，用于接收控制台输入的数据
	BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * 主界面，选择登录、注册或者退出
	 */
	public void show(){
		//循环操作
		while(true){
			//选择登录、注册或退出界面
			System.out.println("********************************************");
			System.out.println("***                                      ***");
			System.out.println("***              欢迎进入用户系统！                                    ***");
			System.out.println("***                                      ***");
			System.out.println("********************************************");
			System.out.println("1、登录");
			System.out.println("2、注册");
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
			 * 值为1，则进行登录操作
			 * 值为2，则进行注册操作
			 * 值为0，则退出程序
			 */
			switch(i){
			case 1:
				this.loginShow();
				break;
			case 2:
				this.registerShow("注册");
				break;//中断switch
			case 0:
				System.out.println("已退出！");
				System.exit(0);//退出当前界面
				
			default://输入0/1/2以外的数字
				System.out.println("输入有误，请重新输入！");
			}
		}
	}

	private void loginShow() {
		System.out.println();
		System.out.println("===================登录页面！==================");
		System.out.println("请输入账号：");
		try {
			//以行为单位，读取输入的各个值，赋值给用户对象的各个属性
			int ID = Integer.parseInt(bfr.readLine());
			System.out.println("请输入密码：");
			String Password = bfr.readLine();
			//调用控制器中的loginuser方法，进行用户登录
			UserController uc = new UserController();
			//如果返回值不为空则登录成功，显示用户信息操作界面；否则登录失败，显示登录失败信息
			UserVO uv = uc.loginUser(ID, Password);
			if(uv!=null){
				System.out.println("登录成功！");
				System.out.println();
				//调用主界面
				AdminFrame am = new AdminFrame(uv);
				am.loginSucShow();
				//退出当前界面
				System.exit(0);
			}else{
				//登录失败，显示失败信息
				System.out.println("登录失败！");
				System.out.println();
			}
		} catch (Exception e) {
			//显示异常信息
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void registerShow(String type) {
		System.out.println();
		if("注册".equals(type)){
			System.out.println("===================注册页面！==================");
		}else{
			
		}
		System.out.println("请输入账号：");
		try {
			//实例化用户
			UserVO uv = new UserVO();
			//读取输入的各个值，赋值给用户对象的各个属性
			uv.setID(Integer.parseInt(bfr.readLine()));
			System.out.println("请输入姓名：");
			uv.setName(bfr.readLine());
			System.out.println("请输入性别：");
			uv.setSex(bfr.readLine());
			System.out.println("请输入年龄：");
			uv.setAge(Integer.parseInt(bfr.readLine()));
			System.out.println("请输入生日（YYYY-MM-DD）：");
			uv.setBirth(TypeUtils.StrToDate(bfr.readLine()));
			while(true){
				System.out.println("请输入邮箱：");
				String Mail = bfr.readLine();
				if(TypeUtils.CheckMail(Mail)){
					uv.setMail(Mail);
					break;
				}
			}
			System.out.println("请输入密码：");
			uv.setPassword(bfr.readLine());
			System.out.println("请输入权限：");
			uv.setSuperuser(bfr.readLine());
			//调用用户控制器中的registerUser方法，进行用户注册操作
			UserController uc = new UserController();
			boolean register = uc.registerUser(uv);
			//如果返回值为true则注册成功，显示成功信息；否则注册失败，显示失败信息
			if(register){
				if("注册".equals(type))
					System.out.println("用户注册成功！");
				else
					System.out.println("添加用户成功！");
			}else{
				if("注册".equals(type))
					System.out.println("用户注册失败！");
				else
					System.out.println("添加用户失败！");
			}
		} catch (DateException e) {
			//显示异常信息
			if("注册".equals(type))
				System.out.println("用户注册时出错"+e.getMessage());
			else
				System.out.println("添加用户时出错"+e.getMessage());
		} catch (Exception e) {
			if("注册".equals(type))
				System.out.println("用户注册时出错"+e.getMessage());
			else
				System.out.println("添加用户时出错"+e.getMessage());
		}
		System.out.println();
	}

	@Override
	public void updateShow(String type, UserVO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findShow() {
		// TODO Auto-generated method stub
		
	}

}
