package 阶段3;

/**
 * LC704 二分查找
 *
 * 难度：简单    标签：二分查找
 *
 * 题目：给定升序整型数组 nums 和目标值 target，存在则返回下标，否则返回 -1。
 *       要求时间复杂度 O(log n)。
 * 示例：nums = [-1,0,3,5,9,12], target = 9  →  4
 *       nums = [-1,0,3,5,9,12], target = 2  →  -1
 *
 * 思路：标准的「左闭右闭」二分模板。
 *       区间定义为 [left, right]，所以循环条件是 left <= right（两端都可能是答案）；
 *       每次比较中点后，把不可能的一半整体排除，区间必然缩小，最终要么命中要么越界。
 * 复杂度：时间 O(log n)，空间 O(1)
 *
 * 坑点：
 * - mid 必须写成 left + (right - left) / 2，写成 (left + right) / 2 在超大数组下会整型溢出。
 * - 循环条件、区间收缩方式、返回值三者的「开闭」必须配套：
 *   左闭右闭 → left <= right，且 right = mid - 1 / left = mid + 1。
 *   若改成左闭右开，条件要变 left < right，right 要赋 mid，否则会死循环。
 * - 数组必须有序，这是二分的前提（本题已保证升序）。
 */
public class LC704 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        // 左闭右闭区间 [left, right]
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // 防溢出写法：等价于 (left + right) / 2
            int mid = left + (right - left) / 2;

            if (nums[mid] > target) {
                // 中点比目标大，答案只可能在左半边
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 中点比目标小，答案只可能在右半边
                left = mid + 1;
            } else {
                // 命中，直接返回下标
                System.out.println(mid);
                return;
            }
        }

        // 区间收缩到空还没命中，说明不存在
        System.out.println(-1);
    }
}
