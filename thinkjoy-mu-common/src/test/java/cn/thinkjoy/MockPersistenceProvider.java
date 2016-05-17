package cn.thinkjoy;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.managerui.service.IRoleService;
import cn.thinkjoy.common.managerui.service.IRoleUserService;
import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.service.IPersistenceProvider;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 16/1/10 上午11:43<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class MockPersistenceProvider implements IPersistenceProvider {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleUserService roleUserService;

    @Override
    public IBaseDAO getDao() {
        return null;
    }

    @Override
    public int add(BaseDomain entity) {
        return 0;
    }

    @Override
    public int edit(BaseDomain entity) {
        return 0;
    }

    @Override
    public int delete(Object id) {
        return 0;
    }

    @Override
    public BaseDomain view(Object id) {
        return null;
    }

    @Override
    public int insert(BaseDomain entity) {
        return 0;
    }

    @Override
    public int update(BaseDomain entity) {
        return 0;
    }

    @Override
    public int updateNull(BaseDomain entity) {
        return 0;
    }

    @Override
    public int deleteById(Object id) {
        return 0;
    }

    @Override
    public int deleteByIds(List list) {
        return 0;
    }

    @Override
    public int deleteByProperty(String property, Object value) {
        return 0;
    }

    @Override
    public BaseDomain fetch(Object id) {
        return null;
    }

    @Override
    public BaseDomain findOne(String property, Object value) {
        return null;
    }

    @Override
    public List findList(String property, Object value) {
        return null;
    }

    @Override
    public List findList(String property, Object value, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List findAll(String orderBy, SqlOrderEnum sqlOrderEnum) {
        return null;
    }

    @Override
    public Object selectMaxId() {
        return null;
    }

    @Override
    public void updateOrSave(BaseDomain baseDomain, Object id) {

    }

    @Override
    public BaseDomain selectOne(String mapperId, Object obj) {
        return null;
    }

    @Override
    public List selectList(String mapperId, Object obj) {
        return null;
    }

    @Override
    public int updateByCondition(Map updateMap, Map conditionMap) {

        return 0;
    }

    @Override
    public int count(Map condition) {
        return 0;
    }

    @Override
    public BaseDomain queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map selector) {
        return null;
    }

    @Override
    public BaseDomain queryOne(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return null;
    }

    @Override
    public BaseDomain queryOne(Map condition) {
        return null;
    }

    @Override
    public List queryList(Map condition, String orderBy, String sortBy, Map selector) {
        return null;
    }

    @Override
    public List queryList(Map condition, String orderBy, String sortBy) {
        return null;
    }

    @Override
    public List like(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum, Map selector) {
        return null;
    }

    @Override
    public List like(Map condition, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return null;
    }

    @Override
    public List like(Map condition) {
        return null;
    }

    @Override
    public List queryPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum, Map selectorpage) {
        return null;
    }

    @Override
    public List queryPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return null;
    }

    @Override
    public List queryPage(Map condition, int offset, int rows) {
        return null;
    }

    @Override
    public int deleteByCondition(Map condition) {
        return 0;
    }

    @Override
    public int updateMap(Map entityMap) {
        return 0;
    }

    @Override
    public int insertMap(Map entityMap) {
        //other biz logic process


        roleService.updateMap(entityMap);
        roleUserService.updateMap(entityMap);


        return 0;
    }

    @Override
    public List listByPage(Map condition, int offset, int rows, String orderBy, SqlOrderEnum sqlOrderEnum) {
        return null;
    }

    @Override
    public List listByPage(Map condition, int offset, int rows) {
        return null;
    }

    @Override
    public void verifyData(Map<String, Object> dataMap) {

    }

    @Override
    public List findByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public BaseDomain findOneByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public int updateByCriteria(Criteria criteria) {
        return 0;
    }

    @Override
    public int deleteByCriteria(Criteria criteria) {
        return 0;
    }
}
