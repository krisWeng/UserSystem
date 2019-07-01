package com.lingnan.common.exception;

/**
 * service异常类
 * @author krisW
 *
 */
public class ServiceException extends RuntimeException{
	
	/**
	 * 默认的构造方法
	 */
	public ServiceException(){
		
	}
	
	/**
	 * 构造方法1
	 * @param arg0 异常的详细信息
	 */
	public ServiceException(String arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法2
	 * @param arg1 产生异常的原因
	 */
	public ServiceException(Throwable arg1){
		super(arg1);
	}
	
	/**
	 * 构造方法3
	 * @param arg0 异常的详细信息
	 * @param arg1 产生异常的原因
	 */
	public ServiceException(String arg0,Throwable arg1){
		super(arg0, arg1);
	}
}
