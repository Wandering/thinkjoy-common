package cn.thinkjoy.dao;

import cn.thinkjoy.common.mybatis.core.mybatis.dao.MyBatisDAO;
import cn.thinkjoy.domain.Role;
import com.qtone.mh.item.entity.AdcProduct;
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
public interface IRoleDAO extends MyBatisDAO<Role, Long> {
    Role findOne(@Param("property") String var1, @Param("value") Object var2, @Param("orderBy") String var3, @Param("sortBy") String var4);

}
