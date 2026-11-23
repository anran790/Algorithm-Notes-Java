package 字节跳动算法题;

import java.util.Stack;

/**
 * 3.识别有效括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。
 * 3.每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 */
public class 识别有效括号 {
    public static void main(String[] args) {
        String s1 = "()[]{}";
        String s2 = "(]";
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 左括号入栈
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 右括号：栈空直接失败；取出栈顶比对
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(')
                        || (c == '}' && top != '{')
                        || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        // 全部遍历完，栈必须是空（不能剩左括号）
        return stack.isEmpty();

    }
}
