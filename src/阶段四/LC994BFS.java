package 阶段四;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. 腐烂的橘子
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 *
 * 示例 1：
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * 示例 3：
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 * 思路：多源BFS分层遍历
 * 1. 先把所有初始腐烂橘子入队，同时统计新鲜橘子总数
 * 2. 逐层向外感染，flag标记本层是否产生新感染；只有感染成功才时间+1
 * 3. BFS结束后，如果还有新鲜橘子剩余，返回-1
 */
public class LC994BFS {
    public static void main(String[] args) {
        // 测试样例，预期输出：4
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int ans = orangesRotting(grid);
        System.out.println(ans);
    }

    // 方向数组：下、上、左、右
    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;        // 记录耗时（分钟）
        int freshNum = 0;     // 新鲜橘子总数

        // 1.遍历网格：收集所有腐烂橘子入队 + 统计新鲜橘子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    freshNum++;
                }
            }
        }

        // 没有新鲜橘子，直接返回0
        if (freshNum == 0) {
            return 0;
        }

        // 2.多源分层BFS，一层代表一轮扩散
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false; // 标记本层是否成功感染新鲜橘子

            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                // 四个方向尝试感染邻居
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    // 坐标合法，并且是新鲜橘子
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        freshNum--;
                        grid[nx][ny] = 2;   // 标记为腐烂，防止重复入队
                        queue.add(new int[]{nx, ny});
                        flag = true;        // 本轮产生感染
                    }
                }
            }
            // 只有本轮成功感染橘子，时间才 +1
            if (flag) {
                count++;
            }
        }
        // 新鲜橘子全部烂完返回时间，否则返回-1
        return freshNum == 0 ? count : -1;
    }
}
