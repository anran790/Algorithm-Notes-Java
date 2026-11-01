package 阶段2;

/**
 * LC209 长度最小的子数组
 *
 * 难度：中等    标签：滑动窗口（不定长，求最小）
 *
 * 题目：给定正整数数组 nums 和正整数 target，找出和 ≥ target 的长度最小的连续子数组，返回其长度；
 *       不存在则返回 0。
 * 示例：target = 7, nums = [2,3,1,2,4,3]  →  2（子数组 [4,3]）
 *
 * 思路：求「最小」窗口，所以用「先扩张、再拼命收缩」的节奏。
 *       right 一直右移累加 sum；一旦 sum >= target 就进入 while，
 *       记录当前窗口长度并尝试吐出左边的元素，看还能不能更短，
 *       直到 sum < target 再让 right 继续前进。
 * 复杂度：时间 O(n)（每个元素最多进出窗口一次），空间 O(1)
 *
 * 坑点：
 * - 本题是「求最小」，所以收缩用 while 且收缩到不满足条件为止；
 *   LC3 那种「求最大」是收缩到满足条件为止，两者方向相反，别记混。
 * - 答案初始化为 Integer.MAX_VALUE，最后要判断是否更新过，没更新过返回 0。
 * - 前提是数组元素全为正数，全正才能保证「窗口越长和越大」的单调性。
 */
public class LC209 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

        int n = nums.length;
        int minLen = Integer.MAX_VALUE;  // 最短长度，先设为「不可能的大值」
        int sum = 0;                     // 当前窗口内元素之和
        int left = 0;                    // 窗口左边界

        for (int right = 0; right < n; right++) {
            // 扩张：把右边界元素纳入窗口
            sum += nums[right];

            // 只要还满足条件，就尝试继续收缩，看能不能更短
            while (sum >= target) {
                // 先记录当前合法窗口的长度
                minLen = Math.min(minLen, right - left + 1);
                // 吐出左边界元素后左指针右移，窗口变短
                sum -= nums[left];
                left++;
            }
        }

        // 一次都没满足过，说明不存在这样的子数组
        if (minLen < Integer.MAX_VALUE) {
            System.out.println(minLen);
        } else {
            System.out.println(0);
        }
    }
}
