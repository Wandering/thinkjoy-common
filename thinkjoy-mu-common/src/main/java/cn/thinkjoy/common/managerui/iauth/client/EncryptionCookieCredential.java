package cn.thinkjoy.common.managerui.iauth.client;

import cn.thinkjoy.common.managerui.iauth.core.Credential;
import cn.thinkjoy.security.utils.Cryptos;
import cn.thinkjoy.security.utils.Digests;
import cn.thinkjoy.security.utils.Encodes;

/**
 * Created by Michael on 11/11/14.
 * 随便写的一个加密的cookie验证
 */
public class EncryptionCookieCredential extends Credential{

    public static String generateKey(String input){
        return Encodes.encodeHex(
                Digests.md5(input.getBytes()))
                .substring(0, 5);
    }
    public static String generateSecret(){
        return Encodes.encodeBase64(Cryptos.generateSecret(6));
    }
}
