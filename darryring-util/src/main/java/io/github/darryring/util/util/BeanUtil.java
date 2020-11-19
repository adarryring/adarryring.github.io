package io.github.darryring.util.util;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-16 09:41
 */
public class BeanUtil {

    public static <T> T nullZero(T o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(BigDecimal.class)) {
                field.setAccessible(true);
                try {
                    if (null == field.get(o))
                        field.set(o, BigDecimal.ZERO);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

    public static <T> T zero(T o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(BigDecimal.class)) {
                field.setAccessible(true);
                try {
                    field.set(o, BigDecimal.ZERO);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

    public static <T, R> T one(R one, Class<T> clazz) {
        T detailT = null;
        if (Objects.nonNull(one)) {
            try {
                detailT = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            assert detailT != null;
            BeanUtils.copyProperties(one, detailT);
        }
        return detailT;
    }

    static <T> Map<String, Object> toMap(List<T> list) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, String>> listMap = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            // 时间格式
//            ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
//            DateConverter dateConverter = new DateConverter();
//            dateConverter.setPattern("yyyy-MM-dd");
//            convertUtils.register(dateConverter, String.class);

            list.forEach(e -> {
                try {
                    listMap.add(org.apache.commons.beanutils.BeanUtils.describe(e));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            });
        }
        map.put("lt", listMap);
        return map;
    }

    public static <T> void removeOther(List<String> collect, Class<T> clazz, List<T> pageData) {
        for (T record : pageData) {
            for (Field field : clazz.getDeclaredFields()) {
                if (collect.contains(field.getName()) || "serialVersionUID".equals(field.getName()) || "bizId".equals(field.getName()))
                    continue;
                try {
                    field.setAccessible(true);
                    field.set(record, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Data
    private static class Student {
        private Integer id;

        Student(Integer id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
//        List<String> collect = Lists.newArrayList("id");
        List<String> collect = Lists.newArrayList();

        List<Student> pageData = Lists.newArrayList(new Student(1));
        removeOther(collect, Student.class, pageData);
        System.out.println(pageData);
    }
}
