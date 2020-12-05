package io.github.darryring.learn.java.superextends;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author darryring
 **/
class Super1Test {

    // 上界
    class A {
    }

    class B extends A {
    }

    private class C extends B {
    }
    // 下界

    private void extends_(Class<? extends B> clazz) {
        System.out.println(clazz.getSimpleName());
    }

    private void super_(Class<? super B> clazz) {
        System.out.println(clazz.getSimpleName());
    }

    @Test
    void t1() {
//        extends_(A.class);
        extends_(B.class);
        extends_(C.class);

        super_(A.class);
        super_(B.class);
//        super_(C.class);

        // java要明确类型， Integer extends Number ，但是 List 可以存放 Integer、Long 这么多种类型，但是只能是其中一种，又不能确定是哪一种
        List<? extends  Number> list = new ArrayList<>();
//        List<  Number> list = new ArrayList<>();
//        list.add(new Integer(123));
    }
}
