package 阶段6.A维DP;

/**
 * LC746 使用最小花费爬楼梯
 *
 * 难度：简单    标签：动态规划（线性 DP）
 *
 * 题目：cost[i] 表示从第 i 阶向上爬需要支付的费用，支付后可以爬 1 阶或 2 阶。
 *       你可以从下标 0 或下标 1 出发，求到达楼顶（下标 n 的位置）的最小花费。
 * 示例：cost = [10,15,20]   →  15（从 1 出发，花 15 直接跨 2 阶到顶）
 *       cost = [1,100,1,1,1,100,1,1,100,1]  →  6
 *
 * 思路：dp[i] 定义为「站在第 i 阶、并准备向上走」时的累计最小花费（含 cost[i]）。
 *       到达 i 阶只能从 i-1 跨 1 步或 i-2 跨 2 步，取两者较小值再加上 cost[i]：
 *       dp[i] = cost[i] + min(dp[i-1], dp[i-2])
 *       楼顶在 n 位置（不在 cost 数组里，不需要付费），
 *       所以答案是 min(dp[n-1], dp[n-2])。
 * 复杂度：时间 O(n)，空间 O(n)（可优化为 O(1)）
 *
 * 坑点：
 * - 楼顶不是一个台阶，不付 cost，所以最后取 min 而不是 dp[n-1]。
 * - dp[0] / dp[1] 都要含自己的 cost，因为「站在这里准备走」就意味着已经付过这笔钱。
 * - 与 LC70 的区别：那题数方案数（相加），本题求最小花费（加 cost 后取 min）。
 * - 题目保证 n >= 2，所以 dp[1] 不会越界；若 n 可能为 1，需要单独处理。
 */
public class LC746 {
    public static void main(String[] args) {
        // 测试用例，预期输出 15
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // dp[i]：站上第 i 阶并准备继续向上走时的最小花费
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            // 从 i-1 跨 1 阶上来，或从 i-2 跨 2 阶上来，取花费小的
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        // 楼顶不收费：从最后一阶跨 1 步，或从倒数第二阶跨 2 步
        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
