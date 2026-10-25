package 阶段四.DFS;

/**
 * 200. 岛屿数量
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出：3
 *
 */
public class LC200DFS {
    public static void main(String[] args) {
        //* DFS思路：
        // * 遍历网格每一个位置；发现一块未访问的陆地，岛屿数+1；
        // * 启动DFS递归，把和它连通的所有陆地全部标记为已访问；
        // * 递归往上下左右四个方向深入搜索，碰到边界、水、已访问节点就返回。
        // 测试用例，预期输出 3
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int n = numIslands(grid);
        System.out.println(n);
    }
    // 方向数组：下、上、右、左 四个方向（只走横竖，不走对角线）
    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int numIslands(char[][] grid) {

        int m = grid.length;        // m：总行数
        int n = grid[0].length;     // n：总列数
        boolean[][] flag = new boolean[m][n]; // 访问标记数组，true代表已经搜索过
        int count = 0;              // 岛屿计数器
        // 双层循环，遍历网格上每一个格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当前点：未访问 并且 是陆地'1'，说明找到新岛屿
                if (!flag[i][j] && grid[i][j] == '1') {
                    // 符合加一
                    count++;
                    // 启动DFS，把这块岛屿所有连通陆地全部标记访问
                    dfs(i, j, m, n, grid, flag);
                }
            }
        }
        return count;
    }

    public static void dfs(int i, int j, int m, int n, char[][] grid, boolean[][] flag) {
        // 否决条件
        if (i < 0 || i >= m || j < 0 || j >= n || flag[i][j] || grid[i][j] != '1') {
            return;
        }
        flag[i][j] = true;
        for (int[] dir : dirs) {
            // 当前点向下搜素一直到搜素不到，四个方向分别进行深入搜素
            int nx = i + dir[0];
            int ny = j + dir[1];
            dfs(nx, ny, m, n, grid, flag);
        }
    }
}
