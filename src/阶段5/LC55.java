package 阶段5;

/**
 * LC55 跳跃游戏
 * 贪心思路：遍历数组，维护当前能够到达的最远下标 maxReach（变量count）
 * 如果当前位置i > 最远可达下标，说明到不了i，自然到不了终点，返回false
 * 否则尝试更新最远可达下标：i + nums[i]
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class LC55 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        // count：记录目前能跳到的最远下标
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当前位置i，已经超出最远可达范围，无法到达
            if (count < i) {
                System.out.println(false);
                return;
            }
            // 更新最远可达下标：当前位置 + 可以跳的步数
            count = Math.max(count, nums[i] + i);
        }
        // 全部位置都能到达，可以抵达终点
        System.out.println(true);
    }
}
