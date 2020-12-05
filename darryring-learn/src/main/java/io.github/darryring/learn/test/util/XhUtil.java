package io.github.darryring.learn.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author darryring
 **/
public class XhUtil {
    public static String convertStreamToString(InputStream stream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        try {
            while ((len = stream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            return outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 多行字符串
     * 入参括号中传入，使用 /* ....* /  形式注释
     *
     * @return 字符串
     */
    public static String S() {
        String javaSource = threadLocal.get();
        try {
            StackTraceElement element = new RuntimeException().getStackTrace()[1];
            byte[] bytes = javaSource.getBytes("UTF-8");
            String s = convertStreamToString(new ByteArrayInputStream(bytes, 0, bytes.length));
            s += element.getLineNumber();
            return s.substring(s.indexOf("/*") + 2, s.indexOf("*/"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
