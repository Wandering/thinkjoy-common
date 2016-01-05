package cn.thinkjoy;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import cn.thinkjoy.dao.impl.ProductDAOImpl;
import cn.thinkjoy.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})//, "classpath*:spring/spring-service.xml"
public class MainTest2 {
	@Autowired
	private ProductDAOImpl dao;

	@Test
	public void test(){

		try {
			List<Product> products = dao.findByCriteria(Cnd.builder(Product.class).andEQ("id", 1).buildCriteria());
			System.out.println(products.size()+"-----");

			List<Product> products2 = dao.findByCriteria(Cnd.builder(Product.class).andEQ("id", 1).andLike("linkUrl", "www").buildCriteria());
			System.out.println(products2.size()+"-----");

			List<Product> products3 = dao.findByCriteria(Cnd.builder(Product.class).andEQ("id", 1).andEQ("name", "成长帮手").andLike("linkUrl", "www").buildCriteria());
			System.out.println(products3.size()+"-----");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}