package cn.thinkjoy.common.managerui.dao;

import cn.thinkjoy.common.managerui.domain.DatagroupData;
import cn.thinkjoy.common.managerui.domain.Resource;
import cn.thinkjoy.common.managerui.domain.ResourceAction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 权限专有的DAO
 * <p/>
 * 创建时间: 14-10-3 下午11:52<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IPermissionDAO {
    public List<Resource> getResByPerm(Object userId);

    public List<Resource> getResByPerm(@Param("userId")Object userId,@Param("product")String product);

    public List<ResourceAction> getResActionByPerm(@Param("condition")Map<String, Object> params);

    public List<DatagroupData> getDataByPerm(@Param("condition")Map<String, Object> params);
}
