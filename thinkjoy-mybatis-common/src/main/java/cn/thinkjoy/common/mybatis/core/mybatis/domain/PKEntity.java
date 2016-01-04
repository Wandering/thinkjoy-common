package cn.thinkjoy.common.mybatis.core.mybatis.domain;

import java.io.Serializable;

/**
 * @author shadow
 */
public interface PKEntity<PK extends Serializable> extends Serializable {

	public abstract void setId(PK id);

	public abstract PK getId();

}
