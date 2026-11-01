package 阶段6.A维DP;

/**
 * LC198 打家劫舍
 *
 * 难度：中等    标签：动态规划（线性 DP）
 *
 * 题目：沿街房屋金额存在非负数组 nums 中，相邻房屋不能同时偷，求一夜能偷到的最高金额。
 * 示例：nums = [1,2,3,1]    →  4（偷 1 号和 3 号）
 *       nums = [2,7,9,3,1]  →  12（偷 1、3、5 号）
 *
 * 思路：每间房只有「偷」和「不偷」两种选择，取金额更大的那个：
 *       不偷 i → 最大金额就是 dp[i-1]
 *       偷 i   → i-1 不能偷，只能拿 dp[i-2] 再加上 nums[i]
 *       dp[i] = max(dp[i-1], dp[i-2] + nums[i])
 * 复杂度：时间 O(n)，空间 O(n)（可优化为两个滚动变量 O(1)）
 *
 * 坑点：
 * - 初始化最容易错：dp[1] 必须是 max(nums[0], nums[1])，不是 nums[1]。
 * - 递推要从 i = 2 开始，否则 dp[i-2] 会越界。
 * - 只有一间房时 dp[1] 会越界，必须提前返回。
 * - 空数组题目不出现（n >= 1），若考虑则需额外判断。
 */
public class LC198 {
    public static void main(String[] args) {
        // 测试用例，预期输出 4
        System.out.println(rob(new int[]{1, 2, 3, 1}));
    }

    public static int rob(int[] nums) {
        // 只有一间房，没得选
        if (nums.length == 1) {
            return nums[0];
        }

        // dp[i]：考虑前 i+1 间房（下标 0..i）能偷到的最大金额
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 两间房时只能二选一，取金额大的
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            // 不偷 i：dp[i-1]；偷 i：dp[i-2] + nums[i]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
