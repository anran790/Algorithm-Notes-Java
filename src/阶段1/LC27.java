package 阶段1;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码
 * 测试用例
 * 测试用例
 * 测试结果
 * 27. 移除元素
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 * <p>
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * <p>
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 * 用户评测：
 * <p>
 * 评测机将使用以下代码测试您的解决方案：
 * <p>
 * int[] nums = [...]; // 输入数组
 * int val = ...; // 要移除的值
 * int[] expectedNums = [...]; // 长度正确的预期答案。
 * // 它以不等于 val 的值排序。
 * <p>
 * int k = removeElement(nums, val); // 调用你的实现
 * <p>
 * assert k == expectedNums.length;
 * sort(nums, 0, k); // 排序 nums 的前 k 个元素
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * 如果所有的断言都通过，你的解决方案将会 通过。
 */
public class LC27 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;

        List<Integer> list = new ArrayList<>();
        int slow = 0;
        for (int n : nums) {
            if (n != val) {
                nums[slow] = n;
                slow++;
            }
        }
        System.out.println(list);
        System.out.println(slow);
    }
}
