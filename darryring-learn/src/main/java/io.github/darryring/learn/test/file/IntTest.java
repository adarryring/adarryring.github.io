package io.github.darryring.learn.test.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IntTest {

    private final static List<Integer> list_content = new ArrayList<Integer>(8) {
        {
            add(0);
            add(1);
            add(0);
            add(1);
            add(1);
            add(1);
            add(0);
            add(0);
        }
    };
    private final static String content = "01011100";
    private final static String file_path = "D:/1.dat";

    /**
     * @param content 纯数字的字符串
     * @throws IOException
     */
    private static void write(String content) throws IOException {
        new File(file_path).delete();
        String str = new String(content.getBytes(), "ascii"); // 单字节读取
        byte[] arr_b = new byte[str.length() / 8]; // 位存储，节省8倍空间
        for (int i = 0, k = str.length() / 8; i < k; i++) {
            for (int j = 0; j < 8; j++) {
                // 等于字符1表示有值
                if (str.charAt(i * 8 + j) == '1') {
                    byte b = (byte) (0x80 >> j); // 十进制128，二进制-128
                    arr_b[i] = (byte) (arr_b[i] | b);
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(file_path);
        fos.write(arr_b);
        fos.close();
    }

    private static void read() throws IOException {
        byte[] arr_b = Files.readAllBytes(Paths.get(file_path));
        // ... 自己写一下
    }

    private static String tansform() {
        StringBuilder sb = new StringBuilder(8);
        list_content.forEach(sb::append);
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
//        write(content);
        write(tansform());
//        read();
    }

}
