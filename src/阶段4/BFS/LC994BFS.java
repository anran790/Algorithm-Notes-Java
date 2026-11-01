package 阶段4.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC994 腐烂的橘子
 *
 * 难度：中等    标签：多源 BFS / 分层遍历
 *
 * 题目：网格中 0 是空格、1 是新鲜橘子、2 是腐烂橘子。每分钟，腐烂橘子会让上下左右相邻的
 *       新鲜橘子腐烂。求所有橘子都腐烂所需的最少分钟数；若有橘子永远烂不掉，返回 -1。
 * 示例：[[2,1,1],[1,1,0],[0,1,1]]  →  4
 *       [[2,1,1],[0,1,1],[1,0,1]]  →  -1
 *
 * 思路：多源 BFS。所有腐烂橘子同时作为起点入队，一分钟 = 一层扩散。
 *       1. 先遍历一遍：腐烂的入队，同时统计新鲜橘子总数；
 *       2. 逐层扩散，每层把能感染的新鲜橘子标记为腐烂并让 freshNum 减 1；
 *       3. 只有当这一层真的感染了橘子，分钟数才 +1；
 *       4. 结束后 freshNum 归零则返回分钟数，否则说明有孤岛，返回 -1。
 * 复杂度：时间 O(m×n)，空间 O(m×n)
 *
 * 坑点：
 * - 必须用「多源」：把所有腐烂橘子一次性入队，而不是对每个腐烂橘子单独 BFS。
 * - 分钟数不能每轮都 +1，要用 flag 判断本层是否真的产生了感染，
 *   否则最后一层会多算一分钟。
 * - 一开始就判断 freshNum == 0 直接返回 0，否则会多算 1 分钟。
 * - 感染后立刻把 grid 改成 2，天然起到 visited 的作用，不需要额外的标记数组。
 */
public class LC994BFS {
    // 方向数组：下、上、左、右
    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        // 测试样例，预期输出 4
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int minutes = 0;   // 已过去的分钟数
        int freshNum = 0;  // 剩余新鲜橘子数

        // 第 1 步：收集所有腐烂橘子作为多源起点，同时数清新鲜橘子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshNum++;
                }
            }
        }

        // 本来就没有新鲜橘子，0 分钟即完成
        if (freshNum == 0) {
            return 0;
        }

        // 第 2 步：分层 BFS，一层代表一分钟的扩散
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean infected = false;  // 本层是否感染了新的橘子

            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    // 越界判断 + 必须是新鲜橘子
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        freshNum--;
                        grid[nx][ny] = 2;   // 立刻变腐烂，兼作 visited 标记
                        queue.add(new int[]{nx, ny});
                        infected = true;
                    }
                }
            }

            // 只有真的感染了，才算过去一分钟
            if (infected) {
                minutes++;
            }
        }

        // 还有新鲜橘子说明被空格隔开，永远烂不掉
        return freshNum == 0 ? minutes : -1;
    }
}
