package cn.thinkjoy.common.managerui.controller.helpers;

import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPersistenceProvider;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 16/1/10 上午11:11<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class BasePersistenceProviderMaps {
    protected final Map<String, IPersistenceProvider> providerMap = Maps.newHashMap();


    public IBaseService get(BaseServiceMaps serviceMaps, String mainObj){
        IPersistenceProvider persistenceProvider = providerMap.get(mainObj);
        if(persistenceProvider != null){
            return persistenceProvider;
        }
        return serviceMaps.get(mainObj);
    }

    public IPersistenceProvider get(String mainObj){
        return providerMap.get(mainObj);
    }
}
