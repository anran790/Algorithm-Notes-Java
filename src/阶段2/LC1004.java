package 阶段2;

/**
 * 1004. 最大连续1的个数 III
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * <p>
 * <p>
 * 提示：
 */
public class LC1004 {
    public static void main(String[] args) {
        // 经典求最大值滑动窗口，不懂的可以查看lc3
        // 难点：不确定left定义，最大字符串中left定义的是重复字符剔除
        // 本题是当可以使用的k用完后，左侧就需要剔除已经使用0转1的“0”
        // 这样右侧可以继续累加求长度
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        int sum = 0;
        int next = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0) {
                // 遇到0进行累加
                next++;
            }
            while (next > k) {
                if (nums[left] == 0) {
                    // 左侧遇到0进行剔除，--操作跳出循环，right可以继续右移了
                    next--;
                }
                left++;
            }
            sum = Math.max(sum, right - left + 1);
        }
        System.out.println(sum);
    }
}
