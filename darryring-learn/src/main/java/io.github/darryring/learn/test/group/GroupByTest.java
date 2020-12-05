package io.github.darryring.learn.test.group;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupByTest {

    String[] dd = {"2,1", "3,2", "4,3"}; // id, pId
    String[] ud = {"张三,4", "李四,3", "张三,3", "王五,2"}; // user, deptId

    /**
     * 计算部门人员数量，非递归
     */
    private void testGroupBy() {
        String[] strs = {"张三,4,1-2-3", "李四,3,1-2", "张三,3,1-2", "王五,2,1"};

        Map<String, Set<String>> counting = Lists.newArrayList(strs).stream().flatMap(x -> {
            List<User> users = Lists.newArrayList();

            String[] arr = x.split(",");
            User user = new User(arr[0], arr[1]);
            users.add(user);

            String[] depts = arr[2].split("-");
            for (String pDeptId : depts) {
                User userT = new User(arr[0], pDeptId);
                users.add(userT);
            }

            return users.stream();
        }).collect(Collectors.groupingBy(User::getDeptId, Collectors.mapping(User::getUsername, Collectors.toSet())));

        counting.forEach((k, v) -> {
            System.out.println(k + "\t" + v.size());
        });
    }

    public static void main(String[] args) {
        GroupByTest t = new GroupByTest();
        t.testGroupBy();
    }
}