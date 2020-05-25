package cn.idea360.demo.modules.thread;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-05-25
 */
public class MyThread1 extends Thread {
    private String control = "";//只是任意的实例化一个对象而已

    private boolean suspend = false;//线程暂停标识

    @Override
    public void run() {
        while (true) {
            synchronized (control) {
                if (suspend) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "is running...");
                        control.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            //doSomething
        }


    }

    public void setSuspend(boolean suspend) {
        if (!suspend) {
            synchronized (control) {
                control.notifyAll();
            }
        }
        this.suspend = suspend;
    }


    public static void main(String[] args) throws Exception{
        MyThread1 myThread = new MyThread1();
        Thread thread = new Thread(myThread);
        thread.start();
        Thread.sleep(1000);
        myThread.setSuspend(true);

    }
}
