package cn.thinkjoy.dao.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.dao.impl.SimpleMyBatisDAO;
import cn.thinkjoy.dao.IRoleDAO;
import cn.thinkjoy.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/12/31<br/>
 *
 * @author xule
 * @since v0.0.1
 */
@Repository
public class RoleDAOImpl extends SimpleMyBatisDAO<Role, Long> implements IRoleDAO {
    @Override
    public Role findOne(@Param("property") String var1, @Param("value") Object var2, @Param("orderBy") String var3, @Param("sortBy") String var4) {
        return null;
    }


}
