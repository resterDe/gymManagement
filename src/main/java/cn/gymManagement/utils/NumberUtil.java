package cn.gymManagement.utils;

/**
 * 获取三位随机数
 */
public class NumberUtil {
    //获取三位随机整数
    public static String getNumber() {
        int i = (int) (Math.random() * 900 + 100);
        String myStr = Integer.toString(i);
        System.out.println(myStr);
        return myStr;
    }
}
