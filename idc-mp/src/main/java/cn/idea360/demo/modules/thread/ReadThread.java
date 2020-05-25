package cn.idea360.demo.modules.thread;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-05-25
 */
public class ReadThread implements Runnable{
    public Thread t;
    private String threadName;
    volatile boolean suspended=false;

    public ReadThread(String threadName){
        this.threadName=threadName;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        for(int i = 50; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            try {
                Thread.sleep(300);
                synchronized(this) {
                    while(suspended) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Thread " +  threadName + " interrupted.");
                e.printStackTrace();
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }
    }

    /**
     * 开始
     */
    public void start(){
        System.out.println("Starting " +  threadName );
        if(t==null){
            t=new Thread(this, threadName);
            t.start();
        }
    }

    /**
     * 暂停
     */
    void suspend(){
        suspended = true;
        System.out.println("suspend " +  threadName );
    }

    /**
     * 继续
     */
    synchronized void resume(){
        suspended = false;
        notify();
        System.out.println("resume " +  threadName );
    }


    public static void main(String[] args) throws Exception{
        ReadThread readThread = new ReadThread("aaa");
        new Thread(readThread).start();

        System.out.println("=======");
        Thread.sleep(2000);
        readThread.start();
        Thread.sleep(2000);
        readThread.suspend();
        Thread.sleep(2000);
        readThread.resume();
    }
}
