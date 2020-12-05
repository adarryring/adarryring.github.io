package io.github.darryring.learn.test;

/**
 * @author darryring
 **/
public class Main {

    public static void main(String[] args) {
        int t = 8;
        int p = t;
        int tail = 9;
        boolean result = (t != (t = tail));
        System.out.println("p=" + p + ", t=" + t + ", tail=" + tail + ", result=" + result);

        System.out.println(2 << 1);
        int numberPriorPrev = -1;
        int i = -5;
        int size = 9;
        System.out.println((numberPriorPrev - i) < (size >>> 1));

        numberPriorPrev = 66;
        i = 0;
        size = 7;
        System.out.println((numberPriorPrev - i) < (size >>> 1));


        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 << 29));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(1 << 29));
    }
}
