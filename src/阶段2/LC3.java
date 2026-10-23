package 阶段2;

import java.util.HashSet;
import java.util.Set;

/**
 *
 代码
 测试用例
 测试用例
 测试结果
 3. 无重复字符的最长子串
 已解答
 中等
 相关标签
 premium lock icon
 相关企业
 提示
 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。


 示例 1:

 输入: s = "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
 示例 2:

 输入: s = "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: s = "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 */
public class LC3 {
    public static void main(String[] args) {
        // 典型滑动窗口求最大值
        // 出现重复字符串 left~right所有都是重复的
        // while循环条件判断是否重复，出现重复立刻进行左侧元素弹出
        // 走完while循环表示当前字符已经不重复，添加到set集合中，进行下一轮判断
        //求最大值的条件是不符进入while循环，符合跳出while循环，最小值正好相反
        String s = "abcabcbb";

        Set<Character> set = new HashSet<>();
        int count = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char str = s.charAt(right);
            while (set.contains(str)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(str);
            count = Math.max(count, right - left + 1);

        }
        System.out.println(count);
    }
}
