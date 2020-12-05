package io.github.darryring.learn.java.hack.bytes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xh on 2019-12-21 11:30
 *
 * @explain
 */
public class Bytes1Test {

    private static void t1() {
        Package aPackage = HackSystem.class.getPackage();
        System.out.println(aPackage.getName());
        System.out.println(HackSystem.class.getName().replace(".", "/"));
    }

    private static void hack() throws IOException {
        InputStream is = new FileInputStream("C:/TestClass.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();

        String s = JavaClassExecuter.execute(b);
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        hack();
    }
}
