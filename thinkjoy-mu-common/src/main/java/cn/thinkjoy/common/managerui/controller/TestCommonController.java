package cn.thinkjoy.common.managerui.controller;

import cn.thinkjoy.common.managerui.controller.helpers.BaseServiceMaps;
import cn.thinkjoy.common.service.IBaseService;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14-10-3 下午8:19<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class TestCommonController extends AbstractCommonController {
    @Override
    protected BaseServiceMaps getServiceMaps() {
        return null;
    }

    @Override
    protected IBaseService getExportService() {
        return null;
    }

    @Override
    public boolean getEnableDataPerm() {
        return false;
    }
}
