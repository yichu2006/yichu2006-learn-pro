package com.yichu.api.service;

/**
 * 调度相关操作
 */
public interface DispatchService {

	/**
	 * 添加调度信息
	 */
	void dispatch(String orderId) throws Exception;

}
