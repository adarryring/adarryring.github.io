package io.github.darryring.learn.concurrent.lock.sync;

import org.junit.jupiter.api.Test;

/**
 * javap -v Sync1Test.class
 * <p>
 * 1. 源码：
 * src/share/vm/runtime/objectMonitor.cpp
 * <p>
 * 2. 可重入：
 * <pre> {@code
 * if (cur == Self) {
 *   // TODO-FIXME: check for integer overflow!  BUGID 6557169.
 *   _recursions ++ ;
 *   return ;
 * }}</pre>
 * <p>
 * 3. 主内存与工作内存之间具体的 8 种交互操作协议：lock、unlock、read、load、use、assign、store、write
 * 4. happens-before：程序次序规则、管程锁定规则、volatile 变置规则、线程启动规则、线程终止规则、线程中断规则、对象终结规则、传递性
 * 5. 锁优化：自旋锁、自适应自旋、锁消除、锁粗化、轻量级锁、偏向锁
 *
 * @author darryring
 **/
class Sync1Test {

    /**
     * 同步块：
     * monitorenter / monitorexit
     */
    @Test
    void t1() {
        synchronized (Sync1Test.class) {
            System.out.println("hello synchronized block");
        }
    }

    /**
     * 同步方法：
     * flags: ACC_SYNCHRONIZED
     */
    @Test
    synchronized void t2() {
        System.out.println("hello synchronized method");
    }
}
