package 阶段4.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LC77 组合（回溯法，标准模板）
 *
 * 难度：中等    标签：DFS / 回溯
 *
 * 题目：给定 n 和 k，返回 [1, n] 范围内所有 k 个数的组合。
 * 示例：n = 4, k = 2  →  [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 *
 * 思路：共用一份 path，递归前 add、递归后 remove。
 *       start 保证下标只往后走，因此不会出现 [2,1] 这种重复组合，也就不需要 flag。
 *       与 LC78 子集的区别：子集每层都收答案，组合只收长度为 k 的。
 * 复杂度：时间 O(C(n,k) × k)，空间 O(k)（递归栈深度）
 *
 * 坑点：
 * - 存答案要 new ArrayList<>(path)，否则后面 remove 会污染已存结果。
 * - add / remove 必须成对；remove 删的是最后一个元素。
 * - start 传 i + 1，传 i 会让同一数字被重复选。
 * - 剪枝：若剩余元素数量不足以凑满 k 个，可以提前 break，能显著提速。
 */
public class LC77DFSBACK {
    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, list, ans, path, k);
        return ans;
    }

    /**
     * 回溯 DFS：共用 path，靠 add / remove 维护状态。
     *
     * @param start 本层起始下标，保证不回头选
     * @param list  候选数字 1..n
     * @param ans   结果集合
     * @param path  当前组合（共享，会被 add / remove）
     * @param k     目标长度
     */
    public static void dfs(int start, List<Integer> list, List<List<Integer>> ans, List<Integer> path, int k) {
        // 收答案：长度达到 k，快照后返回
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            // 1. 做选择
            path.add(list.get(i));
            // 2. 递归：下一层从 i+1 开始
            dfs(i + 1, list, ans, path, k);
            // 3. 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
