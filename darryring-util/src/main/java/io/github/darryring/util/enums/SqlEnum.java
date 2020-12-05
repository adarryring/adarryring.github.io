package io.github.darryring.util.enums;

import lombok.Getter;

/**
 * <p>
 * mysql
 * </p>
 *
 * @author darryring
 * @since 2020-11-21 10:07
 */

@Getter
public enum SqlEnum {

    tinyint("    %s tinyint(4) COMMENT '%s',"),
    int_("    %s int(11) COMMENT '%s',"),
    text("    %s text COMMENT '%s',"),
    decimal("    %s DECIMAL(17, 7) COMMENT '%s',"),
    string("    %s varchar(255) default null COMMENT '%s',"),
    datetime("    %s datetime default null COMMENT '%s',");

    public String sql;

    SqlEnum(String sql) {
        this.sql = sql;
    }

    public static boolean judge(String src, String[] arr) {
        for (String s : arr) {
            // 注意：endsWith
            if (src.endsWith(s)) {
                return true;
            }
        }
        return false;
    }

    public static String quote(String src) {
        int i = src.indexOf("（");
        int j = src.indexOf("(");
        if (-1 == i || i > j) i = j;
        if (-1 != i) src = src.substring(0, i);
        return src;
    }

    public static String sql(String src, String[] arrText, String[] arrInt, String[] arrDecimal, String[] arrDatetime) {
        src = quote(src);
        // 判断是否富文本类型
        if (judge(src, arrText)) return text.sql;
        // 判断是否整数类型
        if (judge(src, arrInt)) return int_.sql;
        // 判断是否数字类型
        if (judge(src, arrDecimal)) return decimal.sql;
        // 时间类型
        if (judge(src, arrDatetime)) return datetime.sql;
        return string.sql;
    }

}
