package io.github.darryring.util.fanyi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.darryring.util.api.TransApi;
import io.github.darryring.util.util.FileUtil;
import io.github.darryring.util.util.IOStreamUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * <p>
 * 翻译请求
 * </p>
 *
 * @author darryring
 * @since 2020-11-21 09:26
 */
public class FanyiRequest {

    public static JSONObject fanyi(TransApi api, String path) {
        // 翻译结果
        String lines = FileUtil.lines(path + ".txt");
//        System.out.println(api.getTransResult(lines.get(0) + "\n" + lines.get(1), "auto", "en"));
//        System.out.println(api.getTransResult(lines, "auto", "en")); // 414 请求太长
        lines = api.postTransResult(lines, "auto", "en"); // 翻译结果 !!

        // 写入 json 文件
        JSONObject jsonObject = JSON.parseObject(lines);
        try {
            IOStreamUtil.write(new FileOutputStream(path + ".json"), JSONObject.toJSONString(jsonObject, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (null == jsonObject) throw new RuntimeException("翻译接口请求太频繁了！");
        return jsonObject;
    }

}
