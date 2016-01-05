package cn.thinkjoy.dao;

import cn.thinkjoy.common.dao.IBaseDAO;
import org.apache.ibatis.annotations.Param;
import cn.thinkjoy.domain.Role;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/27 下午8:33<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IRoleDAO extends IBaseDAO<Role> {
    Role findOne(@Param("property") String var1, @Param("value") Object var2, @Param("orderBy") String var3, @Param("sortBy") String var4);

}
