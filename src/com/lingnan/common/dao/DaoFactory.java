package com.lingnan.common.dao;

import java.sql.Connection;

import com.lingnan.common.exception.ServiceException;
import com.lingnan.usersys.business.dao.UserDAOImpl;

/**
 * 获得DAO对象的工厂方法
 * @author krisW
 *
 */
public class DaoFactory {

	public static BaseDao getDao(Connection conn,String type){
		//如果传入dao类型是用户user，就实例化用户
		if("user".equals(type))
			//返回实例化的DAO对象
			return new UserDAOImpl(conn);
		
		else {
			throw new ServiceException("DAO工厂方法出错！");
		}
		
	}
}
