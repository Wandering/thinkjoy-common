package cn.thinkjoy.common.domain;

/**
 * 排序规则
 * <p/>
 * 创建时间: 15/3/10 上午9:21<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class OrderField {
    /* 排序字段名称 */
    private String field;

    /* 排序符 == > < */
    private String op;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}