package io.github.darryring.learn.concurrent.atomicity;

import io.github.darryring.util.thread.ThreadUtil;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author darryring
 **/
class Atomicity3Test {

    private int i = 0;
    private AtomicInteger j = new AtomicInteger(0);

    @RepeatedTest(3)
    void t1() {
        ThreadUtil.run(() -> i++);
        System.out.println(i);
    }


    @RepeatedTest(3)
    void t2() {
        ThreadUtil.run(() -> j.getAndAdd(1));
        System.out.println(j);
    }
}
