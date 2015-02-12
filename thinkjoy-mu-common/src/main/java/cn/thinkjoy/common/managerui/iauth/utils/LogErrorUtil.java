package cn.thinkjoy.common.managerui.iauth.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Michael on 2/12/15.
 */
public class LogErrorUtil {

    public static String toString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try
        {
            t.printStackTrace(pw);
            return sw.toString();
        }
        finally
        {
            pw.close();
        }

    }
}
