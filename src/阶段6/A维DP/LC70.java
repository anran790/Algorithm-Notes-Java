package 阶段6.A维DP;

/**
 * LC70 爬楼梯
 *
 * 难度：简单    标签：动态规划（线性 DP）
 *
 * 题目：每次可以爬 1 阶或 2 阶，问爬到 n 阶共有多少种不同的方法。
 * 示例：n = 2  →  2（1+1、2）
 *       n = 3  →  3（1+1+1、1+2、2+1）
 *
 * 思路：最后一步只有两种可能 —— 从 n-1 跨 1 阶，或从 n-2 跨 2 阶，
 *       所以 dp[n] = dp[n-1] + dp[n-2]，本质就是斐波那契数列。
 * 复杂度：时间 O(n)，空间 O(n)（可优化到 O(1)，只留两个滚动变量）
 *
 * 坑点：
 * - 这是「计数」类 DP，不是「最值」类，所以是相加而不是取 max/min。
 * - 初始化要对：dp[1] = 1、dp[2] = 2，不能都设成 1。
 * - n = 1 或 2 要提前返回，否则 dp 数组初始化时会漏掉这些情况。
 * - 空间可以优化：只保留前两项，用两个变量滚动即可，是面试常见的追问。
 */
public class LC70 {
    public static void main(String[] args) {
        // 测试 n = 2，预期输出 2
        System.out.println(climbStairs(2));
    }

    /**
     * @param n 台阶总数
     * @return 爬到 n 阶的方案数
     */
    public static int climbStairs(int n) {
        // 边界：1 阶只有 1 种，2 阶有 2 种
        if (n == 1 || n == 2) {
            return n;
        }

        // dp[i]：爬到第 i 阶的方案数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            // 最后一步跨 1 阶（来自 i-1）+ 跨 2 阶（来自 i-2）
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
