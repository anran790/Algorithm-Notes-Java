package 阶段5;

import java.util.Arrays;

/**
 * LC455 分发饼干
 *
 * 难度：简单    标签：贪心 / 排序 + 双指针
 *
 * 题目：g[i] 是第 i 个孩子的胃口，s[j] 是第 j 块饼干的尺寸。每块饼干最多给一个孩子，
 *       只有 s[j] >= g[i] 才能满足。求最多能满足多少个孩子。
 * 示例：g = [1,2,3], s = [1,1]  →  1
 *       g = [1,2],   s = [1,2,3]  →  2
 *
 * 思路：典型的「小胃口优先喂小饼干」。
 *       两个数组都升序排序，双指针分别指向孩子和饼干：
 *       饼干够大就同时前移，孩子数 +1；饼干太小就只换下一块更大的饼干。
 *       这样能保证每一块饼干都用在「最容易被满足」的孩子身上，不浪费。
 * 复杂度：时间 O(n log n + m log m)（排序主导），空间 O(1)
 *
 * 坑点：
 * - 必须两个数组都排序，否则贪心不成立。
 * - 饼干不够时只能丢饼干（j++），不能丢孩子（i++），因为孩子还在等下一块。
 * - 循环条件是「任一数组走完即停」，剩下的孩子再也满足不了。
 */
public class LC455 {
    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println(findContentChildren(g, s));
    }

    /**
     * @param g 每个孩子的胃口
     * @param s 每块饼干的尺寸
     * @return 最多能满足的孩子数
     */
    public static int findContentChildren(int[] g, int[] s) {
        // 从小到大排序，小胃口配小饼干
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;      // 指向当前待满足的孩子
        int j = 0;      // 指向当前待使用的饼干
        int count = 0;  // 已满足的孩子数

        // 孩子或饼干任一用完就结束
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                // 这块饼干能满足孩子：孩子和饼干都前进
                i++;
                j++;
                count++;
            } else {
                // 饼干太小，孩子不满足，换下一块更大的饼干
                j++;
            }
        }
        return count;
    }
}
