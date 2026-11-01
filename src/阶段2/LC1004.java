package 阶段2;

/**
 * LC1004 最大连续 1 的个数 III
 *
 * 难度：中等    标签：滑动窗口
 *
 * 题目：二进制数组 nums，最多可以把 k 个 0 翻转成 1，求操作后连续 1 的最大长度。
 * 示例：nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2  →  6
 *
 * 思路：维护一个「内部 0 的个数不超过 k」的窗口。
 *       right 一直右移，遇到 0 就让 zeroCount + 1；
 *       一旦 zeroCount > k，说明窗口不合法，收缩 left，
 *       直到从左边吐出一个 0（zeroCount 减回去）。
 *       每次收缩到合法后，窗口长度 right - left + 1 就是一个可行答案，取最大。
 * 复杂度：时间 O(n)（每个元素最多进出窗口一次），空间 O(1)
 *
 * 坑点：
 * - 收缩必须用 while 而不是 if：一次可能需要连续吐出多个元素才能丢掉下一个 0。
 * - 与 LC3 的区别：LC3 维护的是「字符是否重复」，本题维护的是「0 的个数」，
 *   但套路完全一样 —— 窗口内状态不合法就收缩 left。
 * - 能用滑动窗口的前提是元素非负（本题只有 0/1）。若数组含负数，需改前缀和 + 哈希。
 */
public class LC1004 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        int maxLen = 0;     // 答案：最长合法窗口长度
        int zeroCount = 0;  // 当前窗口内 0 的个数（也就是已经用掉的翻转次数）
        int left = 0;       // 窗口左边界

        for (int right = 0; right < nums.length; right++) {
            // 右边界扩张：遇到 0 就消耗一次翻转机会
            if (nums[right] == 0) {
                zeroCount++;
            }
            // 翻转次数超了，收缩左边界直到丢掉一个 0
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            // 此时窗口一定合法，更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        System.out.println(maxLen);
    }
}
