package cn.thinkjoy;

import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by xjli on 15-12-9.
 */
public class ReadEnv {

    public static String readEnv() {


        String result = "";
        File file = new File("/etc/env");
        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                result = br.readLine();
            } catch (IOException e) {
                return "";
            }
        } else {
            try {
                result = DynConfigClientFactory.getClient().getConfig("cmc", "cmc", "common", "env");
            } catch (Exception e) {
                return "";
            }
        }

        return result;

    }
}
