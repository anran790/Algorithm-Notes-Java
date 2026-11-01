package 阶段2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC187 重复的 DNA 序列
 *
 * 难度：中等    标签：滑动窗口（定长）/ 哈希表
 *
 * 题目：DNA 序列由 A、C、G、T 四种字符组成，找出所有「长度恰好为 10 且出现次数 ≥ 2」的子串。
 * 示例：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *       → ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * 思路：定长滑动窗口。窗口长度固定为 10，从左往右平移，枚举出每个长度为 10 的子串，
 *       用 Map 统计出现次数，最后挑出次数 > 1 的。
 * 复杂度：时间 O(n × L)（L = 10，每格都要生成子串并算哈希），空间 O(n × L)
 *
 * 坑点：
 * - right 从 L 开始，取 [right - L, right) 才是长度 10；写成 [right, right + L) 需要注意边界。
 * - 同一个子串出现 3 次也只能进结果一次，所以先全部统计、最后统一筛选，
 *   不要边遍历边加入结果。
 * - 进阶：把 A/C/G/T 编码成 0/1/2/3，用 20 位整数表示一个子串，
 *   配合滑动哈希可做到 O(n) 时间，是大数据量下的标准解法。
 */
public class LC187 {
    public static void main(String[] args) {
        // 定长滑窗 + 哈希计数
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        Map<String, Integer> map = new HashMap<>();
        int n = s.length();
        int L = 10;  // 窗口长度

        for (int right = L; right <= n; right++) {
            // 取长度为 L 的窗口 [right - L, right)
            String key = s.substring(right - L, right);
            // 统计该子串出现次数（写成一行更好读，也避免重复 substring 调用）
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        // 统一筛选出现次数大于 1 的子串
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) > 1) {
                list.add(key);
            }
        }
        System.out.println(list);
    }
}
