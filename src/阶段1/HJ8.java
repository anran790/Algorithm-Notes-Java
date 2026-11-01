package 阶段1;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * HJ8 合并表记录
 *
 * 难度：简单    标签：哈希表 / 有序聚合
 *
 * 题目：输入 n 条记录，每条包含「索引 数值」。索引相同的记录将数值相加合并，
 *       结果按索引从小到大输出。
 * 示例：
 *   输入：4 / 0 1 / 0 2 / 1 2 / 3 4
 *   输出：0 3 / 1 2 / 3 4
 *
 * 思路：用 TreeMap 存「索引 -> 累加值」，边读边合并，最后按 key 天然升序输出。
 * 复杂度：时间 O(n log n)，空间 O(n)
 *
 * 坑点：
 * - nextInt() 读完 n 后光标还停在行尾，必须先 nextLine() 吃掉换行，
 *   否则第一次 nextLine() 读到空串，split 会数组越界。
 * - 用 TreeMap 而不是 HashMap，是因为题目要求按索引升序输出，省去额外排序。
 * - map.getOrDefault(key, 0) + value：同一索引可能重复出现，必须累加而不是覆盖。
 */
public class HJ8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // TreeMap：key 天然按升序排列，正好满足输出顺序要求
        Map<Integer, Integer> map = new TreeMap<>();
        // 消化掉 nextInt() 留在缓冲区里的换行符
        in.nextLine();
        while (n-- > 0) {
            String[] str = in.nextLine().split(" ");
            int key = Integer.parseInt(str[0]);
            int value = Integer.parseInt(str[1]);
            // 同索引累加；第一次出现时以 0 为底
            map.put(key, map.getOrDefault(key, 0) + value);
        }
        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }
}
