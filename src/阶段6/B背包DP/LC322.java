package 阶段6.B背包DP;

/**
 * LC322 零钱兑换
 * 中等
 * 题目：
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少 的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 每种硬币数量无限（完全背包）
 *
 * 状态定义：
 * dp[i]：凑出金额 i 需要的最少硬币数量
 *
 * 转移方程：
 * dp[i] = Math.min(dp[i], dp[i - coin] + 1)
 * 含义：
 * dp[i-coin] 是凑 i-coin 的最优解（已经提前算好）
 * +1 代表再拿一枚 coin 面值的硬币，就能凑出 i
 * min 是在多个硬币方案里，保留硬币数量最小的那个
 *
 * 初始化：
 * dp[0] 默认就是0，凑0元不需要硬币
 * dp[1 ~ amount] 初始化为 amount+1（一个不可能达到的大数，标记暂时无法凑出）
 * 最多全用1元硬币也只需要 amount 枚，amount+1一定达不到
 */
public class LC322 {
    public static void main(String[] args) {
        // 测试用例1
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println(coinChange(coins1, amount1)); // 预期 3

        // 测试用例2
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(coinChange(coins2, amount2)); // 预期 -1

        // 测试用例3
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println(coinChange(coins3, amount3)); // 预期 0
    }

    public static int coinChange(int[] coins, int amount) {
        // dp[i]：凑金额i最少硬币数
        int[] dp = new int[amount + 1];

        // 初始化：从1开始全部填充为大数amount+1，表示暂时凑不出来
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        // dp[0]默认就是0，不用赋值：金额0，0枚硬币

        // 遍历每一个需要凑的金额，从小到大
        for (int i = 1; i <= amount; i++) {
            // 尝试每一种硬币
            for (int coin : coins) {
                // 硬币面额不能超过当前金额，才可以选这个硬币
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // 如果还是大于amount，说明没有可行方案
        if (dp[amount] > amount) {
            return -1;
        } else {
            return dp[amount];
        }
    }
}
