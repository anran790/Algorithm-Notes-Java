package 阶段6.A维DP;

/**
 * 53. 最大子数组和
 * 已解答
 * 中等
 * 相关标签：动态规划、贪心（Kadane算法）
 *
 * 题意：
 * 找出连续子数组，子数组至少包含一个元素，返回子数组的最大和。
 * 子数组：元素必须在原数组中连续。
 *
 * DP定义（Kadane）：
 * cur：以 nums[i] 结尾的连续子数组的最大和
 * 两种选择：
 * 1. 前面的子数组和(cur + nums[i]) 带上当前元素
 * 2. 抛弃前面，从当前nums[i]重新开始
 * cur = Math.max(nums[i], cur + nums[i]);
 *
 * count：全局记录遍历过程中出现过的最大cur，也就是答案
 */
public class LC53 {
    public static void main(String[] args) {
        // 测试用例 [-2,1,-3,4,-1,2,1,-5,4]，预期输出6
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int maxSubArray(int[] nums) {
        // cur：以当前位置结尾的连续子数组最大和
        int cur = nums[0];
        // count：全局最大值，保存所有cur里面最大的值
        int count = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 要么单独选nums[i]，要么接上前面一段
            cur = Math.max(nums[i], cur + nums[i]);
            // 更新全局最大
            count = Math.max(cur, count);
        }
        return count;
    }
}
