package 阶段4.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LC77 组合（副本法，无显式回溯）
 *
 * 难度：中等    标签：DFS / 回溯
 *
 * 题目：给定 n 和 k，返回 [1, n] 范围内所有 k 个数的组合（顺序不同视为同一组合）。
 * 示例：n = 4, k = 2  →  [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 *
 * 思路：与 LC78 子集同一套骨架，区别是「什么时候收答案」：
 *       子集每进一层都收，组合只在 path.size() == k 时才收。
 *       start 参数保证只能选下标 >= start 的元素，从而避免 [2,1] 这种重复组合。
 *       本版用副本法：每层复制一份新 path，原 path 不动，所以不需要 remove。
 * 复杂度：时间 O(C(n,k) × k)，空间 O(C(n,k) × k)
 *
 * 坑点：
 * - 递归调用必须把「新建的 pathNew」传下去，传回原来的 path 会导致子集永远为空 ——
 *   这个坑非常隐蔽，因为代码能编译、能运行，只是结果不对。
 * - start 传 i + 1（不是 i），否则同一个数字会被重复选取。
 * - 组合不需要 flag 数组，因为 start 已经保证不会回头选。
 * - 可剪枝：i <= list.size() - (k - path.size())，剩余元素不够时提前结束。
 */
public class LC77DFS {
    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        // 把 1..n 装进列表，方便按下标遍历
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
     * 副本法 DFS：每层新建 path 副本，不修改上层 path。
     *
     * @param start 本层只能从 start 下标开始选，保证下标递增、不重复
     * @param list  候选数字 1..n
     * @param ans   结果集合
     * @param path  当前组合（只读，添加元素的是它的副本）
     * @param k     目标组合长度
     */
    public static void dfs(int start, List<Integer> list, List<List<Integer>> ans, List<Integer> path, int k) {
        // 组合长度够了就收集答案（子集是每层都收，这里是够 k 个才收）
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            // 复制当前 path，得到全新副本
            List<Integer> pathNew = new ArrayList<>(path);
            pathNew.add(list.get(i));
            // 注意：往下传的是 pathNew，不是 path（传 path 会导致结果永远为空）
            dfs(i + 1, list, ans, pathNew, k);
            // 副本法不需要撤销：path 自始至终没被改过
        }
    }
}
