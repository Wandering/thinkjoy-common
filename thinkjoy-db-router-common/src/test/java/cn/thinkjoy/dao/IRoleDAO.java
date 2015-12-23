package cn.thinkjoy.dao;

import cn.thinkjoy.cloudstack.router.annotation.DBShardAnnotation;
import cn.thinkjoy.domain.Role;
import org.apache.ibatis.annotations.Param;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/27 下午8:33<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
//@DBShardAnnotation(ruleKey = "sr", ruleProps = {"topic", "seqs", "seqe"})
public interface IRoleDAO {
    Role findOne(@Param("property") String var1, @Param("value") Object var2, @Param("orderBy") String var3, @Param("sortBy") String var4);

    int insert(Role var1);

}
