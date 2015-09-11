package cn.thinkjoy.common.domain;

/**
 * 业务状态   正常 10 、  停用 20 、  逻辑删除 30
 * <p/>
 * 创建时间: 15/9/2 上午10:14<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public enum BizStatusEnum {
    N(10, "正常"),
    S(20, "停用"),
    D(30, "逻辑删除");

    private int code;
    private String name;

    private BizStatusEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
