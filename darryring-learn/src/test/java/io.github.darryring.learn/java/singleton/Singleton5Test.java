package io.github.darryring.learn.java.singleton;

/**
 * Created by xh on 2019-11-10 17:07
 *
 * @explain
 */
public class Singleton5Test {
    /**
     * 内部类，延迟加载，由于【类加载】是线程安全的，所以这里不需要额外的同步措施
     */
    private static class SingletonHolder {
        private static final Singleton5Test uniqueInstance = new Singleton5Test();
    }

    public static Singleton5Test getInstance() {
        return SingletonHolder.uniqueInstance;
    }
}
