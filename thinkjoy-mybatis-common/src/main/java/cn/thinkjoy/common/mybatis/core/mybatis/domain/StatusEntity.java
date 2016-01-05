package cn.thinkjoy.common.mybatis.core.mybatis.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 状态记录对象接口
 * 
 * @author shadow
 * 
 * @param <PK>
 */
public interface StatusEntity<PK extends Serializable> extends Serializable {

	/** 活动状态 */
	public static final long ACTIVE = 1;
	/** 删除状态 */
	public static final long INACTIVE = 2;
	/** 锁定状态 */
	public static final long LOCKED = 3;

	public abstract PK getRecordStatus();

	public abstract void setRecordStatus(PK pk);

	public abstract PK getCreatorId();

	public abstract void setCreatorId(PK pk);

	public abstract PK getUpdaterId();

	public abstract void setUpdaterId(PK pk);

	public abstract Date getUpdateDate();

	public abstract void setUpdateDate(Date date);

	public abstract Date getCreateDate();

	public abstract void setCreateDate(Date date);

	public abstract PK getUpdateCount();

	public abstract void setUpdateCount(PK pk);

}
