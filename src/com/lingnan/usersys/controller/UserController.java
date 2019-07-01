package com.lingnan.usersys.controller;

import java.sql.SQLException;
import java.util.Vector;

import com.lingnan.common.exception.DaoException;
import com.lingnan.usersys.business.service.UserService;
import com.lingnan.usersys.business.service.UserServiceImpl;
import com.lingnan.usersys.domain.UserVO;

public class UserController {
	//声明用户Service接口对象，用于业务处理
	UserService us = UserServiceImpl.getInstance();
	
	/**
	 * 用户登录
	 * @param ID 账号
	 * @param Password 密码
	 * @return 用户信息
	 */
	public UserVO loginUser(int ID,String Password){
		UserVO uv = null;
		try{
			//调用Service接口中的loginUser方法，用于用户登录
			uv = us.loginUser(ID, Password);
		}catch(Exception e){
			//显示异常
			System.out.println("用户登录出错："+e.getMessage());
		}
		return uv;
	}
	
	/**
	 * 用户注册
	 * @param user 用户信息
	 * @return 注册成功返回true，失败返回false
	 */
	public boolean registerUser(UserVO user){
		boolean flag = false;
		try {
			////调用Service接口中的registerUser方法，用于用户注册或添加用户
			flag = us.registerUser(user);
		} catch (Exception e) {
			//显示异常
			System.out.println("用户注册出错："+e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 查找全部用户信息
	 * @return 全部用户信息
	 */
	public Vector<UserVO> findAllUer(){
		Vector<UserVO> uv = null;
		try{
			//调用Service接口中的findAllUser方法，用于查询全部用户信息
			uv = us.findAllUser();
		}catch(Exception e){
			//显示异常
			System.out.println("查询全部用户出错："+e.getMessage());
		}
		return uv;
	}
	
	/**
	 * 按ID查找用户
	 * @param ID 输入用户ID
	 * @return 该ID的用户信息
	 */
	public Vector<UserVO> findUserByID(int ID){
		Vector<UserVO> uv = null;
		try{
			//调用Service接口中的findUserByID方法，用于通过ID查询用户信息
			uv = us.findUserByID(ID);
		}catch(Exception e){
			//显示异常
			System.out.println("查询用户出错："+e.getMessage());
		}
		return uv;
	}
	
	/**
	 * 按姓名查找用户
	 * @param Name 输入用户姓名
	 * @return 该姓名的用户信息
	 */
	public Vector<UserVO> findUserByName(String Name){
		Vector<UserVO> uv = null;
		try{
			//调用Service接口中的findUserByName方法，用于通过姓名查询用户信息
			uv = us.findUserByName(Name);
		}catch(Exception e){
			//显示异常
			System.out.println("查询用户出错："+e.getMessage());
		}
		return uv;
	}
	
	/**
	 * 查找年龄最大的用户信息
	 * @return 年龄最大的用户信息
	 */
	public Vector<UserVO> findMaxAge(){
		Vector<UserVO> uv = null;
		try{
			//调用Service接口中的findMaxAge方法，用于查询年龄最大的用户信息
			uv = us.findMaxAge();
		}catch(Exception e){
			//显示异常
			System.out.println("查询用户出错："+e.getMessage());
		}
		return uv;
	}
	
	/**
	 * 计算记录数量
	 * @return 数值
	 */
	public int getCountUser(){
		try{
			//调用Service接口中的getCountUser方法，用于查询用户信息记录数
			us.getCountUser();
		}catch(Exception e){
			//显示异常
			System.out.println("查询记录数出错："+e.getMessage());
		}
		return 0;
	}
	
	/**
	 * 查找指定页的用户信息
	 * @param pageNo 第x页
	 * @param pageSize 共x条
	 * @return 返回该页的用户信息
	 */
	public Vector<UserVO> findUsers(int pageNo,int pageSize){
		Vector<UserVO> uv = null;
		try{
			//调用Service接口中的findUsers方法，用于查询指定页的用户信息
			uv = us.findUsers(pageNo, pageSize);
		}catch(Exception e){
			//显示异常
			System.out.println("查询用户出错："+e.getMessage());
		}
		return uv;
	}
	
	/**
	 * 按ID删除用户信息
	 * @param ID 输入要删除的用户ID
	 * @return 删除成功返回true，失败返回false
	 */
	public boolean deleteUserByID(int ID){
		boolean flag = false;
		try {
			////调用Service接口中的deleteUserByID方法，用于删除用户信息
			flag = us.deleteUserByID(ID);
		} catch (Exception e) {
			//显示异常
			System.out.println("删除用户信息出错："+e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 修改用户信息
	 * @param user 输入修改的信息
	 * @return 修改后的信息
	 */
	public boolean updateUser(UserVO user){
		boolean flag = false;
		try {
			////调用Service接口中的updateUser方法，用于更新用户信息
			flag = us.updateUser(user);
		} catch (Exception e) {
			//显示异常
			System.out.println("更新用户信息出错："+e.getMessage());
		}
		return flag;
	}
}
