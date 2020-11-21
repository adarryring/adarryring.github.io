package io.github.darryring.util.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>
 * 常用检查
 * </p>
 *
 * @author darryring
 * @since 2020-08-19 17:48
 */
public class CheckUtil {

    // JSON.toJSONString 顺序
    // @JSONType(orders = {"express", "yes", "no"})

    public static void id(String id) {
        if (Objects.isNull(id)) throw new RuntimeException("id 不能为空");
    }

    public static void empty(List<?> lt, String msg) {
        if (CollectionUtils.isEmpty(lt))
            throw new RuntimeException(msg);
    }

    public static void nullObject(Object o, String msg) {
        if (Objects.isNull(o))
            throw new RuntimeException(msg);
    }

    public static void blank(String str, String msg) {
        if (StringUtils.isBlank(str))
            throw new RuntimeException(msg);
    }

    public static <T> void sameByOneColumn(List<T> lt, Function<T, ?> getOneColumn, String msg) {
        if (lt.stream().map(getOneColumn).distinct().count() > 1)
            throw new RuntimeException(msg);
    }

    public static <T> void allMatch(List<T> lt, Predicate<T> predicate, String msg) {
        if (Boolean.FALSE.equals(lt.stream().allMatch(predicate)))
            throw new RuntimeException(msg);
    }

    public static void count(List<?> lt, int i, String msg) {
        if (i != lt.size())
            throw new RuntimeException(msg);
    }

    public static void exist(int i, String msg) {
        if (i > 0)
            throw new RuntimeException(msg);
    }

    public static void neg(int i, String msg) {
        if (i < 0)
            throw new RuntimeException(msg);
    }

    public static void entity(Object entity) {
        if (Objects.isNull(entity)) throw new RuntimeException("数据不存在");
    }

    public static void eq(int i, Integer j, String msg) {
        if (Objects.nonNull(j) && i == j) throw new RuntimeException(msg);
    }
}
