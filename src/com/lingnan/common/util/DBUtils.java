package com.lingnan.common.util;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import com.lingnan.common.exception.DaoException;

/**
 * 数据的工具类
 * @author krisW
 *
 */

public class DBUtils {
	/**
	 * 获取数据库连接
	 * @return 连接对象
	 */
	public static Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //注册驱动程序
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","root");//连接数据库
		}catch(ClassNotFoundException e){
			//将异常封装成自定义异常
			throw new DaoException("数据库jar包加载失败！",e);
			//System.out.println("数据库jar包加载失败！");
			//e.printStackTrace();
		}catch(SQLException e) {
			//将异常封装成自定义异常
			throw new DaoException("获取数据库连接失败！");
//			System.out.println("获取数据库连接失败！");
//			e.printStackTrace();
		}
		//System.out.println("获取数据库连接成功！");
		//返回连接对象
		return conn;
	}
	
	/**
	 * 开启事务
	 * @param conn 连接对象
	 */
	public static void OpenTransaction(Connection conn) {
		try{
			//将事务自动提交设为假
			conn.setAutoCommit(false);
		}catch(SQLException e){
			//将异常封装成自定义异常
			throw new DaoException("开启事务失败！",e);
			//System.out.println("开启事务失败！");
			//e.printStackTrace();
		}
	}
	
	/**
	 * 提交事务
	 * @param conn 连接对象
	 */
	public static void Commit(Connection conn){
		try{
			//将事务自动提交设为真
			conn.setAutoCommit(true);
		}catch(SQLException e){
			//将异常封装成自定义异常
			throw new DaoException("提交事务失败！",e);
			//System.out.println("提交事务失败！");
			//e.printStackTrace();
		}
	}
	
	/**
	 * 回滚事务
	 * @param conn 连接对象
	 */
	public static void Rollback(Connection conn){
		try{
			//回滚事务
			conn.rollback();
			//将事务自动提交设为真
			conn.setAutoCommit(true);
		}catch(SQLException e){
			//将异常封装成自定义异常
			throw new DaoException("关闭事务失败！",e);
			//System.out.println("关闭事务失败！");
			//e.printStackTrace();
		}
	}
	
	/**
	 * 关闭对象
	 * @param rs 结果集对象
	 * @param statm 声明对象
	 */
	public static void CloseStatement(ResultSet rs, Statement statm){
		try{
			//如果结果集对象不为空，关闭该对象
			if(rs!=null)
				rs.close();
			//如果声明对象不为空，关闭该对象
			if(statm!=null)
				statm.close();
		}catch(SQLException e){
			//将异常封装成自定义异常
			throw new DaoException("关闭对象失败！",e);
			//System.out.println("关闭对象失败！");
			//e.printStackTrace();
		}
	}
	
	/**
	 * * 关闭连接
	 * @param conn 数据库对象
	 */
	public static void CloseConnection(Connection conn){
		try{
			//如果数据库对象不为空，则关闭该对象
			if(conn!=null)
				conn.close();
		}catch(SQLException e){
			//将异常封装成自定义异常
			throw new DaoException("关闭数据库连接失败！",e);
			//System.out.println("关闭数据库连接失败！");
			//e.printStackTrace();
		}
	}
}
