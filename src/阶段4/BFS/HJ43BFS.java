package 阶段4.BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * HJ43 迷宫问题
 *
 * 难度：中等（华为机考高频）    标签：BFS / 路径还原
 *
 * 题目：h 行 w 列的网格，0 是通路、1 是墙，四周都是墙。从 (0,0) 走到 (h-1,w-1)，
 *       只能上下左右移动，输出一条可行路径（保证唯一）。
 * 示例：5×5 迷宫，输出 (0,0) → (1,0) → … → (4,4) 的完整坐标序列。
 *
 * 思路：BFS + 前驱数组还原路径。
 *       BFS 保证「第一次到达某点时的步数就是最短步数」；
 *       用 pre[x][y] 记录 (x,y) 是从哪个格子走过来的，
 *       到达终点后从终点沿 pre 反向回溯，再 reverse 得到正向路径。
 * 复杂度：时间 O(h×w)，空间 O(h×w)
 *
 * 坑点：
 * - 必须在「入队时」就标记 visited（flag[nx][ny] = true），
 *   如果等出队才标记，同一个点会被多个邻居重复入队，队列会爆。
 * - 路径还原依赖 pre 数组，起点 (0,0) 的 pre 是 null，回溯到它就停止。
 * - 本题保证路径唯一；若有多个终点或多条路径，BFS 得到的是最短的那条。
 * - Node 是包级类（非 public），若同包其他文件也要用，注意命名冲突。
 */
public class HJ43BFS {
    // 四个方向：下、上、右、左
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        // 正式提交时改为从标准输入读取，取消下面这段注释即可
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // int m = sc.nextInt();
        // int[][] ans = new int[n][m];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         ans[i][j] = sc.nextInt();
        //     }
        // }

        // 本地测试用迷宫 5×5
        int[][] ans = {
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };
        int n = ans.length;     // 行数
        int m = ans[0].length;  // 列数

        // BFS 队列，元素是坐标 {x, y}
        Queue<int[]> que = new LinkedList<>();
        // 访问标记，防止重复入队、死循环
        boolean[][] flag = new boolean[n][m];
        // 是否已到达终点
        boolean found = false;
        // 回溯出来的路径节点
        List<Node> list = new ArrayList<>();
        // pre[x][y]：走到 (x,y) 的上一个格子，用于路径还原
        Node[][] pre = new Node[n][m];

        // 起点入队并标记
        que.add(new int[]{0, 0});
        flag[0][0] = true;

        // BFS 主循环：队列不空且未找到终点就继续
        while (!que.isEmpty() && !found) {
            // 当前层的节点数，用于分层遍历
            int size = que.size();
            while (size-- > 0) {
                int[] cur = que.poll();
                int x = cur[0];
                int y = cur[1];

                // 尝试四个方向
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    // 越界 / 已访问 / 是墙，直接跳过
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || flag[nx][ny] || ans[nx][ny] == 1) {
                        continue;
                    }

                    // 入队前立刻标记，避免被其他邻居重复加入
                    flag[nx][ny] = true;
                    que.add(new int[]{nx, ny});
                    // 记录当前格子的前驱是 (x,y)
                    pre[nx][ny] = new Node(x, y);

                    // 到达终点，结束搜索
                    if (nx == n - 1 && ny == m - 1) {
                        found = true;
                        break;
                    }
                }
            }
        }

        // 从终点沿 pre 反向回溯
        Node node = new Node(n - 1, m - 1);
        while (node != null) {
            list.add(node);
            node = pre[node.x][node.y];
        }
        // 回溯得到的是「终点 → 起点」，反转成「起点 → 终点」
        Collections.reverse(list);

        for (Node nod : list) {
            System.out.println("(" + nod.x + "," + nod.y + ")");
        }
    }
}

/**
 * 坐标节点，用于保存前驱关系。
 */
class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
