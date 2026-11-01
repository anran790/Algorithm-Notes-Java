package 阶段6.C网格路径DP;

/**
 * LC62 不同路径
 *
 * 难度：中等    标签：网格 DP
 *
 * 题目：m × n 的网格，机器人从左上角出发，每次只能向右或向下走一步，求到右下角的不同路径数。
 * 示例：m = 3, n = 7  →  28
 *       m = 3, n = 2  →  3
 *
 * 思路：dp[i][j] 表示从起点走到 (i,j) 的路径数。
 *       由于只能从上方或左方过来：
 *       dp[i][j] = dp[i-1][j] + dp[i][j-1]
 *       第一行只能一直向右、第一列只能一直向下，所以它们的路径数都是 1（边界初始化）。
 * 复杂度：时间 O(m×n)，空间 O(m×n)（可滚动数组优化到 O(n)）
 *
 * 坑点：
 * - 边界初始化别漏：第一行和第一列必须显式填 1，否则递推结果全为 0。
 * - 循环从 i=1、j=1 开始，第 0 行第 0 列已经初始化。
 * - 用 long 存中间结果，防止较大网格时 int 溢出；返回时再转回 int。
 * - 与 LC63（带障碍物）的区别：那题需要在遇到障碍时把 dp 置 0。
 */
public class LC62 {
    public static void main(String[] args) {
        // 测试用例：3 行 7 列，预期输出 28
        System.out.println(uniquePaths(3, 7));
    }

    /**
     * @param m 行数
     * @param n 列数
     * @return 从左上角到右下角的路径总数
     */
    public static int uniquePaths(int m, int n) {
        // dp[i][j]：走到 (i,j) 的路径数，long 防溢出
        long[][] dp = new long[m][n];

        // 第一列：只能一直向下走，每种位置只有 1 条路径
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 第一行：只能一直向右走，每个位置只有 1 条路径
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 其余格子：从上方来 + 从左方来
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 右下角即答案
        return (int) dp[m - 1][n - 1];
    }
}
