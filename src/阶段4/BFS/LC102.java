package 阶段4.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * 中等
 * 相关标签：树、广度优先搜索、二叉树
 * 相关企业
 * 提示
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 */

/**
 * 二叉树节点定义
 */
class TreeNode {
    int val;
    TreeNode left;  // 左子节点引用
    TreeNode right; // 右子节点引用

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class LC102 {

    public static void main(String[] args) {
        // 手动构建测试树：[3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        // 调用层序遍历方法
        List<List<Integer>> lists = levelOrder(root);
        // 打印最终结果
        System.out.println(lists);
    }

    /**
     * BFS 层序遍历二叉树
     * @param root 二叉树根节点
     * @return 按层封装的节点值集合，每层单独一个 List
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 存放最终结果：每一层是一个 List
        List<List<Integer>> list = new ArrayList<>();
        // 边界：空树直接返回空集合
        if (root == null) {
            return list;
        }

        // 队列：BFS 核心，用来保存待访问的节点
        Queue<TreeNode> qu = new LinkedList<>();
        // 根节点先入队，作为遍历起点
        qu.add(root);

        // 队列不为空，说明还有节点没有遍历
        while (!qu.isEmpty()) {
            // 记录当前这一层一共有多少节点（关键！用来区分层）
            Integer size = qu.size();
            // 保存当前这一层的节点值
            List<Integer> cur = new ArrayList<>();

            // 固定循环 size 次：只处理【当前这一层】的所有节点
            for (int i = 0; i < size; i++) {
                // 队首节点出队
                TreeNode node = qu.poll();
                // 当前节点的值加入本层集合
                cur.add(node.val);

                // 左孩子不为空，入队，留给下一轮 while（下一层）处理
                if (node.left != null) {
                    qu.add(node.left);
                }
                // 右孩子不为空，入队
                if (node.right != null) {
                    qu.add(node.right);
                }
            }
            // 当前一层收集完成，放入总结果
            list.add(cur);
        }
        return list;
    }
}
