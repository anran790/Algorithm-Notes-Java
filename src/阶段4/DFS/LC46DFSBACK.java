package 阶段4.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LC46 全排列（回溯法，标准模板）
 *
 * 难度：中等    标签：DFS / 回溯
 *
 * 题目：给定不含重复数字的数组 nums，返回所有可能的全排列。
 * 示例：nums = [1,2,3]  →  [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 思路：全程复用同一份 path 和 flag，递归前「做选择」，递归后「撤销选择」。
 *       三段式：path.add / flag 置位 → dfs → path.remove / flag 复位。
 *       每层 for 都从 0 开始，靠 flag 排除已经用过的数字。
 * 复杂度：时间 O(n × n!)，空间 O(n)（不计结果集，只用了递归栈和一份 path）
 *
 * 坑点：
 * - 存入答案时必须 new ArrayList<>(path)：直接 add(path) 存的是引用，
 *   后续 remove 会把已经存进结果的数据一起改掉，最后全是空列表。
 * - add 和 remove 必须成对出现，且 remove 的位置是 path.size() - 1（删最后一个）。
 * - flag 复位不能漏，漏了就相当于这个数字被永久占用，后续分支全错。
 * - 与副本法（LC46DFS）对比：回溯省内存，但状态管理容易出错。
 */
public class LC46DFSBACK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        // ans：最终所有全排列
        List<List<Integer>> ans = new ArrayList<>();
        // path：唯一共用的路径容器，会被反复 add / remove
        List<Integer> path = new ArrayList<>();
        // flag：flag[i] = true 表示 nums[i] 已在本条排列中
        boolean[] flag = new boolean[nums.length];

        dfs(flag, path, ans, nums);
        return ans;
    }

    /**
     * 回溯 DFS：共用 path 和 flag，靠「做选择 / 撤销选择」保证状态正确。
     *
     * @param flag 标记数组（共享，修改后必须恢复）
     * @param path 当前排列路径（共享草稿纸）
     * @param ans  结果集合
     * @param nums 原始数组
     */
    static void dfs(boolean[] flag, List<Integer> path, List<List<Integer>> ans, int[] nums) {
        // 终止条件：凑齐一条完整排列
        if (path.size() == nums.length) {
            // 必须快照！否则后续 remove 会污染这条已存入的答案
            ans.add(new ArrayList<>(path));
            return;
        }

        // 每次都从 0 开始，靠 flag 排除已用过的数字
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) {
                continue;
            }

            // 1. 做选择
            path.add(nums[i]);
            flag[i] = true;

            // 2. 递归深入，选下一个位置的数字
            dfs(flag, path, ans, nums);

            // 3. 撤销选择，恢复到进入本轮循环前的状态
            path.remove(path.size() - 1);
            flag[i] = false;
        }
    }
}
