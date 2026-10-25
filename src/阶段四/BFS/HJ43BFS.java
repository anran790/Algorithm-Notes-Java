package 阶段四.BFS;

import java.util.*;

/**
 * HJ43 迷宫问题
 * 有一个 h 行 w 列的网格，我们使用 (i,j) 表示网格中从上往下数第 i 行和从左往右数第 j 列的单元格。每个方格要么是可以通过的空方格 0，要么是不可通过的墙方格 1
 * 特别的，网格的四周都是墙方格，你可以沿着空方格上下左右随意移动：从 (x,y) 向上移动一格即抵达 (x−1,y) 、向下移动一格即抵达 (x+1,y) 、向左移动一格即抵达 (x,y−1) 、向右移动一格即抵达 (x,y+1)。
 * 现在，你位于迷宫的入口 (0,0) ，想要前往终点 (h−1,w−1) 。请输出一条从起点到终点的可行路径。
 * <p>
 * 保证起点和终点一定为空方格，你始终可以找到且能唯一找到一条从起点出发到达终点的可行路径。
 * 输入描述：
 * 第一行输入两个整数 h,w(1≦h,w≦100) 代表迷宫的行数和列数。
 * 此后 h 行，第 i 行输入 w 个整数 a_i1,a_i2,…,a_iw(0≦a_ij≦1) 代表迷宫的布局。其中，a_ij=0 表示单元格 (i,j) 是空方格，a_ij=1 表示单元格 (i,j) 是墙方格。
 * 输出描述：
 * 输出若干行，第 i 行输出两个整数 x_i,y_i，表示路径的第 i 步抵达的单元格坐标为 (x_i,y_i)。
 * <p>
 * 你需要保证输出的路径是符合题目要求的，即从起点 (0,0) 出发，到达终点 (h−1,w−1) ，且路径上每个单元格都是空方格，行走的单元格都是彼此相邻的。
 *
 * 思路：BFS广度优先搜索 + 前驱数组路径还原
 * BFS特性：第一次到达终点就是最短路径；pre数组记录每个点是从哪个坐标走过来的，最后从终点反向回溯再反转得到正向路径
 */
public class HJ43BFS {
    // 四个方向：下、上、右、左
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = sc.nextInt();
            }
        }
        */
        // 测试用迷宫 5*5
        int[][] ans = {
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };
        // 行数、列数
        int n = ans.length;
        int m = ans[0].length;

        // BFS队列，存放坐标 int[]{x,y}
        Queue<int[]> que = new LinkedList<>();
        // flag标记该坐标是否已经访问过，防止重复入队、死循环
        boolean[][] flag = new boolean[n][m];
        // 起点(0,0)入队
        que.add(new int[]{0, 0});
        // 是否找到终点的标记
        boolean res = false;
        // 保存回溯出来的路径节点
        List<Node> list = new ArrayList<>();
        // pre[x][y]：记录走到(x,y)的上一个节点（前驱节点），用于路径还原
        Node[][] pre = new Node[n][m];
        // 起点标记已访问
        flag[0][0] = true;

        // BFS主循环，队列不为空且未找到终点就继续
        while (!que.isEmpty()) {
            // size：当前这一层的节点总数，用来分层遍历（标准层序BFS）
            int size = que.size();
            // 处理当前层所有节点
            while (size-- > 0) {
                int[] tmp = que.poll();
                int x = tmp[0];
                int y = tmp[1];
                // 遍历4个方向
                for (int[] dir : dirs) {
                    // 计算邻居坐标nx, ny
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    // 边界判断 + 未访问 + 是通路0
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !flag[nx][ny] && ans[nx][ny] == 0) {
                        flag[nx][ny] = true;
                        que.add(new int[]{nx, ny});
                        // 记录当前邻居的前驱是(x,y)
                        pre[nx][ny] = new Node(x, y);
                        // 判断是不是终点
                        if (nx == n - 1 && ny == m - 1) {
                            res = true;
                            break;
                        }
                    }
                }
            }
            // 找到终点，跳出外层BFS循环
            if (res) {
                break;
            }
        }

        // 从终点开始反向回溯
        Node node = new Node(n - 1, m - 1);
        while (node != null) {
            list.add(node);
            // 向前找前驱节点
            node = pre[node.x][node.y];
        }
        // 回溯得到的是【终点→起点】，反转变成【起点→终点】
        Collections.reverse(list);

        // 按格式输出路径
        for (Node nod : list) {
            System.out.println("(" + nod.x + "," + nod.y + ")");
        }
    }
}

/**
 * Node节点类，封装坐标x,y，用来存前驱信息
 */
class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
