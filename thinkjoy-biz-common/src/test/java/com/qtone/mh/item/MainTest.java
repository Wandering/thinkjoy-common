package com.qtone.mh.item;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import com.qtone.mh.item.dao.AdcProductDAO;
import com.qtone.mh.item.entity.AdcProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})//, "classpath*:spring/spring-service.xml"
public class MainTest {
	@Autowired
	private AdcProductDAO dao;

	@Test
	public void test(){

		try {
			List<AdcProduct> products = dao.findByCriteria(Cnd.builder(AdcProduct.class).andEQ("id", 1).buildCriteria());
			System.out.println(products.size()+"-----");

			List<AdcProduct> products2 = dao.findByCriteria(Cnd.builder(AdcProduct.class).andEQ("id", 1).andLike("linkUrl", "www").buildCriteria());
			System.out.println(products2.size()+"-----");

			List<AdcProduct> products3 = dao.findByCriteria(Cnd.builder(AdcProduct.class).andEQ("id", 1).andEQ("name", "成长帮手").andLike("linkUrl", "www").buildCriteria());
			System.out.println(products3.size()+"-----");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}