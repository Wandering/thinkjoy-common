package cn.thinkjoy.common.managerui.iauth.provider;

import java.util.List;

/**
 * Created by Michael on 11/13/14.
 * bundled + others
 */
public interface Bundled<T> {

    void setBundled(boolean bundled);

    boolean isBundled();

    void setBundledTo(T t);

    T getBundledTo();

    List<T> getOthers();

}
