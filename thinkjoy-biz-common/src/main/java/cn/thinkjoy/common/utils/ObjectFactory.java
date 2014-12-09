package cn.thinkjoy.common.utils;

/**
 * 对象工厂
 * <p/>
 * 创建时间: 14/12/4 下午9:30<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class ObjectFactory {
    private static class ObjectBuild{
        private static Object instance = new Object();
    }

    /**
     * 获取一个空对象的单例
     * @return
     */
    public static Object getSingle(){
        return ObjectBuild.instance;
    }
}
