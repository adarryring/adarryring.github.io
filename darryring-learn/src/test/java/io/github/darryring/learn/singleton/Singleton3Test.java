package io.github.darryring.learn.singleton;

/**
 * Created by darryring on 2019-11-10 17:07
 *
 * @explain
 */
public class Singleton3Test {
    private static Singleton3Test uniqueInstance;
//    private static volatile Singleton3Test uniqueInstance;

    /**
     * 双重检查
     * 实例化对象有 3 个步骤：
     * 1. 分配内存
     * 2. 执行构造函数
     * 3. 将对象地址赋予引用
     * 如果发生【指令重排序】，那么执行顺序可能变为：1->3->2，这会导致阻塞线程进入同步块，且 2 未执行时，就会返回一个还没执行构造函数的对象引用，非常危险。
     * 可以将该引用声明为 volatile，可以达到禁止指令重排序优化。（JDK 5 才修复为禁止指令重排序）
     *
     * @return singleton
     */
    public static Singleton3Test getInstance() {
        if (uniqueInstance == null) {                         // Single Checked
            synchronized (Singleton3Test.class) {
                if (uniqueInstance == null) {                 // Double Checked
                    uniqueInstance = new Singleton3Test();
                }
            }
        }
        return uniqueInstance;
    }
}
