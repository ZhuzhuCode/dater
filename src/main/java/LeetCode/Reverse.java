package LeetCode;

/**
 * Created by zhujr on 2018/11/7.
 * 整数反转
 */
public class Reverse {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > (Integer.MAX_VALUE / 10) || (rev == (Integer.MAX_VALUE / 10) && pop > 7)) {
                return 0;
            } else if (rev < (Integer.MIN_VALUE / 10) || (rev == (Integer.MIN_VALUE / 10) && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        Reverse r = new Reverse();
        System.out.println(r.reverse(-2147483412));
    }
}
