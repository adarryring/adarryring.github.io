package io.github.darryring.learn.leetcode;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class Main {

    private final Logger logger = LoggerFactory.getLogger(Main.class);
    private final String UTF8 = "UTF-8";

    public JSONObject requestTakeJson(String url) {
        String result = request(url);
        if (StringUtils.isBlank(result)) {
            logger.info("请求返回值为空 url : {}", url);
        }
        return JSONObject.parseObject(result);
    }

    public String request(String url) {
        StringBuilder result = new StringBuilder();
        try {
            URLConnection urlConnection = URI.create(url).toURL().openConnection();
            urlConnection.connect();

            // read result
            try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String post(String url, Map<String, String> mapParam) {
        StringBuilder result = new StringBuilder();
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) URI.create(url).toURL().openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.connect();

            // make param
            StringBuilder paramBuilder = new StringBuilder();
            mapParam.forEach((k, v) -> {
                try {
                    paramBuilder.append("&").append(k).append("=").append(URLEncoder.encode(v, UTF8));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });

            // set param
            try (DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream())) {
                out.writeBytes(paramBuilder.toString());
                out.flush();
            }

            // read result
            try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public void writeToMarkdown(String name, String content) {
        // make nothing tag and save return id
        final String urlMakeNothing = "http://local.xh.com:8080/markdown/entityListTreeAdd?pId=40288342692244a901693e87eb260028"; // composition下

        // {"id":"40288342692244a901693e8966b0002b","name":"nothing","pId":"40288342692244a901693e87eb260028"}
        JSONObject jsonObject = requestTakeJson(urlMakeNothing);
        String id = jsonObject.getString("id");

        try {
            name = URLEncoder.encode(name, UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String urlRename = String.format("http://local.xh.com:8080/markdown/entityRenameRest?id=%s&name=%s", id, name);
        jsonObject = requestTakeJson(urlRename);
        logger.info("request urlRename result is {}", jsonObject.getString("msg"));

        // POST
        // id:40288342692244a901693e8837370029
        // content:123
        // 操作成功
        final String urlContent = "http://local.xh.com:8080/markdown/update";
        Map<String, String> mapParam = Maps.newHashMapWithExpectedSize(2);
        mapParam.put("id", id);
        mapParam.put("content", content);
        String result = post(urlContent, mapParam);
        logger.info("update content result is {}", result);
    }

    public static void start() {
        Main main = new Main();
        int i = 10;
        i = 9;
        switch (i) {
            case 1:
                MatchChar.main(null);
                break;
            case 2:
                TreeLevelTraversal.main(null);
                break;
            case 3:
                LoopQueue.main(null);
                break;
            case 4:
                TopKFrequent.main(null);
                break;
            case 5:
                SegmentTree.main(null);
                break;
            case 6:
                String sql = "select cst_id, cst_name, cst_type from vk_ds_d_erp_customer_info where substr(loadtime, 1,10)='%s' and substr(createtime, 1,10)='%s'";
                System.out.println(String.format(sql, "123", "123"));
                break;
            case 7:
                String a = "123";
                String b = "123";
                System.out.println(a == b);
                break;
            case 8:
                int retryTimes = 3;
                do {
                    System.out.println("fuck");
                } while (--retryTimes > 0);
                break;
            case 9:

//                main.writeToMarkdown("good-中文", "中文乱码");

                final String picFolder = "D:\\Deposit\\DataMan\\server\\8519-apache-tomcat-8.0.39\\webapps\\ROOT\\pic\\composition/";
                final String picUrlPrefix = "![](http://file.xh.com/pic/composition/";
                final String pngPath = "C:/Users/xh/Documents/Run/png";

                // save to markdown
                try {
                    Files.walkFileTree(Paths.get(pngPath), new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            String name = file.getFileName().toString();
                            String content = picUrlPrefix + name + ")";

                            name = name.substring(0, name.lastIndexOf("."));
//                            System.out.println(name);
//                            System.out.println(content);
                            main.writeToMarkdown(name, content);

                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // move to picUrlPrefix folder
                try {
                    Files.walkFileTree(Paths.get(pngPath), new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            System.out.println("move file " + file.getFileName());
                            Files.move(file, Paths.get(picFolder + file.getFileName()));

                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 10:
                String num = "-123.3";
                try {
                    Double dou = new Double(num);
                    System.out.println(dou);
                } catch (Exception e) {
                    throw new RuntimeException("非数值型");
                }

                System.out.println(NumberUtils.isNumber(num));
                break;

            case 11:
//                Strman.append()
                Thread thread = new Thread();
                break;
        }
    }
}
