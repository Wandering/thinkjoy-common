package cn.thinkjoy;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Cnd;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingCondition;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.PagingResult;
import cn.thinkjoy.dao.IRoleDAO;
import cn.thinkjoy.domain.Role;
import com.alibaba.fastjson.JSONObject;
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
	private IRoleDAO roleDAO;

	@Test
	public void test(){
//		System.out.println(roleDAO.findOne("id", 1, null, null).getName());

		try {
			//findByCriteria
			List<Role> list1 = roleDAO.findByCriteria(Cnd.builder(Role.class).andEQ("id", 1).buildCriteria());
			System.out.println(list1.size()+"-----");

			List<Role> list2 = roleDAO.findByCriteria(Cnd.builder(Role.class).andLike("name", "测试").buildCriteria());
			System.out.println(list2.size()+"-----");

			List<Role> list3 = roleDAO.findByCriteria(Cnd.builder(Role.class).andEQ("creator", 1).andLike("name", "测试").buildCriteria());
			System.out.println(list3.size()+"-----");

			//countByCriteria
			int countNum = roleDAO.countByCriteria(Cnd.builder(Role.class).andLike("name", "测试").buildCriteria());
			System.out.println(countNum+"-----");

			//findOneByCriteria
			Role role = roleDAO.findOneByCriteria(Cnd.builder(Role.class).andLike("name", "测试").buildCriteria());
			System.out.println(JSONObject.toJSONString(role)+"-----");

			//pagingByCriteria
			PagingCondition page = new PagingCondition();
			page.setPageNo(1);
			page.setPageSize(2);
			Criteria criteria = Cnd.builder(Role.class).andEQ("creator", 1).pagination(page).buildCriteria();

			int records = roleDAO.countByCriteria(criteria);
			List<Role> result = roleDAO.pagingByCriteria(criteria);

			BizData4Page bizData4Page = new BizData4Page();
			bizData4Page.setRows(result);
			bizData4Page.setPage(criteria.getPagination().getPageNo());
			bizData4Page.setPagesize(criteria.getPagination().getPageSize());
			bizData4Page.setRecords(records);

			int total = records / criteria.getPagination().getPageSize();
			int mod = records % criteria.getPagination().getPageSize();
			if(mod > 0){
				total = total + 1;
			}
			bizData4Page.setTotal(total);

			System.out.println(JSONObject.toJSONString(bizData4Page)+"-----");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}