package 阶段5;

import java.util.Arrays;

/**
 * LC435 无重叠区间
 *
 * 难度：中等    标签：贪心 / 区间调度
 *
 * 题目：给定区间集合 intervals，返回需要移除的最少区间数，使剩余区间互不重叠。
 *       注意：只在端点接触（如 [1,2] 与 [2,3]）算不重叠。
 * 示例：[[1,2],[2,3],[3,4],[1,3]]  →  1（移除 [1,3]）
 *       [[1,2],[1,2],[1,2]]        →  2
 *
 * 思路：区间调度问题的标准贪心 —— 按右端点升序排序，优先保留「结束得早」的区间，
 *       因为它给后面留出的空间最大。
 *       排序后从第二个区间开始扫描：只要当前区间的左端点 ≥ 上一个保留区间的右端点，
 *       就说明不重叠，可以保留；否则必须移除。
 *       最后用「总数 - 保留数」得到移除数。
 * 复杂度：时间 O(n log n)（排序主导），空间 O(1)
 *
 * 坑点：
 * - 必须按「右端点」排序。按左端点排序是错的，会漏掉最优解。
 * - 端点相等算不重叠，所以判断用 >= 而不是 >。
 * - 比较器用 Integer.compare(a, b)，不要写 a - b：差值在极端值下会整型溢出。
 * - 循环从 i = 1 开始，因为第 0 个区间默认被保留，它的右端点就是初始边界。
 */
public class LC435 {
    public static void main(String[] args) {
        // 测试样例1，预期输出 1
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        // 贪心关键：按右端点升序，结束越早越优先保留
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        // rightIndex：上一个被保留区间的右端点，也就是当前的「可用边界」
        int rightIndex = intervals[0][1];
        // keptAfterFirst：除第一个区间外，还能保留下来的区间数
        int keptAfterFirst = 0;

        // 从第二个区间开始扫描（第一个默认保留）
        for (int i = 1; i < intervals.length; i++) {
            // 左端点 >= 上一个右端点，说明不重叠，可以保留
            if (intervals[i][0] >= rightIndex) {
                keptAfterFirst++;
                // 更新边界为当前区间的右端点
                rightIndex = intervals[i][1];
            }
            // 否则与已保留区间重叠，必须移除，边界不变
        }

        // 总数 - 第一个 - 后续保留数 = 需要移除的数量
        return intervals.length - 1 - keptAfterFirst;
    }
}
