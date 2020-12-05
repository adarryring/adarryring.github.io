package io.github.darryring.learn.concurrent.atomicity;

import io.github.darryring.util.thread.ThreadUtil;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author darryring
 **/
class Atomicity2Test {

    private final int threadNum = 10;
    private CountDownLatch countDownLatch = new CountDownLatch(threadNum);
    private int i = 0;
    private AtomicInteger j = new AtomicInteger(0);

    public class Thread1 implements Runnable {
        @Override
        public void run() {
            ThreadUtil.waitLittleTime();
            i++;
            countDownLatch.countDown();
        }
    }

    public class Thread2 implements Runnable {
        @Override
        public void run() {
            ThreadUtil.waitLittleTime();
            j.getAndAdd(1);
            countDownLatch.countDown();
        }
    }

    /**
     * https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven-engines-configure 重复执行
     *
     * @throws InterruptedException e
     */
    @RepeatedTest(3)
    void t1() throws InterruptedException {
        List<Thread> list = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; i++) list.add(new Thread(new Thread1()));
        for (int i = 0; i < threadNum; i++) list.get(i).start();
        countDownLatch.await();
        System.out.println(i);
    }


    @RepeatedTest(3)
    void t2() throws InterruptedException {
        List<Thread> list = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; i++) list.add(new Thread(new Thread2()));
        for (int i = 0; i < threadNum; i++) list.get(i).start();
        countDownLatch.await();
        System.out.println(j);
    }
}
