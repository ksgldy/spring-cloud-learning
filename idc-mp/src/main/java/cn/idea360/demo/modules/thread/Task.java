package cn.idea360.demo.modules.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-05-25
 */
public class Task implements Runnable {

    private String name;
    private Thread runThread;

    public Task(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();

        System.out.println("Starting thread " + name);
        while (true) {
            try {
                Thread.sleep(4000);
                System.out.println("Hello from thread " + name);
            } catch (InterruptedException e) {
                // We've been interrupted: no more messages.
                return;
            }
        }
    }

    public void stop() {
        runThread.interrupt();
    }

    public String getName() {
        return name;
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Task t1 = new Task("Task1");
        Task t2 = new Task("Task2");
        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(new Task("Task3"));
        executorService.execute(new Task("Task4"));

        try {
            Thread.sleep(12000);
            t1.stop();
            System.err.println("Stopped thread " + t1.getName());
            Thread.sleep(8000);
            t2.stop();
            System.err.println("Stopped thread " + t2.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
