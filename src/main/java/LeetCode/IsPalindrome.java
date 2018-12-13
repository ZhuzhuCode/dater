package LeetCode;

/**
 * Created by zhujr on 2018/11/7.
 * 判断是否为回文数字
 */
public class IsPalindrome {
        public boolean isPalindrome(int x) {
        //反转一半的数字
        boolean flag = false;
        int rev = 0;
        while (x > rev && x > 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        if(x==rev)
            flag = true;
        return flag;
    }
//    public boolean isPalindrome(int x) {
//        //反转全部的数字
//        boolean flag = false;
//        int rev = 0;int org = x;
//        while (x > 0) {
//            int pop = x % 10;
//            x /= 10;
//            rev = rev * 10 + pop;
//        }
//        if (org == rev)
//            flag = true;
//        return flag;
//    }

    public static void main(String[] args) {
        IsPalindrome i = new IsPalindrome();
        System.out.println(i.isPalindrome(121));
    }
}
