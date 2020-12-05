package io.github.darryring.learn.java.singleton;

/**
 * Created by xh on 2019-11-10 17:07
 *
 * @explain
 */
public class Singleton2Test {
    private static Singleton2Test uniqueInstance;

    /**
     * 懒汉模式：线程安全
     *
     * @return singleton
     */
    public static synchronized Singleton2Test getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton2Test();
        }
        return uniqueInstance;
    }
}
