package 阶段3;

import java.util.Arrays;

/**
 * 代码
 * 测试用例
 * 测试用例
 * 测试结果
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class LC34 {
    public static void main(String[] args) {
        // 题意：寻找target在数组中第一个出现的位置和最后的一个位置
        // 分开遍历：最左面出现的target，最右面出现的target
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        // 最左边出现的target
        int left = findLeft(nums, target);
        // 最右面出现的target
        int right = findRight(nums, target);

        int[] arr = new int[]{left, right};
        System.out.println(Arrays.toString(arr));
    }

    private static int findLeft(int[] nums, int target) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 记录答案
                ans = mid;
                // 左侧二分
                right = mid - 1;
            } else if (nums[mid] > target) {
                // 左侧二分
                right = mid - 1;
            } else {
                // 右侧二分nums[mid] < target
                left = mid + 1;
            }
        }
        return ans;
    }

    private static int findRight(int[] nums, int target) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 记录答案
                ans = mid;
                // 右侧二分
                left = mid + 1;
            } else if (nums[mid] < target) {
                // 右侧二分
                left = mid + 1;
            } else {
                // 左侧二分 nums[mid] > target
                right = mid - 1;
            }
        }
        return ans;
    }
}
