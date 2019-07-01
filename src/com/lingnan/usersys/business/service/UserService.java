package com.lingnan.usersys.business.service;

import java.util.Vector;

import com.lingnan.common.dao.BaseDao;
import com.lingnan.usersys.domain.UserVO;

public interface UserService extends BaseDao{
	
	/**
	 * 用户登录
	 * @param ID 用户账号
	 * @param Password 用户密码
	 * @return 用户信息
	 */
	public UserVO loginUser(int ID,String Password);

	/**
	 * 用户注册/添加用户
	 * @param user 用户信息
	 * @return 注册成功返回true，失败返回false
	 */
	public boolean registerUser(UserVO user);
	
	/**
	 * 查找全部用户信息
	 * @return 全部用户信息
	 */
	public Vector<UserVO> findAllUser();
	
	/**
	 * 按ID查找用户
	 * @param ID 输入用户ID
	 * @return 该ID的用户信息
	 */
	public Vector<UserVO> findUserByID(int ID);
	
	/**
	 * 按姓名查找用户
	 * @param Name 输入用户姓名
	 * @return 该姓名的用户信息
	 */
	public Vector<UserVO> findUserByName(String Name);
	
	/**
	 * 查找年龄最大的用户信息
	 * @return 年龄最大的用户信息
	 */
	public Vector<UserVO> findMaxAge();
	
	/**
	 * 计算记录数量
	 * @return 数值
	 */
	public int getCountUser();
	
	/**
	 * 查找指定页的用户信息
	 * @param pageNo 第x页
	 * @param pageSize 共x条
	 * @return 返回该页的用户信息
	 */
	public Vector<UserVO> findUsers(int pageNo,int pageSize);
	
	/**
	 * 按ID删除用户信息
	 * @param ID 输入要删除的用户ID
	 * @return 删除成功返回true，失败返回false
	 */
	public boolean deleteUserByID(int ID);
	
	/**
	 * 修改用户信息
	 * @param user 输入修改的信息
	 * @return 修改后的信息
	 */
	public boolean updateUser(UserVO user);
}
