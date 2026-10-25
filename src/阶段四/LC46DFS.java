package 阶段四;

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
 * <p>
 * 【说明】
 * 全排列 无回溯版本！
 * 核心特点：不共用path、flag标记数组。每次递归前复制一份全新副本，在副本上修改。
 * 上层原来的 path、flag 不会被改动，因此不需要 remove、不需要撤销状态（没有backtrack动作）
 * 全排列规则：顺序不同代表不同答案，所以for循环每次从i=0从头遍历，依靠flag标记数字是否已经在当前排列中使用
 */
public class LC46DFS {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        // 输出所有全排列结果
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        // ans：保存最终所有全排列
        List<List<Integer>> ans = new ArrayList<>();
        // path：当前正在探索的排列路径（初始为空）
        List<Integer> path = new ArrayList<>();
        // flag：标记数组，标记下标对应的数字是否已经被选取
        boolean[] flag = new boolean[nums.length];

        dfs(flag, path, ans, nums);
        return ans;
    }

    /**
     * DFS 无回溯实现全排列
     *
     * @param flag 标记数组副本，true代表该下标数字已经选入当前path
     * @param path 当前排列路径副本
     * @param ans  结果集合
     * @param nums 原始输入数组
     */
    static void dfs(boolean[] flag, List<Integer> path, List<List<Integer>> ans, int[] nums) {
        // 终止条件：path长度等于数组全长，说明已经凑成一条完整排列
        if (path.size() == nums.length) {
            // 直接存入答案，path本身就是独立新建出来的对象，不需要再new快照
            ans.add(path);
            return;
        }

        // 全排列：i从0从头遍历全部元素，不是start！可以选前面下标（只要没被标记）
        for (int i = 0; i < nums.length; i++) {
            // flag[i]=true，说明这个数字已经在当前排列里，跳过，防止同一个排列重复选同一个数
            if (flag[i]) {
                continue;
            }
            // 复制当前path，生成全新的pathNew副本，上层path保持不变
            List<Integer> pathNew = new ArrayList<>();
            pathNew.addAll(path);
            // 在副本上加入当前选中的数字
            pathNew.add(nums[i]);

            // 克隆flag数组，得到全新标记副本newFlag，上层flag不受影响
            boolean[] newFlag = flag.clone();
            // 在副本中标记：这个数字已经被选用
            newFlag[i] = true;
            // 递归深入，继续挑选下一个数字
            dfs(newFlag, pathNew, ans, nums);
            // 没有撤销代码！上层的path、flag自始至终没有改动，不需要回溯
        }
    }
}
