package 字节跳动算法题;

import java.util.HashSet;

/**
 * 只出现一次的数字
 * 某个元素只出现一次以外，其余每个元素均出现两次，找出1次的
 * 输入：nums = [2,2,1]
 * 输出：1
 */
public class 只出现一次的数字 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }
        // 只剩一个元素
        System.out.println(set.iterator().next());
    }


}
