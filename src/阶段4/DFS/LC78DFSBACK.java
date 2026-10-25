package 阶段4.DFS;

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
 * DFS + Backtracking 回溯版本！
 * 全程只使用同一个list（路径容器），递归前add选择，递归返回后remove撤销选择（回溯），节省内存，是标准模板写法
 * start：控制只能选下标 >= start 的元素，不回头，避免生成 [2,1] 这类重复组合
 */
public class LC78DFSBACK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        // 打印所有子集
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        // ans：保存最终全部子集结果
        List<List<Integer>> ans = new ArrayList<>();
        // list：唯一共用的路径，相当于草稿纸，不断修改、恢复
        List<Integer> list = new ArrayList<>();
        // 从下标0开始搜索
        dfs(0, list, ans, nums);
        return ans;
    }

    /**
     * 回溯dfs
     * @param start 只能从start下标往后选，不选前面已经跳过的数字
     * @param list  当前路径（共用，会被add/remove修改）
     * @param ans   答案集合
     * @param nums  原始数组
     */
    private static void dfs(int start, List<Integer> list, List<List<Integer>> ans, int[] nums) {
        // 快照！new一份新list存入答案。如果直接add(list)，后面list修改会污染已经存进去的数据
        ans.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {
            // 1. 做选择：把当前数字加入路径
            list.add(nums[i]);
            // 2. 递归深入探索：下一层只能从 i+1 往后选
            dfs(i + 1, list, ans, nums);
            // 3. 回溯 backtrack：撤销刚才的选择，恢复list到进入本次循环前的状态
            list.remove(list.size() - 1);
        }
    }
}
