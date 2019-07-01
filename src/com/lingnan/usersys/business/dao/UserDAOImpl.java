package com.lingnan.usersys.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.lingnan.common.exception.DaoException;
import com.lingnan.common.util.DBUtils;
import com.lingnan.usersys.domain.UserVO;

public class UserDAOImpl implements UserDAO{

	/**
	 * 数据库连接
	 */
	private Connection conn;
	
	/**
	 * 构造方法
	 * @param conn 数据库对象
	 */
	public UserDAOImpl(Connection conn){
		//给属性赋初始化值
		this.conn = conn;
	}

	/**
	 * 用户登录
	 * @param ID 登录账号
	 * @param Password 登录密码
	 * @return 登录成功返回true，失败返回false
	 */
	public UserVO loginUser(int ID, String Password) {
		//声明结果集对象，用于保存数据库的查询结果
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		//声明用户对象变量，用于保存从结果集中提取出来的数据
		UserVO uv = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("select * from W_USER where ID=? and Password=?");
			//调用预编译对象的setXX方法，给“？”赋值
			prep.setInt(1, ID);
			prep.setString(2, Password);
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，分装在用户对象的各个属性中
			while(rs.next()){//处理结果集
				//创建一个新用户对象，给用户对象变量赋值
				uv = new UserVO();
				//调用结果集对象的getXX方法，取出各个字段的值，然后再调用用户对象的setXX方法，给属性赋值，最后新创建的对象中包含了查询结果中的所有字段的值
				uv.setID(rs.getInt("ID"));
				uv.setName(rs.getString("Name"));
				uv.setSex(rs.getString("Sex"));
				uv.setAge(rs.getInt("Age"));
				uv.setBirth(rs.getDate("Birth"));
				uv.setMail(rs.getString("Mail"));
				uv.setPassword(rs.getString("Password"));
				uv.setSuperuser(rs.getString("Superuser"));
			}
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在登录时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return uv;
	}

