package com.qtone.mh.item;

import cn.thinkjoy.MVCException;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import com.qtone.mh.item.dao.AdcProductDAO;
import com.qtone.mh.item.entity.AdcProduct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainTest {

	public static void main(String args[]) {
		String paths[] = { "application.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		AdcProductDAO dao = ctx.getBean(AdcProductDAO.class);
		try {
			
			List<AdcProduct> products = dao.findByCriteria(Cnd.builder(AdcProduct.class).andEQ("id", 1).buildCriteria());
			System.out.println(products.size()+"-----");
			
			List<AdcProduct> products2 = dao.findByCriteria(Cnd.builder(AdcProduct.class).andEQ("id", 1).andLike("linkUrl", "www").buildCriteria());
			System.out.println(products2.size()+"-----");
			
			List<AdcProduct> products3 = dao.findByCriteria(Cnd.builder(AdcProduct.class).andEQ("id", 1).andEQ("name", "成长帮手").andLike("linkUrl", "www").buildCriteria());
			System.out.println(products3.size()+"-----");
		
		} catch (MVCException e) {
			e.printStackTrace();
		}
	}
}
