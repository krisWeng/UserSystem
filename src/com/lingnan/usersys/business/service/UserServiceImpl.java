package com.lingnan.usersys.business.service;

import java.sql.Connection;
import java.util.Vector;

import com.lingnan.common.constant.EnumType;
import com.lingnan.common.dao.DaoFactory;
import com.lingnan.common.exception.DaoException;
import com.lingnan.common.exception.ServiceException;
import com.lingnan.common.util.DBUtils;
import com.lingnan.usersys.business.dao.UserDAO;
import com.lingnan.usersys.domain.UserVO;

/**
 * 用户service接口的实现类
 * @author krisW
 *
 */
public class UserServiceImpl implements UserService{

	/**
	 * 用户service类实例，在类的内部创建唯一实例
	 */
	private static UserService UserService = new UserServiceImpl();
	
	/**
	 * 构造方法私有化
	 */
	private UserServiceImpl(){
		
	}
	
	/**
	 * 取得用户service实例
	 * 提供对外访问的方法
	 * @return 实例对象
	 */
	public static UserService getInstance(){
		return UserService;
	}

	/**
	 * 用户登录
	 * @param ID 账号
	 * @param Password 密码
	 * @return 用户信息
	 */
	public UserVO loginUser(int ID, String Password) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		UserVO uv = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用dao中的loginUser方法，进行登录操作，把结果赋值给登录结果变量
			uv = ua.loginUser(ID, Password);
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("用户登录出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回用户登录结果
		return uv;
	}
	
	/**
	 * 用户注册/添加用户
	 * @param user 用户信息
	 * @return 注册成功返回true，失败返回false
	 */
	public boolean registerUser(UserVO user){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		boolean flag = false;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
//			//调用数据库工具类OpenTransaction方法，开启事务
//			DBUtils.OpenTransaction(conn);
			//调用dao中的registerUser方法，进行注册操作，把结果赋值给注册结果变量
			flag = ua.registerUser(user);
//			//调用数据库工具类Commit方法，提交事务
//			DBUtils.Commit(conn);
			//操作过程出现异常，调用数据库工具类Rollback方法，回滚事务
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			DBUtils.Rollback(conn);
			//将异常封装成自定义异常并抛出
			throw new ServiceException("用户注册出错",e);
		} finally {
//			DBUtils.CloseConnection(conn);
		}
		//返回用户注册结果
		return flag;
	}
	
	/**
	 * 查找全部用户信息
	 * @return 全部用户信息
	 */
	public Vector<UserVO> findAllUser(){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> uv = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用dao中的findAllUser方法，进行查询全部用户信息操作，把结果赋值给查询结果变量
			uv = ua.findAllUser();
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查询用户出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回查询用户结果
		return uv;
	}
	
	/**
	 * 按ID查找用户
	 * @param ID 输入用户ID
	 * @return 该ID的用户信息
	 */
	public Vector<UserVO> findUserByID(int ID){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> uv = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用dao中的findUserByID方法，进行通过ID查询用户信息操作，把结果赋值给查询结果变量
			uv = ua.findUserByID(ID);
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查询用户出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回查询用户结果
		return uv;
	}
	
	/**
	 * 按姓名查找用户
	 * @param Name 输入用户姓名
	 * @return 该姓名的用户信息
	 */
	public Vector<UserVO> findUserByName(String Name){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> uv = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用dao中的findUserByName方法，进行通过姓名查询用户信息操作，把结果赋值给查询结果变量
			uv = ua.findUserByName(Name);
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查询用户出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回查询用户结果
		return uv;
	}
	
	/**
	 * 查找年龄最大的用户信息
	 * @return 年龄最大的用户信息
	 */
	public Vector<UserVO> findMaxAge(){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> uv = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用dao中的findMaxAge方法，进行查询年龄最大的用户信息操作，把结果赋值给查询结果变量
			uv = ua.findMaxAge();
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查询用户出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回查询用户结果
		return uv;
	}
	
	/**
	 * 计算记录数量
	 * @return 数值
	 */
	public int getCountUser(){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用dao中的getCountUser方法，进行查询信息记录数操作，把结果赋值给查询结果变量
			ua.getCountUser();
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查询记录出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回查询用户结果
		return 0;
	}
	
	/**
	 * 查找指定页的用户信息
	 * @param pageNo 第x页
	 * @param pageSize 共x条
	 * @return 返回该页的用户信息
	 */
	public Vector<UserVO> findUsers(int pageNo,int pageSize){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> uv = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用dao中的findUsers方法，进行查询指定页的用户信息操作，把结果赋值给查询结果变量
			uv = ua.findUsers(pageNo, pageSize);
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("查询用户出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回查询用户结果
		return uv;
	}
	
	/**
	 * 按ID删除用户信息
	 * @param ID 输入要删除的用户ID
	 * @return 删除成功返回true，失败返回false
	 */
	public boolean deleteUserByID(int ID){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		boolean flag = false;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用数据库工具类OpenTransaction方法，开启事务
			DBUtils.OpenTransaction(conn);
			//调用dao中的deleteUserByID方法，进行通过ID删除信息操作，把结果赋值给删除结果变量
			flag = ua.deleteUserByID(ID);
			//调用数据库工具类Commit方法，提交事务
			DBUtils.Commit(conn);
			//操作过程出现异常，调用数据库工具类Rollback方法，回滚事务
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			DBUtils.Rollback(conn);
			//将异常封装成自定义异常并抛出
			throw new ServiceException("删除用户出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回删除用户结果
		return flag;
	}
	
	/**
	 * 修改用户信息
	 * @param user 输入修改的信息
	 * @return 修改后的信息
	 */
	public boolean updateUser(UserVO user){
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		boolean flag = false;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并给数据库连接对象变量赋值
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并给dao接口变量赋值
			UserDAO ua = (UserDAO)DaoFactory.getDao(conn, EnumType.USER_DA0);
			//调用数据库工具类OpenTransaction方法，开启事务
			DBUtils.OpenTransaction(conn);
			//调用dao中的updateUser方法，进行通过ID更新用户信息操作，把结果赋值给更新结果变量
			flag = ua.updateUser(user);
			//调用数据库工具类Commit方法，提交事务
			DBUtils.Commit(conn);
			//操作过程出现异常，调用数据库工具类Rollback方法，回滚事务
		} catch(DaoException e){
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			DBUtils.Rollback(conn);
			//将异常封装成自定义异常并抛出
			throw new ServiceException("更新用户出错",e);
		} finally {
			DBUtils.CloseConnection(conn);
		}
		//返回更新用户结果
		return flag;
	}
}
