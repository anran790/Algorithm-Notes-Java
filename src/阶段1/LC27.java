package 阶段1;

import java.util.ArrayList;
import java.util.List;

/**
 * LC27 移除元素
 *
 * 难度：简单    标签：数组 / 快慢指针
 *
 * 题目：原地移除数组中所有等于 val 的元素，返回剩余元素的个数 k。
 *       评测只检查前 k 个元素是否等于期望值，数组后面的残留内容无所谓，
 *       因此不需要真的「删除」，只需把有效元素往前挪。
 * 示例：nums = [3,2,2,3], val = 3  →  k = 2，前两个元素为 [2,2]
 *
 * 思路：快慢指针。fast 负责遍历原数组，slow 指向下一个有效元素该放的位置。
 *       遇到不等于 val 的元素，就写到 nums[slow] 并让 slow 前移；
 *       遇到等于 val 的元素，直接跳过（相当于被覆盖掉）。
 *       遍历结束时，slow 正好等于有效元素个数。
 * 复杂度：时间 O(n)，空间 O(1)
 *
 * 坑点：
 * - 是「覆盖」不是「交换」，因为慢指针永远不超过快指针，写位置不会被后续读取影响。
 * - 返回值是长度 k，不是数组本身。
 */
public class LC27 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;

        // 早期调试留下的变量，实际没有参与逻辑，输出永远是 []，可以删掉
        List<Integer> list = new ArrayList<>();

        // slow：下一个有效元素要写入的位置，同时也就是有效元素的个数
        int slow = 0;
        for (int n : nums) {
            if (n != val) {
                // 保留该元素：写到 slow 位置，然后 slow 前移
                nums[slow] = n;
                slow++;
            }
            // n == val 时什么都不做，相当于被后面的有效元素覆盖
        }

        System.out.println(list);
        // slow 此时就是剩余元素个数 k
        System.out.println(slow);
    }
}
