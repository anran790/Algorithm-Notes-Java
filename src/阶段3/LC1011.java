package 阶段3;

/**
 * LC1011 在 D 天内送达包裹的能力
 *
 * 难度：中等    标签：二分答案
 *
 * 题目：包裹必须按给定顺序装船，每天装的重量不能超过船的载重。
 *       求能在 days 天内运完所有包裹的「最低载重能力」。
 * 示例：weights = [1,2,3,4,5,6,7,8,9,10], days = 5  →  15
 *       weights = [3,2,2,4,1,4], days = 3            →  6
 *
 * 思路：与 LC875 完全同构，只是 check 的含义从「吃香蕉耗时」换成「运完需要几天」。
 *       载重越大天数越少，单调性成立 → 二分载重。
 *       下界 = 最大单个包裹（再小就永远运不走最重那个）；
 *       上界 = 所有包裹总重（一天全运完）。
 *       二分过程中，check(mid) 成立就记录答案并向左收缩，找最小可行载重。
 * 复杂度：时间 O(n log sum(weights))，空间 O(1)
 *
 * 坑点：
 * - 下界必须是 max(weights) 而不是 1，否则遇到超大包裹会算出「不存在的方案」。
 * - 包裹顺序固定，不能排序也不能随意拆分，只能按原顺序连续装船。
 * - check 里的 day 统计的是「已经装满并开走的船数」，最后还有一船没结算，
 *   所以判断时要用 day + 1。
 */
public class LC1011 {
    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        // 载重区间：下界至少能装下最重的单个包裹，上界是一天装完全部
        int left = 1;
        int right = 0;
        int ans = 0;

        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        // 二分「最小可行载重」
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(weights, mid, days)) {
                // 当前载重够用，记录并尝试更小的载重
                ans = mid;
                right = mid - 1;
            } else {
                // 载重太小导致超天数，需要加大
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    /**
     * 判断载重为 k 时能否在 days 天内运完。
     */
    public static boolean check(int[] weights, int k, int days) {
        int day = 0;  // 已经装满开走的船数
        int sum = 0;  // 当前这艘船已装的重量

        for (int weight : weights) {
            sum += weight;
            // 装不下了：本船结算，当前包裹放到新船上
            if (sum > k) {
                sum = weight;
                day += 1;
            }
        }
        // 最后一艘船还没结算，所以要 +1
        return day + 1 <= days;
    }
}
