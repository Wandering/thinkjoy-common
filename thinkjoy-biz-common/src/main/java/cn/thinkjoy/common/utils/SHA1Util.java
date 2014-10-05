package cn.thinkjoy.common.utils;

import java.security.MessageDigest;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14-9-1 下午9:55<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class SHA1Util {
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            	            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String encode(String str) {
        if (str == null) {
   	            return null;
   	        }
        try {
   	            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
   	            messageDigest.update(str.getBytes());
   	            return getFormattedText(messageDigest.digest());
   	        } catch (Exception e) {
   	            throw new RuntimeException(e);
   	        }

    }


    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {          buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }







    public static void main(String[] args) {
        String psw = SHA1Util.encode("123");
        System.out.println(psw);
    }
}
