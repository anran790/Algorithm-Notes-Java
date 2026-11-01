package 阶段3;

/**
 * LC875 爱吃香蕉的珂珂
 *
 * 难度：中等    标签：二分答案
 *
 * 题目：有 n 堆香蕉 piles[i]，珂珂每小时选一堆吃掉 k 根（不足 k 根则吃完这堆，本小时不再吃别的）。
 *       求在 h 小时内吃完所有香蕉的最小速度 k。
 * 示例：piles = [3,6,7,11], h = 8  →  4
 *       piles = [30,11,23,4,20], h = 5  →  30
 *
 * 思路：从「暴力枚举速度」升级为「二分速度」。
 *       速度 k 越小总耗时越长，k 越大总耗时越短 —— 这种单调关系是二分的前提。
 *       于是对 k 在 [1, max(piles)] 上二分：
 *       check(k) 求出按速度 k 吃完的总耗时，若 ≤ h 说明速度够快，
 *       可以尝试更小的速度（right = mid - 1）并记录答案；否则加大速度。
 *       单堆耗时 = ⌈pile / k⌉，整数写法是 (pile + k - 1) / k。
 * 复杂度：时间 O(n log max(piles))，空间 O(1)
 *
 * 坑点：
 * - 向上取整别写成 pile / k，会少算一小时，样例会挂。
 * - 上界取 max(piles) 即可：再快也不会让单堆耗时少于 1 小时，没有意义。
 * - 题目隐含 h ≥ piles.length，否则永远吃不完；本解法无需特判，check 会自然返回 false。
 * - 总耗时要用 long，n × 每堆小时数可能超出 int 范围。
 */
public class LC875 {
    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        int left = 1;   // 速度下界：至少 1 根/小时
        int right = 0;  // 速度上界：最大的一堆，再快也没意义
        int ans = 0;

        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        // 在速度区间上二分，找「最小的可行速度」
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(piles, mid) <= h) {
                // 当前速度可行，先记下来，再试试更慢的（更小的 k）
                ans = mid;
                right = mid - 1;
            } else {
                // 太慢导致超时，必须加速
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    /**
     * 按速度 k 吃完所有香蕉需要的总小时数。
     */
    public static long check(int[] piles, int k) {
        long times = 0;
        for (int pile : piles) {
            // 向上取整：ceil(pile / k) == (pile + k - 1) / k
            times += (pile + k - 1) / k;
        }
        return times;
    }
}
