package cn.thinkjoy.cn.thinkjoy.helpers;

//import cn.thinkjoy.common.managerui.HashedDataSource;
import cn.thinkjoy.common.managerui.dao.IRoleDAO;
import cn.thinkjoy.common.managerui.domain.Role;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14/12/13 下午5:45<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springme.xml")
public class CommonTest {
    @Autowired
    private IRoleDAO roleDAO;

    @Test
    public void test(){

//        HashedDataSource.DynamicDataSourceHolder.putDataSource("ds2");

        System.out.println(roleDAO.findOne("description", 1, null, null).getId());
        System.out.println(roleDAO.findOne("description", 1, "id", SqlOrderEnum.ASC.getAction()).getId());
        System.out.println(roleDAO.findOne("description", 1, "id", SqlOrderEnum.DESC.getAction()).getId());

        Map<String, Object> params = Maps.newHashMap();
        params.put("description", 1);
        System.out.println(roleDAO.queryOne(params, null, null).getId());
        System.out.println(roleDAO.queryOne(params, "id", SqlOrderEnum.ASC.getAction()).getId());
        System.out.println(roleDAO.queryOne(params, "id", SqlOrderEnum.DESC.getAction()).getId());

        List<Role> roleList = roleDAO.findList("description", 1, null, null);
        print(roleList);
        roleList = roleDAO.findList("description", 1, "id", SqlOrderEnum.ASC.getAction());
        print(roleList);
        roleList = roleDAO.findList("description", 1, "id", SqlOrderEnum.DESC.getAction());
        print(roleList);


        roleList = roleDAO.findAll(null, null);
        print(roleList);
        roleList = roleDAO.findAll("id", SqlOrderEnum.ASC.getAction());
        print(roleList);
        roleList = roleDAO.findAll("id", SqlOrderEnum.DESC.getAction());
        print(roleList);

        Map<String, Object> innerParams = Maps.newHashMap();
        innerParams.put("op", "=");
        innerParams.put("data", "1");
        params.put("groupOp", "and");
        params.put("description", innerParams);

        roleList = roleDAO.queryPage(params, 0, 10, null, null);
        print(roleList);
        roleList = roleDAO.queryPage(params, 0, 10, "id", SqlOrderEnum.ASC.getAction());
        print(roleList);
        roleList = roleDAO.queryPage(params, 0, 10, "id", SqlOrderEnum.DESC.getAction());
        print(roleList);

        System.out.println(roleDAO.count(params));

        params = Maps.newHashMap();
        params.put("description", 1);
        roleList = roleDAO.like(params, null, null);
        print(roleList);
        roleList = roleDAO.like(params, "id", SqlOrderEnum.ASC.getAction());
        print(roleList);
        roleList = roleDAO.like(params, "id", SqlOrderEnum.DESC.getAction());
        print(roleList);

        roleList = roleDAO.queryList(params, null, null);
        print(roleList);
        roleList = roleDAO.queryList(params, "id", SqlOrderEnum.ASC.getAction());
        print(roleList);
        roleList = roleDAO.queryList(params, "id", SqlOrderEnum.DESC.getAction());
        print(roleList);

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("sid", 1);
        roleDAO.queryOne(paramMap, null, null);
        System.out.println(roleDAO.findAll(null, null));

        Role role = new Role();
        role.setId(1L);
        role.setCreateDate(System.currentTimeMillis());
        int affectRow = roleDAO.update(role);

        System.out.println(affectRow);
    }

    private void print(List<Role> roleList){
        for(Role role : roleList){
            System.out.print(role.getId() + ",");
        }
        System.out.println();
    }
}
