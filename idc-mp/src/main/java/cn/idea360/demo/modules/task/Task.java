package cn.idea360.demo.modules.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class Task implements Callable {

    // 当前线程名
    private String threadName;

    // 线程暂停标识
    private volatile boolean suspend = false;

    // 线程运行标识
    private volatile boolean running = true;

    // 线程锁
    private final Object lock = new Object();

    // 当前线程
    private Thread currentThread;

    // 构造方法，传入线程名
    public Task(String threadName) {
        this.threadName = threadName;
        this.onCreate();
    }

    @Override
    public Objects call() {
        onStart();
        currentThread = Thread.currentThread();
        while (running && !Thread.currentThread().isInterrupted()) {
            if (suspend) {
                // 当前线程暂停
                System.out.println("thread:" + threadName + " suspend...");
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("thread:" + threadName + " running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 线程中断，当前线程结束
//                return;
                break;
            }
        }
        return null;
    }

    /**
     * 暂停当前线程
     */
    public void suspend() {
        onPause();
        this.suspend = true;
    }

    /**
     * 唤起当前线程
     */
    public void resume() {
        onResume();
        this.suspend = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    /**
     * 停止线程
     */
    public void stop() {
        onStop();
        currentThread.interrupt();
    }

    private void onCreate() {
        System.out.println("onCreate...");
    }

    /**
     * 线程启动回调
     */
    private void onStart() {
        System.out.println("onStart...");
    };
    /**
     * 线程暂停回调
     */
    private void onPause() {
        System.out.println("onPause...");
    };
    /**
     * 线程唤起回调
     */
    private void onResume() {
        System.out.println("onResume...");
    };
    /**
     * 线程终止回调
     */
    private void onStop() {
        System.out.println("onStop...");
    };


    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Task task = new Task("Task-1");
        Map<Integer, Task> tasks = new HashMap<Integer, Task>();
        tasks.put(1, task);

        // 提交线程任务
        Future<?> future = executorService.submit(task);

        // 暂停
        TimeUnit.SECONDS.sleep(2);
        Task task1 = tasks.get(1);
        task1.suspend();

        // 恢复
        TimeUnit.SECONDS.sleep(2);
        task1.resume();

        // 停止
        TimeUnit.SECONDS.sleep(2);
        task1.stop();

        // 销毁线程池
        executorService.shutdown();
    }

}
