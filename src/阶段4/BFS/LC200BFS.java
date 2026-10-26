package 阶段4.BFS;

import java.util.LinkedList;
import java.util.Queue;

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
 */
public class LC200BFS {
    public static void main(String[] args) {
        // 测试用例，预期输出 3
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int n = numIslands(grid);
        System.out.println(n);
    }

    // 方向数组：下、上、右、左 四个方向（只走横竖，不走对角线）
    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // 基础广度优先搜素类型
    public static int numIslands(char[][] grid) {
        // m：行数，n：列数
        int m = grid.length;
        int n = grid[0].length;
        // flag标记数组：记录该坐标是否已经访问过，避免重复搜索
        boolean[][] flag = new boolean[m][n];
        // BFS核心：队列，用来存放待遍历的坐标点 {x,y}
        Queue<int[]> queue = new LinkedList<>();
        // 岛屿计数器
        int count = 0;

        // 两层循环：遍历网格中每一个格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果这个点已经访问过 或者 是水'0'，直接跳过，不用处理
                if (flag[i][j] || grid[i][j] == '0') {
                    continue;
                }

                // 走到这里说明：找到一块全新未访问的陆地，岛屿数量+1
                if (grid[i][j] == '1') {
                    count++;
                }
                // 标记当前点已经访问，加入队列，开启BFS扩散
                flag[i][j] = true;
                queue.add(new int[]{i, j});

                // ========== BFS广度优先搜索核心循环 ==========
                // 队列不为空，代表还有连通陆地没有遍历
                while (!queue.isEmpty()) {
                    // 队首元素出队（先进先出，广度优先的关键）
                    int[] que = queue.poll();
                    int x = que[0];
                    int y = que[1];

                    // 遍历上下左右四个方向
                    for (int[] dir : dirs) {
                        // 计算相邻格子坐标 nx, ny
                        int nx = x + dir[0];
                        int ny = y + dir[1];
                        // 条件：坐标不越界 && 是陆地'1' && 没有访问过
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1' && !flag[nx][ny]) {
                            flag[nx][ny] = true; // 立刻标记访问，防止重复入队
                            queue.add(new int[]{nx, ny}); // 新陆地入队，后续继续向外扩散
                        }
                    }
                }
                // BFS结束：当前这一整块连通的陆地全部被标记访问完毕
            }
        }
        return count;
    }
}
