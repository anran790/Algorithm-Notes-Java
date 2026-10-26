package 阶段5;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 * 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class LC435 {
    public static void main(String[] args) {
        // 测试样例1
        int[][] ints = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(ints));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        // 贪心策略：按区间右端点从小到大排序，优先选结束早的区间，能留出更多时间给后面
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        // rightIndex：上一个选中区间的右端点边界
        // 【注意！这个版本有bug，初始直接拿第0个区间右端点，count初始0，代表后面能接上的数量】
        int rightIndex = intervals[0][1];
        // count：统计【从i=1开始】能接上、不重叠的区间个数
        int count = 0;

        // 从头遍历所有区间
        for (int i = 0; i < intervals.length; i++) {
            // 当前区间起点 >= 上一个选中的终点：不重叠，可以选
            if (intervals[i][0] >= rightIndex) {
                count++;
                // 更新边界为当前区间的右端点
                rightIndex = intervals[i][1];
            }
        }
        // 总区间 - 第0个本身(固定保留1个) - 后面接上的count个 = 需要删掉的数量
        return intervals.length - 1 - count;
    }
}
