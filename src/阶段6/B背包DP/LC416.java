package 阶段6.B背包DP;

/**
 * 416. 分割等和子集
 * 已解答
 * 中等
 * 相关标签：01背包、动态规划
 * 相关企业
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * 思路转化：
 * 1. 数组总和sum，如果sum是奇数，一定无法平分，直接返回false
 * 2. 如果sum是偶数，目标转化为：能否选出一部分数字，和等于 sum/2（target）
 * 3. 等价于01背包：背包容量target，每个数字只能选一次，能否恰好装满背包
 * dp[j]含义：从已经遍历过的数字中，是否可以选出若干，凑出和为j
 * dp[0]=true：和为0，不取任何数字，一定成立
 * 01背包内层循环倒序，避免同一个数字被重复选取
 * 转移方程：dp[j] = dp[j] || dp[j - n]
 *  不选当前数字n：保持原来dp[j]
 *  选当前数字n：前面能凑出j-n，加上n就能凑j
 */
public class LC416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        // 计算数组全部元素总和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 总和是奇数，无法分成两个和相等子集，直接false
        if (sum % 2 > 0) {
            return false;
        }
        // 目标：选出子集和等于总和一半
        int target = sum / 2;
        // dp[j]：是否可以凑出和 j
        boolean[] dp = new boolean[target + 1];
        // base case：和为0，不选任何数，可达
        dp[0] = true;
        // 这里比较难理解，可以针对代码逻辑解释：
        // 转移方程：dp[j] = dp[j] || dp[j - n]
        // 第一次循环，当 j==n 的时候，j-n=0，dp[0]=true，于是 dp[j] = true；意思就是单独选这个数字就能凑出n
        // 后续每处理一个数字，就可以用上一轮已经得到的 dp[j]=true，继续推算出更大的j
        // 如果数组里能选出一部分数字凑出target，最后 dp[target] 就会变成true
        // 提前判断总和是 target*2，只要能凑出target，剩下数字的和自动等于target，满足题目分割条件
        // 为什么倒序 j 从target往下：
        // 如果写成正序，举例子 n=5：先算出 dp[5]=dp[0]=true，后面j=10又拿这个刚更新好的dp[5]计算，相当于同一个5选两次，重复选取，是错误的，所以01背包要用倒序


        // 遍历每一个数字（01背包的物品，每个物品只能选一次）
        for (int n : nums) {
            // 倒序遍历背包容量，01背包核心，防止同一个数字重复选取
            for (int j = target; j >= n; j--) {
                // 凑j：要么本来就能凑（不选n），要么凑出j-n再加n（选n）
                dp[j] = dp[j] || dp[j - n];
            }
        }
        // 能否凑出target，凑出则可以分割
        return dp[target];
    }
}
