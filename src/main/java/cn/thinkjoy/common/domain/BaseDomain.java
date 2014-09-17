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
public class BaseDomain implements Serializable {

    // ID
    private long              id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
