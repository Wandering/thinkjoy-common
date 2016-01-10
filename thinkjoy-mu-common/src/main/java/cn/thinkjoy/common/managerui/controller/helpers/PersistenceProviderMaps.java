package cn.thinkjoy.common.managerui.controller.helpers;

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
public class PersistenceProviderMaps {
    protected final Map<String, IPersistenceProvider> providerMap = Maps.newHashMap();


    public IPersistenceProvider get(BaseServiceMaps serviceMaps, String mainObj){
        IPersistenceProvider persistenceProvider = providerMap.get(mainObj);
        if(persistenceProvider != null){
            return persistenceProvider;
        }
        return (IPersistenceProvider) serviceMaps.get(mainObj);
    }
}
