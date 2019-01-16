package com.dongnaoedu.taskschedule;

import java.util.Date;
import java.util.TimerTask;

/**
 * @Auther: allen
 * @Date: 2018/10/30 14:35
 */
public class TimerTaskTest extends TimerTask {
    @Override
    public void run() {
        Date date = new Date(this.scheduledExecutionTime());
        System.out.println("本次执行该线程的时间为：" + date);
    }
}
