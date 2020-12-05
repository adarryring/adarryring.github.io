package io.github.darryring.learn.test.simple;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.github.darryring.learn.test.effective.wrapper.MyHashSet;
import io.github.darryring.learn.test.effective.wrapper.MyHashSet2;
import io.github.darryring.learn.test.notifyx.OutTurn;
import io.github.darryring.learn.test.steam.Receiver;
import io.github.darryring.learn.test.steam.Sender;
import io.github.darryring.learn.test.volatilex.wrongsingleton;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaTest {

    private static void t0() {
//        Timestamp
//        Arrays
//        Point
//        AbstractCollection
//        Maps
        Map e;
    }

    private static void t1() {
        Book book = new Book();
        book.setName("darryring");
        book.setPrice(100);

        String path_file = "d:/1.bin";
        // 写
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path_file));
            oos.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path_file));
            System.out.println(ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void t2() {
        MyHashSet<String> s = new MyHashSet<>();
        s.addAll(Arrays.asList("a", "b", "c"));
        System.out.println(s.getAddCount()); // 6 继承的脆弱性

        MyHashSet2<String> s2 = new MyHashSet2<>(new HashSet<>(3));
        s2.addAll(Arrays.asList("a", "b", "c")); // 3 包装类的强大
        System.out.println(s2.getAddCount());
    }

    private static void t3() {
        List<String> list_str = Lists.newArrayList();
        list_str.add("2");
        list_str.add("3");
        list_str.add("1");

        String[] arr_str = list_str.toArray(new String[list_str.size()]);
        Arrays.sort(arr_str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    return Integer.valueOf(o1) - Integer.valueOf(o2);
                } catch (Exception e) {
                    return o1.length() - o2.length();
                }
            }
        });
        // array to list
        System.out.println(new ArrayList<>(Arrays.asList(arr_str)));
    }

    private static void t4() {
        String a = "${f123";
//        System.out.println(Integer.valueOf(a));
        System.out.println(a.replace("1", "$123\""));
//        Node
//        Document
    }

    private static void t5() {
        Sender t1 = new Sender();

        Receiver t2 = new Receiver();

        PipedWriter out = t1.getWriter();

        PipedReader in = t2.getReader();
        try {
            //管道连接。下面2句话的本质是一样。
            //out.connect(in);
            in.connect(out);

            /**
             * Thread类的START方法：
             * 使该线程开始执行；Java 虚拟机调用该线程的 run 方法。
             * 结果是两个线程并发地运行；当前线程（从调用返回给 start 方法）和另一个线程（执行其 run 方法）。
             * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
             */
            t1.start();
            t2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void t6() throws IOException {
        String content = "BoyceZhang!";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        // 判断该输入流是否支持mark操作
        if (!inputStream.markSupported()) {
            System.out.println("mark/reset not supported!");
        }
        int ch;
        boolean marked = false;
        while ((ch = inputStream.read()) != -1) {

            //读取一个字符输出一个字符
            System.out.print((char) ch);
            //读到 'e'的时候标记一下
            if (((char) ch == 'e') & !marked) {
                inputStream.mark(content.length());  //先不要理会mark的参数
                marked = true;
            }

            //读到'!'的时候重新回到标记位置开始读
            if ((char) ch == '!' && marked) {
                inputStream.reset();
                marked = false;
            }
        }

        //程序最终输出：BoyceZhang!Zhang!

        // FileInputStream重复读
        try {
            File file = new File("d:/a.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            System.out.println(br.readLine());
            br.mark(1024);
            System.out.println(br.readLine());
            br.reset();
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void t7() {

        Thread t1 = new Thread(new wrongsingleton());
        Thread t2 = new Thread(new wrongsingleton());
        Thread t3 = new Thread(new wrongsingleton());
        Thread t4 = new Thread(new wrongsingleton());
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        while (t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive()) {
        }
        System.out.println("done");
    }

    private static void t8() {
        // System.out.println("lock");

        final OutTurn ot = new OutTurn();

        for (int j = 0; j < 100; j++) {

            new Thread(new Runnable() {

                public void run() {
                    // try {
                    // Thread.sleep(10);
                    // } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // }
                    for (int i = 0; i < 5; i++) {
                        ot.sub();
                    }
                }
            }).start();

            new Thread(new Runnable() {

                public void run() {
                    // try {
                    // Thread.sleep(10);
                    // } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // }
                    for (int i = 0; i < 5; i++) {
                        ot.main();
                    }
                }
            }).start();
        }
    }

    private static void t9() {
        long start, end;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        // 时间清零，从零点开始
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 0); // 上个月最后一天
        end = calendar.getTimeInMillis();

        calendar.set(Calendar.DAY_OF_MONTH, 1); // 上个月1号
        start = calendar.getTimeInMillis();

        System.out.println(start);
        System.out.println(end);
    }

    private static void t10() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss"); // 大小写是有区别的。。。
        System.out.println(sdf.format(Long.valueOf(1535731200000L)));
    }

    private static void t11() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "123\n123\n\n\"");
        System.out.println(jsonObject.get("result"));
        jsonArray.add(jsonObject);

        try {
            Files.write(Paths.get("C:\\Users\\xh\\Desktop/1.txt"), jsonArray.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void t12() {
        try {
//            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\xh\\Downloads/jb-all.txt"));
//            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\xh\\Desktop/1.txt"));
//            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\xh\\Desktop/test2.json"));
//            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\xh\\Downloads/jb-all-2.txt"));
//            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\xh\\Downloads/jb-all-3.txt"));
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\xh\\Downloads/jb-all-4.txt"));
            System.out.println(lines.size());
            String line = lines.get(0);

            // img tag
            // FIXME
            String pattern = "<img[^>]*>";
            line.replaceAll(pattern, "");
//            final Pattern compile = Pattern.compile(pattern);
//            List<String> result = Lists.newLinkedList();
//            Matcher matcher = compile.matcher(line);
//            while (matcher.find()) {
//                result.add(matcher.group());
//            }

//            System.out.println(line.substring(52348, 60000));

            JSONArray jsonArray = JSON.parseArray(String.join("", lines));
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            System.out.println(jsonObject.get("result"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void t13() {
        final Pattern compile = Pattern.compile("(http|https):\\/\\/([\\w.]+\\/?)\\S*");
        List<String> lines;
        List<String> result = Lists.newLinkedList();
        String line = "123asd http://img.39.net/yp/2010/4/17/c2f759cc72b.jpg 12313";
        Matcher matcher = compile.matcher(line);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        System.out.println(String.join("\n", result));

    }

    private static void t14() {
        String hosts = "http://img.39.net, http://pimg.39.net";
        String input = "http://img.39.net--http://pimg.39.net123";
        for (String s : hosts.split(",")) {
            input = input.replace(s.trim(), "");
        }
        System.out.println(input);
    }

    private static void t15() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\xh\\Downloads/jb-all.txt"));
            String content = String.join("", lines);
            Files.write(Paths.get("C:\\Users\\xh\\Downloads/jb-all-2.txt"), content.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        t1();
//        t2();
//        t3();
//        t4();
//        t7();
//        t9();
//        t10();
//        Observer
//        AtomicBoolean
//        Main.start();
//        t11();
//        t12();
//        t13();
//        t14();
//        t15();
        t12();
    }


}
