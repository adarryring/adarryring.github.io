package io.github.darryring.learn.java.concurrent.atomicity;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性
 * javap -v Atomicity1Test.class
 *
 * @author darryring
 **/
class Atomicity1Test {

    /**
     * 进源码看
     * 静态代码块：unsafe.objectFieldOffset() 反射获取本对象的 value 属性内存地址，作为 硬件原子指令 的参数用
     * 更新值原理：unsafe.compareAndSwapInt() 硬件原子指令
     * 这就是 CAS 操作
     */
    private AtomicInteger atomicInteger = new AtomicInteger(10);

    /**
     * iinc M N	（M 为非负整数，N 为整数）将局部变量数组的第 M 个单元中的 int 值增加 N，常用于 for 循环中自增量的更新
     * 一共 3 个步骤：读-改-写
     * 非原子性，多线程下会出现统计错误
     */
    @Test
    void t1() {
        int i = 10;
        i++;
        System.out.println(i);
    }

    @Test
    void t2() {
        int i = atomicInteger.getAndAdd(1);
        System.out.println(i);
    }
}
