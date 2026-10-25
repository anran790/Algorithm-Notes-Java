package 阶段四.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码
 * 测试用例
 * 测试用例
 * 测试结果
 * 77. 组合
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 示例 2：
 * <p>
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * 此题和78题类型一样，详解可以参考LC78
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

    public static void dfs(int start, List<Integer> list, List<List<Integer>> ans, List<Integer> path, int k) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < list.size(); i++) {
            path.add(list.get(i));
            dfs(i + 1, list, ans, path, k);
            path.remove(path.size() - 1);
        }
    }
}
