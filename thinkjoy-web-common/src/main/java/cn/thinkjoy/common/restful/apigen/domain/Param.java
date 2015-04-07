package cn.thinkjoy.common.restful.apigen.domain;

/**
 * 参数模型
 * <p/>
 * 创建时间: 15/4/4 下午3:45<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class Param {
    private String name;
    private String type;
    private boolean required;
    private String show;
    private String defaultVal;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
