package io.github.darryring.learn.test.map;

import java.util.*;

public class MapSort {

    private static void t1() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");

        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getKey().compareTo(mapping2.getKey());
            }
        });

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + " ：" + mapping.getValue());
        }
    }

    private static void t2() {
        Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1);// 降序排序
            }
        });
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");

        for (String key : map.keySet()) {
            System.out.println(key + " ：" + map.get(key));
        }
    }

    private static void t3() {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");

        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });

        for (String key : map.keySet()) {
            System.out.println(key + " ：" + map.get(key));
        }
    }
}
