package 阶段4.DFS;

/**
 * LC200 岛屿数量（DFS 解法）
 *
 * 难度：中等    标签：DFS / 连通块
 *
 * 题目：'1' 为陆地、'0' 为水，岛屿由上下左右相邻的陆地连成，求岛屿数量。
 * 示例：[['1','1','0'],['1','1','0'],['0','0','1']]  →  2
 *
 * 思路：遍历每个格子，遇到「未访问的陆地」就计数 +1，并启动 DFS 递归，
 *       把与它连通的整块陆地全部标记为已访问。
 *       DFS 的作用就是「淹没」一整座岛，避免后续重复计数。
 * 复杂度：时间 O(m×n)，空间 O(m×n)（递归栈最坏情况是整个网格）
 *
 * 坑点：
 * - 递归终止条件要写全：越界 / 已访问 / 不是陆地，三者任一就 return。
 * - 标记必须在递归之前做，否则会无限递归（A 调用 B、B 又调用 A）。
 * - 网格很大时（如 1000×1000）递归可能栈溢出，此时应改用 BFS 版 LC200BFS。
 * - 与 BFS 版结果完全一致，选择哪种取决于是否担心递归深度。
 */
public class LC200DFS {
    // 方向数组：下、上、右、左（只走横竖，不走对角线）
    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        // 测试用例，预期输出 3
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int m = grid.length;      // 总行数
        int n = grid[0].length;   // 总列数
        boolean[][] flag = new boolean[m][n];  // true 表示已经搜索过
        int count = 0;                         // 岛屿计数器

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 未访问 且 是陆地，说明发现一座新岛
                if (!flag[i][j] && grid[i][j] == '1') {
                    count++;
                    // 把整座岛淹没（全部标记为已访问）
                    dfs(i, j, m, n, grid, flag);
                }
            }
        }
        return count;
    }

    /**
     * 从 (i,j) 出发，把连通的陆地全部标记为已访问。
     */
    public static void dfs(int i, int j, int m, int n, char[][] grid, boolean[][] flag) {
        // 终止条件：越界 / 已访问 / 不是陆地
        if (i < 0 || i >= m || j < 0 || j >= n || flag[i][j] || grid[i][j] != '1') {
            return;
        }
        // 先标记，再递归，防止重复访问和无限递归
        flag[i][j] = true;

        // 向四个方向继续深入
        for (int[] dir : dirs) {
            int nx = i + dir[0];
            int ny = j + dir[1];
            dfs(nx, ny, m, n, grid, flag);
        }
    }
}
