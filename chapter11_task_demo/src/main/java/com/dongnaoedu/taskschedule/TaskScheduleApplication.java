package com.dongnaoedu.taskschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling   //对应同步定时任务  只能开启一个
@EnableAsync        //开启异步定时任务
public class TaskScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskScheduleApplication.class, args);
	}
}
