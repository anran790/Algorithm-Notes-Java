package 阶段5;

import java.util.Arrays;

/**
 * LC452 用最少数量的箭引爆气球
 *
 * 难度：中等    标签：贪心 / 区间调度
 *
 * 题目：气球用水平区间 [start, end] 表示，在坐标 x 射一支箭可以引爆所有满足
 *       start ≤ x ≤ end 的气球。求引爆全部气球所需的最少箭数。
 * 示例：[[10,16],[2,8],[1,6],[7,12]]  →  2（x=6 和 x=11）
 *       [[1,2],[3,4],[5,6],[7,8]]     →  4
 *
 * 思路：与 LC435 同源，都是「按右端点排序 + 贪心保留」。
 *       排序后把第一支箭放在第一个气球的右端（尽量靠右，覆盖更多后续气球），
 *       之后遍历：只要气球的左端点 > 箭的位置，说明这支箭够不着，必须新射一支，
 *       并把新箭放到该气球的右端。
 * 复杂度：时间 O(n log n)，空间 O(1)
 *
 * 坑点：
 * - 判断用 > 而不是 >=：气球端点与箭重合时是可以被引爆的，算同一支箭。
 * - 与 LC435 的区别：那题求「移除几个」，本题求「射几支」，贪心策略一致但计数含义不同。
 * - 比较器同样要用 Integer.compare，避免坐标相减溢出。
 */
public class LC452 {
    public static void main(String[] args) {
        int[][] points = {{1, 6}, {2, 8}, {10, 16}, {7, 12}};
        System.out.println(findMinArrowShots(points));
    }

    /**
     * @param points 每个气球的左右端点
     * @return 最少弓箭数
     */
    public static int findMinArrowShots(int[][] points) {
        // 按右端点升序：越早结束的气球越需要优先处理
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        // 第一支箭放在第一个气球的右端
        int arrowPos = points[0][1];
        // extraArrows：后续需要新增的箭数（不含第一支）
        int extraArrows = 0;

        // 从第二个气球开始判断（第一个已被第一支箭覆盖）
        for (int i = 1; i < points.length; i++) {
            // 当前气球起点在箭的右侧，这支箭够不着，必须新射一支
            if (points[i][0] > arrowPos) {
                extraArrows++;
                // 新箭同样放在该气球的右端
                arrowPos = points[i][1];
            }
        }

        // 加上最开始那支
        return extraArrows + 1;
    }
}
