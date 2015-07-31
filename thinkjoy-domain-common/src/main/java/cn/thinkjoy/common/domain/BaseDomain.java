package cn.thinkjoy.common.domain;

import java.io.Serializable;

/**
 * 
 * 基类 IDEntity
 * <p/>
 * 创建时间: 2014年4月17日 下午2:06:32 <br/>
 *
 * @author ktian
 * @version 
 * @since v0.0.1
 */
public class BaseDomain<T> implements Serializable {

    private static final long serialVersionUID = 6667600871541308622L;
    // ID
    private T              id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
