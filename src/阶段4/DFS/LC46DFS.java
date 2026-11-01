package 阶段4.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * LC46 全排列（副本法，无显式回溯）
 *
 * 难度：中等    标签：DFS / 回溯
 *
 * 题目：给定不含重复数字的数组 nums，返回所有可能的全排列。
 * 示例：nums = [1,2,3]  →  [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 思路：每层递归都「复制」一份新的 path 和 flag，在副本上修改，
 *       上层的数据自始至终不被改动，所以递归返回后不需要撤销（没有 remove / 回滚）。
 *       全排列的 for 循环每次都从 i = 0 开始（不同于组合的 start），
 *       靠 flag 判断某个数字是否已经在当前排列中使用过。
 * 复杂度：时间 O(n × n!)，空间 O(n × n!)（每个节点都复制了路径）
 *
 * 坑点：
 * - 不能用 start 控制，全排列顺序不同就是不同答案，必须每层从头遍历、用 flag 去重。
 * - 副本法的代价是每个递归节点都 new 一次 List 和 clone 一次数组，内存开销明显更大；
 *   数据量大时应改用回溯版（LC46DFSBACK）。
 * - 对照记忆：副本法 = 不改原状态，回溯法 = 改完记得改回来。
 */
public class LC46DFS {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        // ans：最终所有全排列
        List<List<Integer>> ans = new ArrayList<>();
        // path：当前正在构建的排列（初始为空）
        List<Integer> path = new ArrayList<>();
        // flag：标记某个数字是否已被选入当前排列
        boolean[] flag = new boolean[nums.length];

        dfs(flag, path, ans, nums);
        return ans;
    }

    /**
     * 副本法 DFS：每次递归都在副本上操作，不修改上层状态。
     *
     * @param flag 标记数组（本层只读，修改的是新克隆的副本）
     * @param path 当前排列路径（本层只读，添加元素的是新创建的副本）
     * @param ans  结果集合
     * @param nums 原始输入数组
     */
    static void dfs(boolean[] flag, List<Integer> path, List<List<Integer>> ans, int[] nums) {
        // 终止条件：路径长度等于数组长度，说明凑成了一条完整排列
        if (path.size() == nums.length) {
            // path 本身是独立对象，上层不会再去改它，可以直接放入答案
            ans.add(path);
            return;
        }

        // 全排列：每次都从 0 开始尝试所有数字，靠 flag 排除已用过的
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) {
                continue;  // 已经在当前排列里，跳过
            }

            // 复制一份全新的路径，上层 path 保持不变
            List<Integer> pathNew = new ArrayList<>(path);
            pathNew.add(nums[i]);

            // 克隆一份全新的标记，上层 flag 保持不变
            boolean[] newFlag = flag.clone();
            newFlag[i] = true;

            // 带着副本继续往下走
            dfs(newFlag, pathNew, ans, nums);
            // 这里没有撤销代码：原件从未被修改，无需回滚
        }
    }
}
