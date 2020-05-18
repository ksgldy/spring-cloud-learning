package com.example.taskdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TaskDemoApplicationTests {

    @Test
    void contextLoads() throws Exception{
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.put(1);
    }

    @Test
    void thread() {
        // 有效期
        boolean valid = true;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        while (valid) {
            threadPoolExecutor.submit(new Task(null));
        }
    }

    class Task implements Callable {

        private Object data;

        private volatile boolean stopTag;

        public Task(Object o) {
            this.data = o;
        }

        @Override
        public Object call() throws Exception {
            // send to robot, 异步， 发送成功失败结果

            return null;
        }


    }

}
