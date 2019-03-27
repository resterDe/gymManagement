package cn.gymManagement.utils;
import java.util.Random;

/**
 * 图形验证码工具类
 * 实现图形验证码
 * 随机获取字符串
 */
public class VerificationCodeUtil {
    //定义随机字符串范围
    private static final String code="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * length表示字符数量
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        Random random=new Random();
        StringBuffer stringBuffer=new StringBuffer();
        for (int i=0;i<length;i++){
            //调用0-62之间的随机数
            int number=random.nextInt(62);
            stringBuffer.append(code.charAt(number));
        }
        return stringBuffer.toString();
    }
}
