package cn.thinkjoy;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.impl.DefaultCriteria;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingCondition;
import cn.thinkjoy.domain.Product;
import cn.thinkjoy.domain.Role;
import cn.thinkjoy.service.IRoleService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})//, "classpath*:spring/spring-service.xml"
public class MainTest {

	@Autowired
	private IRoleService roleService;

	@Test
	public void test(){
//		System.out.println(roleDAO.findOne("id", 1, null, null).getName());

		try {
//			List<Role> list0 = roleService.queryPageByDataPerm()findByCriteria(Cnd.builder(Role.class).andEQ("id", 1).buildCriteria());
//			System.out.println(list1.size()+"-----");

			//findByCriteria
//			List<Role> list1 = roleService.findByCriteria(Cnd.builder(Role.class).andEQ("id", 1).buildCriteria());
//			System.out.println(list1.size()+"-----");
//
//			List<Role> list2 = roleService.findByCriteria(Cnd.builder(Role.class).andLike("name", "测试").buildCriteria());
//			System.out.println(list2.size()+"-----");
//
			List<Role> list3 = roleService.findByCriteria(Cnd.builder(Role.class).buildCriteria().limit(0L,5L));
			System.out.println(list3.size()+"-----");

			int i = roleService.deleteByCriteria(Cnd.builder(Role.class).andEQ("id", 99).buildCriteria().limit(0L, 5L));
			System.out.println(i+"-----");

			Criteria criteria = Cnd.builder(Role.class).andEQ("id", 1).buildCriteria().addUpdateParam("description", "测试修改2");
			int j = roleService.updateByCriteria(criteria);
			System.out.println(j+"-----");

//
//			//countByCriteria
//			int countNum = roleService.countByCriteria(Cnd.builder(Role.class).andLike("name", "测试").buildCriteria());
//			System.out.println(countNum+"-----");
//
//			//findOneByCriteria
//			Role role = roleService.findOneByCriteria(Cnd.builder(Role.class).andLike("name", "测试").buildCriteria());
//			System.out.println(JSONObject.toJSONString(role)+"-----");
//
//			//pagingByCriteria
//			PagingCondition page = new PagingCondition(1, 2);
//			Criteria criteria = Cnd.builder(Role.class).andEQ("creator", 1).pagination(page).buildCriteria();
//			BizData4Page bizData4Page = roleService.pagingByCriteria(criteria);
//			System.out.println(JSONObject.toJSONString(bizData4Page)+"-----");
//
//			//queryPage
//			Map<String, Object> conditions = new HashMap<>();
//			List<Role> roles = roleService.queryPage(conditions, 2, 4);
//			System.out.println(JSONObject.toJSONString(roles)+"-----");
//
//			//queryPageByDataPerm
//			BizData4Page<Role> bizData = roleService.queryPageByDataPerm(null, conditions, 2, 2, 4);
//			System.out.println(JSONObject.toJSONString(bizData)+"-----");

//			Role role = roleService.findOneByCriteria(Cnd.builder(Product.class).andEQ("id", 1).buildCriteria());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}