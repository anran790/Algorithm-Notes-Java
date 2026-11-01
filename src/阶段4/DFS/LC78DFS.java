package 阶段4.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LC78 子集（副本法，无显式回溯）
 *
 * 难度：中等    标签：DFS / 回溯
 *
 * 题目：给定元素互不相同的数组 nums，返回所有子集（幂集），子集之间不重复。
 * 示例：nums = [1,2,3]  →  [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]（共 8 个）
 *
 * 思路：每进一层就「复制」一份新列表，在副本上追加元素再递归；原列表不动，
 *       所以不需要 remove 撤销。
 *       每层都把当前路径收入答案 —— 这是子集与组合/排列最大的不同：
 *       子集在树的每个节点都是合法答案，而组合/排列只在叶子节点收。
 *       start 保证下标只往后选，避免 [2,1] 与 [1,2] 重复。
 * 复杂度：时间 O(2^n × n)，空间 O(2^n × n)（每个节点都复制了路径）
 *
 * 坑点：
 * - ans.add(list) 直接放当前列表是安全的，因为 list 之后不会被修改（每次都新建副本）；
 *   如果改用回溯法，就必须 new ArrayList<>(list)。
 * - start 传 i + 1，保证组合内部下标递增。
 * - 副本法写起来简单但内存开销大，2^n 个节点各复制一次列表，n 大了会明显变慢。
 */
public class LC78DFS {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        // 输出全部子集，共 2^3 = 8 个
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 当前路径（本版只读，永远不会被修改）
        List<Integer> list = new ArrayList<>();
        dfs(0, list, ans, nums);
        return ans;
    }

    /**
     * 副本法 DFS。
     *
     * @param start 本层只能从 start 及之后挑选，保证不回头
     * @param list  当前路径（只读）
     * @param ans   所有子集
     * @param nums  原始数组
     */
    private static void dfs(int start, List<Integer> list, List<List<Integer>> ans, int[] nums) {
        // 子集：每个节点都是合法答案，进来先收
        ans.add(list);

        for (int i = start; i < nums.length; i++) {
            // 复制当前路径，生成全新副本（关键：原 list 保持不变）
            List<Integer> temp = new ArrayList<>(list);
            temp.add(nums[i]);
            // 下一层只能从 i+1 往后选
            dfs(i + 1, temp, ans, nums);
            // 没有 remove，因为改的是副本，上层 list 没被动过
        }
    }
}
