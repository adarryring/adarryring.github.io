package io.github.darryring.util.fanyi.convert;

import com.alibaba.fastjson.JSONObject;
import io.github.darryring.util.enums.SqlEnum;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.github.darryring.util.enums.SqlEnum.tinyint;

/**
 * <p>
 * 翻译转换器
 * </p>
 *
 * @author darryring
 * @since 2020-10-19 16:10
 */
public class FanyiConvert {

    public static JSONObject key(JSONObject jsonObject, Map<String, String> replaceFanyi, Map<String, String> replaceEnglish, Map<String, String> yesOrNo) {
        if (null == jsonObject) return null;
        jsonObject.getJSONArray("trans_result").forEach(e -> {
            JSONObject ee = (JSONObject) e;

            // 转为 utf8 ，解决中文乱码
            ee.put("src", new String(ee.getString("src").getBytes(), StandardCharsets.UTF_8));

            // 将中文改成自定义的翻译字段
            boolean doReplace = false;
            for (String k : replaceFanyi.keySet()) {
                if (k.equals(ee.getString("src"))) {
                    ee.put("dst", replaceFanyi.get(k));
                    doReplace = true;
                    break;
                }
            }
            if (doReplace) return;

            String s = ee.getString("dst");
            s = s.toLowerCase();

            // 去掉括号注释
            s = SqlEnum.quote(s);

            // 替换掉翻译字段中的特殊符号
            for (String k : replaceEnglish.keySet()) s = s.replace(k, replaceEnglish.get(k));

            // 去掉尾巴
            if (s.endsWith("_")) s = s.substring(0, s.length() - 1);
            // 改掉 is
            if (s.startsWith("is_")) s = "do_" + s.substring(3);
            // 编码
            if (s.startsWith("code_of_")) s = s.substring("code_of_".length()) + "_code";
            if (s.startsWith("name_of_")) s = s.substring("name_of_".length()) + "_name";

            // 是否
            if (!s.startsWith("do_"))
                for (String k : yesOrNo.keySet()) {
                    if (ee.getString("src").contains(k)) {
                        s = "do_" + s;
                        break;
                    }
                }
            ee.put("dst", s);
        });
        return jsonObject;
    }

    public static String sql(JSONObject jsonObject, String[] arrText, String[] arrInt, String[] arrDecimal, String[] arrDatetime) {
        StringBuilder sb = new StringBuilder();
        jsonObject.getJSONArray("trans_result").forEach(e -> {
            JSONObject ee = (JSONObject) e;
            String s;
            if (ee.getString("dst").startsWith("do_")) s = tinyint.sql;
            else s = SqlEnum.sql(ee.getString("src"), arrText, arrInt, arrDecimal, arrDatetime);

            sb.append(String.format(s, ee.getString("dst"), ee.getString("src"))).append("\n");
        });
        return sb.toString();
    }

}
