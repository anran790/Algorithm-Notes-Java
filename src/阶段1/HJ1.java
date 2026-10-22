package 阶段1;

import java.util.Scanner;
/**
HJ1 字符串最后一个单词的长度
题目
题解(2k)
讨论(3k)
排行
面经
简单  通过率：36.59%  时间限制：1秒  空间限制：32M
知识点
字符串
warning 校招时部分企业笔试将禁止编程题跳出页面，为提前适应，练习时请使用在线自测，而非本地IDE。
描述
对于给定的若干个单词组成的句子，每个单词均由大小写字母混合构成，单词间使用单个空格分隔。输出最后一个单词的长度。
输入描述：
在一行上输入若干个字符串，每个字符串代表一个单词，组成给定的句子。
除此之外，保证每个单词非空，由大小写字母混合构成，且总字符长度不超过
1
0
3
10
3
  。
输出描述：
在一行上输出一个整数，代表最后一个单词的长度。
示例1
输入：
HelloNowcoder
复制
输出：
13
复制
说明：
在这个样例中，最后一个单词是
"HelloNowcoder"
"HelloNowcoder" ，长度为
13
13 。
示例2
输入：
A B C D
复制
输出：
1
 */
public class HJ1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arr = str.split(" ");
        System.out.println(arr[arr.length-1].length());
    }
}

