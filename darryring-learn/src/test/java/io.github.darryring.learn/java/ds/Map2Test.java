package io.github.darryring.learn.java.ds;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by xh on 2019-11-10 17:35
 *
 * @explain
 */
class Map2Test {

    /**
     * 自动扩容数组
     */
    @Test
    void t1() {
        ArrayList<String> list = new ArrayList<>();
        // 减少扩容次数
        int minCapacity = 100;
        list.ensureCapacity(minCapacity); // 使得数组容量直接扩大为 100
        // 线程安全
        List<String> synchronizedList = Collections.synchronizedList(list);
    }

    /**
     * 头尾指针、前驱后继节点，双向链表
     */
    @Test
    void t2() {
        LinkedList<String> list = new LinkedList<>();
    }

    /**
     * 阻塞写，只保证最终一致性，不保证实时一致性
     * 数组的长度就是集合的大小
     */
    @Test
    void t3() {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    }
}
