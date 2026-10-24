package 阶段3;

/**
 * 1011. 在 D 天内送达包裹的能力
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * <p>
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * 示例 2：
 * <p>
 * 输入：weights = [3,2,2,4,1,4], days = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * 示例 3：
 * <p>
 * 输入：weights = [1,2,3,1,1], days = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 */
public class LC1011 {
    public static void main(String[] args) {
        // 题型和875一模一样，最大承载是数组总和，最小承载是数组中的最大值
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        int left = 1;
        int right = 0;
        int ans = 0;
        for (int weigth : weights) {
            left = Math.max(left, weigth);
            right += weigth;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(weights, mid, days)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean check(int[] weights, int k, int days) {
        int day = 0;
        int sum = 0;
        for (int weigth : weights) {
            sum += weigth;
            // 最后一次百分之百没有统计进去
            if (sum > k) {
                sum = weigth;
                day += 1;
            }
        }
        // 外面判断条件需要+1，小于系统天数还可以降低传送容量
        if (day + 1 <= days) {
            return true;
        } else {
            // 大于系统天数，需要加大传送量
            return false;
        }

    }
}
