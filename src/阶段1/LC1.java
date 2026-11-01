package 阶段1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LC1 两数之和
 *
 * 难度：简单    标签：数组 / 哈希表
 *
 * 题目：给定整数数组 nums 和目标值 target，找出和为 target 的两个整数，返回它们的下标。
 *       假设每组输入只有一个答案，且同一个元素不能使用两次。
 * 示例：nums = [2,7,11,15], target = 9  →  [0,1]
 *       nums = [3,2,4],       target = 6  →  [1,2]
 *
 * 思路：一次遍历「边查边存」。用 Map 记录已遍历过的「数值 -> 下标」，
 *       对当前 nums[i]，先查 target - nums[i] 是否出现过：
 *       出现过就说明配成一对；没出现过再把 nums[i] 存进去，留给后面的元素查。
 * 复杂度：时间 O(n)，空间 O(n)
 *
 * 坑点：
 * - 必须先查后存。若先存后查，nums = [3,3] 会匹配到自己，得到 [0,0] 这种错答案。
 * - 题目要的是下标不是数值，所以 Map 的 value 必须存 i。
 * - 题目保证唯一解，找到一对即可；若要输出所有组合则不能提前结束。
 */
public class LC1 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int n = nums.length;

        // key：已经遍历过的数值；value：该数值所在的下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 当前元素需要的另一半
            int num = target - nums[i];
            // 第 1 步：先查另一半是否在前面出现过
            if (map.containsKey(num)) {
                System.out.println(map.get(num) + " " + i);
            }
            // 第 2 步：再登记当前值，留给后面的元素查
            map.put(nums[i], i);
        }

        // ===== 对照写法：暴力双重循环，时间 O(n²)，保留用于理解题意 =====
        // int[] arr = new int[2];
        // for (int i = 0; i < n; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         if (nums[i] + nums[j] == target) {
        //             arr[0] = i;
        //             arr[1] = j;
        //         }
        //     }
        // }
        // System.out.println(Arrays.toString(arr));
    }
}
