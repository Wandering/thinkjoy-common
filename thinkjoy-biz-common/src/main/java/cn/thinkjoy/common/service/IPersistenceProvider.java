package cn.thinkjoy.common.service;

import java.util.Map;

/**
 * 持久化提供者接口,用于进行 CRU 操作  只需要重载  insertMap updateMap
 * <p/>
 * 创建时间: 16/1/10 上午11:09<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IPersistenceProvider extends IBaseService{

    /**
     * 数据表单校验扩展
     * @param dataMap
     */
    void verifyData(Map<String, Object> dataMap);
}
