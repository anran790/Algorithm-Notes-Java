package 阶段6.B背包DP;

/**
 * LC416 分割等和子集
 *
 * 难度：中等    标签：01 背包
 *
 * 题目：给定只含正整数的非空数组 nums，判断能否把它分成两个元素和相等的子集。
 * 示例：nums = [1,5,11,5]  →  true（[1,5,5] 与 [11]）
 *       nums = [1,2,3,5]   →  false
 *
 * 思路：先把问题转化，再套 01 背包。
 *       1. 总和 sum 为奇数 → 不可能平分，直接 false；
 *       2. sum 为偶数 → 目标变成「能否挑出若干数，和恰好等于 sum/2」。
 *       把 target = sum/2 看作背包容量，每个数字是一件物品（只能用一次），
 *       问题就变成「能否恰好装满背包」。
 *       dp[j]：能否用已遍历过的数字凑出和 j。
 *       dp[j] = dp[j] || dp[j - n]，不选 n 或选 n。
 * 复杂度：时间 O(n × target)，空间 O(target)
 *
 * 坑点：
 * - 内层容量必须倒序。正序会让同一数字在同一轮被重复使用（相当于完全背包），结果偏大。
 * - dp[0] = true 是隐含条件：和为 0 时什么都不选即可，也是单个数字恰好等于 target 的来源。
 * - 总和一半用 sum/2，先判偶数再除，避免精度与语义问题。
 * - target 为 0 的情况（数组有 0）题目不会出现，本题元素均为正整数。
 */
public class LC416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        // 先求总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和为奇数，无法平分
        if (sum % 2 != 0) {
            return false;
        }

        // 目标：能否凑出总和的一半
        int target = sum / 2;
        // dp[j]：能否凑出和为 j
        boolean[] dp = new boolean[target + 1];
        // 和为 0 天然可达（一个都不选）
        dp[0] = true;

        // 外层：逐个物品（每个数字只能用一次）
        for (int num : nums) {
            // 内层：容量倒序，保证本轮不会重复使用同一个数字
            // 例：num = 5 时，若正序先算出 dp[5]=true，后面 j=10 又拿刚更新的 dp[5] 推 dp[10]，
            // 相当于用了两次 5，这是错的
            for (int j = target; j >= num; j--) {
                // 不选 num（沿用 dp[j]）或 选 num（由 dp[j-num] 推出）
                dp[j] = dp[j] || dp[j - num];
            }
        }

        // 能凑出 target，另一半自然也是 target
        return dp[target];
    }
}
