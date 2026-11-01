package 阶段6.B背包DP;

/**
 * LC322 零钱兑换
 *
 * 难度：中等    标签：完全背包
 *
 * 题目：给定硬币面额数组 coins 和总金额 amount，每种硬币数量无限，
 *       求凑出 amount 所需的最少硬币个数；凑不出返回 -1。
 * 示例：coins = [1,2,5], amount = 11  →  3（5+5+1）
 *       coins = [2],       amount = 3   →  -1
 *
 * 思路：完全背包求最小值。
 *       dp[i]：凑出金额 i 所需的最少硬币数。
 *       对每个金额 i，尝试最后一枚用哪种硬币：
 *       dp[i] = min(dp[i], dp[i - coin] + 1)
 *       其中 dp[i-coin] 是已经算好的子问题最优解，+1 代表再添这一枚。
 * 复杂度：时间 O(amount × coins.length)，空间 O(amount)
 *
 * 坑点：
 * - 初始化用 amount + 1 当作「无穷大」：因为最多用 amount 枚 1 元硬币，
 *   所以 amount + 1 一定凑不出来，可以安全地表示不可达。
 * - dp[0] = 0：凑 0 元不需要任何硬币，这是整个递推的起点。
 * - 内层循环正序遍历（金额从小到大），这正是「完全背包」与 01 背包的区别：
 *   正序允许同一枚硬币被重复使用。
 * - 最后要判断 dp[amount] 是否仍大于 amount，是则说明无解。
 */
public class LC322 {
    public static void main(String[] args) {
        // 用例1：预期 3
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        // 用例2：预期 -1
        System.out.println(coinChange(new int[]{2}, 3));
        // 用例3：预期 0
        System.out.println(coinChange(new int[]{1}, 0));
    }

    public static int coinChange(int[] coins, int amount) {
        // dp[i]：凑出金额 i 的最少硬币数
        int[] dp = new int[amount + 1];

        // 初始化：除 dp[0] 外全部标记为「不可达」
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        // dp[0] 默认 0：凑 0 元需要 0 枚硬币

        // 外层遍历金额（正序）
        for (int i = 1; i <= amount; i++) {
            // 内层尝试每一种硬币
            for (int coin : coins) {
                // 硬币面额不能超过当前金额，否则 i-coin 为负
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 还是初始值说明凑不出来
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
