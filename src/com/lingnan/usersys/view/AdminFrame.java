package com.lingnan.usersys.view;

import java.io.BufferedReader;
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
 * 这个是管理员
 * @author krisW
 *
 */
public class AdminFrame extends NormalFrame{

	Scanner input = new Scanner(System.in);
	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param user user
	 */
	public AdminFrame(UserVO user) {
		super(user);
	}

	/**
	 * 用户登录成功后的界面
	 */
	public void loginSucShow() {
	
		//选择登录、注册或退出界面
		System.out.println("--------------------------------------");
		System.out.println(uv.getName()+"你好！");
		System.out.println("你的权限是："+uv.getSuperuser());
		System.out.println();
		if(uv.getSuperuser().equals("管理员")){
			this.show();
		}
		else {
			new NormalFrame(uv).show();
		}
	}
		
	/**
	 * 管理员登录后的界面
	 */
	public void show(){
		//循环操作
		while(true){
			//显示管理员登录成功后的操作界面
			System.out.println("===================主界面！==================");
			System.out.println("1、查询");
			System.out.println("2、添加");
			System.out.println("3、删除");
			System.out.println("4、更新");
			System.out.println("0、返回上一界面");
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
					System.out.println("输入有误，请重新输入！（只能输入0~4的数字）");
				} 
			}
			
