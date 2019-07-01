package com.lingnan.usersys.view;

import com.lingnan.usersys.domain.UserVO;

public interface BaseFrame {

	/**
	 * 页面显示
	 */
	public void show();
	
	/**
	 * 注册显示
	 * @param type type
	 */
	public void registerShow(String type);
	
	/**
	 * 更新显示
	 * @param type type
	 * @param user user
	 */
	public void updateShow(String type,UserVO user);
	
	/**
	 * 查询显示
	 */
	public void findShow();
}
