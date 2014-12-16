package cn.thinkjoy.common.domain;

/**
 * 数据状态
 * <p/>
 * 创建时间: 14/10/31 下午5:44<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public enum StatusEnum {
    N(0, "新增"),
    U(1, "更新"),
    D(-1, "删除");

    private int code;
    private String name;

    private StatusEnum(int code, String name){
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