			/**
			 * 判断输入值
			 * 值为1，则进行查询操作
			 * 值为2，则进行添加操作
			 * 值为3，则进行删除操作
			 * 值为4，则进行更新操作
			 * 值为0，则退出程序
			 */
			switch(i){
			case 1:
				this.findshow();
				break;
			case 2:
				this.registerShow("添加");
				break;//中断switch
			case 3:
				this.deleteShow();
				break;
			case 4:
				this.updateShow();
				break;
			case 0:
				new IndexFrame().show();//返回登录注册界面
				
			default://输入0/1/2/3/4以外的数字
				System.out.println("输入有误，请重新输入！");
			}
		}
}

	/**
	 * 管理员进行查询操作
	 */
	private void findshow(){
		//循环操作
		while(true){
			System.out.println();
			System.out.println("===================查询界面！==================");
			System.out.println("1、查询全部用户信息");
			System.out.println("2、按ID查询用户信息");
			System.out.println("3、按姓名查询用户信息");
			System.out.println("4、查询年龄最大的用户信息");
			System.out.println("5、查询用户信息记录数");
			System.out.println("6、查询指定页的用户信息");
			System.out.println("0、返回上一界面");
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
					System.out.println("输入有误，请重新输入！（只能输入0~3的数字）");
				} 
			}
			
			/**
			 * 判断输入值
			 * 值为1，则进行查询全部用户信息操作
			 * 值为2，则进行按ID查询用户信息操作
			 * 值为3，则进行按姓名查询用户信息操作
			 * 值为4，则进行查询年龄最大的用户信息操作
			 * 值为5，则进行查询用户信息记录数操作
			 * 值为6，则进行查询指定页的用户信息操作
			 * 值为0，则退出程序
			 */
			switch(i){
			case 1:
				this.findAllShow();
				break;
			case 2:
				this.findIDShow();
				break;//中断switch
			case 3:
				this.findNameShow();
				break;
			case 4:
				this.findAgeShow();
				break;
			case 5:
				this.getCountShow();
				break;
			case 6:
				this.findUsersShow();
				break;
			case 0:
				AdminFrame.this.show();//返回上一界面
				
			default://输入0/1/2/3以外的数字
				System.out.println("输入有误，请重新输入！");
			}
		}	
	}

	private void findAllShow() {
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		Vector<UserVO> uv = new Vector<UserVO>();
		uv = ua.findAllUser();
		System.out.println("--------------------------------------------------");
		System.out.println("所有用户信息如下：");
		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
		for(UserVO s:uv)
			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());	
	}
	
	private void findIDShow() {
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		Vector<UserVO> uv = new Vector<UserVO>();
		System.out.println("--------------------------------------");
		System.out.println("请输入要查询的ID：");
		int ID = input.nextInt();
		uv = ua.findUserByID(ID);
		System.out.println("--------------------------------------------------");
		System.out.println("该用户信息如下：");
		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
		for(UserVO s:uv)
			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());		
	}
	
	private void findNameShow() {
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		Vector<UserVO> uv = new Vector<UserVO>();
		System.out.println("--------------------------------------");
		System.out.println("请输入要查询的姓名：");
		String Name = input.next();
		uv = ua.findUserByName(Name);
		System.out.println("--------------------------------------------------");
		System.out.println("该用户信息如下：");
		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
		for(UserVO s:uv)
			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());		
	}
	
	private void findAgeShow() {
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		Vector<UserVO> uv = new Vector<UserVO>();
		uv = ua.findMaxAge();
		System.out.println("--------------------------------------------------");
		System.out.println("该用户信息如下：");
		System.out.println("账号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"年龄"+"\t"+"生日"+"\t\t"+"邮箱"+"\t\t"+"密码"+"\t"+"权限");	
		for(UserVO s:uv)
			System.out.println(s.getID()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t"+s.getBirth()+"\t"+s.getMail()+"\t"+s.getPassword()+"\t"+s.getSuperuser());		
	}
	
	private void getCountShow() {
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		UserVO uv = null;
		System.out.println("--------------------------------------------------");
		ua.getCountUser();
	}
	

	private void findUsersShow() {
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		System.out.println("--------------------------------------------------");
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

	/**
	 * 管理员进行添加操作
	 */
	public void registerShow(String type) {
		System.out.println();
		if("添加".equals(type)){
			System.out.println("===================添加界面！==================");
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
			//调用用户控制器中的registerUser方法，进行添加用户操作
			UserController uc = new UserController();
			boolean insert = uc.registerUser(uv);
			//如果返回值为true则添加成功，显示成功信息；否则添加失败，显示失败信息
			if(insert){
				if("添加".equals(type))
					System.out.println("添加用户成功！");
				else
					System.out.println("用户注册成功！");
			}else{
				if("添加".equals(type))
					System.out.println("添加用户失败！");
				else
					System.out.println("用户注册失败！");
			}
		} catch (DateException e) {
			//显示异常信息
			if("添加".equals(type))
				System.out.println("添加用户时出错"+e.getMessage());
			else
				System.out.println("用户注册时出错"+e.getMessage());
		} catch (Exception e) {
			if("添加".equals(type))
				System.out.println("添加用户时出错"+e.getMessage());
			else
				System.out.println("用户注册时出错"+e.getMessage());
		}
		System.out.println();
	}
	
	/**
	 * 管理员进行删除操作
	 */
	private void deleteShow() {
		System.out.println();
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		boolean flag = false;
		System.out.println("===================删除界面！==================");
		System.out.println("请输入要删除的ID：");
		int ID = input.nextInt();
		flag = ua.deleteUserByID(ID);
		if(flag)
			System.out.println("删除成功！");
		else
			System.out.println("删除失败！");
		System.out.println();
	}
	
	/**
	 * 管理员进行更新操作
	 */
	public void updateShow() {
		System.out.println();
		System.out.println("===================更新界面！==================");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		UserDAOImpl ua = new UserDAOImpl(DBUtils.getConnection());
		UserVO uv = new UserVO();
		boolean flag = false;
		System.out.println("请输入用户ID：");
		try {
			uv.setID(Integer.parseInt(bfr.readLine()));
			System.out.println("请输入要更新的用户姓名：");
			uv.setName(bfr.readLine());
			System.out.println("请输入要更新的用户性别：");
			uv.setSex(bfr.readLine());
			System.out.println("请输入要更新的用户年龄：");
			uv.setAge(Integer.parseInt(bfr.readLine()));
			System.out.println("请输入要更新的用户生日（YYYY-MM-DD）：");
			uv.setBirth(TypeUtils.StrToDate(bfr.readLine()));
			while(true){
				System.out.println("请输入要更新的用户邮箱：");
				String Mail = bfr.readLine();
				if(TypeUtils.CheckMail(Mail)){
					uv.setMail(Mail);
					break;
				}
			}
			System.out.println("请输入要更新的用户密码：");
			uv.setPassword(bfr.readLine());
			System.out.println("请输入要更新的用户权限：");
			uv.setSuperuser(bfr.readLine());
			//调用用户控制器中的updateUser方法，进行更新用户信息操作
			UserController uc = new UserController();
			boolean update = uc.updateUser(uv);
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
