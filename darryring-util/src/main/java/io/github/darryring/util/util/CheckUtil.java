package io.github.darryring.util.util;

import io.github.darryring.util.exception.RestException;
import io.github.darryring.util.http.HttpStatusEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-08-19 17:48
 */
public class CheckUtil {

    // JSON.toJSONString 顺序
    // @JSONType(orders = {"express", "yes", "no"})

    public static void id(String id) {
        if (Objects.isNull(id)) throw new RestException(HttpStatusEnum.CONFLICT.status(), "id 不能为空");
    }

    public static void empty(List<?> lt, String msg) {
        if (CollectionUtils.isEmpty(lt))
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static void nullObject(Object o, String msg) {
        if (Objects.isNull(o))
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static void blank(String str, String msg) {
        if (StringUtils.isBlank(str))
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static <T> void sameByOneColumn(List<T> lt, Function<T, ?> getOneColumn, String msg) {
        if (lt.stream().map(getOneColumn).distinct().count() > 1)
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static <T> void allMatch(List<T> lt, Predicate<T> predicate, String msg) {
        if (Boolean.FALSE.equals(lt.stream().allMatch(predicate)))
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static void count(List<?> lt, int i, String msg) {
        if (i != lt.size())
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static void exist(int i, String msg) {
        if (i > 0)
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static void neg(int i, String msg) {
        if (i < 0)
            throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }

    public static void entity(Object entity) {
        if (Objects.isNull(entity)) throw new RestException(HttpStatusEnum.CONFLICT.status(), "数据不存在");
    }

    public static void eq(int i, Integer j, String msg) {
        if (Objects.nonNull(j) && i == j) throw new RestException(HttpStatusEnum.CONFLICT.status(), msg);
    }
}
