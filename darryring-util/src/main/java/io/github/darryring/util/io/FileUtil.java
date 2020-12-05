package io.github.darryring.util.io;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文件工具类
 * </p>
 *
 * @author darryring
 * @since 2020-10-19 16:32
 */
public class FileUtil {
    public static void t1() {
        System.out.println(System.getProperty("user.dir"));
        final String current = new File("").getAbsolutePath();
        final String root = new File(".").getAbsolutePath();
        System.out.println(current);
        System.out.println(root);
        System.out.println(FileUtil.class.getResource("/").getPath());
    }

    public static String lines(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            lines = lines.stream().filter(e -> !StringUtils.EMPTY.equals(e)).collect(Collectors.toList()); // 去掉空行

            // 去掉括号
//            lines = lines.stream().map(s -> {
//                int i = s.indexOf('（');
//                if (-1 != i) s = s.substring(0, i);
//                return s;
//            }).collect(Collectors.toList());
            return lines.stream().distinct().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("fuck you");
    }

    public static void rename(String path, String s, String con_, String sa_conf_) {
        for (File file : new File(path + s).listFiles()) {
            // 改文件名
            String filename = file.getAbsolutePath();
            String newName = filename.replace(con_, sa_conf_);
            boolean ignore = file.renameTo(new File(newName));
            System.out.println(ignore);
        }
//        try {
//            Files.walk(Paths.get(CommonConstant.resourcePath + "salary/conf/")).forEach(e -> {
//                String newName = e.toUri().toString().replace("sa_conf_", "sa_conf_");
//                System.out.println(e.toUri());
//                System.out.println(newName);
//                System.exit(1);
////                boolean ignore = e.toFile().renameTo(new File(newName));
////                System.out.println(ignore);
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void replace(String resourcePath, String s, String con_, String sa_conf_) {
        for (File file : new File(resourcePath + s).listFiles()) {
            String filename = file.getAbsolutePath();

            // 改表名
            if (filename.endsWith(".sql")) {
                try {
                    List<String> list = Files.readAllLines(Paths.get(file.toURI()));
                    list.set(0, list.get(0).replace(con_, sa_conf_));
                    String join = String.join("\n", list);

                    IOStreamUtil.write(new FileOutputStream(file), join);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                System.exit(1);
            }
        }
    }

    public static void allSql(String resourcePath, String path, String allName) {
        StringBuilder sb = new StringBuilder();
        for (File file : new File(resourcePath + path).listFiles()) {
            String filename = file.getAbsolutePath();

            // 合并内容
            if (filename.endsWith(".sql")) {
                try {
                    List<String> list = Files.readAllLines(Paths.get(file.toURI()));
                    String join = String.join("\n", list);
                    sb.append(join).append("\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            IOStreamUtil.write(new FileOutputStream(new File(resourcePath + allName + ".sql")), sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
