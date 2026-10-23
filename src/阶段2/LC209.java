package 阶段2;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的
 * 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 */
public class LC209 {
    public static void main(String[] args) {
        // 典型滑动窗口求最小数组：满足条件后尽量压缩窗口，压到最小
        // 规则是right一直往右走，元素累加，一旦满足条件，进入while循环，记录当前窗口最小值
        // 吐出左面元素，实现压缩，不符合条件跳出while循环，right++继续，直到记录所有符合条件的连续数组，记录最小值
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

        int n = nums.length;
        int count = Integer.MAX_VALUE;
        // 总数
        int sum = 0;
        // 最左窗口起点
        int left = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum >= target) {
                // 记录最小值 right - left + 1
                count = Math.min(count, right - left + 1);
                // 吐出左侧元素
                sum -= nums[left];
                left++;
            }
        }
        // 题目要求没有符合的返回0
        if (count < Integer.MAX_VALUE) {
            System.out.println(count);
        } else {
            System.out.println(0);
        }
    }
}
