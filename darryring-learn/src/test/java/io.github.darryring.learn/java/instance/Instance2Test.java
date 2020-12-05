package io.github.darryring.learn.java.instance;

/**
 * Created by xh on 2019-12-05 21:15
 *
 * @explain
 */
public class Instance2Test extends Instance1Test {

    private int a;

    static {
        System.out.println("a");
    }

    public static void main(String[] args) {
        // 父类初始化、子类初始化、父类构造方法、子类构造方法
        new Instance2Test();
    }

    {
        System.out.println("b");
        a = 10;
        System.out.println(a);
    }

    Instance2Test() {
        System.out.println("c");
    }

}
