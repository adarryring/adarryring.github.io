package io.github.darryring.learn.sort;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.github.darryring.learn.exception.RRException;
import io.github.darryring.learn.util.CronExpression;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author darryring
 **/
public class Main {

    private static List<Integer> integers() {
        List<Integer> integers = Lists.newArrayList();
        integers.add(2);
        integers.add(1);
        integers.add(3);
        System.out.print("\nbefore : ");
        System.out.println(integers);
        return integers;
    }

    private static List<String> strings() {
        List<String> strings = Lists.newArrayList();
        strings.add("b");
        strings.add("c");
        strings.add("a");
        System.out.print("before : ");
        System.out.println(strings);
        return strings;
    }

    public static void simpleSort() {
        List<String> strings = strings();

        String[] arrStr = strings.toArray(new String[strings.size()]);
        Arrays.sort(arrStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 字符串升序
                return o1.compareTo(o2);
            }
        });
        System.out.print("after : ");
        System.out.println(Arrays.toString(arrStr));
        /* result
            before : [b, c, a]
            after : [a, b, c]
        */
    }

    public static void sorted() {
        List<Integer> integers;

        // 升序
        integers = integers();
        Collections.sort(integers, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.print("after : ");
        System.out.println(integers);
        /* result
            before : [2, 1, 3]
            after : [1, 2, 3]
         */

        // 升序
        integers = integers();
        integers.sort(Comparator.naturalOrder());
        System.out.print("after : ");
        System.out.println(integers);
        /* result
            before : [2, 1, 3]
            after : [1, 2, 3]
         */

        // 升序
        integers = integers();
        integers.sort(Comparator.comparingInt(x -> x));
        System.out.print("after : ");
        System.out.println(integers);
        /* result
            before : [2, 1, 3]
            after : [1, 2, 3]
         */
    }

    public static void main(String[] args) {
//        simpleSort();
//        sorted();
//        planCreateMsgTemplateDTOList.sort(Comparator.comparingInt(x -> MsgTemplateEnum.valueOf(x.getCode()).getOrd()));
//        planCreateMsgTemplateSuffixDTOList.sort(Comparator.comparingInt(PlanCreateMsgTemplateSuffixDTO::getOrd));


        String j = "[{\"userId\":\"1\"}]";
        List<JSONObject> parseArray = JSONArray.parseArray(j, JSONObject.class);
        System.out.println(parseArray.get(0).get("a"));

        String cron = "0 0 08 1 1 ? *";
        if (!CronExpression.isValidExpression(cron)) {
            throw new RRException("cron解析失败：" + cron);
        }
    }
}
