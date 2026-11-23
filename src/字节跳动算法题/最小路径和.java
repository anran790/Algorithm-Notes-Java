package 字节跳动算法题;

/**
 * 最小路径和
 * 求二位矩阵中最小路径和, 从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 限定往右或者往下走。
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 **/
public class 最小路径和 {
    public static void main(String[] args) {
        int[][] arr = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minSum(arr));
    }

    public static int minSum(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = arr[0][0];
        // 第一列：只能从上往下
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        // 第一行：只能从左往右
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + arr[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }
}
