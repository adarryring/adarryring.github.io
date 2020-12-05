package io.github.darryring.util.algorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 范围数组算法
 * </p>
 *
 * @author darryring
 * @since 2020-08-25 11:36
 */
public class RangeAlgo {

    /**
     * @param arr 左开右闭
     * @param val 区间分值，比 arr 多一个长度
     * @param n   查找值
     * @return 分值
     */
    private static <T> T hitRange(int[] arr, T[] val, Number n) {
        final int v = n.intValue();
        int i = 0;
        for (; i < arr.length; i++) {
            if (arr[i] >= v) break;
        }
        return val[i];
    }

    /**
     * 如果 v <= 0，值为 0
     * 如果 0 < v ≤ 50，值为 1
     * 如果 50 < v ≦ 100，值为 3
     * 如果 v > 200，值为 10
     *
     * @param v 分数
     * @return 评级
     */
    private static int t1(BigDecimal v) {
        return hitRange(new int[]{0, 50, 100, 200}, new Integer[]{0, 1, 3, 10}, v);
    }

    /**
     * 分值累计叠加：
     * 1.歌唱 = 10
     * 2.跑步3公里 = 3
     * 3.俯卧撑50个 = 5
     *
     * @param vs 个项
     * @return 累计总分值
     */
    private static int total(List<Integer> vs) {
        if (CollectionUtils.isEmpty(vs)) return 0;
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(vs.size());
        map.put(1, 10);
        map.put(2, 3);
        map.put(3, 5);
        if (1 == vs.size()) return map.get(vs.get(0));
        return vs.stream().reduce((a, b) -> map.get(a) + map.get(b)).orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(1 == t1(BigDecimal.valueOf(2)));
        System.out.println(3 == t1(BigDecimal.valueOf(55)));
        System.out.println(10 == t1(BigDecimal.valueOf(120)));
        System.out.println(15 == total(Lists.newArrayList(1, 3)));
    }
}
