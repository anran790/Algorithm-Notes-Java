package 阶段6.A维DP;

/**
 * 70. 爬楼梯
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * 动态规划思路：
 * dp[i] 代表爬到第 i 阶楼梯的方案总数
 * 转移方程：dp[i] = dp[i-1] + dp[i-2]
 * 含义：到达i阶，最后一步要么跨1阶（前面到i-1），要么跨2阶（前面到i-2）
 * 初始：dp[1]=1，dp[2]=2
 */
public class LC70 {
    public static void main(String[] args) {
        // 测试 n=2，预期输出2
        System.out.println(climbStairs(2));
    }

    /**
     * @param n 台阶总数
     * @return 爬到n阶的方案数量
     */
    public static int climbStairs(int n) {
        // base case：1阶、2阶直接返回结果
        if (n == 1 || n == 2) {
            return n;
        }
        // dp数组，arr[i]：爬到第i阶的方案数
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        // 从第3阶开始递推计算
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }
}
