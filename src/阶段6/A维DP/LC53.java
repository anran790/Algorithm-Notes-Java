package 阶段6.A维DP;

/**
 * LC53 最大子数组和
 *
 * 难度：中等    标签：动态规划 / 贪心（Kadane 算法）
 *
 * 题目：给定整数数组 nums，找出和最大的连续子数组（至少包含一个元素），返回其和。
 * 示例：nums = [-2,1,-3,4,-1,2,1,-5,4]  →  6（子数组 [4,-1,2,1]）
 *
 * 思路：Kadane 算法。定义 cur 为「以当前元素结尾的连续子数组最大和」，
 *       对每个 nums[i] 只有两个选择：
 *       接上前面的 cur + nums[i]，或者抛弃前面从 nums[i] 重新开始。
 *       cur = max(nums[i], cur + nums[i])
 *       再用 count 记录遍历过程中 cur 出现过的最大值，就是答案。
 * 复杂度：时间 O(n)，空间 O(1)（只有两个滚动变量）
 *
 * 坑点：
 * - 不能用「遇到负数就清零」的写法，会漏掉 [5,-2,3] 这种跨过小负数仍最优的情况。
 * - 初值必须是 nums[0] 而不是 0：数组全为负数时，0 会得到错误答案。
 * - 循环从 i = 1 开始，因为 i = 0 时 cur 已经用 nums[0] 初始化过了。
 * - 求的是「连续」子数组，不能跳着选，否则就变成子序列问题。
 */
public class LC53 {
    public static void main(String[] args) {
        // 测试用例，预期输出 6
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int maxSubArray(int[] nums) {
        // cur：以当前位置结尾的连续子数组最大和
        int cur = nums[0];
        // best：全局最大和（所有 cur 里的最大值）
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 要么接上前面一段，要么从当前元素重新开始
            cur = Math.max(nums[i], cur + nums[i]);
            // 更新全局最大
            best = Math.max(best, cur);
        }
        return best;
    }
}
