package io.github.darryring.util.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-11-04 14:02
 */
public class JavaLearn {


    public static <T extends BaseDO> void setBizId(T d) {
        for (Method declaredMethod : d.getClass().getDeclaredMethods()) {
            if (declaredMethod.getName().equals("setBizId")) {
                try {
                    declaredMethod.invoke(d, UUID.randomUUID().toString());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        // String 转 BigDecimal
        BigDecimal bigDecimal = new BigDecimal("1.23");
        System.out.println(bigDecimal);

        //  Array 转换为 Stream
        String[] array = {"a", "b", "c", "d", "e"};
        //Arrays.stream
        Stream<String> stream = Arrays.stream(array);
        stream.forEach(x -> System.out.println(x));

        System.out.println("======");

        //Stream.of
        Stream<String> stream1 = Stream.of(array);
        stream1.forEach(x -> System.out.println(x));

        // java 反射 set
        setBizId(null);

        // java8 group 统计
        //分组
//        Map<String,List<Apple>> map = appleList.stream().collect(Collectors.groupingBy(Apple::getType));
//        for (Map.Entry<String, List<Apple>> entry : map.entrySet()) {
//            System.out.println("分组" + JsonUtil.toJson(entry));
//        }

        //分组求和
//        Map<String, LongSummaryStatistics> collect = appleList.stream().collect(
//                Collectors.groupingBy(Apple::getType,
//                        Collectors.summarizingLong(Apple::getCount)));
    }
}
