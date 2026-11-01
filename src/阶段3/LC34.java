package 阶段3;

import java.util.Arrays;

/**
 * LC34 在排序数组中查找元素的第一个和最后一个位置
 *
 * 难度：中等    标签：二分查找
 *
 * 题目：给定非递减数组 nums 和目标值 target，返回 target 出现的起始下标和结束下标；
 *       不存在返回 [-1, -1]。要求时间复杂度 O(log n)。
 * 示例：nums = [5,7,7,8,8,10], target = 8  →  [3,4]
 *       nums = [5,7,7,8,8,10], target = 6  →  [-1,-1]
 *
 * 思路：一次二分只能确定「某个」位置，无法直接得到边界，所以拆成两次二分：
 *       - findLeft：命中后不返回，继续往左收缩，用 ans 记住最后一个命中的位置 → 得到左边界；
 *       - findRight：命中后继续往右收缩 → 得到右边界。
 *       两次结构完全对称，只是命中后收缩的方向相反。
 * 复杂度：时间 O(log n)，空间 O(1)
 *
 * 坑点：
 * - 命中时不能立刻 return，必须先记录 ans 再继续收缩，否则只能得到随机某个位置。
 * - 两次二分必须各自独立初始化 left/right，不能共用。
 * - 找不到时 ans 保持 -1，两个方法都返回 -1，正好是题目要求的 [-1,-1]。
 */
public class LC34 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        // 分别求左右边界
        int left = findLeft(nums, target);
        int right = findRight(nums, target);

        int[] arr = new int[]{left, right};
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 找 target 第一次出现的下标。
     * 命中后不返回，而是继续压缩右边界，逼出最左的那个位置。
     */
    private static int findLeft(int[] nums, int target) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 记录候选答案
                ans = mid;
                // 关键：继续往左找，看还有没有更靠前的 target
                right = mid - 1;
            } else if (nums[mid] > target) {
                // 中点偏大，答案在左侧
                right = mid - 1;
            } else {
                // 中点偏小，答案在右侧
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 找 target 最后一次出现的下标。
     * 与 findLeft 对称：命中后继续压缩左边界。
     */
    private static int findRight(int[] nums, int target) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 记录候选答案
                ans = mid;
                // 关键：继续往右找，看还有没有更靠后的 target
                left = mid + 1;
            } else if (nums[mid] < target) {
                // 中点偏小，答案在右侧
                left = mid + 1;
            } else {
                // 中点偏大，答案在左侧
                right = mid - 1;
            }
        }
        return ans;
    }
}
