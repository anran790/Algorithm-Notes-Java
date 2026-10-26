package 阶段2;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 * 难度：中等
 * 标签：前缀和、哈希表
 *
 * 题目：
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数。
 * 子数组是数组中元素的连续非空序列。
 *
 * 示例 1：nums = [1,1,1], k = 2，输出：2
 * 示例 2：nums = [1,2,3], k = 3，输出：2
 *
 * 核心思路：前缀和 + 哈希表
 * 公式：preSum[r] - preSum[l-1] = k  →  preSum[l-1] = preSum[r] - k
 * 含义：以r结尾的子数组，如果存在前缀和等于 preSum[r]-k，说明 l ~ r 这一段子数组和为k
 *
 * ⚠️重要提醒：数组包含负数、0，不能用普通滑动窗口（滑动窗口只适合全正数），所以选用前缀和+哈希
 */
public class LC560 {
    public static void main(String[] args) {
        // 测试样例
        int[] nums = {1, 1, 1};
        int k = 2;

        // key：前缀和的值；value：这个前缀和出现过多少次
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化：前缀和=0，出现1次
        // 作用：处理 preSum == k 的情况，也就是从数组起点到当前位置的子数组直接等于k
        // 等价 preSum - k = 0，此时需要有 preSum=0 的记录
        map.put(0, 1);

        // preSum：遍历到当前位置的前缀和（nums[0] ~ nums[i]累加和）
        int preSum = 0;
        // count：记录满足条件的子数组总数量
        int count = 0;

        for (int num : nums) {
            // 更新前缀和，累加当前数字
            preSum += num;

            // 查找是否存在 preSum - k，也就是找满足公式的前面的前缀和
            if (map.containsKey(preSum - k)) {
                // 存在的话，把这个前缀和出现的次数加到总数上
                count += map.get(preSum - k);
            }

            // 将当前前缀和存入map：
            // 如果之前已经出现过这个前缀和，次数+1；不存在则初始为1
            // 多个不同下标可以拥有相同前缀和（有负数时很常见），所以要统计次数，不能直接覆盖
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        System.out.println(count);
    }
}
