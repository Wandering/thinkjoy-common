import cn.thinkjoy.MVCException;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import cn.thinkjoy.dao.IRoleDAO;
import cn.thinkjoy.domain.Role;
import com.qtone.mh.item.dao.AdcProductDAO;
import com.qtone.mh.item.entity.AdcProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})//, "classpath*:spring/spring-service.xml"
public class MainTest {
	@Autowired
	private IRoleDAO roleDAO;

	@Autowired
	private AdcProductDAO dao;

	@Test
	public void test(){
//		System.out.println(roleDAO.findOne("id", 1, null, null).getName());

		try {

			List<AdcProduct> products0 = dao.findByCriteria(Cnd.builder(AdcProduct.class).andEQ("id", 1).buildCriteria());
			System.out.println(products0.size()+"-----");

			List<Role> products = roleDAO.findByCriteria(Cnd.builder(Role.class).andEQ("id", 1).buildCriteria());
			System.out.println(products.size()+"-----");

//			List<Role> products2 = roleDAO.findByCriteria(Cnd.builder(Role.class).andEQ("id", 1).andLike("linkUrl", "www").buildCriteria());
//			System.out.println(products2.size()+"-----");
//
//			List<Role> products3 = roleDAO.findByCriteria(Cnd.builder(Role.class).andEQ("id", 1).andEQ("name", "成长帮手").andLike("linkUrl", "www").buildCriteria());
//			System.out.println(products3.size()+"-----");

		} catch (MVCException e) {
			e.printStackTrace();
		}
	}
}