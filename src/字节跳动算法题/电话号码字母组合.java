package 字节跳动算法题;

import java.util.ArrayList;
import java.util.List;

/**
 *  1. 电话号码字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 */
public class 电话号码字母组合 {
    public static void main(String[] args) {
        System.out.println(bfs("23"));
    }
    public static List<String> bfs(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;
        res.add("");
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (char ch : digits.toCharArray()) {
            List<String> temp = new ArrayList<>();
            String letters = map[ch - '0'];
            for (String s : res) {
                for (char c : letters.toCharArray()) {
                    temp.add(s + c);
                }
            }
            res = temp;
        }
        return res;
    }

}
