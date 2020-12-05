package io.github.darryring.learn.java.instance;

/**
 * Created by xh on 2019-12-05 21:15
 *
 * @explain
 */
public class Instance1Test {

    static {
        System.out.println("a");
    }

    {
        System.out.println("b");
    }

    Instance1Test() {
        System.out.println("c");
    }
}
