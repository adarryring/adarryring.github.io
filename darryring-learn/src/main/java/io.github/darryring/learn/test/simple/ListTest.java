package io.github.darryring.learn.test.simple;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ListTest {
    public static void main(String[] args) {
        List<Map<String, Object>> list_result = new LinkedList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("fuck", "111");
        map.put("is_read", "1");
        list_result.add(map);

        Map<String, Object> map_2 = new HashMap<>();
        map_2.put("fuck", "111");
        map_2.put("is_read", null);
        list_result.add(map_2);
        list_result.removeIf(e -> e.get("is_read") == null || !("1".equals(e.get("is_read").toString())));
        System.out.println(list_result);
    }

}
