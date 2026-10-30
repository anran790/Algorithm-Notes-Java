package 阶段6.A维DP;

import java.util.Arrays;

/**
 * 213. 打家劫舍 II
 * 已解答
 * 中等
 * 相关标签：动态规划
 * <p>
 * 题意：
 * 房屋围成一圈，nums[0] 和 nums[n-1] 相邻，不能同时偷；
 * 相邻两间房子不能同时偷，求能偷到的最大金额。
 * <p>
 * 核心思路：
 * 环形的难点：第一个和最后一个不能一起选
 * 拆成两个线性打家劫舍（LC198）问题，取最大值：
 * 情况a：不偷最后一间 → 区间 [0, n-2]
 * 情况b：不偷第一间 → 区间 [1, n-1]
 * 答案 = max(a,b)
 * <p>
 * helper函数：处理直线排列的房屋（就是LC198逻辑）
 * dp[i]含义：arr[0]~arr[i]范围内，能偷的最大金额
 * 两种选择：
 * 1. 不偷arr[i]：dp[i-1]
 * 2. 偷arr[i]：不能偷i-1，dp[i-2] + arr[i]
 * dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i])
 */
public class LC213 {
    public static void main(String[] args) {
        // 测试用例 [2,3,2]，预期输出3
        System.out.println(rob(new int[]{2, 3, 2}));
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        // 边界：只有一间房，直接偷
        if (nums.length == 1) {
            return nums[0];
        }

        // 子问题1：不取最后一个元素 [0, n-2]
        int a = helper(Arrays.copyOfRange(nums, 0, n - 1));
        // 子问题2：不取第一个元素 [1, n-1]
        int b = helper(Arrays.copyOfRange(nums, 1, n));
        // 两种方案取最大
        int res = Math.max(a, b);
        return res;
    }

    /**
     * 直线版打家劫舍（LC198）
     *
     * @param arr 直线排列房屋数组，首尾不相连
     * @return 该区间能偷的最大金额
     */
    public static int helper(int[] arr) {
        // 只有一间房，直接返回金额
        if (arr.length == 1) {
            return arr[0];
        }
        // dp数组：dp[i]代表 arr[0]~arr[i] 的最大偷窃金额
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        // 从第3间（下标i=2）开始遍历
        for (int i = 2; i < arr.length; i++) {
            // 不偷当前：dp[i-1]；偷当前：dp[i-2]+arr[i]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
        }
        // 整个区间最优解
        return dp[arr.length - 1];
    }
}
