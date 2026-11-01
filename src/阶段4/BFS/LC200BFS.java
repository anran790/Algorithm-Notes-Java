package 阶段4.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC200 岛屿数量（BFS 解法）
 *
 * 难度：中等    标签：BFS / 连通块
 *
 * 题目：'1' 为陆地、'0' 为水，岛屿由上下左右相邻的陆地连成（不含对角线），
 *       求网格中岛屿的数量。
 * 示例：[['1','1','0'],['1','1','0'],['0','0','1']]  →  2
 *
 * 思路：遍历每个格子，遇到「未访问的陆地」就说明发现一座新岛，计数 +1，
 *       然后以它为起点做 BFS，把整块连通的陆地全部标记为已访问。
 *       每个格子只会入队一次，所以整体是线性的。
 * 复杂度：时间 O(m×n)，空间 O(m×n)
 *
 * 坑点：
 * - 入队时立刻标记 visited，出队才标记会导致重复入队。
 * - 判断条件是「越界 / 是水 / 已访问」三者任一即跳过，顺序不影响结果但要写全。
 * - 只需要 4 个方向，不要写成 8 方向（对角线不算连通）。
 * - 与 DFS 版（LC200DFS）等价，区别只是扩散方式：BFS 用队列，DFS 用递归。
 */
public class LC200BFS {
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
        int m = grid.length;      // 行数
        int n = grid[0].length;   // 列数
        // 访问标记，避免同一块陆地被重复计数
        boolean[][] flag = new boolean[m][n];
        // BFS 队列，存放待扩散的坐标 {x, y}
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;             // 岛屿数量

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 已访问过的、或者是水的，跳过
                if (flag[i][j] || grid[i][j] == '0') {
                    continue;
                }

                // 找到一块全新的陆地：岛屿数 +1
                count++;
                // 入队并标记，开始 BFS 扩散
                flag[i][j] = true;
                queue.add(new int[]{i, j});

                // ---------- BFS 核心循环 ----------
                while (!queue.isEmpty()) {
                    // 出队（先进先出，这是广度优先的关键）
                    int[] cur = queue.poll();
                    int x = cur[0];
                    int y = cur[1];

                    for (int[] dir : dirs) {
                        int nx = x + dir[0];
                        int ny = y + dir[1];
                        // 不越界 && 是陆地 && 没访问过，才继续扩散
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n
                                && grid[nx][ny] == '1' && !flag[nx][ny]) {
                            flag[nx][ny] = true;      // 入队前就标记
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
                // BFS 结束：这一整块连通陆地全部标记完毕
            }
        }
        return count;
    }
}
