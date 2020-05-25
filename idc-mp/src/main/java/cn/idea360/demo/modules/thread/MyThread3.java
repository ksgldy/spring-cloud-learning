package cn.idea360.demo.modules.thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 当我遇上你
 * @公众号 当我遇上你
 * @since 2020-05-25
 */
public class MyThread3 implements Runnable{
    // using `AtomicBoolean` which wraps a `volatile boolean` but is const object
// NOTE: we _can't_ synchronized on Boolean, needs to be constant object reference
    private final AtomicBoolean pauseFlag = new AtomicBoolean(false);



    public void pause() {
        pauseFlag.set(true);
    }
    public void resume() {
        pauseFlag.set(false);
        synchronized (pauseFlag) {
            pauseFlag.notify();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (pauseFlag.get()) {
                synchronized (pauseFlag) {
                    // we are in a while loop here to protect against spurious interrupts
                    while (pauseFlag.get()) {
                        try {
                            pauseFlag.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            // we should probably quit if we are interrupted?
                            return;
                        }
                    }
                }
            }
        }
    }
}
