package 阶段1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC15 三数之和
 *
 * 难度：中等    标签：排序 / 双指针
 *
 * 题目：找出所有和为 0 且不重复的三元组 [nums[i], nums[j], nums[k]]，
 *       要求 i、j、k 互不相同（值可以相同，但下标不能重复）。
 * 示例：nums = [-1,0,1,2,-1,-4]  →  [[-1,-1,2], [-1,0,1]]
 *       nums = [0,1,1]           →  []
 *
 * 思路：排序后「固定一个数 + 双指针」，把三数之和降成两数之和。
 *       外层固定 nums[i]，内层 left = i+1、right = n-1 向中间收缩：
 *       三数和 > 0 说明右边太大 → right--；
 *       三数和 < 0 说明左边太小 → left++；
 *       等于 0 就收集答案，并让 left、right 同时向中间移动。
 * 复杂度：时间 O(n²)，空间 O(1)（不计结果集本身）
 *
 * 坑点（三处去重，缺一处就会输出重复三元组）：
 * 1. 外层 i：nums[i] == nums[i-1] 时跳过。
 * 2. 命中后 left：跳过与左邻居相同的元素。
 * 3. 命中后 right：跳过与右邻居相同的元素。
 * 另有剪枝：排序后若 nums[i] > 0，后面三个数只会更大，不可能凑出 0，直接 break。
 */
public class LC15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> list = new ArrayList<>();

        // 双指针的前提是数组有序，先排序
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // 去重 1：当前这个数如果和上一个相同，会产生相同三元组，跳过
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            // 剪枝：最小的数都大于 0，后面不可能再凑出 0
            if (nums[i] > 0) {
                break;
            }

            // 双指针从 i 的右侧开始，保证不会回头选到已经用过的数
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int res = nums[i] + nums[left] + nums[right];
                if (res > 0) {
                    // 和太大，右指针左移让和变小
                    right--;
                } else if (res < 0) {
                    // 和太小，左指针右移让和变大
                    left++;
                } else {
                    // 找到一组解
                    List<Integer> listOne = new ArrayList<>();
                    listOne.add(nums[i]);
                    listOne.add(nums[left]);
                    listOne.add(nums[right]);
                    list.add(listOne);

                    // 先各自移动一步，再继续跳过重复值
                    left++;
                    right--;
                    // 去重 2：跳过重复的左元素
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    // 去重 3：跳过重复的右元素
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        System.out.println(list);
    }
}
