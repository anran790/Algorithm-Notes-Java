package 字节跳动算法题;

/**
 * 4.两个字符串相加
 * 别名：大数相加
 * 描述：给两个大整数, 用字符串表示, 比如”21543655", "4332656442", 都可能超过1万位, 写一个函数输出他们之和.
 * 需要自己实现加法过程, 不能用某些语言自带的高精度加法函数.
 * 输入："21543655”, "4332656442"
 * 输出："4354200097"
 */
public class 两个字符串相加 {
    public static void main(String[] args) {
        System.out.println(addStr("21543655", "4332656442"));
    }

    public static String addStr(String num1, String num2) {
        int m = num1.length() - 1;
        int n = num2.length() - 1;
        int car = 0;
        StringBuilder sb = new StringBuilder();
        while (m >= 0 || n >= 0 || car > 0) {
            int a = m >= 0 ? num1.charAt(m) - '0' : 0;
            int b = n >= 0 ? num2.charAt(n) - '0' : 0;
            int sum = a + b + car;
            car = (a + b + car) / 10;
            sb.append(sum % 10);
            m--;
            n--;
        }

        return sb.reverse().toString();
    }
}
