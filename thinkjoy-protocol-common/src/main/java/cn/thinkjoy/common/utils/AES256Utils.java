package cn.thinkjoy.common.utils;

/**
 * AES256加密解密<br/>
 * 兼容各终端，参考：http://www.cocoachina.com/bbs/read.php?tid=129781
 * <p/>
 * 创建时间: 16/1/8<br/>
 *
 * @author xule
 * @since v0.0.1
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;

public class AES256Utils {

    public static final String BM = "UTF-8";

    /**
     * 密钥算法
     * java6支持56位密钥，bouncycastle支持64位
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法/工作模式/填充方式
     * <p/>
     * JAVA6 支持PKCS5PADDING填充方式
     * Bouncy castle支持PKCS7Padding填充方式
     */
    public static final String CIPHER_ALGORITHM = "AES";//AES/ECB/PKCS7Padding

    private static byte[] defaultKey = new byte[]{
            -54, 100, 122, 64, 98, 32, 123, 110, -95, 36, -93, -18, -49, -24, -66, -84};
//            0x08, 0x08, 0x04, 0x0b, 0x02, 0x0f, 0x0b, 0x0c,
//            0x01, 0x03, 0x09, 0x07, 0x0c, 0x03, 0x07, 0x0a,
//            0x04, 0x0f, 0x06, 0x0f, 0x0e, 0x09, 0x05, 0x01,
//            0x0a, 0x0a, 0x01, 0x09, 0x06, 0x07, 0x09, 0x0d};

    /**
     * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥
     * @return byte[] 二进制密钥
     */
    public static byte[] initkey() throws Exception {
        //实例化密钥生成器
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
        //初始化密钥生成器，AES要求密钥长度为128位、192位、256位
//      kg.init(256);
        kg.init(128);
        //生成密钥
        SecretKey secretKey = kg.generateKey();
        //获取二进制密钥编码形式
        return secretKey.getEncoded();
    }

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return Key 密钥
     */
    public static Key toKey(byte[] key) throws Exception {
        //实例化DES密钥
        //生成密钥
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 加密数据
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密后的数据
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        //还原密钥
        Key k = toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        //初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        //执行操作
        return cipher.doFinal(data);
    }

    public static byte[] encrypt(byte[] data) throws Exception {
        return encrypt(data, defaultKey);
    }

    public static byte[] encrypt(String data) throws Exception {
        return encrypt(data.getBytes(BM), defaultKey);
    }

    /**
     * 解密数据
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密后的数据
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        //欢迎密钥
        Key k = toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        //执行操作
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data) throws Exception {
        return decrypt(data, defaultKey);
    }

    public static String decrypt2str(byte[] data) throws Exception {
        return new String(decrypt(data, defaultKey), BM);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "AES中国雄起";
        System.out.println("原文：" + str);

        //初始化密钥
        byte[] key;
        try {
//            key = AES256Utils.initkey();
            key = defaultKey;
            System.out.print("密钥：");
            for (int i = 0; i < key.length; i++) {
                System.out.printf("%x", key[i]);
            }
            System.out.print("\n");
            //加密数据
            byte[] data = AES256Utils.encrypt(str.getBytes(), key);
            System.out.print("加密后：");
            for (int i = 0; i < data.length; i++) {
                System.out.printf("%x", data[i]);
            }
            System.out.print("\n");

            //解密数据
            data = AES256Utils.decrypt(data, key);
            System.out.println("解密后：" + new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}