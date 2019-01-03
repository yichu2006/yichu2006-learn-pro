package com.yichu.interrupt;

/**
 * 调用阻塞方法时，如何中断线程
 */
public class BlockInterrupt {
    private static Object o = new Object();

    /*while循环中包含try/catch块*/
    private static class WhileTryWhenBlock extends Thread {
        private volatile boolean on = true;
        private long i = 0;
        @Override
        public void run() {
            System.out.println("当前执行线程id："+Thread.currentThread().getId());
            while (on && !Thread.currentThread().isInterrupted()) {
                System.out.println("i="+i++);
                try {
                    //抛出中断异常的阻塞方法，抛出异常后，中断标志位会改成false
                    //可以理解为这些方法会隐含调用Thread.interrputed()方法
                    synchronized (o){
                        o.wait();
                    }

                } catch (InterruptedException e) {
                    System.out.println("当前执行线程的中断标志位："
                            +Thread.currentThread().getId()
                            +":"+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();//重新设置一下，因为此时为false，会无限while
                    System.out.println("被中断的线程_"+getId()
                            +":"+isInterrupted());
                    //do my work
                }
                //清理工作，准备结束线程
            }
        }

        public void cancel() {
            //on = false;
            interrupt();
            System.out.println("本方法所在线程实例："+getId());
            System.out.println("执行本方法的线程："+Thread.currentThread().getId());
            //这句注意，有可能设置的是当前线程，这里就是设置的main主线程的状态。
            //因为cancel方法调用，是在主线程中调用的。
            //Thread.currentThread().interrupt();
        }
    }

    /*try/catch块中包含while循环*/
    private static class TryWhileWhenBlock  extends Thread {
        private volatile boolean on = true;
        private long i =0;

        @Override
        public void run() {
            try {
                while (on) {
                    System.out.println(i++);
                        //抛出中断异常的阻塞方法，抛出异常后，中断标志位改成false
                        synchronized (o) {
                            o.wait();
                        }
                }
            } catch (InterruptedException e) {
                System.out.println("当前执行线程的中断标志位："
                        +Thread.currentThread().getId()
                        +":"+Thread.currentThread().isInterrupted());
            }finally {
                //清理工作结束线程
            }
        }
        public void cancel() {
            on = false;
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
       /* WhileTryWhenBlock whileTryWhenBlock = new WhileTryWhenBlock();
        whileTryWhenBlock.start();
        Thread.sleep(100);
        whileTryWhenBlock.cancel();

        System.out.println("==========================================");*/

        TryWhileWhenBlock tryWhileWhenBlock = new TryWhileWhenBlock();
        tryWhileWhenBlock.start();
        Thread.sleep(100);
        tryWhileWhenBlock.cancel();
    }
}