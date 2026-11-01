package 阶段6.A维DP;

import java.util.Arrays;

/**
 * LC213 打家劫舍 II
 *
 * 难度：中等    标签：动态规划（环形 DP）
 *
 * 题目：房屋围成一圈（首尾相邻），相邻房屋不能同时偷，求能偷到的最高金额。
 * 示例：nums = [2,3,2]  →  3（偷中间那间，首尾不能同时偷）
 *       nums = [1,2,3,1] →  4
 *
 * 思路：环形带来的唯一麻烦是「第一个和最后一个不能同时选」。
 *       于是把环拆成两个线性问题，分别求最优再取最大：
 *       情况 a：不偷最后一间 → 考察 [0, n-2]
 *       情况 b：不偷第一间   → 考察 [1, n-1]
 *       这样就把 LC213 退化成了两次 LC198。
 * 复杂度：时间 O(n)，空间 O(n)
 *
 * 坑点：
 * - 只有一间房时要单独返回，否则 copyOfRange 会切出空数组，helper 里取 dp[0] 越界。
 * - 两个子区间都「不能同时包含首尾」，这是拆分的核心，别写成 [0,n-2] 和 [0,n-1]。
 * - 取最大值用 Math.max，不要漏掉两种情况的比较。
 */
public class LC213 {
    public static void main(String[] args) {
        // 测试用例，预期输出 3
        System.out.println(rob(new int[]{2, 3, 2}));
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        // 边界：只有一间房，直接偷
        if (n == 1) {
            return nums[0];
        }

        // 情况 a：不偷最后一间，考察 [0, n-2]
        int a = helper(Arrays.copyOfRange(nums, 0, n - 1));
        // 情况 b：不偷第一间，考察 [1, n-1]
        int b = helper(Arrays.copyOfRange(nums, 1, n));
        // 两种方案取最大
        return Math.max(a, b);
    }

    /**
     * 直线版打家劫舍（即 LC198）。
     *
     * @param arr 首尾不相连的房屋金额
     * @return 该区间能偷到的最大金额
     */
    public static int helper(int[] arr) {
        // 只有一间房，直接偷
        if (arr.length == 1) {
            return arr[0];
        }

        // dp[i]：考虑下标 0..i 的房屋能偷到的最大金额
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            // 不偷 i 或 偷 i（则 i-1 不能偷）
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
        }
        return dp[arr.length - 1];
    }
}
