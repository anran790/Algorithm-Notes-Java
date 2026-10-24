package 阶段3;

/**
 * 875. 爱吃香蕉的珂珂
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 */
public class LC875 {
    public static void main(String[] args) {
        // 题意1：速度大于当前一堆或者剩余香蕉需要按一小时计算，我们首先要有知识储备java向上取整(x+k-1/k是x/k的向上取整写法)
        // 题意2：题意1约束可以确定时间h不会小于数组长度length，所有我们可以获得速度区间[1,最大堆香蕉] 例如示例2
        // 思考过程：首先思考暴力循环，for循环累加速度计算吃香蕉所用时间是否小于警员离开时时间
        // 伪代码
        // for(int i = 1;i<=最大一堆;i++){
        // 循环 (3+i-1)/i+(6+i-1)/i+(7+i-1)/i+(11+i-1)/i <= h
        // }
        // 因为速度 k 越小，总耗时越大；k 越大，总耗时越小，是**单调递减关系**，具备二分的条件
        // 我可以考虑用二分法实现速度区间快速筛选，内部循环依旧需要计算，减少了外部暴力循环的次数
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        int left = 1;
        int right = 0;
        int ans = 0;
        for (int pile : piles) {
            // 获取最大速度
            right = Math.max(right, pile);
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(piles, mid) <= h) {
                // 速度还可以尝试缩小，左侧二分
                right = mid - 1;
                ans = mid;
            } else {
                // 速度太慢，超时，右侧二分check(piles, mid) > h
                left = mid + 1;
            }

        }
        System.out.println(ans);
    }

    public static long check(int[] piles, int k) {
        long times = 0;
        for (int pile : piles) {
            // 计算当前速度吃香蕉需要花费多少时间（向上取整）
            times += (pile + k - 1) / k;
        }
        return times;
    }
}