	/**
	 * 用户注册/添加用户
	 * @param user 用户信息
	 * @return 注册成功返回true，失败返回false
	 */
	public boolean registerUser(UserVO user){
		boolean flag = false;
		//先判断参数是否为空，如果不为空，进行数据库插入操作
		if(user !=null){
			//声明预编译的声明对象变量，用于进行数据库操作的载体
			PreparedStatement prep = null;
			//声明变量，用于保存数据库更新结果
			int result = -1;
			try{
				//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
				prep = conn.prepareStatement("insert into W_USER values(?,?,?,?,?,?,?,?)");
				//调用预编译对象的setXX方法，给“？”赋值
				prep.setInt(1, user.getID());
				prep.setString(2, user.getName());
				prep.setString(3, user.getSex());
				prep.setInt(4, user.getAge());
				//将java.util.Date类型转换为java.sql.Date类型
				prep.setDate(5, new java.sql.Date(user.getBirth().getTime()));
				prep.setString(6, user.getMail());
				prep.setString(7, user.getPassword());
				prep.setString(8, user.getSuperuser());
				//调用预编译对象的excuteUpdate方法，执行添加操作。返回添加结果，赋值给变量
				result = prep.executeUpdate();
				//判断添加结果变量，如果结果>0则表示添加成功，返回true，否则添加失败，返回false
				if(result>0)
					return true;
				else
					return false;
				//如果出现异常，输出异常信息且返回false
			} catch (SQLException e) {
				System.out.println("在注册时出错："+ e.getMessage());
				//将异常封装成自定义异常
				throw new DaoException("在注册时出错！",e);
			} finally{
				try {	
					if(prep!=null){
						prep.close();
						prep = null;
					}
					if(conn!=null){
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					System.out.println("关闭连接、语句及结果集时出现错误"+e.getMessage());
				}
			}
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return flag;
	}
	
	/**
	 * 查找全部用户信息
	 * @return 全部用户信息
	 */
	public Vector<UserVO> findAllUser(){
		Vector<UserVO> v = new Vector<UserVO>();
		//声明结果集对象，用于保存数据库的查询结果
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("select * from W_USER");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，分装在用户对象的各个属性中
			while(rs.next()){//处理结果集
				//创建一个新用户对象，给用户对象变量赋值
				UserVO uv = new UserVO();
				//调用结果集对象的getXX方法，取出各个字段的值，然后再调用用户对象的setXX方法，给属性赋值，最后新创建的对象中包含了查询结果中的所有字段的值
				uv.setID(rs.getInt("ID"));
				uv.setName(rs.getString("Name"));
				uv.setSex(rs.getString("Sex"));
				uv.setAge(rs.getInt("Age"));
				uv.setBirth(rs.getDate("Birth"));
				uv.setMail(rs.getString("Mail"));
				uv.setPassword(rs.getString("Password"));
				uv.setSuperuser(rs.getString("Superuser"));
				v.add(uv);
			}
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在查询时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return v;
	}
	
	/**
	 * 按ID查找用户
	 * @param ID 输入用户ID
	 * @return 该ID的用户信息
	 */
	public Vector<UserVO> findUserByID(int ID){
		Vector<UserVO> v = new Vector<UserVO>();
		//声明结果集对象，用于保存数据库的查询结果
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("select * from W_USER where ID = ?");
			//调用预编译对象的setXX方法，给“？”赋值
			prep.setInt(1, ID);
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，分装在用户对象的各个属性中
			while(rs.next()){//处理结果集
				//创建一个新用户对象，给用户对象变量赋值
				UserVO uv = new UserVO();
				//调用结果集对象的getXX方法，取出各个字段的值，然后再调用用户对象的setXX方法，给属性赋值，最后新创建的对象中包含了查询结果中的所有字段的值
				uv.setID(rs.getInt("ID"));
				uv.setName(rs.getString("Name"));
				uv.setSex(rs.getString("Sex"));
				uv.setAge(rs.getInt("Age"));
				uv.setBirth(rs.getDate("Birth"));
				uv.setMail(rs.getString("Mail"));
				uv.setPassword(rs.getString("Password"));
				uv.setSuperuser(rs.getString("Superuser"));
				v.add(uv);
			}
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在查询时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return v;
	}
	
	/**
	 * 按姓名查找用户
	 * @param Name 输入用户姓名
	 * @return 该姓名的用户信息
	 */
	public Vector<UserVO> findUserByName(String Name){
		Vector<UserVO> v = new Vector<UserVO>();
		//声明结果集对象，用于保存数据库的查询结果
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("select * from W_USER where Name = ?");
			//调用预编译对象的setXX方法，给“？”赋值
			prep.setString(1, Name);
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，分装在用户对象的各个属性中
			while(rs.next()){//处理结果集
				//创建一个新用户对象，给用户对象变量赋值
				UserVO uv = new UserVO();
				//调用结果集对象的getXX方法，取出各个字段的值，然后再调用用户对象的setXX方法，给属性赋值，最后新创建的对象中包含了查询结果中的所有字段的值
				uv.setID(rs.getInt("ID"));
				uv.setName(rs.getString("Name"));
				uv.setSex(rs.getString("Sex"));
				uv.setAge(rs.getInt("Age"));
				uv.setBirth(rs.getDate("Birth"));
				uv.setMail(rs.getString("Mail"));
				uv.setPassword(rs.getString("Password"));
				uv.setSuperuser(rs.getString("Superuser"));
				v.add(uv);
			}
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在查询时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return v;
	}
	
	/**
	 * 查找年龄最大的用户信息
	 * @return 年龄最大的用户信息
	 */
	public Vector<UserVO> findMaxAge(){
		Vector<UserVO> v = new Vector<UserVO>();
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("select * from W_USER where Age=(select MAX(Age) from W_USER)");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，分装在用户对象的各个属性中
			while(rs.next()){//处理结果集
				//创建一个新用户对象，给用户对象变量赋值
				UserVO uv = new UserVO();
				//调用结果集对象的getXX方法，取出各个字段的值，然后再调用用户对象的setXX方法，给属性赋值，最后新创建的对象中包含了查询结果中的所有字段的值
				uv.setID(rs.getInt("ID"));
				uv.setName(rs.getString("Name"));
				uv.setSex(rs.getString("Sex"));
				uv.setAge(rs.getInt("Age"));
				uv.setBirth(rs.getDate("Birth"));
				uv.setMail(rs.getString("Mail"));
				uv.setPassword(rs.getString("Password"));
				uv.setSuperuser(rs.getString("Superuser"));
				v.add(uv);
			}
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在查询时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return v;
	}
	
	/**
	 * 计算记录数量
	 * @return 数值
	 */
	public int getCountUser(){
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("select * from W_USER");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			int count=0;
			while(rs.next()){
				count++;
			}
			System.out.println("用户信息数量为："+count);
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在查询时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		return 0;
	}
	
	/**
	 * 查找指定页的用户信息
	 * @param pageNo 第x页
	 * @param pageSize 共x条
	 * @return 返回该页的用户信息
	 */
	public Vector<UserVO> findUsers(int pageNo,int pageSize) {
		Vector<UserVO> v = new Vector<UserVO>();
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("select b.* from (select ROWNUM RN,W_USER.* from W_USER where ROWNUM<='"+pageNo+"'*'"+pageSize+"') b where RN>('"+pageNo+"'-1)*'"+pageSize+"'");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，分装在用户对象的各个属性中
			while(rs.next()){//处理结果集
				//创建一个新用户对象，给用户对象变量赋值
				UserVO uv = new UserVO();
				//调用结果集对象的getXX方法，取出各个字段的值，然后再调用用户对象的setXX方法，给属性赋值，最后新创建的对象中包含了查询结果中的所有字段的值
				uv.setID(rs.getInt("ID"));
				uv.setName(rs.getString("Name"));
				uv.setSex(rs.getString("Sex"));
				uv.setAge(rs.getInt("Age"));
				uv.setBirth(rs.getDate("Birth"));
				uv.setMail(rs.getString("Mail"));
				uv.setPassword(rs.getString("Password"));
				uv.setSuperuser(rs.getString("Superuser"));
				v.add(uv);
			}
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在查询时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 * 如果查询结果为空，该对象为空值null
		 */
		return v;
	}
	
	/**
	 * 按ID删除用户信息
	 * @param ID 输入要删除的用户ID
	 * @return 删除成功返回true，失败返回false
	 */
	public boolean deleteUserByID(int ID){
		boolean flag = false;
		//声明结果集对象，用于保存数据库的查询结果
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try{
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("delete from W_USER where ID = '"+ID+"'");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = prep.executeQuery();
			flag = true;
			//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在查询时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 */	
		return flag;
	}
	
	/**
	 * 更新用户信息
	 * @param user 输入要更新的信息
	 * @return 更新后的信息
	 */
	public boolean updateUser(UserVO user){
		boolean flag = false;
		//声明结果集对象，用于保存数据库的查询结果
		ResultSet rs = null;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement prep = null;
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，给预编译对象变量赋值
			prep = conn.prepareStatement("update W_USER set Name=?,Sex=?,Age=?,Birth=?,Mail=?,Password=?,Superuser=? where ID=?");
			//调用预编译对象的setXX方法，给“？”赋值
			prep.setInt(8, user.getID());
			prep.setString(1, user.getName());
			prep.setString(2, user.getSex());
			prep.setInt(3, user.getAge());
			//将java.util.Date类型转换为java.sql.Date类型
			prep.setDate(4, new java.sql.Date(user.getBirth().getTime()));
			prep.setString(5, user.getMail());
			prep.setString(6, user.getPassword());
			prep.setString(7, user.getSuperuser());
			//调用预编译对象的executeUpdate方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			prep.executeUpdate();
			flag = true;
		//如果出现异常，输出异常信息
		} catch (SQLException e) {
			System.out.println("在更新时出错："+ e.getMessage());
			//将异常封装成自定义异常
			throw new DaoException("运行SQL语句时出现错误",e);
		} finally{
			//调用数据库工具类，关闭结果集和声明对象
			DBUtils.CloseStatement(rs, prep);
		}
		/**
		 * 最后，返回用户对象，如果查询结果不为空，该对象中封装了查询结果中的数据
		 */	
		return flag;
	}
}
