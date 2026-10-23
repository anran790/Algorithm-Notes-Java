package 阶段2;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码
 * 测试用例
 * 测试用例
 * 测试结果
 * 560. 和为 K 的子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 */
public class LC560 {
    public static void main(String[] args) {
        // 这是一种算法思想，思想分两步，
        // 思想1和LC344有异曲同工之意，通过map减少暴力寻循环
        // 思想2是计算所有下标位置 的 前面的总和，每个下标代表前面所有数字的总和，
        // 由此我们可以得出两个下标之差等于“k”,就代表两个下标之间总和等于k，符合题目要求
        // （这是滑动窗口里面的一类算法思想：有负数不能直接滑窗，“换前缀和 + 哈希”）
        // 一定要理解思想1和思想2在看代码
        int[] nums = {1, 1, 1};
        int k = 2;
        Map<Integer, Integer> map = new HashMap<>();
        // 设置初始值，前缀差为0的时候出现1次（这个设置是为了解决当前num[0]到num[r]的和等于k）
        // 适应这种情况preSum[r] - k = 0;
        map.put(0, 1);
        // 初始总和
        int preSum = 0;
        // 符合题目要求的连续子串
        int count = 0;
        for (int num : nums) {
            preSum += num;
            // 判断是否有符合的k
            if (map.containsKey(preSum - k)) {
                // 并获取对应次数
                count += map.get(preSum - k);
            }
            // 因为是累加的，队列不会重复，但是会出现不同下标相同累加值（中间有负数），所以要多次记录，map不允许重复
            // 最终k符合的记录是队列的总数
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        System.out.println(count);

    }
}
