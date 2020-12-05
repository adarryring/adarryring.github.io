package io.github.darryring.learn.java.reference;

import io.github.darryring.util.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by xh on 2019-11-10 15:57
 *
 * @explain 引用类型：强、软、弱、虚
 */
class Reference1Test {

    /**
     * 1. 引用队列可以获取被回收的 key，参考 {@link WeakHashMap#expungeStaleEntries()}
     * 2. 虚引用参考：{@link sun.misc.Cleaner}
     * 3. 对象存活判断：引用计数法、可达性分析
     * 4. 垃圾收集算法：Mark-Sweep、Copying、Mark-Compact、Generational Collection
     * 5. Hotspot 实现：枚举根节点、OopMap（Ordinary Object Pointer，普通对象指针）、Safepoint、Stop The World、主动式中断、SafeRegion
     */
    @Test
    void t1() {
        String s = "强引用";

        SoftReference<String> softReference = new SoftReference<>("软引用"); // 内存不足时回收
        System.out.println(softReference.get());
        WeakReference<String> weakReference = new WeakReference<>("弱引用"); // gc 时回收
        System.out.println(weakReference.get());

        PhantomReference<Object> phantomReference = new PhantomReference<>("虚引用", new ReferenceQueue<>()); // 回收时可获取一个系统通知
        phantomReference = new PhantomReference<>(new Object(), new ReferenceQueue<>()); // 如果是 String，手动 gc 可能不被回收，因为常量池有自己的回收机制
        System.out.println(phantomReference.get()); // 永远返回 null，即不能通过【虚引用】获取实例
        System.out.println(phantomReference.isEnqueued()); // 判断是否被回收
        System.gc();
        ThreadUtil.waitThreeSeconds(); // 等待 gc 操作完成
        System.out.println(phantomReference.isEnqueued()); // 判断是否被回收
    }
}
