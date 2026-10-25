package 阶段4.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 【说明】
 * DFS + Backtracking 回溯版本（标准模板）
 * 全程只使用同一份 path（路径集合）、同一份 flag 标记数组，复用状态，节省内存
 * for循环每次从 i=0 从头遍历全部元素；flag数组标记当前排列里哪些下标已经用过，避免同一个数字重复选取
 * 顺序不同视为不同答案，所以不像子集用start限制下标只能往后走
 */
public class LC46DFSBACK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        // ans：存放最终所有全排列结果
        List<List<Integer>> ans = new ArrayList<>();
        // path：共用的路径草稿，会不断 add、remove 修改
        List<Integer> path = new ArrayList<>();
        // flag：标记数组，flag[i]=true 代表 nums[i] 已经选入当前 path
        boolean[] flag = new boolean[nums.length];

        dfs(flag, path, ans, nums);
        return ans;
    }

    /**
     * 回溯dfs，全排列
     * @param flag 标记数组（共享，会修改恢复）
     * @param path 当前排列路径（共享草稿）
     * @param ans 答案集合
     * @param nums 原始数组
     */
    static void dfs(boolean[] flag, List<Integer> path, List<List<Integer>> ans, int[] nums) {
        // 终止条件：path长度等于数组长度，说明凑齐一条完整排列
        if (path.size() == nums.length) {
            // 快照！new一份新list存入结果。直接add(path)会因为后续修改path污染已存入的数据
            ans.add(new ArrayList<>(path));
            return;
        }

        // 全排列：i从0从头遍历所有数字，不是start
        for (int i = 0; i < nums.length; i++) {
            // 当前下标数字已经被使用，跳过
            if (flag[i]) {
                continue;
            }

            // 1. 做选择：把当前数字加入路径，打上标记
            path.add(nums[i]);
            flag[i] = true;

            // 2. 递归深入，继续选下一个位置的数字
            dfs(flag, path, ans, nums);

            // 3. 回溯 backtrack：撤销刚才的选择，恢复到进入本次循环前的状态
            path.remove(path.size() - 1);
            flag[i] = false;
        }
    }
}
