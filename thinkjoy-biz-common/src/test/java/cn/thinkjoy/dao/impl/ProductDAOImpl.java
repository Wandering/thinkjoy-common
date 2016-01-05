package cn.thinkjoy.dao.impl;

import cn.thinkjoy.common.mybatis.core.mybatis.dao.impl.SimpleMyBatisDAO;
import cn.thinkjoy.dao.IProductDAO;
import cn.thinkjoy.domain.Product;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author shadow
 * @email 124010356@qq.com
 * @create 2015-12-29 13:19
 * 
 */
@Repository
public class ProductDAOImpl extends SimpleMyBatisDAO<Product, Long> implements IProductDAO {


}