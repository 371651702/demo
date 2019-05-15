package com.xinhui.xinhui.concurrentInterface;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author dongliang.wang
 * @createTime 2019/5/15
 **/
class CountDownLatchUtil {


    private CountDownLatch start;
    private CountDownLatch end;
    private int pollSize;


    CountDownLatchUtil(int pollSize) {
        this.pollSize = pollSize;
        this.start = new CountDownLatch(1);
        this.end = new CountDownLatch(pollSize);
    }

    void latch(MyFunctionalInterface functionalInterface) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("high-concurrence-thread-name").build();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(pollSize,pollSize,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),threadFactory,new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < pollSize; i++) {
            executorService.submit(() -> {
                try {
                    start.await();
                    functionalInterface.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            });
        }
        start.countDown();
        end.await();
        executorService.shutdown();
    }

}
