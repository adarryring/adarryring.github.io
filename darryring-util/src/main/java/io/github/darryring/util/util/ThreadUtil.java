package io.github.darryring.util.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * 线程测试工具类
 *
 * @author darryring
 **/
public class ThreadUtil {
    private static final int threadNum = 10;

    public static class ThreadConsumer implements Runnable {
        private CountDownLatch countDownLatch;
        private Supplier supplier;

        ThreadConsumer(CountDownLatch countDownLatch, Supplier supplier) {
            this.countDownLatch = countDownLatch;
            this.supplier = supplier;
        }

        @Override
        public void run() {
            ThreadUtil.waitLittleTime();
            supplier.get();
            countDownLatch.countDown();
        }
    }

    /**
     * 睡眠 10 秒
     */
    public static void waitMoreTime() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 睡眠 3 秒
     */
    public static void waitThreeSeconds() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机睡眠 10 毫秒以内
     */
    public static void waitLittleTime() {
        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable newInstance(Class<? extends Runnable> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("fuck your object");
    }

    /**
     * is not an enclosing class 内部类反射实例化
     *
     * @param clazz 内部类
     * @param outer 父类
     * @return 内部类的实例
     */
    private static Runnable newInnerInstance(Class<? extends Runnable> clazz, Class outer) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return (Runnable) constructor.newInstance(outer.newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("fuck your object");
    }

    /**
     * @param supplier 线程级无参执行方法
     */
    public static void run(Supplier supplier) {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        List<Thread> list = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; i++) list.add(new Thread(new ThreadConsumer(countDownLatch, supplier)));
        for (int i = 0; i < threadNum; i++) list.get(i).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
