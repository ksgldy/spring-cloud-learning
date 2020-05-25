package cn.idea360.demo.modules.thread;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-05-25
 */

public class MyThread2 extends Thread {
    private final Object lock = new Object();
    private volatile boolean pause = false;

    /**
     * 调用这个方法实现暂停线程
     */
    void pauseThread() {
        pause = true;
    }

    /**
     * 调用这个方法实现恢复线程的运行
     */
    void resumeThread() {
        pause = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    /**
     * 注意：这个方法只能在run方法里调用，不然会阻塞主线程，导致页面无响应
     */
    void onPause() {
        System.out.println("onPause");
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            int index = 0;
            while (true) {
                // 让线程处于暂停等待状态
                while (pause) {
                    onPause();
                }
                try {
                    System.out.println(index);
                    Thread.sleep(500);
                    ++index;
                } catch (InterruptedException e) {
                    //捕获到异常之后，执行break跳出循环
                    break;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{

        MyThread2 my = new MyThread2();
        Thread thread = new Thread(my);
        thread.start();
        Thread.sleep(2000);
        my.pauseThread();
        Thread.sleep(2000);
        my.resumeThread();

    }
}
