package com.yichu.dispatch.service;

import com.yichu.api.service.DispatchService;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 调度相关操作
 */
@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 添加调度信息
     */
    @Transactional
    @Compensable(confirmMethod = "confirmDispatch", cancelMethod = "cancelDispatch", transactionContextEditor = MethodTransactionContextEditor.class)
    public void dispatch(TransactionContext transactionContext, String orderId) {   //这里不能 throws 异常

        System.out.println("服务端代码执行");

        //往数据库插入一条调度记录
        String sql = "insert into table_dispatch (dispatch_seq, order_id,dispatch_content) values (UUID(), ?, ?)";
        int update = jdbcTemplate.update(sql, orderId, "派送此订单");

        //手动抛异常   try 阶段 不成功，tcc表不会有事务记录
        int ii = 1/0;

    }

    @Transactional
    public void confirmDispatch(TransactionContext transactionContext, String orderId) {

        System.out.println("调用系统 confirm");
    }

    @Transactional
    public void cancelDispatch(TransactionContext transactionContext, String orderId) {

        System.out.println("调用系统 cancle");

    }
}
