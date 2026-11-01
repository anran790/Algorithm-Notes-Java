package 阶段1;

import java.util.Scanner;

/**
 * HJ1 字符串最后一个单词的长度
 *
 * 难度：简单    标签：字符串
 *
 * 题目：给定一行由单个空格分隔的单词，单词仅由大小写字母组成，输出最后一个单词的长度。
 * 示例：输入 "HelloNowcoder" → 13；输入 "A B C D" → 1
 *
 * 思路：按空格切分，取数组最后一个元素的长度。
 * 复杂度：时间 O(n)，空间 O(n)
 *
 * 坑点：
 * - 本题保证单词间只有单个空格，所以 split(" ") 够用；
 *   若存在连续空格，应改用 split("\\s+")，否则会切出空串。
 * - 若输入行末尾多了空格，split 后最后一个元素是空串，长度会算成 0（本题保证不会）。
 */
public class HJ1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arr = str.split(" ");
        System.out.println(arr[arr.length - 1].length());
    }
}
