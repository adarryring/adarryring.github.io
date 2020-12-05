package io.github.darryring.learn.singleton;

/**
 * Created by darryring on 2019-11-10 17:07
 *
 * @explain
 */
public class Singleton4Test {
    // 饿汉模式：类加载时就初始化
    private static final Singleton4Test uniqueInstance = new Singleton4Test();

    public static Singleton4Test getInstance() {
        return uniqueInstance;
    }
}
