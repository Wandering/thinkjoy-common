package cn.thinkjoy;

import cn.thinkjoy.common.restful.apigen.annotation.ApiPropDesc;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/17 下午10:10<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DTOTest {
    @ApiPropDesc("名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
