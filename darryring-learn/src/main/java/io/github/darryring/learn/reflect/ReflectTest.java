package io.github.darryring.learn.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-21 11:35
 */
public class ReflectTest {
    public static void main(String[] args) {
        Student student = zero();
        System.out.println(student.getScore());
    }

    @Data
    private static class Student {
        private String id;
        private BigDecimal score;
    }

    public static Student zero() {
        Student student = new Student();
        Field[] fields = Student.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(BigDecimal.class)) {
                field.setAccessible(true);
                try {
                    field.set(student, BigDecimal.ZERO);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
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
}
