package 字节跳动算法题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间
 * 输入： [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class 合并区间 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        printIntervals(merge(intervals));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        // 贪心左侧端点排序
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        List<int[]> list = new ArrayList<>();
        int rightIndex = intervals[0][1];
        int leftIndex = intervals[0][0];
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[0] <= rightIndex) {
                rightIndex = Math.max(rightIndex, curr[1]);
            } else {
                list.add(new int[]{leftIndex, rightIndex});
                leftIndex = curr[0];
                rightIndex = curr[1];
            }
        }
        list.add(new int[]{leftIndex, rightIndex});
        int[][] intervalsNew = list.toArray(new int[list.size()][]);
        return intervalsNew;
    }

    static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            int[] item = intervals[i];
            System.out.print("[" + item[0] + "," + item[1] + "]");
            if (i != intervals.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

}
