package io.github.darryring.learn.singleton;

/**
 * Created by darryring on 2019-11-10 17:07
 *
 * @explain
 */
public class Singleton1Test {
    private static Singleton1Test uniqueInstance;

    /**
     * 懒汉模式：线程不安全
     *
     * @return singleton
     */
    public static Singleton1Test getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton1Test();
        }
        return uniqueInstance;
    }
}
