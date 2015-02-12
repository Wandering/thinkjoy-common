package cn.thinkjoy.cn.thinkjoy.helpers;

//import cn.thinkjoy.common.managerui.HashedDataSource;
import cn.thinkjoy.common.managerui.dao.IRoleDAO;
import cn.thinkjoy.common.managerui.domain.Role;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        roleDAO.findOne("sid", 11);
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("sid", 1);
        roleDAO.queryOne(paramMap);
        System.out.println(roleDAO.findAll());

        Role role = new Role();
        role.setId(1L);
        role.setCreateDate(System.currentTimeMillis());
        int affectRow = roleDAO.update(role);

        System.out.println(affectRow);
    }
}
