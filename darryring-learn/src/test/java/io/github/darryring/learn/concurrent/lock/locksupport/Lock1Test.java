package io.github.darryring.learn.concurrent.lock.locksupport;

import io.github.darryring.util.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author darryring
 * 1. 线程的状态：new、Runnable、waiting、time waiting、blocked、terminated
 **/
class Lock1Test {

    /**
     * 1. Condition 的阻塞原理就是调用了 LockSupport
     * 2. 等待通知机制：Object（用于 synchronized） 的 `wait/notify/notifyAll` 对应于 Condition（用于 AQS） 的`await/signal/signalAll`
     * 3. `Thread.sleep` 不释放资料，且在指定时间内自动唤醒
     * 4. AQS 和 Condition 用的是相同结构的队列来存在阻塞的线程
     */
    @Test
    void t1() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i'm park");
                LockSupport.park();
                System.out.println("i'm alive");
            }
        });
        t.start();

//        LockSupport.unpark(t);

        // main operation
        ThreadUtil.waitThreeSeconds();
        System.out.println("main");

        LockSupport.unpark(t);
    }
}
