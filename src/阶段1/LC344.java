package 阶段1;

import java.util.Arrays;

/**
 * LC344 反转字符串
 *
 * 难度：简单    标签：字符串 / 双指针
 *
 * 题目：将字符数组 s 原地反转，要求只使用 O(1) 的额外空间。
 * 示例：s = ['h','e','l','l','o']  →  ['o','l','l','e','h']
 *
 * 思路：对撞双指针。i 从头、j 从尾，交换后同时向中间靠拢；
 *       走到中间即结束。循环次数只需 length / 2，因为一次交换处理两端两个字符。
 * 复杂度：时间 O(n)，空间 O(1)
 *
 * 坑点：
 * - 循环上界是 length / 2 而不是 length：写成 length 会交换两次，数组又变回原样。
 * - 交换必须借助临时变量（char 是基本类型，不能像对象那样直接赋值）。
 * - 奇数长度时中间那个字符不用动，length / 2 正好跳过它。
 */
public class LC344 {
    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        // 只需交换前一半，后一半由对称位置处理
        int n = s.length / 2;
        int len = s.length;

        for (int i = 0; i < n; i++) {
            // 与对称位置 len-1-i 交换
            char tmp = s[i];
            s[i] = s[len - 1 - i];
            s[len - 1 - i] = tmp;
        }

        System.out.println(Arrays.toString(s));
    }
}
