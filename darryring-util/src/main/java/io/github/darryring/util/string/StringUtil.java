package io.github.darryring.util.string;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author darryring
 * @explain 字符串处理类
 */
public class StringUtil {

    // 常量
    public static String IS_NULL = "IS_NULL";
    public final static String split_blank = " ";
    public final static String split_sprit = "/";
    public final static String encoding_utf8 = "UTF-8";
    public final static String encoding_gbk = "GBK";
    public final static int default_pagesize = 10;
    public final static String str_list = "[]";
    public final static String split_double_line = "\n\n";
    public final static String split_line = "\n";
    public final static String produces = "text/html;charset=UTF-8";
    public final static String str_split = "-";
    public final static String str_point = ".";
    public final static String str_double_point = ":";
    public final static String str_target_class = "target_class";
    public final static String str_do_application = "do_application";
    public final static String str_empty = "";

    public static String content_type_json = "application/json";
    public static String content_type_plain = "text/plain";

    // 模板
    public static String str_javascript = "<script type='text/javascript'>[[js]]</script>";

    // form提交方式的返回
    public static String getFormMsg(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("alert('").append(msg).append(
                "');if (typeof (eval(parent.closeModal)) == \"function\") {parent.closeModal(); parent.location.reload();} else  if(top.location==self.location){window.close();}");
        return StringUtil.getJs(sb.toString());
    }

    // js
    public static String getJs(String js) {
        return str_javascript.replace("[[js]]", js);
    }

    // 生成32位uuid
    public static String generateUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 获取实体类名的字符串 Authority
    public static String getEntityString(String class_path) {
        String temp[] = class_path.split("\\.");
        return temp[temp.length - 1];
    }

    // 获取数组内容
    public static String getStringList(JSONArray jsonArray) {
        String str = jsonArray.toJSONString();
        return str.substring(1, str.length() - 1); // 去掉中括号
    }

    // 命名规则：Camel-Case 首字母小写的驼峰规则
    public static String getPrefixLower(String str) {
        return (char) (str.charAt(0) + 32) + str.substring(1);
    }

    // 首字母大写
    public static String getPrefixUpper(String str) {
//        return (char) (str.charAt(0) - 32) + str.substring(1);

        // 不用管下划线的事
//        int i = str.indexOf("_");
//        if (-1 != i && i != (str.length() - 1)) {
//            str = str.substring(0, i + 1) + Character.toLowerCase(str.charAt(i + 1)) + str.substring(i + 2);
//        }
        if (Character.isLowerCase(str.charAt(0))) {
            str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        return str;
    }

    // 返回值
    public static String getJsonResult(Object obj) {
        // JSONObject.toJSONString(jsonArray_myprocessinstance_id).replace("\"", "\\\"")
        return JSONObject.toJSONString(obj).replaceAll("//", "\\/\\/");
    }

    // 返回msg
    public static String getMsg(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        return getJsonResult(jsonObject);
    }

    // string to ListMap
    public static void getListMap(List<Map<String, Object>> listMap_result, String result) {
        JSONArray jsonArray = JSONArray.parseArray(result);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject_e = jsonArray.getJSONObject(i);
            Map<String, Object> map_e = new HashMap<>();

            Iterator<String> iterator_e = jsonObject_e.keySet().iterator();
            while (iterator_e.hasNext()) {
                String str_key = iterator_e.next();
                map_e.put(str_key, jsonObject_e.get(str_key));
            }
            listMap_result.add(map_e);
        }
    }

    // 去掉首尾空格、换行符
    public static String trimString(String str_content) {
        if (str_content == null) {
            str_content = "";
        }
        str_content = str_content.trim();
        str_content = str_content.replace("\r|\n", ""); // 去掉末尾的换行符

        return str_content;
    }

    // 判断字符是否是中文
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    // 判断字符串是否是乱码
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;

        return result > 0.4;
    }

    // 去除乱码
    public static String removeMessyCode(String strName) {
        String str_result = "";
        for (int i = 0; i < strName.length(); i++) {
            if (!isMessyCode(strName.substring(i, i + 1))) {
                str_result += strName.substring(i, i + 1);
            }
        }
        return str_result;
    }

    // 生成时间字符串
    public static String getTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        return sdf.format(new Date());
    }

    // 展示合理的时间格式
    public static String getRightTime(Date date) {
        if (null == date) {
            return null;
        }

        // SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    // 生成时间戳
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * @explain 根据范围的闭区间生成随机数
     **/
    public static int getRandomBySection(int min, int max) {
        Random random = new Random();

        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 获取当前页码
     **/
    public static int getCurrentPage(String str_current_page) {
        int current_page = 0;

        if (!(null == str_current_page || str_current_page.trim().isEmpty())) {
            try {
                current_page = Integer.parseInt(str_current_page);
            } catch (Exception e) {
                current_page = 1;
            }
        }
        if (current_page < 1) {
            current_page = 1;
        }

        return current_page;
    }

    /**
     * 获取页大小
     **/
    public static int getPageSize(String str_pagesize) {
        int pagesize = default_pagesize; // 默认每页大小

        if (!(null == str_pagesize || str_pagesize.isEmpty() || Integer.parseInt(str_pagesize) == 0)) {
            try {
                pagesize = Integer.parseInt(str_pagesize);
            } catch (Exception e) {
                pagesize = default_pagesize;
            }
        }
        return pagesize;
    }

}
