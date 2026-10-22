package 阶段1;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 校招时部分企业笔试将禁止编程题跳出页面，为提前适应，练习时请使用在线自测，而非本地IDE。
 * 描述
 * 数据表中，一条记录包含表索引和数值两个值。请对表索引相同的记录进行合并（即将相同索引的数值进行求和运算），随后按照索引值的大小从小到大依次输出。
 * 输入描述：
 * 第一行输入一个整数 n(1≦n≦500)n(1≦n≦500) 代表数据表的记录数。
 * 此后 nn 行，第 ii 行输入两个整数 xi,yi(0≦xi≦11 111 111; 1≦yi≦105)xi​,yi​(0≦xi​≦11111111; 1≦yi​≦105) 代表数据表的第 ii 条记录的索引和数值。
 * 输出描述：
 * 一共若干行（视输入数据变化），第 ii 行输出两个整数，代表合并后数据表中第 ii 条记录的索引和数值。
 * 输入：
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 复制
 * 输出：
 * 0 3
 * 1 2
 * 3 4
 * 复制
 */
public class HJ8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();
        in.nextLine();
        while (n-- > 0) {
            String[] str = in.nextLine().split(" ");
            int key = Integer.parseInt(str[0]);
            int value = Integer.parseInt(str[1]);
            map.put(key, map.getOrDefault(key, 0) + value);
        }
        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }
}
