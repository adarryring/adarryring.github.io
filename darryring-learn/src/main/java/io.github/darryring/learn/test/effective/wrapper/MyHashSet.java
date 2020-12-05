package io.github.darryring.learn.test.effective.wrapper;

import java.util.Collection;
import java.util.HashSet;

public class MyHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public MyHashSet() {
    }

    public MyHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c); // 继承HashSet，这里就会发生问题。。
    }

    public int getAddCount() {
        return addCount;
    }
}
