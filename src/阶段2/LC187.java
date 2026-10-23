package 阶段2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC187 {
    public static void main(String[] args) {
        // 滑动窗口定长
        // 通过记录重复次数统计

        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        Map<String, Integer> map = new HashMap<>();
        int n = s.length();
        int L = 10;
        for (int right = L; right <= n; right++) {
            // map.put(s.substring(right - L, right), map.getOrDefault(s.substring(right - L, right), 0) + 1);
            // 一行代码可以实现方便阅读
            String key = s.substring(right-L, right);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (String i : map.keySet()) {
            if (map.get(i) > 1) {
                list.add(i);
            }
        }
        System.out.println(list);
    }
}
