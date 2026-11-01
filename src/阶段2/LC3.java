package 阶段2;

import java.util.HashSet;
import java.util.Set;

/**
 * LC3 无重复字符的最长子串
 *
 * 难度：中等    标签：滑动窗口（不定长，求最大）
 *
 * 题目：给定字符串 s，找出不含重复字符的最长「子串」的长度。
 *       注意是子串（连续），不是子序列。
 * 示例：s = "abcabcbb"  →  3（"abc"）
 *       s = "pwwkew"    →  3（"wke"）
 *
 * 思路：维护一个「内部字符互不重复」的窗口，用 Set 记录窗口内的字符。
 *       right 右移，若新字符已存在，就从左边不断吐字符，直到把它排掉；
 *       排掉之后再把新字符加进来，此时窗口一定无重复，用长度更新答案。
 * 复杂度：时间 O(n)（每个字符最多进出 Set 一次），空间 O(字符集大小)
 *
 * 坑点：
 * - 收缩用 while 而不是 if：可能连续吐出多个字符才轮到那个重复字符。
 * - 求最大长度，是「不合法就收缩到合法」；求最小长度（LC209）是「合法就收缩到不合法」，
 *   两者收缩时机正好相反。
 * - 窗口内不能有重复，所以只能删最左边的字符，不能跳着删。
 */
public class LC3 {
    public static void main(String[] args) {
        String s = "abcabcbb";

        // 记录当前窗口内出现过哪些字符
        Set<Character> set = new HashSet<>();
        int maxLen = 0;  // 答案：最长无重复窗口长度
        int left = 0;    // 窗口左边界

        for (int right = 0; right < s.length(); right++) {
            char cur = s.charAt(right);

            // 新字符已经在窗口里了：从左边一直吐，直到把它吐出去
            while (set.contains(cur)) {
                set.remove(s.charAt(left));
                left++;
            }

            // 此时窗口内一定没有重复字符，把它加进来
            set.add(cur);
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        System.out.println(maxLen);
    }
}
