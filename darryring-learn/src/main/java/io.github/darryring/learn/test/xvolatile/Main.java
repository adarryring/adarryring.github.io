package io.github.darryring.learn.test.xvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author darryring
 **/
public class Main {

    private static volatile AtomicInteger i = new AtomicInteger(0);

    public static void increase() {
        i.addAndGet(1);
    }

    public static void main(String[] args) {
        System.out.println(i.get());
        Thread[] threads = new Thread[20];
        for (int j = 0; j < threads.length; j++) {
            threads[j] = new Thread(Main::increase);
            threads[j].start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(i.get());
    }
}
