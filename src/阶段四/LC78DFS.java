package 阶段四;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 【说明】
 * 这是 DFS 无回溯版本：每次递归都新建一份独立的temp列表，不修改原list，不需要remove撤销（没有backtrack）
 * 核心规则：start参数保证只能往后选下标更大的元素，防止出现 [2,1] 这种重复排列
 */
public class LC78DFS {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        // 输出全部子集
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        // ans：存放最终所有子集结果
        List<List<Integer>> ans = new ArrayList<>();
        // list：当前正在探索的路径（不会被修改，只用来复制）
        List<Integer> list = new ArrayList<>();
        // 启动dfs，从下标0开始
        dfs(0, list, ans, nums);
        return ans;
    }

    /**
     * 深度优先搜索，无回溯版本
     * @param start 只能从start下标及之后挑选元素，不能回头选前面的
     * @param list 当前的路径集合（只读，不修改）
     * @param ans 保存所有子集答案
     * @param nums 原始输入数组
     */
    private static void dfs(int start, List<Integer> list, List<List<Integer>> ans, int[] nums) {
        // 把当前这条路径直接加入结果集，它本身就是一个合法子集
        ans.add(list);

        // 循环：从start开始，依次尝试选取后面每一个数字
        for (int i = start; i < nums.length; i++) {
            // 复制当前路径，生成全新的temp副本（关键：原list保持不变）
            List<Integer> temp = new ArrayList<>();
            temp.addAll(list);
            // 在副本上添加当前选中的数字nums[i]
            temp.add(nums[i]);
            // 递归往下搜：下一层只能从 i+1 往后选，保证下标递增、不回头
            dfs(i + 1, temp, ans, nums);
            // 没有 remove！不需要回溯，因为用的是全新temp，上层list不受任何影响
        }
    }
}
