package cn.idea360.demo.modules.thread;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-05-25
 */
public class MyThread extends Thread {

    private volatile boolean stopFlag = false;

    @Override
    public void run() {
        while (!stopFlag) {
            //doSomething
            try {
                System.out.println(Thread.currentThread().getName() + "is running...");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }

        }


    }
    public boolean isStopFlag() {
        return stopFlag;
    }
    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    public static void main(String[] args) throws Exception{
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        Thread.sleep(1000);
//        myThread.setStopFlag(true);
        thread.interrupt();

    }
}
