package io.github.darryring.learn.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author darryring
 **/
public class CyclicBarrierTest3 {
    private static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException,
            BrokenBarrierException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                }
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }
    }
}