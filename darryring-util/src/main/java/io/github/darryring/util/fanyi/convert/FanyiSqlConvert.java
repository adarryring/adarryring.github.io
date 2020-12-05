package io.github.darryring.util.fanyi.convert;

import com.alibaba.fastjson.JSONObject;
import io.github.darryring.util.api.TransApi;
import io.github.darryring.util.fanyi.FanyiRequest;
import io.github.darryring.util.fanyi.convert.FanyiConvert;
import io.github.darryring.util.io.FileUtil;
import io.github.darryring.util.io.IOStreamUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 转换SQL操作
 * </p>
 *
 * @author darryring
 * @since 2020-11-21 09:34
 */
public class FanyiSqlConvert {
    public static void run(TransApi api, String tableName, String tableComment, String path
            , Map<String, String> replaceFanyi, Map<String, String> replaceEnglish, List<String> yesOrNo
            , String templatePath, String[] arrText, String[] arrInt, String[] arrDecimal, String[] arrDatetime) {
        JSONObject jsonObject = FanyiRequest.fanyi(api, path);
        FanyiConvert.key(jsonObject, replaceFanyi, replaceEnglish, yesOrNo);

        // 生成SQL
        String sql = FanyiConvert.sql(jsonObject, arrText, arrInt, arrDecimal, arrDatetime);
        sql = String.format(FileUtil.lines(templatePath), tableName, sql, tableComment);
        try {
            IOStreamUtil.write(new FileOutputStream(path + ".sql"), sql);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
