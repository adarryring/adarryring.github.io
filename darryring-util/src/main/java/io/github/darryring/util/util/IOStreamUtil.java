package io.github.darryring.util.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author darryring
 **/
public class IOStreamUtil {

    /**
     * 流读取【第一行】字符串
     *
     * @param inputStream 流
     * @return 字符串
     */
    public static String read(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("fuck your input stream");
    }

    /**
     * 流读取【所有】字符串
     *
     * @param inputStream 流
     * @return 字符串
     */
    public static List<String> readAllLine(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.lines().collect(Collectors.toList());
    }


    /**
     * 字符串写入流
     *
     * @param outputStream 写入流
     * @param s            字符串
     */
    public static void writeWithBuffer(OutputStream outputStream, String s) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        try {
            bufferedWriter.write(s);
            bufferedWriter.flush(); // 刷流
//            bufferedWriter.close(); // Socket is closed
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fuck your output stream");
        }
    }

    /**
     * 字符串写入流
     *
     * @param outputStream 写入流
     * @param s            字符串
     */
    public static void write(OutputStream outputStream, String s) {
        try {
            outputStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void code(String path) {
        // 解析 Unicode 编码
        try {
            List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            JSONObject jsonObject = JSON.parseObject(String.join("\n", lines));
            System.out.println(jsonObject.toJSONString());
            write(new FileOutputStream(path), jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
