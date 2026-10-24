package 阶段3;

/**
 *
 代码
 测试用例
 测试用例
 测试结果
 704. 二分查找
 已解答
 简单
 相关标签
 premium lock icon
 相关企业
 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。

 你必须编写一个具有 O(log n) 时间复杂度的算法。

 示例 1:

 输入: nums = [-1,0,3,5,9,12], target = 9
 输出: 4
 解释: 9 出现在 nums 中并且下标为 4
 示例 2:

 输入: nums = [-1,0,3,5,9,12], target = 2
 输出: -1
 解释: 2 不存在 nums 中因此返回 -1
 */
public class LC704 {
    public static void main(String[] args) {
        // 最基础标准的二分查找
        // 优点：查找速度很快，数组越大越明显2的31次方长度只需要查询31次就可以完成
        // 原理：二分数值大于target左面二分，二分数值小于target右面二分
        // 整个算法遵循规律不断循环最终获取结果
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                // 左侧二分，中间值减一
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 右侧二分，中间值加一
                left = mid + 1;
            } else {
                // 相等输出答案
                System.out.println(mid);
                return;
            }
        }
        System.out.println(-1);
        int i = 1>0?1:0;
    }

}
