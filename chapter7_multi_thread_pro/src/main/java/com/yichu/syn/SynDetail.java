package com.yichu.syn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**

 * 创建日期：2017/11/28
 * 创建时间: 20:39
 */
public class SynDetail {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        synchronized (SynDetail.class){
            //do my work

        }

        if(lock.tryLock()){

        }else{
            lock.unlock();
        }


    }
}
