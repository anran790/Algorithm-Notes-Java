package 阶段4.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LC78 子集（回溯法，标准模板）
 *
 * 难度：中等    标签：DFS / 回溯
 *
 * 题目：给定元素互不相同的数组 nums，返回所有子集（幂集）。
 * 示例：nums = [1,2,3]  →  [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]（共 8 个）
 *
 * 思路：全程只用一个 list 当路径草稿，递归前 add 选择、递归后 remove 撤销。
 *       每进一层都先把当前 list 收进答案（子集在每个节点都是合法解）。
 *       start 保证下标递增，不回头选，所以不会出现重复子集。
 * 复杂度：时间 O(2^n × n)，空间 O(n)（只用了递归栈和一份路径）
 *
 * 坑点：
 * - 收答案必须 new ArrayList<>(list)。直接 ans.add(list) 会把同一个引用存很多次，
 *   最后全变成空列表 —— 这是回溯法最经典的错误。
 * - add / remove 必须严格成对，写在递归的两侧。
 * - 与副本法（LC78DFS）相比省内存，但状态一旦漏撤销就会串味。
 */
public class LC78DFSBACK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 唯一共用的路径，相当于草稿纸
        List<Integer> list = new ArrayList<>();
        dfs(0, list, ans, nums);
        return ans;
    }

    /**
     * 回溯 DFS。
     *
     * @param start 本层起始下标，只能往后选
     * @param list  当前路径（共享，会被 add / remove 修改）
     * @param ans   所有子集
     * @param nums  原始数组
     */
    private static void dfs(int start, List<Integer> list, List<List<Integer>> ans, int[] nums) {
        // 快照：每层都是一个合法子集，先收再说
        ans.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {
            // 1. 做选择
            list.add(nums[i]);
            // 2. 递归：下一层从 i+1 开始
            dfs(i + 1, list, ans, nums);
            // 3. 撤销选择，恢复现场
            list.remove(list.size() - 1);
        }
    }
}
