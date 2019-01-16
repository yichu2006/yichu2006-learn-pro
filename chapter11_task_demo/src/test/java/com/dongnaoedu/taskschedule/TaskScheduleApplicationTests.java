package com.dongnaoedu.taskschedule;

import com.dongnaoedu.taskschedule.quartz.QuartzTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskScheduleApplicationTests {

	@Autowired
	private AsyncScheduledTasks task;

	@Autowired
	private QuartzTask quartzTask;

	@Test
	public void test() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}

	@Test
	public void testFuture() throws Exception {
		long start = System.currentTimeMillis();

		Future<String> task1 = task.doTaskOne();
		Future<String> task2 = task.doTaskTwo();
		Future<String> task3 = task.doTaskThree();

		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}

		long end = System.currentTimeMillis();

		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}

	@Test
	public void testQuartz() throws Exception {
		System.out.println("执行调度任务："+new Date());
		quartzTask.reptilian();
	}

}
