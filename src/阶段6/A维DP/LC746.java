package 阶段6.A维DP;

/**
 * 746. 使用最小花费爬楼梯
 * 已解答
 * 简单
 * 相关标签：动态规划
 * <p>
 * 题意：
 * cost[i]：站在第i台阶往上爬，需要付的费用。付完钱，可以爬1阶或者2阶。
 * 起点：可以选择从下标0 或者下标1台阶出发。
 * 目标：到达楼梯顶部（top位置，下标等于 cost.length，不在cost数组里面）
 * <p>
 * dp定义：
 * dp[i]：到达【第i个台阶】时，并且要从i台阶向上走的最小花费（必须要付cost[i]）
 * 转移方程：
 * 想要到i台阶，只能从i-1跳1步上来，或者i-2跳2步上来
 * dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
 * <p>
 * 最后要跳到楼顶：楼顶在 cost.length 位置
 * 到达楼顶有两条路径：
 * 1. 在 cost.length-1 台阶，付完钱跳1阶上去
 * 2. 在 cost.length-2 台阶，付完钱跳2阶上去
 * 所以答案 = min(dp[n-1], dp[n-2])
 */
public class LC746 {
    public static void main(String[] args) {
        // 测试用例 [10,15,20]，预期输出15
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        // 从0台阶出发，要往上走就要付cost[0]
        dp[0] = cost[0];
        // 从1台阶出发，要往上走就要付cost[1]
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        // 最后从最后一阶或者倒数第二阶跳到顶部，取较小花费
        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
