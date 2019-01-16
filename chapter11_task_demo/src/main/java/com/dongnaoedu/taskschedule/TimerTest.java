package com.dongnaoedu.taskschedule;

import java.util.Timer;

/**
 * @Auther: allen
 * @Date: 2018/10/30 14:33
 */
public class TimerTest {
    Timer timer;
    public TimerTest(){
        timer = new Timer();
        timer.schedule(new TimerTaskTest(), 1000, 2000);
    }

    public static void main(String[] args) {
        new TimerTest();
    }
}
