package io.github.darryring.learn.leetcode;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Stack;

//import org.junit.Assert;

/**
 * leetcode-20
 * 跟里面的solution一样的使用了map结构，真是6
 */
public class MatchChar {

    public boolean solution(String str) {
        Stack<Character> stack = new Stack<>();
        String leftChar = "([{";
        Map<Character, Character> mapRightLeft = Maps.newHashMap();
        mapRightLeft.put(')', '(');
        mapRightLeft.put(']', '[');
        mapRightLeft.put('}', '{');

        for (int i = 0, j = str.length(); i < j; i++) {
            char k = str.charAt(i);
            // left push
            if (leftChar.contains(String.valueOf(k))) {
                stack.push(k);
            } else {
                // check
                if (stack.isEmpty()) {
                    return false;
                }
                // right pop
                char matchReal = mapRightLeft.get(k);
                if (stack.pop() != matchReal) {
                    return false;
                }
            }
        }
        // empty is right result
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        MatchChar matchChar = new MatchChar();

//        Assert.assertTrue(matchChar.solution("()[]{}"));
//        Assert.assertFalse(matchChar.solution("([)]"));
//        Assert.assertFalse(matchChar.solution("("));
//        Assert.assertFalse(matchChar.solution(")"));
    }
}
