package cn.thinkjoy;

import cn.thinkjoy.dao.IRoleDAO;
import cn.thinkjoy.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/27 下午8:30<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springme.xml")
public class MainTest {
    @Autowired(required = false)
    private IRoleDAO roleDAO;

    @Test
    public void test(){
        System.out.println(roleDAO.findOne("id", 1, null, null).getName());

        Role role = new Role();
        role.setName("rw test");
        roleDAO.insert(role);
    }
}
