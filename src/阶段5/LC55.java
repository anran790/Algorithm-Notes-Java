package 阶段5;

/**
 * LC55 跳跃游戏
 *
 * 难度：中等    标签：贪心
 *
 * 题目：非负整数数组 nums，从下标 0 出发，nums[i] 表示该位置最多能跳几步。
 *       判断能否到达最后一个下标。
 * 示例：nums = [2,3,1,1,4]  →  true
 *       nums = [3,2,1,0,4]  →  false
 *
 * 思路：不需要真的去跳，只需要维护「当前能到达的最远下标」maxReach。
 *       遍历过程中，若当前下标 i 已经超过 maxReach，说明这个位置根本走不到，直接返回 false；
 *       否则用 i + nums[i] 去更新 maxReach。
 *       只要遍历能走完，就说明终点可达。
 * 复杂度：时间 O(n)，空间 O(1)
 *
 * 坑点：
 * - 判断顺序是先校验再更新：如果 i > maxReach，说明 i 都到不了，nums[i] 也就用不上。
 * - 初值 maxReach = 0（起点），不要初始化为 nums[0]，否则 [0] 这种单元素用例会判断错误。
 * - 本题只问「能不能到」，若要问「最少跳几步」则需要改成 BFS 或另一套贪心（LC45）。
 */
public class LC55 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};

        // maxReach：目前能跳到的最远下标
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            // 当前位置已经超出可达范围，后面的更到不了
            if (maxReach < i) {
                System.out.println(false);
                return;
            }
            // 更新最远可达：当前位置 + 该位置能跳的步数
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        // 全部位置都可达，自然能到终点
        System.out.println(true);
    }
}
