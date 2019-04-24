package com.yichu.api.service;

import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 调度相关操作
 */
public interface DispatchService {

	/**
	 * 添加调度信息
	 */
	void dispatch(TransactionContext transactionContext, String orderId) throws Exception;

}
